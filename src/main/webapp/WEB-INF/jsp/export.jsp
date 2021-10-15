<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Book</title>
    </head>
    <body>
        <c:url var="export_url" value="/ru/export"/>
        <form:form action="${export_url}" method="post" modelAttribute="requestDTO">
            <form:label path="number">number: </form:label> <form:input type="text" path="number"/>
            <form:label path="name">Name: </form:label> <form:input type="text" path="name"/>
            <input type="submit" value="submit"/>
        </form:form>
    </body>
</html>