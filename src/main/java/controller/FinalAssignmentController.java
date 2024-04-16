package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FinalAssignmentController
 */
@WebServlet("/")
public class FinalAssignmentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Dao dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalAssignmentController() {
        super();
        dao = new Dao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			request.setAttribute("rows", dao.select());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String url = "WEB-INF/views/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("action").equals("変更")) {
			// 変更ボタン押したので、edit.jsp へ遷移
			String url = "WEB-INF/views/edit.jsp";
			request.setAttribute("JAN_CD", request.getParameter("JAN_CD"));
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			return;
		} else {
			// キャンセルならそのまま一覧画面に戻る
			if (request.getParameter("action").equals("キャンセル")) {
				doGet(request, response);
				return;
			}
			// 変更確認なので、sqlを更新、そして一覧画面に戻る
			try {
				dao.update(request.getParameter("isbnCD"), 
						   request.getParameter("bookNM"), 
						   request.getParameter("price"),
						   (String)request.getParameter("janCD"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			doGet(request, response);
			return;
		}
		
	}

}
