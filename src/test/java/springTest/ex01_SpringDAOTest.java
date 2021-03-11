package springTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javaTest.MemberDAO;
import vo.MemberVO;

//** DAOTest Spring Version
//=> 설정화일(~.xml) 을  사용
//	-> 테스트코드 실행시에 설정파일을 이용해서 스프링이 로딩 되도록 해줌
//	-> @RunWith(스프링 로딩) , @ContextConfiguration (설정파일 등록)

//=> IOC/DI Test
//=> 공통적으로 사용하는 MemberDAO dao 인스턴스를 전역으로 정의
//=> 자동 주입 받기 ( xml_root-context.xml , @ )

//** import 제대로 안되고 오류발생시 Alt+f5 눌러 Maven Update 한다.
//=> 메뉴 : Project 우클릭 - Maven - Update Project .. 
//   ( 하기전 주의사항은 pom.xml 의  <plugin> <configuration> 의 
//     <source>1.8</source> 와 <target> Java 버전 확인 )

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ex01_SpringDAOTest {
	
	@Autowired
	MemberDAO dao ;  // javaTest/MemberDAO
	@Autowired
	MemberVO vo ;    // vo/MemberVO
	 
	//test1) Detail 정확하게 구현 했는지 Test
	public void detailTest() {
		// DAO 자동주입 확인
		//assertNotNull(dao); // green 생성성공
		
		// Detail 확인
		//vo.setId("banana");      // green
		vo.setId("banana123");    // red
		assertNotNull(dao.selectOne(vo));  
		// NotNull: green ,  Null: red      
	}
	
	@Test
	//test2) Insert 구현 정확성 Test 
	public void insertTest() {
		vo.setId("spring");
		vo.setPassword("1234!");
		vo.setName("가나다");
		vo.setLev("A");
		vo.setBirthd("1999-10-09");
		vo.setPoint(1000);
		vo.setWeight(44.33);
		vo.setRid("apple");
		vo.setUploadfile("resources/uploadImage/basicman2.jpg");
		// 성공:1, 실패:0
		assertEquals(dao.insert(vo), 1);  // true:green , false:red   
	}

}
