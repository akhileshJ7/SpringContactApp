<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List - Contact Application </title>
         <s:url var="url_css" value="/static/css/style.css"/>
        <link href="${url_css}" rel="stylesheet" type="text/css"/>
         <s:url var="url_jqlib" value="/static/js/jquery-3.5.0.min.js"  />
        <script src="${url_jqlib}"></script>
        <script>
            function changeStatus(uid, lstatus){
                //alert(userId+", "+loginStatus);
                $.ajax({
                    url:'change_status',
                    data:{userId:uid, loginStatus:lstatus} ,
                    success: function (data) {  
                        alert(data);
                    }
                });
            }
        </script>
    </head>
    <s:url var="url_bg" value="/static/image/bg.jpg"/>
    <body background="${url_bg}">
        <table border="1" width="80%" align="center">
            <tr>
                <td height="80px">
                    <%-- Header --%>
                    <jsp:include page="include/header.jsp"/>
                </td>
            </tr>
            <tr>
                <td height="25px">
                     <%-- Menu --%>
                     <jsp:include page="include/menu.jsp"/>
                </td>
            </tr>
            <tr>
                <td height="350px" valign="top">
                     <%-- Page Content Area--%>
                     <h3>Users List</h3>
                     <table border="1" cellpadding="3">
                         <tr>
                            <th>S.No.</th>
                            <th>User ID</th>
                            <th>Name</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Username</th>
                            <th>Status</th>
                         </tr>
                         <c:forEach var="u" items="${userList}" varStatus="st">
                            <tr>
                                <td>${st.count}</td>
                                <td>${u.userId}</td>
                                <td>${u.name}</td>
                                <td>${u.phone}</td>
                                <td>${u.address}</td>
                                <td>${u.loginName}</td>
                                <td>${u.loginStatus}</td>
                                <td>
                                     <select id="id_${u.userId}" onchange="changeStatus(${u.userId},$(this).val())">
                                        <option value="1">Active</option>
                                        <option value="2">Block</option>
                                    </select>   
                                    <script>
                                           $('#id_${u.userId}').val(${u.loginStatus});
                                    </script>
                                    
                                </td>
                            </tr>
                         </c:forEach>
                     </table>
                </td>
            </tr>
             <tr>
                <td height="25px">
                     <%-- Footer --%>
                     <jsp:include page="include/footer.jsp"/>
                </td>
            </tr>
        </table>
    </body>
</html>