#include "heap.h"
#include <string.h>
No* criaNo(char c, int freq)//cria um n� da arvore com o caractere e a respectiva frequencia em que ela aparece no texto
{
    No* novo= (No*) malloc(sizeof( No));//aloca espa�o de memoria
    novo->esq = NULL;
    novo->dir = NULL;
    novo->c = c;//caractere correspondente
    novo->freq = freq;//frequencia do caractere
    return novo;
}

Arvore* criaArvore(int capac)//cria uma arvore, de acordo com a capacidade pedida
{
    Arvore* arvore =(Arvore*) malloc(sizeof(Arvore));//aloca espa�o de memoria
    arvore->tam = 0;//tamanho inicial igual a zero
    arvore->capac = capac;//capacidade pedida
    arvore->array = (No**)malloc(arvore->capac * sizeof(No*));//aloca o vetor de n�s(esquerda e direita)
    return arvore;
}

void swapNo(No** a,  No** b)//troca os n�s da arvore
{
    No* aux = *a;//auxiliar recebe o n� a
    *a = *b; //a recebe n� b
    *b = aux;//b recebe o n� a
}
//verificando a propriedade de heap
void MinHeapify(Arvore* arvore, int id)
{
    int menor = id;
    int esq = 2 * id + 1;
    int dir = 2 * id + 2;

    if ( esq < arvore->tam && arvore->array[esq]->freq < arvore->array[menor]->freq){
    //se a frequencia dos n�s da esquerda forem menores que a frquencia dos menores n�s
      menor = esq;//ent�o a esquerda � agora o menor
    }
    if (dir < arvore->tam && arvore->array[dir]->freq < arvore->array[menor]->freq){
      //se a frequencia dos n�s da direita forem menores que a frquencia dos menores n�s
      menor = dir;//ent�o a direita � agora a menor
    }
    if (menor != id)//se o menor for diferente do id que veio da fun��o
    {
        swapNo(&arvore->array[menor], &arvore->array[id]);//ent�o troca os n�s menor e id
        MinHeapify(arvore, menor);//chamada recursiva, at� todos os n�s estarem nas posi��es certas
    }
}

void insereArvore(Arvore* arvore,  No* no)//insere um n� na arvore
{
    arvore->tam = arvore->tam+1;//aumenta o tamanho da arvore
    int i = arvore->tam - 1;
    while (i>0 && no->freq < arvore->array[(i - 1)/2]->freq) //enquando a frequencia do n� for menor que a frequancia dos n�s da arvore na posi��o(i-1)/2
    {
        arvore->array[i] = arvore->array[(i - 1)/2];//n� da arvore na posi��o i � = ao n� da arvore na posi��o de (i-1)/2
        i = (i - 1)/2;
    }
    arvore->array[i] = no; //insere o n� na arvore
}

void constroiArvore(Arvore* arvore)
{
    int i, n = arvore->tam - 1;
    for (i = (n - 1)/2; i >= 0; i--)//acha o lado menor a partir da posicao i dada(metade do tamanho da arvore) at� a posicao 0
        MinHeapify(arvore, i);
}

//extrai o valor minimo da arvore
No* achaMin(Arvore* arvore)
{
    No* aux = arvore->array[0];
    arvore->array[0] = arvore->array[arvore->tam - 1];
    arvore->tam = arvore->tam-1;
    MinHeapify(arvore, 0);
    return aux;
}

No* constroiArvHuffman(char c[], int freq[], int tam)
{
    No *esq, *dir, *topo;
    Arvore* arvore = criaArvore(tam);//cria a arvore de acordo com a capacidade especificada
    int i;
    for (i = 0; i < tam; ++i)
        arvore->array[i] = criaNo(c[i], freq[i]); //o array da arvore recebe os nos que est�o sendo criados com os caracateres e frequencias
    arvore->tam = tam;//seta o tamanho da arvore que foi criada
    constroiArvore(arvore);//chama a fun��o que constroi a arvore
    while (arvore->tam != 1)//enquando o tamanho da arvore for diferente de 1
    {
        esq = achaMin(arvore);//extrai o minimo do n� esquerdo
        dir = achaMin(arvore);//extrai o minimo do n� direito
        topo = criaNo(' ', (int)(esq->freq+dir->freq));//cria um n� vazio com a soma das frequencias dos n�s esquerdo e direito
        topo->esq = esq; // a esquerda do n� vazio � o minimo encontrado a esquerda
        topo->dir = dir; //a direita do n� vazio � o minimo encontrado a direita
        insereArvore(arvore, topo);
    }
    return achaMin(arvore);//retorna o valor minimo da arvora apos todo o processo
}

void imprimeCod(No* no, int vet[], int topo)
{//imprime na tela os codigos que s�o gerados ao percorrer a arvore criada
    if (no->esq!=NULL)//se a esquerda n�o for nula
    {
        vet[topo] = 0;//para o lado esquerdo o codigo referente � zero
        imprimeCod(no->esq, vet, topo + 1);//imprime o lado esquerdo
    }
    if (no->dir!=NULL)//se a direita n�o for nula
    {
        vet[topo] = 1;//para o lado direito o codigo referente � um
        imprimeCod(no->dir, vet, topo + 1);//imprime o lado direito
    }
    if (no->esq == NULL && no->dir == NULL)//se a direita e a esquerda forem nulas, o n� � uma folha e � nele que h� as informa��es
    {
        if(no->freq!=0){//n�o imprime as folhas onde a frequencia � nula para evitar processos desnecessarios
            printf(" %c: ", no->c);//imprime na tela o caracter
            imprimeArray(vet, topo);//chama a fun�ao que imprime o codigo de huffman gerado para ela
        }
    }
}

void imprimeArray(int vet[], int n)//funcao auxiliar para imprimir o codigo de huffman gerado para um caracter
{
    int i;
    for (i = 0; i < n; ++i)
        printf("%d", vet[i]);
    printf("\n");
}

void codigoHuffman(char c[], int freq[], int tam)
{
   No* no = constroiArvHuffman(c, freq, tam);
   int vetor[MAX];
   imprimeCod(no, vetor, 0);//imprime na tela o codigo de huffman gerado
}

int contaCaracteres(char c, char* string, int op){//conta a frequancia em que um determinado caractere aparece
    int i = 0;

    if(op == 1){//op � a opcao que o usuario escolheu no menu, 1 define que o usuario pediu para abrir um arquivo para ser codificado
        string[strcspn(string, "\n")]=0;
        FILE* arq = fopen(string,"r");
        char d;
        while((d = fgetc(arq) ) != EOF){//enquanto nao chegar ao fim do arquivo
            if(d == c)//verifica se o caractere � o que eu estou procurando
                i++;//incrementa a contadora;
        }
    }
    else if(op==2){//a opcao 2 define que o usuario digitou uma string para ser codificada
        i = 0;//contadora
        int a = 0;//variavel para controlar a posicao do vetor
        while(string[a]!='\0'){//enquanto n�o chegar ao fim da string
            if(string[a]==c){//se a posicao do vetor for igual ao caractere desejado
               i++;//incrementa a contadora
            }
             a++;//proxima posicao do vetor
        }
    }
    return i;//retorna a contadora
}

