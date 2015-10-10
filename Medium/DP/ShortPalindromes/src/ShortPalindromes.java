/**
 * Created by pratikku on 29/8/15.
 */
public class ShortPalindromes {

    public static String shortest(String base){
        class Node {
            int len;
            int n_row;
            int n_col;

            public Node(int l, int pr, int pc){
                len = l;
                n_row = pr;
                n_col = pc;
            }
        }
        Node[][] n = new Node[base.length()][base.length()];
        //BaseCase:
        for(int i=0; i<base.length(); ++i)
            n[i][i] = new Node(1,-1,-1);

        for(int gap=1; gap < base.length(); ++gap){
            for(int i=0; i<base.length()-gap; ++i){
                int j = i+gap;
                if(base.charAt(i) == base.charAt(j)){
                    if((j-i) <= 1){
                        n[i][j] = new Node(2, -1, -1);
                        continue;
                    }
                    int n_row = i+1;
                    int n_col = j-1;
                    n[i][j] = new Node(2+n[n_row][n_col].len, n_row, n_col);
                    continue;
                }
                if(n[i][j-1].len < n[i+1][j].len){
                    n[i][j] = new Node(n[i][j-1].len+2, i, j-1);
                }
                else if (n[i+1][j].len < n[i][j-1].len){
                    n[i][j] = new Node(n[i+1][j].len+2, i+1, j);
                }
                else {
                    if(base.charAt(i) < base.charAt(j)){
                        n[i][j] = new Node(n[i+1][j].len+2, i+1, j);
                    }
                    else {
                        n[i][j] = new Node(n[i][j-1].len + 2, i, j-1);
                    }
                }
            }
        }

        char[] str = new char[n[0][base.length()-1].len];
        int row=0;
        int col=base.length()-1;
        int p_b = 0;
        int p_e = str.length-1;
        while(p_b <= p_e){
            if((n[row][col].n_row == row+1) && (n[row][col].n_col == col-1)){
                str[p_b++] = base.charAt(row);
                str[p_e--] = base.charAt(col);
            }
            else if(n[row][col].n_row == row+1){
                str[p_b++] = base.charAt(row);
                str[p_e--] = base.charAt(row);
            }
            else {
                str[p_b++] = base.charAt(col);
                str[p_e--] = base.charAt(col);
            }
            int row_n = n[row][col].n_row;
            int col_n = n[row][col].n_col;
            row = row_n;
            col = col_n;
        }

        return new String(str);
    }

    public static void main(String[] args){
        System.out.println(ShortPalindromes.shortest("race"));
        System.out.println(ShortPalindromes.shortest("TOPCODER"));
        System.out.println(ShortPalindromes.shortest("Q"));
        System.out.println(ShortPalindromes.shortest("MADAMIMADAM"));
        System.out.println(ShortPalindromes.shortest("ALRCAGOEUAOEURGCOEUOOIGFA"));

    }
}
