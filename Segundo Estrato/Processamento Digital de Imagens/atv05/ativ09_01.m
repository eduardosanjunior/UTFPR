%% IF69D - Processamento Digital de Imagens
%   Tarefa 9 - Exercício 01
%   Autores : Eduardo Vanderlei dos Santos Junior
%   Data: 28/04/2021


%Mantra
clear all, close all, clc;

% Carrega a Imagem
rgb =  imread('cherry01.jpg');


% Separa em  canais
r = rgb(:,:,1);
g = rgb(:,:,2);
b = rgb(:,:,3);

% monta comparação em canais (tirei a imagem, mas pode constatar que os canais R e G são os melhores para
%trabalhar. Vou deixar comentado a vizualização.
r_g_b =[r g b];
%figure, imshow(r_g_b)

% Subtrai um canal do outro (Segmentação)
cherry= imsubtract(r,g);

%limiarização
th = graythresh(cherry)
cherry_bw = im2bw(cherry, th);

%regularização
se = strel("square", 7);
cherry_bw2 = imclose(cherry_bw, se);
se = strel("square", 3);
cherry_bw3 = imopen(cherry_bw, se);
cherry_bw4 = imfill(cherry_bw3, 'holes');
figure, imshow(cherry_bw4), title('Mascara binária')


%Perimetro do filtro
perim_bw =bwperim(cherry_bw4, 8);


% Definindo o perimetro das cerejas
g_sat = g;
r_sat = r;
g_sat(perim_bw) = 255;
r_sat(perim_bw) = 255;
rgb2 = cat(3, r_sat, g_sat, b);
figure, imshow(rgb2), title('Perimetro')

%Destaque das cerejas
cherry_bw4d = double(cherry_bw4);
cherry_bw4dn = ~cherry_bw4d;
cherry_bw4d(cherry_bw4dn) = 0
rgb3 =double(rgb) .* repmat(cherry_bw4d, [1,1,3]);
rgb3 = uint8(rgb3);
figure, imshow(rgb3), title('destaque das cerejas')