package com.coding404.myweb.product.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Mapper //이거 매퍼야
public interface ProductMapper {
	
	public int productRegist(ProductVO vo);
//	public ArrayList<ProductVO> getList(String writer); //조회
	public ArrayList<ProductVO> getList(@Param("writer") String writer,
										@Param("cri") Criteria cri); //조회
	
	public int getTotal(@Param("writer")String writer,
			@Param("cri") Criteria cri); //전체게시글수	
	
	public ProductVO getDetail(int prod_id); //상세
	public int productUpdate(ProductVO vo); //수정
	public void productDelete(int prod_id); //삭제
}
