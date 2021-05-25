%% IF69D - Processamento Digital de Imagens
%   Tarefa 04 - Exercício 05
%   Autor : Eduardo Vanderlei dos Santos Junior
%   Data: 29/03/2021
%% Script
clear all;
close all;
clc;

I = imread('gDSC04422m16.png');

% Questão 1
[counts,binLocations] = imhist(I,256);

% Questão 2
counts = counts ./ (size(I,1).*size(I,2));

% Questão 3, 4
y2 = uint8(255 .* cumsum(counts));

% Questão 5
J = intlut(I, y2);


%% plot
figure, subplot(2,2,1), imshow(I), title('gDSC04422m16.png');
subplot(2,2,3), imhist(I), axis([0 255 0 4000]); title('histograma');
subplot(2,2,2), imshow(J), title('gDSC04422m16.png processada');
subplot(2,2,4), imhist(J), axis([0 255 0 4000]); title('histograma equalizado');