%Autor: Eduardo v dos Santos Junior
%------------resolucao -----------

%Limpeza de variáveis
clear all, clc

%Carregamento da imagem
original =  imread('42049_20-200.png');

%Aplicação do contraste
autocontrast = mat2gray(original);

%Plot das Imagens
ax1 = subplot(2,2,1);
imshow(original)
title('Original')

ax2 = subplot(2,2,2);
imshow(autocontrast)
title('Autocontrast')
