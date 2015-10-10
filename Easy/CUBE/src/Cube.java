import java.util.Scanner;

/**
 * Created by pratikku on 11/8/15.
 */
class Cube {
    char [][][] A,B;
    float P;
    int N;
    int maxSize; // max sub-cube size
    int maxCount; // number of max sub-cubes

    public Cube(String Sa, String Sb, int size, float precision){
        N = size;
        A = new char[N][N][N];
        B = new char[N][N][N];
        P = precision;
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                for(int k=0; k<N; ++k){
                    A[i][j][k] = Sa.charAt(i*N + j*N + k);
                    B[i][j][k] = Sb.charAt(i*N + j*N + k);
                }
            }
        }
        maxSize = 0;
        maxCount = 0;
        for(int i = 0; i<N; ++i){
            for(int j = 0; j<N; ++j){
                for(int k = 0; k<N; ++k){
                    int iN = N - i;
                    int jN = N - j;
                    int kN = N - k;
                    int lDim = iN;
                    if(lDim > jN)
                        lDim = jN;
                    if(lDim > kN)
                        lDim = kN;
                    if(lDim < maxSize)
                        continue;
                    int total=0, match=0, Sm=0; // Sm -> Smatch
                    //Update Sm, size of max-sub_cube at (i,j,k)
                    //System.out.println("i="+i+" j="+j+" k="+k);
                    for(int S = 1; S <= lDim; ++S){
                        //System.out.println("S="+S);
                        for(int ing = i; ing < i+S; ++ing ){
                            for(int jng = j; jng < j+S; ++jng){
                                for(int kng = k; kng < k+S; ++kng){
                                    //System.out.println((ing)+" "+(jng)+" "+(kng));
                                    if((ing == i+(S-1)) || (jng == j+(S-1)) || (kng == k+(S-1))){
                                        if(A[ing][jng][kng] == B[ing][jng][kng]){
                                            ++match;
                                        }
                                        ++total;
                                    }
                                }
                            }
                        }
                        if( ((match*100)/total) >= P)
                            Sm = S;
                    }
                    if(Sm > maxSize) {
                        maxSize = Sm;
                        maxCount = 1;
                    }
                    else if(Sm == maxSize){
                        ++maxCount;
                    }
                }
            }
        }
        if(maxSize < 1)
            maxCount = -1;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public int getMaxCount(){
        return maxCount;
    }

    public static void main(String[] args){
        /*Cube c = new Cube("abcdefgh", "abcdefgh", 2, 40);
        System.out.println(c.getMaxSize()+" "+c.getMaxCount());
        Cube c2 = new Cube("abcdefgh","ccccccch", 2, 100);
        System.out.println(c2.getMaxSize()+" "+c2.getMaxCount());
        Cube c3 = new Cube("a", "b", 1, 100);
        System.out.println(c3.getMaxSize()+" "+c3.getMaxCount());
        */
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for(int i=0; i<T; ++i){
            int N = s.nextInt();
            float P = s.nextFloat();
            String Sa = s.next();
            String Sb = s.next();
            Cube c = new Cube(Sa, Sb, N, P);
            if(c.getMaxSize() > 0){
                System.out.println(c.getMaxSize()+" "+c.getMaxCount());
            }
            else
                System.out.println("-1");
        }
    }
}
