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
<meta name="description" content="西南大学中山校友会2016年年会即将举行，邀请各位中山的校友在此报名！" />
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

		StudentManager.getAllStudents(function(students) {
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
		var name = document.getElementById("name").value.replace(/^\s+|\s+$/g, "");
		var phone = document.getElementById("phone").value;
		if (name == "" || phone == "") {
			alert("请输入姓名和手机号码后再报名，谢谢！");
			return;
		}
		if (StudentManager.findStudentByName(name) != null) {
			alert(name + "已经报名成功，请不要重复报名，谢谢！");
			return;
		}
		if (!phone.match(/^[0-9]+$/g) || (phone.length != 11)) {
			alert("请输入正确的手机号码，谢谢！");
			return;
		}
		var collage = document.getElementById("collage");
		var collegeName = collage.options[collage.selectedIndex].innerText;
		if(collegeName==="请选择") {
			alert("请选择学院，谢谢！");
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
			alert("报名成功！");
			loadData();
		} else {
			alert(msg);
		}
	}
</script>
<title>西南大学中山校友会2016年会通知</title>
</head>
<body onLoad="loadData()" class="zh_CN mm_appmsg">
	<div id="js_article" class="rich_media">
		<div id="js_top_ad_area" class="top_banner"></div>
		<div class="rich_media_inner">
			<div id="page-content">
				<div id="img-content" class="rich_media_area_primary">
					<div class="rich_media_content " id="js_content">
						<section class="article135" style="font-family: 微软雅黑;">
						<p style="text-align: center; white-space: normal;">
							<strong>
							<span style="font-size: 20px; color: rgb(38, 38, 38);">西南大学中山校友会
							</span>
							</strong>
						</p>
						<p style="text-align: center; white-space: normal;">
							<strong>
							<span style="font-size: 20px; color: rgb(38, 38, 38);">年会通知(2016)
							</span>
							</strong>
						</p>
						<p style="white-space: normal;">
							<br />
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
							缙云苍苍，嘉陵泱泱。特立西南，学行天下。</p>
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
							遥忆缙云绿，近看香山红，感岁月如织，叹情谊似海。美丽山城给予我们含弘光大的底蕴与力量，伟人故里赋予我们继往开来的沃土与胆魄。走过嘉陵，共畅岐江，一代代西大人不忘初衷，勇于开创，在各行各业独树一帜，收获良多。2015，西南大学中山校友会在各校友的支持下，秉承“西大情，与你行”的家庭理念，打造出了一个和美幸福的大家庭。在这告别美羊，迎来灵猴的时刻，让我们共聚大家庭，举杯中美酒，同叙西大情！
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">时间地点</p>

						<p>举办时间：2016年1月9日下午</p>
						<p>举办地点：中山市东区万谷广场风韵土家楼</p>
						<img src="images/swu/map.png"
							style="box-shadow: rgb(102, 102, 102) 0.2em 0.2em 0.5em; box-sizing: border-box; margin: 0px; width: 100%; visibility: visible !important;"
							width="100%" height="auto" border="0" opacity="" mapurl=""
							title="" data-width="100%" data-ratio="0.642570281124498"
							data-w="498" />
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p>下午活动流程（初定）：</p>
						<p>3:30--4:00 签到</p>
						<p>4:00--5:00 怀旧舞会及k歌</p>
						<p>5:00--6:30 联欢晚会</p>
						<p>6:30--9:00 晚宴、微信红包及抽奖</p>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p class="ptitle">年会费用</p>
						<p>采取缴交费用与捐赠相结合的形式。欢迎携带家属，成人家属需要交费，并且需要网上报名（家属报名时在学院栏选择家属）。</p>
						<p>收费：成人100元/人，小孩免费。</p>
						<p>收费方式：现场设收费箱，与会人员直接将钱投入箱中，晚会开始前开箱点数，不足费用部分由会长副会长秘书长等人自费补足。如校友缴费金额高于年会开支，余额部分作为明年校庆向母校捐款之用。
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">年会联系人</p>
						<p>物品捐赠：孙涛 18923310765</p>
						<p>节目表演：杨钰莹 15019918515</p>
						<p>报名统计：陈超 13424535331</p>
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
										<p class="ptitle">网上报名</p>
										<div class="form_ctrl input_text" id="3" title="姓名">
											<label class="ctrl_title">姓名：</label> <input type="text"
												id="name" value=""
												onmouseover="this.style.borderColor='black';this.style.backgroundColor='plum'"
												onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'"
												style="border-width: 1px; border-color =black; height: 30px; width: 200px; line-height: 30px;" />
										</div>
										<div class="form_ctrl input_text" id="5" title="手机">
											<label class="ctrl_title">手机：</label> <input type="text"
												id="phone" value=""
												onmouseover="this.style.borderColor='black';this.style.backgroundColor='plum'"
												onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'"
												style="border-width: 1px; border-color =black; height: 30px; width: 200px; line-height: 30px;" />
										</div>
										<div class="form_ctrl input_text" id="5" title="手机">
											<label class="ctrl_title">学院：</label> 
											<SELECT id="collage">
												  <OPTION selected>请选择</OPTION>
												  <OPTION>家属</OPTION>
												  <OPTION>政治与公共管理学院</OPTION>
												  <OPTION>法学院</OPTION>
												  <OPTION>心理学部</OPTION>
												  <OPTION>外国语学院</OPTION>
												  <OPTION>美术学院</OPTION>
												  <OPTION>物理科学与技术学院</OPTION>
												  <OPTION>地理科学学院</OPTION>
												  <OPTION>计算机与信息科学学院</OPTION>
												  <OPTION>纺织服装学院</OPTION>
												  <OPTION>农学与生物科技学院</OPTION>
												  <OPTION>药学院 中医药学院</OPTION>
												  <OPTION>含弘学院</OPTION>
												  <OPTION>马克思主义学院</OPTION>
												  <OPTION>文化与社会发展学院</OPTION>
												  <OPTION>体育学院</OPTION>
												  <OPTION>新闻传媒学院</OPTION>
												  <OPTION>历史文化学院 民族学院</OPTION>
												  <OPTION>化学化工学院</OPTION>
												  <OPTION>材料与能源学部</OPTION>
												  <OPTION>工程技术学院</OPTION>
												  <OPTION>食品科学学院</OPTION>
												  <OPTION>植物保护学院</OPTION>
												  <OPTION>电子信息工程学院</OPTION>
												  <OPTION>国际学院</OPTION>
												  <OPTION>经济管理学院</OPTION>
												  <OPTION>教育学部</OPTION>
												  <OPTION>文学院</OPTION>
												  <OPTION>音乐学院</OPTION>
												  <OPTION>数学与统计学院</OPTION>
												  <OPTION>生命科学学院</OPTION>
												  <OPTION>资源环境学院</OPTION>
												  <OPTION>生物技术学院</OPTION>
												  <OPTION>园艺园林学院</OPTION>
												  <OPTION>动物科技学院</OPTION>
												  <OPTION>应用技术学院</OPTION>
											</SELECT>
										</div>
										<div class="form_ctrl form_submit" id="12">
											<input type="button" name="button" value="我要报名"
												onClick="baoming()"
												style="border: hidden; width: 100%; box-sizing: border-box; padding: 1.5em 0; background-color: #ff6c00; color: blue; font-weight: bold; border-radius: .15em; font-size: 18px">
										</div>
									</form>

									<div id="bottom_info">
										已报名人数：<span id="totalCount"></span>
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
