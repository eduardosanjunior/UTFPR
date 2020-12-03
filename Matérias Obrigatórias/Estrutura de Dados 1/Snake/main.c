/* Considera��es gerais: n�o criei um tipo especifico de lista pois n�o achei necessidade, j� que
 * voc� n�o pediu para a cobra crescer ou comer comida. Utilizei um vetor simples.
 * Na hora que a cobra est� 'nascendo', d� um bug de uma 'itera��o' onde ela n�o cresce, e n�o descobri
 * como resolver isso, mas depois ela cresce normalmente.
 * :@
 * Abra�os
 */

#include "snake.h"
#define TAM 7

int main(){
	int i, w, h, d=2,cx[TAM+1]={2,2},cy[TAM+1]={2,2},t=TAM; //x � o contador b�sico, w e h guardar�o a width e height do tabuleiro, d � a dire��o, cx e cy s�o as coordenadas
    char tecla='a';		//t � o tamanho da cobra, e a tecla vai guardar qual tecla vai entrar.
	system("cls");
	printf("Entre com as dimensoes do campo: (minimo 5x5)\n");
	scanf("%d %d", &w, &h);					//esse scan serve para receber a entrada: largura e altura do campo
	printf("Regras:\nUse as teclas e, b, c e d para movimentar a cobra para\nesquerda, baixo, cima e direita, respectivamente.\nAperte 'S' a qualquer momento para sair da aplicacao.\nAperte qualquer tecla para comecar.");
	getch();
	system("cls");
    imprimeCampo(w, h);
    while(tecla!='s'){
		while(tecla!='s'&&!(tecla=kbhit())){ //esse while vai aguardar uma tecla ser pressionada
			for(i=t;i>0;i--){ //primeiro, vai atualizara posi��o da cobra, independente de ser pressionado uma tecla ou n�o
				cx[i]=cx[i-1];
                cy[i]=cy[i-1];
            }
            if(d==0)		//d guarda a dire��o da cobra:0 � esquerda, 1 � cima, 2 � direita e 3 � baixo
				cx[0]--;	//e atualiza as coordenadas baseado na tecla pressionada. o padr�o � a cobra indo pra
            if(d==1)		//direita, 2.
				cy[0]--;
            if(d==2)
				cx[0]++;
            if(d==3)
				cy[0]++;
            gotoxy(cx[t],cy[t]); //aqui eu apago o tra�o deixado pelo rastro anterior
            printf(" ");
            gotoxy(cx[0],cy[0]); //e fa�o o novo tra�o na cabe�a da cobra.
            printf("%c",'*');
            Sleep(500); //aqui ocorre a pausa, para n�o ficar muito r�pido a velocidade da cobra
            if(cy[0]==1||cy[0]==h-1||cx[0]==1||cx[0]==w-1) //e se a cobra chegar em alguma extremidade, o jogo acaba
				tecla='s';
			for(i=1;i<t;i++){
				if(cx[0]==cx[i]&&cy[0]==cy[i])
					tecla='s';
			}
        }
        if(tecla!='s')				//aqui eu pego qual tecla foi pressionada, e dependendo da tecla, atualizo a dire��o da cobra
			tecla=getch();
        if(tecla=='e'||tecla=='E') 	//ou saio do jogo, se chegar a extremidade.
			d=0;
        if(tecla=='c'||tecla=='C')
			d=1;
        if(tecla=='d'||tecla=='D')
			d=2;
        if(tecla=='b'||tecla=='B')
			d=3;
        if(cy[0]==1||cy[0]==h-2||cx[0]==1||cx[0]==w-2)
			tecla='s';
    }
	system("cls");
	printf("\n\nVoce Perdeu! Pressione qualquer tecla para sair.");
	getch();
	system("cls"); //no fim, limpa a tela
	return 0;
}