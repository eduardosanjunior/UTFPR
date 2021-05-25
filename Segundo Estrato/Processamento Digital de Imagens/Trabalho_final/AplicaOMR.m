function [ Alunos, Gabaritos, Valores ] = AplicaOMR(ImagensTratadas)
% AplicaOMR processa as imagens usando OMR
%Recebe as imagens tratadas e os cantos marcados e identifica o os valores marcados




    % Criando as estruturas de retorno
    Alunos = struct('Prova', {}, 'Tipo', {}, 'Codigo', {});
    Gabaritos = struct('Prova', {}, 'Valores', {});     
    Valores = zeros(50, 1);
    Valores(:,1) = 1;

    %Contadores
    c_alunos = 0;
    c_gabaritos = 0;

    
    disp('Aplicando algoritmo de OMR');

    % para cada imagem tratada
    for i=1:length(ImagensTratadas)
        
        % Guarda as centroides das pontas
        centroideSE = round(ImagensTratadas(i).PixelList(ImagensTratadas(i).IMSE).Centroid);
        centroideSD = round(ImagensTratadas(i).PixelList(ImagensTratadas(i).IMSD).Centroid);
        centroideIE = round(ImagensTratadas(i).PixelList(ImagensTratadas(i).IMIE).Centroid);
        centroideID = round(ImagensTratadas(i).PixelList(ImagensTratadas(i).IMID).Centroid);
        
        % acha as linhas
        linhas =        zeros(1,37);
        linhas(1) =     centroideSE(1,2);
        linhas(2:11) =  (centroideSE(1,2) + 157):25:(centroideSE(1,2) + 382);
        linhas(12:36) = (centroideSE(1,2) + 472):25:(centroideSE(1,2) + 1072);
        linhas(37) =    centroideIE(1,2);
             
        % acha as colunas
        colunas =        zeros(1,15);
        colunas(1) =     centroideIE(1,1);
        colunas(2:9) =   (centroideIE(1,1) + 100):25:(centroideIE(1,1) + 280);
        colunas(10:14) = (centroideIE(1,1) + 520):25:(centroideIE(1,1) + 620);
        colunas(15) =    centroideID(1, 1);
        
        % escaneia cada linha para encontrar os valores
        % linha 2 a 11 -> cod aluno cols -> 3 a 8
        % linha 2 tipo prova cols -> 11 a 14
        % linhas 12 a 26 -> questoes cols -> 2 a 6 e 10 a 14
        
        % descobre tipo prova
        tipo = AnalisaLinha(ImagensTratadas(i).Image((linhas(2)-5):(linhas(2)+5), :), ... 
        round([colunas(11) colunas(12) colunas(13) colunas(14)]), 0);
        
        Tipo = sum([1 2 3 4] .* tipo);
        
        % descobre codigo aluno
        codigo = zeros(10, 7);
        for l = 2:11
            
            codigo(l-1, :) = AnalisaLinha(ImagensTratadas(i).Image(round((linhas(l)-5):(linhas(l)+5)), :), ... 
            round([colunas(2) colunas(3) colunas(4) colunas(5) colunas(6) colunas(7) colunas(8)]), 0);
            
        end
        Codigo = sum(repmat([1;2;3;4;5;6;7;8;9;0], 1, 7) .* codigo);
        
        prova = zeros(50, 5);
        
        for l = 12:36
            
            prova(l-11, :) = AnalisaLinha(ImagensTratadas(i).Image(round((linhas(l)-5):(linhas(l)+5)), :), ... 
            round([colunas(2) colunas(3) colunas(4) colunas(5) colunas(6)]), 0);
            
        end
        
        for l = 12:36
            
            prova(l+14, :) = AnalisaLinha(ImagensTratadas(i).Image(round((linhas(l)-5):(linhas(l)+5)), :), ... 
            round([colunas(10) colunas(11) colunas(12) colunas(13) colunas(14)]), 0);
        end
        
    
        % se o cod de aluno é zero, pode ser a folha com pesos ou um gabarito
        if (sum(Codigo) == 0)
            % se o tipo for 0 (nenhuma bolinha) é a folha de pesos
            if (Tipo == 0)
                Valores = sum(repmat([1 2 3 4 5], 50, 1) .* prova, 2); 
            else
                % se é um gabarito, identifica as uestoes nao usadas,
                % atribuindo 5 para ela (para funcionar depois com o
                % calculo da nota)
                prova(sum(prova, 2) == 0, :) = 5;
            	Gabaritos(Tipo).Prova = prova;
            end
        else
             % se nao for 0, considera como aluno
             c_alunos =  c_alunos + 1;
             Alunos(c_alunos).Prova = prova;
             Alunos(c_alunos).Tipo = Tipo;
             str = num2str(Codigo);
             str(str==' ') = '';
             Alunos(c_alunos).Codigo = str;
        end
        
    end
    disp('algoritmo de OMR apicado');
end