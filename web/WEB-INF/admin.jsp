<%-- 
    Document   : admin
    Created on : May 30, 2020, 9:21:51 AM
    Author     : dungntmse63284
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ADMIN</h1>
        <p>${sessionScope.USER}</p>
        <form method="POST" action="AdminController">
            <input name="btnAction" value="CrawlStock" type="hidden"/>
            <button>Lấy dữ liệu stock</button>
            
        </form>
        <form method="POST" action="AdminController">
            
            <input name="btnAction" value="CrawlName" type="hidden"/>
            <button>Lấy dữ liệu stock - name</button>
        </form>
        <form method="POST" action="AdminController">
            
            <input name="btnAction" value="CrawlBank" type="hidden"/>
            <button>Lấy dữ liệu bank</button>
        </form>
    </body>
</html>
<!--
<input name="btnAction" value="CrawlName" type="hidden"/>
            <button>Lấy dữ liệu cafef - Name</button>
            <input name="btnAction" value="CrawlBeta" type="hidden"/>
            <button>Lấy dữ liệu cafef - Beta</button>
-->