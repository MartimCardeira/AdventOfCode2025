import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    public static void main(String[] args) throws IOException {
        //leading zero is invalid
        //id composed of the concatenatation of two identical numbers is also invalid
        long sum = 0;
        FileReader input = new FileReader("AdventOfCode/data/day2_input");
        BufferedReader bufRead = new BufferedReader(input);
        String str = bufRead.readLine();
        String[] ranges = str.split(",");
        for (String range : ranges) {
            String[] startAndEnd = range.split("-");
            long start = Long.parseLong(startAndEnd[0]);
            long end = Long.parseLong(startAndEnd[1]);
            for(int i = 0; i <= end-start; i++) {
                long current = start + i;
                if(!isValid(Long.toString(start + i))){
                    sum += current;
                }
            }
        }

        System.out.println(sum);
    }

    public static boolean isValid(String str){
        if(str.charAt(0) == '0') return false;
        //other logic
        if(str.length() % 2 == 0){
            //two pointer, check if they have common substring
            int i = 0;
            int j = str.length()/2;
            while(j < str.length()){
                if(str.charAt(i) != str.charAt(j)) return true;
                i++;
                j++;
            }
            return false;
        }

        return true;
    }
}
