package net.utility;

import java.sql.Connection;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class DBClose {// 데이터베이스 자원 반납에 관한 함수 를 만들자
	public DBClose() {
		System.out.println("------DBClose() 객체 생성됨");
	}
	
	public void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {}
	}//method end
	//----------------------------------------
	public void close(Connection con, PreparedStatement pstmt) {		
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {}

		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {}
	}//method end
	//----------------------------------------
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {}
		
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {}

		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {}
		
	}//method end
}//class end
