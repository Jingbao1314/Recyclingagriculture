var http = require('http');
var querystring = require('querystring');
var mysql = require('mysql');
var express = require('express');
var app = express();

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
            console.log(params);
            console.log(params.name);
        })
        //使用Access-Control-Allow-Origin解决跨域问题  
        res.setHeader('Access-Control-Allow-Origin','*');
        //返回JSON数据  
        res.writeHead(200,{'Content-Type' : 'application/json'});
        //var str=JSON.stringify(result);
        //res.end(JSON.stringify({status:1}));
        //console.log(result);
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
var arr = [];
var stc,stc1;
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
            var sql='SELECT * FROM user where Uname=? and Upwd=?';
            var sqll=[params.name,params.password];
            console.log(sql);


            connection.query(sql,sqll,
                function(err,result){
                    if(err){
                        console.log('查询数据失败');
                    }else{
                        console.log('查询数据成功');
                        console.log('查询结果为：',result);
                        var stc=eval('(' + (JSON.stringify(result))+')');//string变json
                        console.log(stc);
                        //return stc;
                        //success();
                        //function success() {
                            if (stc[0].Uname == params.name && stc[0].Upwd == params.password) {
                                console.log(stc[0].Uname);
                                console.log(params.name);
                                console.log(11111111);

                                //res.send(JSON.stringify({status:1}));
                            } else {
                                console.log(222222222);
                                console.log(stc);
                                console.log(stc.Upwd);
                                console.log(typeof stc);
                                console.log(params.name);
                            }
                        //}
                        //console.log(result);
                        /*for (var i = 0; i < rows.length; i++) {
                            arr[i] = result[i].Uname;
                        }
                    console.log(arr);*/
                    connection.end();
                }
                app.post('./first.html', function(req, res) {
                    res.send(stc);
                });
            })
        }
    })
}