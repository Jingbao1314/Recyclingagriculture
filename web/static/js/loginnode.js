
var http = require('http');//'http'相当于引入js包
var url = require('url');

http.createServer(function (request, response) {

    var params = url.parse(req.url,true).query;
    var queryStr1 = params.hiddenmobile;
    var queryStr2 = params.hiddenpaw;

    var param = url.parse(req.url).pathname;

    // 发送 HTTP 头部
    // HTTP 状态值: 200 : OK
    // 内容类型: text/plain
    response.writeHead(200, {'Content-Type': 'text/plain'});

    // 发送响应数据 "Hello World"
    response.end('result');
}).listen(8888);//8888是端口号

// 终端打印如下信息
console.log('Server running at http://127.0.0.1:8888/');