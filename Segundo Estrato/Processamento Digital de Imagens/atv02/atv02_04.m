%Autor: Eduardo v dos Santos Junior
%------------resolucao -----------
%[script]

%Carregamento da imagem
clear all; close all; clc
original = imread('42049_20-200.png');
figure, imshow(original), title('original')

%corte
%Professor n�o sei se era para ser assim, mas eu fiz no olh�metro mesmo
corte = original(85:270,180:280);
figure, imshow(corte), title('corte')

%salvar a imagem
imwrite(corte, 'passaro.png');

%retirando o pass�ro da original
spassaro = original;
original(85:270,180:280) = 0;
figure, imshow(original), title('Sem pass�ro')