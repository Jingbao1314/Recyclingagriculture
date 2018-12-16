$(function () {
    $.ajax({
        type:'post',
        url:'',
        data:data,
        sucess:function (data) {
            console.log(data);
            $("#buymain3").text("欢迎   "+data);
        }
    });
});