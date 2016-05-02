<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="org.directwebremoting.extend.*"%>
<%@ page import="org.directwebremoting.ServerContext"%>
<%@ page import="org.directwebremoting.Container"%>
<%@ page import="java.io.*"%>
<%@ page import="org.directwebremoting.impl.StartupUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icbc.hexm.swu.DwrTest"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="Content-Style-Type" content="text/css">
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/interface/service.js"></script>
<script type="text/javascript" src="dwr/interface/DwrTest2.js"></script>
<script type="text/javascript" src="js/dwr.synremoter.helper.js" charset="UTF-8"></script>
<script type="text/javascript">
      function firstDwr(){
        DwrTest2.sayHello("lijie",callBack); 
      }
      function callBack(data){
       alert(data); 
      }
     </script>

</head>
<title>Products</title>
<body>
hello 工苦革123
<input type="button" name="button" value="test1" onclick="firstDwr()">
</body>
</html>