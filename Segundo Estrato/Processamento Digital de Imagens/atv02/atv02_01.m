%Autor: Eduardo v dos Santos Junior
%------------resolucao -----------

%limpa o cache
clear all; close all; clc

%carrega a imagem
aux =  imread('42049.jpg');
original = aux

%verifica��o de Min/M�x
maantes = min(aux(:));
Mantes = max(aux(:));

%Percorrer matriz e realizar as trocas
 [lin,col] = size(aux);

    for ii=1:lin
        for jj =1:col
            if aux(ii,jj) > 200
                aux(ii,jj) = 200;
            end
            if aux(ii,jj) <20
                aux(ii,jj) = 20;
            end   
        end
  end 
  
%Verifica��o dos M�n/M�x ap�s a convers�o
mdepois = min(aux(:));
Mdepois = max(aux(:));

%Mostrar imagens
figure, imshow(original), title('orig')
figure, imshow(aux), title('aux')

%Escrever nova img alterada.
imwrite(aux,'42049_20-200.png');