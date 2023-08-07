#include <stdio.h>
#include "stringedit.h"
#include "editdistance.h"

extern cell m[MAXLEN + 1][MAXLEN + 1];

void row_init(int n)
{
	m[0][n].cost = n;
	m[0][n].parent = ((n > 0) ? INSERT : -1);
}

void column_init(int n)
{
	m[n][0].cost = n;
	m[n][0].parent = ((n > 0)? DELETE : -1);
}

void goal_cell(char* s, char* t, int* i, int* j)
{
	*i = strlen(s) - 1;
	*j = strlen(t) - 1;
}


int match(char c, char d)
{
	return ((c == d) ? 0 : 1);
}


int indel(char c)
{
	return 1;
}

void match_out(char* s, char* t, int i, int j)
{
	if (s[i] == t[j])
		printf("(M)");
	else
		printf("(SU)");
}

void delete_out(char*, int)
{
	printf("(D)");
}

void insert_out(char*, int)
{
	printf("(I)");
}

void swap_out(char*,char*,int, int)
{
	printf("(SW)");
}