package kr.co.EZHOME.dto;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import kr.co.EZHOME.domain.User;

public class UserDTO {
	private String name;
	private String userid;
	private String pwd;
	private String birth;
	private String email;
	private String phone;
	private String registDate;
	private String addr;
	private String deliAddr;
	private int point;
	private int admin;
	
	public UserDTO() {
		
	}
	
	public UserDTO(String name, String userid, String pwd, String birth, String email, String phone, String registDate,
			String addr, String deliAddr, int point, int admin) {
		super();
		this.name = name;
		this.userid = userid;
		this.pwd = pwd;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.registDate = registDate;
		this.addr = addr;
		this.deliAddr = deliAddr;
		this.point = point;
		this.admin = admin;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDeliAddr() {
		return deliAddr;
	}
	public void setDeliAddr(String deliAddr) {
		this.deliAddr = deliAddr;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	
	public Date transformDate(String birth) {
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
		
		//Date??? ???????????? ????????? ?????? ????????? yyyy-mm-dd??? ??????
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		
		java.util.Date tempDate = null;
		
		try {
			tempDate = beforeFormat.parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// java.util.Date??? yyyy-mm-dd ???????????? ???????????? String?????? ??????
		String transDate = afterFormat.format(tempDate);
		
		// ????????? String ?????? Date??? ??????
		Date d = Date.valueOf(transDate);
		
		return d;
	}
	

}
