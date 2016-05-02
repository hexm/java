<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>��С���Ĳ�����վ</title>
		<LINK href="css/tab_menu.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/prototype.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/fabtabulous.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/jquery-1.7.1.min.js" type=text/javascript></SCRIPT>
		<SCRIPT type=text/javascript>
			// The idea borrowed from dynamicDrive.com
			
			$(document).ready(function() {
				$("#sc21").click(function() {
               	 	$('#content').load('content1.jsp');
           		 });
           		$("#sc22").click(function() {
               	 	$('#content').load('content2.jsp');
           		 });
			});
		
			var initialtab = [ 1, "sc1" ]
		
			var previoustab = "";
			var intv;
		
			function expandcontent(cid, aobject) {
				stopTimer();
		
				highlighttab(aobject)
		
				if (previoustab != "")
					document.getElementById(previoustab).style.display = "none"
		
				document.getElementById(cid).style.display = "block"
				previoustab = cid
			}
		
			function highlighttab(aobject) {
				stopTimer();
		
				if (typeof tabobjlinks == "undefined")
					collectddimagetabs()
		
				for (i = 0; i < tabobjlinks.length; i++)
					tabobjlinks[i].className = ""
		
				aobject.className = "current"
			}
		
			function collectddimagetabs() {
				var tabobj = document.getElementById("ddimagetabs")
				tabobjlinks = tabobj.getElementsByTagName("A")
			}
		
			function do_onload() {
				collectddimagetabs()
				expandcontent(initialtab[1], tabobjlinks[initialtab[0] - 1])
			}
		
			function startTimer() {
				intv = setTimeout(
						"expandcontent(initialtab[1], tabobjlinks[initialtab[0]-1])",
						2000);
			}
		
			function stopTimer() {
				clearTimeout(intv);
			}
		
			Event.observe(window, 'load', do_onload, false);
			Event.observe('xcx_tabmenu', 'mouseover', function(event) {
				stopTimer();
			}, false);
		
			Event.observe('xcx_tabmenu', 'mouseout', function(event) {
				var reltg = (event.relatedTarget) ? event.relatedTarget
						: event.toElement;
		
				var tg = (window.event) ? event.srcElement : event.target;
		
				if (tg.nodeName != 'DIV')
					return;
		
				while (reltg != tg && reltg.nodeName != 'BODY') {
					reltg = reltg.parentNode;
		
					if (reltg.id == "xcx_tabmenu")
						return;
				}
		
				if (reltg == tg)
					return;
		
				stopTimer();
				startTimer();
			}, false);
		</SCRIPT>
	</head>

	<body>
		<DIV id=header>
			<DIV style="height: 80px"></DIV>

			<DIV id="xcx_tabmenu">
				<DIV class="cx_tabmenu" id="cx_tabmenu">
					<DIV id="ddimagetabs">
						<A href="#" target="_blank" class=sclink id=sclink1
							onMouseOver="expandcontent('sc1', this)">��ҳ</A>
						<A class=sclink id=sclink2
							onMouseOver="expandcontent('sc2', this)" href="#">��˾�Ļ�</A>
						<A class=sclink id=sclink3
							onMouseOver="expandcontent('sc3', this)" href="#">��Ʒչʾ</A>
						<A class=sclink id=sclink4
							onMouseOver="expandcontent('sc4', this)" href="#">�ɹ���Ŀ</A>
						<A class=sclink id=sclink5
							onMouseOver="expandcontent('sc5', this)" href="#">��ҵ��Ѷ</A>
						<A class=sclink id=sclink6
							onMouseOver="expandcontent('sc6', this)" href="#">��������</A>
					</DIV>


					<DIV id="submenu" style="clear: left">
						<DIV id="tabcontentcontainer">
							<DIV class=tabcontent id=sc1>
								<A href="#">���棺��ӭ���ã���վ�����У�����֧�֣�</A>
								<A href="#">www.icbc.com.cn</A>
							</DIV>
							<DIV class=tabcontent id=sc2>
								<A href="#" id="sc21">��������</A>
								<A href="#" id="sc22">��ҵ��ʷ</A>
								<A href="#">��Ӫ����</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">�����Ŷ�</A>
								<A href="#">�����ƻ�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
							</DIV>
							<DIV class=tabcontent id=sc3>
								<A href="#">����������</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">������ҳ����</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
							</DIV>
							<DIV class=tabcontent id=sc4>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">���������˲�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
							</DIV>
							<DIV class=tabcontent id=sc5>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">������վ����</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
							</DIV>
							<DIV class=tabcontent id=sc6>
								<A href="#">��ҵ��ʷ</A>
								<A href="#">��Ӫ����</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">��ȶԲ�</A>
								<A href="#">������վ�ƹ�</A>
								<A href="#">���XX</A>
							</DIV>
						</DIV>
					</DIV>
				</DIV>
			</DIV>
			
		</DIV>
		
		<div id="content" style="margin-top:40px; BACKGROUND:url('../images/sub_menu_bg.jpg') repeat-x; WIDTH: 100%; height:100%">
			web content to load here.
		</div>
		
		

	</body>
	
</html>
