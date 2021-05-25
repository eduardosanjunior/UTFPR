%Autor: Eduardo v dos Santos Junior
%------------resolucao -----------
%a02_02 [script]
clear all; close all; clc
g=imread('42049_20-200.png');
mg=min(g(:)); Mg=max(g(:));
figure, imshow(g), title('g')
g1=double(g); mg1=min(g1(:)); Mg1=max(g1(:));
figure, imshow(g1); title('g1')
g2=im2double(g); mg2=min(g2(:)); Mg2=max(g2(:));
figure, imshow(g2), title('g2')
g3=mat2gray(g); mg3=min(g3(:)); Mg3=max(g3(:));
figure, imshow(g3), title('g3')
3 / 4
% Responda aqui no pr�prio script
% g � da classe:
%uint8
% M�n em g: , M�x em g:
% M�n = 20 e Max = 200
% g1 � da classe:
% Double
% M�n. em g1: , M�x em g1:
% M�n = 20 e Max = 200
% imshow(g1) mostra uma imagem:
% N�o
% porque:
% Ao converter uma imagem de uma classe int para uma classe double,
% voc� estar� tentando converter algo no range[0,255] para o range [0,1]
% o octave faz todas as convers�es perdendo informa��o.
% g2 � da classe:
% Double
% M�n em g2: , M�x em g2:
% M�n = 0,078431 e Max = 0.7843
% g3 � da classe:
% Double
% M�n em g3: , M�x em g3:
% M�n = 0 e Max = 1
% Qual � a diferen�a entre as fun��es
% im2double e mat2gray?
% mat2gray converte uma imagem do tipo double para escala de cinza no 
% range [0,1], enquanto im2double aceita double, int e imagens true colorangle
% para realizar a convers�o.