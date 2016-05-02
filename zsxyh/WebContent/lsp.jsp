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
<meta name="description" content="南部中学高2004级13班" />
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

		LspStudentManager.getAllStudentsNoHide(function(students) {
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
		//LspStudentManager.addStudent({name:"lijie",phone:"134654"}, addCallBack); 
		var name = document.getElementById("name").value.replace(/^\s+|\s+$/g, "");
		var phone = document.getElementById("phone").value;
		var place = document.getElementById("place").value;
		if (name == "" || phone == "") {
			alert("请输入姓名和手机号码后再报名，谢谢！");
			return;
		}
		if (LspStudentManager.findStudentByName(name) != null) {
			alert(name + "已经报名成功，请不要重复报名，谢谢！");
			return;
		}
		if (!phone.match(/^[0-9]+$/g) || (phone.length != 11)) {
			alert("请输入正确的手机号码，谢谢！");
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
			alert("报名成功！");
			loadData();
		} else {
			alert(msg);
		}
	}
</script>
<title>2016年新春同学会通知及网上报名通道</title>
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
							<span style="font-size: 20px; color: rgb(38, 38, 38);">南部中学高2004级13班 
							</span>
							</strong>
						</p>
						<p style="text-align: center; white-space: normal;">
							<strong>
							<span style="font-size: 20px; color: rgb(38, 38, 38);">2016年新春同学会通知及网上报名通道(2016)
							</span>
							</strong>
						</p>
						<p style="white-space: normal;">
							<br />
						</p>
						<p class="ptitle">引子</p>
						<p	style="text-align: justify; text-indent: 2em; white-space: normal;">
							十几岁的时候，当时觉得三十的大人好成熟。</p>
						<section class="135editor" data-id="3047"
							style="border: 0px none; padding: 0px; box-sizing: border-box; margin: 0px;">
						</section>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
							如今十多年又过去了，自己已然三十，尼玛怎么老感觉自己还是小孩子一样？
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
							现在能有如此的痛悟，那定当是属于我们80后这一代的人了。
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">正题</p>

						<p>南部中学高2004级13班，一个文科班，全是80后，一大帮美女，一大帮好汉。</p>
						<p>从2004年6月高考结束后一别至今，整整隔了11年零好多天了。</p>
						<p>扳了扳手指头，只算出时间已经跨越了13个年头。</p>
						<p>三十而立，原来的帅哥美女差不多都已经成了帅爸靓妈，可我们真的长大了吗？</p>
						<img src="images/lsp/lsp.png"
							style="box-shadow: rgb(102, 102, 102) 0.2em 0.2em 0.5em; box-sizing: border-box; margin: 0px; width: 100%; visibility: visible !important;"
							width="100%" height="auto" border="0" opacity="" mapurl=""
							title="" data-width="100%" data-ratio="0.642570281124498"
							data-w="498" />
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p>马脸是否还是那张马脸？</p>
						<p>小白是否还是那个花心萝卜干？</p>
						<p>明洪是否还是瘦得有点仙？</p>
						<p>李老师是否还记得咱们的笑脸和捣蛋？</p>
						<p>2016年新春，金猴贺岁，所有的跨越13年的悬案，将在猴年大年初三一一揭晓。</p>
						<p>特此号召13班的各位兄弟姐妹，不管我们高中时候的友谊有多么深多么烈还是多么浅多么淡，只愿我们都还没有长大：
只愿我们都还是如当初十七八岁的那般纯真、善良、有目标、有理想!
</p>
						<p>愿友谊天长地久！</p>
						<p>不讲功与利，只谈仁与义！</p>
						<p>期待大家真诚热情参与本次同学会，一起致我们的花季雨季，致属于我们的青春！</p>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p
							style="text-align: justify; text-indent: 2em; white-space: normal;">
						<p class="ptitle">时间地点</p>
						<p>举办时间：2016年2月10日（大年初三） 16：00~</p>
						<p>举办地点：南部县城 某酒楼某特大包间（具体待定，会在同学微信群里面再正式通知）</p>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">活动流程</p>
						<p>（16：00之前可以到达县城的同学，可以一起去南部中学再看看风景，打打篮球~）</p>
						<p>16：00~17：30 签到</p>
						<p>17：30~18：00 合影留念</p>
						<p>18：00~20：00 晚宴</p>
						<p>20：00~ KTV等其他活动</p>
						<p>（晚上有住宿需求的同学，请提前与组织方联系，可以提前统一安排酒店住宿）</p>
						</span>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">关于费用</p>
						<p>本次同学会采取AA制原则。欢迎携带家属参加，但成人家属需要交费。为便于统计，参会人员均需要网上通道报名（家属报名时在“现在生活所在地”一栏，填写：某某家属即可）。</p>
						<p>费用：男生400元/人，女生300元/人。小孩免费。</p>
						<p>收费方式：报名参加的同学，请在2月10日之前，以微信红包/微信转账（推荐）或者其他方式，将费用发给本次同学会的费用管理人：武婷婷同学。(武婷婷微信号请自行联系添加)。费用将用于如下几个方面：制作签到板、制作横幅、餐费、酒水费、KTV费用、纪念册制作（实体相册or电子相册）、送给老师的新年贺礼等。所有花费的发票/消费单据将在班级群里面公示，并由费用管理人及组织方进行最终清算，然后在群里面发布清算结果。费用不足的部分，晚宴现场或同学会过后在群里面临时征集红包补足；费用如有余额，清算结果出来后在同学群里面发红包清零。</p>
						</p>
						<p style="text-align: justify; white-space: normal;">
						<hr>
						</p>
						<p class="ptitle">本次同学会组织方</p>
						<p>发起：宋先锋 </p>
						<p>前期组织及协调：刘松坪  </p>
						<p>场地、费用预算及现场协调：杨智中</p>
						<p>报名统计：吴明洪</p>
						<p>费用管理：武婷婷</p>
						<p>(各位联络人的具体联系方式请到微信群中查看和询问)</p>
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
											<label class="ctrl_title">现居地：</label> 
											<input type="text"
												id="place" value=""
												onmouseover="this.style.borderColor='black';this.style.backgroundColor='plum'"
												onmouseout="this.style.borderColor='black';this.style.backgroundColor='#ffffff'"
												style="border-width: 1px; border-color =black; height: 30px; width: 200px; line-height: 30px;" />
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
