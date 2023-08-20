package Chapter7.RandomGenerator;

import java.util.Random;

public class RandomGenerator
{

    private int rng04()
    {
        return new Random().nextInt(5);
    }

    private int rng03()
    {
        while(true)
        {
            int n = rng04();
            if(n<4)
                return n;
        }
    }

    private int rng01()
    {
        while(true)
        {
            int n = rng04();
            if(n<4)
                return n%2;
        }
    }

    public int rng07()
    {
        int i = rng03();
        int j = rng01();
        return i*2+j;
    }

    public static void main(String[] args)
    {
        RandomGenerator r = new RandomGenerator();
        for(int i=0; i<10000; i++)
            System.out.println(r.rng07());
    }
}
