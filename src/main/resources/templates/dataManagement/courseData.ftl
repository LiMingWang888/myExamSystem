<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#include  "../common/navAdmin.ftl">
</div>


<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    课程管理<small>&nbsp;&nbsp;&nbsp;${userType}@${userName}</small>
                </h1>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <p class="text">
                管理课程数据。
            </p>
        </div>
    </div>
</div>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <button id="modal-662079" href="#modal-container-662079" role="button"
                    class="btn btn-default btn-primary addButton" data-toggle="modal">添加课程
            </button>

            <div class="modal fade" id="modal-container-662078" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                修改课程
                            </h4>
                        </div>
                        <div class="container">
                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <form name="modifyCourse" id="modifyCourse" class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">课程代号</label>
                                            <div class="col-sm-3">
                                                <input type="text" name="courseNo" class="form-control" id="courseNo"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">课程名</label>
                                            <div class="col-sm-3">
                                                <input type="text" name="courseName" class="form-control"
                                                       id="courseName"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">课程建议开放学期</label>
                                            <div class="col-sm-3">
                                                <input type="text" name="courseSemester" class="form-control"
                                                       id="courseSemester"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">课程限制代号</label>
                                            <select class="form-control" style="width: 200px;" name="cLimit">
                                                <option value="0">0</option>
                                                <#list limits as limit>
                                                    <option value="${limit}">${limit}</option>
                                                </#list>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">备注</label>
                                            <div class="col-sm-3">
                                                <input type="text" value="0" name="remark" class="form-control"
                                                       id="remark"/>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" id="modify">修改</button>
                        </div>
                    </div>
                </div>
            </div>


            <div class="modal fade" id="modal-container-662079" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                添加课程
                            </h4>
                        </div>
                        <div class="container">
                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <form name="addCourse" id="addCourse" class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">课程代号</label>
                                            <div class="col-sm-3">
                                                <input type="text" name="courseNo" class="form-control" id="courseNo"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">课程名</label>
                                            <div class="col-sm-3">
                                                <input type="text" name="courseName" class="form-control"
                                                       id="courseName"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">课程建议开放学期</label>
                                            <div class="col-sm-3">
                                                <input type="text" name="courseSemester" class="form-control"
                                                       id="courseSemester"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">课程限制代号</label>
                                            <select class="form-control" style="width: 200px;" name="cLimit">
                                                <option value="0">0</option>
                                                <#list limits as limit>
                                                    <option value="${limit}">${limit}</option>
                                                </#list>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="course" class="col-sm-2 control-label">备注</label>
                                            <div class="col-sm-3">
                                                <input type="text" value="0" name="remark" class="form-control"
                                                       id="remark"/>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" id="add">添加</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover table-condensed">
                <thead>
                <tr>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>课程推荐学期</th>
                    <th>课程限制代号</th>
                    <th>备注</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list coursePage.getList() as course>

                <tr>
                    <td>${course.getCourseNo()}</td>
                    <td>${course.getCourseName()}</td>
                    <td>${course.getCourseSemester()}</td>
                    <td>${course.getCLimit()}</td>
                    <#if course.getRemark()=="0">
                        <td>无</td>
                    <#else >
                        <td>${course.getRemark()}</td>
                    </#if>
                    <td><a role="button" class="btn btn-default btn-primary" data-toggle="modal"
                           href="deleteCourse?courseNo=${course.getCourseNo()}">删除</a></td>
                    <td><a role="button" class="btn btn-default btn-primary modifyCourse" data-toggle="modal"
                           href="#modal-container-662078">修改</a></td>
                </tr>
                </tbody>
                </#list>
            </table>
        </div>
    </div>
</div>

<#--分页工具-->
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="pagination">
                <li><a href="courseData?page=${currentPage-1}&size=${size}">上一页</a></li>
                <#list 1..coursePage.getPages() as index>
                    <#if currentPage==index>
                        <li class="disabled"><a href="courseData?page=${index}&size=${size}">${index}</a></li>
                    <#else>
                        <li><a href="courseData?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>

                <li><a href="courseData?page=${currentPage+1}&size=${size}">下一页</a></li>
            </ul>
        </div>
    </div>
</div>


<script>
    $(".addButton").click(function (event) {
        event.preventDefault();
        $("#courseNo").val("");
        $("#courseName").val("");
        $("#courseSemester").val("");
        $("#limit").val("");
        $("#remark").val("无");
    })


    $('.modifyCourse').click(function (event) {
        event.preventDefault();
        var courseNo = $(this).parents("tr").find("td").eq(0).html();
        var courseName = $(this).parents("tr").find("td").eq(1).html();
        var courseSemester = $(this).parents("tr").find("td").eq(2).html();
        var cLimit = $(this).parents("tr").find("td").eq(3).html();
        var remark = $(this).parents("tr").find("td").eq(4).html();
        $("#courseNo").val(courseNo);
        $("#courseName").val(courseName);
        $("#courseSemester").val(courseSemester);
        $("#cLimit").val(cLimit);
        $("#remark").val(remark);
    })


    $('#add').click(function (event) {
        event.preventDefault();
        var formData = new FormData(document.getElementById("addCourse"));

        $.ajax({
            type: "post",
            url: "/exam/admin/addCourse",
            data: formData,
            contentType: false,
            processData: false,
            dataType: "json",
            success: function () {
                window.alert("添加成功");
                window.location.reload();
            }
        });
    });

    $('#modify').click(function (event) {
        event.preventDefault();
        var formData = new FormData(document.getElementById("modifyCourse"));

        $.ajax({
            type: "post",
            url: "/exam/admin/modifyCourse",
            data: formData,
            contentType: false,
            processData: false,
            dataType: "json",
            success: function () {
                window.alert("修改成功");
                window.location.reload();
            }
        });
    });
</script>
