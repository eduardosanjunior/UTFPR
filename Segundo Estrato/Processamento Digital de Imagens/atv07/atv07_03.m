%% IF69D - Processamento Digital de Imagens
%   Tarefa 07 - Exercício 03
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 15/04/2021

%mantra
clear all, close all, clc
 
% Cria imagem
w = 256;
objt = 192; fundo = 64; rdn = 10;
g = makeImSynthHex(w,objt,fundo,rdn);
 
g = im2double(g);
Sh = fspecial('sobel');
gSh = imfilter(g,Sh,'replicate','conv');
Sv = Sh';
gSv = imfilter(g,Sv,'replicate','conv');
 
% Imagem de magnitude do gradiente
S = abs(gSv) + abs(gSh)
S2 = sqrt(gSv.^2 + gSh.^2);
% Normaliza
gSh = mat2gray(gSh);
gSv = mat2gray(gSv);
S = mat2gray(S);
S2 = mat2gray(S);
 
% Display
figure, imshow(g)
title('Imagem de entrada')
figure, subplot(1,2,1), imshow(gSv)
title('Sobel vertical')
subplot(1,2,2), imshow(gSh)
title('Sobel horizontal')
figure, imshow(S),
title('Magnitude do gradiente com valores absolutos')

figure, imshow(S2),
title('Magnitude do gradiente com raiz quadrada')

%Rodar um de cada vez (os dois juntos demora muito, mas mostra)