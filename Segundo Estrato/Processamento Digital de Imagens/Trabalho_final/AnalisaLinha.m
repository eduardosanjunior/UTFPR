function [ Resultado ] = scanline( Linhas, Colunas, Tamanho )
%   AnalisaLinha varre as colunas na linha procurando marcações.
%   A função recebe as linhas e itera pelas colunas procurando marcações
%   Retorna um vetor com o mesmo tamanho das colunas com 1 se a marca estiver presente,
%   e 0 caso contrário

    %Cria o retorno
    Resultado = zeros(size(Colunas));
    
    % Para cada coluna
    for c = 1:length(Colunas)
        % verifica se um quadrado 10x10 tem pelo menos 75 pixels pintados
        % para considerar como uma marcaçao
        if (sum(sum(Linhas(:,(Colunas(c)-5):(Colunas(c)+5)) ~= 0)) > 75)
            Resultado(c) = 1;
        end
	end
end

