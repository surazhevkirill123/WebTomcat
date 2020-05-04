<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Laba2</title>
</head>

<body>
<form>
  Введите текст сообщения<input type="text" name = "messageText" > <br> <br>
  Введите адрес отправителя<input type="text" name = "senderAddress" > <br> <br>

  Введите адрес получателя<input type="text" name = "recipientAddress" > <br> <br>
  <input type="submit">
</form>
<p><%
  try{

    String product = (String) request.getAttribute("product");
    if (product!=null)
    out.println("Итог: " + product);


  }
  catch (Exception e){
    out.println("Итог:... " );
  }
%></p>
</body>
</html>