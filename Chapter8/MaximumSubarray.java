package Chapter8;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MaximumSubarray
{
    private static int[] array = {31,-41,59,26,-53,58,97,-93,-23,84};
    //private static int[] array = {-2,1};
    //private static int[] array = {5,4,-1,7,8};

    public MaximumSubarray()
    {
        //Empty constructor
    }

    private static void initMatrix(int[] nums,int[][] dp)
    {
        int i;
        int dim = nums.length;
        dp[0][0] = nums[0];
        for(i=1; i<dim; i++)                                     /*      Fill the diagonal                               */
            dp[i][i] = nums[i];
    }

    private static void printMatrix( int[] nums,int[][] dp)
    {
        int lenNums = nums.length;
        System.out.print("       ");
        int i,j;

        for(i=0; i<lenNums; i++)
            System.out.printf("  %10d",nums[i]);
        System.out.println();
        System.out.print("        ");

        for(i=0; i<lenNums; i++)
            System.out.print("------------");
        System.out.println();

        for(i=0; i<lenNums; i++)
        {
            System.out.printf("%5d |",nums[i]);
            for(j=0; j<lenNums; j++)
                System.out.printf("  %10d",dp[i][j]);
            System.out.println();
        }


    }

    public int maxSubArray4(int[] nums)                                     /*     Leetcode solution      */
    {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i=1; i<nums.length; i++)
        {
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }
    public int maxSubArray3(int[] nums) {
        int total = Integer.MIN_VALUE;
        int[] sum = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            sum[i] = Math.max(i>0?nums[i]+sum[i-1]:nums[i], nums[i]);
            total = Math.max(total, sum[i]);
        }
        return total;
    }

    public int maxSubArray2(int[] nums)
    {
        int lenNum = nums.length;
        int dp[] = new int[lenNum];
        int i,j;
        int max=nums[0];
        for(i=0; i<lenNum; i++)
        {
            for(j=i; j<lenNum; j++)
            {
                dp[j] = (i==j)?nums[i]:dp[j-1]+nums[j];
                if(dp[j]>max)
                    max = dp[j];
            }
        }
        return max;
    }


    public int maxSubArray(int[] nums)
    {
        int lenNums = nums.length;
        int[][] dp = new int[lenNums][lenNums];
        int i,j;                                             /* counters                                                        */
        int start=0,end=0;                                  /* start and end position of the subvector with the maximum sum     */

        initMatrix(nums,dp);

        int max = dp[0][0];

        for(i=0;i<lenNums; i++)
        {
            j = (i==0)? 1:i;
            for(; j<lenNums; j++)
            {
                dp[i][j] = dp[i][j-1] + nums[j];
                if(dp[i][j] > max)
                {
                    max = dp[i][j];
                    start = i;
                    end = j;
                }
            }

        }
        printMatrix(nums,dp);
        return dp[start][end];
    }



    /* Memory Time exceeded leetcode on 200/210 st test*/

    public static void main(String[] args)
    {

        MaximumSubarray maximumSubarray = new MaximumSubarray();
        System.out.print("Array sequence: [");
        Arrays.stream(array).forEach(e->System.out.print(" "+e));
        System.out.print(" ]\n");
        System.out.printf("Maximum sum: %d\n",maximumSubarray.maxSubArray4(array));
    }
}
