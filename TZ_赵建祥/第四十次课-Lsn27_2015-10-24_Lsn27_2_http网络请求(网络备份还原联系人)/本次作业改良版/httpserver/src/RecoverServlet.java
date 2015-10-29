import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecoverServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		File file=new File("D:/contacts.json");
		FileInputStream fos=new FileInputStream(file);
		byte[] buffer=new byte[1024];
		int len=0;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		while((len=fos.read(buffer))!=-1){
			baos.write(buffer,0,len);
		}
		String json= baos.toString();
		resp.getWriter().write(json);

	}
}
