%% IF69D - Processamento Digital de Imagens
%   Tarefa 8 - Exercício 01
%   Autores : Eduardo Vanderlei dos Santos Junior
%   Data: 26/04/2021


%mantra
clear all;
close all;
clc;

% Recebe categoria do usuário
pasta = input('Digite a categoria desejada = ');
imagens = dir([pwd '\mugs\mug_0' num2str(pasta) '\']);      
tarquivos = length(imagens);
i = 0;

% perceorre os arquvios da pasta procurando imagens
for ii=1:tarquivos
	if (imagens(ii).isdir == 0)
        i = i + 1;
        imagemAtual = imagens(ii).name;
        imagemAtual = imread([pwd '\mugs\mug_0' num2str(pasta) '\' imagemAtual]);
        vimagens{i} = imagemAtual;
	end
end

if (i == 0)
    error('Categoria inexistente!');
end

% Recebe categoria do braço
hand = input('Digite 0 se for destro, 1 se for canhoto = ');

%tratamento de execeção
if (hand ~= 0 && hand ~= 1)
    hand = 0;
    disp('Vou te considerar canhoto.');
end

% percorre imagens detectando as bordas
for i=1:length(vimagens)
    I = vimagens{i};
    I = mat2gray(imcrop(I, [125 160 400 250])); % crop para cortar partes irrelevantes
    level = graythresh(I);
    BW = edge(I);
    vimagens{i} = BW;
end


idx_min = zeros( 1, length(vimagens));
idx_max = zeros( 1, length(vimagens));

% procura-se o primeiro pixel branco mais à esquerda e mais a direita
for i=1:length(vimagens)
    idx_min(i) = find(sum(vimagens{i},1)~=0, 1, 'first');
    idx_max(i) = find(sum(vimagens{i},1)~=0, 1, 'last');
end

% Se for canhoto a melhor opção eh quando o primeiro pixel branco estiver mais a esquerda da imagem
% e para destro quando o pixel estiver mais a direita
[ ~, canhoto ] = min(idx_min);
[ ~, destro ] = max(idx_max);

%mostra melhor opção de imagem
if (hand == 0)
    disp(['A imagem correta para destro é a imagem ' num2str(destro)]);
else
    disp(['A imagem correta para canhoto é a imagem ' num2str(canhoto)]);
end
