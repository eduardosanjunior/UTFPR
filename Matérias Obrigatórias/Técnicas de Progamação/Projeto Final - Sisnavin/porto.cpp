#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <list>
#include <stdio.h>
#include <stdlib.h>
#include <fstream>
#include <sstream>
#include "navio.cpp"
#include "funcionario.cpp"
#include <stdio.h>
#include <windows.h>
#include <time.h>

using namespace std;

class Escritorio
{
protected:
    std::list<TecnicoPortuario> tecnicos;
    std::list<Manobrista> manobristas;
    std::list<Diretor> diretor;
    std::list<Operador> operadores;
    float dinheiro;
public:
    Escritorio()
    {
        loadDataFromFileFuncionarios();
    }
    ~Escritorio() {}
//GETS E SETS
    int getDinheiro(){return dinheiro;}
//FUNÇÕES
//FUNÇOES FUNCIONARIOS
    void loadDataFromFileFuncionarios(){
         //arquivo onde está os dados do cargueiro
        ifstream file("funcionarios.txt");
        //variavel para pegar a linha
        string line;
        //atributos do construtor de cargueiro
        float i; string n; string c; string q; int s; int d;
        //variavel para salvar a string
        char char_array[100];
        TecnicoPortuario *novotec;
        Manobrista *novomano;
        Diretor *novodir;
        Operador *novoop;
        //enquanto o arquivo estiver aberto
        while (!file.eof() )
        {
            //pega a primeira linha do arquivo e salva em line
            getline (file,line);
            if(line=="")
                    break;
            //converte a string para um tipo float;
            i = atof(strcpy(char_array, line.c_str()));
            getline (file,line);
            n = line;
            getline (file,line);
            c = line;
            getline (file,line);
            q= line;
            getline (file,line);
            s = atof(strcpy(char_array, line.c_str()));
            getline (file,line);
            d = atoi(strcpy(char_array, line.c_str()));
            if(n== "Tecnico Portuario"){
                novotec= new TecnicoPortuario(i,n,c,q,s,d);
                adiciona_tecnico(*novotec);
            }else if(n== "Manobrista"){
                novomano= new Manobrista(i,n,c,q,s,d);
                adiciona_manobrista(*novomano);
            }else if(n=="Diretor"){
                novodir= new Diretor(i,n,c,q,s,d);
                adiciona_diretor(*novodir);
            }else{
                novoop= new Operador(i,n,c,q,s,d);
                adiciona_operador(*novoop);
            }
        }
    }
    //FUNÇÕES TECNICOS
    void listaTecnicos(){
        cout << endl << "Listando Tecnicos do Porto";
        for(std::list<TecnicoPortuario>::iterator i=tecnicos.begin(); i!=tecnicos.end(); i++)
        {
            cout << endl << "Nome: " << (*i).getNomeFuncionario() << " / Identificacao: " << (*i).getIdentificacao()<<
                 endl << "Funcao: " << (*i).getFuncao() << endl <<
                 "Salario: "<<(*i).modifica_salario((*i).getSalario(),(*i).getAdicionalSalario()) << endl;
        }
    }

    void adiciona_tecnico(TecnicoPortuario novoTecnico){tecnicos.push_back(novoTecnico);}
    //FUNÇOES MANOBRISTA
    void adiciona_manobrista(Manobrista novoManobrista){manobristas.push_back(novoManobrista);}

    void listaManobristas(){
        cout << endl <<"Listando Manobristas do Porto" << endl;
        for(std::list<Manobrista>::iterator i=manobristas.begin(); i!=manobristas.end(); i++)
        {
            cout << endl << "Nome: " << (*i).getNomeFuncionario() << " / Identificacao: " << (*i).getIdentificacao()<<
                 endl << "Funcao: " << (*i).getFuncao() << endl <<
                 "Salario: "<<(*i).modifica_salario((*i).getSalario(),(*i).getAdicionalSalario()) << endl;
        }
    }

    //FUNÇOES DIRETOR
    void listaDiretor(){
        cout << endl << "Listando Diretor do Porto";
        for(std::list<Diretor>::iterator i=diretor.begin(); i!=diretor.end(); i++)
        {
            cout << endl << "Nome: " << (*i).getNomeFuncionario() << " / Identificacao: " << (*i).getIdentificacao()<<
                 endl << "Funcao: " << (*i).getFuncao() << endl <<
                 "Salario: "<<(*i).modifica_salario((*i).getSalario(),(*i).getAdicionalSalario()) << endl;
        }
    }

    void adiciona_diretor(Diretor novoDiretor){diretor.push_back(novoDiretor);}

