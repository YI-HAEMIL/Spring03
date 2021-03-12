package service;

import java.util.List;

import criTest.Criteria;
import vo.BoardVO;
import vo.PageVO;

public interface BoardService {
	
	// ** Criteria PageList
	public int totalRowCount();
	public List<BoardVO> criList(Criteria cri);
	
	// ** Page BoardList
	public PageVO<BoardVO> pageList(PageVO<BoardVO> vo);
	
	// ** Check BoardList
	public List<BoardVO> checkselectList(BoardVO vo);

	// ** Ajax id BoardList
	public List<BoardVO> idbList(BoardVO vo);
	
	// ** 댓글등록
	public int rinsert(BoardVO vo);
	
	public List<BoardVO> selectList();
	public BoardVO selectOne(BoardVO vo);
	public int insert(BoardVO vo);
	public int update(BoardVO vo);
	public int delete(BoardVO vo);
	public int countUp(BoardVO vo);
	
} //class
