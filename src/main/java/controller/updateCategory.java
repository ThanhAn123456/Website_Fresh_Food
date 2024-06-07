package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CategoryDAO;
import database.JDBCUtil;
import model.Category;

/**
 * Servlet implementation class updateCategory
 */
@WebServlet("/updateCategory")
public class updateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//lấy thông tin update
		String idCategory = request.getParameter("idCategory");
		String nameCategory = request.getParameter("nameCategory");
		String describe = request.getParameter("describe");
		
		//update dữ liệu
		Category category = new Category(idCategory,nameCategory,describe);				
		CategoryDAO.getInstance().update(category);
		
		//chuyển trang
		response.sendRedirect("category-management.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
