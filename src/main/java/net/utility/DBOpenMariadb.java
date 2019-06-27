package net.utility;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component // 스프링 컨테이너가 자동으로 객체 생성함
public class DBOpenMariadb {	
	public DBOpenMariadb() {
		System.out.println("------DBOpen() 객체 생성됨");
	}

	public Connection getConnection() { // DB 연결
		// 1) mariadb 정보
		String url = "jdbc:mariadb://localhost:3306/vagrantly?useUnicode=true&characterEncoding=utf-8";// =localhost, // 127.0.0.1
		String user = "root";
		String password = "1234";
		String driver = "org.mariadb.jdbc.Driver"; 
/*
		// 2) MySQL DB 정보
		String url = "jdbc:mysql://localhost:3306/soldesk?useUnicode=true&characterEncoding=euckr";// =localhost, // 127.0.0.1
		String user = "root";
		String password = "1234";
		String driver = "org.gjt.mm.mysql.Driver"; // mysql-connector
*/
		
		Connection con= null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			System.out.println("DB연결 실패 : "+ e);
		}
		
		return con;
		
		
	}// method end

}// class end
