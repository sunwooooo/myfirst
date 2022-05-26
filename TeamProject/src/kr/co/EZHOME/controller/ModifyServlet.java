package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.dto.UserDTO;

/**
 * Servlet implementation class ModifyServlet
 */ 
@WebServlet("/Modify.do")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String userid =request.getParameter("userid");

		UserDAO udao = UserDAO.getInstance();
		UserDTO udto = udao.getMember(userid);

		request.setAttribute("udto", udto);

		RequestDispatcher dispatcher = request.getRequestDispatcher("modify.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
	
		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");

		UserDTO udto = new UserDTO();
		udto.setUserid(userid);
		udto.setPwd(pwd);
		udto.setEmail(email);
		udto.setPhone(phone);
		udto.setAdmin(Integer.parseInt(admin));


		UserDAO udao = UserDAO.getInstance();		
		udao.updateMember(udto);
		response.sendRedirect("login.do");
		
		 
	}

}
