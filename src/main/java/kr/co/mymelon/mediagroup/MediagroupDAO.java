package kr.co.mymelon.mediagroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class MediagroupDAO {
	@Autowired // Conmponent annotaion 으로 성된 객체를 사용하려면 객체가 서로 연결이 되어야한다.
	private DBOpen dbopen;
	@Autowired
	private DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;
	ArrayList<MediagroupDTO> list = null;

	public MediagroupDAO() {
		System.out.println("------MediagroupDAO() 객체 생성됨");		
	}

	public int create(MediagroupDTO dto) {
		int res = 0;
		try {
			con =dbopen.getConnection();
			sql= new StringBuffer();
			sql.append(" INSERT INTO MEDIAGROUP(mediagroupno, title) ");
			sql.append(" VALUES((SELECT NVL(max(mediagroupno),0)+1 FROM mediagroup), ?) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			res = pstmt.executeUpdate();
			
			System.out.println("입력결과:"+res);
			
		} catch (Exception e) {
			System.out.println("추가 실패"+e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return res;
	}// create end

	public ArrayList<MediagroupDTO> list() {
		try {
			con =dbopen.getConnection();
			sql= new StringBuffer();
			sql.append(" select mediagroupno, title ");
			sql.append(" from mediagroup order by mediagroupno desc ");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				list = new ArrayList<MediagroupDTO>();
				do {
					MediagroupDTO dtoN = new MediagroupDTO();
					dtoN.setMediagroupno(rs.getInt("mediagroupno"));
					dtoN.setTitle(rs.getString("title"));
					list.add(dtoN);
				} while (rs.next());
			}
			
		} catch (Exception e) {
			System.out.println("목록보기 실패"+e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		
		return list;
	}//list end

	public int delete(MediagroupDTO dto) {
		int res=0;
		try {
			con =dbopen.getConnection();
			sql= new StringBuffer();
			sql.append(" DELETE FROM MEDIAGROUP ");
			sql.append(" WHERE MEDIAGROUPNO = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getMediagroupno());
			res = pstmt.executeUpdate();
			
			System.out.println("삭제결과:"+res);
			
		} catch (Exception e) {
			System.out.println("삭제 실패"+e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		
		return res;
	}

	public int update(MediagroupDTO dto) {
		int res=0;
		try {
			con =dbopen.getConnection();
			sql= new StringBuffer();
			sql.append(" UPDATE MEDIAGROUP ");
			sql.append(" SET title=? WHERE mediagroupno=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setInt(2, dto.getMediagroupno());
			res = pstmt.executeUpdate();
			
			System.out.println("수정결과:"+res);
			
		} catch (Exception e) {
			System.out.println("수정 실패"+e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		
		return res;
	}//update end
	
}//class end
