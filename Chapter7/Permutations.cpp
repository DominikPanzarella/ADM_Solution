#include <iostream>
#include <stdio.h>
#define TRUE 1
#define FALSE 0
#define NMAX 100
#define MAXCANDIDATES 100
using namespace std;

int is_a_solution(int k, int n) { return k == n; }

void construct_candidates(int a[], int k, int n, int c[], int* ncandidates) {
    int in_perm[NMAX]{};    /* who is in the permutaion*/
    int i{};

    for (int i = 1; i < NMAX; i++)
        in_perm[i] = FALSE;
    for (int i = 0; i < k; i++)
        in_perm[a[i]] = TRUE;

    *ncandidates = 0;
    for (int i = 1; i <= n; i++) {
        if (in_perm[i] == FALSE) {
            c[*ncandidates] = i;
            *ncandidates = *ncandidates + 1;
        }
    }
}

void process_solution(int a[], int k) {
    cout << "{";
    for (int i = 1; i <= k; i++)
        cout << "" << a[i] << ((i != k) ? "," : "");


    cout << "}" << endl;
}

void backtrack(int a[], int k, int input) {
    int c[MAXCANDIDATES]{};     /* candidates for the next position*/
    int ncandidates{};      /* index of the next candidate's position */

    if (is_a_solution(k, input))
        process_solution(a, k);
    else {
        k = k + 1;
        construct_candidates(a, k, input, c, &ncandidates);
        for (int i = 0; i < ncandidates; i++) {
            a[k] = c[i];
            backtrack(a, k, input);
        }
    }
}

int main() {
    const int n = 3;
    int a[NMAX];
    backtrack(a, 0, n);
}
