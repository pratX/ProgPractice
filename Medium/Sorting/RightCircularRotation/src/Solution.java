import java.util.Scanner;

/**
 * Created by pratikku on 30/9/15.
 */
public class Solution {
    static int[] rightRotate(int[] arr, int k){
        int[] new_arr = new int[arr.length];
        for(int i=0; i<arr.length; ++i){
            new_arr[(i+k)%arr.length] = arr[i];
        }
        return new_arr;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int Q = sc.nextInt();

        int[] arr = new int[N];
        for(int i=0; i<N; ++i)
            arr[i] = sc.nextInt();

        arr = rightRotate(arr, K);

        for(int i=0; i<Q; ++i){
            System.out.println(arr[sc.nextInt()]);
        }
    }
}
