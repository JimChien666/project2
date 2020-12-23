<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" -->
<!-- 	crossorigin="anonymous"> -->
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 	crossorigin="anonymous"></script> -->
<!-- <script -->
<!-- 	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" -->
<!-- 	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" -->
<!-- 	crossorigin="anonymous"></script> -->
<!-- <script -->
<!-- 	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" -->
<!-- 	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" -->
<!-- 	crossorigin="anonymous"></script> -->
<!-- <script src="https://cdn.ckeditor.com/ckeditor5/23.1.0/classic/ckeditor.js"></script> -->

<style>
.btncls{
	background-color: #7E4C4F; /* Green */
    border: none;
    color: white;
    padding: 10px 15px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 10px;
    transition-duration: 0.3s;
    cursor: pointer;
}
.remove{
	background-color: #7E4C4F; /* Green */
    border: none;
    color: white;
    padding: 10px 15px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    border-radius: 10px;
    transition-duration: 0.3s;
    cursor: pointer;
}
button.btncls:hover{
	background-color: #000000;
}
div.btncls:hover{
	background-color: #000000;
}
div.remove:hover{
	background-color: #FF0000;
}
</style>


<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet"> -->
<title>文章發表</title>
<style>
h1 {
	font-family: 'Noto Sans TC', sans-serif
}
.fixed0 {
	position: fixed;
	bottom: 15%;
	right: 2%;
}
</style>
<link rel="stylesheet" href="<c:url value='/css/Animal.css' />">
<script src="js/animal.js" type="text/javascript" charset="UTF-8"></script>
<jsp:include page="../fragments/links.jsp" />


<!-- 轉頁載入動畫1 -->
<link rel="stylesheet"
	href="<c:url value='/css/loadingAnimation.css' />">
</head>
<body>
	<div id="loader"></div>
	<div style="display: none;" id="myDiv" class="animate-bottom">
		<!-- 轉頁載入動畫1 -->
	<%-- <script src="<c:url value='/js/ckeditor/config.js' />"></script> --%>

	<%-- <script src="<c:url value='/js/ckeditor/ckeditor.js' />"></script> --%>
	<script
		src="<c:url value='/assets/javascripts/ckeditor/ckeditor.js' />"></script>

	<div>
		<jsp:include page="../fragments/headerArea.jsp" />
	</div>
	<!-- 	麵包屑 -->
	<div class="breadcrumb-area pt-95 pb-95 bg-img"
		style="background-image:url(<c:url value='/assets/img/banner/banner-2.jpg' />);">
		<div class="container">
			<div class="breadcrumb-content text-center">
				<h2>討論區</h2>
				<ul>
					<li><a href="<c:url value='/'/>">首頁</a></li>
					<li class="active">討論區</li>
				</ul>
			</div>
		</div>
	</div>


	<div class="container">
		<ul style="list-style: none; margin: 0px 0;">
			<li style="float: left; margin: 0px 10px 10px 10px;"><a
				href="<c:url value='/backArticle' />">
					<button class="submit btn-style" type="submit"
						style="margin-top: 10px;">
						<span style="color: white; margin-top: 0px;">回討論版</span>
					</button>
			</a></li>
		</ul>
		
			<ul style="list-style: none; margin: 0px 0;">
				<li style="float: right; margin: 0px 10px 30px 10px;">
						<button class="submit btn-style" type="submit" id='createArticle'
							style="margin-top: 10px;">
							<span style="color: white; margin-top: 0px;">快速輸入資料</span>
						</button>
				</li>
			</ul>

		<div class="starter-template" style="clear: both">
			<h1>文章發表</h1>
			<p class="lead">
				分享故事~
			</p>
		</div>
			<br>


		<form:form action="saveToDB" method="POST" modelAttribute="forums" 
			enctype="multipart/form-data">
<h4>請輸入標題:</h4><form:input id="title" path="article.title" placeholder="標題" required="required"/>
			<br>
			<br>
			<hr>
		
