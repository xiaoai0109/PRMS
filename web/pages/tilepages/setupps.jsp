<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <fmt:setBundle basename="ApplicationResources" />

    <title><fmt:message key="title.setupps" /></title>
</head>
<body>
<h1><fmt:message key="label.setupps"/></h1>

<form action="${pageContext.request.contextPath}/nocturne/enterps" method=post>
    <%--<center>--%>
        <table cellpadding=4 cellspacing=2 border=0>
            <%--<tr>--%>
                <%--<th width="30%"><fmt:message key="label.crudps.rpname" /></th>--%>
                <%--<th width="45%"><fmt:message key="label.crudps.date" /></th>--%>
                <%--<th width="45%"><fmt:message key="label.crudps.sttime" /></th>--%>
                <%--<th width="25%"><fmt:message key="label.crudps.duration" /></th>--%>
            <%--</tr>--%>
            <tr>
                <td><fmt:message key="label.crudps.rpname" /></td>
                <td><input type="text" name="rpname" value="${param['rpname']}" size=15
                           maxlength=20 readonly="readonly">
                    <c:url var="url" scope="page" value="/nocturne/selectrp">
                        <c:param name="rpname" value=""/>
                        <c:param name="date" value="${param['date']}"/>
                        <c:param name="oldDate" value="${param['oldDate']}"/>
                        <c:param name="sttime" value="${param['sttime']}"/>
                        <c:param name="oldSttime" value="${param['oldSttime']}"/>
                        <c:param name="duration" value="${param['duration']}"/>
                        <c:param name="presenter" value="${param['presenter']}"/>
                        <c:param name="producer" value="${param['producer']}"/>
                        <c:param name="insert" value="${param['insert']}"/>
                        <%--<c:param name="error" value="false"/>--%>
                    </c:url>
                    <a href="${url}"><fmt:message key="label.setupps.selectrp"/></a>
                    <input type="hidden" name="ins" value="${param['insert']}" />
                    <%--<input name="error" value="${param['error']}" />--%>
                </td>
                <%--<td><c:if test="${param['insert'] == 'true'}">--%>
                    <%--<input type="text" name="rpname" value="${param['rpname']}" size=15--%>
                           <%--maxlength=20>--%>
                    <%--<input type="hidden" name="ins" value="true" />--%>
                <%--</c:if>--%>
                    <%--<c:if test="${param['insert']=='false'}">--%>
                        <%--<input type="text" name="rpname" value="${param['rpname']}" size=15--%>
                               <%--maxlength=20 readonly="readonly">--%>
                        <%--<input type="hidden" name="ins" value="false" />--%>
                    <%--</c:if></td>--%>
            </tr>
            <tr>
                <td><fmt:message key="label.crudps.date" /></td>
                <td><input type="text" name="date"
                           value="${param['date']}" size=15 maxlength=20>
                    <input type="hidden" name="oldDate" value="${param['oldDate']}" /></td>
            </tr>
            <tr>
                <td><fmt:message key="label.crudps.sttime" /></td>
                <td><input type="text" name="sttime"
                           value="${param['sttime']}" size=15 maxlength=20>
                    <input type="hidden" name="oldSttime" value="${param['oldSttime']}" /></td>
            </tr>
            <tr>
                <td><fmt:message key="label.crudps.duration" /></td>
                <td><input type="text" name="duration"
                           value="${param['duration']}" size=15 maxlength=20></td>
            </tr>
            <tr>
                <td><fmt:message key="label.crudps.presenter" /></td>
                <td><input type="text" name="presenter"
                           value="${param['presenter']}" size=15 maxlength=20></td>
            </tr>
            <tr>
                <td><fmt:message key="label.crudps.producer" /></td>
                <td><input type="text" name="producer"
                           value="${param['presenter']}" size=15 maxlength=20></td>
            </tr>
            <%--<tr>--%>
                <%--<c:if test="${param['error'] == 'true'}">--%>
                    <%--<td><fmt:message key="label.crudps.errMessage" /></td>--%>
                <%--</c:if>--%>
                <%--<c:if test="${param['error'] == 'false'}">--%>
                    <%--<td></td>--%>
                <%--</c:if>--%>
            <%--</tr>--%>
            <tr>
                <td><input type="submit" value="Submit"></td>
                <td><input type="reset" value="Reset"></td>
            </tr>
        </table>
    <%--</center>--%>

</form>

</body>
</html>