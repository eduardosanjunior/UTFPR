%Autor: Eduardo v dos Santos Junior
%------------resolucao -----------
close all; clear all; clc;


%le a imagem para a variavel
G = imread('cameraman.tif');
% define tamanho da imagem das dimensoes da imagem
nr = size(G,1);
nc = size(G,2);
%retorna coordenadas 2-D com base nas coordenadas contidas nas variaveis.
[X,Y] = meshgrid(1:nr,1:nc);

N = nr*nc;

% Imagem de entrada
I = [X(:)';
     Y(:)';
     ones(1,N)];

%angulo base para a rotacao
ang = 30*pi/180;

%Essa variavel que vai "definir" o resultado da mudanca; 
%Nela esta o calculo e a -transformacao affine- da imagem, e nesse caso, 
% essa e a formula para realizar uma  translacao.
T = [ cos(ang) sin(ang) 0;
      -sin(ang) cos(ang) 0;
     0 0 1];
     
%Multiplica para pegar as novas coordenadas dos pixels. Todas as transformaÃ§Ãµes affine 
%podem ser feitas com uma multiplicacao de matrizes
K = T*I;

% interpolacao nas proximas linhas

% computa o menor elemento de cada linha
temp1 = min(K, [], 2);

% cria matriz com esses menores elementos do tamanho da imagem
m = repmat(temp1, 1, N);

% faz subtracao entre os novos pixels e o minimo
% transformação inversa
temp2 = K - m;

%arredonda para o inteiro mais proximo para mapear certo os pixels.
Kadj = 1 + floor(temp2);

% aloca tamanho da imagem nova
nrOut = max(Kadj(1,:));
ncOut = max(Kadj(2,:));
Gout = uint8(zeros(nrOut, ncOut));

%remapeia
for k = 1:length(Kadj)
  Gout(Kadj(1,k), Kadj(2,k)) = G(I(1,k), I(2,k));
end

%mostra a imagem
imshow(Gout);