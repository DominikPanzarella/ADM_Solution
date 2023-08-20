package Chapter7.StringPermutation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class StringPermutation
{
    private static Dictionary dictionaryInstance;
    private static int anNumber = 0;

    static
    {
        dictionaryInstance = Dictionary.getDictionary();
    }

    public StringPermutation()
    {
        // empty construtor
    }

    private static void print(LinkedList<Character> res)
    {
        res.forEach(System.out::print);
        System.out.print("\n");
    }

    public static void constructMap(Map<Character,Integer> map, String res)
    {
        for(int i=0; i<res.length();i++)
        {
            if(res.charAt(i)==' ') continue;
            char el = res.charAt(i);
            if(!map.containsKey(el))
                map.put(el,0);
            int newValue = map.get(el)+1;
            map.put(el,newValue);
        }
    }

    // #TODO : improve method saving memory
    public static void constructCandidates(String s,LinkedList<Character> a, LinkedList<Character> curr) throws IOException {
        Map<Character,Integer> map = new HashMap<>();
        constructMap(map,s);
        // Update the values depending on the current solution (a)
        for(var el : a)
        {
            if(el==' ') continue;
            int newValue = map.get(el) - 1;
            map.put(el,newValue);
        }
        // Fill the curr array, i.e the possible candidates
        map.forEach((k,v)->{
            for(int i=0; i<v; i++)
                curr.add(k);
        });
        //System.out.println("cur: "+curr);
        //System.out.println("a:"+LinkedListToString(a));
        if(a.size()!=0 && a.getLast()!=' ' && lastWordInDictionary(LinkedListToString(a),' '))
            curr.addLast(' ');
        //System.out.println("cur: "+curr);
    }

    public static String LinkedListToString(LinkedList<Character> a){
        String res = "";
        for(var e : a)
            res += e;
        return res;
    }


    public static boolean lastWordInDictionary(String phrase,char del) throws IOException {
        int spacePosition = phrase.length();
        for(int i=(phrase.length()-1); i>=0; i--)
            if(phrase.charAt(i)==del)
            {
                spacePosition = i;
                break;
            }

        String subStr = (spacePosition==phrase.length())?phrase:phrase.substring(spacePosition+1);
        if(subStr.length()==1)
            return subStr.compareTo("a")==0 || subStr.compareTo("i")==0;
        else
            return dictionaryInstance.checkForWord(subStr);

    }



    public static void backtrackHelper(String s,LinkedList<Character> res,int lenRes) throws IOException {

        LinkedList<Character> candidates = new LinkedList<>();
        if(lenRes==s.length() && lastWordInDictionary(LinkedListToString(res),' '))
        {
            anNumber++;
            System.out.print("Anagram "+anNumber+":");
            print(res);
        }

        else {
            constructCandidates(s,res,candidates);
            for(int i=0; i<candidates.size(); i++)
            {
                if(i>0 && candidates.get(i)==candidates.get(i-1)) continue;
                char el = candidates.get(i);
                res.addLast(el);
                if(el != ' ') lenRes++;
                backtrackHelper(s,res,lenRes);
                if(el != ' ') lenRes--;
                res.removeLast();

            }
        }
    }

    public void backtrack(String s) throws IOException {
        backtrackHelper(s.replaceAll(" ",""),new LinkedList<>(),0);
    }

    public static void main(String[] args) throws IOException {
        new StringPermutation().backtrack("pillows");
        //System.out.println(lastWordInDictionary("h",' '));
    }
}
