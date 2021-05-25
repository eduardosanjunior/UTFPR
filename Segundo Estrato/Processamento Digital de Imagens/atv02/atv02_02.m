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
% Responda aqui no próprio script
% g é da classe:
%uint8
% Mín em g: , Máx em g:
% Mín = 20 e Max = 200
% g1 é da classe:
% Double
% Mín. em g1: , Máx em g1:
% Mín = 20 e Max = 200
% imshow(g1) mostra uma imagem:
% Não
% porque:
% Ao converter uma imagem de uma classe int para uma classe double,
% você estará tentando converter algo no range[0,255] para o range [0,1]
% o octave faz todas as conversões perdendo informação.
% g2 é da classe:
% Double
% Mín em g2: , Máx em g2:
% Mín = 0,078431 e Max = 0.7843
% g3 é da classe:
% Double
% Mín em g3: , Máx em g3:
% Mín = 0 e Max = 1
% Qual é a diferença entre as funções
% im2double e mat2gray?
% mat2gray converte uma imagem do tipo double para escala de cinza no 
% range [0,1], enquanto im2double aceita double, int e imagens true colorangle
% para realizar a conversão.