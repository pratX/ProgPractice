import java.util.Scanner;

/**
 * Created by pratikku on 30/8/15.
 */
public class StarAdventure {
    public static int mostStars(String[] level){
        int [][] matrix = new int[level.length][level[0].length()];
        //Build the matrix
        for(int i=0; i<level.length; ++i){
            //Scanner s = new Scanner(level[i]);
            for(int j=0; j<level[i].length(); ++j){
                matrix[i][j] = Character.getNumericValue(level[i].charAt(j));
            }
        }

        int max_collection = collect(matrix);
        max_collection += collect(matrix);
        max_collection += collect(matrix);

        return  max_collection;
    }

    private static int collect(int[][] level){
        class Node{
            int max_coll;
            int p; // Up:0; Left:1; None: -1
            public Node(int coll, int p){
                max_coll = coll;
                this.p = p;
            }
        }

        Node [][] matrix = new Node[level.length][level[0].length];
        for(int i=0; i<matrix.length; ++i){
            for(int j=0; j<matrix[0].length; ++j){
                int max = 0;
                int p = -1;
                if(i>0){
                    max = matrix[i-1][j].max_coll;
                    p=0;
                }
                if(j>0){
                    if(max < matrix[i][j-1].max_coll){
                        max = matrix[i][j-1].max_coll;
                        p = 1;
                    }
                }
                matrix[i][j] = new Node(max+level[i][j], p);
            }
        }

        int p=0;
        int row=level.length-1;
        int col = level[0].length-1;
        //print2Darr(level);
        while(p>=0){
            level[row][col] = 0;
            p = matrix[row][col].p;
            if(p == 0){
                --row;
            }
            if(p == 1){
                --col;
            }
        }

        return matrix[level.length-1][level[0].length-1].max_coll;
    }

    private static void print2Darr(int[][] arr){
        System.out.println("--------------------------------");
        for(int i=0; i<arr.length; ++i){
            for(int j=0; j<arr[i].length; ++j){
                System.out.print(arr[i][j]+" ");
            }
            System.out.format("%n");
        }
        System.out.println("--------------------------------");
    }

    public static void main(String[] args){
        /*System.out.println(StarAdventure.mostStars(new String[]{"01",
                "11"}));
        System.out.println(StarAdventure.mostStars(new String[]{"0999999999"
                ,"9999999999"
                ,"9999999999"
                ,"9999999999"
                ,"9999999999"
                ,"9999999999"
                ,"9999999999"
                ,"9999999999"
                ,"9999999999"
                ,"9999999999"}));*/
        System.out.println(StarAdventure.mostStars(new String[]{"012"
                ,"012"
                ,"012"
                ,"012"
                ,"012"
                ,"012"
                ,"012"}));
        System.out.println(StarAdventure.mostStars(new String[]{"0123456789",
                "1123456789",
                "2223456789",
                "3333456789",
                "4444456789",
                "5555556789",
                "6666666789",
                "7777777789",
                "8888888889",
                "9999999999"}));
    }
}
