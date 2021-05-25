%Autor: Eduardo v dos Santos Junior
%------------resolucao -----------

%limpa o cache
clear all; close all; clc

%carrega a imagem
aux =  imread('42049.jpg');

%conta valores únicos
nc = unique(aux);

%pegando valor máximo
resposta = max(nc);

%mostrando a resposta
display(resposta)