package Chapter7.TelephoneKeypads;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class TelephoneKeypads
{

    private final String[] keypads = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    private List<String> result = new ArrayList<>();

    private void constructCandidates(String digits,LinkedList<Character> candidates, int k)
    {
        int position = digits.charAt(k) - '0';
        String candidateString = keypads[position];
        for(int i=0; i<candidateString.length(); i++)
        {
            candidates.addLast(candidateString.charAt(i));
        }
    }

    private String toString(LinkedList<Character> a)
    {
        AtomicReference<String> res = new AtomicReference<>("");
        a.forEach(e-> res.updateAndGet(v -> v + e));
        return res.toString();
    }



    private void backtrack(String digits, LinkedList<Character> a, int k)
    {
        LinkedList<Character> candidates = new LinkedList<>();
        if(digits.length()==0) return;
        if(a.size() == digits.length())
        {
            result.add(new String(toString(a)));
        }

        else
        {
            k = k + 1;
            constructCandidates(digits,candidates,k);
            for(int i=0; i<candidates.size(); i++)
            {
                a.addLast(candidates.get(i));
                backtrack(digits,a,k);
                a.removeLast();
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        backtrack(digits,new LinkedList<>(),-1);
        return result;
    }

    public static void main(String[] args)
    {
        TelephoneKeypads s = new TelephoneKeypads();
        var combs = s.letterCombinations("23");
        combs.forEach(System.out::println);
    }
}
