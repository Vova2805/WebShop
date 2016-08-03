<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <c:set var="url">${req.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="shortcut icon" type="image/icon" href="/resources/img/res/logo_small.png"/>


    <link href="/resources/css/bootstrap.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.simpleLens.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/slick.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/resources/css/site.css">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/sequence-theme.modern-slide-in.css" rel="stylesheet" media="all">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
    <link href="/resources/css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/jquery.smartmenus.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.smartmenus.bootstrap.js"></script>
    <script src="resources/js/site.js"></script>


</head>
<body>
<jsp:include page="../modal/modalWrapper.jsp"/>
<jsp:include page="../modal/loginModal.jsp"/>
</html>