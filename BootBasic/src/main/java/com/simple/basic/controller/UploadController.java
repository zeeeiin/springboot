package com.simple.basic.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.simple.basic.command.UploadVO;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
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
	

	@GetMapping("/upload")
	public String upload() {
		return "upload/upload";	}
	
	
	//파일데이터는 MultipartFile객체로 받는다.
	@PostMapping("/upload_ok")
	public String upload_ok(@RequestParam("file") MultipartFile file){
		
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
		
		System.out.println(uploadPath);	
		
		try {
			File saveFile = new File(savepath);
			file.transferTo(saveFile); //파일업로드를 진행
			
		} catch (IOException e) {
			System.out.println("파일업로드 중 에러발생");
			e.printStackTrace();
		}
		
		return "upload/upload_ok";
	}
	
	//복수 태그를 사용한 다중파일 업로드 - List(MultipartFile)
	@PostMapping("/upload_ok2")
	public String upload_ok2(@RequestParam("file") List<MultipartFile> list) {	
	
		//리스트 비워주기
		//빈 file객체는 제외하고 새로운 리스트를 생성
		list = list.stream().filter( (f) -> f.isEmpty() == false).collect(Collectors.toList());
		
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
		
		} //end for
		
		return "upload/upload_ok";		
	}
	
	
	//
	@PostMapping("/upload_ok3")
	public String upload_ok3(MultipartHttpServletRequest files) {
		
		List<MultipartFile> list = files.getFiles("file"); //클라이언트 input name 값
		
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
		
		} //end for
		
		return	"upload/upload_ok";				
	}
	
	
	//비동기방식으로 받기 -> 폼데이터라서 @requestBody는 필요x
	@PostMapping("/upload_ok4")
	public @ResponseBody ResponseEntity<String> upload_ok4(UploadVO vo) {
		System.out.println(vo.getFile());
		
		MultipartFile file = vo.getFile(); //getter
		
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
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	//이미지 띄워주기
	@GetMapping("/display") //rest 방식이라 @ResponseBody 들어감
	public @ResponseBody byte[] display (@RequestParam("filename") String filename,
										 @RequestParam("filepath") String filepath,
										 @RequestParam("uuid") String uuid) {
		
		//파일을 읽어올 경로
		String path = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
		
		System.out.println(path);
		
		byte[] data = null;
		
		try {
			data = FileCopyUtils.copyToByteArray(new File(path));//이미지경로
		} catch (IOException e) {			
			e.printStackTrace();
		} 
		
		return data;
	}
	
	
	
		
}

