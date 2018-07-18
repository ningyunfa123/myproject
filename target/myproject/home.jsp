<%@ page import="com.baidu.mybaidu.pojo.User" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
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
        src="${pageContext.request.contextPath}/js/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.min.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath%>/css/bootstrap-combined.min.css">

    <%User user = (User) session.getAttribute("currentUser"); %>
    <% String name = user==null? "false":StringUtils.isNotEmpty(user.getTrueName())? user.getTrueName():user.getUserName().split("@")[0];%>

<form name="template">
    <input type="hidden" name="userName" id="userName" value="<%=name%>">
</form>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="navbar navbar-inverse">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">网站名</a>
                        <div class="nav-collapse collapse navbar-responsive-collapse">
                            <ul class="nav">
                                <li class="active">
                                    <a href="#">主页</a>
                                </li>
                                <li>
                                    <a href="#">链接</a>
                                </li>
                                <li>
                                    <a href="#">链接</a>
                                </li>
                                <li class="dropdown">
                                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#">下拉导航1</a>
                                        </li>
                                        <li>
                                            <a href="#">下拉导航2</a>
                                        </li>
                                        <li>
                                            <a href="#">其他</a>
                                        </li>
                                        <li class="divider">
                                        </li>
                                        <li class="nav-header">
                                            标签
                                        </li>
                                        <li>
                                            <a href="#">链接1</a>
                                        </li>
                                        <li>
                                            <a href="#">链接2</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="nav pull-right" id="loginStatus">

                                <li class="divider-vertical">
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="hero-unit">
                <h1>
                    Hello, world!
                </h1>
                <p>
                    这是一个可视化布局模板, 你可以点击模板里的文字进行修改, 也可以通过点击弹出的编辑框进行富文本修改. 拖动区块能实现排序.
                </p>
                <p>
                    <a class="btn btn-primary btn-large" href="#">参看更多 »</a>
                </p>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <p>
                        <img alt="" src="<%=basePath%>/img/beauty.jpg" />
                    </p>
                    <form>
                        <fieldset>
                            <legend>套餐一</legend>
                            <p>
                                <label>ip地址：</label>
                            </p>
                            <p>
                                <label>加密方式：</label>
                            </p>
                            <p>
                                <label>套餐价格：￥30/月</label>
                            </p>
                            <p>
                                <label>验证码</label>
                            </p><input type="text" /> <span class="help-block">输入验证码获取信息.</span> <label class="checkbox"><input type="checkbox" /> 勾选同意</label> <button class="btn" type="submit">提交</button>
                        </fieldset>
                    </form>
                </div>
                <div class="span4">
                    <p>
                        <img alt="" src="<%=basePath%>/img/beauty.jpg" />
                    </p>
                    <form>
                        <fieldset>
                            <legend>表单项</legend>
                            <p>
                                ip地址：
                            </p>
                            <p>
                                加密方式：
                            </p>
                            <p>
                                套餐价格：￥50/月
                            </p>
                            <p>
                                <label>验证码</label>
                            </p><input type="text" /> <span class="help-block">输入验证码获取信息.</span> <label class="checkbox"><input type="checkbox" /> 勾选同意</label> <button class="btn" type="submit">提交</button>
                        </fieldset>
                    </form>
                </div>
                <div class="span4">
                    <p>
                        <img alt="" src="<%=basePath%>/img/beauty.jpg" />
                    </p>
                    <form>
                        <fieldset>
                            <legend>表单项</legend>
                            <p>
                                ip地址：
                            </p>
                            <p>
                                加密方式：
                            </p>
                            <p>
                                套餐价格：￥60/月
                            </p>
                            <p>
                                <label>验证码</label>
                            </p><input type="text" /> <span class="help-block">输入验证码获取信息.</span> <label class="checkbox"><input type="checkbox" /> 勾选同意</label> <button class="btn" type="submit">提交</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        loginStatus();

    });
    function loginStatus() {
        var userName = $("#userName").val();
        var html;
        if(userName == "false"){
            html = "<li><a href=\"<%=basePath%>/user/login\">请登录</a></li>";
            html+="<li><a href=\"<%=basePath%>/signup\">注册</a></li>";
            $("#loginStatus").append(html);
        }else{
            html = "<li><a href=\"#\">欢迎 【"+userName+"】</a></li>";
            html+= "<li><a href=\"javascript:logout()\">退出</a></li>"
            $("#loginStatus").append(html);
        }

    }
    function logout() {
        if (confirm("确认退出？")) {
            window.location.href = "<%=basePath%>/user/logout";
        }
    }



</script>

