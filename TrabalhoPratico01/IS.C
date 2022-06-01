#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool isFim(char *s)
{
    return (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' &&
            s[2] == 'M');
}
bool ehNumero(char c)
{
    return c >= 48 && c <= 57;
}

bool somenteInteiros(char *s, int i)
{

    if (i != strlen(s))
    {
        if (s[i] >= '0' && s[i] <= '9')
            return somenteInteiros(s, i + 1);
        else
        {
            return false;
        }
    }
    return true;
}

// bool ehInteiro(char *s, int i)
// {

//     if (i != strlen(s))
//     {
//         if (ehNumero(s[i]))
//             return somenteInteiros(s, i+1);
//         return false;
//     }
//     return true;
// }

char converterPraMaiusculo(char c)
{
    if (c >= 97 && c <= 122)
    {
        c -= 32;
    }
    return c;
}

bool ehVogal(char c)
{
    char tmp = converterPraMaiusculo(c);
    return tmp == 'A' || tmp == 'E' || tmp == 'I' || tmp == 'O' || tmp == 'U';
}

bool somenteVogais(char *s, int i)
{

    if (i != strlen(s))
    {
        if (!ehVogal(s[i]))
            return false;
        return somenteVogais(s, i + 1);
    }
    return true;
}

bool somenteConsoantes(char *s, int i)
{

    if (i != strlen(s))
    {

        if (ehVogal(s[i]) && (!ehNumero(s[i])))
            return false;
        return somenteConsoantes(s, i + 1);
    }
    return true;
}

bool somenteReal(char *s, int i, int contador)
{

    if (i != strlen(s))
    {
        if (s[i] == 44 || s[i] == 46)
        {
            if (contador + 1 >= 2)
            {
                return false;
            }
            return somenteReal(s, i + 1, contador + 1);
        }
        else if (s[i] >= 48 && s[i] <= 57)
        {
            return somenteReal(s, i + 1, contador);
        }

        return false;
    }
    return true;
}

int main()
{
    char entrada[1000][1000];
    int numEntrada = 0;

    do
    {
        scanf(" %[^\n]s", entrada[numEntrada]);
    } while (!isFim(entrada[numEntrada++]));
    numEntrada--;

    for (int i = 0; i < numEntrada; i++)
    {
        printf(somenteVogais(entrada[i], 0) ? "SIM " : "NAO ");
        printf(somenteConsoantes(entrada[i], 0) ? "SIM " : "NAO ");
        printf(somenteInteiros(entrada[i], 0) ? "SIM " : "NAO ");
        printf(somenteReal(entrada[i], 0, 0) ? "SIM\n" : "NAO\n");
    }
    return 0;
}