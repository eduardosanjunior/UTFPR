%% IF69D - Processamento Digital de Imagens
%   Tarefa 07 - Exercício 06
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 15/04/2021

%mantra
clear all, close all, clc

%carregamento da imagem
G = imread('cameraman.tif');

%limiar 
t=0.074605;

%Filtro Laplacian of Gaussian (LoG)
G1 = edge (G, "LoG");
G2 = edge (G, "LoG", t, 1.5);
G3 = edge (G, "LoG", t, 1);
G4 = edge (G, "LoG", t, 0.5);


%display
figure, imshow(G)
title('Imagem de entrada')
figure, imshow(G1)
title('G1')
figure, imshow(G2)
title('G2')
figure, imshow(G3)
title('G3')
figure, imshow(G4)
title('G4')

%O Sigma aumenta a distribuicao gaussiana do ~blur~ e isso faz com que a imagem fique mais 'suave', ou seja,
%com bordas mais indetectaveis. 