'''
Sessão do usuario
Pode consultar transfers disponiveis e reservar transfers
'''
import requests

def start(port):
    # Loop principal

    print("Bem vindo")
    user = input("Qual seu nome?\n")
    print(f"Ola {user}!")

    # Verifica se o server esta respondendo
    try:
        r = requests.get("http://localhost:3000", { 'user': user, 'port': port })
        content = r.json()
        print(content['message'])
    except:
        print("Ocorreu um erro na comunicação com o servidor. Tente novamente mais tarde!")
        exit()

    print("Tudo pronto!")

    while True:
        try:
            print(f"MENU - Usuário: {user}")
            print("1 - Consultar Ações")
            print("2 - Consultar Minhas Cotações")
            print("3 - Vender Ação")
            print("4 - Atualizar oferta de Ação")
            print("5 - Comprar Ação")
            print("6 - Receber Notificações")
            print("7 - Adicionar ação a Minhas Cotações")
            print("8 - Remover ação de Minhas Cotações")
            print("0 - Sair")
            choice = int(input())

            # Consultar as ações disponiveis
            if choice == 1:
                print("Vamos listar todas as ações")
                # Envia requisicao
                r = requests.get("http://localhost:3000/stocks/")
                payload = r.json()
                if r.status_code == 200:
                    print("Venda")
                    print(*payload["venda"], sep = "\n") 
                    print("Compra")
                    print(*payload["compra"], sep = "\n") 
                else:
                    print(payload['message'])

            # Consultar as cotações do usuário
            elif choice == 2:
                print("Vamos listar suas cotações")
                
                r = requests.get("http://localhost:3000/stocks/", { 'owner': user })
                payload = r.json()
                if r.status_code == 200:
                    print("Venda")
                    print(*payload["venda"], sep = "\n") 
                    print("Compra")
                    print(*payload["compra"], sep = "\n") 
                else:
                    print(payload['message'])
            
            # Vender ação
            elif choice == 3:
                print("Insira as informações da ação")
                code  = input("Qual o código da ação?\n")
                quantity = int(input("Qual a quantidade de ações que esta vendendo\n"))
                minPrice = float(input("Qual o valor minimo que deseja receber (decimais separados por ponto)\n"))
                dueData = input("Até quando deseja deixar a oferta disponivel (formato AAAA-MM-DD HH:mm:ss)\n")

                r = requests.put("http://localhost:3000/stocks/", { 'code': code, 'quantity': quantity, 'minPrice': minPrice, 'dueData': dueData, 'owner': user })
                payload = r.json()
                if r.status_code == 200:
                    print("Oferta cadastrada com sucesso")
                    print(payload)
                else:
                    print(payload['message'])
            
            # Atualizar oferta
            elif choice == 4:
                print("Vamos atualizar a oferta")
                id = input("Qual o ID da oferta?")
                quantity = input("Qual a quantidade de ações que esta vendendo (Deixe em branco para não alterar)\n")
                minPrice = input("Qual o valor minimo que deseja receber (decimais separados por ponto) (Deixe em branco para não alterar)\n")
                dueData = input("Até quando deseja deixar a oferta disponivel (formato AAAA-MM-DD HH:mm:ss) (Deixe em branco para não alterar)\n")
            
                payload = { 'id': id, 'owner': user }
                if quantity != "":
                    payload['quantity'] = quantity 
                if minPrice != "":
                    payload['minPrice'] = minPrice 
                if dueData != "":
                    payload['dueData'] = dueData 

                r = requests.put("http://localhost:3000/stocks/", payload)
                return_payload = r.json()
                if r.status_code == 200:
                    print("Oferta atualizada com sucesso")
                    print(return_payload)
                else:
                    print(return_payload['message'])
            
            # Comprar ação
            elif choice == 5:
                print("Vamos comprar uma ação")
                code = input("Qual o código da ação que deseja comprar?\n")
                price = float(input("Qual o preço máximo que deseja pagar? (decimais separados por ponto)\n"))
                quantity = int(input("Qual a quantidade que deseja negociar?\n"))
                dueData = input("Até quando deseja deixar a oferta disponivel (formato AAAA-MM-DD HH:mm:ss)\n")

                r = requests.post(f"http://localhost:3000/stocks/{code}/bid/", { 'price': price, 'quantity': quantity, 'bider': user, 'dueData': dueData })
                payload = r.json()
                if r.status_code == 200:
                    print("Oferta realizada com sucesso")
                    print(payload)
                else:
                    print(payload['message'])

            # Receber notificação
            elif choice == 6:
                print("Vamos cadastrar a notificação")
                code = input("Qual o código da ação que deseja receber notificação?\n")
                maxGain = float(input("Qual o preço máximo de ganho para a ação? (decimais separados por ponto)\n"))
                maxLoss = float(input("Qual o preço máximo de perda para a ação? (decimais separados por ponto)\n"))

                r = requests.post("http://localhost:3000/notify/", { 'user': user, 'code': code, 'maxGain': maxGain, 'maxLoss': maxLoss })
                payload = r.json()
                if r.status_code == 200:
                    print("Notificação cadastrada com sucesso")
                    print(payload)
                else:
                    print(payload['message'])

            # Adicionar ação a cotações
            elif choice == 7:
                print("Vamos adicionar uma nova cotação")
                code = input("Qual o codigo da ação que deseja monitorar?\n")
                
                r = requests.put("http://localhost:3000/stocks/cotation", { 'owner': user, 'code': code })
                payload = r.json()
                if r.status_code == 200:
                    print("Cotação adicionada com sucesso")
                else:
                    print(payload['message'])
            
            # Remover ação de cotações
            elif choice == 8:
                print("Vamos remover uma cotação")
                code = input("Qual o codigo da ação que deseja deixar de monitorar?\n")
                
                r = requests.delete("http://localhost:3000/stocks/cotation", { 'owner': user, 'code': code })
                payload = r.json()
                if r.status_code == 200:
                    print("Cotação removida com sucesso")
                else:
                    print(payload['message'])
            
            # Sair
            elif choice == 0:
                print("Ate logo!")
                exit(0)
                break
        except:
            print("Ocorreu um erro durante a execução, tente novamente")