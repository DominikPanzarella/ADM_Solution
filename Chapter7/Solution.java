package Chapter7.StringPermutation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
*
* Problem: Write a function to find all permutations of the letters in a particular string
*
*/
public class Solution
{

    public void backtrack(String s)
    {
        backtrackHelper(s,new LinkedList<>());
    }
    public static void constructMap(Map<Character,Integer> map,String res)
    {

        for(int i=0; i<res.length();i++)
        {
            char el = res.charAt(i);
            if(!map.containsKey(el))
                map.put(el,0);
            int newValue = map.get(el)+1;
            map.put(el,newValue);
        }
    }

    private static void constructCandidates(String s,LinkedList<Character> a,LinkedList<Character> curr)
    {
        Map<Character,Integer> map = new HashMap<>();
        constructMap(map,s);
        // Update the values depending on the current solution (a)
        for(var el : a)
        {
            int newValue = map.get(el) - 1;
            map.put(el,newValue);
        }
        // Fill the curr array, i.e the possible candidates
        map.forEach((k,v)->{
            for(int i=0; i<v; i++)
                curr.add(k);
        });
    }

    public static void backtrackHelper(String s,LinkedList<Character> res)
    {
        LinkedList<Character> candidates = new LinkedList<>();
        if(res.size()==s.length())
            print(res);
        else {
            constructCandidates(s,res,candidates);
            for (Character candidate : candidates) {
                res.addLast(candidate);
                backtrackHelper(s, res);
                res.removeLast();
            }
        }
    }

    private static void print(LinkedList<Character> res)
    {
        res.forEach(System.out::print);
        System.out.print("\n");
    }

    public static void main(String[] args)
    {
        new Solution().backtrack("ello");
    }
}
