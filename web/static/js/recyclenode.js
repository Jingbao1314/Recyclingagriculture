var mysql  = require('mysql');

var connection = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : '1234',
    port: '3306',
    database: 'aaaa',
});

connection.connect();

var  sql = 'SELECT * FROM websites';
//conn.query("select * from"+ " "+ temp,function selectCb(err,result,fields){
//查
connection.query("select * form goods where Gid"+""+temp,function (err, result) {
    if(err){
        console.log('[SELECT ERROR] - ',err.message);
        return;
    }

    console.log('--------------------------SELECT----------------------------');
    console.log(result);
    console.log('------------------------------------------------------------\n\n');
});

connection.end();