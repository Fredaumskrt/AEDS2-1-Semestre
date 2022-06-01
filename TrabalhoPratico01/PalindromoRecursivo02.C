#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
bool isFim(char *s)
{
    return (strlen(s) == 4 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}
bool epalindromo(char *s, int i)
{
    if (i < strlen(s) - 1)
    {
        if (s[i] == s[strlen(s) - 2 - i])
            return epalindromo(s, ++i);
        else
            return false;
    }
    return true;

    do
    {
        s[i] != s[strlen(s) - 2 - i];
        return true;
    } while (i < strlen(s) - 1 / 2, i++);
    return false;
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
        printf("%s", epalindromo(entrada[i], 0) ? "SIM\n" : "NAO\n");
    }
    return 0;
}
