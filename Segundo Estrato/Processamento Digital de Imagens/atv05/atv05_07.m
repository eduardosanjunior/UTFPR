%% IF69D - Processamento Digital de Imagens
%   Tarefa 05 - Exercício 07
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 01/04/2021


%apagando variaveis
clear all, close all, clc;

%lendo imagem
Img = imread('flowervaseg.png');

%alterando o tipo
Imgd = im2double(Img);

%Criando Filtro laplaciano
FL = -1*fspecial('laplacian', 0);

%aplicando filtro
ImgdL = imfilter(Imgd, FL, 'replicate');
ImgdLs = Imgd + ImgdL;

%truncada
ImgdLst = im2uint8(ImgdLs);

%normalizada
ImgdLsm = mat2gray(ImgdLs);

subplot(1,3,1), imshow(Imgd), title('Original');
subplot(1,3,2), imshow(ImgdLst), title('Truncada');
subplot(1,3,3), imshow(ImgdLsm), title('Normalizada');
