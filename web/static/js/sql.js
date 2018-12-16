var mysql = require('mysql');

var connection = mysql.createConnection({
    host     : 'localhost_3306',
    user     : 'root',
    password : '123456',
    database : 'test'
});

connection.connect();

var  addSql = 'INSERT INTO websites(username,password) VALUES(0,?,?,?,?)';
var  addSqlParams = ['菜鸟工具', 'https://c.runoob.com','23453', 'CN'];
//增
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