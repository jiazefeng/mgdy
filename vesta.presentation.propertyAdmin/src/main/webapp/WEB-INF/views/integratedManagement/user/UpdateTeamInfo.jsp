<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL
    </script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>

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

    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <script>
        $(function () {
            $("#000600020000").addClass("active");
            $("#000600020000").parent().parent().addClass("in");
            $("#000600020000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <%-- FileInput --%>
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/css/fileinput.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.1/js/fileinput_locale_zh.js"></script>
    <!--//Metis Menu -->
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
        }
    </style>
    <STYLE type=text/css>
        input.error {
            border: 1px solid red;
        }

        /*.file-input{*/
        /*width: 900px;*/
        /*}*/
    </STYLE>

</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000600020000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <h3 class="text-center">编辑团队信息</h3>
                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="form" action="../user/updateTeamInfo" method="post"
                          enctype="MULTIPART/FORM-DATA">
                        <input type="hidden" name="teamId" value="${teamInfo.teamId}">
                        <%--房屋价格--%>
                        <div class="form-group col-lg-9">
                            <label class="col-sm-2 control-label" for="teamName">团队名称</label>
                            <div class="col-sm-9">
                                <input type="text" id="teamName" class="form-control" name="teamName" value="${teamInfo.teamName}">
                            </div>
                        </div>
                        <div class="form-group col-lg-9">
                            <label class="col-sm-2 control-label" for="teamTitle">团队标题</label>
                            <div class="col-sm-9">
                                <input type="text" id="teamTitle" class="form-control" name="teamTitle" value="${teamInfo.teamTitle}">
                            </div>
                        </div>
                        <%-- 首页展示图 --%>
                        <div class="form-group col-lg-9">
                            <label class="col-sm-2 control-label">团队图</label>
                            <div class="col-sm-9">
                                <input id="teamPageimgFile" name="teamPageimgFile" type="file" multiple/>
                                <p style="color: red">
                                    <span>您将上传首页展示图，建议宽高比为700x400！</span>
                                </p>
                                <div class="col-lg-3" style="margin-top: 5px">
                                    <img src="${teamInfo.teamImageUrl}" alt="" class="img-thumbnail" style="width: auto">
                                </div>
                            </div>
                        </div>
                        <%--房屋描述--%>
                        <div class="form-group col-lg-9">
                            <label class="col-sm-2 control-label" for="teamDescribe">团队描述</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" id="teamDescribe" name="teamDescribe">${teamInfo.teamDescribe}</textarea>
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <button type="button" class="btn btn-primary" onclick="validate()">确定</button>
                        <a href="../user/getTeamList.html" class="btn btn-primary">取消</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
<!-- 校验 -->
<!--日期控件-->
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
</script>
<%--fileinput图片上传控件--%>
<script>
    $(function () {
        initFileInput("teamPageimgFile", "");
    });
    //初始化fileinput控件（第一次初始化）
    function initFileInput(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png', 'jpeg'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
    }
</script>
<script>
    function validate() {
        var teamName = $("#teamName").val();
        var teamTitle = $("#teamTitle").val();
        var teamDescribe = $("#teamDescribe").val();
        if (teamName == null || teamName == "") {
            alert("团队名称为空");
            return;
        }
        if (teamTitle == null || teamTitle == "") {
            alert("团队标题为空");
            return;
        }
        if (teamDescribe == null || teamDescribe == "") {
            alert("团队描述为空");
            return;
        }
        //校验
        if ($("#form").validate()) {
            $("#form").submit();
        }
    }
</script>
</body>
</html>