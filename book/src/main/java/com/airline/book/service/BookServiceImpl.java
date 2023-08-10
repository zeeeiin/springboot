package com.airline.book.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.airline.book.command.DomesticVO;
import com.airline.book.command.MemberVO;
import com.airline.book.command.Non_MemberVO;
import com.airline.book.command.ReservationVO;
import com.airline.book.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	public BookMapper bookMapper;
	
	
	@Override
	public ArrayList<DomesticVO> getList(String dom_num) {
		
		return bookMapper.getList(dom_num);
	}

	@Override
	public Non_MemberVO getNonmem(String nonmember_num) {
		
		return bookMapper.getNonmem(nonmember_num);
	}

	@Override
	public MemberVO getMem(String member_num) {
		
		return bookMapper.getMem(member_num);
	}


	@Override
	public ReservationVO getDetail(String reservation_num) {
		
		return null;
	}}
