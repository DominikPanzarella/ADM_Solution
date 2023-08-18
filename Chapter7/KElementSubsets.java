package Chapter7.KElementSubset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KElementSubsets
{

    private List<List<Integer>> allSubsets = new ArrayList<>();
    private static void constructCandidates(int[] elements,LinkedList<Integer> cur, LinkedList<Integer> a)
    {
        for(var el : elements)
        {
            var cmp = a.size()==0?Integer.MIN_VALUE:a.getLast();
            if(!a.contains(el) && el>cmp)
                cur.addLast(el);
        }
    }

    private static void print(LinkedList<Integer> a)
    {
        a.forEach(System.out::print);
        System.out.print("\n");
    }

    public void backtrack(int[] elements,int k)
    {
        backtrackHelper(elements,new LinkedList<>(),k);
    }

    /**
     * @param a current solution
     * @param k elements in the current subset
     * */
    private static void backtrackHelper(int[] elements,LinkedList<Integer> a, int k)
    {
        LinkedList<Integer> cur = new LinkedList<>();
        if(a.size()==k)
            print(a);
        else
        {
            constructCandidates(elements,cur,a);
            for(int i=0; i<cur.size(); i++)
            {
                a.addLast(cur.get(i));
                backtrackHelper(elements,a,k);
                a.removeLast();
            }
        }
    }

    private void backtrackHelper2(final int[] elements,LinkedList<Integer> a,int k)
    {
        LinkedList<Integer> cur = new LinkedList<>();
        if(a.size()==k)
        {
            //print(a);
            allSubsets.add(new ArrayList<>(a));
            return;
        }

        else
        {
            constructCandidates(elements,cur,a);
            for(int i=0; i<cur.size(); i++)
            {
                a.addLast(cur.get(i));
                backtrackHelper2(elements,a,k);
                a.removeLast();
            }
        }
    }


    /*
    * Function to find all k-element subset of nums
    * */
    public List<List<Integer>> subsets(int[] nums) {
        for(int k=0; k<=nums.length; k++)
        {
            backtrackHelper2(nums,new LinkedList<>(),k);
        }


        return allSubsets;
    }

    public static void main(String[] args)
    {
        //new Solution().backtrack(new int[]{0,1,2,3,4},3);
        System.out.println(new KElementSubsets().subsets(new int[]{1,2,3}));
    }
}
