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
        elem: '#departmentList'
        , url: '/department/queryDepartmentList'
        , cols: [[
            {type: 'checkbox', fixed: 'left'},
            {
                field: 'id', title: 'ID', sort: true
            },
            {
                field: 'departmentName', title: '部门'
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
                field: 'updateTime', title: '修改时间'
            }
            , {
                field: 'createTime', title: '创建时间', sort: true
            }
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 80}
        ]]
        , limit: 15
        , limits: [15, 20, 25, 50, 100]
        , parseData: function (data) {
            /**
             * 打印数据
             *
             */
            console.log(data)
        }
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
                url: '../../../department/updateDepartmentState',
                data: {
                    departmentState: 1,
                    departmentId: data.elem.getAttribute("switchId")
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
                url: '../../../department/updateDepartmentState',
                data: {
                    departmentState: 0,
                    departmentId: data.elem.getAttribute("switchId")
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
        $(".departmentSave").click(function () {
            var index = layui.layer.open({
                title: "添加部门",
                type: 2,
                content: "/page/employee/department/departmentSave.html",
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
     *
     */
    table.on('tool(department)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'edit') {
            // layer.alert('编辑行：<br>' + JSON.stringify(data));

            var index = layui.layer.open({
                title: "修改部门信息",
                type: 2,
                content: "/page/employee/department/departmentUpdate.html",
                success: function (layero, index) {

                    var body = layui.layer.getChildFrame('body', index);
                    if (data) {

                        /**
                         * 取到弹出层里的元素，并把编辑的内容放进去
                         * 重新渲染表单
                         */
                        body.find(".departmentId").val(data.id)
                        body.find(".departmentName").val(data.departmentName);
                        body.find("input[name=state][value='0']").attr("checked", data.state == 0 ? true : false);
                        body.find("input[name=state][value='1']").attr("checked", data.state == 1 ? true : false);

                        form.render();

                    }

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