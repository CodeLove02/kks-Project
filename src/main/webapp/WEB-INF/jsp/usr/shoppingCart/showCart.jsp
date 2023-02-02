<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Member Login" />
<%@ include file="../common/head.jsp"%>




	

<div class="basketHead text-center" >
	<h1 class = "text-4xl text-gray-800" >장바구니</h1>
</div>
	
	
	
<div class = "shopWrap">
	<div class="table-box-shopCart">
		<table  class = "cartTable lg:w-9/12">
			<thead>
				<tr>
					
					<th>제품 카테고리</th>
					<th>제품명</th>
					<th>가격</th>
					<th>수량</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="showCart" items="${showCarts}">
					<div class = "renewTable">
						<tr>
							
								<td>${showCart.productCategory}</td>
								<td>${showCart.productName}</td>
								<td>${showCart.productPrice}</td>
								<td>${showCart.productCount}</td>
								<td><a href="#" id = "del"  onclick = "delCart(${showCart.cartId});">X</a></td>
														
						</tr>
					
					</div>
				</c:forEach>
					<tr style = "border-top : 1px solid  black">
						<th>총 결제금액</th>						
						<td>${totalPriceSum}</td>
					</tr>	
			</tbody>
		</table>
	</div>
</div>

<script>
function delCart(cartId) {
	//return	console.log(cartId);
	
	let addHtml = '<tr>';
	
		$.get('delCart', {
			carId : cartId,
			ajaxMode : 'Y'
		}, function(data){
			
			let showCart = new Array();
			showCart = data.data1;	
			
			
			for (int i=0; i<showCart.length(); i++) {
				
				addHtml += <td>\${showCart[i].productCategory}
							<td>${showCart[i].productName}
							<td>${showCart[i].productPrice}
							<td>${showCart[i].productCount}
				
			}
		
				 $('.renewTable').empty().html(addHtml);
	}, 'json');
}

}) 
	return;
	//return addHtml;
}	
</script>




<%@ include file="../common/foot.jsp"%>