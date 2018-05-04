<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2017/7/13
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
    <!-- Bootstrap Core CSS -->
    <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>


    <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/plus/js/jquery.validate.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <style>
        .btnDis {
            display: none;
            /*background-color: #eee;*/
            /*border: none;*/
        }

        .btn.btnDis:hover {
            display: none;
            /*background-color: #eee;*/
            /*border: none;*/
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000600010000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" name="searchByName" id="searchByName"
                      action="../user/userStaffManage.html">
                    <div class="form-group  col-lg-4">
                        <label for="staffNameDto" class="col-sm-4 control-label">真实姓名</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="staffNameDto"
                                   name="admStaff" value="${adminDTO.admStaff}">
                        </div>
                    </div>
                    <%--用户名--%>
                    <div class="form-group  col-lg-4">
                        <label for="userName" class="col-sm-4 control-label"><spring:message
                                code="staffManage.staffSelUserName"/></label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="userName"
                                   name="admUser" value="${adminDTO.admUser}">
                        </div>
                    </div>

                    <button type="button" id="sbm" class="btn btn-primary" for="propertySearch"><spring:message
                            code="propertyCompany.propertySearch"/></button>
                    <button type="button" class="btn btn-primary" onclick="addstaff()">新建用户</button>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>

    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <th>排序号</th>
                <th>用户名</th>
                <th>姓名</th>
                <th>联系方式</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userStaffList}" var="userStaff" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td>${userStaff.userNameDto}</td>
                    <td>${userStaff.staffNameDto}</td>
                    <td>${userStaff.mobileDto}</td>
                    <td class="last">
                        <a href="javascript:void(0);" onclick="updatestaff('${userStaff.staffIdDto}')" class="a3"><span
                                class="span1">编辑</span></a>
                        <a href="javascript:void(0);" onclick="delStaffUser('${userStaff.staffIdDto}')" id="del"
                           class="a3">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/user/getUserStaffList.html?pageIndex={0}&admStaff=${adminDTO.admStaff}&admUser=${adminDTO.admUser}"/>
    </div>


    <div class="modal fade " id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
         aria-hidden="true">
        <div class="modal-dialog" style="text-align: -webkit-center">
            <div class="modal-content" style="width: 70%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel1">添加员工信息</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="from" action="../user/addStaff" method="post"
                          style="width: 75%">
                        <input type="hidden" id="staffIdDto" name="staffIdDto">
                        <%--用户名--%>
                        <div class="form-group  col-lg-12">
                            <label for="userNameDto" class="col-sm-2 control-label"><spring:message
                                    code="staffManage.staffUserName"/></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="userNameDto" name="userNameDto">
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
                                       name="staffNameDto">
                            </div>
                        </div>
                        <%--手机号--%>
                        <div class="form-group  col-lg-12">
                            <label for="mobileDto" class="col-sm-2 control-label"><spring:message
                                    code="staffManage.staffMobile"/></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="mobileDto" name="mobileDto">
                            </div>
                        </div>

                        <div class="modal-footer" style="border-top: 0px solid #e5e5e5;text-align: -webkit-center;">
                            <%-- 确定 --%>
                            <button type="button" class="btn btn-primary"
                                    onclick="testStaff()">
                                <spring:message code="project.confirm"/></button>
                            <%-- 取消 --%>
                            <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message
                                    code="project.cancel"/></button>
                        </div>
                        <div class="clearfix"></div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script type="text/javascript">
    function addstaff() {
        $("#myModal1").modal({
            backdrop: false,
            show: true
        });
        $("#myModalLabel1").html("添加员工信息");
    }
    function updatestaff(staffId) {
        $.ajax({
            url: "../user/searchStaffById",
            type: "get",
            async: "false",
            data: {"staffId": staffId},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (result) {
                if (isNull(result.data.userStaffInfo)) {
                    $("#myModalLabel1").html("编辑员工信息");
                    $("#myModal1").modal({
                        backdrop: false,
                        show: true
                    });
                    $("#staffIdDto").val(result.data.userStaffInfo.staffIdDto);
                    $("#userNameDto").val(result.data.userStaffInfo.userNameDto);
                    $("#staffNameDto1").val(result.data.userStaffInfo.staffNameDto);
                    $("#mobileDto").val(result.data.userStaffInfo.mobileDto);
                }
            }
        });
    }
    /**
     * 判断是否null
     * @param data
     */
    function isNull(data) {
        if (data == "" || data == undefined || data == null) {
            return false;
        } else {
            return true;
        }
    }
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
    //                    valid = "false";
                        showError(login_userNameError, '*' + result.data.error);
//                        return false;
                    }
                }
            });
        });
    //获得焦点
    $("#userNameDto").focus(function () {
        hideError(login_userNameError);
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

    function testStaff() {
        var userName = $("#userNameDto").val();
       var flag=null;
        $.ajax({
            url: "../user/searchStaff",
            async: false,
            type: "get",
            data: {"userName": userName},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (result) {
                if (!result.data.success) {
                    flag=false;
                    showError(login_userNameError, '*' + result.data.error);
                } else {
                    flag =true;
                }
            }
        });
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
        if (!flag) {
            return false;
        }
//        alert($("#from").valid());
//        if ($("#login_userNameError").html() != "") {
//            return false;
//        }
        $("#from").submit();
    }

    //删除
    function delStaffUser(staffIdDto) {
        if (confirm("确定要删除吗?")) {
            window.location.href = "../user/daleteStaffById?staffId=" + staffIdDto;
        }
    }
</script>
</body>
</html>