<h4>選擇發表版面:</h4>
<%-- <form:select path="articletypesid" items="${allArticleTypes}" itemLabel="articletype" itemValue="id"> --%>
			<div style="width: 75px">
				<form:select path="article.articletypesid" required="required">
					<%-- 									<form:option value="NONE" label="請選擇" /> --%>
					<form:option value="1" label="狗" />
					<form:option value="2" label="貓" />
				</form:select>
			</div>
			<hr>
			<br>
			<%-- 		發表到狗狗討論版:<form:radiobutton value="1" path="article.articletypesid" /> --%>
			<%-- 		發表到貓咪討論版:<form:radiobutton value="2" path="article.articletypesid" /> --%>
			<%-- article:<form:textarea path="content" /> --%>

			<br>
			<br>
		<form:textarea name="editor" id="editor" path="content"></form:textarea>

			<br>
			<form:hidden value="0" path="article.activitysid" />
			<form:hidden value="1" path="article.showarticle" />
			<%-- 		<form:hidden value="0" path="voteid" /> --%>
			<hr>
			<label>發起投票:</label><div class="btncls" id="vote">發起投票</div><div style="float:right" class="btncls" id="voteInsert">快速輸入</div>
				<br />
				
				<div id="voteBlock" style="display: none">
			投票主題:<form:textarea id="votetopic" path="votetopic" placeholder="請輸入投票主題" />
				<br />			
