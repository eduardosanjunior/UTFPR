%% IF69D - Processamento Digital de Imagens
%   Tarefa 05 - Exercício 03
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 01/04/2021

% Esse deu erro, provavelmente eu fiz errado o filtro
%apagando variaveis
close all, clear all, clc;

%leitura da imagem
Img40 = imread('b5s.40.bmp');
Img100 = imread('b5s.100.bmp');

%tamanho da imagem
[X4,Y4] =size(Img40)
[X1,Y1] =size(Img100)

%criando filtro 40 
Box_size = 5;
H_proprio_40 = zeros(Box_size);
sigma = 1; %desvio padrão
v = sigma^2; %variância
H_proprio_40(:) = (1/(2*pi*v))*exp(-(X4.^2+Y4.^2)/(2*v));;
Img_proprio_40 = imfilter(Img40,H_proprio_40);


%criando filtro 100
Box_size = 5;
H_proprio_100 = zeros(Box_size);
sigma = 1; %desvio padrão
v = sigma^2; %variância
H_proprio_100(:) = (1/(2*pi*v))*exp(-(X1.^2+Y1.^2)/(2*v));;
Img_proprio_100 = imfilter(Img100,H_proprio_100);


%plot das imagens
subplot(2,3,1), imshow(Img40), title('Imagem Original 40');
subplot(2,3,2), imshow(Img_proprio_40), title('Average 5x5');
subplot(2,3,3), mesh(Img_proprio_40), title('Grafico 3d 40');
subplot(2,3,4), imshow(Img100), title('Imagem Original 100');
subplot(2,3,5), imshow(Img_proprio_100), title('Average 5x5');
subplot(2,3,6), mesh(Img_proprio_100), title('Grafico 3d 100');

