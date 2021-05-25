%% IF69D - Processamento Digital de Imagens
%   Tarefa 05 - Exercício 04
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 01/04/2021

%apagando variaveis
clear all, close all, clc;

%carregar a imagem
Img = imread('salt-and-pepper1.tif');

%criação dos filtros
HA3 = fspecial('average', [3 3]);
HA5 = fspecial('average', [5 5]);

%aplicação dos filtros
Img_avg3 = imfilter(Img, HA3);
Img_avg5 = imfilter(Img, HA5);
Img_med3 = medfilt2(Img, [3 3]);
Img_med5 = medfilt2(Img, [5 5]);

%plot das imagens
subplot(2,3,1), imshow(Img), title('Original');
subplot(2,3,2), imshow(Img_avg3), title('Average 3x3');
subplot(2,3,3), imshow(Img_med3), title('Median 3x3');
subplot(2,3,4), imshow(Img_avg5), title('Average 5x5');
subplot(2,3,5), imshow(Img_med5), title('Median 5x5');
