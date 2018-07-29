<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=basePath%>/css/index.css" />
    <link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <script type="text/javascript" src="<%=basePath%>/js/jquery.min.js" ></script>
    <script type="text/javascript" src="<%=basePath%>/bootstrap-3.3.7-dist/js/bootstrap.min.js" ></script>


</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="carousel slide" id="carousel-364892" data-interval="4000">
                <ol class="carousel-indicators">
                    <li class="active" data-slide-to="0" data-target="#carousel-364892">
                    </li>
                    <li data-slide-to="1" data-target="#carousel-364892">
                    </li>
                    <li data-slide-to="2" data-target="#carousel-364892">
                    </li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active">
                        <img alt="" src="<%=basePath%>/img/kgyo.jpg" />
                        <div class="carousel-caption">
                            <h4>
                                雄鹰展翅
                            </h4>
                            <p>
                                凛凛生威风，扶遥上太空。盘旋九万里，万物一览中。
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="<%=basePath%>/img/pinzhi.jpg" />
                        <div class="carousel-caption">
                            <h4>
                                品质
                            </h4>
                            <p>
                                好的品质，值得信赖，当然，你，值得拥有。
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="<%=basePath%>/img/planet.jpg" />
                        <div class="carousel-caption">
                            <h4>
                                太空
                            </h4>
                            <p>
                                编不下去了，自己脑补句子吧。
                            </p>
                        </div>
                    </div>
                </div> <a data-slide="prev" href="#carousel-364892" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-364892" class="right carousel-control">›</a>
            </div>
            <form  action="#" id="login_form" >
                <div>
                    <div class="login-item">
                        <span class="span_user glyphicon glyphicon-user "></span>
                        <input type="text"  class="form-input" id="userName" maxlength="21" placeholder="邮箱帐户" name="userName" value="">
                    </div>
                </div>
                <div>
                    <div class="login-item">
                        <span class="span_user glyphicon glyphicon-user "></span>
                        <input type="text"  class="form-input" id="trueName" maxlength="21" placeholder="用户名" name="userName" value="">
                    </div>
                </div>
                <div>
                    <div class="login-item">
                        <span class="span_user glyphicon glyphicon-lock"></span>
                        <input type="password" class="form-input" id="password" maxlength="18"  placeholder="密码" name="password" value="">
                    </div>
                </div>
                <div>
                    <div class="login-item">
                        <span class="span_user glyphicon glyphicon-lock"></span>
                        <input type="password" class="form-input" id="password1" maxlength="18"  placeholder="确认密码" name="password" value="">
                    </div>
                </div>

                <div align="center" >
                    <input type="button"  class="formBtn-login" onclick="signup()" value="注册" />
                </div>


            </form>
        </div>
    </div>
</div>
<%--<div class="login_bottom">--%>
<%--<span class="bottom_info">Copyright©2005-2016 360.CN All Rights Reserved 360安全中心 京ICP证080047号 京公网安备110000000006号</span>--%>
<%--</div>--%>
<script type="text/javascript">
    function signup() {
        var userName = $.trim($("#userName").val());
        var trueName = $.trim($("#trueName").val());
        var password = $.trim($("#password").val());
        var password1 = $.trim($("#password1").val());
        var valid = userName.split("@");
        if(valid.length<=1 || !valid[1]){
            alert("邮箱不合法");
            return false;
        }
        if(!userName || !password || !password1 || !trueName){
            alert("输入信息不全");
            return false;
        }
        if(password != password1){
            alert("两次密码输入不一致");
            return false;
        }

        $
            .ajax({
                url: "<%=basePath%>/user/createUser",
                type: "post",
                contentType: "application/json",
                data: JSON.stringify({
                    userName: userName,
                    trueName:trueName,
                    password: password
                }),
                success: function (data) {
                    if(data.errno ==0){
                        alert("注册成功,请前往登录");
                        window.location.href="<%=basePath%>/login.jsp";
                    }else{
                        alert(data.msg);
                    }

                },
                error: function () {
                    alert("添加出错!");
                    window.location.href = "<%=basePath%>/signup";

                }

            });
    }
</script>
</body>


</html>































