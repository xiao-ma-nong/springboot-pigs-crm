
var $form;
var form;
var $;
layui.config({
    base : "../../js/"
}).use(['form','layer','upload','laydate'],function(){
    form = layui.form();
    var layer = parent.layer === undefined ? layui.layer : parent.layer;
    $ = layui.jquery;
    $form = $('form');
    laydate = layui.laydate;

    /**
     * 显示管理员信息
     */
    $.ajax({
        url: "../../admin/showAdminInfo",
        type: "post",
        dataType: 'json',
        success: function (data) {

            /**
             * console.log(data)
             * 拦截器拦截登录过期的账户 就会提示重新登录
             */
            if (data.code == 103) {
                layer.msg(data.msg, {offset: 'rb'});

                setTimeout(function () {
                    window.location.href = "../../page/login/login.html";
                }, 3000);

            }


            /**
             *  拦截没有登录的管理员进入首页
             */
            if (data.code == 101) {
                layer.msg(data.msg, {offset: 'rb'});
                setTimeout(function () {
                    window.location.href = "../../page/login/login.html";
                }, 3000);

            }

            var adminName = data.data.adminAccount;
            /**
             * 显示管理员名称
             */
            $("#adminLoginName").val(adminName)

        }
    })

    //密码验证规则
    form.verify({
        oldPwd : function(value, item){
            if(value != "123456"){
                return "密码错误，请重新输入！";
            }
        },
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#oldPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })


    //提交个人资料
    form.on("submit(changeUser)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //将填写的用户信息存到session以便下次调取
        var key,userInfoHtml = '';
        userInfoHtml = {
            'realName' : $(".realName").val(),
            'sex' : data.field.sex,
            'userPhone' : $(".userPhone").val(),
            'userBirthday' : $(".userBirthday").val(),
            'province' : data.field.province,
            'city' : data.field.city,
            'area' : data.field.area,
            'userEmail' : $(".userEmail").val(),
            'myself' : $(".myself").val()
        };
        for(key in data.field){
            if(key.indexOf("like") != -1){
                userInfoHtml[key] = "on";
            }
        }
        window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));
        setTimeout(function(){
            layer.close(index);
            layer.msg("提交成功！");
        },2000);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })


    //修改密码
    form.on("submit(changePwd)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("密码修改成功！");
            $(".pwd").val('');
        },2000);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})