    //FUNÇOES OPERADOR
    void listaOperador(){
        cout << endl << "Listando Operador do Porto";
        for(std::list<Operador>::iterator i=operadores.begin(); i!=operadores.end(); i++)
        {
            cout << endl << "Nome: " << (*i).getNomeFuncionario() << " / Identificacao: " << (*i).getIdentificacao()<<
                 endl << "Funcao: " << (*i).getFuncao() << endl <<
                 "Salario: "<<(*i).modifica_salario((*i).getSalario(),(*i).getAdicionalSalario()) << endl;
        }
    }

    void adiciona_operador(Operador novoOperador){operadores.push_back(novoOperador);}

    void cadastraFuncionario(){
        string n, i;
        int f;

        cout <<"Digite a ID do Funcionario com 3 digitos: ";
        cin >> i;
        cout <<"Digite o Nome do Funcionario: ";
        cin >> n;
        cout << "Escolha a Funcao: " << endl <<"   1 para Operador \n   2 Para Manobrista \n   3 Para Tecnico Portuario \n   4 Para Diretor: ";
        cin >> f;

        if(f==1){
            if(operadores.size()<10){
                Operador novoOperador(1, "Operador",n, i, 1000,0);
                adiciona_operador(novoOperador);
                cout << "\nCadastro realizado com sucesso!" << endl;
            }
            else{
                cout << "\nNumero maximo de operadores atingido. Cadastro nao realizado" << endl;
            }
        }
        if(f==2)
        {
            if(manobristas.size()<8){
                Manobrista novoManobrista(1.2,  "Manobrista",n, i, 1200,0);
                adiciona_manobrista(novoManobrista);
                cout << "\nCadastro realizado com sucesso!" << endl;
            }
            else{
                cout << "\nNumero maximo de manobristas atingido. Cadastro nao realizado" << endl;
            }
        }
        if(f==3)
        {
            if(tecnicos.size()<6){
                TecnicoPortuario novoTecnico(1.4, "Tecnico Portuario",n, i, 1400,0);
                adiciona_tecnico(novoTecnico);
                cout << "\nCadastro realizado com sucesso!" << endl;
            }
            else{
                cout << "\nNumero maximo de tecnicos portuarios atingido. Cadastro nao realizado" << endl;
            }
        }
        if(f==4)
        {
            if(diretor.size()<4){
                Diretor novoDiretor(4.5, "Diretor",n,i, 1200,0);
                adiciona_diretor(novoDiretor);
                cout << "\nCadastro realizado com sucesso!" << endl;
            }
            else{
                cout << "\nNumero maximo de diretores atingido. Cadastro nao realizado" << endl;
            }
        }
    }

    virtual void excluindo_tecnico(string id){
        int flag;
        flag=0;
        for(std::list<TecnicoPortuario>::iterator i=tecnicos.begin(); i!=tecnicos.end(); i++)
        {
            if((*i).getIdentificacao() == id || (*i).getNomeFuncionario() == id)
            {
                cout << "A rescisao de " << (*i).getNomeFuncionario();
                cout << " sera de R$ " << (*i).rescisao();
                i = tecnicos.erase(i);
                cout << "\nTecnico removido com sucesso!";
                flag=1;
            }
        }
        if(flag!=1)
            cout << "Tecnico nao encontrado"<< endl;
    }

    virtual void excluindo_operador(string id){
        int flag;
        flag=0;
        for(std::list<Operador>::iterator i=operadores.begin(); i!=operadores.end(); i++)
        {
            if((*i).getIdentificacao() == id || (*i).getNomeFuncionario() == id)
            {
                cout << "A rescisao de " << (*i).getNomeFuncionario();
                cout << " sera de R$ " << (*i).rescisao();
                i=operadores.erase(i);
                cout << "\nOperador removido com sucesso!";
                flag=1;
            }
        }
        if(flag !=1)
            {
                cout << "Operador nao encontrado"<< endl;
            }
    }

    virtual void excluindo_manobrista(string id){
        int flag;
        flag=0;
        for(std::list<Manobrista>::iterator i=manobristas.begin(); i!=manobristas.end(); i++)
        {
            if((*i).getIdentificacao() == id || (*i).getNomeFuncionario() == id)
            {
                cout << "A rescisao de " << (*i).getNomeFuncionario();
                cout << " sera de R$ " << (*i).rescisao();
                i=manobristas.erase(i);
                cout << "\nManobrista removido com sucesso!";
                flag=1;
            }
        }
        if(flag ==0)
            {
                cout << "Manobrista nao encontrado"<< endl;
            }
    }

