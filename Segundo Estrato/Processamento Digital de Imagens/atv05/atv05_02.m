%% IF69D - Processamento Digital de Imagens
%   Tarefa 05 - Exercício 02
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 01/04/2021


%apagando variaveis
close all, clear all, clc;

%carregar imagem
Img40 = imread('b5s.40.bmp');
Img100 = imread('b5s.100.bmp');

%criação dos filtros
F5 = fspecial('average', [5 5]);
F7 = fspecial('average', [7 7]);

%aplicação dos filtros
Img40_f5 = imfilter(Img40,F5);
Img40_f7 = imfilter(Img40,F7);
Img100_f5 = imfilter(Img100,F5);
Img100_f7 = imfilter(Img100,F7);

%plot das imagens
subplot(2,3,1), imshow(Img40), title('RM 40');
subplot(2,3,2), imshow(Img40_f5), title('RM 40 - 5x5');
subplot(2,3,3), imshow(Img40_f7), title('RM 40 - 7x7');
subplot(2,3,4), imshow(Img100), title('RM 100');
subplot(2,3,5), imshow(Img100_f5), title('RM 100 - 5x5');
subplot(2,3,6), imshow(Img100_f7), title('RM 100 - 7x7');