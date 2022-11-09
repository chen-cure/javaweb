<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2022/11/1
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/regist.css"/>
    <script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
    <script type="application/javascript">
        function setMsg(id,msg,color="red"){
            document.getElementById(id+"_msg").innerText=msg;
            document.getElementById(id+"_msg").style.color=color;
        }
        function checkForm() {
            var canSub = true;
            canSub = checkNull("username","用户名不能为空") && canSub;
            canSub = checkNull("password","密码不能为空") && canSub;
            canSub = checkNull("password2","确认密码不能为空") && canSub;
            canSub = checkNull("nickname","昵称不能为空") && canSub;
            canSub = checkNull("email","邮箱不能为空") && canSub;
            canSub = checkNull("valistr","验证码不能为空") && canSub;
            var pwd1 = document.getElementsByName("password")[0].value;
            var pwd2 = document.getElementsByName("password2")[0].value;
            if(pwd1!=null&&pwd1!=''&&pwd2!=null&&pwd2!=''&&pwd1!=pwd2){
                setMsg("password2","密码不一致");
                canSub = false;
            }

            var email = document.getElementsByName("email")[0].value;
            if(email!=null && email!=""&& !/^\w+@\w+(\.\w+)+$/.test(email)){
                setMsg("email","邮箱格式不正确");
                canSub=false;
            }
            return canSub;
        }

        function checkNull(name,msg){
            setMsg(name,"");
            var v = document.getElementsByName(name)[0].value;
            if(v==null||v==""){
                setMsg(name,msg);
                return false;
            }
            return true;
        }
        function checkPwd2(){
            setMsg("password2",'');
            var pwd1 = document.getElementsByName('password')[0].value;
            var pwd2 = document.getElementsByName("password2")[0].value;
            if(pwd2==null||pwd2==''){
                setMsg("password2",'确认密码不能为空');
                return;
            }
            if (pwd1!=null&&pwd1!=''&&pwd2!=null&&pwd2!=''&&pwd2!=pwd1){
                setMsg("password2","两次密码不一致");
                return;
            }
        }
        function checkEmail(){
            setMsg("email","");
            var email = document.getElementsByName("email")[0].value;
            if(email==null||email==''){
                setMsg("email","邮箱不能为空");
                return;
            }
            if (email!=null&&email!=''&&!/^\w+@\w+(\.\w+)+$/.test(email)){
                setMsg("email","邮箱格式不正确");
            }
        }
        function changeImage(ImaObj) {
            ImaObj.src="<%=request.getContextPath()%>/servlet/VerifyServlet?time="+new Date().getTime();
        }
        function checkUsername(){
            var isNotNULL=checkNull('username','用户名不能为空');
            // alert("1111")
            if(isNotNULL){
                var username = document.getElementsByName("username")[0].value;
                // alert(username)
                $.get("<%=request.getContextPath()%>/servlet/AjaxHasUserNameServlet",{"username":username},function (msg) {
                    // alert(msg)
                    setMsg("username",msg,msg=="用户名可使用"?"green":"red");
                });
                // alert(username)
            }
        }
    </script>
</head>
<body>
<h1>欢迎注册EasyMall</h1>
<form action="<%=request.getContextPath()%>/servlet/RegistServlet" method="POST" onsubmit="return checkForm()" >
    <table>
        <tr>
            <td colspan="2" style="color: #D9141E;text-align: center">
                <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
            </td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username" onblur="checkUsername()" value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>">
                <span style="color: #D9141E" id="username_msg"/>
            </td>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password" onblur="checkNull('password','密码不能为空')">
                <span style="color: #D9141E" id="password_msg"/>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2" onblur="checkPwd2()">
                <span style="color: #D9141E" id="password2_msg"/>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname" onblur="checkNull('nickname','昵称不能为空')"  value="<%=request.getParameter("nickname")==null?"":request.getParameter("nickname")%>">
                <span style="color: #D9141E" id="nickname_msg"/>
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email" onblur="checkNull('email','邮箱不能为空')" value="<%=request.getParameter("email")==null?"":request.getParameter("email")%>">
                <span style="color: #D9141E" id="email_msg"/>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr" onblur="checkEmail()">
                <img id="yzm_img" src="<%=request.getContextPath()%>/servlet/VerifyServlet" style="cursor: pointer" onclick="changeImage(this)" />
                <span style="color: #D9141E" id="valistr_msg"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="注册用户"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

