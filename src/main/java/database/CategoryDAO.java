package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Category;
import model.Product;

public class CategoryDAO {
	public static CategoryDAO getInstance() {
		return new CategoryDAO();
	}
	
	public boolean insert(Category category) {
		Connection con=JDBCUtil.getConnection();
		try {
			String sql = "insert into Category "
					+ "values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setObject(1, category.getIdCategory());
			pst.setObject(2, category.getNameCategory());
			pst.setObject(3, category.getDescribe());
			pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public boolean update(Category category) {
		Connection con = JDBCUtil.getConnection();
		try {
			String sql = "update Category set nameCategory=?, describe=? where idCategory=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setObject(1, category.getNameCategory());
			pst.setObject(2, category.getDescribe());
			pst.setObject(3, category.getIdCategory());
			pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public boolean delete(Category category) {
		Connection con = JDBCUtil.getConnection();
		try {
			String sql = "delete Category where idCategory=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setObject(1, category.getIdCategory());
			pst.executeUpdate();
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> listCategories = new ArrayList<>();
		Connection con = JDBCUtil.getConnection();
		try {
			String sql = "select * from Category";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idCategory = rs.getString("idCategory");
				String nameCategory = rs.getString("nameCategory");
				String describe = rs.getString("describe");
				Category category = new Category(idCategory, nameCategory, describe);
				listCategories.add(category);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCategories;
	}
	
	public Category getCategoryById(String id) {
		Connection con = JDBCUtil.getConnection();
		Category category = null;
		try {
			String sql = "select * from Category where idCategory = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setObject(1, id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			String idCategory = rs.getString("idCategory");
			String nameCategory = rs.getString("nameCategory");
			String describe = rs.getString("describe");
			category = new Category(idCategory,nameCategory,describe);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	//lấy id sản phẩm cho thống kê doanh thu
	public ArrayList<String> getIdProductRevenue() {
		ArrayList<String> listIdProduct = new ArrayList<>();
		Connection con = JDBCUtil.getConnection();
		try {
			String sql = "SELECT P.idProduct,SUM(S.quantityBuy) AS TotalQuantity,SUM(S.quantityBuy * P.newPrice) AS Revenue\r\n"
					+ "FROM Product P\r\n"
					+ "JOIN ShoppingCart S ON P.idProduct = S.idProduct\r\n"
					+ "GROUP BY P.idProduct, P.nameProduct, P.newPrice;";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idCategory = rs.getString("idProduct");
				listIdProduct.add(idCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listIdProduct;
	}
	
	// lấy doanh thu sản phẩm
	public ArrayList<Double> getValueProductRevenue() {
		ArrayList<Double> listValueProduct = new ArrayList<>();
		Connection con = JDBCUtil.getConnection();
		try {
			String sql = "SELECT P.idProduct,SUM(S.quantityBuy) AS TotalQuantity,SUM(S.quantityBuy * P.newPrice) AS Revenue\r\n"
					+ "FROM Product P\r\n"
					+ "JOIN ShoppingCart S ON P.idProduct = S.idProduct\r\n"
					+ "GROUP BY P.idProduct, P.nameProduct, P.newPrice;";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Double revenue = rs.getDouble("Revenue");
				listValueProduct.add(revenue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listValueProduct;
	}
	
	//lấy số lượng bán của mỗi sản phẩm
	public ArrayList<Integer> getQuantityProductSelling() {
		ArrayList<Integer> listQuantityProduct = new ArrayList<>();
		Connection con = JDBCUtil.getConnection();
		try {
			String sql = "SELECT P.idProduct,SUM(S.quantityBuy) AS TotalQuantity,SUM(S.quantityBuy * P.newPrice) AS Revenue\r\n"
					+ "FROM Product P\r\n"
					+ "JOIN ShoppingCart S ON P.idProduct = S.idProduct\r\n"
					+ "GROUP BY P.idProduct, P.nameProduct, P.newPrice;";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Integer TotalQuantity = rs.getInt("TotalQuantity");
				listQuantityProduct.add(TotalQuantity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listQuantityProduct;
	}
}
