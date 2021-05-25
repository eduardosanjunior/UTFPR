function [ y ] = adapt_thresh(x)
%Função para converter a imagem em escala de cinza em P&B

    %Converte a estrutura em preto e branco
    y = im2bw(x.data, 0.7*max(x.data(:)));
end

