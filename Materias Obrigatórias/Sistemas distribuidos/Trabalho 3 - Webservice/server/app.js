var express = require('express');
var indexRouter = require('./routes/index');
// Nova instancia do express
var app = express();

// Adiciona plugin de parseamento JSON
app.use(express.json());
// Adiciona plugin de parametros na URL
app.use(express.urlencoded({ extended: false }));
// Adiciona as rotas
app.use('/', indexRouter);
// Exporta a aplicacao para ser rodada
module.exports = app;
