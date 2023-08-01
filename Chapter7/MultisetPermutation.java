import java.util.*;

/**
 *
 * #############################################################################################
 *
 * Chapter 7: Combinatorial Search and Heuristic Methods
 * Exercise 7.2
 * Problem description here: https://www.algorist.com/algowiki_v2/index.php/Search-TADM2E
 *
 * #############################################################################################
 *
 * @author Dominik Panzarella
 * @version 1.2 2023-08-01
 *
 * #############################################################################################
 *
 */
public class MultisetPermutation
{
    public MultisetPermutation()
    {

    }

    public void b(int multiset[])
    {
        backtrack(new LinkedList<>(),multiset);
    }


    private void backtrack(LinkedList<Integer> a,int[] multiset)
    {
        LinkedList<Integer> curr = new LinkedList<>();

        if(a.size() == multiset.length)
            print(a);
        else {
            constructCandidates(a,curr,multiset);
            for(int i=0; i<curr.size(); i++)
            {
                //check if cur contains continuing duplicate values
                if(i>0 && Objects.equals(curr.get(i), curr.get(i - 1))) continue;
                a.addLast(curr.get(i));
                backtrack(a,multiset);
                a.removeLast();
            }
        }
    }


    private void constructMap(Map<Integer,Integer> mp,int[] multiset)
    {
        for(var el: multiset)
        {
            if(!mp.containsKey(el))
                mp.put(el,0);

            int newValue = mp.get(el) + 1;
            mp.put(el,newValue);
        }
    }

    private void constructCandidates(LinkedList<Integer> a,LinkedList<Integer> curr,int[] multiset)
    {
        Map<Integer,Integer> mp = new HashMap<>();
        constructMap(mp,multiset);
        // Update the values depending on the current solution (a)
        for (var el : a) {
            int newValue = mp.get(el) - 1;
            mp.put(el,newValue);
        }

        // Fill the curr array, i.e the possible candidates
        mp.forEach((k,v)->{
            for(int i=0; i<v; i++)
                curr.addLast(k);
        });

    }

    private void print(LinkedList<Integer> l)
    {
        System.out.print("{ ");
        for(var el : l)
            System.out.printf("%d ",el);
        System.out.print("}\n");
    }

    public static void main(String[] args)
    {
        int multiset[] = {1,1,2,2};
        MultisetPermutation m = new MultisetPermutation();
        m.b(multiset);
    }
}

