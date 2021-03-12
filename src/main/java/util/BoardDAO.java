package util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import criTest.Criteria;
import vo.BoardVO;
import vo.PageVO;

// ** Board CRUD 구현
@Repository
public class BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	// SqlSession (Interface) -> SqlSessionTemplate (servl...xml 에 Bean 등록)
	private static final String NS = "com.ncs.BoardMapper.";
	
// ** Criteria PageList
	public List<BoardVO> criList(Criteria cri) {
		return sqlSession.selectList(NS+"pageList", cri);
	}
	
// ** Page BoardList
	public int totalRowCount() {
		return sqlSession.selectOne(NS+"totalRowCount");
	}
	
	public List<BoardVO> pageList(PageVO<BoardVO> vo) {
		return sqlSession.selectList(NS+"pageList", vo);
	}
	
// ** Check BoardList
	public List<BoardVO> checkselectList(BoardVO vo) {
		return sqlSession.selectList(NS+"checkselectList", vo);
	} //checkselectList

// ** Ajax id BoardList
	public List<BoardVO> idbList(BoardVO vo) {
		return sqlSession.selectList(NS+"idbList", vo);
	} //idbList
	
// ** reply insert
// => 답글 등록과 step증가	
	public int stepUpdate(BoardVO vo) {
		return sqlSession.update(NS+"stepUpdate", vo);
	} //stepUpdate
	
	public int rinsert(BoardVO vo) {
		// step증가 후 입력
		System.out.println("** stepUpdate 결과 => "+
				sqlSession.update(NS+"stepUpdate",vo));
		return sqlSession.insert(NS+"rinsert", vo);
	} //rinsert
	
// ** selectList
	public List<BoardVO> selectList() {
		return sqlSession.selectList(NS+"selectList");
	} //selectList
	
// ** selectOne
	public BoardVO selectOne(BoardVO vo) {
		return sqlSession.selectOne(NS+"selectOne", vo);
	} //selctOne

// ** insert (원글)
// => 답글 추가
	public int insert(BoardVO vo) {
		return sqlSession.insert(NS+"insert", vo);
	} //insert

// ** update
	public int update(BoardVO vo) {
		return sqlSession.update(NS+"update", vo);
	} //update
	
// ** delete	
	public int delete(BoardVO vo) {
		return sqlSession.delete(NS+"delete", vo);
	} //delete

// ** countUp(vo)	
	public int countUp(BoardVO vo) {
		return sqlSession.update(NS+"countUp", vo);
	} //countUp
	
} //class
