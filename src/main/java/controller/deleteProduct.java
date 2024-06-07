package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import database.ProductDAO;
import database.ShoppingCartDAO;
import model.Product;
import model.ShoppingCart;

/**
 * Servlet implementation class deleteProduct
 */
@WebServlet("/deleteProduct")
public class deleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter out = response.getWriter()) {
			ServletContext context = request.getServletContext();
			String realpath = context.getRealPath("/img/product");
			MultipartRequest multi = new MultipartRequest(request, realpath);// "C:/Users/AD/Documents/NetBeansProjects/WebApp_QUANLYBANHANGONLINE/web/images"
			
			//lấy idProduct
			String idProduct = multi.getParameter("idProduct");
			
			
			Product product = new Product();
			product.setIdProduct(idProduct);
			
			//xóa trong database
			ProductDAO.getInstance().delete(product);
			
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
