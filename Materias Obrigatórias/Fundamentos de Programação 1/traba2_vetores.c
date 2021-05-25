/* Segundo trabalho de Fundamentos de Programação I
*  Prof. Bogdan Tomoyuki Nassu
*  Aluna: Ana Carolina dos Santos Teixeira
*  Nro: 1609513
*  Data: 27/07/2014
*/

#include "trabalho2.h"

void mudaGanho(double* dados, unsigned long n_amostras, double ganho){
    unsigned long i;
    for(i=0;i<n_amostras;i++)
        dados[i]*=ganho;
}
void misturaDados (double* in1, double* in2, double* out, unsigned long n_amostras){
    unsigned long i;
    for(i=0;i<n_amostras;i++)
        out[i]=in1[i]+in2[i];
}
int hardClipping (double* dados, unsigned long n_amostras, double limite){
    int amostr_alteradas=0;
    unsigned long i;
    for(i=0;i<n_amostras;i++){
        if(dados[i]>0){
            if(dados[i]>limite){
                dados[i]=limite;
                amostr_alteradas++;
            }
        }else{
            if(dados[i]<(limite*-1)){
                dados[i]=(limite*-1);
                amostr_alteradas++;
            }
        }
    }
    return amostr_alteradas;
}
void inverteSinal (double* dados, unsigned long n_amostras){
    double aux_dados;
    unsigned long i;
    for(i=0;i<(n_amostras/2);i++){
        aux_dados = dados[i];
        dados[i] = dados[(n_amostras-1)-i];
        dados[(n_amostras-1)-i] = aux_dados;
    }
}
void tremoloDenteDeSerra (double* dados, unsigned long n_amostras, unsigned int tam_ciclo){
    double ganho_atual = 0, acr_ganho = (double)1/(tam_ciclo-1);
    unsigned long i;
    for(i=0;i<n_amostras;i++){
        if(ganho_atual>1)
            ganho_atual=0;
        dados[i]*=ganho_atual;
        ganho_atual+=acr_ganho;
    }
}
void geraOndaDenteDeSerra (double* dados, unsigned long n_amostras, unsigned long taxa, double freq, int ascendente){
    double periodo = taxa/freq, valor_atual=2/periodo;
    unsigned long i;
    for(i=0;i<n_amostras;i++){
        dados[i]=valor_atual+dados[i-1];
        if (dados[i]>1)
            dados[i]=1-dados[i];
    }
}
void geraSenoide (double* dados, unsigned long n_amostras, unsigned long taxa, double freq, double fase){

}
