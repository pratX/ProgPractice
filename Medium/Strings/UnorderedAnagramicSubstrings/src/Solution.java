import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by pratikku on 30/9/15.
 */
public class Solution {
    private static void insert(char[] arr, int pos){
        if(pos == 0)
            return;
        int l=0;
        int h=pos-1;
        int ins = 0;
        while(l <= h){
            int mid = (l+h)/2;
            if (arr[mid] == arr[pos]){
                ins = mid;
                break;
            }
            else if(arr[mid] > arr[pos]){
                h = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        if(l>h){
            ins = l;
        }
        char temp = arr[pos];
        for(int i=pos; i>ins; --i){
            arr[i] = arr[i-1];
        }
        arr[ins] = temp;
        return;
    }
    static int countUnorderedAnagramicSubstrings(String s){
        int count = 0;
        HashMap<String, Integer> uaf = new HashMap<>();
        for(int i=0; i<s.length(); ++i){
            char [] c_arr = new char[s.length()-i];
            for(int j=i; j<s.length(); ++j){
                c_arr[j-i] = s.charAt(j);
                insert(c_arr, j-i);
                String str = new String(c_arr, 0, j-i+1);
                //System.out.println(str);
                if(uaf.containsKey(str)){
                    uaf.put(str, uaf.get(str)+1);
                }
                else{
                    uaf.put(str, 1);
                }
            }
        }

        for(Map.Entry<String, Integer> m : uaf.entrySet()){
            if(m.getValue() >=2){
                int n=1;
                for(int i=m.getValue(); i> (m.getValue()-2); --i){
                    n = n*i;
                }
                n=n/2;
                count = count + n;
                //System.out.println(m.getKey());
            }
        }

        return count;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-->0){
            System.out.println(countUnorderedAnagramicSubstrings(sc.next()));
        }
    }
}
