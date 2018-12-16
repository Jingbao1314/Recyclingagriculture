var http = require('http');
var querystring = require('querystring');
var mysql = require('mysql');

//服务器端接收数据  
var server = http.createServer(function(req,res){
    if(req.url !== 'favicon.ico'){
        var params;
        req.on('data',function(data){
            //使用querystring模块中的parse方法将字符串转化为对象  
            params = querystring.parse(decodeURIComponent(data));
        })
        req.on('end',function(){
            console.log('客户端请求数据已全部接收完毕');
            connect(params);
        })
        //使用Access-Control-Allow-Origin解决跨域问题  
        res.setHeader('Access-Control-Allow-Origin','*');
        //返回JSON数据  
        res.writeHead(200,{'Content-Type' : 'application/json'});
        res.end(JSON.stringify({status : 1}));
    }
})
    server.listen(1337,'127.0.0.1');

server.on('error',function(e){
    if(e.code == 'EADDRINUSE'){
        console.log('服务器地址及端口已被占用');
    }
})
//设置服务器超时时间为1分钟  
server.setTimeout(60*1000,function(socket){
    console.log('服务器超时');
    console.log(socket);
})
server.on('close',function(){
    console.log('服务器已关闭');
})

//连接数据库  
function connect(params){
    var connection = mysql.createConnection({
        host : 'localhost',
        port : 3306,
        database : 'agri',
        user : 'root',
        password : '1234'
    });
    connection.connect(function(err){
        if(err){
            console.log('与mysql数据库建立连接失败');
        }else{
            console.log('与mysql数据库建立连接成功');
            connection.query('INSERT INTO user SET ?',{Uname: params.name, Upwd: params.password,Iphone:'',Email:'',Address:''},function(err,result){
                if(err){
                    console.log('插入数据失败');
                }else{
                    console.log('插入数据成功');
                    connection.end();
                }
            })
        }
    })
}