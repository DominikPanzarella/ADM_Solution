import java.util.*;

/**
 *
 * #############################################################################################
 *
 * Chapter 7: Combinatorial Search and Heuristic Methods
 * Exercise 7.1
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
class DerangementGenerator {

    private static final int N = 3;

    public DerangementGenerator()
    {

    }

    public void derangements(int[] a) {
        d(a,new LinkedList<Integer>());
    }

    private void d(int[] a, LinkedList<Integer> curr) {
        if(a.length==curr.size())
            print(curr);
        else
        {
            for(int i=0; i<a.length ; i++)
            {
                if(!curr.contains(a[i]) && curr.size()!=i)
                {
                    curr.addLast(a[i]);
                    d(a,curr);
                    curr.removeLast();
                }
            }
        }
    }

    private void print(List<Integer> l) {
        System.out.print("{ ");
        for(var el : l)
            System.out.printf("%d ",el);
        System.out.print("}\n");

    }

    public static void main(String[] args) {
        int[] a = new int[N];
        for(int i=0; i<N ; i++)
            a[i]=(i+1);
        DerangementGenerator dg = new DerangementGenerator();
        dg.derangements(a);
    }
}
