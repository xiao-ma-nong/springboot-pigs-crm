var $form;
var form;
var $;
layui.config({
    base: "../../js/"
}).use(['form', 'layer', 'upload', 'laydate', 'upload'], function () {
    form = layui.form;
    var layer = parent.layer === undefined ? layui.layer : parent.layer;
    $ = layui.jquery;
    $form = $('form');
    laydate = layui.laydate;
    upload = layui.upload;
    /**
     * 显示管理员信息
     */
    $.ajax({
        url: "../../admin/showAdminInfo",
        type: "post",
        dataType: 'json',
        success: function (data) {
            console.log(data)

            /**
             * 显示管理员信息
             */
            var adminAccount = data.data.adminAccount;
            var adminInfoClass = data.data.adminInfoEntity.adminInfoClass;

            $("#adminLoginName").val(adminAccount);
            if (adminInfoClass == 1) {
                $("#adminClass").val("超级管理员");
            } else {
                $("#adminClass").val("普通管理员");
            }

            $("#adminName").val(data.data.adminInfoEntity.adminInfoName);
            $("#adminPhone").val(data.data.adminInfoEntity.adminInfoPhone);
            $("#adminEmail").val(data.data.adminInfoEntity.adminInfoEmail);
            $("#adminImage").attr("src", "../../" + data.data.adminInfoEntity.adminInfoImage);

            console.log($(".adminSex input[name=sex]").val())
            console.log(data.data.adminInfoEntity.adminInfoSex)

            $("input[name=sex][value=女]").attr("checked", data.data.adminInfoEntity.adminInfoSex == 1 ? true : false);
            $("input[name=sex][value=男]").attr("checked", data.data.adminInfoEntity.adminInfoSex == 2 ? true : false);
            $("input[name=sex][value=保密]").attr("checked", data.data.adminInfoEntity.adminInfoSex == 0 ? true : false);

            /**
             * 更新全部 如果没有效果是出不来的
             */
            form.render();
        }
    })


    /**
     * 图片上传
     */
    var uploadInst = upload.render({
        elem: '#updateEmployeeImage'
        , url: '../../employee/updateEmployeeImage'
        , size: 506247
        , before: function (obj) {
            /**
             * 预读本地文件示例，不支持ie8
             */
            obj.preview(function (index, file, result) {
                $('#adminImage').attr('src', result);
            });
        }
        , done: function (res) {
            console.log(res)
            /**
             * 如果上传失败
             */
            if (res.code < 0) {
                return layer.msg('上传失败', {shift: 6, offset: 'rb'});
            } else if (res.code == 200) {
                return layer.msg('上传成功', {offset: 'rb'});
            }else {
                return layer.msg('上传异常', {shift: 6, offset: 'rb'});
            }

        }
        , error: function () {
            /**
             * 演示失败状态，并实现重传
             */

        }
    });
    /**
     * 选完文件后不自动上传
     *

     upload.render({
        elem: '#updateAdminImage'
        ,url: 'https://httpbin.org/post'
        ,auto: false
        //,multiple: true
        ,accept:"images"
        ,bindAction: '#btnSubmit'
        ,before: function(obj){
            / **
     * 预读本地文件示例，不支持ie8
     *  /
            obj.preview(function(index, file, result){
                console.log("文件预读 路径 " +result)
                $('#adminImage').attr('src', result);
            });
        }
        ,done: function(res){
            $('.layui-upload-choose').remove("span.layui-upload-choose")
            layer.msg('上传成功');
            console.log(res)
        }
        });
     **/

})

