%Autor: Eduardo v dos Santos Junior
%------------resolucao -----------

%limpa o cache
clear all; close all; clc

%carrega a imagem
aux =  imread('42049.jpg');

%conta valores �nicos
nc = unique(aux);

%pegando valor m�ximo
resposta = max(nc);

%mostrando a resposta
display(resposta)