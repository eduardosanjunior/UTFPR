%% IF69D - Processamento Digital de Imagens
%   Projeto OMR
%   Autores : Ana Flavia Yanaze e Eduardo V dos Santos Junior
%   Data: 16/05/2021

% Código do projeto de OMR. O sistema tem como entrada um diretório onde
% todas as provas e gabaritos estao contidos, o valor de cada questao e o
% valor da prova. O resultado mostra o desempenho individual de cada aluno,
% o valor final das notas e as estatisticas da prova. 

%mantra
clc; 
clear all; 
close all;

debug = 1;

%Cabeçalho tela inicial
disp('Sistema de OMR (Optical Mark Recognition');
disp('Desenvolvido por: Ana Yanaze e Eduardo Junior');
disp(date);
disp('');

%recebe valor da prova
ValorProva = input('Digite o valor da prova: ');

% recebe o caminho onde pode ser adquiridos as imagens
disp('Selecione o diretório com a imagens das provas (incluindo gabarito)');
CaminhoImagens = uigetdir('', 'Selecione o diretório das provas');
CaminhoImagens = [CaminhoImagens '\'];
ImagensOriginais = CarregaImagens(CaminhoImagens);


%testa se o vetor resultado contém imagens
if (isempty(ImagensOriginais))
    error('A pasta está vazia!');
end

% tratamento das imagens
ImagensTratadas = TrataImagens(ImagensOriginais);
c = ImagensTratadas(1).Image;
figure, imshow(c);

%limpa as variaveis
clearvars ImagensOriginais;

% identificar questoes de alunos e gabaritos
[Alunos, Gabaritos, Valores] = AplicaOMR(ImagensTratadas);

%limpa as variaveis
clearvars ImagensOriginais; 

% se nao tem uma folha de pesos, considera tudo 1
if (exist('Valores', 'var') == 0)
    Valores = ones(50,1);
end

% atribuir pontuação
grades = CalculaPontuacao(Alunos, Gabaritos, Valores, ValorProva);


% calcular nota de cada aluno
disp('Resultados da prova')
for i = 1:length(grades)
   disp(['Aluno ' grades(i).Codigo ' NOTA: ' num2str(grades(i).NotaFinal)]);
end


c = ImagensTratadas(1).Image;
figure, imshow(c);

b = ImagensTratadas(2).Image;
figure, imshow(b);