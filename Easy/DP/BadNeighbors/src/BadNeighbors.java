/**
 * Created by pratikku on 15/8/15.
 */
public class BadNeighbors {
    public int maxDonations(int[] donations){
        int[] pzi = new int[donations.length];
        int[] pzni = new int[donations.length];
        int[] mdzi = new int[donations.length];
        int[] mdzni = new int[donations.length];
        for(int i=0; i<donations.length; ++i){
            if(i==0) {
                pzi[i] = 0;
                pzni[i] = -1;
                mdzi[i] = donations[i];
                mdzni[i] = 0;
            }
            else if((i == 1) || (i == (donations.length-1))){
                pzi[i] = -1;
                mdzi[i] = 0;
                pzni[i] = i;
                mdzni[i] = donations[i];
            }
            else{
                pzi[i] = 0;
                mdzi[i] = donations[0]+donations[i];
                pzni[i] = i;
                mdzni[i] = donations[i];
            }
        }

        for(int i=0; i<=(donations.length - 3); ++i){
            for(int j = i+2; j <= (donations.length-1); ++j){
                if(j != (donations.length-1)){
                    if(mdzi[j] < mdzi[i]+donations[j]){
                        mdzi[j] = mdzi[i]+donations[j];
                        pzi[j] = i;
                    }
                    if(mdzni[j] < mdzni[i]+donations[j]){
                        mdzni[j] = mdzni[i]+donations[j];
                        pzni[j] = i;
                    }
                }
                else{
                    if(mdzni[j] < mdzni[i]+donations[j]){
                        mdzni[j] = mdzni[i]+donations[j];
                        pzni[j] = i;
                    }
                }
            }
        }

        int maxd = mdzi[0];
        //System.out.println("0:"+md[0]);
        for(int i=1; i<donations.length; ++i){
            //System.out.println(i+":"+md[i]);
            if(maxd < mdzi[i])
                maxd = mdzi[i];
            if(maxd < mdzni[i])
                maxd = mdzni[i];
        }
        return maxd;
    }

    public static void main(String[] args){
        BadNeighbors bn = new BadNeighbors();
        System.out.println(bn.maxDonations(new int[]{10, 3, 2, 5, 7, 8}));
        System.out.println(bn.maxDonations(new int[]{11, 15}));
        System.out.println(bn.maxDonations(new int[]{7, 7, 7, 7, 7, 7, 7}));
        System.out.println(bn.maxDonations(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5}));
        System.out.println(bn.maxDonations(new int[]{94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,
                6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
                52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72}));
    }
}
