layui.config({
    base: "js/"
}).use(['form', 'element', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element(),
        $ = layui.jquery;

    $(".panel a").on("click", function () {
        window.parent.addTab($(this));
    })

    /**
     * 总浏览次数
     */
    $.get("../../visitTimes",
        function (data) {
            $(".visitTimes span").text(data.data);  //文章总数

        }
    )

    //图片总数
    $.get("../json/images.json",
        function (data) {
            $(".imgAll span").text(data.length);
        }
    )

    //用户数
    $.get("../users/countUserNum",
        function (data) {
            $(".userAll span").text(data.data);
        }
    )

    //新消息
    $.get("../json/message.json",
        function (data) {
            $(".newMessage span").text(data.length);
        }
    )


    //数字格式化
    $(".panel span").each(function () {
        $(this).html($(this).text() > 9999 ? ($(this).text() / 10000).toFixed(2) + "<em>万</em>" : $(this).text());
    })


    //系统基本参数
    if (window.sessionStorage.getItem("systemParameter")) {
        var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
        fillParameter(systemParameter);
    } else {
        $.ajax({
            url: "../../systemParameter",
            type: "post",
            success: function (data) {

                fillParameter(data);
            }
        })
    }

    /**
     * 填充数据方法
     */
    function fillParameter(data) {

        //判断字段数据是否存在
        function nullData(data) {
            if (data == '' || data == "undefined") {
                return "未定义";
            } else {
                return data;
            }
        }
        $(".copyright").text(data.data.powerby)
        $(".keywords").text(data.data.keywords);    //默认关键字
        $(".systemName").text(data.data.systemName); //当前系统名称
        $(".version").text(data.data.version);      //当前版本
        $(".author").text(data.data.author);        //开发作者
        $(".homePage").text(data.data.homePage);    //网站首页
        $(".server").text(data.data.server);        //服务器环境
        $(".userRights").text(data.data.userRights);//当前用户权限
    }


})
