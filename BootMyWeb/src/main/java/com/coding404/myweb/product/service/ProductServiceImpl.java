package com.coding404.myweb.product.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	
	//////////////////////////////////////////
	@Value("${project.upload.path}")
	private String uploadPath;
	
	
	//폴더생성함수
	public String makeFolder() {
		
		//문자열값 하나 구해오기
		String path = LocalDate.now().format( DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		File file = new File(uploadPath + "/" + path);
		
		if( file.exists() == false ) { //존재하면 true, 존재하지 않으면 false
			file.mkdirs();			
		}
		
		return path; //날짜 폴더명 반환
	}
		
	//////////////////////////////////////////
	//하나의 메서드에서 여러 CRUD작업이 일어나는 경우에 한 부분이 에러가 발생하면 그 에러를 처리하고, 롤백처리를 대신한다.
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int productRegist(ProductVO vo, List<MultipartFile> list) {
		
		//product테이블 처리
		int result = productMapper.productRegist(vo);
		
		//업로드처리
		for(MultipartFile file : list) {
			//System.out.println( file.isEmpty() );
			//if( file.isEmpty() ) continue; 이렇게말고 다르게 처리해볼 것.
			
			//파일 이름을 받는다.
			String originName = file.getOriginalFilename();
			
			//브라우저별로 파일의 경로가 다를 수 있기 때문에 \\기준으로 파일명만 잘라서 다시 저장해주기
			String filename = originName.substring(originName.lastIndexOf("\\") +1);
			
			//파일 사이즈
			long size = file.getSize();
			
			//동일한 파일을 재업로드시 기존 파일을 덮어버리기 때문에, 난수이름으로 파일명을 바꿔서 올림
			String uuid = UUID.randomUUID().toString();
			
			//날짜별로 폴더 생성
			String filepath = makeFolder(); //filepath 반환			
			
			//세이브할 경로
			String savepath = uploadPath + "/" + filepath + "/" + uuid + "_"+ filename;
			
			//System.out.println(originName);
			//System.out.println(size);
			
			//데이터베이스에 추후에 저장
			System.out.println("실제파일명:" + filename);
			System.out.println("난수값:" + uuid);
			System.out.println("날짜폴더경로:" + filepath);
			System.out.println("세이브할 경로" + savepath);	
			System.out.println("----------------------------------");	
			
			System.out.println(uploadPath);	
			
			try {
				File saveFile = new File(savepath);
				file.transferTo(saveFile); //파일업로드를 진행
				
			} catch (IOException e) {
				System.out.println("파일업로드 중 에러발생");
				e.printStackTrace();
			}
			
			//productUpload테이블에 파일의 경로 insert
			productMapper.productFileRegist(ProductUploadVO.builder()
														.filename(filename)
														.filepath(filepath)
														.uuid(uuid)
														.prod_writer(vo.getProd_writer())
														.build());			
					
		} //end for	
		
		return result;
	}
		
	@Override
	public ArrayList<ProductVO> getList(String writer, Criteria cri) {		
		return productMapper.getList(writer, cri);
	}
	
	@Override
	public int getTotal(String writer, Criteria cri) {		
		return productMapper.getTotal(writer, cri);
	}

	
	@Override
	public ProductVO getDetail(int prod_id) {		
		return productMapper.getDetail(prod_id);
	}

	@Override
	public int productUpdate(ProductVO vo) {		
		return productMapper.productUpdate(vo);
	}

	@Override
	public void productDelete(int prod_id) {		
		productMapper.productDelete(prod_id);
	}


	@Override
	public ArrayList<CategoryVO> getCategory() {
		
		return productMapper.getCategory();
	}


	@Override
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo) {
		
		return productMapper.getCategoryChild(vo);
	}

	@Override
	public ArrayList<ProductUploadVO> getAjaxImg(int prod_id) {
	
		return productMapper.getAjaxImg(prod_id);
	}


}