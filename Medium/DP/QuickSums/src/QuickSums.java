import sun.awt.image.IntegerInterleavedRaster;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by pratikku on 23/8/15.
 */
public class QuickSums {
    public static int minSums(String numbers, int sum){
        HashMap<Integer,Integer>[] sNp = new HashMap[numbers.length()];
        sNp[0] = new HashMap<Integer,Integer>();
        sNp[0].put(Character.getNumericValue(numbers.charAt(0)), 0);
        for(int i=1; i<numbers.length(); ++i){
            sNp[i] = new HashMap<Integer,Integer>();
            int a = Character.getNumericValue(numbers.charAt(i));
            for(int j=i-1; j>=0; --j){
                for(Map.Entry<Integer,Integer> m : sNp[j].entrySet()){
                    int n_key = m.getKey() + a;
                    int n_val = m.getValue() + 1;
                    Integer e_val = sNp[i].get(n_key);
                    if(e_val != null){
                        sNp[i].put(n_key,Math.min(n_val, e_val));
                    }
                    else {
                        sNp[i].put(n_key, n_val);
                    }
                }
                a = a + ((int)Math.pow(10, (i-j)) * (Character.getNumericValue(numbers.charAt(j))));
            }
            sNp[i].put(a, 0);
            //System.out.println(a);
        }

        Integer res = sNp[numbers.length()-1].get(sum);
        if(res == null)
            return -1;
        return res;
    }

    public static void main(String[] args){
        System.out.println(QuickSums.minSums("99999", 45));
        System.out.println(QuickSums.minSums("1110", 3));
        System.out.println(QuickSums.minSums("0123456789", 45));
        System.out.println(QuickSums.minSums("99999",100));
        System.out.println(QuickSums.minSums("382834", 100));
        System.out.println(QuickSums.minSums("9230560001", 71));
        System.out.println(QuickSums.minSums("8833614925", 100));
    }
}
