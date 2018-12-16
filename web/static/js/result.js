$(function () {
    $.ajax({
        type:'post',
        url:'',
        data:data,
        success:function (data) {
            console.log(data);
            $("#rmain3").text("欢迎   "+data);
        }
    })
})