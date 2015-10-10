import java.util.Arrays;

/**
 * Created by pratikku on 22/8/15.
 */
public class Jewelry {
    static long[][] nCk;

    private static void nk(int n){
        nCk = new long[n+1][n+1];
        nCk[0][0] = 1;

        for(int i=1; i<=n; ++i){
            for(int j=0; j<=n; ++j){
                nCk[i][j] += nCk[i-1][j];
                if(j>0)
                    nCk[i][j]+=nCk[i-1][j-1];
            }
        }
    }

    private static void calc(int[][] bf, int[] val){
        bf[0][0] = 1;
        for(int i=1; i<=val.length; ++i){
            for(int j=0; j<(bf[i].length-val[i-1]); ++j){
                bf[i][j]+=bf[i-1][j];
                bf[i][j+val[i-1]] += bf[i-1][j];
            }
        }

        /*System.out.println("-----------------------------------------------------------------");
        for(int i=0; i<=val.length; ++i){
            for(int j=0; j<bf[i].length; ++j) {
                if(bf[i][j]>0) {
                    System.out.print(j+" "+bf[i][j] + ", ");
                }
            }
            System.out.format("%n");
        }
        */

    }

    private static void reverseArray(int[] arr){
        for(int i=0; i<=((arr.length/2)-1); ++i){
            int temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }
    }

    private static void printArr(int[] val){
        for(int i=0; i<val.length; ++i)
            System.out.print(val[i]+" ");
        System.out.format("%n");
    }

    public static long howMany(int[] values){
        int max_sum=0;
        for(int i : values){
            max_sum += i;
        }
        //System.out.println("max_sum: "+max_sum);
        nk(values.length);
        int[][] Bob = new int[values.length+1][max_sum+1];
        int[][] Frank = new int[values.length+1][max_sum+1];

        Arrays.sort(values);
        //printArr(values);
        calc(Bob, values);
        reverseArray(values);
        //printArr(values);
        calc(Frank, values);
        reverseArray(values);
        //printArr(values);


        long numWays=0;
        int i=0;
        while(i<values.length){
            int j;
            for(j=i+1;(j<values.length)&&(values[j]==values[i]);++j);
            int c = j-i;
            for(int k=1; k<=c; ++k){
                for(int l=0; l<=max_sum-(k*values[i]); ++l){
                    numWays += Bob[i][l] * Frank[values.length -(i+k)][l+k*values[i]] * nCk[c][k];
                    /*if((Bob[i][l] * Frank[values.length -(i+k)][l+k*values[i]] * nCk[c][k]) > 0){
                        System.out.print("Bob: "+i+" "+l+" "+" "+Bob[i][l]);
                        System.out.format("%n");
                        System.out.print("Frank: " + (values.length - (i + k)) + " " + (l + k * values[i]) + " " + Frank[values.length - (i + k)][l + k * values[i]]);
                        System.out.format("%n");
                    }*/
                }
            }
            i += c;
        }
        return numWays;
    }

    public static void main(String[] args){
        System.out.println(Jewelry.howMany(new int[]{1,2,5,3,4,5}));
        System.out.println(Jewelry.howMany(new int[]{1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000}));
        System.out.println(Jewelry.howMany(new int[]{1,2,3,4,5}));
        System.out.println(Jewelry.howMany(new int[]{7,7,8,9,10,11,1,2,2,3,4,5,6}));
        System.out.println(Jewelry.howMany(new int[]{123,217,661,678,796,964,54,111,417,526,917,923}));
        System.out.println(Jewelry.howMany(new int[]{1, 1, 1, 1, 1}));
        System.out.println(Jewelry.howMany(new int[]{1, 2, 3}));
        System.out.println(Jewelry.howMany(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}));
        System.out.println(Jewelry.howMany(new int[]{1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81}));
        System.out.println(Jewelry.howMany(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6}));

    }
}
