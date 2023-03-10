package com.kks.work.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kks.work.project.repository.ShoppingCartRepository;
import com.kks.work.project.util.ResultData;
import com.kks.work.project.vo.ShoppingCart;


@Service
public class ShoppingCartService {
	private AttrService attrService;
	private ShoppingCartRepository ShoppingCartRepository;
	
	@Autowired
	public ShoppingCartService(ShoppingCartRepository ShoppingCartRepository, AttrService attrService) {
		this.ShoppingCartRepository = ShoppingCartRepository;
		this.attrService = attrService;
	}

	public void insertShoppingCart(int productId, int productPrice, String productCategory, int productCount, int memberid) {
		ShoppingCartRepository.insertShoppingCart(productId,productPrice,productCategory,productCount,memberid);
		
	}

	public List<ShoppingCart> showCart(int loginedid) {
		
		return ShoppingCartRepository.showCart(loginedid);
		
	}

	public ResultData<Integer> delCart(int cartId) {
		
		int affectedRowsCount = ShoppingCartRepository.delCart(cartId);
		
		if(affectedRowsCount == 0) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다", "affectedRowsCount", affectedRowsCount);
		}
		
		return ResultData.from("S-1", "조회수 증가", "affectedRowsCount", affectedRowsCount);
		 
		
	}
}