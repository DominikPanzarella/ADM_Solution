/**
*##################################################################################################
*                           Shuffle of two String
* ##################################################################################################
* Suppose you are given three strings of characters: X,Y and Z,
* where |X|=n, |Y|=m and |Z|=n + m.
* Z is said to be a shuffle of X and Y iff Z
* can be formed by interleaving the characters from X and Y
* in a way that maintains the left-to-right ordering of the characters from each string.
* ##################################################################################################
* Give an efficient dynamic-programming algorithm that determines whether Z
* is a shuffle of X and Y.
* Hint: the values of the dynamic programming matrix you construct should be Boolean, not numeric.
* ##################################################################################################
* @author Dominik Panzarella
* @version 1.1 2023-08-07
* ##################################################################################################
*/
package Chapter8;

public class ShuffleDP
{

    private static String X = " chocolate";
    private static String Y = " chips";
    private static String Z = " cchocohilaptes";      /* Expected result: true */

    //private static String Z = " chocochilatspe";        /* Expected result: false */
    private static boolean[][] B;

    public static boolean isZLenCorrect(String x,String y,String z)
    {
        int lenX = x.length();
        int lenY = y.length();
        int lenZ = z.length();
        return (lenZ==(lenX+lenY-1));
    }

    public static boolean compareChar(char c1,char c2)
    {
        return c1==c2;
    }


    public static void printMatrix()
    {
        int i,j;
        int x,y;

        x = X.length();
        y = Y.length();

        System.out.print(" -  ");

        for(i = 0; i<y; i++)
            System.out.printf("   %3c",Y.charAt(i));
        System.out.println();

        for(i=0; i<x; i++)
        {
            System.out.printf("%c: ", X.charAt(i));
            for(j=0; j<y; j++)
                System.out.printf(" %2b",B[i][j]);
            System.out.println();
        }
    }

    public static boolean  isShuffle()
    {
        return isShuffle(X,Y,Z);
    }


    public static boolean isShuffle(String x,String y,String z)
    {

        if(!isZLenCorrect(x, y, z))
            return false;

        int i,j;                                        /* counters */
        int lenX = x.length();
        int lenY = y.length();
        B = new boolean[lenX+1][lenY+1];

        B[0][0] = true;
        for(i=1; i<=lenX; i++)
            B[i][0] = B[i-1][0] && z.charAt(i)==x.charAt(i);

        for(i=1; i<= lenY; i++)
            B[0][i] = B[0][i-1] && z.charAt(i)==y.charAt(i);


        for(i=1; i<lenX; i++)
            for(j=1; j<lenY; j++)

                B[i][j] = ((z.charAt(i+j)==x.charAt(i)&&B[i-1][j]) || (z.charAt(i+j)==y.charAt(j)&&B[i][j-1]));

        //printMatrix();

        return B[lenX-1][lenY-1];
    }


    public static void main(String[] args)
    {
        System.out.println("X: "+X);
        System.out.println("Y: "+Y);
        System.out.println("Z: "+Z);

        System.out.println("Z is a shuffle of X and Y --> " + isShuffle());
    }
}
