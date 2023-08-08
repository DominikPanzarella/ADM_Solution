/**
 *##################################################################################################
 *                           Longest Common Substring
 * ##################################################################################################
 * ##################################################################################################
 * @author Dominik Panzarella
 * @version 2.0 2023-08-07
 * ##################################################################################################
 */
package Chapter8;

public class LongestCommonSubstring2
{

    private static final String Y = "photograph";
    private static final String X = "tomography";
    //private static final String Y = "ABABC";
    //private static final String X = "BABCA";
    private static final int lenX = X.length();
    private static final int lenY = Y.length();
    private static int[] pos = new int[2];

    private static int[][] dp;


    public static int LCS()
    {
        return LCS(X,Y,lenX,lenY);
    }

    public static int LCS(String x,String y,int lenX,int lenY)            /* only length */
    {
        dp = new int[lenX][lenY];
        int max = 0;
        for(int i=0; i<lenX; i++)
            for(int j=0; j<lenY; j++)
            {
                if(i==0 || j==0) dp[i][j] = x.charAt(i)==y.charAt(j)?1:0;
                else
                {
                    //i>0 && j>0
                    if(x.charAt(i)==y.charAt(j))
                    {
                        dp[i][j] = dp[i-1][j-1] + 1;
                        max = Math.max(max,dp[i][j]);
                        pos[0]=i;
                        pos[1]=j;
                    }

                    else
                        dp[i][j] = 0;
                }


            }

        return max;
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
                System.out.printf("  %3d",dp[i][j]);
            System.out.println();
        }
    }


    public static void reconstructPath()
    {
        reconstructPath(X,Y,pos[0],pos[1]);
    }

    private static void reconstructPath(String x,String y,int i,int j)
    {
        if(dp[i][j]<1)
            return;
        else
        {
            reconstructPath(x,y,i-1,j-1);
            System.out.print(x.charAt(i));
        }
    }


    public static void main(String[] args)
    {

        LCS();


        System.out.println("\n*****************************************************************");
        System.out.println("*     String X : "+X);
        System.out.println("*     String Y : "+Y);

        System.out.print("*     LCS      : ");
        reconstructPath();
        System.out.println("\n*****************************************************************\n");
        printMatrix();
    }
}
