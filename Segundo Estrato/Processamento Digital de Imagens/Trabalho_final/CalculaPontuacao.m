function [ Resultados ] = CalculaPontuacao(Alunos, Gabaritos, Valores, ValorProva)
%   CalculaPontuacao atribui as notas aos alunos
%   a função recebe as informações do OMR e calcula a nota   

   
    Resultados = struct('Codigo', {}, 'Acertos', {}, 'Notas', {}, 'NotaFinal', {});

     % para cada prova
    for i = 1:length(Alunos)
        
        % retorna um vetor com 0 se a questao esta errada (ou anulada) e 1
        % se esta certa de acordo com o gabarito certo
        % se a questao nao foi usada (ex: prova so com 10 questoes) vai
        % retornar sempre zero, porte tem valores 5 em todas as colunas)
        Resultados(i).Acertos = ~sum(abs(Alunos(i).Prova - Gabaritos(Alunos(i).Tipo).Prova), 2);

        % Multiplica as questoes certas pelos seus pesos
        Resultados(i).Notas = Resultados(i).Acertos .* Valores;

        %faz a soma de todas as questoes
        Resultados(i).NotaFinal = ValorProva .* sum(Resultados(i).Notas) ./ sum(Valores);
        
        %Atribui o codigo do aluno
        Resultados(i).Codigo = Alunos(i).Codigo;
    end
end
