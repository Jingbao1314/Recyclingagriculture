<%--
  Created by IntelliJ IDEA.
  User: xiao
  Date: 17-11-15
  Time: 下午11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <%--<script src="../../Content/jquery-2.0.3.min.js" type="text/javascript"></script>--%>
    <script type="text/javascript">
        function aa() {
            xmlhttp=new XMLHttpRequest();
            xmlhttp.open("POST", "http://116.196.91.8/webtest/UserLogin", false);
            xmlhttp.send("{\"iphone\": \"123123\", \"upwd\": \"aaaaa\"}");
//            console.log(xmlhttp.responseText)
            console.log(xmlhttp.status)
            xmlhttp.setRequestHeader("Access-Control-Allow-Origin","*")
            if (xmlhttp.readyState == 4 && xmlhttp.status ==200) {
                xxx = xmlhttp.getAllResponseHeaders();
                console.log(xxx)
                console.log(xmlhttp.responseText);
                alert(xmlhttp.responseText);
            }

        }
    </script>
  </head>
  <body>
  <div>
    <input type="button" value="提交" onclick="aa()" />

  </div>
  </body>
</html>
