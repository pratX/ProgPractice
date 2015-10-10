import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by pratikku on 16/8/15.
 */
public class FlowerGarden {

    class FlowerScoreCompare implements Comparator<Flower>{
        public int compare(Flower f1, Flower f2){
            if(f1.score > f2.score)
                return -1;
            if(f1.score == f2.score){
                if(f1.h > f2.h)
                    return -1;
                else
                    return 1;
            }
            return 1;
        }
    }

    public int[] getOrdering(int[] height, int[] bloom, int[] wilt){
        Flower[] fs = new Flower[height.length];
        for(int i=0; i<height.length; ++i){
            fs[i] = new Flower(height[i], bloom[i], wilt[i]);
        }
        Arrays.sort(fs);
        for(int i=0; i<(fs.length/2); ++i){
            Flower temp = fs[i];
            fs[i] = fs[fs.length-1-i];
            fs[fs.length-1-i] = temp;
        }

        /*
        for(int i=0; i<fs.length; ++i){
            System.out.print(fs[i].h+" ");
        }
        System.out.format("%n");
        */

        for(int i=0; i<fs.length-1; ++i){
            if(i>0){
                for(int j=0; j<fs.length; ++j){
                    if(fs[j].score > fs[i].score)
                        fs[j].score+=2;
                    else if((fs[j].score == fs[i].score) && (j<i)){
                        fs[j].score+=2;
                    }
                }
            }
            for(int j=i+1; j<fs.length; ++j){
                if(intersection(fs[i], fs[j]) && (fs[j].score <= fs[i].score)){
                    fs[j].score = fs[i].score+1;
                }
            }
        }

        Arrays.sort(fs, new FlowerScoreCompare());
        int[] fg = new int[fs.length];
        for(int i=0; i<fs.length; ++i){
            fg[i] = fs[i].h;
        }
        printFG(fg);
        return fg;

    }

    private boolean intersection(Flower f1, Flower f2){
        if((f2.b>=f1.b && f2.b<=f1.w)||(f2.w>=f1.b && f2.w<=f1.w)||(f2.b<=f1.b && f2.w>=f1.w))
            return true;
        return false;
    }

    private void printFG(int[] fg){
        for(int i=0; i<fg.length; ++i){
            System.out.print(fg[i]+" ");
        }
        System.out.format("%n");
    }

    public static void main(String[] args){
        FlowerGarden fg = new FlowerGarden();
        fg.getOrdering(new int[]{5, 4, 3, 2, 1}, new int[]{1, 1, 1, 1, 1}, new int[]{365, 365, 365, 365, 365});
        fg.getOrdering(new int[]{5,4,3,2,1}, new int[]{1,5,10,15,20}, new int[]{4,9,14,19,24});
        fg.getOrdering(new int[]{5,4,3,2,1}, new int[]{1,5,10,15,20}, new int[]{5,10,15,20,25});
        fg.getOrdering(new int[]{5,4,3,2,1}, new int[]{1,5,10,15,20}, new int[]{5,10,14,20,25});
        fg.getOrdering(new int[]{1,2,3,4,5,6}, new int[]{1,3,1,3,1,3}, new int[]{2,4,2,4,2,4});
        fg.getOrdering(new int[]{3,2,5,4}, new int[]{1,2,11,10}, new int[]{4,3,12,13});

    }
}