<%-- 			插入主題圖片:<input name="contentImage" type="file" multiple /><span style="color: red;">${errors.multipartFile}</span> --%>
<!-- 				<br /> -->
		加入選項:<div class="btncls" id="addOption">加入</div>					
				<div id="options">
				<div class='option'><input name='optionInput' name='voteOption' style='display:inline-block; float:left; width: 95%;'><div class='remove' style='float:right; cursor: pointer; padding:0; width: 4%;'>x</div><input type='file' name='voteImg' ><br><br></div>		
				<div class='option'><input name='optionInput' name='voteOption' style='display:inline-block; float:left; width: 95%;'><div class='remove' style='float:right; cursor: pointer; padding:0; width: 4%;'>x</div><input type='file' name='voteImg' ><br><br></div>		
				</div>		
				</div>				
			<center><form:button class="btncls" value="submit" type="submit" id="button_submit"><h5 style="color: white;">送出</h5></form:button></center>
			<script>

			var myeditor=CKEDITOR.replace("editor", {});
			$(document).on("click","#createArticle",function() {					
					console.log("產生!")
					$("#title").val('貓主子盯著聖誕樹「思考喵生」 主人嘆：不要打壞主意！');
//					var myeditor=CKEDITOR.replace('editor', { toolbar: 'Basic' });
					myeditor.setData('<p><img alt="日本一名推主家裡架起聖誕樹，卻看到貓主子盯著聖誕樹出神！   圖／Twitter@TomokoDoe" src="https://s.newtalk.tw/album/news/508/5fd1fb0b465df.png" title="日本一名推主家裡架起聖誕樹，卻看到貓主子盯著聖誕樹出神！   圖／Twitter@TomokoDoe" /></p><p>日本一名推主家裡架起聖誕樹，卻看到貓主子盯著聖誕樹出神！&nbsp;&nbsp;&nbsp;圖／Twitter@TomokoDoe</p><p>聖誕樹在有些皮貓的眼裡，根本就是隻超大逗貓棒，一到了聖誕節一定會聽到不少養貓家庭傳出聖誕樹遭殃的慘事！而日本這名推主在家裡架起聖誕樹，準備迎接聖誕節的到來，沒想到家裡貓主子竟無聲無息、突然在聖誕樹旁冒出來，眼睛盯得十分入迷，模樣看起來貌似在思考喵生、心裡暗自打什麼主意，這讓主人看了突然冒冷汗直呼：「拜託！不管你在想什麼！千萬都不要碰啊！」。</p><p><img alt="" height="800" src="https://petsmao-media.nownews.com/images/2020/12/faf609ea-1607422808-ef2797537ac381e3996306529eda767f-768x1024.jpg" width="600" /></p><p>這隻白底虎斑貓叫「哈魯」（圖／Twitter@TomokoDoe）</p><p><img alt="" height="800" src="https://petsmao-media.nownews.com/images/2020/12/7bc718d4-1607422780-fe9c62be965d5b4cd9b7a8b5f9df71bb-768x1024.jpg" width="600" /></p><p>主人撞見愛貓湊近聖誕樹，一直瘋狂注視著！（圖／Twitter@TomokoDoe）</p><p><img alt="" height="349" src="https://petsmao-media.nownews.com/images/2020/12/c4312369-1607423377-7f62ce671547c21d6897d6ee7206ee29.png" width="600" /></p><p>讓本喵想想要從哪裡開始玩起&hellip;&hellip;（圖／Twitter@TomokoDoe）</p><p>&nbsp;</p><p>時序進入年末，日本推主家開始佈置起聖誕裝飾，這隻13歲的白底虎斑貓哈魯（はる），對於聖誕樹上掛著琳瑯滿目的裝飾感到興趣，牠湊近聖誕樹眼睛狂盯到入迷的地步，小腦袋似乎在盤算些什麼？撞見這幕的主人感到心驚驚，擔心下一秒哈魯就失控出手，弄壞好不容易辛苦裝飾好的聖誕樹。</p><p><img alt="" height="800" src="https://petsmao-media.nownews.com/images/2020/12/3f22b6b8-1607422785-2f89175fd318333d73d3cabb9b3bdf8b-768x1024.jpg" width="600" /></p><p>幸好最後還是手下留情（圖／Twitter@TomokoDoe）</p><p>貼文分享到推特@TomokoDoe看得14萬名網友也捏把冷汗、紛紛留言說：「貓的手努力克制中！」、「我等這天等了好久（盯）」、「不懷好意www」、「悲劇發生前的最後一張照片 XD」、「修但幾勒！請停止你大膽的想法」、「一年一度的玩具！」、「怕爆」幸好貓主子心情大好難得手下留情，聖誕樹安然逃過一劫！</p><p>&nbsp;</p>');
//					$("#editor").val('<p><img alt="日本一名推主家裡架起聖誕樹，卻看到貓主子盯著聖誕樹出神！   圖／Twitter@TomokoDoe" src="https://s.newtalk.tw/album/news/508/5fd1fb0b465df.png" title="日本一名推主家裡架起聖誕樹，卻看到貓主子盯著聖誕樹出神！   圖／Twitter@TomokoDoe" /></p><p>日本一名推主家裡架起聖誕樹，卻看到貓主子盯著聖誕樹出神！&nbsp;&nbsp;&nbsp;圖／Twitter@TomokoDoe</p><p>聖誕樹在有些皮貓的眼裡，根本就是隻超大逗貓棒，一到了聖誕節一定會聽到不少養貓家庭傳出聖誕樹遭殃的慘事！而日本這名推主在家裡架起聖誕樹，準備迎接聖誕節的到來，沒想到家裡貓主子竟無聲無息、突然在聖誕樹旁冒出來，眼睛盯得十分入迷，模樣看起來貌似在思考喵生、心裡暗自打什麼主意，這讓主人看了突然冒冷汗直呼：「拜託！不管你在想什麼！千萬都不要碰啊！」。</p><p><img alt="" height="800" src="https://petsmao-media.nownews.com/images/2020/12/faf609ea-1607422808-ef2797537ac381e3996306529eda767f-768x1024.jpg" width="600" /></p><p>這隻白底虎斑貓叫「哈魯」（圖／Twitter@TomokoDoe）</p><p><img alt="" height="800" src="https://petsmao-media.nownews.com/images/2020/12/7bc718d4-1607422780-fe9c62be965d5b4cd9b7a8b5f9df71bb-768x1024.jpg" width="600" /></p><p>主人撞見愛貓湊近聖誕樹，一直瘋狂注視著！（圖／Twitter@TomokoDoe）</p><p><img alt="" height="349" src="https://petsmao-media.nownews.com/images/2020/12/c4312369-1607423377-7f62ce671547c21d6897d6ee7206ee29.png" width="600" /></p><p>讓本喵想想要從哪裡開始玩起&hellip;&hellip;（圖／Twitter@TomokoDoe）</p><p>&nbsp;</p><p>時序進入年末，日本推主家開始佈置起聖誕裝飾，這隻13歲的白底虎斑貓哈魯（はる），對於聖誕樹上掛著琳瑯滿目的裝飾感到興趣，牠湊近聖誕樹眼睛狂盯到入迷的地步，小腦袋似乎在盤算些什麼？撞見這幕的主人感到心驚驚，擔心下一秒哈魯就失控出手，弄壞好不容易辛苦裝飾好的聖誕樹。</p><p><img alt="" height="800" src="https://petsmao-media.nownews.com/images/2020/12/3f22b6b8-1607422785-2f89175fd318333d73d3cabb9b3bdf8b-768x1024.jpg" width="600" /></p><p>幸好最後還是手下留情（圖／Twitter@TomokoDoe）</p><p>貼文分享到推特@TomokoDoe看得14萬名網友也捏把冷汗、紛紛留言說：「貓的手努力克制中！」、「我等這天等了好久（盯）」、「不懷好意www」、「悲劇發生前的最後一張照片 XD」、「修但幾勒！請停止你大膽的想法」、「一年一度的玩具！」、「怕爆」幸好貓主子心情大好難得手下留情，聖誕樹安然逃過一劫！</p><p>&nbsp;</p>');
				});	

			var editorData = myeditor.getData();			
			var votetopic = $("#votetopic").val();

			$('#button_submit').click(function (e) {
			
			var optionStr = false;
			$(".option :text").each(function(){
				if($(this).val()==""){
				optionStr = true;
				return false;
					}				
				})
					if(optionStr==true || $("#votetopic").val()==''){
				        e.preventDefault();
						swal({
							  title: "投票選項或主題不完整!",
							  icon: "error",
							  button: "確認",
							});						
						}

		        console.log("votetopic"+$("#votetopic").val());
				console.log("option"+$("#options div:first-child").val());
				console.log("optionStr"+optionStr);

			});
			
				$( "#vote" ).click(function() {
				  $( "#voteBlock" ).toggle( "slow", function() {
						if($('#votetopic:visible').length==0){
							$("#votetopic").val('');
							swal({
								  title: "已關閉投票功能!",
								  icon: "error",
								  button: "確認",
								});		
							}
				  });
				});
				$( "#voteInsert" ).click(function() {
				  $( "#votetopic" ).val( "你家的貓會不會調皮搗亂呢?");
				  $(".option:first input:first").val( "會");
				  $(".option:last input:first").val( "不會");
				});
				
				$( "#addOption" ).on("click",function() {
					if (($(".option").length)<=4) {						
				  $( "#options" ).append("<div class='option'><input name='optionInput' name='voteOption' style='display:inline-block; float:left; width: 95%;'><div class='remove' style='float:right; cursor: pointer; padding:0; width: 4%;'>x</div><input type='file' name='voteImg' ><br><br></div>");
					}else {
// 						alert("選項最多五個")
						swal({
							  icon: "info",
							  title: "選項最多五個 !",
							  button: "確認"								  							  
							});					
					}
					});
				
				$(document).on("click",".remove",function() {
					
 					console.log($(".option").length)
 					if(($(".option").length)<=2){
// 						alert("最少要有兩個選項")
						swal({
							  icon: "info",
							  title: "最少要有兩個選項 !",
							  button: "確認"							  							  
							});	
 	 				}else{
// 						$(this).parent().remove();	
					    $( this ).parent().fadeOut( "slow", function() {
					        // After animation completed:
					        $( this ).remove();
					      });								
 	 	 			}
					});	

	
			</script>
		</form:form>


	</div>
	<!-- /.container -->

	<jsp:include page="../fragments/footerArea.jsp" />
	<jsp:include page="../fragments/allJs.jsp" />

		<!-- 轉頁載入動畫2 -->
	</div>
</body>
<script>
setTimeout(function() {
	$(document).ready(function() {
		document.getElementById("loader").style.display = "none";
		document.getElementById("myDiv").style.display = "block";
	});
}, 500);
</script>
<!-- 轉頁載入動畫2 -->
</html>
