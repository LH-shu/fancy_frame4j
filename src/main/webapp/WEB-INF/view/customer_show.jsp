<%@ page import="pers.fancy.customer.model.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>查看客户界面</h1>
<%
    Customer customer = (Customer) request.getAttribute("customer");
%>
<h1><%=customer.getName()%></h1>
<h1><%=customer.getContact()%></h1>
</body>
</html>