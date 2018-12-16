
/*$(function(){

    $.ajax({
        type:'post',
        url:'',
        data:data,
        success:function (data) {
            console.log(data);
            $("#lawmain").text("欢迎   "+data);
        }
    });


    /!*$("#btn2").click(function(){
        $("#myModal2").modal("show");
    });*!/

});*/


/*var list1 = new Array;
var list2 = new Array;
list1[list1.length] = "北京市";
list1[list1.length] = "天津市";
list1[list1.length] = "河北省";
list1[list1.length] = "山西省";
list1[list1.length] = "内蒙古";
list1[list1.length] = "辽宁省";
list1[list1.length] = "吉林省";
list1[list1.length] = "黑龙江省";
list1[list1.length] = "上海市";
list1[list1.length] = "江苏省";
list1[list1.length] = "浙江省";
list1[list1.length] = "安徽省";
list1[list1.length] = "福建省";
list1[list1.length] = "江西省";
list1[list1.length] = "山东省";
list1[list1.length] = "河南省";
list1[list1.length] = "湖北省";
list1[list1.length] = "湖南省";
list1[list1.length] = "广东省";
list1[list1.length] = "广西自治区";
list1[list1.length] = "海南省";
list1[list1.length] = "重庆市";
list1[list1.length] = "四川省";
list1[list1.length] = "贵州省";
list1[list1.length] = "云南省";
list1[list1.length] = "西藏自治区";
list1[list1.length] = "陕西省";
list1[list1.length] = "甘肃省";
list1[list1.length] = "青海省";
list1[list1.length] = "宁夏回族自治区";
list1[list1.length] = "新疆维吾尔自治区";
list1[list1.length] = "香港特别行政区";
list1[list1.length] = "澳门特别行政区";
list1[list1.length] = "台湾省";
list1[list1.length] = "其它";

var ddlProvince = document.getElementById("province");
for(var i =0;i<list1.length; i++) {
    var option = document.createElement("option");
    option.appendChild(document.createTextNode(list1[i]));
    option.value = list1[i];
    ddlProvince.appendChild(option);
}
function indexof(obj,value) {
    var k=0;
    for(;k<obj.length;k++)
    {
        if(obj[k] == value)
            return k;
    }
    return k;
}
function selectprovince(obj) {
    var index = indexof(list1,obj.value);
}*/



//var temp="<tr>标题：<td style='height: 25px;float: left;' onclick=\"myfun1(bbb)\"><li><a style='color: #2F5BB7;' href=\"javascript:;\">aaa</a></li></td></tr>"

/*$(document).ready(function () {

    $.post(" 116.196.91.8:8080/webtest/ServletZhengce",
        {
            id: id,
            title: title,
            content: content,
        },
        function (data, status) {
            console.log(data);
            $(".container").empty();
            $.each(JSON.parse(data), function (index, value) {
                var newt = temp.replace(/aaa/g, value.title).replace(/bbb/g, value.id);
                //console.log(newt);
                console.log(temp);
                $("#container").append(newt);
            });
        });
}
function myfun1(id){
    location.href ="showcontent.html?newsid="+id;
}*/

function selectprovince(obj) {
    var difang;
    //alert(obj.options[obj.selectedIndex].value);
    difang=obj.options[obj.selectedIndex].value;
    console.log(difang);
    //alert(obj.options[obj.selectedIndex].text);
    $.post("",
        {
            hiddendifang:difang,
        },
        function (data,status) {
            console.log(data);

        }
        )
}
