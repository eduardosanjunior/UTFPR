
import java.util.*;

/**
 * Classe para centralizar a manipulacao de Objetos Interesse
 * interesses = List dos interesses criados no servidor
 */
public class InteresseDAO {
    private ArrayList<Interesse> interesses = new ArrayList<Interesse>();

    /**
     * Funcao para atualizar um Interesse
     * inte = Objeto Interesse com as informacoes alteradas
     * retorna True caso consiga alterar o objeto, do contrario retorna false
     */
    public boolean update(Interesse inte){
        // Varre a lista de Interesses
        for (Interesse interesse : this.interesses) {
            // Se os objetos tem o mesmo ID eles sao iguais
            if(interesse.getId() == inte.getId()){
                // Atualiza o objeto antigo com as novas informacoes
                interesse = inte;
                return true;
            }
        }
        return false;
    }

    /**
     * Funcao para apagar um Pacote da lista de pacotes
     * inte = Interesse a ser apagada
     * retorna true caso consiga apagar, do contrario retorna false
     * OBS: o metodo .remove() faz uso do metodo .equals() do Pacote
     *      para comparar os objetos
     */
    public boolean delete(Interesse inte){
        return this.interesses.remove(inte);
    }

    /**
     * Funcao para inserir um novo Interesse na lista de pacotes
     * inte = Interesse a ser inserido
     */
    public boolean insert(Interesse inte){
        this.interesses.add(inte);
        return false;
    }

    /**
     * Funcao para retornar a lista atual de Interesses
     */
    public ArrayList<Interesse> select(){
        return this.interesses;
    }
}
