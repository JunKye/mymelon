package kr.co.mymelon.media;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class MediaCont {
	@Autowired	// 자동으로 생성된 객체를 서로연결 // 스프링컨테이너에 이미 생성되어 있으므로 별도로 new NediaGroupDAO 하지 않아도 된다.
	private MediaDAO dao;
	
	
	public MediaCont() {
		System.out.println("--------MediaCont() 객체 생성됨");
	}

	@RequestMapping(value="/media/list.do", method=RequestMethod.GET)
	public ModelAndView list(MediaDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/list");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("mediagroupno", dto.getMediagroupno());	
		mav.addObject("list", dao.list(dto));	
		return mav;
	}//list end
	
	@RequestMapping(value="/media/create.do", method=RequestMethod.GET)
	public ModelAndView createForm(MediaDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/createForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("mediagroupno", dto.getMediagroupno());	
		return mav;
	}//creatForm end
	
	@RequestMapping(value="/media/create.do", method=RequestMethod.POST)
	public ModelAndView createProc(MediaDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/msgView");
		mav.addObject("root", Utility.getRoot());
//		------------------------------------
//		전송된 파일 처리  	-> 실제 파일은 /media/storage 폴더에 저장
//					-> 파일 정보는 /media/temp 폴더에 저장
		String basePath = req.getRealPath("/media"); 
//serverFullPath: D:\Java1113\wsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\mymelon\media
		System.out.println("posterMF basePath = "+ basePath);		
		// 1) <input type='file' name='posterMF' size='50'>
		MultipartFile posterMF = dto.getPosterMF();
		String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath+"/storage"); //파일 저장하고 리네임된 파일명 반환
		dto.setPoster(poster); // dto 객체에 poster파일명 담기		
		
		// 2) <input type='file' name='filenameMF' size='50'>
		MultipartFile filenameMF = dto.getFilenameMF();
		String filename = UploadSaveManager.saveFileSpring30(filenameMF, basePath+"/temp"); //파일 저장하고 리네임된 파일명 반환
		dto.setFilename(filename); // dto 객체에 poster파일명 담기		
		dto.setFilesize(filenameMF.getSize());
//		------------------------------------
		
		int cnt = dao.create(dto);
		if (cnt==0) {
			mav.addObject("msg1", "<p>음원 등록 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do?mediagroupno="+dto.getMediagroupno()+"\"'>");
		}else {
			mav.addObject("msg1", "<p>음원 등록 성공</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link1", "<input type='button' value='추가등록' onclick='location.href=\"./create.do\"'>");
			mav.addObject("link2", "<input type='button' value='목록' onclick='location.href=\"./list.do?mediagroupno="+dto.getMediagroupno()+"\"'>");
		}//if end
		return mav;
	}//createProc end
	
	
	@RequestMapping(value="/media/read.do", method=RequestMethod.GET)
	public ModelAndView read(int mediano) {
		ModelAndView mav = new ModelAndView();
		MediaDTO dto = new MediaDTO();
		dto = dao.read(mediano);
		ArrayList<MediaDTO> list = new ArrayList<MediaDTO>();
		list = dao.list(dto);
		if (dto!=null) {
			String filename = dto.getFilename();
			filename=filename.toLowerCase();
			if (filename.endsWith(".mp3")) {
				mav.setViewName("media/readMP3");
			} else if(filename.endsWith(".mp4")){
				mav.setViewName("media/readMP4");
			}			
			
		}//if (dto!=null) end
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		mav.addObject("list", list);
		System.out.println("root = "+Utility.getRoot());
		return mav;
	}//read end
	
	@RequestMapping(value="/media/delete.do", method=RequestMethod.GET)
	public ModelAndView deleteForm(MediaDTO dto) {
		ModelAndView mav = new ModelAndView();
	
		mav.setViewName("media/deleteForm");		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dao.read(dto.getMediano()));
		return mav;
	}//creatForm end
	
	@RequestMapping(value="/media/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteProc(MediaDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("media/msgView");		
		mav.addObject("root", Utility.getRoot());
		MediaDTO oldDTO = dao.read(dto.getMediano());
		
		int cnt = dao.delete(dto.getMediano());
		if (cnt==0) {
			mav.addObject("msg1", "<p>음원 삭제 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='그룹목록' onclick='location.href=\"./list.do\"'>");
		}else {
			String basepath=req.getRealPath("/media");			
			System.out.println(req.getPathInfo()); 
			UploadSaveManager.deleteFile(basepath+"/storage", oldDTO.getPoster());
			UploadSaveManager.deleteFile(basepath+"/temp", oldDTO.getFilename());
			mav.addObject("msg1", "<script type=\"text/javascript\">alert(\"삭제 되었습니다.\");window.location.href='./list.do'</script>");
		}//if end
		
		return mav;
	}//deleteProc end
	
	@RequestMapping(value="/media/update.do", method=RequestMethod.GET)
	public ModelAndView updateForm(MediaDTO dto) {
		ModelAndView mav = new ModelAndView();
	
		mav.setViewName("media/updateForm");		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dao.read(dto.getMediano()));
		return mav;
	}//updateForm end
	
	@RequestMapping(value="/media/update.do", method=RequestMethod.POST)
	public ModelAndView updateProc(MediaDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("media/msgView");		
		mav.addObject("root", Utility.getRoot());
		MediaDTO oldDTO = dao.read(dto.getMediano());
//		------------------------------------
//		전송된 파일 처리  	-> 실제 파일은 /media/storage 폴더에 저장
//					-> 파일 정보는 /media/temp 폴더에 저장
		String basePath = req.getRealPath("/media"); 
//serverFullPath: D:\Java1113\wsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\mymelon\media
		System.out.println("posterMF basePath = "+ basePath);		
		// 1) <input type='file' name='posterMF' size='50'>
		MultipartFile posterMF = dto.getPosterMF();
		String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath+"/storage"); //파일 저장하고 리네임된 파일명 반환
		dto.setPoster(poster); // dto 객체에 poster파일명 담기		
		
		// 2) <input type='file' name='filenameMF' size='50'>
		MultipartFile filenameMF = dto.getFilenameMF();
		String filename = UploadSaveManager.saveFileSpring30(filenameMF, basePath+"/temp"); //파일 저장하고 리네임된 파일명 반환
		dto.setFilename(filename); // dto 객체에 poster파일명 담기		
		dto.setFilesize(filenameMF.getSize());
//		------------------------------------
		
		
		int cnt = dao.update(dto);
		if (cnt==0) {
			mav.addObject("msg1", "<p>음원 수정 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			mav.addObject("link2", "<input type='button' value='그룹목록' onclick='location.href=\"./list.do\"'>");
		}else {
			String basepath=req.getRealPath("/media");			
			System.out.println(req.getPathInfo()); 
			UploadSaveManager.deleteFile(basepath+"/storage", oldDTO.getPoster());
			UploadSaveManager.deleteFile(basepath+"/temp", oldDTO.getFilename());
			mav.addObject("msg1", "<script type=\"text/javascript\">alert(\"수정 되었습니다.\");window.location.href='./list.do?mediagroupno="+dto.getMediagroupno()+"'</script>");
		}//if end
		
		return mav;
	}//updateProc end
}//class end
