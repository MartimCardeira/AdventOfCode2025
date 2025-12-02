import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
                if(!isValidPart2(Long.toString(start + i))){
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

    public static boolean isValidPart2(String str){
        for(int i = 1; i <= str.length()/2; i++){
            //the length of the substring is i+1
            if(str.length() % i != 0) continue;
            String pattern = str.substring(0, i);
            int id = 0; //str pointer
            int j = 0; //pattern pointer
            boolean stoppedEarly = false;
            while(id < str.length()){
                if(j == pattern.length()) j = 0; //start the pattern again
                if(str.charAt(id) != pattern.charAt(j)){
                    stoppedEarly = true;
                    break; //str not a concatenation of this particular pattern
                }
                id++;
                j++;
            }

            if(!stoppedEarly){
                return false; //str finishes with last digit of pattern, is a a concatenation, must be invalid
            }
        }
        return true;
    }
}
