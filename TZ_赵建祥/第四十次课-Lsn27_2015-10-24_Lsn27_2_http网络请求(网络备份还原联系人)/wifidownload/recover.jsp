<%@page pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,java.io.*,java.net.*,org.apache.commons.lang.*" %>
<%

String json="[{\"email\":\"451966221@qq.com\",\"tel\":\"152 5180 1723\",\"name\":\"jzhao\",\"phone\":\"587 2903\",\"id\":1},{\"email\":\"825053@qq.com\",\"tel\":\"159 5191 8594\",\"name\":\"shu\",\"phone\":\"587 4130\",\"id\":2}]";
System.out.println(json);
out.write(json);
%>
