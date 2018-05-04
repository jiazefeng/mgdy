<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/4/18
  Time: 14:46
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
</head>
<script type="text/javascript">
  function goSubmit(id){
    document.getElementById("rolesetId").value = id;
    document.getElementById("search_form").submit();
  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000200010000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
          <button data-toggle="modal1" class="btn btn-primary" onclick="addMenu()">添加二级菜单</button>
          <a  href="../menu/menuManage.html" class="btn btn-primary" >返回一级菜单</a>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">

    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <th>排序</th>
        <th>菜单Id</th>
        <th>菜单名称</th>
        <th>菜单描述</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${roleMenuDTOs}" var="menu" varStatus="vs">
        <tr>
          <td><b>${vs.count}</b></td>
          <td>${menu.roleMenuId}</td>
          <td>${menu.roleMenuName}</td>
          <td>${menu.roleMenuDesc}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/menu/menuSecManage?pageIndex={0}"/>
  </div>

  <div class="modal fade " id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
       aria-hidden="true">
    <div class="modal-dialog" style="text-align: -webkit-center">
      <div class="modal-content" style="width: 65%">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
          </button>
          <h4 class="modal-title" id="myModalLabel1">添加二级菜单</h4>
        </div>
        <div class="modal-body">
          <input type="hidden" id="parId" value="${parId}">
          <table>
            <tr class="col-lg-12" style="margin-bottom: 15px">
              <td>
                <label class="" for="roleMenuName">
                  菜单名称 ：
                </label>
              </td>
              <td>
                <input type="text" class="form-control" placeholder="" id="roleMenuName"
                       name="roleMenuName"
                       value="">
              </td>
            </tr>
            <tr class="col-lg-12">
              <td>
                <label class="" for="roleMenuDesc">
                  菜单描述 ：
                </label>
              </td>
              <td>
                <input type="text" class="form-control" placeholder="" id="roleMenuDesc"
                       name="roleMenuDesc"
                       value="">
              </td>
            </tr>
          </table>
        </div>
        <div class="modal-footer">
          <%-- 取消 --%>
          <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message
                  code="project.cancel"/></button>
          <%-- 确定 --%>
          <button type="button" class="btn btn-primary"
                  onclick="saveMenu()">
            <spring:message code="project.confirm"/></button>
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
<script>
    function addMenu() {
        $("#myModal1").modal({
            backdrop: false,
            show: true
        });
    }
    function saveMenu() {
        var parId=$("#parId").val();
        var roleMenuName = $("#roleMenuName").val();
        var roleMenuDesc = $("#roleMenuDesc").val();
        if(roleMenuName == null || roleMenuName == ""){
            alert("菜单名称为空");
            return;
        }
        if(roleMenuDesc == null || roleMenuDesc==""){
            alert("菜单描述为空");
            return;
        }
        window.location.href = "../menu/addSecMenu?roleMenuParId="+parId+"&roleMenuName=" + roleMenuName + "&roleMenuDesc=" + roleMenuDesc;
    }
</script>
</body>
</html>
