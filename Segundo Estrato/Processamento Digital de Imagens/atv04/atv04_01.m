%% IF69D - Processamento Digital de Imagens
%   Tarefa 04 - Exercício 01
%   Autor : Eduardo Vanderlei dos Santos Junior
%   Data: 29/03/2021
%% Script

clear all;
close all;
clc;

I = imread('vpfig.png');

%Aloca uint8
%para depopis usar funcao intlut (y1 é a LUT)
y1 = uint8(zeros([1 256]));
%Equação da reta inferior y = (1/3)*x
y1(1:97) = (1/3)*(0:96);
%Equação da reta intermediária y = 3*x -256
y1(98:161) = 3*(97:160) - 256;
%Equação da reta superior y = (1/3)*x + 170
y1(162:256) = (1/3)*(161:255) + 170;


figure, subplot(2,2,1), imshow(I), title('Original');
subplot(2,2,2), imshow(intlut(I,y1)), title('y1');
