layui.config({
    base: "js/"
}).use(['table', 'layer', 'form', 'jquery'], function () {
    var table = layui.table,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;


    /***
     *
     * 显示全部管理员信息
     *
     */
    table.render({
        elem: '#employeeList'
        , url: '/employee/queryEmployeeList'
        , cols: [[
            {type: 'checkbox', fixed: 'left'},
            {type: 'numbers', title: '序号', align: 'center', width: 40}, // 自增序号
            {
                field: 'image', title: '头像',
                templet: function (data) {
                    return '<img class="layui-circle adminImage" width="26" height="26" src=../../' + data.image + '>'
                }
            }
            , {field: 'name', title: '登录名'}
            , {
                field: 'role', title: '角色'
            }

            , {
                field: 'state', title: '状态', width: 85, templet: function (data) {
                    if (data.state == 0) {
                        return '<div> <input type="checkbox" checked="" name="codeSwitch" lay-skin="switch" id="open" lay-filter="switchTest" switchId=' + data.id + '' +
                            ' lay-text="启用|禁用"  value=' + data.state + '></div>';
                    }
                    return '<div> <input type="checkbox" lay-skin="switch" name="codeSwitch"  switchId=' + data.id + ' lay-filter="switchTest"' +
                        'lay-text="启用|禁用" value=' + data.state + '></div>';

                }
            }
            , {
                field: 'sex', title: '性别',
                templet: function (data) {
                    if (data.sex == 1) {
                        return '<span>' + '男' + '</span>'
                    }

                    return '<span>' + '女' + '</span>'

                }
            }
            , {
                field: 'phone', title: '手机号码', sort: true
            }, {
                field: 'createTime', title: '加入时间', sort: true
            }
            , {
                field: 'departmentName', title: '部门', sort: true
            }
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 110}
        ]]
        , limit: 20
        , limits: [20, 25, 50, 100]
        , parseData: function (data) {
            /**
             * 打印数据
             *  console.log(data)
             */

        }
        /**
         * 开启头部工具栏，并为其绑定左侧模板
         */
        , toolbar: '#utilsa'

        /**
         * 开启分页
         */
        , page: true
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
     * 添加员工
     * 改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
     */
    $(window).one("resize", function () {
        $(".newsAdd_btn").click(function () {
            var index = layui.layer.open({
                title: "添加用户",
                type: 2,
                content: "/page/employee/employeeSave.html",
                success: function (layero, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回员工列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 1000)
                }
            })
            layui.layer.full(index);
        })
    }).resize();

    /**
     *修改员工
     */
    table.on('tool(demo)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'edit') {
            // layer.alert('编辑行：<br>' + JSON.stringify(data));

            var index = layui.layer.open({
                title: "修改员工",
                type: 2,
                content: "/page/employee/employeeUpdate.html?id=" + data.id,
                success: function (layero, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回员工列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 1000)
                }
            });
            layui.layer.full(index);

        }
    });


});