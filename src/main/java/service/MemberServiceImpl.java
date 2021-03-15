package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import criTest.Criteria;
import criTest.SearchCriteria;
import util.MemberDAO;
import vo.MemberVO;
import vo.PageVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;
	
	// ** Search Criteria PageList
	public int searchRowCount(SearchCriteria cri) {
		return dao.searchRowCount(cri);
	}
	public List<MemberVO> searchMList(SearchCriteria cri) {
		return dao.searchMList(cri);
	}
	
	// ** Criteria PageList
	public int totalRowCount() {
		return dao.totalRowCount();
	}
	public List<MemberVO> criMList(Criteria cri) {
		return dao.criMList(cri);
	}
	
	
	// ** Page MemberList
	public PageVO<MemberVO> pageList(PageVO<MemberVO> pvo) {
		pvo.setTotalRowCount(dao.totalRowCount());
		pvo.setList(dao.pageList(pvo));
		return pvo; 
	}

	// ** Check MemberList
	public List<MemberVO> checkselectList(MemberVO vo){
		return dao.checkselectList(vo);
	}
	
	public List<MemberVO> selectList(){
		return dao.selectList();
	}
	public MemberVO selectOne(MemberVO vo) {
		return dao.selectOne(vo);
	} 
	public int insert(MemberVO vo) {
		return dao.insert(vo);
	}
	public int update(MemberVO vo) {
		return dao.update(vo);
	}
	public int delete(MemberVO vo) {
		return dao.delete(vo);
	}
} // class
