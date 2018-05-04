<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/7/27
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mgdy.vesta.taglib.vesta.Viewmodel" %>
<%
    List<Viewmodel> menulist = (List<Viewmodel>) session.getAttribute("menulist");
    List<Viewmodel> secanViewlist = (List<Viewmodel>) session.getAttribute("secanViewlist");

%>

<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="sys.tital"/></title>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
    </style>
    <!-- Bootstrap Core CSS -->
    <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- Custom CSS -->
    <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
    <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function () {
            $("#000600040000").addClass("active");
            $("#000600040000").parent().parent().addClass("in");
            $("#000600040000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000600040000" username="${propertystaff.staffName}"/>
    <div class="container1 userStaffManage">
        <form name="updateStaff" id="updateStaff" method="post">
            <div class="row">
                <div class="col-md-10 role_new_submit">
                    <div class="newRoleSubmit">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">修改密码
                        </button>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal1">编辑
                        </button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 role_new_submit2">
                    <table class="table table-bordered">
                        <caption class="role_table_title">员工基础信息</caption>
                        <tbody>
                        <tr>
                            <td class="role_table_roleName">账号(英文)</td>
                            <td class="role_table_fillCont">
                                ${userInfo.userName}
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_titleable_roleName">姓名(中文)</td>
                            <td class="role_table_fillCont">
                                ${userInfo.staffName}
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName">联系方式</td>
                            <td class="role_table_fillCont">
                                ${userInfo.mobile}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="text-align: -webkit-center">
        <div class="modal-content" style="width: 70%">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">修改员工密码</h4>
            </div>
            <div class="modal-body">
                <form id="altPassword"  class="form-horizontal" action="../user/altPassword" method="post" style="width: 75%">
                    <input type="hidden" id="cnStaffId" name="staffIdDto" value="${userInfo.staffId}">
                    <div class="form-group  col-lg-12">
                        <label for="cnPassword" class="col-sm-2 control-label">旧密码</label>
                        <label class="col-sm-9">
                            <input type="password" class="form-control" autocomplete="off" placeholder=""
                                   id="oldPassword">
                            <span id="checkPwd"
                                  style="color:red;display:none;margin-top: -20px;float: right;margin-right: -90px;"></span>
                        </label>
                    </div>
                    <%--新密码--%>
                    <div class="form-group  col-lg-12">
                        <label for="cnPassword" class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" autocomplete="off" placeholder=""
                                   id="cnPassword" name="passwordDto">
                            <span id="checkNewPwd"
                                  style="color:red;display:none;margin-top: 2px;float: left;margin-right: -22px;"></span>
                        </div>
                    </div>
                    <%--密码确认--%>
                    <div class="form-group  col-lg-12">
                        <label for="Password" class="col-sm-2 control-label">密码确认</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" autocomplete="off" placeholder=""
                                   id="Password">
                            <span id="checkPassword"
                                  style="color:red;display:none;margin-top: 2px;float: left;margin-right: -22px;"></span>
                        </div>
                    </div>
                    <div class="modal-footer" style="border-top: 0px solid #e5e5e5;text-align: -webkit-center;">
                        <button type="button" class="btn btn-primary" onclick="updatePwd()">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                    <div class="clearfix"></div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade " id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
     aria-hidden="true">
    <div class="modal-dialog" style="text-align: -webkit-center">
        <div class="modal-content" style="width: 70%">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">编辑信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="from" action="../user/addStaff" method="post"
                      style="width: 75%">
                    <input type="hidden" id="staffIdDto" name="staffIdDto" value="${userInfo.staffId}">
                    <%--用户名--%>
                    <div class="form-group  col-lg-12">
                        <label for="userNameDto" class="col-sm-2 control-label"><spring:message
                                code="staffManage.staffUserName"/></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="userNameDto" name="userNameDto"
                                   value="${userInfo.userName}">
                            <span id="login_userNameError"
                                  style="color:red;display:none;margin-top: -20px;float: right;margin-right: -90px;"></span>
                        </div>
                    </div>
                    <%--姓名--%>
                    <div class="form-group  col-lg-12">
                        <label for="staffNameDto1" class="col-sm-2 control-label"><spring:message
                                code="staffManage.staffName"/></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="" id="staffNameDto1"
                                   name="staffNameDto" value="${userInfo.staffName}">
                        </div>
                    </div>
                    <%--手机号--%>
                    <div class="form-group  col-lg-12">
                        <label for="mobileDto" class="col-sm-2 control-label"><spring:message
                                code="staffManage.staffMobile"/></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="" id="mobileDto" name="mobileDto"
                                   value="${userInfo.mobile}">
                        </div>
                    </div>

                    <div class="modal-footer" style="border-top: 0px solid #e5e5e5;text-align: -webkit-center;">
                        <%-- 确定 --%>
                        <button type="button" class="btn btn-primary"
                                onclick="testStaff()">
                            <spring:message code="project.confirm"/></button>
                        <%-- 取消 --%>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                                code="project.cancel"/></button>
                    </div>
                    <div class="clearfix"></div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function updatePwd() {
        var oldPwd = $("#oldPassword").val();
        var newPwd = $("#cnPassword").val();
        var newPwd1 = $("#Password").val();
        if (oldPwd == null || oldPwd == "") {
            alert("旧密码为空");
            return;
        }
        if (newPwd == null || newPwd == "") {
            alert("新密码为空");
            return;
        }
        if (newPwd1 == null || newPwd1 == "") {
            alert("确认密码为空");
            return;
        }
        var flag;
        $.ajax({
            url: "../user/checkPwd",
            async: false,
            type: "get",
            data: {"pwd": oldPwd},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (result) {
                if (!result.data.success) {
                    flag = false;
                    showError(checkPwd, '*' + result.data.error);
                } else {
                    flag = true;
                    hideError(checkPwd);
                }
            }
        });
        if (!flag) {
            return;
        }
        if (oldPwd == newPwd) {
            showError(checkNewPwd, '*新密码与旧密码一致');
            return;
        }else {
            hideError(checkNewPwd);
        }
        var reg = /^[a-zA-Z]\w{5,29}$/;
        var flagOldPwd = reg.test(newPwd);
        if (flagOldPwd == false) {
            showError(checkNewPwd, '*以字母开头，长度在6-30之间，只能包含字符、数字和下划线');
            return;
        }else {
            hideError(checkNewPwd);
        }
        if (newPwd != newPwd1) {
            showError(checkPassword, '*确认密码与新密码不一致');
            return;
        }else {
            hideError(checkPassword);
        }
        $("#altPassword").submit();
    }
    function testStaff() {
        var userName = $("#userNameDto").val();
//        var flag = null;
//        $.ajax({
//            url: "../user/searchStaff",
//            async: false,
//            type: "get",
//            data: {"userName": userName},
//            dataType: "json",
//            error: function () {
//                alert("网络异常，可能服务器忙，请刷新重试");
//            },
//            success: function (result) {
//                if (!result.data.success) {
//                    flag = false;
//                    showError(login_userNameError, '*' + result.data.error);
//                } else {
//                    flag = true;
//                }
//            }
//        });
        var staffName = $("#staffNameDto1").val();
        var mobile = $("#mobileDto").val();
        if (userName == "") {
            alert("用户名为空");
            return false;
        }
        var a = new RegExp("^[A-Za-z0-9]+$");
        if (a.test(userName)) {
        } else {
            alert("用户名只能是字母加数字！");
            return false;
        }
        if (userName.length > 20) {
            alert("用户名长度不能大于20");
            return false;
        }
        if (staffName == "" || staffName == null) {
            alert("姓名为空");
            return false;
        }
        if (mobile == "" || mobile == null) {
            alert("手机为空");
            return false;
        }
//        if (!flag) {
//            return false;
//        }
//        alert($("#from").valid());
//        if ($("#login_userNameError").html() != "") {
//            return false;
//        }
        $("#from").submit();
    }
    //获得焦点
    $("#userNameDto").focus(function () {
        hideError(login_userNameError);
    });
    //失去焦点
    $("#userNameDto").blur(function () {
        var userNameDto = $("#userNameDto").val();
        $.ajax({
            url: "../user/searchStaff",
            type: "get",
            async: "false",
            data: {"userName": userNameDto},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (result) {
                if (!result.data.success) {
                    showError(login_userNameError, '*' + result.data.error);
                }
            }
        });
    });
    //显示与隐藏
    function showError(jqId, msg) {
        $(jqId).html(msg);
        $(jqId).show();
    }
    // 隐藏错误信息
    function hideError(jqId) {
        $(jqId).hide();
    }
</script>
</body>
</html>
