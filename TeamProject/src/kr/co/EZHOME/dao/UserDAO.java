package kr.co.EZHOME.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.EZHOME.domain.DataStatus;
import kr.co.EZHOME.domain.LoginStatus;
import kr.co.EZHOME.domain.User;
import kr.co.EZHOME.dto.UserDTO;

public class UserDAO {

	private UserDAO() {

	}

	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/Oracle11g");
		conn = ds.getConnection();

		return conn;
	}
	
	public User findUser (String userid) throws Exception {
		String sql="select * from usertbl where userid=?";
		User user = new User();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				user.setName(rs.getString("name"));
				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("pwd"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAddr(rs.getString("addr"));
				user.setDeli(rs.getString("deli"));
				user.setPoint(rs.getInt("point"));
				user.setAdmin(rs.getInt("admin"));
				//Date값이 null 일때 toString 메서드를 사용하면 NullPointerException 에러가 발생한다
				if (rs.getDate("birth") == null) {
					user.setBirth(null);
				}
				else {
					user.setBirth(rs.getDate("birth").toString());
				}
				if (rs.getDate("RegistDate") == null) {
					user.setRegistDate(null);
				}
				else {
					user.setRegistDate(rs.getDate("birth").toString());
				}
			}
			else {
				throw new IllegalArgumentException("존재하지 않는 회원입니다.");
			}
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	//userCheck의 pwd 비교하는 기능을 domain 패키지의 User 객체에서 수행하도록 변경
//	public LoginStatus userCheck(String userid,String pwd) {
//		LoginStatus result = null;
//		String sql="select pwd from usertbl where userid=?";
//		
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//			conn=getConnection();
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setString(1, userid);
//			rs=pstmt.executeQuery();
//			
//			if(rs.next()) {
//				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
//					result= LoginStatus.LOGIN_SUCCESS; 
//				}else {
//					result= LoginStatus.PASSWORD_WRONG;
//				}
//			}else {
//				result= LoginStatus.NOT_EXIST_USER;
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(rs != null)
//					rs.close();
//				if(pstmt != null)
//					pstmt.close();
//				if(conn != null)
//					conn.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return result;
//	}
	
	public DataStatus confrimID(String userid) {
		DataStatus result = null;
		String sql="select userid from usertbl where userid=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
					result = DataStatus.Exist; // 아이디 일치
			}else {
					result = DataStatus.Not_Exist; // 아이디 없음
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int insertMember(UserDTO udto) {
		int result=-1;				// name1, username2, pwd3, birth4, email5, phone6, rdate7, addr8, deli9, point10, admin11
		String sql="insert into usertbl values(?, ?, ?, ?, ?, ?, default, ?, ?, ?, ?)";		
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,udto.getName());
			pstmt.setString(2,udto.getUserid());
			pstmt.setString(3,udto.getPwd());
			pstmt.setDate(4,udto.transformDate(udto.getBirth()));
			pstmt.setString(5,udto.getEmail());
			pstmt.setString(6,udto.getPhone());
			pstmt.setString(7,udto.getAddr());
			pstmt.setString(8,udto.getDeliAddr());
			pstmt.setInt(9,udto.getPoint());
			pstmt.setInt(10,udto.getAdmin());
			
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int updateMember(UserDTO udto) {
		int result=-1;
		String sql="update usertbl set name = ?, pwd = ?, email = ?, phone = ?, birth = ?, addr = ? where userid = ?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,udto.getName());
			pstmt.setString(2,udto.getPwd());
			pstmt.setString(3,udto.getEmail());
			pstmt.setString(4,udto.getPhone());
			pstmt.setString(5,udto.getBirth());
			pstmt.setString(6,udto.getAddr());
			pstmt.setString(7,udto.getUserid());
			
			result=pstmt.executeUpdate();//
			System.out.println("result="+result);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public Vector<UserDTO> allselect(){ 
		
		Vector<UserDTO> vec=new Vector<UserDTO>();
		String sql = "select * from usertbl";
		
		Connection conn=null;
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			ptmt=conn.prepareStatement(sql);
			rs=ptmt.executeQuery();
			
			while(rs.next()) {
				UserDTO udto=new UserDTO();
				udto.setName(rs.getString("name"));
				udto.setUserid(rs.getString("userid"));
				udto.setPwd(rs.getString("pwd"));
				udto.setEmail(rs.getString("email"));
				udto.setPhone(rs.getString("phone"));
				udto.setAddr(rs.getString("addr"));
				udto.setDeliAddr(rs.getString("deli"));
				udto.setPoint(rs.getInt("point"));
				udto.setAdmin(rs.getInt("admin"));
				
				if(rs.getDate("birth") == null) {
					udto.setBirth(null);
				}else {udto.setBirth(rs.getDate("birth").toString());}
				
				if(rs.getDate("registdate") == null) {
					udto.setRegistDate(null);
				}else {udto.setRegistDate(rs.getDate("registdate").toString());}
				
				vec.add(udto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if( rs != null ) 
				rs.close();
			if( ptmt != null)
				ptmt.close();
			if( conn != null)
				conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	
		return vec;
	}
	
	public int deleteMember(String userid) {
		int answer=-1;
		Connection conn = null;
		PreparedStatement ptmt = null;
		
		String sql="delete from usertbl where userid = ?";
		
		try {
			conn=getConnection();
			ptmt=conn.prepareStatement(sql);
			ptmt.setString(1, userid);
			answer=ptmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if( conn != null) 
					conn.close();
				if( ptmt != null)
					ptmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return answer;
	}
	
	public Vector<UserDTO> likeFind(String type,String key){
		Vector<UserDTO> vec=new Vector<UserDTO>();
		
		Connection conn=null;
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		
		String sql="select * from usertbl where "+type+" like "+"'%"+key+"%'";
		
		try {
			conn=getConnection();
			ptmt=conn.prepareStatement(sql);
			rs=ptmt.executeQuery();
			
			while(rs.next()) {
				UserDTO udto=new UserDTO();
				udto.setName(rs.getString("name"));
				udto.setUserid(rs.getString("userid"));
				udto.setPwd(rs.getString("pwd"));
				udto.setEmail(rs.getString("email"));
				udto.setPhone(rs.getString("phone"));
				udto.setAddr(rs.getString("addr"));
				udto.setDeliAddr(rs.getString("deli"));
				udto.setPoint(rs.getInt("point"));
				udto.setAdmin(rs.getInt("admin"));
				
				if(rs.getDate("birth") == null) {
					udto.setBirth(null);
				}else {udto.setBirth(rs.getDate("birth").toString());}
				
				if(rs.getDate("registdate") == null) {
					udto.setRegistDate(null);
				}else {udto.setRegistDate(rs.getDate("registdate").toString());}
				
				vec.add(udto);
			}
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( rs != null)
					rs.close();
				if( ptmt != null)
					ptmt.close();
				if( conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return vec;
	}
}

