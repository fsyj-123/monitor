$.ajax({
    url: "/user/me",
    async: false,//关键是这个参数 是否异步请求=>false:使用同步请求
    type: "POST",
    success: function (data) {
        console.log(data)
        $(".noLoginShowMenu").show();
        $(".loginShowMenu").hide();
        $(".adminShowMenu").hide();

        $("#loginUserName").html(data.username);
        $(".noLoginShowMenu").hide();
        $(".loginShowMenu").show();

        if (data.authorities[0].authority === "ROLE_ADMIN") {
            $(".adminShowMenu").show();
        }
    },
    error: function (data) {
        console.log(data)
        $(".loginShowMenu").hide();
        $(".adminShowMenu").hide();
    }
})
