%% IF69D - Processamento Digital de Imagens
%   Tarefa 05 - Exercício 10
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 01/04/2021

%limpeza das variáveis
clear all, close all, clc;

%leitura e conversão da imagem
Img = imread('flowervaseg.png');
Img = im2double(Img);

%criação e aplição do filtro
HG = fspecial('laplacian', [5 5]);
Img_gaussian = imfilter(Img, HG, 'replicate');


unsharp_mask = Img - Img_gaussian;

%armazenamento das imagens com filtro
ImgCell = cell(1,5);

%Aplicação do filtro com constante multiplas de 2
for i = 1:5
    Img_unsharp = Img + (unsharp_mask*i*2);
    ImgCell{i} = im2uint8(Img_unsharp);
end

%plot da original
subplot(2,3,1), imshow(Img), title('Original');

%plot das imagens com filtro
for i = 1:5
   subplot(2,3,i+1), imshow(ImgCell{i}), title(['Multiplicador : ' num2str(i*2)]);    
end