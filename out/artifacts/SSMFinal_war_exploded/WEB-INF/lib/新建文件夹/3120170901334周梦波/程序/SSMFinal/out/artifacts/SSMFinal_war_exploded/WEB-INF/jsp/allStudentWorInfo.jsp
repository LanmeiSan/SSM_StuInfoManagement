<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/xml_rt" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 1329669644
  Date: 2020/6/7
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理系统</title>
    <%--    bootstrop美化--%>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        function getQueryVariable(variable) //获取url中lim的值
        {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i=0;i<vars.length;i++) {
                var pair = vars[i].split("=");
                if(pair[0] == variable){return pair[1];}
            }
            return(false);
        }
        const limVal = getQueryVariable("lim");
        if (limVal==="0") {
            $(function () {
                $("#addBtn").addClass("hidden");//增加隐藏
                //$("#selBtn").addClass("hidden");
                $('table tr').find('td:eq(5)').hide();
                $("#opr").hide();
            });
        }else if(limVal==="-1"){
            $(function () {
                $("#addBtn").addClass("hidden");//增加隐藏
                $("#exBtn").addClass("hidden");
                $("#selBtn").addClass("hidden");
                // $("#DeleteBtn").addClass("hidden");
                // $("#upStuWorName").attr("readonly","true");
                // $("#noName").removeClass("hidden");
                $('table tr').find('td:eq(5)').hide();
                $("#opr").hide();
            });
        }
        function update(obj) {/*点击修改按钮，给模态框加载信息并且弹出模态框*/
            var tds = $(obj).parent().parent().find('td');
            $("#upStuId").val(tds.eq(0).text());
            $("#upStuWorName").val(tds.eq(1).text());
            $("#upStuWorNum").val(tds.eq(2).text());
            $("#upStuWorPun").val(tds.eq(3).text());
            $("#upStuWorPs").val(tds.eq(4).text());
            $('#myModalUpdate').modal('show');
        }

        function del(obj) {/*点击删除按钮，给模态框加载信息并且弹出模态框*/
            var tds = $(obj).parent().parent().find('td');
            $('#delStuId').html("[" + tds.eq(0).text() + "]");
            $("#delStuId_text").attr("value",tds.eq(0).text());
            $('#myModalDelete').modal('show');
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <div class="row">
                    <div class="col-md-10 column">
                        <h1>
                            <small>学生管理系统</small>
                        </h1>
                    </div>
                    <div class="col-md-2 column">
                        <h3>
                            <small>用户:<a href="">${param.name}</a>.&nbsp;<a href="${pageContext.request.contextPath}">[注销]</a></small>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-2 column">
            <ul class="nav nav-pills nav-stacked">
                <li class="active" style="font-weight: bold;background-color: cornflowerblue"><a href="">
                    <p style="color: white">学生信息情况</p></a></li>
                <li><a href="${pageContext.request.contextPath}/student/allStudent?id=${param.id}&name=${param.name}&lim=${param.lim}">学生基本信息</a></li>
                <li><a href="${pageContext.request.contextPath}/studentFamilyInfo/allStudentFamilyInfo?id=${param.id}&name=${param.name}&lim=${param.lim}">学生家庭信息</a></li>
                <li><a href="${pageContext.request.contextPath}/studentDipInfo/allStudentDipInfo?id=${param.id}&name=${param.name}&lim=${param.lim}">学生获奖情况</a></li>
                <li><a href="${pageContext.request.contextPath}/studentWorInfo/allStudentWorInfo?id=${param.id}&name=${param.name}&lim=${param.lim}">学生考勤情况</a></li>
            </ul>
        </div>

        <div class="col-md-10 column">
<%--            <i class="glyphicon glyphicon-search form-control-feedback"></i>--%>
            <div class="row">
                <div class="col-md-6 column">
                    <a id="addBtn" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                        <span class="glyphicon glyphicon-plus-sign" style="font-size: 16.3px"></span>
                        添加信息
                    </a>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/studentWorInfo/allStudentWorInfo?id=${param.id}&name=${param.name}&lim=${param.lim}">
                        <span class="glyphicon glyphicon-refresh" style="font-size: 16.3px"></span>
                        刷新全部信息
                    </a>
                    <a id="exBtn" class="btn btn-primary" href="${pageContext.request.contextPath}/studentWorInfo/exToExcel">
                        <span class="glyphicon glyphicon-download-alt" style="font-size: 16.3px"></span>
                        导出信息
                    </a>
                    <span style="color: red;font-weight: bold">
                        ${param.error_Student}
                    </span>
                    <span style="color: red;font-weight: bold">
                        ${error_Student}
                    </span>
                </div>
                <div class="col-md-6 column">
<%--                    <span class="glyphicon glyphicon-search form-control-feedback" style="font-size: 18px"></span>--%>
                    <form id="selBtn" class="form-inline" action="${pageContext.request.contextPath}/studentWorInfo/queryStudentWorInfo?name=${param.name}&lim=${param.lim}" method="post" style="float: right">
                        <span style="color: red;font-weight: bold">
                            ${error_queryStudent}
                        </span>
                        <div class="form-group" style="font-size: 16px">
                            <div class="radio-inline">
                                <label><input type="radio" name="optRadio" value="1" checked>学号</label>
                            </div>
                            <div class="radio-inline">
                                <label><input type="radio" name="optRadio" value="0">姓名</label>
                            </div>
                        </div>
                        <input type="text" name="queryStuWorInfoById" class="form-control" placeholder="请输入要查询的学号或姓名" required oninvalid="setCustomValidity('请输入学号或姓名')" oninput="setCustomValidity('')">

                        <input type="submit"  value="查询" class="btn btn-primary">
                    </form>
                </div>
            </div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>缺勤次数</th>
                    <th>缺勤处分</th>
                    <th>缺勤备注</th>
                    <th id="opr">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="StuWorInfoAll" items="${list}">
                    <tr>
                        <td>${StuWorInfoAll.stuId}</td>
                        <td>${StuWorInfoAll.stuWorName}</td>
                        <td>${StuWorInfoAll.stuWorNum}</td>
                        <td>${StuWorInfoAll.stuWorPun}</td>
                        <td>${StuWorInfoAll.stuWorPs}</td>
                        <td>
                            <button class="btn btn-primary" type="button" id="updateBtn" onclick="update(this)">修改
                            </button>
                            <button class="btn btn-primary" type="button" id="DeleteBtn" onclick="del(this)">删除</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div style="text-align: center">
            当前第<a href="">${page.pageNum}</a>页,共<a href="">${page.pages }</a>页,总<a href="">${page.total}</a>条记录
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li>
                        <a href="${pageContext.request.contextPath}/studentWorInfo/allStudentWorInfo?name=${param.name}&lim=${param.lim}&pg=1">首页</a>
                    </li>

                    <c:if test="${page.hasPreviousPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/studentWorInfo/allStudentWorInfo?name=${param.name}&lim=${param.lim}&pg=${page.pageNum-1}">上一页</a>
                        </li>
                    </c:if>

                    <li>
                        <c:forEach items="${page.navigatepageNums }" var="page_Nums">

                        <c:if test="${page_Nums==page.pageNum }">
                    <li class="active">
                        <a href="#">${page_Nums}</a>
                    </li>
                    </c:if>

                    <c:if test="${page_Nums!=page.pageNum }">
                        <li>
                            <a href="${pageContext.request.contextPath}/studentWorInfo/allStudentWorInfo?name=${param.name}&lim=${param.lim}&pg=${page_Nums}">${page_Nums}</a>
                        </li>
                    </c:if>

                    </c:forEach>
                    </li>

                    <c:if test="${page.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/studentWorInfo/allStudentWorInfo?name=${param.name}&lim=${param.lim}&pg=${page.pageNum+1}" aria-label="Next">下一页</a>
                        </li>
                    </c:if>

                    <li>
                        <a href="${pageContext.request.contextPath}/studentWorInfo/allStudentWorInfo?name=${param.name}&lim=${param.lim}&pg=${page.pages}">末页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<%--添加信息模态框--%>
<div class="modal fade" tabindex="-1" role="dialog" id="myModal" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加学生获奖信息</h4>
            </div>
            <div class="modal-body">
                <%--                提交表单--%>
                <form role="form" action="${pageContext.request.contextPath}/studentWorInfo/addStudentWorInfo?name=${param.name}&lim=${param.lim}" method="post">
                    <div class="form-group">
                        <label for="addStuId">学号：</label>
                        <input type="text" class="form-control" id="addStuId" name="stuId" placeholder="请输入学号" required
                               oninvalid="setCustomValidity('请输入学号')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="addStuWorName">姓名：</label>
                        <input type="text" class="form-control" id="addStuWorName" name="stuWorName" placeholder="请输入姓名"
                               required oninvalid="setCustomValidity('请输入姓名')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="addStuWorNum">缺勤次数：</label>
                        <input type="text" class="form-control" id="addStuWorNum" name="stuWorNum" placeholder="请输入缺勤次数"
                               required oninvalid="setCustomValidity('请示输入缺勤次数')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="addStuWorPun">缺勤处分：</label>
                        <input type="text" class="form-control" id="addStuWorPun" name="stuWorPun" placeholder="请输入缺勤处分"
                               required oninvalid="setCustomValidity('请示输入缺勤处分')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="addStuWorPs">缺勤备注：</label><span style="color: red;font-weight: bold;font-size: 10px">*若为空，填写“无”</span></label>
                        <input type="text" class="form-control" id="addStuWorPs" name="stuWorPs" placeholder="请输入缺勤备注"
                               required oninvalid="setCustomValidity('请示输入缺勤备注')" oninput="setCustomValidity('')">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">添加</button>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--修改信息模态框--%>
<div class="modal fade" tabindex="-1" role="dialog" id="myModalUpdate" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改学生获奖信息</h4>
            </div>
            <div class="modal-body">
                <%--                提交表单--%>
                <form role="form" action="${pageContext.request.contextPath}/studentWorInfo/updateStudentWorInfo?name=${param.name}&lim=${param.lim}" method="post">
                    <div class="form-group">
                        <label for="upStuId">学号：<span style="color: red;font-weight: bold;font-size: 10px">*不可修改</span></label>
                        <input type="text" class="form-control" id="upStuId" name="stuId" placeholder="" readonly>
                    </div>
                    <div class="form-group">
                        <label for="upStuWorName">姓名：<span id="noName" style="color: red;font-weight: bold;font-size: 10px" class="hidden">*不可修改</span></label>
                        <input type="text" class="form-control" id="upStuWorName" name="stuWorName" placeholder="请示姓名"
                               required oninvalid="setCustomValidity('请示输入姓名')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="upStuWorNum">缺勤次数：</label>
                        <input type="text" class="form-control" id="upStuWorNum" name="stuWorNum" placeholder="请示输入缺勤次数"
                               required oninvalid="setCustomValidity('请示输入缺勤次数')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="upStuWorPun">缺勤处分：</label>
                        <input type="text" class="form-control" id="upStuWorPun" name="stuWorPun" placeholder="请示输入缺勤处分"
                               required oninvalid="setCustomValidity('请示输入缺勤处分')" oninput="setCustomValidity('')">
                    </div>
                    <div class="form-group">
                        <label for="upStuWorPs">缺勤备注：</label>
                        <input type="text" class="form-control" id="upStuWorPs" name="stuWorPs" placeholder="请示输入缺勤备注"
                               required oninvalid="setCustomValidity('请示输入缺勤备注')" oninput="setCustomValidity('')">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">修改</button>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--删除模态框--%>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" id="myModalDelete"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">删除学生出勤信息：</h4>
            </div>
            <br>
            <h4>&nbsp;&nbsp;<span class="glyphicon glyphicon-alert"></span>&nbsp;&nbsp;确定删除学生学号&nbsp;<span id="delStuId" style="color: red;font-weight: bold"></span>&nbsp;的出勤信息</h4>
            <br>
            <form role="form" action="${pageContext.request.contextPath}/studentWorInfo/deleteStudentWorInfo?name=${param.name}&lim=${param.lim}" method="post">
                <div class="form-group" style="display: none">
                    <input type="text" class="form-control" id="delStuId_text" name="delStuId" placeholder="" readonly value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">确定</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
