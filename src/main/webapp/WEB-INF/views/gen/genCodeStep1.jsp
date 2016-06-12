<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>代码生成</title>
<%@include file="/WEB-INF/views/common/include.jsp"%>
</head>
<body style="background: #F7F7F7">
<div class="row" style="height:300px;">
	<form class="form-horizontal form-label-left">
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12"
				for="first-name">表名<span class="required">*</span>
			</label>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<select class="select2_multiple form-control" multiple="multiple">
					<c:forEach items="${tables}" var="table">
						<option value="${table}">${table}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</form>
</div>
	
<script>
$(function(){
	 $(".select2_multiple").select2({
         maximumSelectionLength: 1,
         placeholder: "暂只支持最大量1个",
         allowClear: true,
         width:300
       });
});
</script>
</body>
</html>