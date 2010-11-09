#include <stdio.h>
#include <string.h>

char *code = "AAAABBBBCCCCDDD"; // 16 bytes with string terminator '\0'

void main()
{
	char buf[8];
	strcpy(buf, code);
}