'''
Script inicial do client-side.
Responsavel unicamente por direcionar o tipo de usuario
'''
import user
from flask import Flask, request
from random import randint
import threading

app = Flask(__name__)
port = randint(4000, 8000)

@app.route('/notify', methods=['POST'])
def notify():
    print("Nova notificação")
    print(request.json["message"])
    print(request.json["data"])
    return "success"

threading.Thread(target=app.run, kwargs={'host': "localhost", 'port': port}).start()
user.start(port)
