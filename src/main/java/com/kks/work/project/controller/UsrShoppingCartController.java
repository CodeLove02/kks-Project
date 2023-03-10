package com.kks.work.project.controller;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kks.work.project.service.ShoppingCartService;
import com.kks.work.project.util.ResultData;
import com.kks.work.project.util.Utility;
import com.kks.work.project.vo.Rq;
import com.kks.work.project.vo.ShoppingCart;

@Controller
public class UsrShoppingCartController {
	private ShoppingCartService ShoppingCartService;
	private Rq rq;
	
	
	

	@Autowired
	public UsrShoppingCartController(ShoppingCartService ShoppingCartService, Rq rq) {
		this.ShoppingCartService = ShoppingCartService;
		this.rq = rq;
		
	}

//액션 메서드
	//장바구니 페이지
	@RequestMapping("/usr/ShoppingCart/AddCart")
	@ResponseBody
	public String AddCart(int productId,int productPrice,String productCategory,int productCount,int memberid) {
		
		//유효성 검사
		if (Utility.isEmpty(productId)) {
			 return Utility.jsHistoryBack("아이디를 입력해주세요!");
		}
		
		if (Utility.isEmpty(productPrice)) {
			 return Utility.jsHistoryBack("아이디를 입력해주세요!");
		}
		
		if (Utility.isEmpty(productCategory)) {
			 return Utility.jsHistoryBack("아이디를 입력해주세요!");
		}
		
		if (Utility.isEmpty(productCount)) {
			 return Utility.jsHistoryBack("아이디를 입력해주세요!");
		}
		
		if (Utility.isEmpty(memberid)) {
			 return Utility.jsHistoryBack("아이디를 입력해주세요!");
		}
		
		 ShoppingCartService.insertShoppingCart(productId,productPrice,productCategory,productCount,memberid);
		
		 return Utility.jsReplace("장바구니에 상품을 담았습니다" , "ShowCart");
	} 	
	
	
	@RequestMapping("/usr/ShoppingCart/ShowCart")
	public String ShowCart(int loginedid, Model model) {
		
		int totalPriceSum = 0;
		
		List<ShoppingCart> showCart = ShoppingCartService.showCart(loginedid);
		
		List<Integer> totalPrices = new ArrayList<Integer>();
		
		//장바구니 가격*수량 
		for (ShoppingCart cart : showCart) {
			int productPrice = cart.getProductPrice();
			int productCount = cart.getProductCount();
			
			int totalPrice = productPrice * productCount;
			
			totalPrices.add(totalPrice);
		}
		
		//장바구니 총금액 합계
		for (int totalPrice : totalPrices) {
			totalPriceSum += totalPrice;
		}
		
		
		model.addAttribute("showCarts", showCart);
		model.addAttribute("totalPriceSum", totalPriceSum);
		
		return "/usr/shoppingCart/showCart";
	}
	
	@RequestMapping("/usr/ShoppingCart/delCart")
	@ResponseBody
	public ResultData<List<ShoppingCart>> delCart(int cartId) {
		
		ResultData<Integer> delCartRd = ShoppingCartService.delCart(cartId);
		
	/*	if (delCartRd.isFail()) {
			return Utility.jsReplace("해당 내역은 장바구니에 존재하지 않습니다", "/usr/home/main");
		}     */
		
		List<ShoppingCart> showCart = ShoppingCartService.showCart(rq.getLoginedMemberId());
		
		return ResultData.from("S-1", "장바구니 상품삭제", "장바구니 리스트", showCart );
		
	}
	
	
	
}