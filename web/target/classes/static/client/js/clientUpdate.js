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
     * 监听提交
     */
    form.on('submit(clientUpdate)', function (data) {
        console.log(data.field)

        layer.confirm("确认要修改吗?", {
            yes: function () {
                $.ajax({
                    url: "../../../client/clientUpdate",
                    type: 'put',
                    dataType: 'json',
                    contentType: 'application/json;charset=utf-8',
                    data: JSON.stringify(data.field),
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
