%% IF69D - Processamento Digital de Imagens
%   Tarefa 05 - Exerc�cio 06
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 01/04/2021

 
%apagando variaveis
clear all, close all, clc;

%cria��o dos filtros
HL = fspecial('laplacian', 0)

% m�scara do Laplaciano
%HL = 0   1   0
%     1  -4   1
%     0   1   0
