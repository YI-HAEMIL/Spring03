package com.ncs.green;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.BoardService;
import vo.BoardVO;
import vo.PageVO;

@Controller
public class BoardController {
	@Autowired
	BoardService service;

// ** PageList 1.
	@RequestMapping(value="/bpage")
	public ModelAndView bpage(ModelAndView mv, PageVO<BoardVO> pvo) {
		// 1) Paging 준비
		// => currPage: 요청,
		// => sno: 계산,
		// => totalRowCount: sql
		int currPage = 1;
		if(pvo.getCurrPage() > 1)
			currPage = pvo.getCurrPage();
		else pvo.setCurrPage(currPage);
		
		int sno = (currPage-1)*pvo.getRowPerPage()+1;
		int eno = sno + pvo.getRowPerPage() - 1;
		pvo.setSno(sno);
		pvo.setEno(eno);
		
		
		// 2) Service 처리
		// => totalRowCount, 출력할 row from board Table
		// => totalRowCount를 이용하여 total PageNo 계산
		pvo = service.pageList(pvo);
		int totalPageNo = pvo.getTotalRowCount()/pvo.getRowPerPage();
		if(pvo.getTotalRowCount()%pvo.getRowPerPage() != 0)
			totalPageNo += 1;
		
		// 3) View 처리
		// ** view 2
		// => PageBlock 기능: sPageNo, ePageNo
		// => sPageNo, ePageNo 계산
		// => 필요한 값 : currPage, pageNoCount

		// => 유형
		// 1) 항상 현재 Page가 중앙에 위치할때
		// sPageNo = currPage - pageNoCount/2;
		// ePageNo = currPage + pageNoCount/2;
		// 2) Naver 카페 글, 11번가 상품리스트 Type
		// sPageNo= ((currPage-1)/pageNoCount)*pageNoCount)+1;

		// currPage 가 속한 block 의 sPageNo 는 직전 block 의 ePageNo + 1
		// 예를들어 currPage=3 이고 pageNoCount 가 3 이면 1,2,3 page까지 출력 되어야 하므로
		// 아래 처럼 currPage-1 을 pageNoCount 으로 나눈후 다시 곱하고 +1
		// currPage=11 -> 10,11,12, => (11-1)/3 * 3 +1 = 10
		// 연습 ( pageNoCount=5 )
		// -> currPage=11 인경우 : 11,12,13,14,15 -> ((11-1)/5)*5 +1 : 11
		// -> currPage=7 인경우 : 6,7,8,9,10 -> ((7-1)/5)*5 +1 : 6
		
		int sPageNo = ((currPage-1)/pvo.getPageNoCount())*pvo.getPageNoCount()+1;
		int ePageNo = (sPageNo+pvo.getPageNoCount())-1;  
		// 계산으로 얻어진 ePageNo 가 실제 LastPage 인 totalPageNo보다 크면 수정필요 
		if (ePageNo > totalPageNo) { 
			ePageNo = totalPageNo; 
		}
		mv.addObject("sPageNo", sPageNo);
		mv.addObject("ePageNo", ePageNo);
		mv.addObject("pageNoCount", pvo.getPageNoCount());
		
		
		// ** view1
		// => 출력할 dataList, currPage, lastPageNo(=totalPageNo),
		if(pvo.getList().size()>0)
			mv.addObject("Banana", pvo.getList());
		else mv.addObject("message", "~~ 출력할 자료가 1건도 없습니다 ~~");
		
		mv.addObject("currPage", pvo.getCurrPage());
		mv.addObject("totalPageNo", totalPageNo);
		mv.setViewName("board/pageBList");
		return mv;
	} //bpage
	
	
// ** Check BoardList
	@RequestMapping(value = "/bcheck")
	public ModelAndView bcheck(ModelAndView mv, BoardVO vo) {
		// check에 선택사항이 없으면 selectList()
		// check에 선택사항이 있으면 checkselectList()
		System.out.println("** mcheck, Check 확인" + vo);
		List<BoardVO> list = null;
		if (vo.getCheck() != null) {
			list=service.checkselectList(vo);
		} else 
			list=service.selectList();
		
		if (list.size() > 0) {
			mv.addObject("Banana", list);
		} else {
			mv.addObject("message", "~~ 출력자료가 1건도 없습니다 ~~");
		}
		mv.setViewName("board/checkBList"); // forward
		return mv;
	} // bcheck

// ** Ajax jsonView BoardDetail
	@RequestMapping(value = "/jbdetail")
	public ModelAndView jbdetail(HttpServletResponse response, ModelAndView mv, BoardVO vo) {
		
		// jsonView 사용시 response 의 한글 처리
		response.setContentType("text/html; charset=UTF-8");
		
		vo = service.selectOne(vo);
		if ( vo != null) {
			mv.addObject("content", vo.getContent());
		}else {
			mv.addObject("content","~~ 글번호에 해당하는 글이 없습니다 ~~");
		}
		mv.setViewName("jsonView");
		return mv;
	} //jbdetail	
	
	
// ** Ajax BoardList
	@RequestMapping(value = "/ablist")
	public ModelAndView ablist(ModelAndView mv) {
		List<BoardVO> list = service.selectList();
		if ( list != null) {
			mv.addObject("Banana", list);
		}else {
			mv.addObject("message","~~ 출력자료가 1건도 없습니다 ~~");
		}
		mv.setViewName("ajaxTest/axBoardList");
		return mv;
	} //abList	
	
// ** Ajax Id BoardList
	@RequestMapping(value = "/idblist")
	public ModelAndView idblist(ModelAndView mv, BoardVO vo) {
		System.out.println("idblist vo => "+vo);
		List<BoardVO> list = service.idbList(vo);
		if ( list != null) {
			mv.addObject("Banana", list);
		}else {
			mv.addObject("message","~~ 출력자료가 1건도 없습니다 ~~");
		}
		mv.setViewName("board/boardList"); // forward
		return mv;
	} //idbList
	
// ** 답글등록 
// 1) rinsertForm 출력
	@RequestMapping(value = "/rinsertf")
	public ModelAndView rinsertf(ModelAndView mv, BoardVO vo) {
		mv.setViewName("board/replyForm");
		return mv;
	} //rinsertf 

// 2) 답글 저장
	@RequestMapping(value = "/rinsert")
	public ModelAndView rinsert(ModelAndView mv, BoardVO vo) {
		// vo 에 담겨있는 Value
		// => id, title, content 저장을위해 필요한 값 
		// => root, step, indent 는 부모글(원글)의 value
		//    -> 답글의 root 는 원글 root 와 동일
		// 	  -> 답글의 step 은 원글 step+1 
		//       기존 답글의 step의 값이 위에서 계산된 step 보다 같거나 큰경우에는
		//       모두 1씩 증가해야함. (sql 에서 처리)
		//	  -> 답글의 indent 은 원글 indent+1
		
		vo.setStep(vo.getStep()+1);
		vo.setIndent(vo.getIndent()+1);
		
		if (service.rinsert(vo) >0 ) {
			// 성공 -> boardList
			mv.addObject("message","~~ 답글 등록 성공 ~~");
			mv.setViewName("redirect:blist");
		}else {
			// 실패 -> replyForm
			mv.addObject("message","~~ 답글 등록 실패 !!! 다시 하세요 ~~");
			mv.setViewName("board/replyForm");
		}
		return mv;
	} //rinsert
	
// **************************************
// Board CRUD 	
	@RequestMapping(value = "/blist")
	public ModelAndView blist(HttpServletRequest request, ModelAndView mv) {
		
		List<BoardVO> list = service.selectList();
		if ( list != null) {
			mv.addObject("Banana", list);
		}else {
			mv.addObject("message","~~ 출력자료가 1건도 없습니다 ~~");
		}
		// redirect 요청시 전달된 message 처리
		// => from mdetail
		if (request.getParameter("message") !=null )
			mv.addObject("message",request.getParameter("message"));
			
		mv.setViewName("board/boardList"); // forward
		return mv;
	} //bList
	
