%% IF69D - Processamento Digital de Imagens
%   Tarefa 05 - Exercício 01
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 01/04/2021


%apagando variaveis
close all, clear all, clc;

%Lendo a imagem
Img = imread('Lenna256g.png'); 

%gerando ruido
Img_Ruido = imnoise(Img,'gaussian',(0/255),(20/255)^2); 

%criando filtro
Box_size = 3;
H_proprio = zeros(Box_size);
H_proprio(:) = 1/(Box_size^2);
Img_proprio = imfilter(Img,H_proprio);

%plot das imagens
subplot(2,3,1), imshow(Img), title('Imagem Original');
subplot(2,3,2), imshow(Img_Ruido), title('Imagem com ruído');
subplot(2,3,3), imshow(Img_proprio), title('Meu filtro 3x3');
subplot(2,3,4), mesh(Img_proprio), title('imagem com filtro');
subplot(2,3,5), mesh(H_proprio), title('filtro');