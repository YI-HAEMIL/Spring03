package util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.MemberVO;
import vo.PageVO;

@Repository
public class MemberDAO {
// ** member Table 의 CURUD Mybatis
	
	// @Inject 는  Java 에서 제공 , @Autowired 는 스프링 이 제공 
	@Autowired
	private SqlSession sqlSession; 
	// SqlSession (Interface) -> SqlSessionTemplate (servl...xml 에 Bean 등록)
	// 스프링 컨테이너가 setSqlSessionFactory 메서드를 자동으로 호출하여 
	// 스프링 설정파일(..-context.xml)에 <bean> 등록된 SqlSessionFactoryBean 객체를 인자로 받아	
	// 부모인 	SqlSessionDaoSupport 에 setSqlSessionFactory() 메서드로 설정해줌.
	// 이렇게 함으로 SqlSessionDaoSupport 로부터 상속된 getSqlSession() 메서드를 호출하여
	// SqlSession 객체를 return 받을 수 있게됨.
	
	private static final String NS ="com.ncs.MemberMapper.";

// ** Page MemberList
	public int totalRowCount() {
		return sqlSession.selectOne(NS + "totalRowCount");
	}

	public List<MemberVO> pageList(PageVO<MemberVO> pvo) {
		return sqlSession.selectList(NS + "pageList", pvo);
	}

// ** checkselectList
	public List<MemberVO> checkselectList(MemberVO vo) {
		return sqlSession.selectList(NS+"checkselectList", vo) ;
	} 
	
	// selectList
	public List<MemberVO> selectList() {
		return sqlSession.selectList(NS+"selectList") ;
	} //selectList
	
// ** selctOne
	public MemberVO selectOne(MemberVO vo) {
		return sqlSession.selectOne(NS+"selectOne", vo)    ;
	} //selctOne
	
// ** insert
	public int insert(MemberVO vo) {
		return sqlSession.insert(NS+"insert", vo);
	} //insert
	
// ** update
	public int update(MemberVO vo) {
		return sqlSession.update(NS+"update", vo);
	} //update
	
// ** delete
	public int delete(MemberVO vo) {
		return sqlSession.delete(NS+"delete", vo);
	} //delete
	
} //class
