<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="org.directwebremoting.extend.*"%>
<%@ page import="org.directwebremoting.ServerContext"%>
<%@ page import="org.directwebremoting.Container"%>
<%@ page import="java.io.*"%>
<%@ page import="org.directwebremoting.impl.StartupUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icbc.hexm.swu.DwrTest"%>
<html>
<head>
<title>尚好文化发展有限公司</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Onepage Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css'/>
<link href="favicon.ico" mce_href="favicon.ico" rel="icon"	type="image/x-icon" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	    
	    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- 
<link href='http://fonts.googleapis.com/css?family=Arvo:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
 -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
 <script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
				$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
			});
		});
	</script>
	 <!------ Light Box ------>
    <link rel="stylesheet" href="css/swipebox.css">
    <script src="js/jquery.swipebox.min.js"></script> 
    <script type="text/javascript">
		jQuery(function($) {
			$(".swipebox").swipebox();
		});
	</script>
    <!------ Eng Light Box ------>	
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
</head>
<body>
<div class="header" id="head">	
      <div class="container"> 
         <div class="header-top">
      		 <div class="logo">
				<a href="index.html"><img src="images/logo.png" alt=""/><img src="images/logo1.png" alt=""/></a>
			 </div>
		   <div class="top-menu">
		   	<span class="menu"> </span>
			<ul>
			 	<nav class="cl-effect-5">
				<li><a class="active" href="#home" class="scroll"><span data-hover="Home">主页</span></a></li>
				<li><a href="#services" class="scroll"><span data-hover="lesson">课程说明</span></a></li>
				<li><a href="#work" class="scroll"><span data-hover="product">产品介绍</span></a></li>
				<li><a href="#about" class="scroll"><span data-hover="teacher">名师介绍</span></a></li>
				<li><a href="#contact" class="scroll"><span data-hover="contact">联系我们</span></a></li>
				</nav>
			 </ul>
			 </div>
			 <!--script-nav-->
		 <script>
		 $("span.menu").click(function(){
		 $(".top-menu ul").slideToggle("slow" , function(){
		 });
		 });
		 </script>
			<div class="clearfix"></div>
		 </div>  
		  <div class="index-banner">
       	   <div class="wmuSlider example1">
			   <div class="wmuSliderWrapper">
				   <article style="position: absolute; width: 100%; opacity: 0;"> 
				   	<div class="banner-wrap">
				   	       	<div class="banner_center">
				   		 		<img alt="" src="images/banner1.jpg">
				   		 	</div>
				   		 </div>
					</article>
				   <article style="position: relative; width: 100%; opacity: 1;"> 
				   		<div class="banner-wrap">
					   	   	<div class="banner_center">
					   		 		<h1>公司说明</h1> 
					   		 		<h2>尚好文化<span>发展有限公司</span></h2>
					   		 		<h2>reproduced below</h2>
					   		 </div>
					   	</div>
				   </article>
				   <article style="position: absolute; width: 100%; opacity: 0;">
				   	  	<div class="banner-wrap">
				   	       <div class="banner_center">
				   		 		<h1>Lorem Ipsum ,</h1> 
				   		 		<h2>Contrary to <span>&  designer</span></h2>
				   		 		<h2>reproduced below</h2>
				   		 	</div>
				   		 </div>
					 </article>
				 </div>
				
            </div>
            <script src="js/jquery.wmuSlider.js"></script> 
			  <script>
       			$('.example1').wmuSlider();         
   		     </script> 	           	      
      </div>
	 </div>     
	</div>
	<div class="content">
		<div class="services-section" id="services">
			<div class="container"> 
			<div class="services-header">
				<h3><span>[</span> 课程说明 <span>]</span></h3>
					<p>这里用于课程的总体介绍，需要做几个对应的图标 </p>
			</div>
			<div class="services-sectiongrids">
				<div class="col-md-4 services-grid">
					<img src="images/img1.png" alt=""/>
					<h4>音乐</h4>
					<p>This is Photoshop's version  of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet.</p>
				</div>
				<div class="col-md-4 services-grid">
					<img src="images/img2.png" alt=""/>
					<h4>美术</h4>
					<p>This is Photoshop's version  of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet.</p>
				</div>
				<div class="col-md-4 services-grid">
					<img src="images/img3.png" alt=""/>
					<h4>其他</h4>
					<p>This is Photoshop's version  of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet.</p>
				</div>
				<div class="clearfix"></div>
				<div class="arrow1">
					<a href="#work" class="scroll"><img src="images/arrow1.png" alt=""/></a>
					</div>
		</div>
		</div>
		</div>
		<div class="works-section" id="work" id="work">
		   <div class="works-header">
				<h3><span>[</span> 产品介绍 <span>]</span></h3>
					<p>这里用于展示商品 </p>
			</div>
			<div class="portfolio-section-bottom-row" id="portfolio">
			<div class="container">
		 <ul id="filters" class="clearfix">
						<li><span class="filter active" data-filter="app card icon web logo1">全部</span></li>
						<li><span class="filter" data-filter="app">钢琴</span></li>
						<li><span class="filter" data-filter="card">吉它</span></li>
						<li><span class="filter" data-filter="icon">电子琴</span></li>
						<li><span class="filter" data-filter="logo1">其他</span></li>
						</ul>
					<div id="portfoliolist">
					<div class="portfolio card mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="card" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic-1.jpg" class="swipebox"  title="Image Title"> <img src="images/pic-1.jpg" class="img-responsive" alt=""><span class="zoom-icon"></span> </a>

		                </div>
					</div>				
					<div class="portfolio app mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="app" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic-2.jpg" class="swipebox"  title="Image Title"> <img src="images/pic-2.jpg" class="img-responsive" alt=""><span class="zoom-icon"></span> </a>

		                </div>
					</div>					
					<div class="portfolio icon mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="icon" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic-3.jpg" class="swipebox"  title="Image Title"> <img src="images/pic-3.jpg" class="img-responsive" alt=""><span class="zoom-icon"></span> </a>

		                </div>

					</div>
					
					<div class="portfolio app mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="app" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic-4.jpg" class="swipebox"  title="Image Title"> <img src="images/pic-4.jpg" class="img-responsive" alt=""><span class="zoom-icon"></span> </a>

		                </div>
					</div>			
					<div class="portfolio card mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="card" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic-5.jpg" class="swipebox"  title="Image Title"> <img src="images/pic-5.jpg" class="img-responsive" alt=""><span class="zoom-icon"></span> </a>

		                </div>
					</div>	
					<div class="portfolio card mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="card" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic-6.jpg" class="swipebox"  title="Image Title"> <img src="images/pic-6.jpg" class="img-responsive" alt=""><span class="zoom-icon"></span> </a>

		                </div>
					</div>	
					<div class="portfolio icon mix_all  wow bounceIn" data-wow-delay="0.4s" data-cat="icon" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic-7.jpg" class="swipebox"  title="Image Title"> <img src="images/pic-7.jpg" class="img-responsive" alt=""><span class="zoom-icon"></span> </a>

		                </div>
						</div>
						<div class="portfolio logo1 mix_all wow bounceIn" data-wow-delay="0.4s" data-cat="logos" style="display: inline-block; opacity: 1;">
						<div class="portfolio-wrapper grid_box">		
							 <a href="images/pic-8.jpg" class="swipebox"  title="Image Title"> <img src="images/pic-8.jpg" class="img-responsive" alt=""><span class="zoom-icon"></span> </a>

		                </div>
					</div>
					
