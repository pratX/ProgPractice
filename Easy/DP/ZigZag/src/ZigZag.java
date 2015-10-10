/**
 * Created by pratikku on 15/8/15.
 */
public class ZigZag {
    public int longestZigZag(int[] seq){
        int[] lpsp, lpsl, lnsp, lnsl;
        lpsp = new int[seq.length];
        lpsl = new int[seq.length];
        lnsp = new int[seq.length];
        lnsl = new int[seq.length];
        for(int i=0; i<seq.length; ++i){
            lpsp[i] = i;
            lpsl[i] = 1;
            lnsp[i] = i;
            lnsl[i] = 1;
        }

        for(int i=0; i<(seq.length-1); ++i){
            for(int j = i+1; j<seq.length; ++j){
                if(seq[i] < seq[j]){
                    if(lpsl[j] < lnsl[i]+1){
                        lpsl[j] = lnsl[i]+1;
                        lpsp[j] = i;
                    }
                }
                if(seq[i] > seq[j]){
                    if(lnsl[j] < lpsl[i]+1){
                        lnsl[j] = lpsl[i]+1;
                        lnsp[j] = i;
                    }
                }
            }
        }

        int maxl=1;
        for(int i=0; i<seq.length; ++i){
            if(lpsl[i] > maxl)
                maxl = lpsl[i];
            if(lnsl[i] > maxl)
                maxl = lnsl[i];
        }

        return maxl;
    }

    public static void main(String[] args){
        ZigZag zz = new ZigZag();
        System.out.println(zz.longestZigZag(new int[]{1, 7, 4, 9, 2, 5}));
        System.out.println(zz.longestZigZag(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        System.out.println(zz.longestZigZag(new int[]{44}));
        System.out.println(zz.longestZigZag(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(zz.longestZigZag(new int[]{ 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32}));
        System.out.println(zz.longestZigZag(new int[]{374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }));
    }
}
