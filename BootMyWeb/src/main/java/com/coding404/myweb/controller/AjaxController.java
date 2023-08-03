package com.coding404.myweb.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.product.service.ProductService;

@RestController
public class AjaxController {
	
	@Autowired
	private ProductService productService;
	
	
	//반환은 responseentity에 category VO 	
	@GetMapping("/getCategory")
	public ResponseEntity<ArrayList<CategoryVO>> getCategory() {
		
		ArrayList<CategoryVO> list = productService.getCategory();
		
		return new ResponseEntity<>(list,HttpStatus.OK) ;
	}
	
	@GetMapping("/getCategoryChild/{a}/{b}/{c}") //3가지 값이 실려옴 - 가변 매개변수로 받기
	public ResponseEntity<ArrayList<CategoryVO>> getCategoryChild(@PathVariable("a") String group_id,
																  @PathVariable("b") int category_lv,
																  @PathVariable("c") int category_detail_lv) {
			System.out.println(group_id);
			System.out.println(category_lv);
			System.out.println(category_detail_lv);
			
			
			CategoryVO vo = CategoryVO.builder()
									  .group_id(group_id)
									  .category_lv(category_lv)
									  .category_detail_lv(category_detail_lv)
									  .build();
			
			ArrayList<CategoryVO> list = productService.getCategoryChild(vo);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
