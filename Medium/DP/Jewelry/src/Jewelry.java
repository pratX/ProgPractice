import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by pratikku on 19/8/15.
 */
public class Jewelry {
    private static class Combination{
        long sum;
        TreeSet<Integer> ids;

        public Combination(Integer[] id, int[] vals){
            sum=0;
            ids = new TreeSet<Integer>();
            for(int i=0; i<id.length; ++i){
                ids.add(id[i]);
                sum+=vals[id[i]];
            }
        }
    }
    private static class BobCombinationCompare implements Comparator<Combination>{
        int[] vals;
        public BobCombinationCompare(int[] v){
            vals = v;
        }
        public int compare(Combination c1, Combination c2){
            if(c1.sum > c2.sum)
                return 1;
            if(c1.sum == c2.sum){
                if(vals[c1.ids.last()] > vals[c2.ids.last()])
                    return 1;
                if(vals[c1.ids.last()] < vals[c2.ids.last()])
                    return -1;
                if(c1.ids.size() > c2.ids.size())
                    return 1;
                if(c1.ids.size() < c2.ids.size())
                    return -1;
                int c2_id = c2.ids.first();
                for(int c1_id : c1.ids){
                    if(c1_id > c2_id)
                        return 1;
                    if(c1_id < c2_id)
                        return -1;
                    c2_id = c2.ids.ceiling(c2_id);
                }
                return 0;
            }
            return -1;
        }
    }

    private static class FrankCombinationCompare implements Comparator<Combination>{
        int[] vals;
        public FrankCombinationCompare(int[] v){
            vals = v;
        }
        public int compare(Combination c1, Combination c2){
            if(c1.sum > c2.sum)
                return 1;
            if(c1.sum == c2.sum){
                if(vals[c1.ids.first()] > vals[c2.ids.first()])
                    return 1;
                if(vals[c1.ids.first()] < vals[c2.ids.first()])
                    return -1;
                if(c1.ids.size() > c2.ids.size())
                    return 1;
                if(c1.ids.size() < c2.ids.size())
                    return -1;
                int c2_id = c2.ids.first();
                for(int c1_id : c1.ids){
                    if(c1_id > c2_id)
                        return 1;
                    if(c1_id < c2_id)
                        return -1;
                    c2_id = c2.ids.ceiling(c2_id);
                }
                return 0;
            }
            return -1;
        }
    }
    public static long howMany(int[] values){
        Arrays.sort(values);
        ArrayList<Combination> cmbs = new ArrayList<Combination>();
        TreeSet<Combination> bob_cmbs = new TreeSet<Combination>(new BobCombinationCompare(values));
        TreeSet<Combination> frank_cmbs = new TreeSet<Combination>(new FrankCombinationCompare(values));

        for(int i=0; i<values.length; ++i){
            Combination c1 = new Combination(new Integer[]{i},values);
            cmbs.add(c1);
            bob_cmbs.add(c1);
            frank_cmbs.add(c1);

            int end = cmbs.size();
            for(int j=0; j<end; ++j){
                if((!cmbs.get(j).ids.contains(i)) && (cmbs.get(j).ids.last() > i)) {
                    ArrayList<Integer> id_set = new ArrayList<Integer>();
                    for (int k : cmbs.get(j).ids)
                        id_set.add(k);
                    id_set.add(i);
                    Combination c2 = new Combination(id_set.toArray(new Integer[]{}),values);
                    cmbs.add(c2);
                    bob_cmbs.add(c2);
                    frank_cmbs.add(c2);
                }
            }

            for(int k=i+1; k<values.length; ++k){
                Combination c3 = new Combination(new Integer[]{i,k},values);
                cmbs.add(c3);
                bob_cmbs.add(c3);
                frank_cmbs.add(c3);
            }
        }

        Combination[] bobCA = new Combination[bob_cmbs.size()];
        int i=0;
        for(Combination c : bob_cmbs)
            bobCA[i++]=c;
        bob_cmbs = null;

        Combination[] frankCA = new Combination[frank_cmbs.size()];
        i=0;
        for(Combination c : frank_cmbs)
            frankCA[i++]=c;
        frank_cmbs = null;

        int numWays=0;
        int j=0, prev_start=0, prev_end=0;
        for(i=0; i<bobCA.length; ++i){
            if(i>0){
                if(bobCA[i].sum == bobCA[i-1].sum)
                    j = prev_start;
                else
                    j = prev_end;
            }
            int prev_start_flag = 0;
            for(;j<frankCA.length; ++j){
                if(bobCA[i].sum > frankCA[j].sum) {
                    prev_start = j+1;
                    prev_end = j+1;
                    continue;
                }
                if(bobCA[i].sum == frankCA[j].sum){
                    if(values[bobCA[i].ids.last()] < values[frankCA[j].ids.first()]){
                        ++numWays;
                        //printC(bobCA[i], frankCA[j]);
                        if(prev_start_flag == 0){
                            prev_start = j;
                            prev_start_flag = 1;
                        }
                        prev_end = j+1;
                    }
                    if(values[bobCA[i].ids.last()] == values[frankCA[j].ids.first()]){
                        if(prev_start_flag == 0){
                            prev_start = j;
                            prev_start_flag = 1;
                        }
                        prev_end = j+1;
                        int contains_flag=0;
                        for(int id : frankCA[j].ids){
                            if(values[id] > values[frankCA[j].ids.first()])
                                break;
                            if(bobCA[i].ids.contains(id)){
                                contains_flag = 1;
                                break;
                            }
                        }
                        if(contains_flag == 1)
                            continue;
                        ++numWays;
                        //printC(bobCA[i], frankCA[j]);
                    }
                    if(values[bobCA[i].ids.last()] > values[frankCA[j].ids.first()]){
                        prev_start = j+1;
                        prev_end = j+1;
                    }
                }
                if(bobCA[i].sum < frankCA[j].sum){
                    if(prev_start_flag == 0){
                        prev_start = j;
                        prev_start_flag = 1;
                    }
                    prev_end = j;
                    break;
                }
            }
        }
        return numWays;
    }

    private static void printC(Combination cBob, Combination cFrank){
        System.out.print("Bob: ");
        for(int i : cBob.ids)
            System.out.print(i+" ");
        System.out.format("%n");
        System.out.print("Frank: ");
        for(int i : cFrank.ids)
            System.out.print(i+" ");
        System.out.format("%n");
    }

    public static void main(String[] args){
        //System.out.println(Jewelry.howMany(new int[]{1,1,1,1,1,
        //        1,1,1,1,1,
        //        1,1,1,1,1}));
        //        1,1,1,1,1,
        //        1,1,1,1,1,
        //        1,1,1,1,1}));
        /*System.out.println(Jewelry.howMany(new int[]{1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000,
                1000,1000,1000,1000,1000}));
        */
        System.out.println(Jewelry.howMany(new int[]{1,2,3,4,5}));
        System.out.println(Jewelry.howMany(new int[]{7,7,8,9,10,11,1,2,2,3,4,5,6}));
        System.out.println(Jewelry.howMany(new int[]{1, 2, 5, 3, 4, 5}));
        System.out.println(Jewelry.howMany(new int[]{689, 689, 689, 689, 689, 77, 77, 77, 77, 77, 775, 775, 775, 775, 775, 74, 74, 74, 74, 74, 148, 148, 148, 148, 148, 223, 223, 223, 223, 223}));

    }
}
