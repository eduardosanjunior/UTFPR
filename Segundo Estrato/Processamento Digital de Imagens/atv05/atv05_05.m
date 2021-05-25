%% IF69D - Processamento Digital de Imagens
%   Tarefa 05 - Exerc�cio 05
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 01/04/2021


%apagando variaveis
clear all, close all, clc;

%tamanho da janela
windowSize = 3; 

%lendo vari�vel
Img = imread('salt-and-pepper1.tif');

%borda
Edge = (windowSize-1)/2;

%tamanho da imagem
[maxY, maxX] = size(Img);

%matriz de zeros
Img_med = zeros(maxY, maxX);
ImgEdge = [zeros(Edge, maxY); Img; zeros(Edge, maxY)];
ImgEdge = [zeros(size(ImgEdge, 1), Edge), ImgEdge, zeros(size(ImgEdge, 1), Edge)];


WindowCenter = (windowSize^2 + 1)/2;

%filtro
for y = Edge+1:maxY+Edge
    for x = Edge+1:maxX+Edge
        windowed = reshape(ImgEdge(y-Edge:y+Edge, x-Edge:x+Edge), 1, windowSize^2);
        sorted = sort(windowed);
        Img_med(y-Edge, x-Edge) = sorted(WindowCenter);
    end
end

%mudan�a para uint8
Img_med = uint8(Img_med);

%plot das imagens
subplot(1,2,1), imshow(Img), title('Original');
subplot(1,2,2), imshow(Img_med), title(['Pr�pia ', num2str(windowSize),'x',num2str(windowSize)]);
