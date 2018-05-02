$(function(){
	switch(menu){
	
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Home':
		$('#home').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProtucts').addClass('active');
		break;
	default:
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
	}
	
	// ------------------------------------------------------------------
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length >0 ){
		$(document).ajaxSend(function(e, xhr, options){
			xhr.setRequestHeader(header, token);
		});
	} 
	
	
	
	// ------------------------------------------------------------------
	var $table = $('#productListTable');
	
	if($table.length){

		var jsonUrl = '';
		if(window.categoryId == ''){
			jsonUrl = window.contextRoot+'/json/data/all/products';
		}else{
			jsonUrl = window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
		}
		
		$table.DataTable({
			lengthMenu: [[3,5,10,-1],['3 Records','5 Records','10 Records','All']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
					{
						data: 'code',
						mRender: function(data, type, row){
							return '<img src = "'+window.contextRoot+'resource/images'+data+'.jpg" class="dataTableImg"/>';
						}
					},	
					{
						data: 'name'
					},
					{
						data: 'brand'
					},
					{
						data: 'unitPrice',
						mRender: function(data,type,row){
							return '&#8377; '+data;
						}
					},
					{
						data: 'quantity',
						mRender: function(data, type, row){
							if( data<1 ){
								return '<span style="color:red">Out of Stock</span>';
							}
							return data;
						}
					},
					{
						data: 'id',
						bSortable: false,
						mRender: function(data, type, row){
							var str = '';
							str += '<a href="'+window.contextRoot+'/show'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
							
							if(userRole == 'ADMIN'){
								str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
							}else{
								if( row.quantity < 1 ){
									str += '<a href="javascript:void(0)" class="btn btn-success disable"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
								}else{
									str += '<a href="'+window.contextRoot+'/cart/add'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
								}		
							}
							return str;
						}
						
					}
					]
		});
	}
	
	
	// -----------------------------------------------------------
	var $alert = $('.alert');
	if($alert.length){
		setTimeout(function(){
			$alert.fadeout('slow');
		}, 3000)
	}
	
	
	// ----------------------------------------------------------------
	$('.switch input[type = "checkbox"]').on('change', function(){
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked)? 'You want to activate the product?' : 'You want to deactivate the product?';
		var value = checkbox.prop('value');
		
		bootbox.confirm({
			size: 'medium',
			title: 'Product Activation & Deactivation',
			message: dMsg,
			callback: function(confirmed){
				if(confirmed){
					console.log(value),
					bootbox.alert({
						size: 'medium',
						title: 'Information',
						message: 'You are going to perform operation on product ' + value
					});
				}else{
					checkbox.prop('checked', !checked);
				}
			}
		});
	});
	
	
	// -------------------------------------------------------------------
var $adminProductsTable = $('#adminProductsTable');
	
	if($adminProductsTable.length){

		var jsonUrl = window.contextPoot + '/json/data/admin/all/products';
		
		$adminProductsTable.DataTable({
			lengthMenu: [[10,30,50,-1],['10 Records','30 Records','50 Records','All']],
			pageLength: 30,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
					{
						data: 'id'
					},
					{
						data: 'code',
						mRender: function(data, type, row){
							return '<img src = "'+window.contextRoot+'resource/images'+data+'.jpg" class="adminDataTableImg"/>';
						}
					},	
					{
						data: 'name'
					},
					{
						data: 'brand'
					},
					{
						data: 'quantity',
						mRender: function(data, type, row){
							if( data<1 ){
								return '<span style="color:red">Out of Stock</span>';
							}
							return data;
						}
					},
					{
						data: 'unitPrice',
						mRender: function(data,type,row){
							return '&#8377; '+data;
						}
					},
					{
						data: 'active',
						bSortable: false,
						mRender: function(data, type, row){
							var str = '';
							str += '<label class="switch">';
							if(data){
								str += '<input type="checkbox" checked="checked" value="'+row.id+'" />';
							}else{
								str += '<input type="checkbox" value="'+row.id+'" />';
							}
							str += '<div class="slider"></div></label>';
							return str;
						}
						
					},
					{
						data: 'id',
						bSortable: false,
						mRender: function(data, type, row){
							var str = '';
							str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">'; 
							str += '<span class="glyphicon glyphicon-pencil"></span></a>';
							return str;
						}
						
					}
					],
				initComplete: function(){
					var api = this.api();
					api.$('.switch input[type = "checkbox"]').on('change', function(){
						var checkbox = $(this);
						var checked = checkbox.prop('checked');
						var dMsg = (checked)? 'You want to activate the product?' : 'You want to deactivate the product?';
						var value = checkbox.prop('value');
						
						bootbox.confirm({
							size: 'medium',
							title: 'Product Activation & Deactivation',
							message: dMsg,
							callback: function(confirmed){
								if(confirmed){
									console.log(value);
									
									var activationUrl = window.contextRoot + '/manage/product/'+value+'/activation';
									$.post(activationUrl, function(data){
										bootbox.alert({
											size: 'medium',
											title: 'Information',
											message: 'You are going to perform operation on product ' + value
										});
									});
								}else{
									checkbox.prop('checked', !checked);
								}
							}
						});
					});
				}
		});
	}
	
	
	// --------------------------------------------------------------
	var $categoryForm = $('#categoryForm');
	if($categoryForm.length){
		$categoryForm.validate({
			rule : {
				name:{
					required: true,
					minlength: 2
				},
				description:{
					required: true
				}
			},
			messages:{
				name:{
					required: 'Please add the category name!',
					minlength: 'The category name should be more than one character'
				},
				description:{
					required: 'Please input a description for this category!'
				}
			},
			errorElement:'em',
			errorPlacement: function(error, element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		});
	}
	
	
	// -----------------------------------------------------
	
	var $loginForm = $('#loginForm');
	if($loginForm.length){
		$loginForm.validate({
			rule : {
				username:{
					required: true,
					email: true
				},
				password:{
					required: true
				}
			},
			messages:{
				username:{
					required: 'Please enter the email!',
					email: 'Please enter valid email address!'
				},
				password:{
					required: 'Please input the password!'
				}
			},
			errorElement:'em',
			errorPlacement: function(error, element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		});
	}
	
	
	// -----------------------------------------------------
	
	
	
	
	
	
});