	@RequestMapping(value = "/bdetail")
	public ModelAndView bdetail(HttpServletRequest request, ModelAndView mv, BoardVO vo) {
		// ** 조회수 증가
		// => 조건 : 글쓴이의 ID 와 글보는이의 ID (logID) 가 다른 경우
		//          로그인 하지않은 경우도 포함. 
		// => 처리 순서 : 증가 후 조회
		// => 증가 
		//    controller, Java 구문으로 : getCnt() -> setCnt(++getCnt()) -> board Update 
		//	  DAO 에서 sql 구문으로 : board update -> cnt=cnt+1
		
		// 1. 증가조건 확인 
		// => session 에서 logID get
		HttpSession session = request.getSession(false);
		String loginID = null; // 로그인 하지 않음
		if (session !=null && session.getAttribute("loginID") !=null) {
			loginID = (String)session.getAttribute("loginID");
		}
		
		// => board , selectOne 으로 ID get
		vo=service.selectOne(vo);
		if (vo !=null) {
			mv.addObject("Apple", vo);
			// 2. 비교 & 증가
			// => 조건 처리
			if (!vo.getId().equals(loginID)) {
				vo.setCnt(vo.getCnt()+1);
				service.countUp(vo);
			}
			if ("U".equals(request.getParameter("jcode"))) {
				mv.setViewName("board/updateForm");   
			}else {
				mv.setViewName("board/boardDetail");  
			}
		}else {
			mv.addObject("message", "~~ 글번호에 해당하는글이 존재하지 않습니다 ~~");
			mv.setViewName("redirect:blist");
			/* url 로 전달되는 한글 message 처리 위한 encoding
			String message = URLEncoder.encode("~~ 해당하는 글이 없네용 ~~", "UTF-8");
			mv.setViewName("redirect:blist?message="+message); // sendRedirect
			=> 메서드 헤더에 throws UnsupportedEncodingException  해야함
			*/
		}
		// redirect 요청시 전달된 message 처리
		// => from mdetail
		if (request.getParameter("message") !=null )
			mv.addObject("message",request.getParameter("message"));
		
		return mv;
	} //bdetail
	
