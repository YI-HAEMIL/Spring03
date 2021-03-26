package vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// MultipartFile (Interface)
// -> CommonsMultipartFile

@Data
public class MemberVO {
	
	private String id;
	private String password;
	private String name;
	private String lev;
	private String birthd;
	private int point;
	private double weight;
	private String rid;
	private String uploadfile; // Table 에 저장된 Image 화일값 
	private MultipartFile uploadfilef; 
	// form으로부터 Iamge 관련 정보를 받아오기 위한 필드 
	
	private String[] check;
	// ** 배열타입 (CheckBox 처리) 
	// => 배열타입 검색조건 처리
	
	// 전송 자료가 {'A','B','C'} 가정하면
	// => Sql 
	// where lev='A' or lev='B' or lev='C' ..
	// where lev in ('A','B','C')
	
} //vo
