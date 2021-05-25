%% IF69D - Processamento Digital de Imagens
%   Tarefa 07 - Exercício 02
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 15/04/2021

%mantra
clear all, close all, clc


g = imread('42049.jpg');
g = im2double(g);
Sh = fspecial('sobel');
gS1 = imfilter(g,Sh,'replicate');
gS2 = imfilter(g,Sh,'replicate', 'conv');
 
% Normaliza, pois existem
% valores negativos
gS1 = mat2gray(gS1);
gS2 = mat2gray(gS2);
 
% Display
figure, imshow(g)
figure, subplot(1,2,1), imshow(gS1),
title('Com conv')
subplot(1,2,2), imshow(gS2)
title('Sem conv')

% 
%Nao, pois ele detecta as bordas com base na variacao 
%de intensidade horizontal e vertical