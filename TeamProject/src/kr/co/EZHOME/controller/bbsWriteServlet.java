package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.BbsDAO;
import kr.co.EZHOME.dto.BbsDTO;

/**
 * Servlet implementation class bbsWriteServlet
 */
@WebServlet("/bbsWrite.do")
public class bbsWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bbsWriteServlet() {
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
		
		String bbstitle=request.getParameter("bbstitle");
		String bbscontent=request.getParameter("bbscontent");
		//HttpSession session = request.getSession();
		//String userid=(String)session.getAttribute("userid");
		String userid="테스트";
		BbsDTO bdto=new BbsDTO();
		BbsDAO bdao=BbsDAO.getInstance();

		bdto.setUserid(userid);
		bdto.setBbstitle(bbstitle);
		bdto.setBbscontent(bbscontent);
		
		int result=bdao.bbsWrite(bdto);
		System.out.print(result);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("bbsList.do");
		dispatcher.forward(request, response);

	}

}
