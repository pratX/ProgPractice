import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by pratikku on 19/8/15.
 */
public class TestTreeSet {
    TreeSet<Integer> ts;
    public static void main(String[] args){
        TestTreeSet tst = new TestTreeSet();
        tst.ts = new TreeSet<Integer>();
        System.out.println(tst.ts.add(15));
        System.out.println(tst.ts.add(16));
        System.out.println(tst.ts.add(16));
        int i=5, j=6;
        int[] arr = new int[]{i,j};
        System.out.println(arr[0]+" "+arr[1]);
    }
}
