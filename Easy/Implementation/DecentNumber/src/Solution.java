import java.util.Scanner;

/**
 * Created by pratikku on 29/9/15.
 */
public class Solution {
    public static String LargestDecentNumber(int N){
        int q = N/3;
        int num5 = 3*q;
        int num3 = N-num5;
        int flag=0;
        while(q>=0){
            if((num3 % 5) == 0){
                flag=1;
                break;
            }
            num5 = 3 * (--q);
            num3 = N - num5;
        }
        if(flag == 1) {
            StringBuilder s = new StringBuilder();
            for(int i=1; i<=num5; ++i){
                s.append("5");
            }
            for(int i=1; i<=num3; ++i){
                s.append("3");
            }
            return s.toString();
        }
        else
            return "-1";
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T>=1){
            System.out.println(LargestDecentNumber(sc.nextInt()));
            --T;
        }
    }
}
