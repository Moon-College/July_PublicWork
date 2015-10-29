<%@page pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,java.io.*,java.net.*" %>
<%

String json=new String(request.getParameter("json").getBytes("iso-8859-1"),"UTF-8");
File file=new File("D:/contacts.json");
FileOutputStream fos=new FileOutputStream(file);
fos.write(json.getBytes());
fos.close();
out.write(json);
%>
