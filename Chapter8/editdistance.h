#define MAXLEN	101		//Longest possibile string: 100 character + '\0'

#define MATCH 0
#define INSERT 1
#define DELETE 2

typedef struct{
	int cost;
	int parent;
} cell;



int string_compare(char*, char*);
void reconstruct_path(char*, char*, int, int);
void print_matrix(char*, char*, bool);