package service;

import java.util.List;

import criTest.Criteria;
import criTest.SearchCriteria;
import vo.MemberVO;
import vo.PageVO;

public interface MemberService {
	
	// ** Search Criteria PageList
	public int searchRowCount(SearchCriteria cri);
	public List<MemberVO> searchMList(SearchCriteria cri);
	
	// ** Criteria PageList
	public int totalRowCount();
	public List<MemberVO> criMList(Criteria cri);
	
	// ** Page MemberList
	public PageVO<MemberVO> pageList(PageVO<MemberVO> pvo);
	
	// ** Check BoardList
	public List<MemberVO> checkselectList(MemberVO vo);
	
	public List<MemberVO> selectList();
	public MemberVO selectOne(MemberVO vo);
	public int insert(MemberVO vo);
	public int update(MemberVO vo);
	public int delete(MemberVO vo);
} //class
