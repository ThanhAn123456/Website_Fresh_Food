package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import database.JDBCUtil;
import database.ProductDAO;
import model.Product;

/**
 * Servlet implementation class updateProduct
 */
@WebServlet("/updateProduct")
public class updateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			ServletContext context = request.getServletContext();
			String realpath = context.getRealPath("/img/product");
			MultipartRequest multi = new MultipartRequest(request, realpath);// "C:/Users/AD/Documents/NetBeansProjects/WebApp_QUANLYBANHANGONLINE/web/images"
			
			//lấy thông tin update
			String idProduct = multi.getParameter("idProduct");
			String nameProduct = multi.getParameter("nameProduct");
			String quantity = multi.getParameter("quantity");
			String oldPrice = multi.getParameter("oldPrice");
			String newPrice = multi.getParameter("newPrice");
			String describe = multi.getParameter("describe");
			String idCategory = multi.getParameter("idCategory");
			String fileName = multi.getFilesystemName("img");
			
			//update vào database
			Product product = new Product(idProduct, nameProduct, Integer.parseInt(quantity),
					Double.parseDouble(oldPrice), Double.parseDouble(newPrice), fileName, describe, idCategory);
			ProductDAO.getInstance().update(product);
			
			//chuyển trang
			response.sendRedirect("product-management.jsp");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
