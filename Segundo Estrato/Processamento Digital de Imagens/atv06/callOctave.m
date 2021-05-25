%% IF69D - Processamento Digital de Imagens
%   Tarefa 06 - Exercício 01
%   Autor: Eduardo Vanderlei dos Santos Junior
%   Data: 08/04/2021


%a) O resultado não foi similar pois nao ha um desenvolvimento mais profundo da funcao.

%b) O MSSIM vizualiza a imagem por estruturas e nao por pixel a pixel, como
%o MSE. dependendo do caso, o MSE pode sobressair. (por exemplo, rui­dos),
%o MSE tambem eh um pouco mais antigo que torna ele mais utilizado.

% Limpeza de variaveis
clear all;
close all;
clc;

% Leituras de Imagens
i0 = imread('original.jpeg');
i1 = imread('01.jpeg');
i2 = imread('02.jpeg');
i3 = imread('03.jpeg');
i4 = imread('04.jpeg');
i5 = imread('05.jpeg');

% Algoritmo
mmse1 = immse(i1, i0);
disp(mmse1);
[mssim, ssim_map] = ssim(i1, i0);
mssim                        
mmse2 = immse(i2, i0);
disp(mmse2);
[mssim2, ssim_map2] = ssim(i0, i2);
mssim2
mmse3 = immse(i3, i0);
disp(mmse3);
[mssim3, ssim_map3] = ssim(i0, i3);
mssim3
mmse4 = immse(i4, i0);
disp(mmse4);
[mssim4, ssim_map4] = ssim(i0, i4);
mssim4
mmse5 = immse(i5, i0);
disp(mmse5);
[mssim5, ssim_map5] = ssim(i0, i5);
mssim5


%ssim da imagem 1 = 0.9878 - mse da imagem 1 = 144.01
%ssim da imagem 2 = 0.8829 - mse da imagem 2 = 146.70
%ssim da imagem 3 = 0.8639 - mse da imagem 3 = 79.888
%ssim da imagem 4 = 0.7368 - mse da imagem 4 = 122.06
%ssim da imagem 5 = 0.7107 - mse da imagem 5 = 112.27







