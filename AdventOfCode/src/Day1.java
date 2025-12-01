import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {
    static void main() throws IOException {
        FileReader input = new FileReader("AdventOfCode/data/input1");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
        int pos = 50;
        int numZeroes = 0;

        while ( (myLine = bufRead.readLine()) != null){
            char direction =  myLine.charAt(0);
            String[] splitString = myLine.split("[LR]");
            int diff = Integer.parseInt(splitString[1]);
            int timesPassedZero = 0;
            while(diff > 100){
                diff -= 100;
                timesPassedZero++;
            }
            if (direction == 'L') {
                if(pos - diff < 0){
                    timesPassedZero++;
                    pos = 100 - Math.abs((pos - diff) % 100);
                }
                else{
                    pos -= diff;
                }

            }
            else if (direction == 'R') {
                if(pos + diff >= 100) timesPassedZero++;
                pos = Math.abs((pos + diff) % 100);
            }
            numZeroes += timesPassedZero;
        }

        System.out.println(numZeroes);
    }
}
