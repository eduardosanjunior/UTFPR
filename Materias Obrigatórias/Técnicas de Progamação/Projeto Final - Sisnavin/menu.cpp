#include <iostream>
#include <vector>
#include <string.h>
#include <stdlib.h>
#include "porto.cpp"
#include <stdio.h>
#include <windows.h>
#include <fstream>


using namespace std;

class Menu
{
    string nomeUsuario;
    Porto p;
    Data d;
public:
//CONSTRUTOR
    Menu():d(){
        cadastraUsuario();
        mostraMenu();
    }
//DESTRUTOR
    ~Menu() {}
//FUNÇÕES
    void cadastraUsuario(){
        string senha;
        int cont;
        cont = 0;
        cout << endl<< "Insira o nome de Usuario: ";
        cin >> nomeUsuario;
        if(nomeUsuario== "gerente"){
            cout << "Digite a senha do gerente: ";
            cin >> senha;
            while(senha != "admin"&& cont<3){
                cout << "\nSenha incorreta. Digite a senha do gerente: ";
                cin >> senha;
                cont++;
                system ("cls");
           }
           cont=0;
        }
        if(nomeUsuario== "rh"){
            cout << "Digite a senha do RH: ";
            cin >> senha;
            while(senha != "pessoas"&& cont<3){
                cout << "\nSenha incorreta. Digite a senha do gerente: ";
                cin >> senha;
                cont++;
                system ("cls");
        }
        cont=0;
    }
}

    virtual void mostraMenu(){
        Porto();
        int opt;
        system("cls");
        cout << "                 Bem Vindo ao SisNavin 2019      ";
        d.mostraData();
        if(d.getDia()%2==0){
            cout << "                       Mare Alta\n";
        }
        else{
            cout << "                       Mare Baixa\n";
        }
        if(d.getDia()%3==0){
            cout << "                    Tempo: Ensolarado";
        }
        else if(d.getDia()%5==0){
            cout << "                    Tempo: Nublado";
        }
        else if(d.getDia()%7==0){
            cout << "                    Tempo: Chuvoso";
        }
        else if(d.getDia()%2==0){
            cout << "                    Tempo: Neblina";
        }
        else{
            cout << "                    Tempo: Sol entre nuvens";
        }
        cout << "\n\n\nUsuario: "<< nomeUsuario << "\n\n\nEscolha a opcao desejada:\n1. Gerenciar Navios\n2. Gerenciar Funcionarios\n"
        <<"3. Gerenciar Porto\n4. Trocar Usuario\n5. Sair\n\nOpcao: ";
        cin >> opt;
        if(opt == 1 && nomeUsuario=="gerente")
        {
            mostraMenuNavio();
        }

        if(opt == 2 && nomeUsuario=="rh"){
            mostraMenuFunc();
        }
        if(opt == 3){
            mostraMenuPorto();
        }
        if( opt == 4){
            cadastraUsuario();
            mostraMenu();
        }

        if(opt == 5){
            p.WriteToFileCargueiros();
            p.WriteToFileCruzeiros();
            p.WriteToFileFuncionarios();

            exit(EXIT_SUCCESS);
        }

        if(opt>5){
                system("cls");
                cout << "\n\nOpcao Invalida \n Aperte 1 para voltar ou 2 para sair ";
                cin >> opt;
                if(opt==1)
                {
                    mostraMenu();
                }
                if(opt==2)
                {
                    p.WriteToFileCargueiros();
                    p.WriteToFileCruzeiros();
                    p.WriteToFileFuncionarios();
                    exit(EXIT_SUCCESS);
                }
        }
    else{
        cout << "Permissao negada.";
        Sleep(1000);
        mostraMenu();
    }
}

