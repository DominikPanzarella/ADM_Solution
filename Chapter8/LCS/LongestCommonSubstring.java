/**
 *##################################################################################################
 *                           Longest Common Substring
 * ##################################################################################################
 * ##################################################################################################
 * @author Dominik Panzarella
 * @version 1.1 2023-08-07
 * ##################################################################################################
 */

package Chapter8;


public class LongestCommonSubstring
{
    //private static final String Y = "photograph";
    //private static final String X = "tomography";
    private static final String Y = "ABABC";
    private static final String X = "BABCA";
    private static final int lenX = X.length();
    private static final int lenY = Y.length();

    private static final boolean[][] dp= new boolean[lenX][lenY];

    public static void initMatrix()
    {
        for(int i=0; i<lenX; i++)
            for(int j=0; j<lenY; j++)

                dp[i][j] = X.charAt(i)==Y.charAt(j);


    }

    public static void printMatrix()
    {
        System.out.print("       ");
        int i=0,j=0;
        for(; i<lenY; i++)
            System.out.printf("  %3c",Y.charAt(i));
        System.out.println();
        System.out.print("       ");
        for(i=0; i<lenY; i++)
            System.out.print("-----");
        System.out.println();

        for(i=0; i<lenX; i++)
        {
            System.out.printf("%5c |",X.charAt(i));
            for(j=0; j<lenY; j++)
                System.out.printf("  %3c",(dp[i][j]?'T':'F'));
            System.out.println();
        }
    }


    public static String longestCommonSubStr()
    {

        initMatrix();

        int i,j,x,z;                                                  /*   counters   */

        String absoluteLCS = "";
        String relativeLCS = "";
        int lenAbsoluteLCS = 0;
        int lenRelativeLCS = 0;

        for(i=0; i<lenX; i++)
        {
            for(j=0; j<lenY;j++)
            {
                if(dp[i][j])
                {
                    x = i;
                    z = j;
                    for(;x<lenX && z<lenY && dp[x][z] ;z++,x++)
                    {
                        //System.out.println(""+x+" "+z);
                        relativeLCS = X.substring(i,x+1);
                        lenRelativeLCS = relativeLCS.length();

                        if(lenRelativeLCS > lenAbsoluteLCS)
                        {
                            absoluteLCS = relativeLCS;
                            lenAbsoluteLCS = absoluteLCS.length();
                        }
                    }
                }
            }
        }

        return absoluteLCS;
    }

    public static void main(String[] args)
    {
        System.out.println("\n*****************************************************************");
        System.out.println("*     String X : "+X);
        System.out.println("*     String Y : "+Y);
        System.out.println("*     LCS      : "+longestCommonSubStr());
        System.out.println("*****************************************************************\n");
        printMatrix();
    }
}
