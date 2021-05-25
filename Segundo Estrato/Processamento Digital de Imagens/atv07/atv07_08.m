%% IF69D - Processamento Digital de Imagens
%   Tarefa 07 - Exercício 08
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 15/04/2021

clear all, close all, clc
G = imread('cameraman.tif');

%Canny
canny = edge(G,'Canny');
%Sobel
sob = edge(G,'Sobel',0.071);


%Display
figure, imshow(G)
title('Imagem de entrada')
figure, subplot(1,2,1), imshow(canny)
title('Canny')
subplot(1,2,2), imshow(sob)
title('Sobel')
%O Sobel (com esse treshhold) para ficar similar ao resultado default do Canny
%realmente e mais sensi­vel a rui­dos e isso faz tambem com que pegue detalhes da camera e 
%o fato de ele usar gradiente nao pegue a borda do predio do fundo por exemplo,
%coisa que o Canny faz, por nao ocorrer uma variacao tao grande entre o fundo e o objeto.