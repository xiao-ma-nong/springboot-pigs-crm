+layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery', 'layedit', 'laydate', 'upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        layedit = layui.layedit,
        laydate = layui.laydate,
        upload = layui.upload;
    $ = layui.jquery;

    //创建一个编辑器
    var editIndex = layedit.build('news_content');
    var addNewsArray = [], addNews;

    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }



    $.ajax({
        url: "../../employee/queryEmployeeInfo",
        type: 'put',
        dataType: 'json',
        data: {
            employeeId: $.getUrlParam("id")
        },
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                console.log($.getUrlParam("id"))

                $("#employeeName").val(data.data[0].name);
                $("#phone").val(data.data[0].phone);

                if (data.data[0].state == 0) {
                    var stateHtml = '<input type="checkbox" checked=""  lay-skin="switch" id="open" lay-filter="switchTest" switchId=' + data.data[0].id + ' ' +
                        ' lay-text="启用|禁用"  value=' + data.data[0].state + '>';
                    $("#employeeState").html(stateHtml);

                } else {
                    var stateHtml = '<input type="checkbox" lay-skin="switch" id="clos" lay-filter="switchTest" switchId=' + data.data[0].id + '' +
                        ' lay-text="启用|禁用"  value=' + data.data[0].state + '>';
                    $("#employeeState").html(stateHtml);
                }

                $("input[name=sex][value='0']").attr("checked", data.data[0].sex == 0 ? true : false);
                $("input[name=sex][value='1']").attr("checked", data.data[0].sex == 1 ? true : false);

                $("#departmentId").find("option[text=" + data.data[0].departmentName + "]").attr("selected", "selected");
                $("#roleId").find("option[text=" + data.data[0].role + "]").attr("selected", "selected");

                form.render();
            } else {
                layer.msg(data.msg + "..", {shift: 6, offset: 'rb'});
            }
        },
        error: function () {
            layer.msg("稍等再尝试" + "..", {shift: 6, offset: 'rb'});
        }
    });

    /**
     * 监听开关 状态 操作
     * console.log(adminInfoCode + " -- " + (this.checked ? 'true' : 'false'));
     * console.log(data.elem.getAttribute("switchId"))
     * var adminInfoCode = data.value;
     */
    form.on('switch(switchTest)', function (data) {
        /**
         * 禁用管理员
         * 状态 赋值为0
         */
        if ((this.checked ? 'true' : 'false') == 'false') {
            $.ajax({
                url: '../../employee/updateEmployeeState',
                data: {
                    employeeState: 1,
                    employeeId: data.elem.getAttribute("switchId")
                },
                type: 'put', //HTTP请求类型
                success: function (data) {

                    if (data.code == 200) {
                        layer.msg("禁用成功", {offset: 'rb', shift: 6})
                    }

                }, error: function () {
                    layer.msg("稍等一会..", {offset: 'rb', shift: 6})
                }

            })
        } else {
            /**
             * 启动用管理员
             * 状态 赋值为1
             */
            $.ajax({
                url: '../../employee/updateEmployeeState',
                data: {
                    employeeState: 0,
                    employeeId: data.elem.getAttribute("switchId")
                },
                type: 'put',
                success: function (data) {
                    if (data.code) {
                        layer.msg("启动成功", {offset: 'rb', shift: 6})
                    }

                }, error: function (data) {
                    layer.msg("稍等一会..", {offset: 'rb', shift: 6})
                }

            })
        }

    });

    /**
     * 监听提交
     */
    form.on('submit(changeUser)', function (data) {
        console.log(JSON.stringify(data.field))
        layer.confirm("确认要修改吗?", {
            yes: function () {
                $.ajax({
                    url: "../../employee/updateEmployeeList",
                    type: 'post',
                    dataType: 'json',
                    contentType: 'application/json;charset=utf-8',
                    data:
                        JSON.stringify(data.field)
                    ,
                    success: function (data) {
                        console.log(data);
                        layer.msg(data.msg + "..", {shift: 6, offset: 'rb'});
                    },
                    error: function () {
                        layer.msg("稍等再尝试" + "..", {shift: 6, offset: 'rb'});
                    }
                });
            }
        })

        return false;
    })

})
