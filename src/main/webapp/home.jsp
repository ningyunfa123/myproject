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
        src="<%=basePath%>/js/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/JQuery.md5.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath%>/css/bootstrap-combined.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css">

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
                        <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a> <a href="#" class="brand">VPS分享网</a>
                        <div class="nav-collapse collapse navbar-responsive-collapse">
                            <ul class="nav">
                                <li class="active">
                                    <a href="home.jsp">主页</a>
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
                <h2>
                    Hello, everyone!
                </h2>
                <p>
                    &nbsp; &nbsp; &nbsp; &nbsp;当你进入到这个页面的时候,说明你已经购买了相应的vps产品了.该网站是近期刚开发,内部可能会存在一些bug或其它问题,还请各位如果遇到什么问题不要急躁,你及时联系我我及时帮大家解决.淘宝下单支付<a href="https://www.baidu.com"><strong>请点击</strong></a>,二维码支付请点击<button class="btn btn-primary" data-toggle="modal" type="button" data-target="#pictureModal">这里</button>
                </p>
                <p>
                    <a class="btn btn-primary btn-large" href="javascript:fastquery()">快速查看 »</a>
                </p>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <p>
                        <img alt="" src="<%=basePath%>/img/show3.jpg" />
                    </p>
                    <form>
                        <fieldset>
                            <legend>套餐一</legend>
                            <p>ip地址：149.28.19.173</p>
                            <p>加密方式：RC4-MD5</p>
                            <p>套餐价格：￥10/月</p>
                            <p>
                                <label>验证码</label>
                            </p><input id="certCode1" type="text"  /> <span class="help-block">输入验证码获取信息.</span> <label class="checkbox"><input type="checkbox" /> 同意</label> <button class="btn"  type="button" onclick="apply1()" >提交</button>
                        </fieldset>
                    </form>
                </div>
                <!--端口信息返回模态框 -->
                <div id="modal-container-620238" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">
                            vps信息
                        </h3>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label" for="port1">vps端口</label>
                                <div class="controls">
                                    <input id="port1" type="text" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="password1">密码</label>
                                <div class="controls">
                                    <input id="password1" type="text" />
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                    </div>
                </div>
                <!-- #############################-->
                <!--端口信息返回模态框 -->
                <div id="pictureModal" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabe2">
                            支付码
                        </h3>
                    </div>
                    <div class="modal-body">
                        <img alt="" src="<%=basePath%>/img/shoukuan.jpg" style="width: 140px; height: 140px;" />
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                    </div>
                </div>
                <!-- #############################-->
                <div class="span4">
                    <p>
                        <img alt="" src="<%=basePath%>/img/show1.jpg" />
                    </p>
                    <form>
                        <fieldset>
                            <legend>套餐二（暂未开放）</legend>
                            <p>ip地址：149.28.19.173</p>
                            <p> 加密方式：RC4-MD5</p>
                            <p>套餐价格：￥xx/月</p>
                            <p>
                                <label>验证码</label>
                            </p><input id="certCode2" type="text" readonly="readonly" /> <span class="help-block">输入验证码获取信息.</span> <label class="checkbox"><input type="checkbox" /> 同意</label> <button class="btn"  type="button" onclick="apply2()" >提交</button>
                        </fieldset>
                    </form>
                </div>

                <div class="span4">
                    <p>
                        <img alt="" src="<%=basePath%>/img/show2.jpg" />
                    </p>
                    <form>
                        <fieldset>
                            <legend>套餐三（暂未开放）</legend>
                            <p>ip地址：149.28.19.173</p>
                            <p>加密方式：RC4-MD5</p>
                            <p> 套餐价格：￥xx/月</p>
                            <p><label>验证码</label>
                            </p><input id="certCode3"type="text" readonly="readonly"/> <span class="help-block">输入验证码获取信息.</span> <label class="checkbox"><input type="checkbox" /> 同意</label> <button class="btn"  type="button" onclick="apply3()" >提交</button>
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
            html = "<li><a href=\"<%=basePath%>/login.jsp\">请登录</a></li>";
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
    function apply1() {
        var vpsType =1;
        var certCode = $("#certCode1").val();
        var strs = certCode.split("#");
        var useTime = strs[1];
        if(!useTime){
            alert("请输入正确的验证码。");
            return false;
        }
        var preSign = "certCode="+certCode+"&useTime="+useTime+"&vpsType="+vpsType+"&123456";
        var sign = hex_md5(preSign);
        apply(vpsType,certCode,useTime,sign)
        
    }
    function apply2() {
        alert("该套餐暂未开放");
        return false;
        var vpsType =2;
        var certCode = $("#certCode2").val();
        var strs = certCode.split("#");
        var useTime = strs[1];
        if(!useTime){
            alert("请输入正确的验证码。");
            return false;
        }
        var preSign = "certCode="+certCode+"&useTime="+useTime+"&vpsType="+vpsType+"&123456";
        var sign = hex_md5(preSign);
        apply(vpsType,certCode,useTime,sign)
    }
    function apply3() {
        alert("该套餐暂未开放");
        return false;
        var vpsType =3;
        var certCode = $("#certCode3").val();
        var strs = certCode.split("#");
        var useTime = strs[1];
        if(!useTime){
            alert("请输入正确的验证码。");
            return false;
        }
        var preSign = "certCode="+certCode+"&useTime="+useTime+"&vpsType="+vpsType+"&123456";
        var sign = hex_md5(preSign);
        apply(vpsType,certCode,useTime,sign)
    }
    function apply(vpsType,certCode,useTime,sign) {
        $
            .ajax({
                url: "<%=basePath%>/vps/apply",
                type: "post",
                contentType: "application/json",
                data: JSON.stringify({
                    vpsType: vpsType,
                    certCode: certCode,
                    useTime: useTime,
                    sign:sign
                }),
                success: function (data) {
                    if(data.errno ==0){
                        $("#port1").val(data.data.port);
                        $("#password1").val(data.data.password);
                        $('#modal-container-620238').modal('show');
                    }else{
                        alert(data.msg)
                    }

                },
                error: function () {
                    alert("添加出错!");
                }

            });
    }
    function fastquery() {
        $
            .ajax({
                url: "<%=basePath%>/vps/fastquery",
                type: "get",
                data:{},
                success: function (data) {
                    if(data.errno ==0){
                        $("#port1").val(data.data.port);
                        $("#password1").val(data.data.password);
                        $('#modal-container-620238').modal('show');
                    }else{
                        alert(data.msg)
                    }

                },
                error: function () {
                    alert("添加出错!");
                }

            });

    }



</script>

