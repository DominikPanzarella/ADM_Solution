package Chapter8;

import java.util.*;

/**
 *
 * Longest Increasing Subsequence - The Algorithm Design Manual, S. Skiena
 *
 * Given a sequence of integers, find the longest increasing
 * non-contiguous subsequence such that each element is increasing.
 *
 * L(j) = longest strictly increasing subsequence ending at position j.
 *
 * Recurrence relation:
 *
 * L(i) = ( max value of L(j), where j < i, A[j] < A[i] ) + 1
 */

public class LISTest
{

    public static int lisCost(final int[] arr)
    {
        int[] cost = new int[arr.length];
        int totalMax = 0;
        for(int i=0; i<arr.length; i++)
        {
            if(i==0)
                cost[i] = 1;
            else
            {
                int max = 0;
                for(int j=i; j>=0; j--)
                {
                    if(arr[j] < arr[i])
                    {
                        max = Math.max(max,cost[j]);
                    }
                }
                cost[i] = max + 1;
                totalMax = Math.max(totalMax,cost[i]);
            }

        }
        return totalMax;

    }

    public static String lisSequence(int[] arr)
    {
        int[] cost = new int[arr.length];
        int[] prev = new int[arr.length];
        Arrays.fill(prev,-1);
        int totalMax = 0;
        int max;
        for(int i=0; i<arr.length; i++) {
            if (i == 0)
                cost[i] = 1;
            else {
                max = 0;
                for (int j = i; j >= 0; j--) {
                    if (arr[j] < arr[i] && cost[j] > max) {
                        prev[i] = j;
                        max = cost[j];
                        break;
                    }
                }
                cost[i] = max + 1;
                totalMax = Math.max(totalMax, cost[i]);
            }

        }

        StringBuilder str = new StringBuilder();
        int i = cost.length-1;
        while( i >=0)
        {
            if(prev[i]>=0)
            {
                str.append(arr[i]+" ");
                i = prev[i];
            }
            else if(i==0){
                str.append(arr[i]);break;
            }

            else
                i--;
        }
        str.reverse();
        return str.toString();

    }

    public static void main(String[] args)
    {
        //int[] arr = {2,4,3,5,1,7,6,9,8};
        int[] arr = {1,4,5,2,9};
        System.out.println("Max longest increasing sequence length: "+lisCost(arr));
        System.out.println("[ "+lisSequence(arr)+" ]");
    }
}
