<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>
Tech-freaks.com - Struts2 - SimpleLogin App Login Page
</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<s:property value="errorMsg"/><br/>
<s:form method="POST" action="logon">
	<s:textfield  name="userName" label="User Name"/>
	<s:password  name="password" label="Password" />
	<s:submit name="submit" label="Submit" align="center" />
		
</s:form>
</body>
</html>