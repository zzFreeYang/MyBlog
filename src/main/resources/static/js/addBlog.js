window.onload = function () {

};

// 增加留言
$('#addBlog').click(function () {
    var articleName = $('#articleName').val();
    var articleCategory = $("input[name='articleCategory']:checked").val();
    var articleContent = $('#articleContent').val();
    var TopValue = $('#TopValue').val();
    var pictureurl = $('#picturlUrl').val();
    // 判断是否为空
    if ("" == articleName || "" == articleContent) {
        $('#modal').modal();
        return;
        return;
    }

    // topValue处理
    var topBolean ;
    if("1" == TopValue){
        topBolean = true;
    }
    else {
        topBolean = false;
    }

    // 不为空才能增加
    var blog = {
        title: articleName,
        categoryId: articleCategory,
        top:topBolean,
        content: articleContent,
        pictureUrl:pictureurl
    }
    // 提交AJAX请求
    $.ajax({
        url: "http://127.0.0.1:8080/api/addBlog/",
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(blog),
        success: function () {
            // 显示成功提示信息
            $('#addModal').modal();
        },
        error: function () {
            // 显示成功提示信息
            $('#addModal').modal();
        }

    })
});

// 模态框确认按钮点击事件
$('#addConfirmBtn').click(function () {
    // 刷新页面
    location.reload();
});