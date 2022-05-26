package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.dto.UserDTO;

/**
 * Servlet implementation class memberFindServlet
 */
@WebServlet("/memberFind.do")
public class memberFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberFindServlet() {
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
		String type=request.getParameter("type");
		String key=request.getParameter("key");
		String[] arr= {"","",""};
		UserDAO udao=UserDAO.getInstance();
		Vector<UserDTO> vec=new Vector<UserDTO>();
		
		if (type.equals("userid")) {arr[0]="selected";}
		if (type.equals("name")) {arr[1]="selected";}
		if (type.equals("phone")) {arr[2]="selected";}
	
		vec=udao.likeFind(type, key);
		
		request.setAttribute("vec", vec);
		request.setAttribute("arr", arr);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/managePage/memberSearch.jsp");
		dispatcher.forward(request, response);
	}

}
