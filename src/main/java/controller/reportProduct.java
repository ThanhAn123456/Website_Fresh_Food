package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import database.CategoryDAO;
import database.ProductDAO;
import model.Product;

/**
 * Servlet implementation class reportProduct
 */
@WebServlet("/reportProduct")
public class reportProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//thiết lập xuất file pdf
		response.setCharacterEncoding("UTF-8");
   	 	response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf\"");
		String idCategory = request.getParameter("idCategory");
		
		try (OutputStream outputStream = response.getOutputStream()) {
			//tạo document để xuất file pdf
			Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            
            //Thêm font chữ để hiển thị đc tiếng việt
            String fontPath = getServletContext().getRealPath("/WEB-INF/resources/ArialUnicodeMS.ttf"); // Đường dẫn đến tệp font Unicode
            BaseFont unicodeBaseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font unicodeFont = new Font(unicodeBaseFont, 12);
            
            //Thêm tiêu đề
            Paragraph title = new Paragraph("Thông tin sản phẩm danh mục " + CategoryDAO.getInstance().getCategoryById(idCategory).getNameCategory(),unicodeFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));
            
            
            //tạo table
            PdfPTable table = new PdfPTable(5);
            
            //thêm tiêu đề cho mỗi cột
            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm",unicodeFont)));
            table.addCell(new PdfPCell(new Phrase("Số lượng",unicodeFont)));
            table.addCell(new PdfPCell(new Phrase("Giá cũ",unicodeFont)));
            table.addCell(new PdfPCell(new Phrase("Giá mới",unicodeFont)));
            table.addCell(new PdfPCell(new Phrase("Danh mục",unicodeFont)));
            
            // thêm dữ liệu cho mỗi cột
            ArrayList<Product> listProduct = ProductDAO.getInstance().getAllProduct();
            for (Product product : listProduct) {
            	if(product.getIdCategory().equals(idCategory)) {
            		table.addCell(product.getNameProduct());
                	table.addCell(String.valueOf(product.getQuantity()));
                	table.addCell(String.valueOf(product.getOldPrice()));
                	table.addCell(String.valueOf(product.getNewPrice()));
                	table.addCell(String.valueOf(CategoryDAO.getInstance().getCategoryById(product.getIdCategory()).getNameCategory()));
            	}
			}
            
            document.add(table);
            document.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
