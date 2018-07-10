<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
<meta charset="utf-8"/>
<meta name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>

<%-- <link type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap-pagination.min.css"
	rel="stylesheet" /> --%>
<script type="text/javascript" charset="utf-8"
        src="<%=basePath%>/js/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css">

<title>添加展示日志</title>

<script type="text/javascript">


    function createConfig(){
        var logShow = $.trim($("#logShow").val());
        $
            .ajax({
                url: "<%=basePath%>/user/updateUser",
                type: "post",
                contentType: "application/json",
                data:  JSON.stringify({
                    userName:logShow
                }),
                success: function (data) {
                    if (data == true) {
                        alert("添加成功!");
                        pagehtml($("#currentPage").val());
                    }else{
                        alert("添加失败");
                    }
                },
                error: function () {
                    alert("添加出错!");
                }
            });
    }
</script>

<form method="post" class="form-horizontal" action="" role="form"
      id="createForm" style="margin: 20px;">
    <div class="modal fade" id="createEquipment" tabindex="-1"
         role="dialog" aria-labelledby="createModalLabel" aria-hidden="true"
         data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="logShow" class="col-md-4 control-label">日志</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="logShow" name="uriConfig"
                                   placeholder="">
                        </div>
                    </div>

                </div>
                <div class="modal-footer" style="text-align:center">
                    <button id="saveConfig" type="button" class="btn btn-primary" onclick="createConfig()" >提交</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>

</form>


