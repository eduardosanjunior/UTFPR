#include <stdio.h>
#include <stdlib.h>
#include "heap.h"


/*Código de Huffman - Trabalho de Estrutura de Dados 2
Alunos: Juliana Lima S. B. Cavalcante - 1614690
        Eduardo Vanderlei Junior - 1458884 */
int main()
{
    char texto[MAX_TEXTO];
    int op=0;
    while(op!=3){
        printf("1 - Gerar codigo huffman de um arquivo .txt\n");
        printf("2 - Digitar um texto para gerar um codigo de huffman\n");
        printf("3 - Sair\n");
        printf("Digite a opcao: \n");
        scanf("%d", &op);
        if(op==1 || op==2){
            fflush(stdin);
            printf("Texto:");
            fgets(texto, MAX_TEXTO, stdin);//gets foi utilizado para que fosse possivel captar tambem os espaços em brancos digitados pelo usuario
            char vetorLetras[] =  {'a','b','c','d','e','f','g','h','i','j',
            'k','l','m','o','p','q','r','s','t','u','v','w','x','y','z', ' ', ',', '.', '(', ')',
            ';','!','?','\n', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//caracteres disponiveis a serem encontradas no texto
            int vetorQtd[TAM];//vetor que grava a quantidade dos caracteres encontrados
            int i;
            for (i=0; i<TAM;i++){
                vetorQtd[i] = contaCaracteres(vetorLetras[i], texto, op);
            }
            int tamanho = sizeof(vetorLetras)/sizeof(vetorLetras[0]);
            codigoHuffman(vetorLetras, vetorQtd, tamanho);
        }
    }
        return 0;
}
