import java.util.Scanner;

/**
 * Created by pratikku on 1/10/15.
 */
public class Solution {

    private static void printForest(char[][] forest){
        System.out.format("%n");
        for(int i=0; i<forest.length; ++i){
            for (int j=0; j<forest[0].length; ++j){
                System.out.print(forest[i][j]);
            }
            System.out.format("%n");
        }
    }

    public static int countWandWaving(char[][] forest, int[] hp, int[] exit){
        //Mark the current pos
        forest[hp[0]][hp[1]] = '0';
        //printForest(forest);
        //Check neighbours for exit, number of options, store each option
        int[] opts = new int[4];
        //opts[0]->Left; opts[1]->Right; opts[2]->Up; opts[3]->Down
        int num_opts=0;
        int exit_flag=0;
        int[][] new_hp = new int[][]{new int[]{hp[0], hp[1]-1}, new int[]{hp[0], hp[1]+1}, new int[]{hp[0]-1, hp[1]}, new int[]{hp[0]+1, hp[1]}};
        //Check Left
        if((hp[1] > 0) && (forest[hp[0]][hp[1]-1] == '.')){
            if((hp[0] == exit[0]) && ((hp[1]-1) == exit[1])){
                exit_flag =  1;
            }
            opts[0]=1;
            ++num_opts;
        }

        //Check Right
        if((hp[1]<forest[0].length-1) && (forest[hp[0]][hp[1]+1] == '.')){
            if((hp[0] == exit[0]) && (hp[1]+1 == exit[1])){
                exit_flag =  1;
            }
            opts[1] = 1;
            ++num_opts;
        }

        //Check Up
        if((hp[0]>0) && (forest[hp[0]-1][hp[1]] == '.')){
            if((hp[0]-1 == exit[0]) && (hp[1] == exit[1])){
                exit_flag =  1;
            }
            opts[2] = 1;
            ++num_opts;
        }

        //Check Down
        if((hp[0]<forest.length-1) && (forest[hp[0]+1][hp[1]] == '.')){
            if((hp[0]+1 == exit[0]) && (hp[1] == exit[1])){
                exit_flag =  1;
            }
            opts[3] = 1;
            ++num_opts;
        }

        //System.out.println(num_opts);

        if(exit_flag == 1){
            if(num_opts > 1)
                return 1;
            else
                return 0;
        }

        for(int i=0; i<4; ++i){
            if(opts[i] == 1){
                int result = countWandWaving(forest, new_hp[i], exit);
                //System.out.println(result);
                if(result >= 0){
                    if(num_opts > 1)
                        return 1 + result;
                    else
                        return result;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int R = sc.nextInt();
            int C = sc.nextInt();
            int[] hp = new int[2];
            int[] exit = new int[2];
            char[][] forest = new char[R][C];

            for(int i=0; i<R; ++i){
                String str = sc.next();
                for(int j=0; j<C; ++j){
                    if(str.charAt(j) == 'M'){
                        forest[i][j] = '.';
                        hp[0] = i; hp[1] = j;
                    }
                    else if (str.charAt(j) == '*'){
                        forest[i][j] = '.';
                        exit[0] = i;
                        exit[1] = j;
                    }
                    else {
                        forest[i][j] = str.charAt(j);
                    }
                }
            }

            int result = Solution.countWandWaving(forest, hp, exit);
            //System.out.println(result);
            if(result == sc.nextInt()){
                System.out.println("Impressed");
            }
            else
                System.out.println("Oops!");
        }
    }

}
