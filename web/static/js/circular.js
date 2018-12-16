/*function ecologicalcycle() {
    $.post("",
        {

        },
        function (data) {
            $("#wenzi").text(JSON.parse(data));
        }
    )
};
function weicycle() {
    $.post("",
        {

        },
        function (data) {
            $("#wenzi").text(JSON.parse(data));
        }
    )
}*/
$(function(){

    $(".subNav1").click(function(){
        $(this).toggleClass("currentDd1").siblings(".subNav1").removeClass("currentDd1")
        $(this).toggleClass("currentDt1").siblings(".subNav1").removeClass("currentDt1")

        // 修改数字控制速度， slideUp(500)控制卷起速度
        $(this).next(".navContent1").slideToggle(500).siblings(".navContent1").slideUp(500);
    });


    /*$.ajax({
        type:'post',
        url:'',
        data:data,
        success:function (data) {
            console.log(data);
            $("#cmain3").text("欢迎   "+data);
        }
    })*/
})

