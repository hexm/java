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
<meta name="description" content="�ϲ���ѧ��2004��13��" />
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

		LspStudentManager.getAllStudentsNoHide(function(students) {
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
		//LspStudentManager.addStudent({name:"lijie",phone:"134654"}, addCallBack); 
		var name = document.getElementById("name").value.replace(/^\s+|\s+$/g, "");
		var phone = document.getElementById("phone").value;
		var place = document.getElementById("place").value;
		if (name == "" || phone == "") {
			alert("�������������ֻ�������ٱ�����лл��");
			return;
		}
		if (LspStudentManager.findStudentByName(name) != null) {
			alert(name + "�Ѿ������ɹ����벻Ҫ�ظ�������лл��");
			return;
		}
		if (!phone.match(/^[0-9]+$/g) || (phone.length != 11)) {
			alert("��������ȷ���ֻ����룬лл��");
			return;
		}
		var student = LspStudentManager.getNewStudent();
		student.name = name;
		student.phone = phone;
		student.college = place;
		LspStudentManager.addStudent(student, addCallBack);
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
<title>2016���´�ͬѧ��֪ͨ�����ϱ���ͨ��</title>
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
							<span style="font-size: 20px; color: rgb(38, 38, 38);">�ϲ���ѧ��2004��13�� 
							</span>
							</strong>
						</p>
						<p style="text-align: center; white-space: normal;">
							<strong>
							<span style="font-size: 20px; color: rgb(38, 38, 38);">2016���´�ͬѧ��֪ͨ�����ϱ���ͨ��(2016)
							</span>
							</strong>
						</p>
						<p style="white-space: normal;">
							<br />
						</p>
						<p class="ptitle">����</p>
						<p	style="text-align: justify; text-indent: 2em; white-space: normal;">
							ʮ�����ʱ�򣬵�ʱ������ʮ�Ĵ��˺ó��졣</p>
						<section class="135editor" data-id="3047"
							style="border: 0px none; padding: 0px; box-sizing: border-box; margin: 0px;">
						</section>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
							���ʮ�����ֹ�ȥ�ˣ��Լ���Ȼ��ʮ��������ô�ϸо��Լ�����С����һ����
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
							����������˵�ʹ���Ƕ�������������80����һ�������ˡ�
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">����</p>

						<p>�ϲ���ѧ��2004��13�࣬һ���Ŀư࣬ȫ��80��һ�����Ů��һ���ú���</p>
						<p>��2004��6�¸߿�������һ��������������11����ö����ˡ�</p>
						<p>���˰���ָͷ��ֻ���ʱ���Ѿ���Խ��13����ͷ��</p>
						<p>��ʮ������ԭ����˧����Ů��඼�Ѿ�����˧�����裬��������ĳ�������</p>
						<img src="images/lsp/lsp.png"
							style="box-shadow: rgb(102, 102, 102) 0.2em 0.2em 0.5em; box-sizing: border-box; margin: 0px; width: 100%; visibility: visible !important;"
							width="100%" height="auto" border="0" opacity="" mapurl=""
							title="" data-width="100%" data-ratio="0.642570281124498"
							data-w="498" />
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p>�����Ƿ�������������</p>
						<p>С���Ƿ����Ǹ������ܲ��ɣ�</p>
						<p>�����Ƿ����ݵ��е��ɣ�</p>
						<p>����ʦ�Ƿ񻹼ǵ����ǵ�Ц���͵�����</p>
						<p>2016���´��������꣬���еĿ�Խ13������������ں���������һһ������</p>
						<p>�ش˺���13��ĸ�λ�ֵܽ��ã��������Ǹ���ʱ��������ж�ô���ô�һ��Ƕ�ôǳ��ô����ֻԸ���Ƕ���û�г���
ֻԸ���Ƕ������統��ʮ�߰�����ǰ㴿�桢��������Ŀ�ꡢ������!
</p>
						<p>Ը�����쳤�ؾã�</p>
						<p>������������̸ֻ�����壡</p>
						<p>�ڴ�������������뱾��ͬѧ�ᣬһ�������ǵĻ����꼾�����������ǵ��ഺ��</p>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p class="ptitle">ʱ��ص�</p>
						<p>�ٰ�ʱ�䣺2016��2��10�գ���������� 16��00~</p>
						<p>�ٰ�ص㣺�ϲ��س� ĳ��¥ĳ�ش���䣨�������������ͬѧ΢��Ⱥ��������ʽ֪ͨ��</p>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">�����</p>
						<p>��16��00֮ǰ���Ե����سǵ�ͬѧ������һ��ȥ�ϲ���ѧ�ٿ����羰���������~��</p>
						<p>16��00~17��30 ǩ��</p>
						<p>17��30~18��00 ��Ӱ����</p>
						<p>18��00~20��00 ����</p>
						<p>20��00~ KTV�������</p>
						<p>��������ס�������ͬѧ������ǰ����֯����ϵ��������ǰͳһ���žƵ�ס�ޣ�</p>
						</span>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">���ڷ���</p>
						<p>����ͬѧ���ȡAA��ԭ�򡣻�ӭЯ�������μӣ������˼�����Ҫ���ѡ�Ϊ����ͳ�ƣ��λ���Ա����Ҫ����ͨ����������������ʱ�ڡ������������ڵء�һ������д��ĳĳ�������ɣ���</p>
						<p>���ã�����400Ԫ/�ˣ�Ů��300Ԫ/�ˡ�С����ѡ�</p>
						<p>�շѷ�ʽ�������μӵ�ͬѧ������2��10��֮ǰ����΢�ź��/΢��ת�ˣ��Ƽ�������������ʽ�������÷�������ͬѧ��ķ��ù����ˣ�������ͬѧ��(������΢�ź���������ϵ���)�����ý��������¼������棺����ǩ���塢����������ͷѡ���ˮ�ѡ�KTV���á������������ʵ�����or������ᣩ���͸���ʦ���������ȡ����л��ѵķ�Ʊ/���ѵ��ݽ��ڰ༶Ⱥ���湫ʾ�����ɷ��ù����˼���֯�������������㣬Ȼ����Ⱥ���淢�������������ò���Ĳ��֣������ֳ���ͬѧ�������Ⱥ������ʱ����������㣻������������������������ͬѧȺ���淢������㡣</p>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">����ͬѧ����֯��</p>
						<p>�������ȷ� </p>
						<p>ǰ����֯��Э��������ƺ  </p>
						<p>���ء�����Ԥ�㼰�ֳ�Э����������</p>
						<p>����ͳ�ƣ�������</p>
						<p>���ù���������</p>
						<p>(��λ�����˵ľ�����ϵ��ʽ�뵽΢��Ⱥ�в鿴��ѯ��)</p>
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
											<label class="ctrl_title">�־ӵأ�</label> 
											<input type="text"
												id="place" value=""
												onmouseover="this.style.borderColor='black';this.style.backgroundColor='plum'"
												onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'"
												style="border-width: 1px; border-color =black; height: 30px; width: 200px; line-height: 30px;" />
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
