<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Laba2</title>
</head>
<body>
<form action="uploadServlet" method="post" enctype="multipart/form-data">
  <input type="file" name="file">
  <input type="submit" value="Upload">
</form>
<a href="listFilesServlet">List of uploaded files</a>

</body>
</html>