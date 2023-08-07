package Chapter8;

import java.util.Scanner;

public class Partition
{
    private static final int      MAXN =    45;                                    /*   Largest amount of nonnegative numbers in s[]   */
    private static final int      MAXK =    10;                                    /*   Largest number of dividers                     */
    private static       int[][]   m   =   new int[MAXN+1][MAXK+1];                /*   DP table for values                            */
    private static       int[][]   d   =   new int[MAXN+1][MAXK+1];                /*   DP for dividers                                */
    private static       int[]     p   =   new int[MAXN+1];                        /*   prefix sums array                              */

    public static void partition(int[] s, int k)
    {

        int cost;                                               /*   test split cost        */
        int i,j,x;                                              /*   counters               */
        int n = s.length-1;

        p[0]=0;
        for(i=1; i<=n; i++)
            p[i] = p[i-1] + s[i];


        for(i=1;i<=n;i++)
            m[i][1] = p[i];

        for(i=1; i<=k; i++)
            m[1][i] = s[1];

        for(i=2; i<=n; i++)
            for(j=2; j<=k; j++)
            {
                m[i][j] = Integer.MAX_VALUE;
                for(x=1; x<=(i-1); x++)
                {
                    cost = Math.max(m[x][j-1],p[i]-p[x]);
                    if(m[i][j] > cost)
                    {
                        m[i][j] = cost;
                        d[i][j] = x;
                    }
                }
            }
        reconstructPartition(s,n,k);
    }

    public static void reconstructPartition(int[] s,int n,int k)
    {
        if(k==1)
            printBooks(s,1,n);
        else
        {
            reconstructPartition(s, d[n][k],k-1);
            printBooks(s,d[n][k]+1,n);
        }
    }

    public static void printBooks(int s[], int start, int end)
    {
        System.out.print("{");
        for(int i=start; i<=end; i++)
            System.out.printf(" %d",s[i]);

        System.out.println(" }");
    }

    public static void main(String[] args)
    {
        int[] s;

        System.out.print("n: ");
        int n = new Scanner(System.in).nextInt();

        System.out.print("K: ");
        int k = new Scanner(System.in).nextInt();

        s = new int[n+1];

        System.out.print("Arrangement S generated --> { ");
        for(int i=1; i<=n; i++)
        {
            s[i] = i;
            System.out.print(i+" ");
        }
        System.out.println("}");
        System.out.println("Loading partitions . . .");
        partition(s,k);
    }
}