    void procura_Tecnico(string id){
        int flag;
        flag=0;
        for(std::list<TecnicoPortuario>::iterator i=tecnicos.begin(); i!=tecnicos.end(); i++)
        {
            if((*i).getIdentificacao() == id || (*i).getNomeFuncionario() == id)
            {
                cout << endl << "Nome: " << (*i).getNomeFuncionario() << " / Identificacao: " << (*i).getIdentificacao()<<
                     endl << "Funcao: " << (*i).getFuncao() << endl <<
                     "Salario: "<<(*i).modifica_salario((*i).getSalario(),(*i).getAdicionalSalario()) << endl;
                flag=1;
            }
        }
        if(flag !=1)
        {
            cout << "Tecnico Nao Encontrado"<< endl;
        }
    }

    void procura_Diretor(string id){
        int flag;
        flag=0;
        for(std::list<Diretor>::iterator i=diretor.begin(); i!=diretor.end(); i++)
        {
            if((*i).getIdentificacao() == id || (*i).getNomeFuncionario() == id)
            {
                cout << endl << "Nome: " << (*i).getNomeFuncionario() << " / Identificacao: " << (*i).getIdentificacao()<<
                     endl << "Funcao: " << (*i).getFuncao() << endl <<
                     "Salario: "<<(*i).modifica_salario((*i).getSalario(),(*i).getAdicionalSalario()) << endl;
                flag=1;
            }
        }
        if(flag !=1)
        {
            cout << "Diretor Nao Encontrado"<< endl;
        }
    }

    void procura_Manobrista(string id){
        int flag;
        flag=0;
        for(std::list<Manobrista>::iterator i=manobristas.begin(); i!=manobristas.end(); i++)
        {
            if((*i).getIdentificacao() == id || (*i).getNomeFuncionario() == id)
            {
                cout << endl << "Nome: " << (*i).getNomeFuncionario() << " / Identificacao: " << (*i).getIdentificacao()<<
                     endl << "Funcao: " << (*i).getFuncao() << endl <<
                     "Salario: "<<(*i).modifica_salario((*i).getSalario(),(*i).getAdicionalSalario()) << endl;
                flag=1;
            }
        }
        if(flag !=1)
        {
            cout << "\nManobrista Nao Encontrado"<< endl;
        }
    }

    void procura_Operador(string id){
        int flag;
        flag=0;
        for(std::list<Operador>::iterator i=operadores.begin(); i!=operadores.end(); i++)
        {
            if((*i).getIdentificacao() == id || (*i).getNomeFuncionario() == id)
            {
                cout << endl << "Nome: " << (*i).getNomeFuncionario() << " / Identificacao: " << (*i).getIdentificacao()<<
                     endl << "Funcao: " << (*i).getFuncao() << endl <<
                     "Salario: "<<(*i).modifica_salario((*i).getSalario(),(*i).getAdicionalSalario()) << endl;
                flag=1;
            }
        }
        if(flag !=1)
        {
            cout << "\nOperador Nao Encontrado"<< endl;
        }
    }
//FUNÇÕES SALARIO
    virtual float soma_salarios(){
        float total = 0;
        total = (total + (operadores.size()*1000));
        total = (total + (manobristas.size()*1200));
        total = (total + (tecnicos.size()*1600));
        total = (total + (diretor.size()*4500));
        return total;
    }

    void imprime_funcionarios(){
        fazTXTfunc();
        fazODFfunc();
        fazCSVfunc();

    }
    void fazTXTfunc(){
        FILE *c;
        c=fopen("Relatorio funcionario.txt", "w");
        if(c!=NULL){
            fputs("RELATORIO COMPLETO FUNCIONARIO", c);
            fputs("\n\nDiretor:\n",c);
            for(std::list<Diretor>::iterator i=diretor.begin(); i!=diretor.end(); i++){
                fputs("\n\nID do Diretor: ", c);
                fprintf(c,"%s", (*i).getIdentificacao().c_str());
                fputs("\nNome do diretor: ",c);
                fputs((*i).getNomeFuncionario().c_str(), c);

            }
            fputs("\n\nTecnicos Portuários: \n",c);
            for(std::list<TecnicoPortuario>::iterator i=tecnicos.begin(); i!=tecnicos.end(); i++){
                fputs("\n\nID do Tecnico Portuario: ", c);
                fprintf(c,"%s", (*i).getIdentificacao().c_str());
                fputs("\nNome do Tecnico Portuario: ",c);
                fputs((*i).getNomeFuncionario().c_str(), c);
            }
            fputs("\n\nOperadores:\n",c);
             for(std::list<Operador>::iterator i=operadores.begin(); i!=operadores.end(); i++){
                fputs("\n\nID do Operador: ", c);
                fprintf(c,"%s", (*i).getIdentificacao().c_str());
                fputs("\nNome do Operador: ",c);
                fputs((*i).getNomeFuncionario().c_str(), c);
             }
             fputs("\n\nManobrista:\n",c);
              for(std::list<Manobrista>::iterator i=manobristas.begin(); i!=manobristas.end(); i++){

                 fputs("\n\nID do Manobrista: ", c);
                fprintf(c,"%s", (*i).getIdentificacao().c_str());
                fputs("\nNome do Manobrista: ",c);
                fputs((*i).getNomeFuncionario().c_str(), c);
              }
        }

        fclose(c);
    }

