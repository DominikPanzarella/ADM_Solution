
#include <iostream>
#define MAXN 45
#define UNKNOWN -1
long f[MAXN + 1];


/*
* The following three (3) methods implement the Fibonacci method by caching
* fib_driver: initialize the array f with the first two numbers of the serie (F(0) and F(1))
* fib_h: is the helper recurisve function used for the calculus of F(N)
* fib: is the funcion that includes fib_driver and fib_h called by the user
*/

long fib_h(int n)
{
    if (f[n] == UNKNOWN)
        f[n] = fib_h(n - 1) + fib_h(n - 2);
    return f[n];
}

long fib_driver(int n)
{
    f[0] = 0;
    f[1] = 1;
    for (int i = 2; i <= n; i++)
        f[i] = UNKNOWN;

    return fib_h(n);
}



long fib(int n)
{
    fib_driver(n);
    return fib_h(n);
}

/*
* The following method implements the Fibonacci method by Dynamic programming - optimized
* we actually do not need to store all the intermediate values, because F(N) only depends on
* the previous two values ( F(n-1) and F(n-2) ).
* By the way, if for some reason, we need the intermediate values, then we can store them
* into an array. Check the next method: fib_dp(int)
*/
long fib_ultimate(int n)
{
    long back2 = 0;
    long back1 = 1;
    long next;
    for (int i = 2; i < n; i++)
    {
        next = back2 + back1;
        back2 = back1;
        back1 = next;
    }

    return (back2 + back1);
}

long fib_dp(int n)
{
    long f[MAXN];
    f[0] = 0;
    f[1] = 1;
    for (int i = 2; i <= n; i++)
        f[n] = f[n - 1] + f[n - 2];
    return f[n];
}


int main()
{
    std::cout << "Caching: "<<fib(4)<<std::endl;
    std::cout << "Dynamic Programming (optimized): " << fib(4) << std::endl;
    std::cout << "Dynamin Programmin (classic): " << fib(4) << std::endl;
}

