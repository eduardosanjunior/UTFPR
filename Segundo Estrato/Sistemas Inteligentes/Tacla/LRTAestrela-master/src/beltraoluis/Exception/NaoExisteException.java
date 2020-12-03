package beltraoluis.Exception;


/**
 * Escreva a descrição da classe NaoExisteException aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class NaoExisteException extends Exception{

	private static final long serialVersionUID = 1L;
	
    public NaoExisteException(String message){
        super(message);
    }
}