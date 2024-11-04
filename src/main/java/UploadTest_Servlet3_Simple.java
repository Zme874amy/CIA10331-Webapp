import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/back-end/event/uploadServlet3_simple.do")
@MultipartConfig
public class UploadTest_Servlet3_Simple extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();


		Part part = req.getPart("upfile1");
		String dir = getServletContext().getRealPath("/back-end/event/images_upload");
		System.out.println(dir);
		String filename = part.getSubmittedFileName();
		System.out.println(filename);
		part.write(dir + "/" + filename);
		out.println("<br><img src=\""+req.getContextPath()+"/back-end/event/images_upload/"+filename+"\">");


//		req.getPart("upfile1").write(getServletContext().getRealPath("/images_uploaded") + "/file.gif");

	}
}