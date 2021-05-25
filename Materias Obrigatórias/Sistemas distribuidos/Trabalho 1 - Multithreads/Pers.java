/**************************************************
	Trabalho 1 - Sistemas Distribuidos       adnp
	Eduardo Junior                RA: 1458884
***************************************************/

import java.net.* ;
import java.util.* ;
import java.security.*;
import java.security.spec.*;
import javax.crypto.Cipher;


/* **************************** *
*  THREAD PRINCIPAL DO PROGRAMA *
*  **************************** */
public class Pers {
	public static void main(String argv[]) throws Exception
	{
		final Processo processo = new Processo();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ola!");
		System.out.println("Vamos comecar:");
		System.out.println("Entrando no multicast...");

		// Abre conexao com o multicast
		MulticastSocket multicast = new MulticastSocket(processo.multicastPORT);
		processo.group  = InetAddress.getByName(processo.multicastAddres);
		multicast.joinGroup(processo.group);

		// Comeca a escutar multicast
		Thread listenerThread = new Thread(){
			@Override
			public void run(){
				processo.listenMulticast(multicast);
			}
		};
		listenerThread.start();

		// Manda mensagem de entrada no multicast
		processo.enviaMARCO(multicast);
		System.out.println("Entramos!");

		// Esperando para comecar algoritmo
		System.out.println("Aguardando numero minimo de usuarios: " + processo.minUsuarios);
		while( processo.membros.size() < processo.minUsuarios ){
			System.out.println("Aguardando.... Numero de usuarios: " + processo.membros.size() + " de " + processo.minUsuarios);
			Thread.sleep(2000);
		}
		System.out.println("Temos o minimo de usuarios!");

		// Comecando algoritmo
		boolean running = true;
		// Seleciona as Opções de noticia
		while(running) {
			Thread.sleep(2000);
			System.out.println("O que deseja fazer?");
			System.out.println("1 - Enviar noticia");
			System.out.println("2 - Avaliar noticia");
			System.out.println("0 - Sair");
			int opt = Integer.parseInt(scanner.nextLine());
			switch(opt) {
				case 1:
					processo.enviaNoticia(multicast);
					break;
				case 2:
					processo.avaliaNoticia(multicast);
					break;
				case 0:
					running = false;
					processo.enviaTchau(multicast);
					break;
				default:
					System.out.println("Opcao invalida!");
					break;
			}
		}
		// Fim
		System.out.println("Encerrando processo...");
		processo.running = false;
		multicast.close();
		scanner.close();
		System.out.println("Tchau!");
	}
}

/*
* Classe que representa o Processo
* multicastAddres: EndereÃ§o para o multicast
* multicastPORT: Porta onde esta sendo escutada o multicast
* minUsuarios: Numero minimo de usuarios no multicast para executar o algoritmo
* id: Id gerado aleatoriamente
* membros: Lista de de ids dos membros do multicast
* chaves: Map de [id => chavePublica] para os membros do multicast
* reputacoes: Map de [id => reputacao] para os membros do multicast
* chavePrivada: Chave privada do processo
* chavePublica: Chave publica do processo
*/

final class Processo extends Constantes {
	public String multicastAddres = "230.0.0.0";
	public int multicastPORT = 6789;
	public int minUsuarios = 3;
	public InetAddress group;
	public String id = Integer.toString(Math.abs(new Random().nextInt()));
	final static String CRLF = ",";
	public ArrayList<String> membros = new ArrayList<String>();
	public Map<String,String> chaves = new HashMap<String,String>();
	public Map<String,Integer> reputacoes = new HashMap<String,Integer>();
	private PrivateKey chavePrivada;
	private PublicKey chavePublica;
	public boolean running = true;

