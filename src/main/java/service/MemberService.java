package service;

import java.util.List;

import vo.MemberVO;
import vo.PageVO;

public interface MemberService {
	
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
