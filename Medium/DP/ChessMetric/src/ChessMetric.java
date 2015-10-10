/**
 * Created by pratikku on 17/8/15.
 */
public class ChessMetric {
    public static long howMany(int size, int[] start, int[] end, int numMoves){
        long[][][] nw = new long[size][size][numMoves+1];
        nw[start[0]][start[1]][0]=1;
        for(int i=1; i<=numMoves; ++i){
            for(int j=0; j<size; ++j){
                for(int k=0; k<size; ++k){
                    //Immediate neighbours
                    //Up
                    if(j>0)
                        nw[j][k][i]+=nw[j-1][k][i-1];
                    //Down
                    if(j<(size-1))
                        nw[j][k][i]+=nw[j+1][k][i-1];
                    //Left
                    if(k>0)
                        nw[j][k][i]+=nw[j][k-1][i-1];
                    //Right
                    if(k<(size-1))
                        nw[j][k][i]+=nw[j][k+1][i-1];
                    //UpperLeft
                    if(j>0 && k>0)
                        nw[j][k][i]+=nw[j-1][k-1][i-1];
                    //LowerLeft
                    if(j<(size-1) && k>0)
                        nw[j][k][i]+=nw[j+1][k-1][i-1];
                    //UpperRight
                    if(j>0 && k<(size-1))
                        nw[j][k][i]+=nw[j-1][k+1][i-1];
                    //LowerRight
                    if(j<(size-1) && k<(size-1))
                        nw[j][k][i]+=nw[j+1][k+1][i-1];
                    //L-Neigbours
                    //2Left1Up
                    if(j>0 && k>1)
                        nw[j][k][i]+=nw[j-1][k-2][i-1];
                    //2Left1Down
                    if(j<(size-1) && k>1)
                        nw[j][k][i]+=nw[j+1][k-2][i-1];
                    //2Right1Up
                    if(j>0 && k<(size-2))
                        nw[j][k][i]+=nw[j-1][k+2][i-1];
                    //2Right1Down
                    if(j<(size-1) && k<(size-2))
                        nw[j][k][i]+=nw[j+1][k+2][i-1];
                    //2Up1Left
                    if(j>1 && k>0)
                        nw[j][k][i]+=nw[j-2][k-1][i-1];
                    //2Up1Right
                    if(j>1 && k<(size-1))
                        nw[j][k][i]+=nw[j-2][k+1][i-1];
                    //2Down1Left
                    if(j<(size-2) && k>0)
                        nw[j][k][i]+=nw[j+2][k-1][i-1];
                    //2Down1Right
                    if(j<(size-2) && k<(size-1))
                        nw[j][k][i]+=nw[j+2][k+1][i-1];
                }
            }
        }

        return nw[end[0]][end[1]][numMoves];
    }

    public static void main(String[] args){
        System.out.println(ChessMetric.howMany(3,new int[]{0,0}, new int[]{1,0}, 1));
        System.out.println(ChessMetric.howMany(3,new int[]{0,0}, new int[]{1,2}, 1));
        System.out.println(ChessMetric.howMany(3,new int[]{0,0}, new int[]{2,2}, 1));
        System.out.println(ChessMetric.howMany(3,new int[]{0,0}, new int[]{0,0}, 2));
        System.out.println(ChessMetric.howMany(100,new int[]{0,0}, new int[]{0,99}, 50));
    }
}
