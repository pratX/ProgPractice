import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by pratikku on 29/9/15.
 */
public class Solution {
    public static String factorial(int num){
        BigInteger f = new BigInteger("1");
        for(int i=2; i<=num; ++i){
            f = f.multiply(new BigInteger(Integer.toString(i)));
        }
        return f.toString();
    }

    public static void main(String[] args){
        Scanner sc =  new Scanner(System.in);
        System.out.println(factorial(sc.nextInt()));
    }
}
