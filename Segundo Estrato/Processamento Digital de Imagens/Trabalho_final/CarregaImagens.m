function [ ImagensOriginais ] = CarregaImagens( CaminhoImagens )
%Essa função lê as imagens de uma pasta e retorna dentro de um vetor
% no formato (x,y,i)

    %Definindo vetor
    ImagensOriginais = struct('Image', {});

     % verifica se o diretório foi selecionado está correto
    if (isfolder(CaminhoImagens) ~= 1)
        error('O diretório selecionado não é válido!');
    end

    % le informaçoes da pasta selecionada e busca por arquivos
    PastaInfo = dir(CaminhoImagens);
    %Contador para as imagens
    contImagens = 0;


    % le as imagens da pasta
    disp(['O diretório selecionado foi: ' CaminhoImagens]);
    %para cada arquivo na pasta
    for i = 1:length(PastaInfo)
        % se é um arquivo
        if (PastaInfo(i).isdir ~= 1) 
            [~, ~, ext] = fileparts([CaminhoImagens PastaInfo(i).name]);
            % se a extensao é de imagem
            if (strcmp(ext,'.jpg') || strcmp(ext,'.jpeg') || strcmp(ext,'.png') || strcmp(ext,'.bmp')) 
                contImagens = contImagens + 1;
                % ja salva a imagem convertida para escala de cinza
                ImagensOriginais(contImagens).Image = rgb2gray(im2double(imread([CaminhoImagens PastaInfo(i).name])));
                %Mostra o nome das imagens encontradas
                disp(['Imagem encontrada: ' PastaInfo(i).name]);
            end 
        end
    end
    %Mostra a quantidade de imagens
    disp([num2str(contImagens) ' Imagens Carregadas']);

end