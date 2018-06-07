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
        $("#000700010000").addClass("active");
        $("#000700010000").parent().parent().addClass("in");
        $("#000700010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000700010000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <%--<div class="form-body form_b">--%>
            <%--<form id="form" class="form-horizontal" action="../news/getNewsList.html">--%>
            <%--&lt;%&ndash; 新闻标题搜索 &ndash;%&gt;--%>
            <%--<div class="form-group  col-lg-12">--%>
            <%--<label for="newsTitle" class="col-sm-2 control-label">新闻搜索</label>--%>
            <%--<div class="col-sm-2">--%>
            <%--<input type="text" class="form-control" placeholder="" id="newsTitle" name="newsTitle"--%>
            <%--value="${newsInfo.newsTitle}">--%>
            <%--</div>--%>
            <%--<div class="col-sm-5">--%>
            <%--<button type="submit" class="btn btn-primary" for="propertySearch"><spring:message--%>
            <%--code="propertyCompany.propertySearch"/></button>--%>
            <%--<a href="../news/toAddNews.html" class="btn btn-primary">新增</a>--%>
            <%--</div>--%>

            <%--</div>--%>
            <%--</form>--%>
            <%--</div>--%>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td>序号</td>
                <th>标题</th>
                <th>发布时间</th>
                <th>发布人</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${videoList}" var="video" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td>${video.text}</td>
                    <td>${video.create_time}</td>
                    <td>${video.name}</td>
                    <td class="last">
                        <a href="javascript:void(0);" onclick="toEdit('${video.video_uri}')" id="toEdit"
                           class="a3">查看</a>
                        <a href="javascript:void(0);" onclick="toTop('${video.id}','${video.status}')" id="toTop"
                           class="a3">
                            <c:if test="${video.status == 0}">发布
                            </c:if>
                            <c:if test="${video.status == 1}">取消发布
                            </c:if></a>

                            <%--<a href="javascript:void(0);" onclick="toDelete('${news.newsId}')" id="toDelete"--%>
                            <%--class="a3">删除</a>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/video/getVideoList.html?pageIndex={0}"/>
    </div>

</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <%--<div class="modal-header">--%>
        <%--<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span--%>
        <%--class="sr-only">Close</span></button>--%>
        <%--</div>--%>
        <div class="container" style="margin-top: 15px;">
            <div style="margin-top: 20px; line-height: 2; text-align: left">
                <video id="videoId" src=""
                       controls="controls">没有相关视频
                </video>
            </div>
        </div>
    </div>
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
    //新增与修改
    function toEdit(videoUrl) {
//        $("#myModalLabel2").html("编辑设备信息");
        console.log(videoUrl)
        document.getElementById("videoId").src = videoUrl;
        $("#myModal").modal({
            backdrop: true,
            show: true
        });
//        window.location.href = "../news/toModifyNews.html?newsId=" + id;
    }
    //置顶
    function toTop(id, slideShow) {
        if (slideShow == '0') {
            if (confirm("确定该视频合格？确定要发布该视频？")) {
                $.ajax({
                    type: "GET",
                    url: "../video/toReleaseOrCancel?videoId=" + id + "&status=1",
                    cache: false,
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        if (data.error == 0) {
                            alert("发布成功！");
                        } else {
                            alert("发布失败！");
                        }
                    }
                });
                window.location.href = "../video/getVideoList.html";
                return;
            }
        }
        if (slideShow == '1') {
            if (confirm("确定要取消发布此视频？")) {
                $.ajax({
                    type: "GET",
                    url: "../video/toReleaseOrCancel?videoId=" + id + "&status=0",
                    cache: false,
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        if (data.error == 0) {
                            alert("取消成功！");
                        } else {
                            alert("取消失败！");
                        }
                    }
                });
                window.location.href = "../video/getVideoList.html";
            }
        }
    }
</script>
</body>
</html>