	//Construção dos objetos
	public Processo() {
		try {
			// Gera as chaves publicas e privadas para assinatura
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
			keyGen.initialize(1024);
			final KeyPair key = keyGen.generateKeyPair();
		
			this.chavePublica = key.getPublic();
		
			this.chavePrivada = key.getPrivate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

		// Funcao para escutar o multicast
	// multicast: Socket com conexao para o multicast
	public void listenMulticast(MulticastSocket multicast){
		try{
			while(this.running) {
				// Aguardando conexoes
				byte[] buffer = new byte[10*1024];
		        DatagramPacket data = new DatagramPacket(buffer, buffer.length);
				multicast.receive(data);

				// Le a mensagem recebida
				String linha = new String(buffer, 0, data.getLength());
				String[] mensagem = linha.split(CRLF);

				// Verifica o tipo de mensagem enviada
				switch(mensagem[0]){
					// Novo membro no multicast
					case Metodos.MARCO:
						this.respondeMARCO(mensagem, multicast); 
						break;
					// Resposta para entrada no multicast
					case Metodos.POLLO:
						this.trataPOLLO(mensagem, multicast);
						break;
					// Nova noticia
					case Metodos.NOTICIA:
						this.trataNoticia(mensagem);
						break;
					// Avalia noticia
					case Metodos.AVALIACAO:
						this.trataAvaliacao(mensagem);
						break;
					// Aviso de saida do multicast
					case Metodos.TCHAU:
						this.trataTchau(mensagem);
						break;
					default:
						System.out.println("Metodo desconhecido");
						System.out.println(mensagem[0]);
						break;
				}
			}
		}catch(Exception e){
		}
	}

	//Metodo que monta o datagrama da noticia a ser enviada
	public void enviaNoticia(MulticastSocket multicast){
		try{

			//recebe a mensagem do usuário
			System.out.println("Escreva aqui sua noticia:");
			Scanner scanner = new Scanner(System.in);
			String noticia = scanner.nextLine();

			//Gera o ID da noticia 
			String idNoticia = this.id + "-" + Integer.toString(Math.abs(new Random().nextInt()));
			//monta mensagem
			String mensagem = Metodos.NOTICIA + CRLF; // Metodo 
			mensagem += idNoticia + CRLF; // ID da noticia para avaliacao
			mensagem += noticia + CRLF; // Noticia
			mensagem += this.id + CRLF; // Origem
			mensagem += this.sign(); // Assinatura

			// Envia mensagem para multicast
      		DatagramPacket data = new DatagramPacket(mensagem.getBytes(),
													mensagem.length(),
													this.group,
													this.multicastPORT);

			multicast.send(data);
		} catch(Exception e){
			// pass
		}
	}

	/**
	 * Função para tratar o recebimento de uma nova noticia
	 * Apenas imprime a noticia recebida
	 * mensagem: Array com o corpo dos pacotes trocados no multicast
	 */
	public void trataNoticia(String[] mensagem){
		try {
			// Trata mesagens vindas do mesmo processo.
			if (mensagem[3].equals(this.id)) {
				return;
			}

			// Verifica se a assinatura é do processo que enviou
			if (!this.verifySign(mensagem[3], mensagem[4])) {
				System.out.println("Alguem esta se passando pelo processo " + mensagem[3]);
				return;
			}

			// Exibe a noitica
			System.out.println("Nova noticia recebida do processo " + mensagem[3]);
			System.out.println("ID Noticia: " + mensagem[1]);
			System.out.println(mensagem[2]);

		} catch(Exception e) {
			// pass
		}
	}


// Metodo para avaliação das noticias recebidas.
public void avaliaNoticia(MulticastSocket multicast){
	try {

		//Captura a avaliação baseada na id da notícia
		Scanner scanner = new Scanner(System.in);
		System.out.println("Por favor insira o id da noticia que deseja avaliar");
		String idNoticia = scanner.nextLine();
		System.out.println("Como avalia esta noticia: 1 - Verdadeiro; 0 - Falso");
		int opt = Integer.parseInt(scanner.nextLine());


		//Monta mensagem para multicast
		String mensagem = Metodos.AVALIACAO + CRLF; // Metodo
		mensagem += this.id + CRLF; // Origem
		mensagem += idNoticia + CRLF; // Conteudo
		mensagem += opt + CRLF; // Conteudo
		mensagem += this.sign(); // Assinatura

		// Envia mensagem para multicast
		  DatagramPacket data = new DatagramPacket(mensagem.getBytes(),
												mensagem.length(),
												this.group,
												this.multicastPORT);
		  multicast.send(data);
	} catch(Exception e) { 
	}
}

public void trataAvaliacao(String[] mensagem){
                
	try {
		System.out.println("Nova avaliacao sobre noticia recebida");
		String[] idNoticiaTokens = mensagem[2].split("-");  
		int reputacaoAtual = this.reputacoes.get(idNoticiaTokens[0]);
		
		//verifica a autenticidade da mensagem
		if(!this.verifySign(mensagem[1], mensagem[4])){
			System.out.println("Alguem esta se passando pelo processo " + mensagem[2]);
			return;
		}
		// trata a mensagem de avaliação e atualiza a reputação
		if (mensagem[3].equals("1")) {
			System.out.println("A noticia do processo " + idNoticiaTokens[0] + " considerada verdadeira!");
			reputacaoAtual += 1;

		}else {
			System.out.println("A noticia do processo " + idNoticiaTokens[0] + " considerada falsa!");
			reputacaoAtual -= 1;
		}
		this.reputacoes.replace(idNoticiaTokens[0], reputacaoAtual);
		System.out.println("Nova reputacao do processo : " + reputacaoAtual);
	} 
	catch(Exception e) {
	}
}

	// Envia mensagem para entrar no multicast
	// multicast: Socket com conexao para o multicast
	public void enviaMARCO(MulticastSocket multicast){
		try{
			// Monta mensagem de entrada
			String mensagemDeOla = Metodos.MARCO + CRLF; // Metodo
			mensagemDeOla += this.id + CRLF; // Origem/Conteudo
			mensagemDeOla += Base64.getEncoder().encodeToString(this.chavePublica.getEncoded()); // Chave

			// Envia mensagem para multicast
      		DatagramPacket data = new DatagramPacket(mensagemDeOla.getBytes(),
													mensagemDeOla.length(),
													this.group,
													this.multicastPORT);
			//envia mensagem para multicast										
      		multicast.send(data);
		}
		catch(Exception e){
		}
}

	// Funcao para tratar uma mensagem de entrada no multicast
	// multicast: Socket com conexao para o multicast
	// mensagem: Mensagem recebidda
	private void respondeMARCO(String[] mensagem, MulticastSocket multicast){
		try{
			// Adiciona novo membro na lista de membros
			// e adiciona chave na lista de chaves
			this.membros.add(mensagem[1]);
			this.chaves.put(mensagem[1], mensagem[2]);
			this.reputacoes.put(mensagem[1], 10);

			// Monta resposta
			String resposta = Metodos.POLLO + CRLF; // Metodo
			resposta += mensagem[1] + CRLF; // Destinatario
			resposta += this.id + CRLF; // Conteudo
			resposta += this.id + CRLF; // Origem
			resposta += Base64.getEncoder().encodeToString(this.chavePublica.getEncoded()); // Chave publica

			// Envia resposta
			DatagramPacket data = new DatagramPacket(resposta.getBytes(),
														resposta.length(),
														this.group,
														this.multicastPORT);
        	multicast.send(data);
		}
		catch(Exception e){
		}
	}

	// Função para tratar uma resposta ao entrar no multicast
	// multicast: Socket com conexao para o multicast
	// mensagem: Mensagem recebidda
	private void trataPOLLO(String[] mensagem, MulticastSocket multicast){
		try{
			// Tratar mensagens vindas de mim
			if(mensagem[1].equals(this.id) && !mensagem[3].equals(this.id)){
				// Adiciona o membro na lista de membros
				// guarda a chave
				//atualiza as reputações
				this.membros.add(mensagem[2]);
				this.chaves.put(mensagem[2], mensagem[4]);
                this.reputacoes.put(mensagem[2], 10);
			}
		}
		catch(Exception e){
		}
	}

	// Envia mensagem para sair do multicast
	// multicast: Socket com conexao para o multicast
	public void enviaTchau(MulticastSocket multicast){
		try{
			// Monta mensagem de saida
			String mensagemDeTchau = Metodos.TCHAU + CRLF; // Metodo
			mensagemDeTchau += this.id + CRLF; // Origem/Conteudo

			// monta o dtagrama para enviar
      		DatagramPacket data = new DatagramPacket(mensagemDeTchau.getBytes(),
																				mensagemDeTchau.length(),
																			    this.group,
																			    this.multicastPORT);
			// Envia mensagem para multicast
			multicast.send(data);
		}
		catch(Exception e){
	
		}
	}


	// Funcao para tratar uma mensagem de saida do multicast
	private void trataTchau(String[] mensagem){
		try{
			// Remove o membro da lista
			this.membros.remove(mensagem[1]);
		}
		catch(Exception e){
		}
	}


	/**
	* Função para verificar uma assinatura em uma mensagem
	* id: Id do processo que enviou a mensagem
	* sign: Mensagem secreta assinada e criptografada
	* return: Boolean correspondendo caso tenha conseguido
	* verificar a assinatura
	*/
	private boolean verifySign(String id, String sign){
		try {
			System.out.println("Verificando assinatura...");

			KeyFactory factory = KeyFactory.getInstance("DSA");
			String chavePublica = this.chaves.get(id);

			// Caso nao encontre a chave de quem assinou
			if(chavePublica == null){
				return false;
			}

			// Descriptografa a chave publica do processo que assinou
			EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(chavePublica));
			PublicKey chave = (PublicKey) factory.generatePublic(publicKeySpec);

			// Começa a verificaÃ§Ã£o da assinatura
			Signature signature = Signature.getInstance("DSA"); 
			signature.initVerify(chave);
			signature.update(Codigos.Verificador.getBytes()); // Mensagem unica compartilhada entre os processos

			// Verifica se a mensagem enviada corresponde com a mensagem secreta
			if (signature.verify(Base64.getDecoder().decode(sign))) {
				System.out.println("Mensagem verdadeira, assinada por :" + id);
				return true;
			}
			 
			return false;
    } catch (Exception ex) {

    	ex.printStackTrace();
		return false;
    }
	}
	/**
	* Função para assinar uma mensagem secreta
	* return: String criptografada e assinada
	*/
	private String sign(){
		System.out.println("Assinando requisicao...");
		byte[] cipherText = null;
     
        try {
			// Inicia a assinatura com a chave privada do processo
			Signature signature = Signature.getInstance("DSA");
			signature.initSign(this.chavePrivada);

			// Insere a mensagem secreta a ser assinada
			// e assina
			signature.update(Codigos.Verificador.getBytes());
			cipherText = signature.sign();
	} 
	    catch (Exception e) {
    	    e.printStackTrace();
	}
    // Encoda em Base64 para evitar corrupção ao transformar
	// em string
    return Base64.getEncoder().encodeToString(cipherText);
	}
}


// Define as constantes 
class Constantes{
	// Define os tipos de mensagens
	public static class Metodos{
		public final static String MARCO = "MARCO";
		public final static String POLLO = "POLLO";
		public final static String NOTICIA = "NOTICIA";
		public final static String AVALIACAO = "AVALIACAO";
		public final static String TCHAU = "TCHAU";
	}

	//Verificador para o metodos de chave publica e privada.
	public static class Codigos{
		public final static String Verificador = "nouidffdauhafeafshdashsad";
	}
}
