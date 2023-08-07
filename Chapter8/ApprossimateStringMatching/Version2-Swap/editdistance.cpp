#include <string.h>
#include <stdio.h>
#include <iostream>
#include "editdistance.h"
#include "bool.h"
#include "stringedit.h"


cell m[MAXLEN + 1][MAXLEN + 1];

int string_compare(char* s, char* t)
{
	int i, j, k;		//counter
	int opt[4] {};		//cost of the three operations: MATCH,INSERTION,DELETE
	//Assumption: each string has been padded with an initial blank character
	for (int i = 0; i < MAXLEN; i++)
	{
		row_init(i);
		column_init(i);
	}
	for (int i = 1; i < strlen(s); i++)
	{
		for (int j = 1; j < strlen(t); j++)
		{
			opt[MATCH] = m[i - 1][j - 1].cost + match(s[i], t[j]);
			opt[INSERT] = m[i][j - 1].cost + indel(t[j]);
			opt[DELETE] = m[i - 1][j].cost + indel(s[i]);
			opt[SWAP] = i>1 && j>1 && s[i] == t[j - 1] && s[i - 1] == t[j] ? m[i - 2][j - 2].cost + 1 : MAXINT;

			//Which operation is the cheapest one?
			m[i][j].cost = opt[MATCH];
			m[i][j].parent = MATCH;

			int upperRange = DELETE;
			if (i > 1 && j > 1 && s[i] == t[j - 1] && s[i - 1] == t[j])
				upperRange = SWAP;

			for (int k = INSERT; k <= upperRange; k++)
			{
				if (opt[k] < m[i][j].cost)
				{
					m[i][j].cost = opt[k];
					m[i][j].parent = k;
				}
			}
		}

	}
	goal_cell(s, t, &i, &j);
	return m[i][j].cost;
}

void reconstruct_path(char* s, char* t, int i, int j)
{
	if (m[i][j].parent == -1)
		return;

	if (m[i][j].parent == MATCH)
	{
		reconstruct_path(s, t, i - 1, j - 1);
		match_out(s, t, i, j);
		return;
	}

	if (m[i][j].parent == INSERT)
	{
		reconstruct_path(s, t, i, j - 1);
		insert_out(t, j);
		return;
	}

	if (m[i][j].parent == DELETE)
	{
		reconstruct_path(s, t, i - 1, j);
		delete_out(s, i);
		return;
	}

	if (m[i][j].parent == SWAP)
	{
		reconstruct_path(s,t,i-2,j-2);
		swap_out(s,t,i,j);
		return;
	}
}

void print_matrix(char* s, char* t, bool costQ)
{
	int i, j;			/* counters */
	int x, y;			/* string lengths */

	x = strlen(s);
	y = strlen(t);

	printf("   ");
	for (i = 0; i < y; i++)
		printf("  %c", t[i]);
	printf("\n");


	for (i = 0; i < x; i++) {
		printf("%c: ", s[i]);
		for (j = 0; j < y; j++) {
			if (costQ == TRUE)
				printf(" %2d", m[i][j].cost);
			else
				printf(" %2d", m[i][j].parent);

		}
		printf("\n");
	}
}
