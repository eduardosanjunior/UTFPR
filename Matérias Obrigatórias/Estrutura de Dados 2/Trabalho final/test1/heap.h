#include <stdio.h>
#include <stdlib.h>
#define MAX_TEXTO 1024
#define MAX 100
#define TAM 61//tamanho do vetor de letras disponivel

typedef struct No{
    char c;
    int freq;
    struct No *esq, *dir;
}No;

typedef struct Arvore
{
    int tam;
    int capac;
    struct No **array;
}Arvore;

No* criaNo(char c, int freq);
Arvore* criaArvore(int capac);
void swapNo(No** a,  No** b);
void MinHeapify(Arvore* arvore, int id);
void imprimeArray(int arr[], int n);
No* constroiArvHuffman(char c[], int freq[], int tam);
void imprimeCod(No* no, int arr[], int top);
No* achaMin(Arvore* arvore);
void insereArvore(Arvore* arvore,  No* no);
void constroiArvore(Arvore* arvore);
void codigoHuffman(char c[], int freq[], int tam);
int contaCaracteres(char c, char* string, int op);


