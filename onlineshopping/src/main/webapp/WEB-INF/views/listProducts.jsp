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

						<script>
							window.categoryId = ''
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home &nbsp / </a></li>
							<li class="active">&nbsp All Products</li>
						</ol>
					</c:if>

					<c:if test="${userClickCategoryProducts == true}">
						<script>
							window.categoryId = '${category.id}'
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home &nbsp / </a></li>
							<li class="active">&nbsp Category &nbsp /</li>
							<li class="active">&nbsp ${category.name}</li>
						</ol>
					</c:if>

				</div>
			</div>

			<div class="row">

				<div class="col-xs-12">
					<table id="productListTable"
						class="table table-striped table-borderd">
						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty. Available</th>
								<th></th>
								
							</tr>
						</thead>
						
						
						<tfoot>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty. Available</th>
								<th></th>
								
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>


	</div>