/**
 * Created by lin on 2016-05-27.
 */
/**
 * 
 * @param {Object} $
 */
(function($) {

	var My = {};
	
	// -----------------------ajax---------------------------------

	/**
	 * ajax简单封装，调用 $.My.ajax({url:'www.baidu.com'});
	 * 
	 * @param {Object}
	 *            options
	 */
	My.ajax = function(options) {

		var opts = $.extend({}, My.ajax.defaults, options);

		$.ajax({
			url : opts.url,
			method : opts.method,
			dataType : opts.dataType,
			success : opts.success,
			error : opts.error
		});

	}

	My.ajax.defaults = {

		method : 'Get',
		dataType : 'json',
		async : true,
		cache : true,
		data : [],
		url : '',
		success : function(data, textStatus) {
			if (data.code == '200') {
				$.alert({
				    title: '提示!',
				    content: '操作成功!'
				});
			} else {
				$.alert({
				    title: '提示!',
				    content: '操作失败!'
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert({
			    title: '提示!',
			    content: '操作失败!'
			});
		}

	}

	// -----------------------dataTable---------------------------------

	/**
	 * 封装dataTable.js
	 * 
	 * @param {Object}
	 *            options
	 */
	My.dataTable = function(options) {

		var opts = $.extend({}, My.dataTable.defaults, options);
		
		/*console.log('ajax:'+ typeof opts.ajax);
		console.log('columns:'+ typeof opts.columns);
		console.log('columnDefs:'+ typeof opts.columnDefs);*/
		
		/*if(opts.ajax){
			throw new Error('Object ajax required');
		}
		if(opts.columns){
			throw new Error('Object columns required');
		}*/

		return $('#' + opts.domId).DataTable({
			"serverSide" : opts.serverSide,
			"searching" : opts.searching,
			"scrollX" : opts.scrollX,
			"info" : opts.info,
			"paging" : opts.paging,
			"pagingType" : opts.pagingType,
			"ajax" : opts.ajax,
			"columnDefs" : opts.columnDefs,
			"order" : opts.order,
			"columns" : opts.columns,
			"language" : {
				"lengthMenu" : "_MENU_ 条记录每页",
				"zeroRecords" : "没有找到记录",
				"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
				"infoEmpty" : "无记录",
				"infoFiltered" : "(从 _MAX_ 条记录过滤)",
				"paginate" : {
					"first" : "第一页",
					"previous" : "上一页",
					"next" : "下一页",
					"last" : "最后一页"
				}
			}
		});

	}

	My.dataTable.defaults = {
		"domId" : "dataTable",// table dom id
		"serverSide" : true,// 由服务器处理分页
		"searching" : false,// 隐藏搜索框
		"scrollX" : true,// 水平滚动条
		"info" : false,// 分页描述信息
		"paging" : true,// 分页
		"pagingType" : "full_numbers",
		"order" : [ [ 1, "desc" ] ],
		"ajax" : function() {
		},
		"columnDefs" : function() {
		},// 自定义列
		"columns" : []// 列定义
	}
	
	
	
	// -----------------------formValidation---------------------------------
	
	My.formValidation = function(options){
		
		this.validators = {
			notEmpty:{
				 message: '不能为空'
			}
		};
		
		var fields = function(){};
		
		var opts = $.extend({}, My.formValidation.defaults, options);
		
		//{name:{required:true,length:1,},name1:{lenth:[2,3]}}
		
		var columns = opts.fields;
		
		
		var tmp = "";
		/*try
		{
			for (var attr in columns) {
				fields.prototype[attr] = function(){};//随便定义
				fields.prototype[attr].message = '无效的值'; 
				fields.prototype[attr].validators = function(){};
				for(var i in columns[attr]){
					if(i==='required' && columns[attr][i]){
						fields.prototype[attr].validators.notEmpty = {message:'不能为空'};
					}
					if(i==='length' && typeof i == 'Array'){
						if(columns[attr][i].length==2){
							fields.prototype[attr].validators.stringLength = {
								min :  columns[attr][i][0],
								max :  columns[attr][i][1],
								message : '字符长度必须大于'+columns[attr][i][0]+'小于'+columns[attr][i][1]
							};
						}else{
							fields.prototype[attr].validators.stringLength = {
								max :  columns[attr][i][0],
								message : '字符长度必须小于'+columns[attr][i][0]
							};
						}
					}
					
					
				}
				console.log('fields.prototype[attr]---'+fields.prototype[attr]);
			}
		}catch(e){
			alert(e);
			throw new Error('property fields error');
		}*/
		
		
		
		
		return $('#'+opts.form).formValidation({
				form : opts.form,
				message :opts.message,
				icon: opts.icon,
		        fields: columns
		});
		
	}
	
	
	
	
	My.formValidation.defaults = {
	    form : 'defaultForm',
		message :'无效的值',
		icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:function(){}
	}
	
	// -----------------------dataTable custom column---------------------------------
	
	My.opColumn = function(options){
		
		var _html = "";
		if(options.action.length != options.url.length){
			throw new Error('property opColumn defined error.');
		}
		for(var i=0;i<options.action.length;i++){
			if(options.action[i] == 'view'){
				_html += '<a class="tn btn-primary btn-xs" href="'+options.url[i]+'">';
				_html += '<i class="fa fa-folder"></i>查看</a>&nbsp;';
			}
			if(options.action[i] == 'edit'){
				_html += '<a class="btn btn-info btn-xs" href="'+options.url[i]+'">';
				_html += '<i class="fa fa-pencil"></i>编辑</a>&nbsp;';
			}
			if(options.action[i] == 'delete'){
				var delUrl = options.url[i];
				_html += '<a class="btn btn-danger btn-xs" href="#" onclick="$.My.commonDel(\''+delUrl+'\');">';
				_html += '<i class="fa fa-trash-o"></i>删除</a>&nbsp;';
			}
		}
		
		
		
		return _html;
		
	}
	
	
	//---------------------------window----------------------------
	
	/**
	 * alert
	 */
	My.alert=function(options){
		
		return $.alert({
		    title: '提示',
		    content: options.content,
		    confirmButton:'确定',
		    cancelButton:'取消',
		    confirmButtonClass: 'btn-info',
		    cancelButtonClass: 'btn-danger'
		});
		
	}
	
	/**
	 * confirm
	 */
	My.confirm = function(options){
		
	 return	$.confirm({
			    title: '提示',
			    content: options.content || '确认进行删除操作吗?',
			    confirmButton:'确定',
			    cancelButton:'取消',
			    confirmButtonClass: 'btn-info',
			    cancelButtonClass: 'btn-danger',
			    confirm: options.confirm,
			    cancel: options.cancel
	 		});
	
	}
	
	
	My.commonDel = function(url){
		
		My.confirm({
			content:'确认进行删除操作吗?',
			confirm:function(){
				$.ajax({
					url : url,
					method : 'post',
					dataType :'json',
					success : function(data, textStatus) {
						if (data.rtnCode == 'ok') {
							My.alert({content:'操作成功'});
							My.freshDataTable();
						} else {
							My.alert({content:'操作失败'});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						My.alert({content:'操作失败'});
					}
				});
			},
			cancel:function(){}
		});
	}
	
	/**
	 * 空方法用于删除后回调
	 */
	My.freshDataTable = function(){}
	
	
	$.My = My;
	
})(jQuery);

