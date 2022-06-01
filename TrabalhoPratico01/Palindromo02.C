#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isFim(char *s)
{
    return (strlen(s) == 4 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

bool isPalindromo(char *s)
{
    for (int i = 0; i < (strlen(s) - 1) / 2; i++)
    {
        if (s[i] != s[strlen(s) - 2 - i])
            return false; // ovo
    }

    return true;
}
int main()
{
    char entrada[1000][1000];
    int numEntradas = 0;

    do
    {
        fgets(entrada[numEntradas], 1000, stdin);
    } while (!isFim(entrada[numEntradas++]));
    numEntradas--;

    for (int i = 0; i < numEntradas; i++)
    {
        if (isPalindromo(entrada[i]))
            printf("SIM\n");
        else
            printf("NAO\n");
    }
    return 0;
}