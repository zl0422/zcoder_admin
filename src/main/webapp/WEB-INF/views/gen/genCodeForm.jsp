<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>代码生成</title>
    <%@include file="/WEB-INF/views/common/include.jsp" %>
</head>
<body style="background: #F7F7F7">
<sys:message type="${type}" content="${content}"></sys:message>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>
                    <i class="glyphicon glyphicon-th-large"></i>代码生成
                </h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    <li><a class="close-link"><i class="fa fa-close"></i></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <form class="form-horizontal form-label-left" id="frm" action="${ctx}/genCode/save" method="post">

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">表名</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <select class="select2_multiple form-control" id="tableName" multiple="multiple">
                                <c:forEach items="${tables}" var="table">
                                    <option value="${table}">${table}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">类名</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input type="text" class="form-control" id="className" placeholder="className">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">模块名</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input type="text" class="form-control" id="packageName" placeholder="packageName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">子模块名</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                            <input type="text" class="form-control" id="subPackageName" placeholder="subPackageName">
                        </div>
                    </div>


                    <table id="dataTable" class="table table-striped table-bordered"
                           cellspacing="0" width="100%" style="margin-left: 10px;margin-right: 10px;">
                        <thead>
                        <tr>
                            <th>列名</th>
                            <th>数据库类型</th>
                            <th>Java类型</th>
                            <th>主键</th>
                            <th>可空</th>
                            <th>注释</th>
                            <th>查询条件</th>
                            <th>查询方式</th>
                            <th>列表</th>
                            <th>表单类型</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                        <!-- tbody是必须的 -->
                    </table>
                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
                            <button id="send" type="submit" class="btn btn-success">保存</button>
                            <button type="button" class="btn btn-primary">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/common/include-js.jsp" %>
<%@include file="/WEB-INF/views/common/include-datatables.jsp" %>
<script>

    $(function () {
        $(".select2_multiple").select2({
            maximumSelectionLength: 1,
            placeholder: "暂只支持最大量1个",
            allowClear: true
        });

        //默认不必作为条件的字段
        var exclude = ['id','create_by','create_date','update_by','update_date','remarks','del_flag'];

        var table = $.My.dataTable({
            "ajax": {
                "url": "${ctx}/genCode/findColumn",
                "data": function (d) {
                    d.tableName = $.trim($('.select2_multiple').val());
                }
            },
            "ordering": false,
            "scrollX": true,
            "info": false,
            "paging": false,
            "columns": [{
                "data": "field"
            }, {
                "data": "jdbcType"
            }, {
                "data": "javaType"
            }, {
                "data": "isKey"
            }, {
                "data": "isNull"
            }, {
                "data": "comment"
            }, {
                "data": "isQuery",
                "render": function (data, type, row) {

                    if (type === 'display') {
                        var flag = false;
                        for(var val in exclude){
                            if($.trim(row.field) == exclude[val]){
                                flag = true;
                                break;
                            }
                        }
                        if(flag){
                            return '<input type="checkbox"  name="isQuery_' + row.field + '" class="editor-active">';
                        }else {
                            return '<input type="checkbox" checked="true" name="isQuery_' + row.field + '" class="editor-active">';
                        }
                    }
                    return data;
                },
                "className": "dt-body-center"
            }, {
                "data": "queryMethod",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        var _html = '<select id="queryMethod" name="queryMethod_' + row.field + '">';
                        _html += '<option value="=">等于</option>';
                        _html += '<option value="like">模糊</option>';
                        _html += '<option value="between">between</option>';
                        _html += '</select>';
                        return _html;
                    }
                    return data;
                },
                "className": "dt-body-center"
            }, {
                "data": "isList",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        var flag = false;
                        for(var val in exclude){
                            if($.trim(row.field) == exclude[val]){
                                flag = true;
                                break;
                            }
                        }
                        if(flag){
                            return '<input type="checkbox"  name="isList_' + row.field + '" class="editor-active">';
                        }else {
                            return '<input type="checkbox" checked="true" name="isList_' + row.field + '" class="editor-active">';
                        }
                    }
                    return data;
                },
                "className": "dt-body-center"
            }, {
                "data": "formType",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        var _html = '<select id="formType" name="formType_' + row.field + '">';
                        _html += '<option value="text">文本框</option>';
                        _html += '<option value="date">日期</option>';
                        _html += '<option value="select">下拉列表</option>';
                        _html += '<option value="radio">单选</option>';
                        _html += '<option value="checkBox">复选</option>';
                        _html += '<option value="textarea">文本区</option>';
                        _html += '</select>';
                        return _html;
                    }
                    return data;
                },
                "className": "dt-body-center"
            }]

        });

        //隐藏分页下拉列表
        $('#dataTable_length').css('display', 'none');

        $('.select2_multiple').bind('change', function () {
            table.draw();
        });
    });

    $('form').submit(function (e) {
        e.preventDefault();
        var submit = true;
        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
            submit = false;
        }
        if (submit)
            this.submit();
        return false;
    });

</script>
</body>
</html>