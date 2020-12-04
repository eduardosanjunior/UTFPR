#include <iostream>
#include <vector>
#include <string.h>
#include "data.cpp"

using namespace std;

class Navio
{
    int id;
    string nome;
    Data dataEntrada;
    Data dataSaida;
public:
//CONSTRUTOR
    Navio() {}
    Navio(int i, string n, Data dE, Data dS)
    {
        id=i;
        nome=n;
        dataEntrada=dE;
        dataSaida=dS;
    }
//DESTRUTOR
    ~Navio() {}
//GETTERSS E SETTERS
    const int getId(){return id;}
    string getNome(){return nome;}
    Data getDataEntrada(){return dataEntrada;}
    Data getDataSaida(){return dataSaida;}

    void setId(int i){ id= i;}
    void setNome(string n){nome = n;}
    void setDataEntrada(Data de){ dataEntrada = de;}
    void setDataSaida(Data ds){ dataSaida = ds;}
//FUNÇÕES

};

class Cargueiro:public Navio
{
    string carga;
    int quantidade;

public:
//CONSTRUTOR
    Cargueiro(){}
    Cargueiro(int i, string n, string c, int q, Data dE, Data dS) : Navio(i, n, dE, dS)
    {
        carga=c;
        quantidade=q;
    }
//DESTRUTOR
    ~Cargueiro(){}
//GETS E SETS
    string getCarga(){return carga;}
    int getQuantidade(){return quantidade;}
    void setCarga(string c) { carga= c; }
    void setQuantidade(int q) {quantidade = q;}
};

class Cruzeiro:public Navio
{
    string localSaida;
    string localIda;
    int passageiros;
    string nomeCapitao;
public:
//CONSTRUTOR
    Cruzeiro(int i, string n,  string lS, string lI, int p, string nC, Data dE, Data dS)
        : Navio(i,n,dE,dS)
    {
        localSaida=lS;
        localIda=lI;
        passageiros=p;
        nomeCapitao=nC;
    }
//DESTRUTOR
    ~Cruzeiro() {}
//GETTERS E SETTERS
    string getLocalSaida(){return localSaida;}
    string getLocalIda(){return localIda;}
    int getPassageiros(){return passageiros;}
    string getNomeCapitao(){return nomeCapitao;}
};
