#include <string.h>
#include "bool.h"

/* Declaration of the functions' signature */

/*******************************************************************/

/* Normale edit distance computation */

void row_init(int);
void column_init(int);
void goal_cell(char*, char*, int*, int*);
int match(char, char);
int indel(char);

/************************************************************************/

void match_out(char*, char*, int, int);
void delete_out(char*, int);
void insert_out(char*, int);
void swap_out(char*,char*,int, int);