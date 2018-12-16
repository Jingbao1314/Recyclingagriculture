var querystring = require("querystring");
var fs=require("fs");
var mysql      = require('mysql');
var connection = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : '',
    database : 'test'
});

function start(request, response) {
    request.writeHead(200, {'Content-Type': 'text/plain'});
    request.end('result');
}

function register(request, response) {
    request.writeHead(200, {'Content-Type': 'text/plain'});
    var username=querystring.parse(response).username;
    var password=querystring.parse(response).password;
    connection.connect();
    var  addSql = 'INSERT INTO websites(username,password) VALUES(?,?)';
    var  addSqlParams = ['username', 'password'];
    connection.query(addSql,addSqlParams,function (err, result) {
        if(err){
            console.log('[INSERT ERROR] - ',err.message);
            return;
        }

        console.log('--------------------------INSERT----------------------------');
        //console.log('INSERT ID:',result.insertId);
        console.log('INSERT ID:',result);
        console.log('-----------------------------------------------------------------\n\n');
    });
    connection.end();
    request.writeHead(200, {'Content-Type': 'text/plain'});
    request.end('result');
}

exports.start=start;
exports.register=register;