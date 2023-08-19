package Chapter7.InOutCombination;

import java.util.LinkedList;

public class InOutCombination
{



    /*
    * true = in
    * false = out
    * */
    public void backtrack(int n,LinkedList<Boolean> a)
    {
        LinkedList<Boolean> cur = new LinkedList<>();

        if(a.size()==n)
            processoSolution(a);
        else{
            createCandidates(cur);
            for(var el: cur)
            {
                a.addLast(el);
                backtrack(n,a);
                a.removeLast();
            }
        }
    }


    /*
    * I := in
    * O := out
    * */
    public static void processoSolution(LinkedList<Boolean> a)
    {
        System.out.print("[ ");
        a.forEach(e->{
            System.out.print(e?"I ":"O ");
        });
        System.out.print("]\n");
    }

    public static void createCandidates(LinkedList<Boolean> cur)
    {
        cur.add(true);
        cur.add(false);
    }


    public static void main(String[] args)
    {
        new InOutCombination().backtrack(3,new LinkedList<>());
    }
}
