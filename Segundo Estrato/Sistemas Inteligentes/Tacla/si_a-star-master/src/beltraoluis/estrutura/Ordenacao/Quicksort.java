package beltraoluis.Estrutura.Ordenacao;


/**
 * Escreva a descrição da classe Quicksort aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Quicksort
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private int cont = 0;
    private int pivo = 0; /// posicao no vetor vet
    private int vpivo = 0;
    private int flag = 0;
    private int[] vetEsq;
    private int tamVetEsq = 0;
    private int[] vetDir;
    private int tamVetDir = 0;
    /**
     * COnstrutor para objetos da classe Quicksort
     */
    public Quicksort()
    {
    }
    
    private void ordenar(int vet[], int ini, int fim){
        cont = 0;
        pivo = 0; /// posicao no vetor vet
        vpivo = 0;
        flag = 0;
        tamVetEsq = 0;
        tamVetDir = 0;

        if(ini!=fim){
            /// alocar subvetores
            vetEsq = new int[fim-ini];
            vetDir = new int[fim-ini];
            /// seleciona pivo como sendo o elemento central
            pivo = (int) Math.round((fim - ini)/2)+ini;
            /// preencher subvetores em relacao ao pivo
            /// esse procedimento eh a base para a ordenacao
            vpivo = vet[pivo];
            for(cont=ini;cont<=fim;cont++){
                if(cont==pivo) cont++;
                if(vet[cont]<=vet[pivo]){
                    vetEsq[tamVetEsq++]=vet[cont];
                }else{
                    vetDir[tamVetDir++]=vet[cont];
                }
            }
            /// modificar o vetor original
            for(cont=ini;cont<=fim;cont++){
                if(tamVetEsq>0){
                    vet[cont]=vetEsq[--tamVetEsq];
                }else if(tamVetEsq==0&&flag==0){
                    pivo = cont;
                    vet[cont] = vpivo;
                    flag = 1;
                }else if(tamVetDir>0){
                    vet[cont]=vetDir[--tamVetDir];
                }
            }
            //ordenar os subvetores
            if(pivo-1>=ini){
               ordenar(vet,ini,pivo-1);
            }
            if(fim>=pivo+1){
                ordenar(vet,pivo+1,fim);
            }
        }
    }
    
    public int[] ordenar(int[] vet, int tam){
        ordenar(vet,0,tam-1);
        return vet;
    }
    
    public static int[] vet(){
        int[] vet = {5, 4, 3, 2, 1,0};
        return vet;
    }
}
