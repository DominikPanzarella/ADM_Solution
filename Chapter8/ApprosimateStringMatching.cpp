#define _CRT_SECURE_NO_WARNINGS		/* important to depract the warning on scanf */

#include <iostream>
#include <stdio.h>
#include <string.h>
#include "bool.h"
#include "editdistance.h"
#include "stringedit.h"

using namespace std;

int main()
{
	int i, j;
	char s[MAXLEN], t[MAXLEN];		/* input strings */

	s[0] = t[0] = ' ';

	printf("s: ");
	scanf("%s", &(s[1]));
	printf("t: ");
	scanf("%s", &(t[1]));

	printf("matching cost = %d \n", string_compare(s, t));

	print_matrix(s, t, TRUE);
	printf("\n");
	print_matrix(s, t, FALSE);

	goal_cell(s, t, &i, &j);

	printf("%d %d\n", i, j);

	printf("Path: ");
	reconstruct_path(s, t, i, j);
	printf("\n");
}