    void mostraMenuNavio(){
        int opt, opt2;
        system("cls");
        cout << "                 Bem Vindo ao SisNavin 2019      ";
        d.mostraData();
        cout <<"\n\n\nEscolha uma opcao\n\n1. Cadastrar Navio\n2. Registar Saida\n3. Listar Cargueiro"
        << "\n4. Listar Cruzeiros\n5. Procurar Navio\n6. Voltar\n\nOpcao: ";
        cin >> opt;
        if(opt==1)
        {
            if(nomeUsuario != "gerente"){
                cout << "Funcao permitida apenas para o gerente! \nAperte 1 para voltar ou 2 para sair: ";
                cin >> opt2;
                if(opt2==1)
                {
                    mostraMenuNavio();
                }
                if(opt2==2)
                {
                    p.WriteToFileCargueiros();
                    p.WriteToFileCruzeiros();
                    p.WriteToFileFuncionarios();
                    exit(EXIT_SUCCESS);
                }
            }
            else{
                if(d.getDia()%2 != 0){
                    cout << "Mare baixa! Impossivel aceitar novo navio :( \nAperte 1 para voltar ou 2 para sair: ";
                    cin >> opt2;
                    if(opt2==1)
                    {
                        mostraMenuNavio();
                    }
                    if(opt2==2)
                    {
                        p.WriteToFileCargueiros();
                        p.WriteToFileCruzeiros();
                        p.WriteToFileFuncionarios();
                        exit(EXIT_SUCCESS);
                    }
                }
                else{
                    system("cls");
                    cout << "                 Bem Vindo ao SisNavin 2019\n\n\n\nEscolha uma opcao\n\n1. Cadastrar Cargueiro "<<
                    "\n2. Cadastrar Cruzeiro\n\nOpcao: ";
                    cin >> opt2;
                    if(opt2==1)
                    {
                        p.cadastraCargueiro();
                        cout << "\n\nCadastro Realizado com Sucesso! \n Aperte 1 para voltar ou 2 para sair ";
                        cin >> opt2;
                        if(opt2==1)
                        {
                            mostraMenu();
                        }
                        if(opt2==2)
                        {
                            p.WriteToFileCargueiros();
                            p.WriteToFileCruzeiros();
                            p.WriteToFileFuncionarios();
                            exit(EXIT_SUCCESS);
                        }
                    }
                    if(opt2==2)
                    {
                        p.cadastraCruzeiro();
                        cout << "\n\nCadastro Realizado com Sucesso! \n Aperte 1 para voltar ou 2 para sair ";
                        cin >> opt2;
                        if(opt2==1)
                        {
                            mostraMenu();
                        }
                        if(opt2==2)
                        {
                            p.WriteToFileCargueiros();
                            p.WriteToFileCruzeiros();
                            p.WriteToFileFuncionarios();
                            exit(EXIT_SUCCESS);
                        }
                    }
                }
            }
        }
        if(opt==2)
        {
            if(nomeUsuario != "gerente"){
                cout << "Funcao permitida apenas para o gerente! \nAperte 1 para voltar ou 2 para sair: " << endl;
                cin >> opt2;
                if(opt2==1)
                {
                    mostraMenuNavio();
                }
                if(opt2==2)
                {
                    p.WriteToFileCargueiros();
                    p.WriteToFileCruzeiros();
                    p.WriteToFileFuncionarios();
                    exit(EXIT_SUCCESS);
                }
            }
            else{
                if(d.getDia()%2 != 0){
                    cout << "Mare baixa! Impossivel aceitar novo navio :( \nAperte 1 para voltar ou 2 para sair: ";
                    cin >> opt2;
                    if(opt2==1)
                    {
                        mostraMenuNavio();
                    }
                    if(opt2==2)
                    {
                        p.WriteToFileCargueiros();
                        p.WriteToFileCruzeiros();
                        p.WriteToFileFuncionarios();
                        exit(EXIT_SUCCESS);
                    }
                }
                else{
                    d=p.saidaNavio();
                    cout << "\n\nSaida Realizada com Sucesso! \nAperte 1 para voltar ou 2 para sair ";
                    cin >> opt2;
                    if(opt2==1)
                    {
                        mostraMenu();
                    }
                    if(opt2==2)
                    {
                        p.WriteToFileCargueiros();
                        p.WriteToFileCruzeiros();
                        p.WriteToFileFuncionarios();
                        exit(EXIT_SUCCESS);
                    }
                }
            }
        }
        if(opt==3)
        {
            system("cls");
            p.listaCargueiros();
            cout << "\n\nCargueiros Listados Com Sucesso! \n Aperte 1 Para Voltar ou 2 Para Sair";
            cin >> opt2;
            if(opt2==1)
            {
                mostraMenu();
            }
            if(opt2==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }
        }
        if(opt==4){
            system("cls");
            p.listaCruzeiros();
            cout << "\n\nCruzeiros Listados Com Sucesso! \n Aperte 1 Para Voltar ou 2 Para Sair";
            cin >> opt2;
            if(opt2==1)
            {
                mostraMenu();
            }
            if(opt2==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }
        }
        if(opt==5)
        {
            int idShip;
            cout << "Digite a ID do navio: ";
            cin >> idShip;
            p.procuraCargueiro(idShip);
            p.procuraCruzeiro(idShip);
            cout << "\n Aperte 1 Para Voltar ou 2 Para Sair";
            cin >> opt2;
            if(opt2==1)
            {
                mostraMenu();
            }
            if(opt2==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }
        }
        if(opt==6)
        {
            mostraMenu();
        }
    }

