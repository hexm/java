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
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="西南大学中山校友会2016年年会即将举行，邀请各位中山的校友报名！" />
<link href="favicon.ico" mce_href="favicon.ico" rel="icon"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/xc.css" />
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="js/dwr.synremoter.helper.js"
	charset="UTF-8"></script>
<%!private static final StringBuffer buffer = new StringBuffer();
	private static final String root = "dwr/interface/";%>
<%
	//加载dwr访问的所有对象。此方法必须在dwrServlet初始化成功后才生效

	if (buffer.length() == 0) {
		synchronized (buffer) {
			if (buffer.length() == 0) {
				CreatorManager creatorManager = (CreatorManager) StartupUtil.getSingletonServerContext().getContainer().getBean(
						"org.directwebremoting.extend.CreatorManager");
				for (Iterator<?> i$ = creatorManager.getCreatorNames(false).iterator(); i$.hasNext(); buffer.append("\n")) {
					String name = (String) i$.next();
					Creator creator = creatorManager.getCreator(name, false);
					buffer.append("<script type='text/javascript' src=\"");
					buffer.append(root);
					buffer.append(name);
					buffer.append(".js\" ");
					buffer.append("></script>");
				}
			}
		}
	}

	out.println(buffer);
%>
<script type="text/javascript">
	function addRow(data) {
		var row = document.getElementById("allMemberTable").insertRow(0);
		row.insertCell(0).innerHTML = data[0];
		row.insertCell(1).innerHTML = data[1];
	}

	function deleteTableRows(tb) {
		var rowNum = tb.rows.length;
		for ( var i = 0; i < rowNum; i++) {
			tb.deleteRow(i);
			rowNum = rowNum - 1;
			i = i - 1;
		}
	}

	function loadData() {
		deleteTableRows(document.getElementById("allMemberTable"));

		StudentManager.getAllStudentsNoHide(function(students) {
			document.getElementById("totalCount").innerHTML = students.length;
			for ( var i = 0; i < students.length; i++) {
				addRow([ students[i].name, students[i].phone ]);
			}
		});

		//表格隔行变色
		/*
		var tbl = document.getElementById("allMemberTable");
		var rows = tbl.rows.length;
		for(var i=0;i<rows;i++){
		  if(i%2 == 0){
		     tbl.rows[i].style.backgroundColor = "#cccccc";
		  }
		}
		 */
	}

	function baoming() {
		//alert("dwr begin");
		//StudentManager.addStudent({name:"lijie",phone:"134654"}, addCallBack); 
		var password = document.getElementById("password").value.replace(/^\s+|\s+$/g,
				"");
		if (password != "0109") {
			alert("查询口令不正确！");
			return;
		}
		loadData();
	}

</script>
<title>2016年校友会报名统计</title>
</head>
<body class="zh_CN mm_appmsg">
	<p style="text-align: center; white-space: normal; font-family: 微软雅黑;">
		<strong> <span
			style="font-size: 20px; color: rgb(38, 38, 38);">2016年校友会报名统计 </span> </strong>
	</p>
	<div class="form_ctrl input_text" id="3" title="姓名">
		<label class="ctrl_title">请输入查询口令：</label> <input type="text" id="password"
			value=""
			onmouseover="this.style.borderColor='black';this.style.backgroundColor='plum'"
			onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'"
			style="border-width: 1px; border-color =black; height: 30px; width: 200px; line-height: 30px;" />
	</div>
	<div class="form_ctrl form_submit" id="12">
		<input type="button" name="button" value="报名统计" onClick="baoming()"
			style="border: hidden; width: 100%; box-sizing: border-box; padding: 1.5em 0; background-color: #ff6c00; color: blue; font-weight: bold; border-radius: .15em; font-size: 18px">
	</div>
	<div id="bottom_info">
		已报名人数：<span id="totalCount"></span>
	</div>
	<div id="bottom_jump">
		<table align="center" id="allMemberTable">
		</table>
	</div>
</body>
</html>
