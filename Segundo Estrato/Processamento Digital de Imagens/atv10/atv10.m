%% IF69D - Processamento Digital de Imagens
%   Tarefa 8 - Exercício 01
%   Autores : Eduardo Vanderlei dos Santos Junior
%   Data: 26/04/2021


%mantra
clear all;
close all;
clc;

vimagens = dir(['C:\Users\Eduar\Desktop\atv10\hanoi_01\']);

% perceorre os arquvios da pasta procurando imagens
for i=1:length(vimagens)
  I=I+i;
end



tol = 5 .* ones(5, 3);

C = cell(5, 1);
C{1} = 'Amarelo';
C{2} = 'Vermelho';
C{3} = 'Azul';
C{4} = 'Verde';
C{5} = 'Laranja';

thr = [ 0.15 0.7 0.75;
         0.005 0.77 0.84;
       0.6 0.85 0.64;
        0.4 1 0.6;
        0.07 0.9 0.75];

thr_max = thr + tol;
thr_min = thr - tol;

thr_max(thr_max > 255) = 255;
thr_min(thr_min < 0) = 0;

thr_max = double(thr_max/255);
thr_min = double(thr_min/255);

img = imread('hanoi_01/hanoi_01_01.png');
img2 = rgb2hsv(img(109:234, 419:577, :));

H = zeros(3, 5);


for i = 1:1
    for j = 1:5
        H =((img2(:,:,1) > thr_min(j,1) & img2(:,:,1) < thr_max(j,1)));
        S = ((img2(:,:,2) > thr_min(j,2) & img2(:,:,2) < thr_max(j,2)));
        figure;
        subplot(1, 2, 1); imshow(H);
        subplot(1, 2, 2); imshow(S);
    end
end

for i = 1:5
    [~, idx] = max(H(:,i));
    
    disp([C{i} ': Disco ' num2str(idx)]);
end


