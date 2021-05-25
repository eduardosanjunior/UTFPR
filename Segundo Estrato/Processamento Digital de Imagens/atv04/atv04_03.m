%% IF69D - Processamento Digital de Imagens
%   Tarefa 04 - Exercício 03
%   Autor : Eduardo Vanderlei dos Santos Junior
%   Data: 29/03/2021
%% Script

clear all;
close all;
clc;

I = imread('42049_20-200.png');
y = (0:255);

for i = 0:255
    y(i+1) = nnz(I == i);
end 

%%
figure, subplot(2,2,1), imshow(I), title('42049_20-200.png');
subplot(2,2,3), imhist(I), axis([0 255 0 4000]); title('histograma pronto');
subplot(2,2,2), bar(y), axis([0 255 0 4000]); title('histograma na unha');