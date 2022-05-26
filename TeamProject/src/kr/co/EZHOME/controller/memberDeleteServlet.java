package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.UserDAO;

/**
 * Servlet implementation class memberDeleteServlet
 */
@WebServlet("/memberDelete.do")
public class memberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberDeleteServlet() {
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
		String userid=request.getParameter("delete");
		UserDAO udao=UserDAO.getInstance();
		
		int result = udao.deleteMember(userid);
		
		System.out.print(result);
		
		if(result == 1) {
			request.setAttribute("message","삭제되었습니다");
		}else {
			request.setAttribute("message","오류 확인");
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/memberSearch.do");
		dispatcher.forward(request, response);
	}

}
