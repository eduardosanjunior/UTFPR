function [ ImagensTratadas ] = TrataImagens(ImagensOriginais)
%TrataImagens realiza um processamento primários nas imagens
%para prepará-las para o algoritmo principal.
%Retorna como resultado as imagens tratadas e parâmetros de saída


%Cria a estrutura de retorno
    ImagensTratadas = struct('Image', {});

    disp('Iniciando o tratamento das imagens');

    % Para cada imagem 
    for i=1:length(ImagensOriginais)
        
        % Aplica filtro na imagem
        ImagensTratadas(i).Image = imfilter(ImagensOriginais(i).Image,fspecial('gaussian'));
        
        % rotaciona a imagem se ela estiver em paisagem
        if (size(ImagensTratadas(i).Image, 2) > size(ImagensTratadas(i).Image, 1))
             ImagensTratadas(i).Image = imrotate( ImagensTratadas(i).Image, 270);
        end

        % reduz o tamanho para economizar memoria
        ImagensTratadas(i).Scale = 799/size(ImagensTratadas(i).Image, 2);
        ImagensTratadas(i).Image = imresize(ImagensTratadas(i).Image, ImagensTratadas(i).Scale);
        
        
        % iluminação
        % usa adptative threashold para transformar em preto e branco
        ImagensTratadas(i).Image = blockproc(ImagensTratadas(i).Image,[10 10],@adapt_thresh);
        
        % fazer um crescimento na imagem para cobrir os pontos das alternativas
        % faz uma erosao para excluir pequenos ruidos e uma dilatacao para
        % reforaçar as bolinhas
        ImagensTratadas(i).Image = imdilate(imerode(~ImagensTratadas(i).Image, strel('square', 6)), strel('square', 6));


        % faz o labeling
        % rotula os componentes conectados
        ImagemRotulo = bwlabel(ImagensTratadas(i).Image);
        % retorna os componentes conectados encontrados na imagem.
        ImagemInfo = bwconncomp(ImagensTratadas(i).Image);
        % mede um conjunto de propriedades para cada componente conectado 
        ImagemData = regionprops(ImagemInfo, 'basic');
                
        % encontrar os indices das regioes com os marcadores dos cantos
        % Monta a matriz de zeros
        Centroide = zeros(length(ImagemData),2);

        % Para cada elemento
        for ii = 1:length(ImagemData)
            %arredonda as centroides 
            Centroide(ii,:) = round(ImagemData(ii).Centroid);
        end
        
        % O indice sera o da menor distancia entre os cantos e os centroides de cada pixel
        % IMSE -> Indice Marcador Superior Esquerda
        % IMSD -> Indice Marcador Superior Direita
        % IMIE -> Indice Marcador Inferior Esquerda
        % IMID -> Indice Marcador Inferior Direita
        [dummy, IMSE] = min(sqrt((abs(1-Centroide (:,1)) + abs(0 - Centroide(:,2))).^2));
        [dummy, IMSD] = min(sqrt((abs(size(ImagemRotulo, 2) - Centroide(:,1)) + abs(0 - Centroide(:,2))).^2));
        [dummy, IMIE] = min(sqrt((abs(1-Centroide(:,1)) + abs(size(ImagemRotulo, 1) - Centroide(:,2))).^2));
        [dummy, IMID] = min(sqrt((abs(size(ImagemRotulo, 2)-Centroide(:,1)) + abs(size(ImagemRotulo, 1) - Centroide(:,2))).^2));
        
        %Cria a estrutura resposta
        ImagensTratadas(i).Image = ImagemRotulo;
        ImagensTratadas(i).PixelList = ImagemData;
        ImagensTratadas(i).IMSE = IMSE;
        ImagensTratadas(i).IMSD = IMSD;
        ImagensTratadas(i).IMIE = IMIE;
        ImagensTratadas(i).IMID = IMID;
        
        % Define os pontos para a transformação
        PontosFixos = [76 82; 782 82; 76 1193; 782 1193];
        PontosMoveis = [round(ImagemData(IMSE).Centroid); 
                        round(ImagemData(IMSD).Centroid);
                        round(ImagemData(IMIE).Centroid);
                        round(ImagemData(IMID).Centroid)];

        %Aplica a transformaçao nos pontos para endireitar as imagens
        tform = fitgeotrans(PontosMoveis,PontosFixos,'projective');
        
        % faz novamente as operaçoes morfologicas para filtrar os efeitos da transformada
        ImagensTratadas(i).Image = imdilate(imerode(imwarp(ImagensTratadas(i).Image, tform), strel('square', 2)), strel('square', 6));
        
        % recalcula pontos
        ImagemRotulo = bwlabel(ImagensTratadas(i).Image);
        ImagemInfo = bwconncomp(ImagensTratadas(i).Image);
        ImagemData = regionprops(ImagemInfo, 'basic');
                
        % encontrar os indices das regioes com os marcadores dos cantos
        Centroide = zeros(length(ImagemData),2);
        for ii = 1:length(ImagemData)
            Centroide(ii,:) = round(ImagemData(ii).Centroid);
        end
        
        % o indice sera o da menor distancia entre os cantos e os
        % centroides de cada pixel
        [dummy, IMSE] = min(sqrt((abs(1-Centroide(:,1)) + abs(0 - Centroide(:,2))).^2));
        [dummy, IMSD] = min(sqrt((abs(size(ImagemRotulo, 2) - Centroide(:,1)) + abs(0 - Centroide(:,2))).^2));
        [dummy, IMIE] = min(sqrt((abs(1-Centroide(:,1)) + abs(size(ImagemRotulo, 1) - Centroide(:,2))).^2));
        [dummy, IMID] = min(sqrt((abs(size(ImagemRotulo, 2)-Centroide(:,1)) + abs(size(ImagemRotulo, 1) - Centroide(:,2))).^2));
        

        %Monta o resultado
        ImagensTratadas(i).Image = ImagemRotulo;
        ImagensTratadas(i).PixelList = ImagemData;
        ImagensTratadas(i).IMSE = IMSE;
        ImagensTratadas(i).IMSD = IMSD;
        ImagensTratadas(i).IMIE = IMIE;
        ImagensTratadas(i).IMID = IMID;

    
    end
    disp('Terminado o processo de tratamento');
end