	@RequestMapping(value = "/binsertf")
	public ModelAndView binsertf(ModelAndView mv) {
		mv.setViewName("board/insertForm");
		return mv;
	} //binsertf 

	@RequestMapping(value = "/binsert")
	public ModelAndView binsert(ModelAndView mv, BoardVO vo) {
		
		if (service.insert(vo) >0 ) {
			// 성공 -> boardList
			mv.addObject("message","~~ 글등록 성공 ~~");
			mv.setViewName("redirect:blist");
		}else {
			// 실패 -> insertForm
			mv.addObject("message","~~ 글등록 실패 !!! 다시 하세요 ~~");
			mv.setViewName("board/insertForm");
		}
		return mv;
	} //binsert
	
	@RequestMapping(value = "/bupdate")
	public ModelAndView bupdate(ModelAndView mv, BoardVO vo) {
		
		if (service.update(vo) > 0) {
			mv.addObject("message","~~ 글수정 성공 ~~");
		}else {
			mv.addObject("message","~~ 글수정 실패 ~~");
		}
		// redirect 의 경우 mv에 addObject 값은 파라미터로 연결되어 전송
		// => 받는쪽에서는 request.getParameter("message") 로 처리가능
		
		mv.setViewName("redirect:bdetail?seq="+vo.getSeq());
		return mv;
	} //bupdate
	
	@RequestMapping(value = "/bdelete")
	public ModelAndView bdelete(ModelAndView mv, BoardVO vo) {
		
		if (service.delete(vo) >0 ) {
			// 성공 -> boardList
			mv.addObject("message","~~ 글삭제 성공 ~~");
			mv.setViewName("redirect:blist");
		}else {
			// 실패 -> bdetail
			mv.addObject("message","~~ 글삭제 실패 ~~");
			mv.setViewName("redirect:bdetail?seq="+vo.getSeq());
		}
		return mv;
	} //bdelete
	
} //class
