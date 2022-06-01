#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 30

bool isFim(char *s)
{
    return ((strlen(s) == 3 || strlen(s) == 4) && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

typedef struct Filmes
{
    char nome[100];
    char tituloOriginal[100];
    char dataLancamento[8];
    int duracao;
    char genero[500];
    char idiomaOriginal[500];
    char situacao[500];
    float orcamento;
    char palavraChave[500];
};
bool contains(char str[], char strToFind[])
{
    char *ptr = strstr(str, strToFind);
    if (ptr != NULL)
        return true;
    return false;
}

void removerTags(char *entrada, char *remover)
{
    char tempo[2];
    tempo[1] = '\0';
    remover[0] = '\0';

    for (int i = 0; i < strlen(entrada); i++)
    {
        if (entrada[i] != '>')
        {
            while (entrada[i] != '>')
                i++;
        }
        else
        {
            tempo[0] = entrada[i];
            strcat(remover, tempo);
        }
    }
}

void imprimir(struct Filmes printar)
{
    printf("%s", printar.nome);
    printf("%s", printar.tituloOriginal);
    printf("%s", printar.dataLancamento);
    printf("%s", printar.duracao);
    printf("%s", printar.genero);
    printf("%s", printar.idiomaOriginal);
    printf("%s", printar.situacao);
    printf("%s", printar.orcamento);
    printf("[%s]\n", printar.palavraChave);
}

char *replace(const char *s, const char *s2, // actual_s / old_s s2  /new_s s3
              const char *s3)
{
    char *result;
    int i = 0;
    int aux = 0; // cnt
    int slen01 = strlen(s3);
    int slen02 = strlen(s3);

    for (int i = 0; s[i] != '\0'; i++)
    {
        if (strstr(&s[i], s2) == &s[i])
        {
            aux++;
            i += slen02 - 1;
        }
    }
    result = (char *)malloc(i + aux * (slen01 - slen02) + 1);

    i = 0;
    while (*s)
    {
        if (strstr(s, s2) == s)
        {
            strcpy(&result[i], s3);
            i += slen01;
            s += slen02;
        }
        else
            result[i++] == *s++;
    }
    result[i] = '\0';
    return result;
}

void trim(char *str)
{
    int inicio = 0;
    int fim = strlen(str) - 1;

    while (str[inicio] == ' ' || str[inicio] == '\n' || str[inicio] == '\t')
        inicio++;

    while (str[fim] == ' ' || str[fim] == '\n' || str[fim] == '\t')
        fim--;
    fim++;

    for (int i = 0; i < fim - inicio; i++)
    {
        str[i] = str[i + inicio];
    }
    str[fim - inicio] = '\0';
}

int stringToMinutos(char *str)
{
    int minutos = 0;
    int leitura = strlen(str);

    if (contains(str, "h"))
    {
        minutos += str[leitura - 2] - '0';
        leitura = (str[0] - '0') * 60;

        if (leitura == 6)
            minutos += (str[strlen(str) - 3] - '0') * 10;
    }
    else
        minutos = atoi(replace(str, "m", ""));
    return minutos;
}

// LER NOME DO FILME
struct Filmes ler(char *endereco)
{
    char linha[300];
    char tagsRemovidas[300];

    FILE *arq = fopen(endereco, "rt");
    if (arq == NULL)
    {
        perror("Unable to open the file");
        exit(1);
    }

    struct Filmes filme;

    while (!contains(fgets(linha, sizeof(linha), arq), "h2 class"))
    {
    }
    fgets(linha, sizeof(linha), arq);
    removerTags(linha, tagsRemovidas);
    trim(tagsRemovidas);
    strcpy(filme.nome, tagsRemovidas);
    strcpy(filme.tituloOriginal, filme.nome);

    // LER DATA DE LANCAMENTO

    while (!contains(fgets(linha, sizeof(linha), arq), "\"release\""))
    {
    }
    fgets(linha, sizeof(linha), arq);
    removerTags(linha, tagsRemovidas);
    trim(tagsRemovidas);
    strcpy(filme.dataLancamento, tagsRemovidas);
    filme.dataLancamento[10] = '\0';

    // LER GENERO

    while (!contains(fgets(linha, sizeof(linha), arq), "\"genres\""))
    {
    }
    fgets(linha, sizeof(linha), arq);
    fgets(linha, sizeof(linha), arq);
    removerTags(linha, tagsRemovidas);
    trim(tagsRemovidas);
    strcpy(filme.genero, replace(tagsRemovidas, "&nbsp;", ""));

    // LER DURACAO

    while (!contains(fgets(linha, sizeof(linha), arq), "\"runtime\""))
    {
    }
    fgets(linha, sizeof(linha), arq);
    fgets(linha, sizeof(linha), arq);
    removerTags(linha, tagsRemovidas);
    trim(tagsRemovidas);
    filme.duracao = stringToMinutos(tagsRemovidas);

    // LER TITULO ORIGINAL

    while (!contains(fgets(linha, sizeof(linha), arq), "Título original"))
    {
    }
    if (contains(linha, "Título original"))
    {
        removerTags(linha, tagsRemovidas);
        trim(tagsRemovidas);
        strcpy(filme.tituloOriginal, replace(tagsRemovidas, "Título original ", ""));

        for (int i = 0; i < 3; i++)
            fgets(linha, sizeof(linha), arq);
    }
    if (contains(linha, "i>Situação"))
    {
        removerTags(linha, tagsRemovidas);
        trim(tagsRemovidas);
        strcpy(filme.situacao, replace(tagsRemovidas, "Situação ", ""));
    }
    fclose(arq);
    return filme;
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

    struct Filmes filme;
    char dir[100];

    for (int i = 0; i < numEntradas; i++)
    {
        strcpy(dir, "/tmp/filmes/");
        strcat(dir, entrada[i]);
        dir[strcspn(dir, "\n")] = 0;
        filme = ler(dir);
        imprimir(filme);
    }

    return 0;
}