    void fazODFfunc(){
        FILE *c;
        c=fopen("Relatorio funcionario.odf", "w");
        if(c!=NULL){
            fputs("RELATORIO COMPLETO FUNCIONARIO", c);
            fputs("\n\nDiretor:\n",c);
            for(std::list<Diretor>::iterator i=diretor.begin(); i!=diretor.end(); i++){
                fputs("\n\nID do Diretor: ", c);
                fprintf(c,"%s", (*i).getIdentificacao().c_str());
                fputs("\nNome do diretor: ",c);
                fputs((*i).getNomeFuncionario().c_str(), c);

            }
            fputs("\n\nTecnicos Portuários: \n",c);
            for(std::list<TecnicoPortuario>::iterator i=tecnicos.begin(); i!=tecnicos.end(); i++){
                fputs("\n\nID do Tecnico Portuario: ", c);
                fprintf(c,"%s", (*i).getIdentificacao().c_str());
                fputs("\nNome do Tecnico Portuario: ",c);
                fputs((*i).getNomeFuncionario().c_str(), c);
            }
            fputs("\n\nOperadores:\n",c);
             for(std::list<Operador>::iterator i=operadores.begin(); i!=operadores.end(); i++){
                fputs("\n\nID do Operador: ", c);
                fprintf(c,"%s", (*i).getIdentificacao().c_str());
                fputs("\nNome do Operador: ",c);
                fputs((*i).getNomeFuncionario().c_str(), c);
             }
             fputs("\n\nManobrista:\n",c);
              for(std::list<Manobrista>::iterator i=manobristas.begin(); i!=manobristas.end(); i++){

                 fputs("\n\nID do Manobrista: ", c);
                fprintf(c,"%s", (*i).getIdentificacao().c_str());
                fputs("\nNome do Manobrista: ",c);
                fputs((*i).getNomeFuncionario().c_str(), c);
              }
        }

        fclose(c);
    }
    int fazCSVfunc(){
        const char comma = ',';
        string line, word;

        ifstream in( "Relatorio funcionario.txt" );   if ( !in ) { cerr << "Can't open file"; return 1; }
        ofstream out( "Relatorio.csv" );

        while( getline( in, line ) )
        {
            stringstream ss( line );
            bool first = true;
            while ( ss >> word )
            {
                if ( !first ) out << comma;
                out << word;
                first = false;
            }
            out << '\n';
        }

        in.close();
        out.close();
        return 0;
    }
};

class Doca
{
protected:
    std::list<Cargueiro> cargueiros;
    std::list<Cruzeiro> cruzeiros;

public:
    Doca()
    {
        loadDataFromFileCargueiro();
        loadDataFromFileCruzeiro();
    }

    void loadDataFromFileCargueiro(){
         //arquivo onde está os dados do cargueiro
        ifstream file("cargueiros.txt");
        //variavel para pegar a linha
        string line;
        //atributos do construtor de cargueiro
        int i; string n; string c; int q; int de; int me; int ae; int ds; int ms; int as;
        //variavel para salvar a string
        char char_array[100];
        Cargueiro *novo;
        //enquanto o arquivo estiver aberto
        while (!file.eof() )
        {
            //pega a primeira linha do arquivo e salva em line
            getline (file,line);
            if(line=="")
                    break;
            //converte a string para um tipo int;
            i = atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            n = line;
            getline (file,line);
            c = line;
            getline (file,line);
            q= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            de= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            me= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            ae= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            ds= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            ms= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            as= atoi(strcpy(char_array, line.c_str()));
            novo= new Cargueiro(i,n,c,q,Data(de,me,ae),Data(ds,ms,as));
            adiciona_cargueiro(*novo);
        }
    }

