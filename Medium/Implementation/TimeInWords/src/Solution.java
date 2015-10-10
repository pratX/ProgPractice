import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by pratikku on 29/9/15.
 */
public class Solution {
    private static HashMap<Integer, String> hour;
    private static HashMap<Integer, String> minute;

    static void initialize(){
        hour = new HashMap<>();
        hour.put(1, "one");
        hour.put(2, "two");
        hour.put(3, "three");
        hour.put(4, "four");
        hour.put(5, "five");
        hour.put(6, "six");
        hour.put(7, "seven");
        hour.put(8, "eight");
        hour.put(9, "nine");
        hour.put(10, "ten");
        hour.put(11, "eleven");
        hour.put(12, "twelve");

        minute = new HashMap<>();
        minute.put(1, "one minute");minute.put(2, "two minutes");
        minute.put(3, "three minutes");minute.put(4, "four minutes");
        minute.put(5, "five minutes");minute.put(6, "six minutes");
        minute.put(7, "seven minutes");minute.put(8, "eight minutes");
        minute.put(9, "nine minutes");minute.put(10, "ten minutes");
        minute.put(11, "eleven minutes");minute.put(12, "twelve minutes");
        minute.put(13, "thirteen minutes");minute.put(14, "fourteen minutes");
        minute.put(15, "quarter");minute.put(16, "sixteen minutes");
        minute.put(17, "seventeen minutes");minute.put(18, "eighteen minutes");
        minute.put(19, "nineteen minutes");minute.put(20, "twenty minutes");
        minute.put(21, "twenty one minutes");minute.put(22, "twenty two minutes");
        minute.put(23, "twenty three minutes");minute.put(24, "twenty four minutes");
        minute.put(25, "twenty five minutes");minute.put(26, "twenty six minutes");
        minute.put(27, "twenty seven minutes");minute.put(28, "twenty eight minutes");
        minute.put(29, "twenty nine minutes");


    }
    static String timeInWords(int HH, int MM){
        StringBuilder time_str = new StringBuilder();
        if(MM > 30){
            int h = HH+1;
            if(HH == 12)
                h = 1;
            time_str.append(minute.get(60-MM));
            time_str.append(" to ");
            time_str.append(hour.get(h));
        }
        else if (MM == 30){
            time_str.append("half past ");
            time_str.append(hour.get(HH));
        }
        else if (MM == 0){
            time_str.append(hour.get(HH));
            time_str.append(" o' clock");
        }
        else {
            time_str.append(minute.get(MM));
            time_str.append(" past ");
            time_str.append(hour.get(HH));
        }

        return time_str.toString();
    }

    public static void main(String[] args){
        Solution.initialize();
        Scanner sc = new Scanner(System.in);
        System.out.println(timeInWords(sc.nextInt(), sc.nextInt()));
    }
}






