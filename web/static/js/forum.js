$(function () {
    $.ajax({
        type:'post',
        url:'',
        data:data,
        success:function (data) {
            console.log(data);
            $("#forummain3").text("欢迎   "+data);
        }
    })
})