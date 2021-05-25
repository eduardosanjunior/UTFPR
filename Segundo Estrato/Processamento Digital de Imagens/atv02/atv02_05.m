%Autor: Eduardo v dos Santos Junior
%------------resolucao -----------
%[script]
clear all; close all; clc

%definição da escala de cinza de 0 a 255
cinza = 0:1:255;

%conversão para inteiro
cinza = uint8(cinza);

%Remapeamento para o tamanho 50 px de altura repitindo a matriz uma vez
cinza = repmat(cinza, 50, 1);

%Mostra a imagem
figure, imshow(cinza), title('Escala de Cinza')

%salva a imagem
imwrite(cinza, 'cinza.png');