    void loadDataFromFileCruzeiro(){
         //arquivo onde está os dados do cargueiro
        ifstream file("cruzeiros.txt");
        //variavel para pegar a linha
        string line;
        //atributos do construtor de cargueiro
        int i; string n; string c; string d; int q; string cap; int de; int me; int ae; int ds; int ms; int as;
        //variavel para salvar a string
        char char_array[100];
        Cruzeiro *novo;
        //enquanto o arquivo estiver aberto
        while (!file.eof() )
        {
            //pega a primeira linha do arquivo e salva em line
            getline (file,line);
            if(line=="")
                    break;
            //converte a string para um tipo int;
            i = atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            n = line;
            getline (file,line);
            c = line;
            getline (file,line);
            d = line;
            getline (file,line);
            q= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            cap = line;
            getline (file,line);
            de= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            me= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            ae= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            ds= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            ms= atoi(strcpy(char_array, line.c_str()));
            getline (file,line);
            as= atoi(strcpy(char_array, line.c_str()));
            novo= new Cruzeiro(i,n,c,d,q,cap,Data(de,me,ae),Data(ds,ms,as));
            adiciona_cruzeiro(*novo);
        }
    }

//FUNÇOES CARGUEIRO
    void adiciona_cargueiro(Cargueiro novoCargueiro){cargueiros.push_back(novoCargueiro);}

    void listaCargueiros(){
        cout <<"Listando Navios: " << endl;
        for(std::list<Cargueiro>::iterator i=cargueiros.begin(); i!=cargueiros.end(); i++)
        {
            cout <<endl << "Id do Cargueiro: " << (*i).getId() << endl << "Nome do Cargueiro: " << (*i).getNome()
                 <<endl << "Tipo de carga: " << (*i).getCarga() <<endl << "Quantidade de container: "<< (*i).getQuantidade()
                 << endl << "Data de chegada: ";
            (*i).getDataEntrada().mostraData();
        }
    }

    void cadastraCargueiro(){
        string  n, c;
        int q,d,m, i, a;

        if(cargueiros.size()<10){
            cout << "Insira ID do Navio de 4 digitos: ";
            cin >> i;
            cout <<"Insira o Nome do Navio: ";
            cin >> n;
            cout <<"Insira o Tipo de Carga do Navio: ";
            cin >> c;
            cout <<"Insira a Quantidade de Container: ";
            cin >> q;
            cout <<"Insira a Data de Chegada(d/m/a): ";
            cin >> d >> m >> a;

            Cargueiro novoCargueiro(i,n,c,q,Data(d,m,a),Data());
            adiciona_cargueiro(novoCargueiro);
            cout << "\n\nCadastro Realizado com Sucesso!" << endl;
        }
        else {
            cout << "\n\nNumero maximo de cargueiros atingidos!" << endl;
        }
    }

    void procuraCargueiro(int idCarg){
        std::list<Cargueiro>::iterator it;
        int aqui;
        it=cargueiros.begin();
        aqui=0;
        while(it!=cargueiros.end())
        {
            if(idCarg==(*it).getId())
            {
                cout << endl << "ID: " <<(*it).getId() << "\n" << " / Nome " <<(*it).getNome()<<endl << "Data de entrada: ";
                (*it).getDataEntrada().mostraData();
                cout << endl <<"Data de saida: ";
                (*it).getDataSaida().mostraData();
                cout << endl << "carga: " << (*it).getCarga() << endl
                     << "quantidade: " << (*it).getQuantidade() << endl;
                aqui = 1;
            }
            it++;
        }
        if(aqui!=1)
        {
            cout << "Navio nao encontrado!";
        }
    }

//FUNÇOES CRUZEIRO
    void adiciona_cruzeiro(Cruzeiro novoCruzeiro){cruzeiros.push_back(novoCruzeiro);}

    void listaCruzeiros(){
        cout <<"Listando Cruzeiros" << endl << endl;
        for(std::list<Cruzeiro>::iterator i=cruzeiros.begin(); i!=cruzeiros.end(); i++)
        {
            cout << endl << "ID do Cruzeiro: " << (*i).getId() << endl << "Nome do Cruzeiro: " << (*i).getNome() << endl
                 << "Nome do Capitao: " << (*i).getNomeCapitao() <<endl
                 <<"Destino: De " << (*i).getLocalSaida() << " para " << (*i).getLocalIda() << endl
                 << "Total de Passageiros:" << (*i).getPassageiros() << endl << "Data Chegada: ";
            (*i).getDataEntrada().mostraData();
        }
    }

    void cadastraCruzeiro(){
        string n, nC, lS, lI;
        int p, d, m, i,a;

        if(cruzeiros.size()<10){
            cout<<"Insira A ID do Cruzeiro de 4 digitos: ";
            cin >> i;
            cout<<"Insira o Nome do Cruzeiro: ";
            cin >> n;
            cout<<"Insira o Nome do Capitao: ";
            cin >> nC;
            cout<<"Insira o Local de Saida: ";
            cin >> lS;
            cout<<"Insira o Destino: ";
            cin >> lI;
            cout <<"Insira o Numero de Passageiros: ";
            cin >> p;
            cout <<"Insira a Data de Chegada <d/m/a>: ";
            cin >> d >> m >> a;

            Cruzeiro novoCruzeiro(i, n, lS, lI, p, nC, Data(d,m,a), Data());
            adiciona_cruzeiro(novoCruzeiro);
            cout << "\n\nCadastro Realizado com Sucesso!" << endl;
        }
        else {
            cout << "\n\nNumero maximo de cruzeiros atingidos!" << endl;
        }
    }

