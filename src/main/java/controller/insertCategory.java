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
import database.ProductDAO;
import model.Category;
import model.Product;

/**
 * Servlet implementation class insertCategory
 */
@WebServlet("/insertCategory")
public class insertCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//lấy dữ liệu
		String nameCategory = request.getParameter("nameCategory");
		String describe = request.getParameter("describe");
		Connection con = JDBCUtil.getConnection();
		String idCategory = null;
		
		// tìm mã danh mục tiếp theo
		try {
			String sql = "select max(idCategory) idCategory from Category";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String maxId = rs.getString("idCategory");
				if (maxId != null) {
					int num = Integer.parseInt(maxId.substring(2)) + 1;
					idCategory = String.format("DM%03d", num);
				} else {
					idCategory = "DM001";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//tạo đối tượng Product để chứa dữ liệu
		Category category = new Category(idCategory,nameCategory,describe);
		
		//Thêm product
		CategoryDAO.getInstance().insert(category);
		
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
