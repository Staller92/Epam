<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="app.Phonebook" %>
<%@ page import="app.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core"
      xmlns:h="http://java.sun.com/jsf/html">
<head>
    <%
        HashMap<String, String> jsp_parameters = new HashMap<String, String>();
        Person person = new Person();
        String user_message;
        String error_message;
        String title;
        if (request.getAttribute("jsp_parameters") != null) {
            jsp_parameters = (HashMap<String, String>) request.getAttribute("jsp_parameters");
        }

        if (request.getAttribute("person") != null) {
            person = (Person) request.getAttribute("person");
        }

        user_message = jsp_parameters.get("current_action_result_label");
        error_message = jsp_parameters.get("error_message");
        title = jsp_parameters.get("title");
    %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><%=title%>
    </title>
</head>
<body>


<form action="<%=request.getContextPath()%>/ManagePhoneServlet/" method="post">
    <input type="hidden" name="personId" value="<%=person.getId()%>"/>
    <input type="hidden" name="numberId" value="<%=jsp_parameters.get("numberId")%>"/>
    <table align="center" border="1" width="50%">
        <%
            if ((user_message != null) && (!user_message.equals(""))) {
        %>
        <tr>
            <td colspan="6" align="center"><%=user_message%>
            </td>
        </tr>
        <%
            }
        %>
        <%
            if ((error_message != null) && (!error_message.equals(""))) {
        %>
        <tr>
            <td colspan="2" align="center"><span style="color:red"><%=error_message%></span></td>
        </tr>
        <%
            }
        %>

        <tr>
            <td colspan="2">Информация о телефоне
                владельца: <%=person.getSurname()%>&nbsp;<%=person.getName()%>&nbsp;<%=person.getMiddlename()%>
            </td>
        </tr>
        <tr>
            <td>Номер:</td>
            <td><input type="text" name="phoneNumber" value="<%=jsp_parameters.get("editable_number")%>"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" name="<%=jsp_parameters.get("next_action")%>"
                       value="<%=jsp_parameters.get("next_action_label")%>"/></br>
                <a href="<%=request.getContextPath()%>/?action=edit&id=<%=person.getId()%>">Вернуться к данным о
                    человеке</a>
            </td>

        </tr>
    </table>
</form>
</body>
</html>