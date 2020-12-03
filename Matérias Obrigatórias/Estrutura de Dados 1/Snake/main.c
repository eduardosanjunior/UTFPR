/* Considerações gerais: não criei um tipo especifico de lista pois não achei necessidade, já que
 * você não pediu para a cobra crescer ou comer comida. Utilizei um vetor simples.
 * Na hora que a cobra está 'nascendo', dá um bug de uma 'iteração' onde ela não cresce, e não descobri
 * como resolver isso, mas depois ela cresce normalmente.
 * :@
 * Abraços
 */

#include "snake.h"
#define TAM 7

int main(){
	int i, w, h, d=2,cx[TAM+1]={2,2},cy[TAM+1]={2,2},t=TAM; //x é o contador básico, w e h guardarão a width e height do tabuleiro, d é a direção, cx e cy são as coordenadas
    char tecla='a';		//t é o tamanho da cobra, e a tecla vai guardar qual tecla vai entrar.
	system("cls");
	printf("Entre com as dimensoes do campo: (minimo 5x5)\n");
	scanf("%d %d", &w, &h);					//esse scan serve para receber a entrada: largura e altura do campo
	printf("Regras:\nUse as teclas e, b, c e d para movimentar a cobra para\nesquerda, baixo, cima e direita, respectivamente.\nAperte 'S' a qualquer momento para sair da aplicacao.\nAperte qualquer tecla para comecar.");
	getch();
	system("cls");
    imprimeCampo(w, h);
    while(tecla!='s'){
		while(tecla!='s'&&!(tecla=kbhit())){ //esse while vai aguardar uma tecla ser pressionada
			for(i=t;i>0;i--){ //primeiro, vai atualizara posição da cobra, independente de ser pressionado uma tecla ou não
				cx[i]=cx[i-1];
                cy[i]=cy[i-1];
            }
            if(d==0)		//d guarda a direção da cobra:0 é esquerda, 1 é cima, 2 é direita e 3 é baixo
				cx[0]--;	//e atualiza as coordenadas baseado na tecla pressionada. o padrão é a cobra indo pra
            if(d==1)		//direita, 2.
				cy[0]--;
            if(d==2)
				cx[0]++;
            if(d==3)
				cy[0]++;
            gotoxy(cx[t],cy[t]); //aqui eu apago o traço deixado pelo rastro anterior
            printf(" ");
            gotoxy(cx[0],cy[0]); //e faço o novo traço na cabeça da cobra.
            printf("%c",'*');
            Sleep(500); //aqui ocorre a pausa, para não ficar muito rápido a velocidade da cobra
            if(cy[0]==1||cy[0]==h-1||cx[0]==1||cx[0]==w-1) //e se a cobra chegar em alguma extremidade, o jogo acaba
				tecla='s';
			for(i=1;i<t;i++){
				if(cx[0]==cx[i]&&cy[0]==cy[i])
					tecla='s';
			}
        }
        if(tecla!='s')				//aqui eu pego qual tecla foi pressionada, e dependendo da tecla, atualizo a direção da cobra
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