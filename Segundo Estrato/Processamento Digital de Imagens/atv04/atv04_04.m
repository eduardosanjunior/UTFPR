%% IF69D - Processamento Digital de Imagens
%   Tarefa 04 - Exerc�cio 04
%   Autor : Eduardo Vanderlei dos Santos Junior
%   Data: 29/03/2021
%% Script
clear all;
close all;
clc;

I = imread('gDSC04422m16.png');
J = histeq(I,256);

%% Professor aqui eu n�o entendi a quest�o se o senhor queria ver a imagem ou a 
%% o histograma ent�o deixei os 2
figure, subplot(2,2,1), imshow(I), title('gDSC04422m16.png');
subplot(2,2,3), imhist(I), axis([0 255 0 4000]); title('histograma');
subplot(2,2,2), imshow(J), title('gDSC04422m16.png processada');
subplot(2,2,4), imhist(J), axis([0 255 0 4000]); title('histograma equalizado');