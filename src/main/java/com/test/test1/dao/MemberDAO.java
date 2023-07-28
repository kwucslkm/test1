package com.test.test1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.test1.dto.MemberDTO;

// DAO(Data Access Object)
public class MemberDAO {
	Connection con = null; // DB 접속 관련 처리를 위한 객체
	PreparedStatement pstmt = null; // 쿼리문 전송 관련 처리를 위한 객체
	ResultSet rs = null; // select 결과를 담기 위한 객체

	// DB에 접속하기 위한 메서드. Repository 클래스의 모든 메서드가 시작전에 이 메서드를 호출해야 DB 작업 수행 가능.
	public Connection getConnection() throws Exception {
		// 이 메서드의 내용은 보통 평가지에서 제시함. (사용자 계정만 잘 구분하면 됨.)

		// 클래스를 로드 하는 역할(매개변수로 넘기는 클래스가 가지고 있는 필드, 메서드 종류, 클래스 이름 등을 JVM에 할당)
		// 인스턴스 생성 및 초기화를 해줌.
		// 컴파일 시점이 아니라 런타임 시점에 로딩을 할 수 있게 해줌.(DI랑 비슷한 역할)
		Class.forName("oracle.jdbc.OracleDriver"); // 사용할 DB 드라이버 로드
		// 접속할 DB 주소, 사용자 계정, 비밀번호
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "user1", "1234");
		return con;
	}

	public List<MemberDTO> findMember(String searchType, String idValue, String deptValue) throws Exception {
		con = getConnection();
		String sql = "";
		List<MemberDTO> findResult = new ArrayList<>();
		// 사원번호로 조회
		if (searchType.equals("id")) {
			sql = "SELECT * FROM PERSONNEL WHERE ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idValue);
			// 부서명으로 조회
		} else if (searchType.equals("dept")) {
			sql = "SELECT * FROM PERSONNEL WHERE DEPT=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, deptValue);
		}
		rs = pstmt.executeQuery(); // select 쿼리 수행할 때만 executeQuery() 호출
		while (rs.next()) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(rs.getString(1));
			memberDTO.setName(rs.getString(2));
			memberDTO.setDept(rs.getString(3));
			memberDTO.setPosition(rs.getString(4));
			memberDTO.setDuty(rs.getString(5));
			memberDTO.setPhone(rs.getString(6));
			findResult.add(memberDTO);
		}
		System.out.println("findResult: " + findResult);
		return findResult;
	}

	public int saveMember(String id, String name, String dept, String position, 
			String duty, String phone) throws Exception {
		System.out.println("save 호출");
		System.out.println(
				"id" + id + "name: " + name + "dept" + dept + "position" + position 
				+ "duty" + duty + "phone" + phone);
		// 1. DB 접속
		con = getConnection();
		// 2. 쿼리문 작성(변수 값을 넣을 부분은 물음표로 작성)
		String sql = "insert into PERSONNEL(id, name, dept, position, duty, phone)"
				   + " values(?,?,?,?,?,?)";
		// 3. DB에 전송할 쿼리문 준비
		pstmt = con.prepareStatement(sql);
		// 4. 물음표 채우기
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, dept);
		pstmt.setString(4, position);
		pstmt.setString(5, duty);
		pstmt.setString(6, phone);
		// 5. 쿼리문 실행
		int result = pstmt.executeUpdate();
		// 6. 실행 후 pstmt 종료
		pstmt.close();
		// 7. 접속 종료
		con.close();
		if (result == 1) {
			System.out.println("저장성공");
		}
		return result;

	}

	public int updateMember(String id, String name, String dept, String position, String duty, String phone)
			throws Exception {
		con = getConnection();
		String sql = "UPDATE PERSONNEL SET NAME=?, DEPT=?, POSITION=?, DUTY=?, PHONE=? WHERE ID=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, dept);
		pstmt.setString(3, position);
		pstmt.setString(4, duty);
		pstmt.setString(5, phone);
		pstmt.setString(6, id);
		int result = pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return result;
	}

	public int deleteMember(String id, String name) throws Exception {
		con = getConnection();
		String sql = "DELETE FROM PERSONNEL WHERE ID=? AND NAME=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		int result = pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return result;
	}

}
