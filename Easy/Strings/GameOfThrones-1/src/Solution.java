import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by pratikku on 29/9/15.
 */
public class Solution {
    static String isPalindrome(String s){
        HashMap<Character, Integer> char_freq = new HashMap<>();
        for(int i=0; i<s.length(); ++i){
            Character key = s.charAt(i);
            if(char_freq.containsKey(key)){
                char_freq.put(key, char_freq.get(key)+1);
            }
            else {
                char_freq.put(key, 1);
            }
        }
        if(s.length()%2 == 0){
            for( Map.Entry<Character, Integer> me : char_freq.entrySet()){
                if(me.getValue()%2 != 0)
                    return "NO";
            }
        }
        else {
            int odd_flag = 0;
            for(Map.Entry<Character, Integer> me : char_freq.entrySet()){
                if(me.getValue()%2 != 0){
                    if (odd_flag == 0)
                        odd_flag = 1;
                    else
                        return "NO";
                }
            }
        }

        return "YES";
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println(Solution.isPalindrome(sc.next()));
    }
}
