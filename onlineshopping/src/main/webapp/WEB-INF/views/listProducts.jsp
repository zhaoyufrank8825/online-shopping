<div class="container">
	<div class="row">

		<!-- Would be to display sidebar -->
		<div class="col-md-3">
			<%@ include file="./shared/sidebar.jsp"%>
		</div>

		<!-- Would be to display actual products -->
		<div class="col-md-9">
			<div class="row">
				<div class="col-lg-12">

					<c:if test="${userClickAllProducts == true}">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home &nbsp /  </a></li>
							<li class="active"> &nbsp All Products</li>
						</ol>
					</c:if>
					
					<c:if test="${userClickCategoryProducts == true}">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home &nbsp / </a></li>
							<li class="active"> &nbsp Category &nbsp / </li>
							<li class="active"> &nbsp ${category.name}</li>
						</ol>
					</c:if>
					
				</div>
			</div>
		</div>


	</div>