function mychuan(num) {
    $.post("",
        {
            num:num
        },
        function (data) {
            console.log(data);
            var data1=JSON.parse(data);
        }
        )
}


/*
$(function () {
    $.ajax({
        type:'post',
        url:'',
        data:data,
        success:function (data) {
            console.log(data);
            $("#pmain3").text("欢迎   "+data);
        }
    })
})*/
