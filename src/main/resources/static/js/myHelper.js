layui.use(['element', 'layer', 'form'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;


    let room = {
        status: false,
        name: '',
        areaid: '',
        buildid: '',
        roomid: '',
        projectId: '',
        email: ''
    }
    // TODO 通过查询任务列表是否拥有，用于判断是否需要增加
    $.ajax({
        url: "/task/get",
        async: false,
        type: "GET",
        success: function (data) {
            $("#addTask").hide();
            showList(data)
        },
        error: function () {
            $("#addTask").click(function () {
                addTask();
            })
        }
    })

    form.on('select(one_cate)', function (data) {
        //data.value 得到被选中的值
        let selectObj = data.elem;
        let obj = $(selectObj.options[selectObj.selectedIndex]);
        room.areaid = obj.attr('areaid');
        room.projectId = obj.attr('projectid')
        $.get({
            url: '/room/build',
            type: 'GET',
            data: {
                areaId: data.value
            },
            dataType: 'json',
            success: function (data) {
                room.buildid = null;
                room.roomid = null;
                let buildHtml = '<option value="">请选择</option>';
                $.each(data, function (index, item) {
                    buildHtml += "<option value='" + item.buildId + "' id='" + item.id + "'>" + item.buildName + "</option>"
                })
                $("#two_cate").html(buildHtml);
                layui.form.render("select");
            }
        })
    });

    form.on('select(two_cate)', function (data) {
        room.buildid = data.value;
        let elem = data.elem;
        let id = $(elem.options[elem.selectedIndex]).attr('id');
        console.log(id);
        $.get({
            url: '/room/room',
            data: {
                buildId: id
            },
            dataType: 'json',
            success: function (data) {
                $("three_cate").empty()
                let roomHtml = '<option value="">请选择</option>';
                $.each(data, function (index, item) {
                    roomHtml += "<option value='" + item.roomId + "'>" + item.roomName + "</option>"
                })
                $("#three_cate").html(roomHtml);
                layui.form.render("select");
            }
        })
    })

    form.on('select(three_cate)', function (data) {
        room.roomid = data.value;
        // 单次查询电费
        $.get({
            url: '/room/oddl',
            data: room,
            dataType: 'json',
            success: function (data) {
                $("#restOddl").val(data)
            }
        })
    })


    $("#submitTask").click(function () {
        // 获取任务名
        room.name = $('#taskName').val();
        // 获取status
        room.status =  $('input[name=status]:checked').val();
        room.email = $('#email').val();

        console.log(room);
        if (room.roomid ==null || room.projectId ==null || room.buildid ==null || room.areaid == null) {
            alert("请选择完整")
            return false;
        }
        let pattern = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
        console.log();
        if (room.email === '' || room.email !== '' && !pattern.test(room.email)) {
            alert("请输入正确邮箱")
            return false;
        }
        $.post({
            url: '/task/add',
            data: room,
            dataType: 'json',
            success: function (data) {
                alert(data)
            }
        })
    })
})


function showList(job) {
    console.log(job)
    let show = $("#taskShow");
    show.show();
    show.html(
        "<form class=\"layui-form\" action=\"#\">\n" +
        "                            <div class=\"layui-form-item\">\n" +
        "                                <label class=\"layui-form-label\">任务名</label>\n" +
        "                                <div class=\"layui-input-block\">\n" +
        "                                    <input type=\"text\" name=\"title\" required  lay-verify=\"required\" disabled=\"disabled\" autocomplete=\"off\" value='" + job.name + "' class=\"layui-input\">\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div class=\"layui-form-item\">\n" +
        "                                <label class=\"layui-form-label\">邮箱：</label>\n" +
        "                                <div class=\"layui-input-inline\">\n" +
        "                                    <input type=\"text\" name=\"password\" required lay-verify=\"required\" disabled=\"disabled\" autocomplete=\"off\" value='" + job.webhook + "' class=\"layui-input\">\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "<div class=\"layui-form-item\">\n" +
        "                            <label class=\"layui-form-label\">任务状态：</label>\n" +
        "                            <input type=\"text\" disabled=\"disabled\" required class=\"layui-input\" value= " + (job.enable ? "开启" : "关闭") + " >\n" +
        "                        </div>\n" +
        "                        <div class=\"layui-form-item\">\n" +
        "                            <label class=\"layui-form-label\">实时监控：</label>\n" +
        "                            <input type=\"text\" disabled=\"disabled\" required class=\"layui-input\" value=" + (job.status ? "开启" : "关闭") + ">\n" +
        "                        </div>" +
        "                        </form>"
    )
}


function openLog(type, id) {
    var boxSize = '600px';
    if (name === "netmusic") {
        boxSize = '450px';
    }

    var index = layer.open({
        title: type + "日志查看",
        type: 2,
        content: "/getlog?type=" + type + "&id=" + id,
        maxmin: true,
        area: screen() < 2 ? ['90%', '80%'] : ['600px', boxSize],
        end: function (index, layero) {
            return true;
        }
    });
}


function editTask(name, id) {
    layer.open({
        type: 2,
        title: '修改' + name + '任务',
        shade: 0.1,
        area: screen() < 2 ? ['90%', '80%'] : ['1200px', '600px'],
        content: name + "/edit?id=" + id,
        end: function (index, layero) {
            updateHtml(name);
            return true;
        }
    });
}

function addTask() {
    $("#addTaskForm").show();
}


//添加状态
function addStatus(_html, status) {
    if (status === "200") {
        _html += '<button class="layui-btn layui-btn-sm">运行完毕</button>';
    }

    if (status === "-1") {
        _html += '<button class="layui-btn layui-btn-sm layui-btn-danger">运行失败</button>';
    }

    if (status === "500") {
        _html += '<button class="layui-btn layui-btn-sm layui-btn-danger">账号信息已过期</button>';
    }

    if (status === "501") {
        _html += '<button class="layui-btn layui-btn-sm layui-btn-normal">执行成功，账号信息更新失败</button>';
    }

    if (status === "0") {
        _html += '<button class="layui-btn layui-btn-sm layui-btn-warm">未开启</button>';
    }

    if (status === "1") {
        _html += '<button class="layui-btn layui-btn-sm layui-btn-disabled">任务运行中</button>';
    }

    if (status === "100" || status == null) {
        _html += '<button class="layui-btn layui-btn-sm layui-btn-primary">等待运行</button>';
    }

    return _html;
}

function removePlan(name, autoId) {
    layer.confirm('确定要删除该任务?删除后无法恢复！', {icon: 3, title: '提示'}, function (index) {
        layer.close(index);
        let loading = layer.load();
        $.post("/api/user/" + name + "/delete", {id: autoId}, function (result) {
            layer.close(loading);
            if (result.code == 200) {
                layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                    updateHtml(name);
                });
            } else {
                layer.msg(result.msg, {icon: 2, time: 1000});
            }
        });
    });
}


