<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body style="background: #F7F7F7">
<div class="row">
        <form:form action="" modelAttribute="table" method="post" id="frm" Class="form-horizontal form-label-left">
            <div class="item form-group">
                <label for="name" class="control-label col-md-3 col-sm-3 col-xs-12">表名</label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <form:input path="name" readonly="true"
                                class="form-control col-md-7 col-xs-12"/>
                </div>
            </div>
            <div class="item form-group">
                <label for="className"
                       class="control-label col-md-3 col-sm-3 col-xs-12">类名</label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <form:input path="className" class="form-control col-md-7 col-xs-12"/>
                </div>
            </div>
            <div class="item form-group">
                <label for="comments"
                       class="control-label col-md-3 col-sm-3 col-xs-12">注释</label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <form:input path="comments" class="form-control col-md-7 col-xs-12"/>
                </div>
            </div>
            <table id="dataTable" class="table table-striped table-bordered"
                   cellspacing="0" width="100%" style="margin-left: 30px;">
                <thead>
                <tr>
                    <th>列名</th>
                    <th>数据库类型</th>
                    <th>主键</th>
                    <th>可空</th>
                    <th>注释</th>
                    <th>条件</th>
                </tr>
                </thead>
                <tbody></tbody>
                <!-- tbody是必须的 -->
            </table>
        </form:form>
</div>
<%@include file="/WEB-INF/views/common/include-datatables.jsp"%>
<script>
    $(document).ready(
            function () {




            });
</script>