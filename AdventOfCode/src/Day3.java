import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 {
    static void main() throws IOException {
        long sum = 0;
        FileReader input = new FileReader("AdventOfCode/data/day3_input");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine;
        while ( (myLine = bufRead.readLine()) != null){
            int[] nums = new int[myLine.length()];
            int max = 0;
            for(int i = 0; i < myLine.length(); i++){
                char ch = myLine.charAt(i);
                nums[i] = Integer.parseInt(String.valueOf(ch));
            }
            //int[] result = divide(nums, 0, nums.length);
            //sum += result[0]* 10L + result[1];
            //for(int i = 0; i < myLine.length(); i++){
            //    for(int j = i +1; j < myLine.length(); j++){
            //        max = Math.max(nums[i]*10 + nums[j], max);
            //    }
            //}
            List<Integer> acc = new ArrayList<>();
            finder(nums, 0, 12, acc);
            long builder = 0L;
            while(acc.size() > 0){
                builder = builder*10L + acc.removeFirst();
            }
            sum += builder;
        }

        System.out.println(sum);
    }

    static int[] divide(int[] nums, int start, int end){
        //start index inclusive, end exclusive
        int n =  end - start;
        if(n == 1) return new int[]{nums[0], nums[0]};
        int mid = n/2;
        int[] left = divide(nums, start, mid);
        int[] right = divide(nums, mid, end);
        return new int[]{Math.max(left[0], left[1]), right[0], right[1]};
    }

    static void finder(int[] nums, int start, int remainingDigits, List<Integer> acc){
        //last possible candidate found at n-12;
        if(remainingDigits == 0){
            return;
        }
        int lastIndex = -1;
        int max = -1;
        for(int i = start; i <= nums.length - remainingDigits; i++){
            if(nums[i] > max){
                max = nums[i];
                lastIndex = i;
            }
        }
        acc.add(max);
        finder(nums, lastIndex + 1, remainingDigits - 1, acc);
    }
}