    void procuraCruzeiro(int idCruz){
        std::list<Cruzeiro>::iterator it;
        int aqui;
        it=cruzeiros.begin();
        aqui=0;
        while(it!=cruzeiros.end())
        {
            if(idCruz==(*it).getId())
            {
                cout << endl << "ID do Cruzeiro: " << (*it).getId() << endl << "Nome do Cruzeiro: " << (*it).getNome() << endl
                 << "Nome do Capitao: " << (*it).getNomeCapitao() <<endl
                 <<"Destino: De " << (*it).getLocalSaida() << " para " << (*it).getLocalIda() << endl
                 << "Total de Passageiros:" << (*it).getPassageiros() << endl << "Data Chegada: ";
                (*it).getDataEntrada().mostraData();
                aqui = 1;
            }
            it++;
        }
        if(aqui!=1)
        {
            cout << "Navio nao encontrado!";
        }
    }
//DEMAIS FUNÇÕES
    Data saidaNavio(){
        Data aux;
        std::list<Cargueiro>::iterator i=cargueiros.begin();
        std::list<Cruzeiro>::iterator a=cruzeiros.begin();
        if((*i).getDataEntrada().getMes()==(*a).getDataEntrada().getMes())
        {
            if((*i).getDataEntrada().getDia()>(*a).getDataEntrada().getDia())
            {
                aux=(*a).getDataSaida();
                cruzeiros.pop_front();
                return aux;
            }
            else
            {
                aux=(*i).getDataSaida();
                cargueiros.pop_front();
                return aux;
            }
        }
        else if((*i).getDataEntrada().getMes()>(*a).getDataEntrada().getMes())
        {
            aux=(*a).getDataSaida();
            cruzeiros.pop_front();
            return aux;
        }
        else
        {
            aux=(*i).getDataSaida();
            cargueiros.pop_front();
            return aux;
        }
    }

    float soma_custo(){
        float total=0;
        total = (total + (cargueiros.size()*30000));
        total = (total + (cruzeiros.size()*7000));
        return total;
    }

};


class Porto : public Escritorio, public Doca
{
    string nomePorto;
    float lucro;
//NAVIOS
//FUNCIONARIOS
public:
//CONSTRUTOR
    Porto()
    {
        nomePorto="PROG2";
        lucro = 150000;
    }

//DESTRUTOR
    ~Porto() {}

//GETS E SETS
    void setNomePorto(string np){nomePorto=np;}
    string getNomePorto(){return nomePorto;}

//FUNÇOES PORTO
    void mostraNome(){cout<<"Nome do Porto: "<<nomePorto;}

//OUTRAS FUNÇÕES
    void mostra_custos_porto(){
        float total = lucro + (soma_custo()-soma_salarios());
        FILE *c;
        c=fopen("Relatorio caixa.txt", "w");
        if(c!=NULL){
            fputs("RELATORIO COMPLETO CAIXA", c);
            fputs("\n Dinheiro atual: ", c);
            fprintf(c, "%.2f", lucro);
            fputs("\n entrada futuras: ",c);
            fprintf(c, "%.2f", soma_custo());
            fputs("\n saidas futuras: ",c);
            fprintf(c,"%.2f", soma_salarios());
            fputs("\n total: ",c);
            fprintf(c, "%.2f", total);


        }
        fclose(c);

    }

    void imprime_relatorio(){
   system ("cls");
        fazTXT();
        fazCSV();
        fazODF();
    };

