<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Real Time Optimal Fulfillment</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container">
    <div class="login_area">
    <div class="login_logo">Real Time Optimal Fulfillment</div>
        <div class="login_area_content">
        <s:form action="login" namespace="/" method="post" id="form">
        <table cellspacing="0" cellpadding="0" class="login_table">
            <tr>
            	<jsp:include page="errors.jsp" />
                <td class="text_status">Username:</td>
                <td><input type="text" name="username"/></td>
                 <td rowspan="2"><input type="submit" value="Login"  style="width:60px"/> </td>
            </tr>
            <tr>
                <td class="text_status">Password:</td>
                <td><input type="text" name="password"/></td>
                <td></td>
            </tr>
        </table>
        </s:form>		    
        </div>
        <div class="login_notice">
		        <font size="4"><s:actionerror /></font>
		</div>
        <div class="login_area_bottom"><div class="login_area_bottom_left"></div>Copyright © 2013 eBusiness Team<div class="login_area_bottom_right"></div></div>
    </div>
</div>
</body>
</html>