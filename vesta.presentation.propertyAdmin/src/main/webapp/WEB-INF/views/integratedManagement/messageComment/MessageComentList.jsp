<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script src="../static/property/js/propertyHousPay.js"></script>
</head>
<style type="text/css">
    .search_button {
        text-align: center;
    }

    .form_b {
        height: 5.5rem;
    }
</style>
<script>
    $(function () {
        $("#000300010000").addClass("active");
        $("#000300010000").parent().parent().addClass("in");
        $("#000300010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000300010000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <%--<form id="form" class="form-horizontal" action="../news/getNewsList.html">--%>
                <%--&lt;%&ndash; 新闻标题搜索 &ndash;%&gt;--%>
                <%--<div class="form-group  col-lg-12">--%>
                <%--&lt;%&ndash;<label for="newsTitle" class="col-sm-2 control-label">新闻搜索</label>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="col-sm-2">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="text" class="form-control" placeholder="" id="newsTitle" name="newsTitle"&ndash;%&gt;--%>
                <%--&lt;%&ndash;value="${newsInfo.newsTitle}">&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--<div class="col-sm-5">--%>
                <%--&lt;%&ndash;<button type="submit" class="btn btn-primary" for="propertySearch"><spring:message&ndash;%&gt;--%>
                <%--&lt;%&ndash;code="propertyCompany.propertySearch"/></button>&ndash;%&gt;--%>
                <%--<a href="../house/toAddHouse.html" class="btn btn-primary">新增</a>--%>
                <%--</div>--%>

                <%--</div>--%>
                <%--</form>--%>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td>序号</td>
                <th>用户名称</th>
                <th>联系方式</th>
                <th>星级</th>
                <th>描述</th>
                <th>回复内容</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${messageCommentList}" var="message" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td>${message.mcUserName}</td>
                    <td>${message.mcContactWay}</td>
                    <c:if test="${message.mcGrade ne '0'}"><td>${message.mcGrade}星</td></c:if>
                    <c:if test="${message.mcGrade eq '0'}"><td>0星</td></c:if>
                    <td>${message.mcDescribe}</td>
                    <td>${message.reply}</td>
                    <td class="last">
                        <a href="javascript:void(0);" onclick="toEdit('${message.mcId}')" id="toEdit" class="a3">编辑</a>
                        <a href="javascript:void(0);" onclick="toDelete('${homessageuse.mcId}')" id="toDelete"
                           class="a3">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/leave/getMessageCommentInfoList.html?pageIndex={0}"/>
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
                    <form class="form-horizontal" id="from" action="../leave/updateMessageComment" method="post"
                          style="width: 75%">
                        <input type="hidden" id="mcId" name="mcId">
                        <%--用户名--%>
                        <div class="form-group  col-lg-12">
                            <label for="mcUserName" class="col-sm-2 control-label">用户名称</label>
                            <div class="col-sm-9">
                                <span id="mcUserName"></span>
                            </div>
                        </div>
                        <%--联系方式--%>
                        <div class="form-group  col-lg-12">
                            <label for="mcContactWay" class="col-sm-2 control-label">联系方式</label>
                            <div class="col-sm-9">
                                <span id="mcContactWay"></span>
                            </div>
                        </div>
                        <%--等级--%>
                        <div class="form-group  col-lg-12">
                            <label for="mcGrade" class="col-sm-2 control-label">等级</label>
                            <div class="col-sm-9">
                                <span id="mcGrade"></span>
                            </div>
                        </div>
                        <%--描述--%>
                        <div class="form-group  col-lg-12">
                            <label for="mcDescribe" class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-9">
                                <span id="mcDescribe"></span>
                            </div>
                        </div>
                        <div class="form-group  col-lg-12">
                            <label for="replyInfo" class="col-sm-2 control-label">回复内容</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" id="replyInfo" name="reply"></textarea>
                            </div>
                        </div>
                        <div class="modal-footer" style="border-top: 0px solid #e5e5e5;text-align: -webkit-center;">
                            <%-- 确定 --%>
                            <button type="button" class="btn btn-primary"
                                    onclick="updateInfo()">
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

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
    $(".form_YM").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm',
        autoclose: true,
        todayBtn: true,
        startView: 'year',
        minView: 'year',
        maxView: 'decade'
    });
</script>
<script>
    //修改
    function toEdit(id) {
        $.ajax({
            url: "../leave/getMessageCommentInfoById",
            type: "get",
            async: "false",
            data: {"mcId": id},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (result) {
                if (isNull(result.data.messageCommentInfo)) {
                    $("#myModal1").modal({
                        backdrop: false,
                        show: true
                    });
                    $("#mcId").val(result.data.messageCommentInfo.mcId);
                    $("#mcUserName").html(result.data.messageCommentInfo.mcUserName);
                    $("#mcContactWay").html(result.data.messageCommentInfo.mcContactWay);
                    $("#mcGrade").html( result.data.messageCommentInfo.mcGrade + "星");
                    $("#mcDescribe").html(result.data.messageCommentInfo.mcDescribe);
                    $("#replyInfo").val(result.data.messageCommentInfo.reply);
                }
            }
        });
    }
    function updateInfo() {
        var reply1 = $("#replyInfo").val();
        if (reply1 == null || reply1 == "") {
            alert("回复内容为空");
            return;
        }
        $("#from").submit();
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
    //删除
    function toDelete(id) {
        if (confirm("确定要删除吗?")) {
            window.location.href = "../leave/deleteInfoById?mcId=" + id;
        }
    }
</script>
</body>
</html>