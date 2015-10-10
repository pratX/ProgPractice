import java.util.Scanner;

/**
 * Created by pratikku on 16/8/15.
 */
public class AvoidRoads {
    public static long numWays(int width, int height, String[] bad){
        long[][] nw = new long[height+1][width+1];
        int[][][] avoid = new int[height+1][width+1][2];
        /*
        Approachability:
        0: Approach from left
        1: Approach from up
         */

        nw[0][0] = 1;
        for(int i=0; i<=height; ++i){
            for(int j=0; j<=width; ++j){
                //Is i,j it approachable from left
                if(j>0)
                    avoid[i][j][0]=1;
                //Is i,j approachable from up
                if(i>0)
                    avoid[i][j][1]=1;
            }
        }
        for(int i=0; i<bad.length; ++i){
            Scanner sc = new Scanner(bad[i]);
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            if(x1 == x2){
                if(y1<y2)
                    avoid[y2][x2][1]=0;
                if(y1>y2)
                    avoid[y1][x1][1]=0;
            }
            if(y1 == y2){
                if(x1<x2)
                    avoid[y2][x2][0]=0;
                if(x1>x2)
                    avoid[y1][x1][0]=0;
            }
        }

        for(int i=0; i<=height; ++i){
            for(int j=0; j<=width; ++j){
                if(avoid[i][j][0]==1)
                    nw[i][j]+=nw[i][j-1];
                if(avoid[i][j][1]==1)
                    nw[i][j]+=nw[i-1][j];
            }
        }

        return nw[height][width];
    }

    public static void main(String[] args){
        System.out.println(AvoidRoads.numWays(6,6,new String[]{"0 0 0 1","6 6 5 6"}));
        System.out.println(AvoidRoads.numWays(1,1,new String[]{}));
        System.out.println(AvoidRoads.numWays(35,31,new String[]{}));
        System.out.println(AvoidRoads.numWays(2,2,new String[]{"0 0 1 0","1 2 2 2","1 1 2 1"}));
    }
}
