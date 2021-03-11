package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.BoardDAO;
import vo.BoardVO;
import vo.PageVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO dao;
	
	// ** Page BoardList
	// => totalRowCount set, 출력할 board row set 
	public PageVO<BoardVO> pageList(PageVO<BoardVO> vo) {
		vo.setTotalRowCount(dao.totalRowCount());
		vo.setList(dao.pageList(vo));
		return vo; 
	}
	
	// ** Check BoardList
	public List<BoardVO> checkselectList(BoardVO vo) {
		return dao.checkselectList(vo);
	}

	// ** Ajax id BoardList
	public List<BoardVO> idbList(BoardVO vo) {
		return dao.idbList(vo);
	}
	
	// ** 댓글등록
	public int rinsert(BoardVO vo) {
		return dao.rinsert(vo);
	} //rinsert
	
	public List<BoardVO> selectList() {
		return dao.selectList(); 
	} //selectList
	
	public BoardVO selectOne(BoardVO vo) {
		return dao.selectOne(vo);
	} //selectOne
	
	public int insert(BoardVO vo) {
		return dao.insert(vo);
	} //insert
	
	public int update(BoardVO vo) {
		return dao.update(vo);
	} //update
	
	public int delete(BoardVO vo) {
		return dao.delete(vo);
	} //delete
	
	public int countUp(BoardVO vo) {
		return dao.countUp(vo);
	}
} //class
