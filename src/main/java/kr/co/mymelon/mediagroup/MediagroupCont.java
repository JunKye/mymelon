package kr.co.mymelon.mediagroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Utility;

@Controller
public class MediagroupCont {
	@Autowired	// 자동으로 생성된 객체를 서로연결 
				// 스프링컨테이너에 이미 생성되어 있으므로 별도로 new NediaGroupDAO 하지 않아도 된다.
	private MediagroupDAO dao;
	
	public MediagroupCont() {
		System.out.println("------MediagroupCont() 객체 생성됨");
	}
	
	//mymelon 프로젝트의 첫 페이지 호출
	//http://localhost:9090/mymelon/mediagroup/list.do
	//미디어 그룹 등록 페이지 호출
	//http://localhost:9090/mymelon/mediagroup/create.do
	
	@RequestMapping(value="/mediagroup/create.do", method=RequestMethod.GET)
	public String createForm() {
		return "mediagroup/createForm";
	}//creatForm end
	
	@RequestMapping(value="/mediagroup/create.do", method=RequestMethod.POST)
	public ModelAndView createProc(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/msgView");
		int cnt = dao.create(dto);
		if (cnt==0) {
			mav.addObject("msg1", "<p>미디어 그룹 등록 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='그룹목록' onclick='location.href=\"./list.do\"'>");
		}else {
			mav.addObject("msg1", "<p>미디어 그룹 등록 성공</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link1", "<input type='button' value='추가등록' onclick='location.href=\"./create.do\"'>");
			mav.addObject("link2", "<input type='button' value='그룹목록' onclick='location.href=\"./list.do\"'>");
		}//if end
		
		return mav;
	}//creatForm end
	
	@RequestMapping(value="/mediagroup/list.do", method=RequestMethod.POST)
	public ModelAndView listPOST(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/list");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.list());
		
		return mav;
	}//list end
	
	@RequestMapping(value="/mediagroup/list.do", method=RequestMethod.GET)
	public ModelAndView listGET(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/list");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.list());
		
		return mav;
	}//list end
	
	@RequestMapping(value="/mediagroup/delete.do", method=RequestMethod.GET)
	public ModelAndView deleteForm(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/deleteForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		
		return mav;
	}//delete end
	@RequestMapping(value="/mediagroup/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteProc(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/msgView");
		mav.addObject("root", Utility.getRoot());
		int cnt = dao.delete(dto);
		if (cnt==0) {
			mav.addObject("msg1", "<p>미디어 그룹 삭제 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='그룹목록' onclick='location.href=\"./list.do\"'>");
		}else {
			mav.addObject("msg1", "<script type=\"text/javascript\">alert(\"삭제 되었습니다.\");window.location.href='./list.do'</script>");
		}//if end
		return mav;
	}//delete end
	
	@RequestMapping(value="/mediagroup/update.do", method=RequestMethod.GET)
	public ModelAndView updateForm(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/updateForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);		
		return mav;
	}//update end
	
	@RequestMapping(value="/mediagroup/update.do", method=RequestMethod.POST)
	public ModelAndView updateProc(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/msgView");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dao.update(dto));		
		mav.addObject("msg1", "<script type=\"text/javascript\">alert(\"수정 되었습니다.\");window.location.href='./list.do'</script> ");		
		return mav;
	}//update end
}//class end
