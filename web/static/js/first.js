$(document).ready(function () {
    $("#btn2").click(function(){
        $("#myModal2").modal("show");
    });

    $("#btn3").click(function(){
        $("#myModal3").modal("show");
    });

});

var phoneCheck = function (tel) {
    if (isNaN(tel)) {
        return false;
    } else {
        return true;
    }
};

function check_validr() {
    var username = encodeURI($("#username2").val());
    var email = encodeURI($("#email1").val());


    if (username == "") {
        alert("请输入账号");
        return false;
    }
    if (!phoneCheck(username)) {
        alert("输入手机号不正确");
        return false;
    }
    if (username.length != 11) {
        alert("输入手机号应该11位");
        return false;
    }
    if (!$("#nicheng").val()) {
        alert("请输入昵称！");
        return false;
    }
    if (!$("#password2").val()) {
        alert("密码不能为空");
        return false;
    }
    if (email == "") {
        alert("请输入邮箱！");
    } else {
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        isok = reg.test(email);
        if (!isok) {
            alert("邮箱格式不正确！");
            document.getElementByIdx("emailname").focus();
            return false;
        }
    }
    if (!$("#home").val()) {
        alert("请输入生源地！");
        return false;
    }


    var name = $('#username2').val(),
        nicheng = $('#nicheng').val(),
        password = $('#password2').val(),
        email1 = $('#email1').val(),
        home = $('#home').val(),
        data = {iphone: name, uname: nicheng, upwd: password, email: email1, address: home};
    $.ajax({
        type: 'POST',
        url: 'http://116.196.91.8:8080/webtest/Register',
        data: data,
        success: function (data) {
            console.log(data);
            if(data==true){
                alert("注册成功!");
                $("#myModal2").hide();
            }else{
                alert("未注册成功！");
            }

        }
    })
}
function check_valid() {

    var username = encodeURI($("#username").val());
    if (username == "") {
        alert("请输入账号");
        return false;
    }
    if (!phoneCheck(username)) {
        alert("输入手机号不正确");
        return false;
    }
    if (username.length != 11) {
        alert("输入手机号应该11位");
        return false;
    }
    if (!$("#password").val()) {
        alert("密码不能为空");
        return false;
    }

    var name = $('#username').val(),
        password = $('#password').val(),
        data = {iphone : name,upwd : password};
    /*alert(name);
    alert(password);*/
    $.ajax({
        type : 'POST',
        url : 'http://116.196.91.8:8080/webtest/UserLogin',
        data : data,
        success : function(data){
            alert(JSON.stringify(data));
            console.log(data);
            if(data==true){
                alert("登录成功!");
                window.location.href="first.html";
            }else{
                alert("未登录成功！");
            }
        }
    })

}
function check_validadmin() {

    var username = encodeURI($("#username3").val());
    if (username == "") {
        alert("请输入账号");
        return false;
    }
    /*if (!phoneCheck(username)) {
        alert("输入手机号不正确");
        return false;
    }
    if (username.length != 11) {
        alert("输入手机号应该11位");
        return false;
    }*/
    if (!$("#password3").val()) {
        alert("密码不能为空");
        return false;
    }

    var name = $('#username3').val(),
        password = $('#password3').val(),
        data = {iphone : name,upwd : password};
    /*alert(name);
    alert(password);*/
    $.ajax({
        type : 'POST',
        url : 'http://116.196.91.8:8080/webtest/UserLogin',
        data : data,
        success : function(data){
            alert(JSON.stringify(data));
            console.log(data);
            if(data==true){
                alert("管理员登录成功!");
                window.location.href="admin.html";
            }else{
                alert("未登录成功");
            }
        }
    })
};

/*
var temp="<tr>标题：<td style='height: 25px;float: left;' onclick=\"myfun(bbb)\"><li><a style='color: #2F5BB7;' href=\"javascript:;\">aaa</a></li></td></tr>";


$.post(" 16.196.91.8:8080/webtest/ServletNewSelect",
    {
        //id:id,
        title:title,
        content:content,
    },
    function(data,status){
        console.log(data);
        $(".container").empty();
        $.each(JSON.parse(data), function(index, value) {
            var newt = temp.replace(/aaa/g,value.title).replace(/bbb/g,value.id);
            //console.log(newt);
            console.log(temp);
            $("#container").append(newt);
        });
    });

function myfun(id){
    location.href ="showcontent.html?newsid="+id;
}
*/

