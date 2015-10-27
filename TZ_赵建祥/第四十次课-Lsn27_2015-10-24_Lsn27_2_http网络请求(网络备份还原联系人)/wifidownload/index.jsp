<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//String appdir=request.getSession().getServletContext().getRealPath("/");

String filedir=request.getParameter("filedir");
//System.out.println(appdir+"download");
File f = new File(filedir); //new File(appdir+"download");
if(!f.exists()){
	f.mkdir();
}
 String[] filelist = f.list();
 String files="";
 boolean isFirst=true;
 for (int i = 0; i < filelist.length; i++) {
	 	long s=0;
	 	System.out.println(filedir+"/"+filelist[i]);
	 	File fs=new File(filedir+"/"+filelist[i]);
	    if (fs.exists()) {
	        FileInputStream fis = null;
	        fis = new FileInputStream(fs);
	       	s= fis.available();
	    } else {
	        continue;
	    }
	 	if(isFirst){
	 		files+=filelist[i]+"|"+s;
	 		isFirst=false;
	 	}
	 	else{
	 		files+=","+filelist[i]+"|"+s;
	 		
	 	}
 }
response.getWriter().write(files);
%>
