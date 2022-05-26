package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.domain.User;
import kr.co.EZHOME.dto.UserDTO;

/**
 * Servlet implementation class memberUpdateServlet
 */
@WebServlet("/memberUpdate.do")
public class memberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		UserDAO udao=UserDAO.getInstance();
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String birth=request.getParameter("birth");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String addr1=request.getParameter("addr1");
		String roadaddr=request.getParameter("roadAddr");
		String addr3=request.getParameter("addr3");
		String userid=request.getParameter("userid");
		
			String addr = "("+addr1 + ") " + roadaddr + ", " + addr3;
			UserDTO udto=new UserDTO();
			udto.setName(name);
			udto.setPwd(pwd);
			udto.setBirth(birth);
			udto.setEmail(email);
			udto.setPhone(phone);
			udto.setAddr(addr);
			udto.setUserid(userid);
			int result=udao.updateMember(udto);
			System.out.print(result);
			
			if(result == 1) {
				request.setAttribute("message","수정되었습니다");
			}else {
				request.setAttribute("message","오류 확인");
			}
	
		RequestDispatcher dispatcher=request.getRequestDispatcher("/memberSearch.do");
		dispatcher.forward(request, response);
	
	}

}
