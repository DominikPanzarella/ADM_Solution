package Chapter8.InterviewProblems;

import java.util.Comparator;
import java.util.LinkedList;

public class MinimumCoinChange
{

    //private int[] coins = new int[]{1,5,10,25,50};
    private int[] coins = new int[]{2};
    private int amount = 3;

    public MinimumCoinChange()
    {

    }

    public static int findMinPrevCost(int[] coins,int[] dp,int n) {
        LinkedList<Integer> A = new LinkedList<>();
        for (int i = 0; i < coins.length; i++) {
            if (n >= coins[i] )
                if(dp[n-coins[i]]!=(-1))
                    A.addLast(dp[n - coins[i]]);
        }
        return A.stream().min(Comparator.comparingInt(Integer::intValue)).orElse(-1);
    }

    public int coinChange(int[] coins,int amount) {
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for(int i=1; i<=amount; i++)
        {
            int val = findMinPrevCost(coins,dp,i);
            dp[i] = val==(-1)?(-1):val+1;
            System.out.print(i+":"+dp[i]+"\n");
        }

        return dp[amount];
    }


    public static void main(String[] args)
    {
        MinimumCoinChange m = new MinimumCoinChange();
        System.out.print("\n p-> "+m.coinChange(m.coins,m.amount));
    }
}
