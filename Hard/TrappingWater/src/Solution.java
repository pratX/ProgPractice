import java.util.ArrayList;

/**
 * Created by pratikku on 14/8/15.
 */
public class Solution {
    int[] lower;
    int[] upper;

    class Node {
        public int x;
        public int y;
        Node(int i, int j){
            x = i;
            y = j;
        }
    }
    public int trap(int[] height){
        lower = new int[height.length];
        upper = new int[height.length];
        for(int i = 0; i<height.length; ++i){
            lower[i] = height[i];
            upper[i] = height[i];
        }
        ArrayList<Node> aln = new ArrayList<Node>();
        for(int i=0; i<lower.length; ++i){
            Node n = new Node(i, lower[i]);
            int idx = ibs(aln, n);
            if(idx >= aln.size()){
                aln.add(n);
                continue;
            }
            if(idx == 0){
                for(int j = aln.get(0).x+1; j < n.x; ++j){
                    upper[j] = aln.get(0).y;
                }
            }
            aln.set(idx,n);
            aln = new ArrayList<Node>(aln.subList(0,idx+1));
        }

        for(int i = aln.size() - 1; i > 0; --i){
            for(int j = aln.get(i).x-1; j > aln.get(i-1).x; --j)
                upper[j] = aln.get(i).y;
        }

        /*System.out.print("U: ");
        for(int i=0; i<upper.length; ++i){
            System.out.print(upper[i]+" ");
        }
        System.out.format("%n");
        System.out.print("L: ");
        for(int i=0; i<lower.length; ++i){
            System.out.print(lower[i]+" ");
        }
        System.out.format("%n");*/

        int trap_sum = 0;
        for(int i = 0; i<upper.length; ++i)
            trap_sum += upper[i] - lower[i];

        return trap_sum;
    }

    private int ibs(ArrayList<Node> al, Node n){
        if(al.isEmpty())
            return 0;
        int lo = 0;
        int hi = al.size() - 1;
        int mid = (lo + hi)/2;
        while(hi >= lo){
            if(n.y > al.get(mid).y){
                hi = mid - 1;
            }
            else if(n.y < al.get(mid).y){
                    lo = mid + 1;
                 }
                 else {
                     return mid;
                 }
            mid = (lo + hi)/2;
        }
        return lo;
    }

    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
