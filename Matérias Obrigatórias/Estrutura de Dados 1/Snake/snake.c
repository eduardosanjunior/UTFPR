#include "snake.h"

void gotoxy(int x,int y){
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),(COORD){x,y}); //Achei essa função na documentação, serve para mover o mouse até certa posição no 
}																			//console, vai auxiliar a desenhar as bordas do tabuleiro e a mover a cobra.

void imprimeCampo(int w, int h){
	int i;
	for(i=1;i<h;i++){		//cada um desses 'for' imprime uma linha da beirada do campo.
		gotoxy(0,i);
		printf("%c",'#');
	}
	for(i=1;i<w;i++){
		gotoxy(i,0);
		printf("%c",'#');
	}
	for(i=1;i<h;i++){
		gotoxy(w,i);
		printf("%c",'#');
	}
	for(i=1;i<w;i++){   
		gotoxy(i,h);
		printf("%c",'#');
	}
}