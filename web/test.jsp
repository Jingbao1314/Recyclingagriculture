<%--
  Created by IntelliJ IDEA.
  User: xiao
  Date: 17-12-2
  Time: 下午1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function createXml() {
            var xmlreq=false;
            if(window.XMLHttpRequest){
                xmlreq=new XMLHttpRequest("Msxml2.XMLHTTP");
            }else if(window.ActiveXObject){
                xmlreq=new ActiveXObject("Microsoft.XMLHTTP");
            }
            return xmlreq;
        }
//        function submit1() {
//            var title=document.all.title.value;
//            var content=document.all.content.value;
//            var request=createXml();
//            $.ajax({
//                data: JSON.stringify({
//                    title: ($('input[title="title"]').val()),
//                    content: ($('input[content="content"]').val()),
//                }),
//            })
//
//                request.open("post","ServletNewADD"+data);
//            if(request.readyState==4)
//                if(request.status==200){
//                    var value=request.responseText;
//                    alert(value)
//                    if(value=="true"){
//                        alert("true");
//                    }
//                    else
//                        alert("false");
//                }
//        }
//        function submit1() {
//            var request=createXml();
//            $.ajax({
//                type: "POST",
//                url: "/ServletNewADD",
//                contentType: "application/json", //必须有
//                dataType: "json", //表示返回值类型，不必须
//                data: JSON.stringify({ 'title': '新闻', 'content': '新闻' }),  //相当于 //data: "{'str1':'foovalue', 'str2':'barvalue'}",
//                success: function (jsonResult) {
//                    alert(jsonResult);
//                }
//            });
//        }
//        }
        function submit1() {
            xmlhttp=new XMLHttpRequest();
            xmlhttp.open("POST", "/ServletNewADD", false);
            
            xmlhttp.send("{\"title\": \"新闻\", \"content\": \"新闻\"}");
//            console.log(xmlhttp.responseText)
            console.log(xmlhttp.status)
//            xmlhttp.setRequestHeader("Access-Control-Allow-Origin","*")
            if (xmlhttp.readyState == 4 && xmlhttp.status ==200) {
                xxx = xmlhttp.getAllResponseHeaders();
                console.log(xxx)
                console.log(xmlhttp.responseText);
                alert(xmlhttp.responseText);
            }
//
        }
    </script>
</head>
<body>
<input type="text" id="title" class="title" placeholder="标题"/><br>
<input type="text" id="content" class="content" placeholder="内容" style="margin-top:10px;"/><br>
<button type="button" id="submit1" style="margin-top: 10px;margin-left:50px;"
        onclick="submit1()">提交</button>
</body>
</html>
