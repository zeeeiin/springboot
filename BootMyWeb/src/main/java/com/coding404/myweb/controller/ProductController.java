package com.coding404.myweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@GetMapping("/productList")
	public String list(Model model, Criteria cri) {
		
		//로그인 기능이 없으므로, admin이라는 계정기반으론 조회
		String writer = "admin";
		
		//1st
//		ArrayList<ProductVO> list = productService.getList(writer);
//		model.addAttribute("list", list);
		
		//2nd
		ArrayList<ProductVO> list = productService.getList(writer, cri);
		
		int total = productService.getTotal(writer, cri);
		PageVO pageVO = new PageVO(cri, total); 
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		System.out.println(pageVO.toString());
		
		return "product/productList";
	}
	
	@GetMapping("/productReg")
	public String reg() {
		
		return "product/productReg";
	}
	
	@GetMapping("/productDetail")
	public String detail(@RequestParam("prod_id") int prod_id,
						  Model model) {
		
		//조회 - prod_id가 필요
		ProductVO vo = productService.getDetail(prod_id);
		
		//vo도 화면으로 가지고 나가야한다. - model 필요
		model.addAttribute("vo", vo);
		
		return "product/productDetail";
	}
	
	//post 방식 
	//등록요청
	@PostMapping("/registForm")
	public String registForm(ProductVO vo, RedirectAttributes ra,
								@RequestParam("file") List<MultipartFile> list) {
				
		//1. 빈 객체 검사
		list = list.stream().filter( t -> t.isEmpty() == false).collect( Collectors.toList());
		
		//2. 확장자 검사
		for(MultipartFile file : list ) {
			System.out.println( file.getContentType() );
			
			if(file.getContentType().contains("image") == false ) {
				ra.addFlashAttribute("msg", "jpg, jpeg, png 이미지 파일만 등록이 가능합니다.");
				return "redirect:/product/productList"; //이미지가 아니라면 list 목록으로
			}			
		}	
		
		//3. 파일을 처리하는 작업은 service 위임 => 애시당초에 controller에서는 request 객체를 받아서 service로 위임하는 전략이 있다.
		
		
//		System.out.println(vo.toString());
//		productService.productRegist(vo);
		
		//성공시 정수 반환을 받기 위해.
		int result = productService.productRegist(vo, list);
		String msg = result == 1 ? "등록되었습니다" : "등록 실패. 관리자에게 문의하세요";
		
		ra.addFlashAttribute("msg", msg );
		
		return "redirect:/product/productList";
	}
	
	
	//post요청
	//수정요청	
	@PostMapping("/modifyForm")
	public String modifyForm(ProductVO vo, RedirectAttributes rra) {
		
		System.out.println(vo);
		//메서드명 = productUpdate()
		//매개변수를 받아서 DB에서 업데이트 작업을 진행
		//업데이트된 결과를 반환받아서 list화면으로 "업데이트성공" 메세지를 띄워주세요.
		
		int result = productService.productUpdate(vo);
		String msg = result == 1 ? "업데이트 성공" : "업데이트 실패";
		rra.addFlashAttribute("msg", msg);
		
		return "redirect:/product/productList";
	}
	 
	
	//post요청
	//삭제요청
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("prod_id")int prod_id) {
		
		productService.productDelete(prod_id);
		
		return "redirect:/product/productList";
	}
	
	
}
