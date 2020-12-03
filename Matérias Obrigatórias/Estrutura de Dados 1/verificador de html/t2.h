#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct elemento
{
	struct elemento* ant;
	struct elemento* prox;
	char* tag;
}elemento;

typedef struct pilha
{
	elemento* topo;
	elemento* primeiro;
} pilha;


pilha* inicia_pilha ();

elemento* inicia_elemento (char* tag);

//Verifica se a pilha contém elementos (1 para vazia, 0 caso contrario)
int vazia (pilha *p);

//Insere um elemento na pilha
void inserir (elemento* e, pilha *p);

//Remove o topo da pilha
void remover (pilha *p);

//Compara se duas tags são iguais (1 para iguais, 0 para diferentes)
int compara_tag (char* ch1, char* ch2);

//Extrai o comando de uma tag
char* extrai_tag (char* tag);

//Verifica a validade de um arquivo .html
int compara_html (char* diretorio);