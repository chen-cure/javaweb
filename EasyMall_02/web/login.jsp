<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/11/4
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css"/>
    <title>EasyMall欢迎您登陆</title>
</head>
<%
    Cookie[] cs =request.getCookies();
    Cookie findc = null;
    if (cs!=null){
        for(Cookie c :cs){
            if ("remname".equals(c.getName())){
                findc =c ;
                break;
            }
        }
    }
    String username="";
    if (findc!=null){
        username= URLDecoder.decode(findc.getValue(),"utf-8");
    }
%>
<body>
<h1>欢迎登陆EasyMall</h1>
<form action="<%=request.getContextPath()%>/servlet/LoginServlet" method="POST">
    <table>
        <tr>
            <td colspan="2" style="color: #D9141E;text-align: center">
                <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
            </td>
        </tr>
        <tr>
            <td class="tdx">用户名:</td>
            <td><input type="text" name="username" value="<%=username%>"/></td>
        </tr>
        <tr>
            <td class="tdx">密码:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="checkbox" name="remname" value="true"
                        <%if(findc!=null){%>
                            checked="checked"
                        <%}%>

                />记住用户名
                <input type="checkbox" name="autologin" value="true"/>30天内自动登陆
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登陆"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

