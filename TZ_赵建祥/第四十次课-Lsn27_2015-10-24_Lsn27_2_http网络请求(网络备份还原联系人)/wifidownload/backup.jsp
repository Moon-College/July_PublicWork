<%@page pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,java.io.*,java.net.*,org.apache.commons.lang.*" %>
<%

String json=new String(request.getParameter("json").getBytes("iso-8859-1"),"UTF-8");
System.out.println(json);
out.write(json);
%>
