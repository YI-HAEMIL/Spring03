package springMybatisTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class ex02_MapperTest {

	@Autowired
	private SqlSession sqlsession;
	@Autowired
	private MemberVO vo;

	private static final String NS = "com.ncs.MemberMapper.";

	// mapper의 각 sql 구문 동작 test
	// test 1)
	@Test
	public void selectOne() {
		vo.setId("apple");
		// 존재하는 id 사용시 해당 row return
		// 없는 id 사용시 null return
		vo = sqlsession.selectOne(NS + "selectOne", vo);
		System.out.println("***" + vo);
		assertNotNull(vo);
	}

	// test2)
	public void insertTest() {
		vo.setId("hotpink");
		vo.setPassword("1234!");
		vo.setName("가나다");
		vo.setLev("A");
		vo.setBirthd("1999-10-09");
		vo.setPoint(1000);
		vo.setWeight(44.33);
		vo.setRid("apple");
		vo.setUploadfile("resources/uploadImage/basicman2.jpg");
		// 성공:1, 실패:0
		assertEquals(sqlsession.insert(NS + "insert", vo), 1);
		// true:green , false:red
	}

	// test3)
	@Test
	public void deleteTest() {
		vo.setId("hotpink");
		// 성공:1, 실패:0
		assertEquals(sqlsession.delete(NS + "delete", vo), 1);
		// true:green , false:red
	}

}