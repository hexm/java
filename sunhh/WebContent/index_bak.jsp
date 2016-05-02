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
<meta name="description" content="���ϴ�ѧ��ɽУ�ѻ�2016����ἴ�����У������λ��ɽ��У���ڴ˱�����" />
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
	//����dwr���ʵ����ж��󡣴˷���������dwrServlet��ʼ���ɹ������Ч

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

		StudentManager.getAllStudents(function(students) {
			document.getElementById("totalCount").innerHTML = students.length;
			for ( var i = 0; i < students.length; i++) {
				addRow([ students[i].name, students[i].phone ]);
			}
		});

		//�����б�ɫ
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
		var name = document.getElementById("name").value.replace(/^\s+|\s+$/g, "");
		var phone = document.getElementById("phone").value;
		if (name == "" || phone == "") {
			alert("�������������ֻ�������ٱ�����лл��");
			return;
		}
		if (StudentManager.findStudentByName(name) != null) {
			alert(name + "�Ѿ������ɹ����벻Ҫ�ظ�������лл��");
			return;
		}
		if (!phone.match(/^[0-9]+$/g) || (phone.length != 11)) {
			alert("��������ȷ���ֻ����룬лл��");
			return;
		}
		var collage = document.getElementById("collage");
		var collegeName = collage.options[collage.selectedIndex].innerText;
		if(collegeName==="��ѡ��") {
			alert("��ѡ��ѧԺ��лл��");
			return;
		}
		var student = StudentManager.getNewStudent();
		student.name = name;
		student.phone = phone;
		student.college = collegeName;
		StudentManager.addStudent(student, addCallBack);
	}

	function addCallBack(msg) {
		//addRow(["hexm","13543"]);
		if (msg === "ok") {
			alert("�����ɹ���");
			loadData();
		} else {
			alert(msg);
		}
	}
