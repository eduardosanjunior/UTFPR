%% IF69D - Processamento Digital de Imagens
%   Tarefa 05 - Exercício 08
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 01/04/2021

%Limpar variaveis
clear all, close all, clc;

%leitura da imagem
Img = imread('flowervaseg.png');

%alterando o tipo da imagem
Imgd = im2double(Img);

%filtro extendido
HLIe = [1 1 1; 1 -8 1; 1 1 1];

%filtro normal
HLI = fspecial('laplacian', 0);

%aplicando filtro normal
ImgdL = imfilter(Imgd, HLI, 'replicate');
ImgdLs = Imgd - ImgdL;
ImgdLst = im2uint8(ImgdLs);

%aplicando filtro extendido
ImgdLe = imfilter(Imgd, HLIe, 'replicate');
ImgdLse = Imgd - ImgdLe;
ImgdLste = im2uint8(ImgdLse);

% Plot da imagem
subplot(1,3,1), imshow(Imgd), title('Original');
subplot(1,3,2), imshow(ImgdLst), title('Normal');
subplot(1,3,3), imshow(ImgdLste), title('Extendido');
