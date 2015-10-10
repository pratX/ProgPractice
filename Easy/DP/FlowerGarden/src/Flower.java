/**
 * Created by pratikku on 16/8/15.
 */
public class Flower implements Comparable<Flower>{
    int h;
    int b;
    int w;
    int score;
    Flower(int he, int bl, int wi){
        h = he;
        b = bl;
        w = wi;
        score = he;
    }

    public int compareTo(Flower f){
        if(this.h < f.h)
            return -1;
        if(this.h == f.h)
            return 0;
        return 1;
    }
}
