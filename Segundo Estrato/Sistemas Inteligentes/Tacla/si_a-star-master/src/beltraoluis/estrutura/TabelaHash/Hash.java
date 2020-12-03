package beltraoluis.Estrutura.TabelaHash;

import beltraoluis.Estrutura.ListaEncadeada.Lista;
import java.util.ArrayList;

/**
 * TODO: implementar.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Hash<T extends Comparable<T>>
{
    protected int tam;
    protected ArrayList<Lista<T>> tabela;

    public Hash(int tam)
    {
        this.tam = 0;
        this.tabela = new ArrayList<Lista<T>>(tam);
    }
}
