import java.util.Scanner;

/**
 * Created by pratikku on 30/9/15.
 */
public class Solution {
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void partition(int[] arr){
        int l=1;
        int h = arr.length-1;
        while(l<=h){
            while(arr[l] < arr[0]) l++;
            while(arr[h] > arr[0]) h--;
            if(l<h){
                swap(arr, l, h);
            }
        }
        swap(arr, 0, h);
    }

    static void printArray(int[] arr){
        for(int i=0; i<arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.format("%n");
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; ++i){
            arr[i] = sc.nextInt();
        }
        partition(arr);
        //print arr
        printArray(arr);
    }
}
