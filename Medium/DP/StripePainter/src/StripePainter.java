import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pratikku on 22/8/15.
 */
public class StripePainter {
    public static int minStrokes(String stripes){
        int[][] nn = new int[stripes.length()][stripes.length()];
        int n = stripes.length();
        for(int i=0; i<n; ++i){
            nn[i][i] = 1;
        }
        for(int j=1; j<n; ++j){
            for(int i=0; i<j; ++i){
                nn[i][j] = nn[i][j-1]+1;
                ArrayList<Integer> k = new ArrayList<>(); //positions of last occurence of char at j
                for(int p = j-1; p>=i; --p){
                    if(stripes.charAt(p) == stripes.charAt(j)){
                        k.add(p);
                    }
                }
                if(!k.isEmpty()){
                    if(k.get(0) == j-1){
                        nn[i][j] = nn[i][j-1];
                    }
                    else{
                        for(int l : k){
                            int strokes = nn[i][l] + nn[l][j-1] - 1;
                            if(nn[i][j] > strokes)
                                nn[i][j] = strokes;
                        }
                    }
                }
            }
        }

        return nn[0][n-1];
    }


    private static void print2Darr(int[][] arr){
        for(int i=0; i<arr.length; ++i){
            for(int j=0; j<arr[i].length; ++j){
                System.out.print(arr[i][j]+" ");
            }
            System.out.format("%n");
        }
    }


    public static void main(String[] args){
        System.out.println(StripePainter.minStrokes("RGBGR"));
        System.out.println(StripePainter.minStrokes("RGRG"));
        System.out.println(StripePainter.minStrokes("ABACADA"));
        System.out.println(StripePainter.minStrokes("AABBCCDDCCBBAABBCCDD"));
        System.out.println(StripePainter.minStrokes("BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD"));
        System.out.println(StripePainter.minStrokes("AAA"));
    }
}
