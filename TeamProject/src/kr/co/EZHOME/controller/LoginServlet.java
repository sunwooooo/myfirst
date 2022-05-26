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
import kr.co.EZHOME.domain.LoginStatus;
import kr.co.EZHOME.domain.User;
import kr.co.EZHOME.dto.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/login/login.jsp";
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		User user;
		
		try {
			validate(userid, pwd);
			UserDAO udao = UserDAO.getInstance();
			user = udao.findUser(userid);
		}catch (Exception e) {
			request.setAttribute("message", e.getMessage());
			forward(request, response, url);
			e.printStackTrace();
			return;
		}
		
		LoginStatus result = user.login(pwd);
		
		if (result == LoginStatus.LOGIN_SUCCESS) {
			makeSession(request, user);
			request.setAttribute("message", "로그인 되었습니다.");
			url = "index.jsp";
		} else if (result == LoginStatus.PASSWORD_WRONG) {
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		}
		forward(request, response, url);
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String url) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void makeSession(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", user);
		session.setAttribute("name", user.getName());
		session.setAttribute("userid", user.getUserid());
		session.setAttribute("password",user.getPassword());
		session.setAttribute("birth", user.getBirth());
		session.setAttribute("email",user.getEmail());
		session.setAttribute("phone",user.getPhone());
		session.setAttribute("registDate", user.getRegistDate());
		session.setAttribute("addr", user.getAddr());
		session.setAttribute("deli", user.getDeli());
		session.setAttribute("point", user.getPoint());
		session.setAttribute("admin", user.getAdmin());
	}
	
	private void validate(String userid, String pwd) {
		if (userid == null || userid == "") {
			throw new IllegalArgumentException("아이디가 비워있습니다.");
		}
		else if(pwd == null || pwd == "") {
			throw new IllegalArgumentException("패스워드가 비워있습니다.");
		}
	}
}