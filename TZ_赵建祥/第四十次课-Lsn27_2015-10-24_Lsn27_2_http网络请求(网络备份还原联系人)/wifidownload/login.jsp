<%@page pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,java.io.*,java.net.*,org.apache.commons.lang.*" %>
<%

String filepath=new String(request.getParameter("filepath").getBytes("iso-8859-1"),"UTF-8");
int err_notfound = 404;
try
{
	err_notfound = Integer.parseInt(getInitParameter("err_notfound"));
}
catch (Exception e)
{
}
File file = new File(filepath);
if( !(file.exists()) )
{
	response.sendError(err_notfound);
	return;
}
// 设置响应头，200,206支持断点续传  
response.reset();
response.setStatus(200);
String mime = "application/octet-stream";
response.setContentType(mime);
response.setCharacterEncoding("UTF-8");
 //文件名用ISO08859_1编码 
String downName = filepath.substring(filepath.lastIndexOf("/")+1);
response.addHeader("Content-Disposition", "attachment;filename=" + downName);
try {  
    //下载起始位置  
    long since=0;  
    //下载结束位置  
    long until=file.length()-1;  
    //获取Range，下载范围  
    String range=request.getHeader("range");
    if( range !=null ){
        //剖解range
        range=range.split("=")[1];
        String[] rs=range.split("-");
        since=Long.parseLong(rs[0]);
        if( rs.length>1){
            until=Long.parseLong(rs[1]);  
        }
    }  
    //设置响应头
    response.setHeader("Accept-Ranges", "bytes");
    response.setHeader("Content-Range", "bytes "+since+"-"+ until + "/" + file.length());  
    response.setHeader("Content-Length", "" + (until-since+1)); 
    //定位到请求位置 
    OutputStream os = response.getOutputStream();
	FileInputStream fis = new FileInputStream(file);
	fis.skip(since); 
    int len = 0;
    long haveReadLen = since;
    //读取，输出流  
    byte[] buffer=new byte[4096]; 
    while( (len=fis.read(buffer)) >0 ){
    	if( (haveReadLen+len) > until ){
    		len = Long.valueOf(until-haveReadLen).intValue()+1;
    	}
        os.write(buffer, 0, len);
    	haveReadLen += len;
    	if( haveReadLen >= until ){
    		break;
    	}
    }
    //输出  
    os.flush();
    os.close();
    fis.close();
} catch (java.net.SocketException e) {  
	response.sendError(err_notfound);
	return;
}  
%>
