#include <iostream>
#include <vector>
#include <string.h>
#include <time.h>

using namespace std;

class Data{
private:
    int dia;
    int mes;
    int ano;
public:
//CONSTRUTOR
    Data()
    {
        struct tm *atual;
        time_t segundos;

        time(&segundos);
        atual = localtime(&segundos);

        dia= atual->tm_mday;
        mes= atual->tm_mon+1;
        ano= atual->tm_year+1900;
    }
    Data(int d, int m)
    {
        dia=d;
        mes=m;
    }
    Data(int d, int m, int a)
    {
        dia=d;
        mes=m;
        ano=a;
    }

//DESTRUTOR
    ~Data() {}
//GETTERS E SETTERS
    void setDia(int d){dia=d;}
    const int getDia(){return dia;}
    void setMes(int m){mes=m;}
    const int getMes(){return mes;}
    void setAno(int a){ano=a;}
    const int getAno(){return ano;}
//FUNÇÕES
    virtual void mostraData(){
        cout << dia <<"/" <<mes <<"/" << ano << endl;
    }
};
