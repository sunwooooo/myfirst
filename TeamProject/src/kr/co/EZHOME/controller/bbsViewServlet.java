package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.BbsDAO;
import kr.co.EZHOME.dto.BbsDTO;

/**
 * Servlet implementation class bbsViewServlet
 */
@WebServlet("/bbsView.do")
public class bbsViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bbsViewServlet() {
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
		
		String bbsid = request.getParameter("bbsid");
		
		BbsDAO bdao=BbsDAO.getInstance();
		BbsDTO bdto=new BbsDTO();
		Vector<BbsDTO> vec=new Vector<BbsDTO>();
		
			bdto=bdao.findUser(bbsid);
			vec.add(bdto);
			
		//String content = bdto.getBbscontent();
		//String title = bdto.getBbstitle();
		
			request.setAttribute("vec", vec);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("/managePage/bbsView.jsp");
			dispatcher.forward(request, response);
			
		
		
	}

}