    void fazTXT(){
        FILE *c;
        struct tm *atual;
        time_t segundos;

        time(&segundos);
        atual = localtime(&segundos);
        c=fopen("Relatorio.txt", "w");
        if(c!=NULL){
            fputs("RELATORIO COMPLETO PORTO", c);
            fputs("\n\nCargueiros\n",c);
            for(std::list<Cargueiro>::iterator i=cargueiros.begin();i!=cargueiros.end(); i++){
                fputs("\n\nID do Cargueiro: ", c);
                fprintf(c, "%d", (*i).getId());
                fputs("\nNome do Cargueiro: ",c);
                fputs((*i).getNome().c_str(), c);
                fputs("\nCarga: " ,c);
                fputs((*i).getCarga().c_str(),c);
                fputs("\nQuantidade de Container: ",c);
                fprintf(c, "%d", (*i).getQuantidade());
                fputs("\nData de Chegada e Saida", c);
                fprintf(c, "\nEntrada: %d/%d/%d\n", (*i).getDataEntrada().getDia(),(*i).getDataEntrada().getMes(),(*i).getDataEntrada().getAno()  );
                fputs("Dias no Porto:", c);
                fprintf(c, "%d",atual->tm_mday-(*i).getDataEntrada().getDia());
            }
            fputs("\n\nCruzeiros", c);
            for(std::list<Cruzeiro>::iterator i=cruzeiros.begin();i!=cruzeiros.end();i++){
                fputs("\n\nID do Cruzeiro: ",c);
                fprintf(c, "%d", (*i).getId());
                fputs("\nNome do Cruzeiro: ",c);
                fputs((*i).getNome().c_str(),c);
                fputs("\nNome do Capitão: ",c);
                fputs((*i).getNomeCapitao().c_str(),c);
                fputs("\nLocal de Saida\\Destino: ",c);
                fputs((*i).getLocalSaida().c_str(), c);
                fputs(" \\ ",c);
                fputs((*i).getLocalIda().c_str(),c);
                fputs("\nNumero de Passageiros: ",c);
                fprintf(c, "%d", (*i).getPassageiros());
                fputs("\nData de Chegada: ",c);
                fprintf(c, "Entrada: %d/%d/%d\n", (*i).getDataEntrada().getDia(),(*i).getDataEntrada().getMes(),(*i).getDataEntrada().getAno()  );
                fputs("Dias no Porto:", c);
                fprintf(c, "%d",atual->tm_mday-(*i).getDataEntrada().getDia());
            }
        }
        fclose(c);
    }
    int fazCSV(){
        const char comma = ',';
        string line, word;

        ifstream in( "Relatorio.txt" );   if ( !in ) { cerr << "Can't open file"; return 1; }
        ofstream out( "Relatorio.csv" );

        while( getline( in, line ) )
        {
            stringstream ss( line );
            bool first = true;
            while ( ss >> word )
            {
                if ( !first ) out << comma;
                out << word;
                first = false;
            }
            out << '\n';
        }

        in.close();
        out.close();
        return 0;
    }
    void fazODF(){
        FILE *c;
        struct tm *atual;
        time_t segundos;

        time(&segundos);
        atual = localtime(&segundos);
        c=fopen("Relatorio.odf", "w");
        if(c!=NULL){
            fputs("RELATORIO COMPLETO PORTO", c);
            fputs("\n\nCargueiros\n",c);
            for(std::list<Cargueiro>::iterator i=cargueiros.begin();i!=cargueiros.end(); i++){
                fputs("\n\nID do Cargueiro: ", c);
                fprintf(c, "%d", (*i).getId());
                fputs("\nNome do Cargueiro: ",c);
                fputs((*i).getNome().c_str(), c);
                fputs("\nCarga: " ,c);
                fputs((*i).getCarga().c_str(),c);
                fputs("\nQuantidade de Container: ",c);
                fprintf(c, "%d", (*i).getQuantidade());
                fputs("\nData de Chegada e Saida", c);
                fprintf(c, "\nEntrada: %d/%d/%d\n", (*i).getDataEntrada().getDia(),(*i).getDataEntrada().getMes(),(*i).getDataEntrada().getAno()  );
                fputs("Dias no Porto:", c);
                fprintf(c, "%d",atual->tm_mday-(*i).getDataEntrada().getDia());
            }
            fputs("\n\nCruzeiros", c);
            for(std::list<Cruzeiro>::iterator i=cruzeiros.begin();i!=cruzeiros.end();i++){
                fputs("\n\nID do Cruzeiro: ",c);
                fprintf(c, "%d", (*i).getId());
                fputs("\nNome do Cruzeiro: ",c);
                fputs((*i).getNome().c_str(),c);
                fputs("\nNome do Capitão: ",c);
                fputs((*i).getNomeCapitao().c_str(),c);
                fputs("\nLocal de Saida\\Destino: ",c);
                fputs((*i).getLocalSaida().c_str(), c);
                fputs(" \\ ",c);
                fputs((*i).getLocalIda().c_str(),c);
                fputs("\nNumero de Passageiros: ",c);
                fprintf(c, "%d", (*i).getPassageiros());
                fputs("\nData de Chegada: ",c);
                fprintf(c, "Entrada: %d/%d/%d\n", (*i).getDataEntrada().getDia(),(*i).getDataEntrada().getMes(),(*i).getDataEntrada().getAno()  );
                fputs("Dias no Porto:", c);
                fprintf(c, "%d",atual->tm_mday-(*i).getDataEntrada().getDia());
            }
        }
        fclose(c);
    }
    void WriteToFileCargueiros(){
        FILE *c;

        c=fopen("cargueiros.txt", "w");

        if(c!=NULL){
            for(std::list<Cargueiro>::iterator i=cargueiros.begin();i!=cargueiros.end(); i++){
                fprintf(c, "%d", (*i).getId());
                fputs("\n", c);
                fputs((*i).getNome().c_str(), c);
                fputs("\n", c);
                fputs((*i).getCarga().c_str(),c);
                fputs("\n", c);
                fprintf(c, "%d", (*i).getQuantidade());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataEntrada().getDia());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataEntrada().getMes());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataEntrada().getAno());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataSaida().getDia());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataSaida().getMes());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataSaida().getAno());
                fputs("\n", c);
            }
        }
        fclose(c);
    }
    void WriteToFileCruzeiros(){
        FILE *c;

        c=fopen("cruzeiros.txt", "w");

        if(c!=NULL){
            for(std::list<Cruzeiro>::iterator i=cruzeiros.begin();i!=cruzeiros.end(); i++){
                fprintf(c, "%d", (*i).getId());
                fputs("\n", c);
                fputs((*i).getNome().c_str(), c);
                fputs("\n", c);
                fputs((*i).getLocalSaida().c_str(),c);
                fputs("\n", c);
                fputs((*i).getLocalIda().c_str(),c);
                fputs("\n", c);
                fprintf(c, "%d", (*i).getPassageiros());
                fputs("\n", c);
                fputs((*i).getNomeCapitao().c_str(),c);
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataEntrada().getDia());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataEntrada().getMes());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataEntrada().getAno());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataSaida().getDia());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataSaida().getMes());
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDataSaida().getAno());
                fputs("\n", c);
            }
        }
        fclose(c);
    }
    void WriteToFileFuncionarios(){
        FILE *c;

        c=fopen("funcionarios.txt", "w");

        if(c!=NULL){
            for(std::list<TecnicoPortuario>::iterator i=tecnicos.begin();i!=tecnicos.end(); i++){
                fprintf(c, "%.2f", (*i).getAdicionalSalario());
                fputs("\n", c);
                fputs((*i).getFuncao().c_str(), c);
                fputs("\n", c);
                fputs((*i).getNomeFuncionario().c_str(),c);
                fputs("\n", c);
                fputs((*i).getIdentificacao().c_str(),c);
                fputs("\n", c);
                fprintf(c, "%.2f", ((*i).getSalario()/(*i).getAdicionalSalario()));
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDiasTrabalhados());
                fputs("\n", c);
            }
            for(std::list<Manobrista>::iterator i=manobristas.begin();i!=manobristas.end(); i++){
                fprintf(c, "%.2f", (*i).getAdicionalSalario());
                fputs("\n", c);
                fputs((*i).getFuncao().c_str(), c);
                fputs("\n", c);
                fputs((*i).getNomeFuncionario().c_str(),c);
                fputs("\n", c);
                fputs((*i).getIdentificacao().c_str(),c);
                fputs("\n", c);
                fprintf(c, "%.2f", ((*i).getSalario()/(*i).getAdicionalSalario()));
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDiasTrabalhados());
                fputs("\n", c);
            }
            for(std::list<Diretor>::iterator i=diretor.begin();i!=diretor.end(); i++){
                fprintf(c, "%.2f", (*i).getAdicionalSalario());
                fputs("\n", c);
                fputs((*i).getFuncao().c_str(), c);
                fputs("\n", c);
                fputs((*i).getNomeFuncionario().c_str(),c);
                fputs("\n", c);
                fputs((*i).getIdentificacao().c_str(),c);
                fputs("\n", c);
                fprintf(c, "%.2f", ((*i).getSalario()/(*i).getAdicionalSalario()));
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDiasTrabalhados());
                fputs("\n", c);
            }
            for(std::list<Operador>::iterator i=operadores.begin();i!=operadores.end(); i++){
                fprintf(c, "%.2f", (*i).getAdicionalSalario());
                fputs("\n", c);
                fputs((*i).getFuncao().c_str(), c);
                fputs("\n", c);
                fputs((*i).getNomeFuncionario().c_str(),c);
                fputs("\n", c);
                fputs((*i).getIdentificacao().c_str(),c);
                fputs("\n", c);
                fprintf(c, "%.2f", ((*i).getSalario()/(*i).getAdicionalSalario()));
                fputs("\n", c);
                fprintf(c, "%d", (*i).getDiasTrabalhados());
                fputs("\n", c);
            }
        }
        fclose(c);
    }
};
