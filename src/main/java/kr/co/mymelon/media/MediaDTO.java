package kr.co.mymelon.media;

import org.springframework.web.multipart.MultipartFile;

public class MediaDTO {
	
	public MediaDTO() {}
	int mediano;		//NUMBER NOT NULL PRIMARY KEY,          -- 미디어 번호
	String title;		//VARCHAR2(255) NOT NULL,                      -- 제목
	String poster;		//VARCHAR2(255) DEFAULT 'poster.jpg' NOT NULL, -- 포스터 이미지
	String filename;	//VARCHAR2(255) NOT NULL,                      -- 음원 파일명
	long filesize;		//NUMBER        DEFAULT 0 NOT NULL,
	char mview;			//CHAR(1)       DEFAULT 'Y' NOT NULL,          -- 출력모드
	String rdate;		//DATE          NOT NULL,                      -- 등록일
	int mediagroupno;	// NUMBER NULL 
//----------------------------------------------------------
	//스프링 파일 객체 맴버변수
 	private MultipartFile posterMF; // <input type='file' name='posterMF' size='50'>
	private MultipartFile filenameMF; // <input type='file' name='filenameMF' size='50'>
//----------------------------------------------------------
	public int getMediano() {
		return mediano;
	}
	public void setMediano(int mediano) {
		this.mediano = mediano;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public char getMview() {
		return mview;
	}
	public void setMview(char mview) {
		this.mview = mview;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public int getMediagroupno() {
		return mediagroupno;
	}
	public void setMediagroupno(int mediagroupno) {
		this.mediagroupno = mediagroupno;
	}
	public MultipartFile getPosterMF() {
		return posterMF;
	}
	public void setPosterMF(MultipartFile posterMF) {
		this.posterMF = posterMF;
	}
	public MultipartFile getFilenameMF() {
		return filenameMF;
	}
	public void setFilenameMF(MultipartFile filenameMF) {
		this.filenameMF = filenameMF;
	}
	
}//class end
