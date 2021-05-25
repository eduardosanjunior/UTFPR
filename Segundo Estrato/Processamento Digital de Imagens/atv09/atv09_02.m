%% IF69D - Processamento Digital de Imagens
%   Tarefa 9 - Exercício 02
%   Autores : Eduardo Vanderlei dos Santos Junior
%   Data: 28/04/2021


%Mantra
clear all, close all, clc;

%carrega a imagem
knife = imread('f3_p3_knife_plane_drop_dy_2-22.jpg');
figure, imshow(knife), title('Original')
%Pseudocolorizacao
c1 =colormap(jet(350));
figure, imshow(knife, c1), title('Pseudocolorização')