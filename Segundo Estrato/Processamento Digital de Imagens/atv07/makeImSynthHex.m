function  s = makeImSynthHex(M, obj, bck, sd)
% Cria uma imagem sint�tica uint8 de dimens�es
% M linhas e M colunas. O objeto tem n�vel de
% cinza obj e o fundo tem n�vel de cinza bck.
% O centro tem n�vel de cinza zero.
% Adiciona ru�do Gaussiano de desvio padr�o sd.

nrhs = floor(M/2);
nchs = floor(M/2);

a = triu(ones(nrhs,nchs))*bck;
b = tril(ones(nrhs,nchs),-1)*obj;
g1 = uint8(a+b);

%display(a);
g2 = fliplr(g1);
g3 = flipud(g2);
g4 = flipud(g1);
s = [g2 g1; g3 g4];
[r c] = size(s);

g5 = ones(r,c);
%display(r);
g5(nrhs/4:end-(nrhs/4),nchs/4:end-(nchs/4)) = 0;
idx = g5 == 1;
s(idx) = bck;
% achei q seria + f�cil :-)
% deve existir uma maneira mais esperta...

circ = fspecial('gaussian', [r c], 315/10);
circ = circ < max(circ(:))/10;
%display(circ);
s = s .* uint8(circ);

% Suaviza, para o degrau n�o ser ideal
h = fspecial('average', [3 3]);
s = imfilter(s, h, 'replicate');
% Um ruidinho de m�dia zero e desvio padr�o sd
s = imnoise(s,'gaussian',...
(0/255),(sd/255)^2);