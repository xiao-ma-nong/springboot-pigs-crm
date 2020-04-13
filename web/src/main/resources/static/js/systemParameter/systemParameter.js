layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery;

    var systemParameter;


    form.on("submit(systemParameter)", function () {

        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: 500, shade: 0.1});


        $.ajax({
            url: "../../updateSystemParameter",
            type: "get",
            data: $("#formData").serialize(),
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            success: function (data) {
                console.log(data)
                setTimeout(function () {
                    layer.close(index);
                    layer.msg("系统基本参数修改成功！");
                }, 500);

            }
        })

    })

    /**
     * serializeArray()
     * 表单序列化返回json对象
     * @type {*|jQuery}
     * var params = $("#formData").serializeObject();
     * 将表单序列化为JSON对象
     *  $.fn.serializeObject = function () {
     *  var o = {};
     *   var a = this.serializeArray();
     *   $.each(a, function () {
     *       if (o[this.name]) {
     *           if (!o[this.name].push) {
     *               o[this.name] = [o[this.name]];
     *           }
     *           o[this.name].push(this.value || '');
     *       } else {
     *           o[this.name] = this.value || '';
     *       }
     *   });
     *   return o;
     *   }
     */

    //加载默认数据
    $.ajax({
        url: "../../systemParameter",
        type: "post",
        success: function (data) {
            // console.log(data)
            fillData(data);
        }
    })


    //填充数据方法
    function fillData(data) {
        $(".version").val(data.data.version);      //当前版本
        $(".author").val(data.data.author);        //开发作者
        $(".homePage").val(data.data.homePage);    //网站首页
        $(".server").val(data.data.server);        //服务器环境
        $(".serverIp").val(data.data.serverIp);        //服务器Ip
        $(".mongoDBVersion").val(data.data.mongoDBVersion);    //mongoDB数据库版本
        $(".redisVersion").val(data.data.redisVersion);    // redis数据库版本
        $(".mysqlVersion").val(data.data.mysqlVersion);    //mysql数据库版本
        $(".jdkVersion").val(data.data.jdkVersion);    //jdk版本
        $(".userRights").val(data.data.userRights);//当前用户权限
        $(".systemName").val(data.data.systemName);      //系统名称
        $(".description").val(data.data.description);//站点描述
        $(".powerby").val(data.data.powerby);      //版权信息
        $(".record").val(data.data.record);      //网站备案号
        $(".keywords").val(data.data.keywords);    //默认关键字
    }

})
