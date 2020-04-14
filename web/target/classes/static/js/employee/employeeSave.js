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


    /**
     * 查询角色 并回显
     */
    $.ajax({
        type: "GET",
        url: "/role/queryRole",
        datatype: "json",
        success: function (data) {

            $.each(data.data, function (index, item) {
                $('#role').append(new Option(item.role, item.id));
            });

            layui.form.render("select");
        }, error: function () {
            layer.msg("稍等再尝试" + "..", {shift: 6, offset: 'rb'});
        }
    });

    /**
     * 监听提交
     */
    form.on('submit(changeUser)', function (data) {
        console.log(data)
        layer.confirm("确认要添加吗?", {
            yes: function () {
                $.ajax({
                    url: "../../employee/saveEmployeeList",
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
