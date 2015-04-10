<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Expense System</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <script src="resources/js/jquery-2.1.1.min.js" type="text/javascript"></script>
        <script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body class="container-fluid">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <h1 style="display:inline">Expense System</h1>
                    <c:if test='${sessionScope.currentUser.userID != ""}'>
                        <p>User: <c:out value="${sessionScope.currentUser.username}"/></p>
                    </c:if>
                </div>
            </div><!-- /.container-fluid -->
        </nav>

        <c:if test="${message != null}">
            <div class="row alert-danger">
                <p><c:out value="${message}" /></p>
            </div>
        </c:if>