</script>
<title>���ϴ�ѧ��ɽУ�ѻ�2016���֪ͨ</title>
</head>
<body onLoad="loadData()" class="zh_CN mm_appmsg">
	<div id="js_article" class="rich_media">
		<div id="js_top_ad_area" class="top_banner"></div>
		<div class="rich_media_inner">
			<div id="page-content">
				<div id="img-content" class="rich_media_area_primary">
					<div class="rich_media_content " id="js_content">
						<section class="article135" style="font-family: ΢���ź�;">
						<p style="text-align: center; white-space: normal;">
							<strong>
							<span style="font-size: 20px; color: rgb(38, 38, 38);">���ϴ�ѧ��ɽУ�ѻ�
							</span>
							</strong>
						</p>
						<p style="text-align: center; white-space: normal;">
							<strong>
							<span style="font-size: 20px; color: rgb(38, 38, 38);">���֪ͨ(2016)
							</span>
							</strong>
						</p>
						<p style="white-space: normal;">
							<br />
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
							���ƲԲԣ����������������ϣ�ѧ�����¡�</p>
						<section class="135editor" data-id="3047"
							style="border: 0px none; padding: 0px; box-sizing: border-box; margin: 0px;">
						<section
							style="border: 0px; box-sizing: border-box; width: auto; clear: both; margin: 0px; padding: 0px 0.5em 0.5em 0px; text-align: center;">
						<img src="images/swu/swu1.jpg"
							style="box-shadow: rgb(102, 102, 102) 0.2em 0.2em 0.5em; box-sizing: border-box; margin: 0px; width: 100%; visibility: visible !important;"
							width="100%" height="auto" border="0" opacity="" mapurl=""
							title="" data-width="100%" data-ratio="0.6606425702811245"
							data-w="498" /></section> </section>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
							ң�������̣�������ɽ�죬��������֯��̾�����ƺ�������ɽ�Ǹ������Ǻ�����ĵ�����������ΰ�˹��︳�����Ǽ��������������뵨�ǡ��߹����꣬����᪽���һ���������˲������ԣ����ڿ������ڸ��и�ҵ����һ�ģ��ջ����ࡣ2015�����ϴ�ѧ��ɽУ�ѻ��ڸ�У�ѵ�֧���£����С������飬�����С��ļ�ͥ����������һ�������Ҹ��Ĵ��ͥ������������ӭ������ʱ�̣������ǹ��۴��ͥ���ٱ������ƣ�ͬ�������飡
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">ʱ��ص�</p>

						<p>�ٰ�ʱ�䣺2016��1��9������</p>
						<p>�ٰ�ص㣺��ɽ�ж�����ȹ㳡��������¥</p>
						<img src="images/swu/map.png"
							style="box-shadow: rgb(102, 102, 102) 0.2em 0.2em 0.5em; box-sizing: border-box; margin: 0px; width: 100%; visibility: visible !important;"
							width="100%" height="auto" border="0" opacity="" mapurl=""
							title="" data-width="100%" data-ratio="0.642570281124498"
							data-w="498" />
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p>�������̣���������</p>
						<p>3:30--4:00 ǩ��</p>
						<p>4:00--5:00 ������ἰk��</p>
						<p>5:00--6:30 �������</p>
						<p>6:30--9:00 ���硢΢�ź�����齱</p>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p class="ptitle">������</p>
						<p>��ȡ�ɽ�������������ϵ���ʽ����ӭЯ�����������˼�����Ҫ���ѣ�������Ҫ���ϱ�������������ʱ��ѧԺ��ѡ���������</p>
						<p>�շѣ�����100Ԫ/�ˣ�С����ѡ�</p>
						<p>�շѷ�ʽ���ֳ����շ��䣬�����Աֱ�ӽ�ǮͶ�����У���Ὺʼǰ���������������ò����ɻ᳤���᳤���鳤�����ԷѲ��㡣��У�ѽɷѽ�������Ὺ֧��������Ϊ����У����ĸУ���֮�á�
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">�����ϵ��</p>
						<p>��Ʒ���������� 18923310765</p>
						<p>��Ŀ���ݣ�����Ө 15019918515</p>
						<p>����ͳ�ƣ��³� 13424535331</p>
						</span>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
							<span style="font-size: 14px; color: rgb(127, 127, 127);">


								<div id="subjects">
									<form id="main_form" action="#" method="get"
										accept-charset="utf-8" style="padding-bottom: 2em;">
										<p class="ptitle">���ϱ���</p>
										<div class="form_ctrl input_text" id="3" title="����">
											<label class="ctrl_title">������</label> <input type="text"
												id="name" value=""
												onmouseover="this.style.borderColor='black';this.style.backgroundColor='plum'"
												onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'"
												style="border-width: 1px; border-color =black; height: 30px; width: 200px; line-height: 30px;" />
										</div>
										<div class="form_ctrl input_text" id="5" title="�ֻ�">
											<label class="ctrl_title">�ֻ���</label> <input type="text"
												id="phone" value=""
												onmouseover="this.style.borderColor='black';this.style.backgroundColor='plum'"
												onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'"
												style="border-width: 1px; border-color =black; height: 30px; width: 200px; line-height: 30px;" />
										</div>
										<div class="form_ctrl input_text" id="5" title="�ֻ�">
											<label class="ctrl_title">ѧԺ��</label> 
											<SELECT id="collage">
												  <OPTION selected>��ѡ��</OPTION>
												  <OPTION>����</OPTION>
												  <OPTION>�����빫������ѧԺ</OPTION>
												  <OPTION>��ѧԺ</OPTION>
												  <OPTION>����ѧ��</OPTION>
												  <OPTION>�����ѧԺ</OPTION>
												  <OPTION>����ѧԺ</OPTION>
												  <OPTION>�����ѧ�뼼��ѧԺ</OPTION>
												  <OPTION>�����ѧѧԺ</OPTION>
												  <OPTION>���������Ϣ��ѧѧԺ</OPTION>
												  <OPTION>��֯��װѧԺ</OPTION>
												  <OPTION>ũѧ������Ƽ�ѧԺ</OPTION>
												  <OPTION>ҩѧԺ ��ҽҩѧԺ</OPTION>
												  <OPTION>����ѧԺ</OPTION>
												  <OPTION>���˼����ѧԺ</OPTION>
												  <OPTION>�Ļ�����ᷢչѧԺ</OPTION>
												  <OPTION>����ѧԺ</OPTION>
												  <OPTION>���Ŵ�ýѧԺ</OPTION>
												  <OPTION>��ʷ�Ļ�ѧԺ ����ѧԺ</OPTION>
												  <OPTION>��ѧ����ѧԺ</OPTION>
												  <OPTION>��������Դѧ��</OPTION>
												  <OPTION>���̼���ѧԺ</OPTION>
												  <OPTION>ʳƷ��ѧѧԺ</OPTION>
												  <OPTION>ֲ�ﱣ��ѧԺ</OPTION>
												  <OPTION>������Ϣ����ѧԺ</OPTION>
												  <OPTION>����ѧԺ</OPTION>
												  <OPTION>���ù���ѧԺ</OPTION>
												  <OPTION>����ѧ��</OPTION>
												  <OPTION>��ѧԺ</OPTION>
												  <OPTION>����ѧԺ</OPTION>
												  <OPTION>��ѧ��ͳ��ѧԺ</OPTION>
												  <OPTION>������ѧѧԺ</OPTION>
												  <OPTION>��Դ����ѧԺ</OPTION>
												  <OPTION>���＼��ѧԺ</OPTION>
												  <OPTION>԰��԰��ѧԺ</OPTION>
												  <OPTION>����Ƽ�ѧԺ</OPTION>
												  <OPTION>Ӧ�ü���ѧԺ</OPTION>
											</SELECT>
										</div>
										<div class="form_ctrl form_submit" id="12">
											<input type="button" name="button" value="��Ҫ����"
												onClick="baoming()"
												style="border: hidden; width: 100%; box-sizing: border-box; padding: 1.5em 0; background-color: #ff6c00; color: blue; font-weight: bold; border-radius: .15em; font-size: 18px">
										</div>
									</form>

									<div id="bottom_info">
										�ѱ���������<span id="totalCount"></span>
									</div>
									<div id="bottom_jump">
										<table align="center" id="allMemberTable">
										</table>
									</div>
							</span>
						</p>
						</section>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
