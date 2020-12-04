#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

class Funcionario
{
protected:
    string nome_funcinario;
    string identificacao;
    float salario;
    int diasTrabalhados;
public:
    Funcionario() {}
    Funcionario(string nF, string ident, float s, int d)
    {
        diasTrabalhados=d;
        nome_funcinario=nF;
        identificacao=ident;
        salario = s;
    }
    ~Funcionario() {}
    const string getNomeFuncionario(){return nome_funcinario;}
    const string getIdentificacao(){return identificacao;}
    const float getSalario(){return salario;}
    const int getDiasTrabalhados() {return diasTrabalhados;}
    virtual float modifica_salario(float s, float aS){
        s=s*aS;
        return s;
    }
    virtual float rescisao(){return salario * (diasTrabalhados/30);}
};

class TecnicoPortuario : public Funcionario
{
    float adicional_salario;
    string funcao;
public:
    TecnicoPortuario() {}
    TecnicoPortuario(float aS, string f, string n, string i, int s, int d) : Funcionario(n,i,modifica_salario(s,aS), d)
    {
        adicional_salario=aS;
        funcao=f;
    }
    ~TecnicoPortuario() {}
//GETTERS E SETTER
    float getAdicionalSalario(){return adicional_salario;}
    string getFuncao(){return funcao;}
    virtual float rescisao(){return adicional_salario*getSalario()*(getDiasTrabalhados()/30);}
//FUNÇÕES
};

class Manobrista : public Funcionario
{
    float adicional_salario;
    string funcao;
public:
//CONSTRUTOR
    Manobrista() {}
    Manobrista(float aS, string f, string n, string i, int s, int d) : Funcionario(n, i, modifica_salario(s,aS), d)
    {
        adicional_salario=aS;
        funcao=f;
    }
//DESTRUTOR
    ~Manobrista() {}
//GETTERS E SETTER
    float getAdicionalSalario(){return adicional_salario;}
    string getFuncao(){return funcao;}
    virtual float rescisao(){return adicional_salario*getSalario()*(getDiasTrabalhados()/30);}
};

class Diretor: public Funcionario
{
    float adicional_salario;
    string funcao;
public:
//OONSTRUTOR
    Diretor() {}
    Diretor(float aS, string f, string n, string i, int s, int d) : Funcionario(n, i, modifica_salario(s, aS), d)
    {
        adicional_salario=aS;
        funcao=f;
    }
//DESTRUTOR
    ~Diretor() {}
//GET E SET
    float getAdicionalSalario(){return adicional_salario;}
    string getFuncao(){return funcao;}
    virtual float rescisao(){return adicional_salario*getSalario()*(getDiasTrabalhados()/30);}
};

class Operador : public Funcionario
{
    float adicional_salario;
    string funcao;
public:
//CONSTRUTOR
    Operador(float aS, string f, string n, string i, int s, int d) : Funcionario(n, i, modifica_salario(s, aS), d)
    {
        adicional_salario=aS;
        funcao=f;
    }
//DESTRUTOR
    ~Operador() {}
//GETTERS E SETTERS
    float getAdicionalSalario(){return adicional_salario;}
    string getFuncao(){return funcao;}
    virtual float rescisao(){return adicional_salario*getSalario()*(getDiasTrabalhados()/30);}
};