<div class="clearfix"></div>					
				</div>
 <!-- Script for gallery Here -->
				<script type="text/javascript" src="js/jquery.mixitup.min.js"></script>
					<script type="text/javascript">
					$(function () {
						
						var filterList = {
						
							init: function () {
							
								// MixItUp plugin
								// http://mixitup.io
								$('#portfoliolist').mixitup({
									targetSelector: '.portfolio',
									filterSelector: '.filter',
									effects: ['fade'],
									easing: 'snap',
									// call the hover effect
									onMixEnd: filterList.hoverEffect()
								});				
							
							},
							
							hoverEffect: function () {
							
								// Simple parallax effect
								$('#portfoliolist .portfolio').hover(
									function () {
										$(this).find('.label').stop().animate({bottom: 0}, 200, 'easeOutQuad');
										$(this).find('img').stop().animate({top: -30}, 500, 'easeOutQuad');				
									},
									function () {
										$(this).find('.label').stop().animate({bottom: -40}, 200, 'easeInQuad');
										$(this).find('img').stop().animate({top: 0}, 300, 'easeOutQuad');								
									}		
								);				
							
							}
				
						};
						
						// Run the show!
						filterList.init();
						
						
					});	
					</script>
			<!-- Gallery Script Ends -->
			
		
	  
	  <div class="arrow">
				<a href="#about" class="scroll"><img src="images/arrow.png" alt=""/></a>
				</div>
			</div>
		</div>
	</div>
	<!-- portfolio-section-ends -->
	<div class="about-section" id="about" id="about">
	<div class="container">
		<div class="about-header">
			<h3><span>[</span> 名师介绍 <span>]</span></h3>
			<div class="about-imag">
			<img src="images/pic-9.jpg" alt=""/>
			</div>
			<div class="about-text">
					<p>这里用于教师的介绍.</p> 
					<p>This is Photoshop's version  of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet. Aenean sollicitudin, lorem quis bibendum auctor, nisi elit consequat ipsum, nec sagittis sem nibh id elit. Duis sed odio sit amet nibh vulputate cursus a sit amet mauris. . </p>
			</div>
			<div class="clearfix"></div>
		</div>
			<div class="about-sectiongrids">
				<div class="col-md-6 about-leftgrid">
					<div class="polls">
					<div class="poll">
						<div class="poll-desc">
							<h4>Graphic Design</h4>
						</div>
						<div class="percentage">
							<p>75%</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="skills">
			              	<div style="width:75%"> </div>
	                      </div>
	                      </div>
	                      <div class="polls">
					<div class="poll">
						<div class="poll-desc">
							<h4>Ui/Ux</h4>
						</div>
						<div class="percentage">
							<p>75%</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="skills">
			              	<div style="width:75%"> </div>
	                      </div>
	                      </div>
	                      <div class="polls">
					<div class="poll">
						<div class="poll-desc">
							<h4>Logo Design</h4>
						</div>
						<div class="percentage">
							<p>75%</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="skills">
			              	<div style="width:75%"> </div>
	                      </div>
	                      </div>
	                      <div class="polls">
					<div class="poll">
						<div class="poll-desc">
							<h4>Html/Css</h4>
						</div>
						<div class="percentage">
							<p>75%</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="skills">
			              	<div style="width:75%"> </div>
	                      </div>
	                      </div>

	</div>
	<div class="col-md-6 about-rightgrid">
		<div class="polls">
					<div class="poll">
						<div class="poll-desc">
							<h4>web design</h4>
						</div>
						<div class="percentage">
							<p>75%</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="skills">
			              	<div style="width:75%"> </div>
	                      </div>
	                      </div>
	                      <div class="polls">
					<div class="poll">
						<div class="poll-desc">
							<h4>branding</h4>
						</div>
						<div class="percentage">
							<p>75%</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="skills">
			              	<div style="width:75%"> </div>
	                      </div>
	                      </div>
	                      <div class="polls">
					<div class="poll">
						<div class="poll-desc">
							<h4>Photography</h4>
						</div>
						<div class="percentage">
							<p>75%</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="skills">
			              	<div style="width:75%"> </div>
	                      </div>
	                      </div>
	                      <div class="polls">
					<div class="poll">
						<div class="poll-desc">
							<h4>Jquery</h4>
						</div>
						<div class="percentage">
							<p>75%</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="skills">
			              	<div style="width:75%"> </div>
	                      </div>
	                      </div>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="arrow1">
	<a href="#contact" class="scroll"><img src="images/arrow1.png" alt=""/></a>
					</div>
	</div>
	</div>
	</div>
	<div class="footer-section" id="contact" id="contact">
			<div class="container"> 
			<div class="contact-header">
				<h3><span>[</span> 联系我们 <span>]</span></h3>
					<p>这里写联系方式.电话，邮箱，微信公众号，二维码，等等。。。 </p>
			</div>
			<div class="social-icon">
				<a href="#"><i class="icon1"></i></a>
				<a href="#"><i class="icon2"></i></a>
				<a href="#"><i class="icon3"></i></a>
				<a href="#"><i class="icon4"></i></a>
				<a href="#"><i class="icon5"></i></a>
				<a href="#"><i class="icon6"></i></a>
				<a href="#"><i class="icon7"></i></a>
				<a href="#"><i class="icon8"></i></a>
			</div>
			<div class="contact">
			<div class="col-md-4 contactgrid">
				<input type="text" class="text" value=" name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = ' name';}">
				</div>
				<div class="col-md-4 contactgrid">
				<input type="text" class="text" value="email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'email';}">
				</div>
				<div class="col-md-4 contactgrid">
				<input type="text" class="text" value="phone" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'phone';}">
				</div>
				<div class="col-md-8 contactgrid1">
				<textarea onfocus="if(this.value == 'Your Message') this.value='';" onblur="if(this.value == '') this.value='Your Message';" >Your Message</textarea>
				</div>
				<div class="col-md-4 contactgrid2">
				<input type="button" value="[send message]">
			   </div>
			   <div class="clearfix"></div>
				</div>
				<div class="footer-bottom">

					<p> Copyright &copy;2015  All rights  Reserved.</p>

					</div>
					<script type="text/javascript">
						$(document).ready(function() {
							/*
							var defaults = {
					  			containerID: 'toTop', // fading element id
								containerHoverID: 'toTopHover', // fading element hover id
								scrollSpeed: 1200,
								easingType: 'linear' 
					 		};
							*/
							
							$().UItoTop({ easingType: 'easeOutQuart' });
							
						});
					</script>
				<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
</div>

		</div>		
	</div>
	
	
</body>
</html>