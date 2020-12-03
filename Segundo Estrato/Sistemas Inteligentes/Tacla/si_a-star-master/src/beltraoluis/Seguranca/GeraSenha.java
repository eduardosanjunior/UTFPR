package beltraoluis.Seguranca;

import java.util.Random;

/**
 * Escreva a descrição da classe GeraSenha aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class GeraSenha
{
    private Random rand;
    private int tam;
    private StringBuffer prototipo;
    private StringBuffer senha;
    private final char[] alf_min = {'a','b','c','d','e','f','g','h','i','j','k','l',
                                    'm','n','o','p','q','r','s','t','u','v','w','x',
                                    'y','z'};
    private final char[] alf_mai = {'A','B','C','D','E','F','G','H','I','J','K','L',
                                    'M','N','O','P','Q','R','S','T','U','V','W','X',
                                    'Y','Z'};
    private final char[] num = {'0','1','2','3','4','5','6','7','8','9'};
    private final char[] esp = {'!','@','#','$','%','*','(',')','_','-','+','~','^',
                                '[',']','{','}'};
  

    /**
     * COnstrutor para objetos da classe GeraSenha
     */
    public GeraSenha(String prototipo)
    {
        rand = new Random(System.currentTimeMillis());
        this.prototipo = new StringBuffer(prototipo);
        this.senha = new StringBuffer(prototipo);
        this.tam = prototipo.length();
        this.gerar_prototipo();
    }
    
    public void gerar_prototipo()
    {
        for(int i = 0; i < this.tam; i++)
        {
            switch(prototipo.charAt(i))
            {
                case '#':
                {
                    senha.setCharAt(i, num[rand.nextInt(10)]);
                    break;
                }
                case '@':
                {
                    senha.setCharAt(i, alf_min[rand.nextInt(26)]);
                    break;
                }
                case '$':
                {
                    senha.setCharAt(i, alf_mai[rand.nextInt(26)]);
                    break;
                }
                case '%':
                {
                    senha.setCharAt(i, esp[rand.nextInt(17)]);
                    break;
                }
                default:
                {
                    break;
                }
            }
        }
    }
    
    public String senha()
    {
        return senha.toString();
    }
}