    void mostraMenuFunc(){
        int opt3;
        system("cls");
        cout << "                 Bem Vindo ao SisNavin 2019      ";
        d.mostraData();
        cout << "\n\n\n\nEscolha uma opcao\n\n1. Cadastra Novo Funcionario\n2. Listar Funcionarios"
        << "\n3. Despedir Funcionario\n4. Procurar Funcionario\n5. Relatorio \n6 Voltar \nOpcao: ";
        cin >> opt3;
        if(opt3==1)
        {
            system("cls");
            p.cadastraFuncionario();
            cout << "\\nAperte 1 para voltar ou 2 para sair: ";
            cin >> opt3;
            if(opt3==1)
            {
                mostraMenu();
            }
            if(opt3==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }
        }
        if(opt3==2)
        {
            system("cls");
            p.listaDiretor();
            p.listaTecnicos();
            p.listaManobristas();
            p.listaOperador();

            cout << "\n\nListagem feita com Sucesso!\nDigite 1 para voltar ou 2 para sair ";
            cin >> opt3;
            if(opt3==1)
            {
                mostraMenu();
            }
            if(opt3==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }
        }
        if(opt3==3)
        {
            string ident;
            system("cls");
            cout << "Escolha um cargo para a demissao: \n 1. Manobrista \n 2. Tecnico \n 3. Operador \n Opcao: ";
            cin >> opt3;
            if(opt3==1)
            {
                cout << "Digite a ID ou Nome do Manobrista: ";
                cin >> ident;
                p.excluindo_manobrista(ident);
            }
            if(opt3==2)
            {
                cout << "Digite a ID ou Nome do Tecnico: ";
                cin >> ident;
                p.excluindo_tecnico(ident);
            }
            if(opt3==3)
            {
                cout << "Digite a ID ou Nome do Operador: ";
                cin >> ident;
                p.excluindo_operador(ident);
            }
            cout << endl << endl <<"Demissao feita com Sucesso" << endl << "Digite 1 para voltar ou 2 para sair ";
            cin >> opt3;
            if(opt3==1)
            {
                mostraMenu();
            }
            if(opt3==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }
        }
        if(opt3==4){
            system("cls");
            string id;
            cout << "Digite a ID ou o Nome do funcionario: ";
            cin >> id;
            p.procura_Diretor(id);
            p.procura_Manobrista(id);
            p.procura_Tecnico(id);
            p.procura_Operador(id);

            cout << endl << endl <<"Procura feita com Sucesso" << endl << "Digite 1 para voltar ou 2 para sair ";
            cin >> opt3;
            if(opt3==1)
            {
                mostraMenu();
            }
            if(opt3==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }
        }
        if(opt3 == 5){
            p.imprime_funcionarios();
            cout << "\n\n\nDigite 1 para voltar ou 2 para sair ";
            cin >> opt3;
            if(opt3==1)
            {
                mostraMenu();
            }
            if(opt3==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }
        }

        if(opt3==6){
            mostraMenu();
        }

    }

    void mostraMenuPorto(){
        int opt4;
        system("cls");
        cout << "                 Bem Vindo ao SisNavin 2019      ";
        d.mostraData();
        cout << "\n\n\n\nEscolha uma opcao\n 1. Contabilidade\n2. Relatorio\n3. voltar\nOpcao: ";
        cin >> opt4;
        if(opt4==1){
            system("cls");
            p.mostra_custos_porto();
            cout << "\n\n\nDigite 1 para voltar ou 2 para sair ";
            cin >> opt4;
            if(opt4==1)
            {
                mostraMenu();
            }
            if(opt4==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }
        }
        if(opt4==2){
            system("cls");
             cout << "\n\n\n Relatório Gerado. ";
            p.imprime_relatorio();
            cout << "\n\n\nDigite 1 para voltar ou 2 para sair ";
            cin >> opt4;
            if(opt4==1)
            {
                mostraMenu();
            }
            if(opt4==2)
            {
                p.WriteToFileCargueiros();
                p.WriteToFileCruzeiros();
                p.WriteToFileFuncionarios();
                exit(EXIT_SUCCESS);
            }

        }
        if (opt4=3)
        {
            mostraMenu();
        }
    }

};
