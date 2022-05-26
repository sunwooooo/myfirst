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

/**
 * Servlet implementation class memberOnepickServlet
 */
@WebServlet("/memberOnepick.do")
public class memberOnepickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberOnepickServlet() {
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
		String userid=request.getParameter("update");
		UserDAO udao=UserDAO.getInstance();
		User bean=new User();
		String[] arr= {"","",""};
		
		
		try {
			bean=udao.findUser(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String addr=bean.getAddr();
		int count=0;
		int count1=0;
		
		for(int i=0;i<addr.length();i++) {
			if(addr.charAt(i) == ')') { count=i;}
			if(addr.charAt(i) == ',') { count1=i;}}
		
		for(int i=1;i<=count-1;i++) {
			arr[0]+=addr.charAt(i);
		}
		for(int i=count+2;i<count1;i++) {
			arr[1]+=addr.charAt(i);
		}
		for(int i=count1+2;i<addr.length();i++) {
			arr[2]+=addr.charAt(i);
		}
		
		request.setAttribute("arr", arr);
		request.setAttribute("bean", bean);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/managePage/memberUpdate.jsp");
		dispatcher.forward(request, response);
		
	}

}
