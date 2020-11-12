//ReadAnimal
//預防新增後，重整頁面會跳出是否重複新增的提示視窗
if ( window.history.replaceState ) {
    window.history.replaceState( null, null, window.location.href );
}

//SweetAlert刪除
function deleteAnimal(animalId){
	swal({
	    title: "確定刪除動物編號"+animalId+"這筆資料?",
	    icon: "warning",
	    buttons: {Btn: false,
	        cancel: {text: "取消",visible: true},
	        danger: {text: "刪除",visible: true}
	    },
	    dangerMode: true
	}).then((value) => {
		switch(value){
			case "danger":
				document.forms[0].action="DeleteAnimal.controller?animalId="+animalId;
				document.forms[0].method="POST";
				document.forms[0].submit();//submit is not a function可能是因為有按鈕的name也叫submit
				break;
			  }});
}
	
//傳統提示視窗刪除Delete
function confirmDelete(animalId) {
	if(confirm("確定刪除此筆動物資料(動物編號:"+animalId+")?")){
		document.forms[0].action="DeleteAnimal.controller?animalId="+animalId;
		document.forms[0].method="POST";
		document.forms[0].submit();//submit is not a function可能是因為有按鈕的name也叫submit
	}
}

//CreateAnimal
//當animalFile有變動時，會將animalFileSpan顯示
//$("#animalFiles").change(function() {
//	alert("good");
//	document.getElementById("animalFileSpan").style.display = "block";
//	readURL(this);
//});
function showImage(){
	document.getElementById("animalFileSpan").style.display = "block";
	readURL(document.getElementById("animalFiles"));
};

//UpdateAnimal
//當animalFile1有變動時，會將animalFileSpan1顯示
//$("#animalFiles1").change(function() {
//	document.getElementById("animalFileSpan2").style.display = "none";
//	document.getElementById("animalFileSpan1").style.display = "block";
//	readURL(this);
//});
function showImage1(){
	document.getElementById("animalFileSpan2").style.display = "none";
	document.getElementById("animalFileSpan1").style.display = "block";
	readURL(document.getElementById("animalFiles1"));
};
			
//給showImage的readURL用的
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#preview_animalFile").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

//檢查會員ID
function checkmemberId() {
	let memberId = document.getElementById("memberId").value;
	let memberIdLength = memberId.length;
	let memberIdSpan = document.getElementById("memberIdSpan");
	let flag1 = false;
	if (memberId == "") {
		document.getElementById("memberIdSpan").style.display = "block";
		memberIdSpan.innerHTML = "不可空白";
	} else if (memberIdLength <= 10) {
		for (let i = 0; i < memberIdLength; i++) {
			let ch = memberId.charAt(i);
			if ((ch >= "\u0030" && ch <= "\u0039")) {//判斷數字
				flag1 = true;
			} else {
				flag1 = false;
				break;
			}
		}
		if (flag1) {
			memberIdSpan.innerHTML = "";
		} else {
			document.getElementById("memberIdSpan").style.display = "block";
			memberIdSpan.innerHTML = "只能輸入數字";
		}
	} else {
		document.getElementById("memberIdSpan").style.display = "block";
		memberIdSpan.innerHTML = "只能輸入10碼";
	}
}

//檢查收容動物ID
function checkacceptionId() {
	let acceptionId = document.getElementById("acceptionId").value;
	let acceptionIdLength = acceptionId.length;
	let acceptionIdSpan = document
			.getElementById("acceptionIdSpan");
	let flag1 = false;
	if (acceptionId == "") {
		acceptionIdSpan.innerHTML = "";
	} else if (acceptionIdLength > 0) {
		for (let i = 0; i < acceptionIdLength; i++) {
			let ch = acceptionId.charAt(i);
			if ((ch >= "\u0030" && ch <= "\u0039")
					|| (ch >= "\u0061" && ch <= "\u007a")
					|| (ch >= "\u0041" && ch <= "\u005a")) {//判斷數字或英文大小寫
				flag1 = true;
			} else {
				flag1 = false;
				break;
			}
		}
		if (flag1) {
			acceptionIdSpan.innerHTML = "";
		} else {
			document.getElementById("acceptionIdSpan").style.display = "block";
			acceptionIdSpan.innerHTML = "只能輸入英文字母、數字";
		}
	} else {
		document.getElementById("acceptionIdSpan").style.display = "block";
		acceptionIdSpan.innerHTML = "";
	}
}

//檢查品種ID
function checkbreedId() {
	let breedId = document.getElementById("breedId").value;
	let breedIdLength = breedId.length;
	let breedIdSpan = document.getElementById("breedIdSpan");
	let flag1 = false;
	if (breedId == "") {
		document.getElementById("breedIdSpan").style.display = "block";
		breedIdSpan.innerHTML = "必須選擇品種";
	} else if (breedIdLength <= 10) {
		for (let i = 0; i < breedIdLength; i++) {
			let ch = breedId.charAt(i);
			if ((ch >= "\u0030" && ch <= "\u0039")) {//判斷數字
				flag1 = true;
			} else {
				flag1 = false;
				break;
			}
		}
		if (flag1) {
			breedIdSpan.innerHTML = "";
		} else {
			document.getElementById("breedIdSpan").style.display = "block";
			breedIdSpan.innerHTML = "只能輸入數字";
		}
	} else {
		document.getElementById("breedIdSpan").style.display = "block";
		breedIdSpan.innerHTML = "只能輸入10碼";
	}
}

//檢查毛色
function checkcoatColor() {
	let coatColor = document.getElementById("coatColor").value;
	let coatColorLength = coatColor.length;
	let coatColorSpan = document.getElementById("coatColorSpan");
	let flag1 = false;
	if (coatColor == "") {
		coatColorSpan.innerHTML = "";
	} else {
		for (let i = 0; i < coatColorLength; i++) {
			let ch = coatColor.charAt(i);
			if ((ch >= "\u4e00" && ch <= "\u9fa5")
					|| (ch >= "\u0061" && ch <= "\u007a")
					|| (ch >= "\u0041" && ch <= "\u005a")) {//判斷數字或英文大小寫
				flag1 = true;
			} else {
				flag1 = false;
				break;
			}
		}
		if (flag1) {
			coatColorSpan.innerHTML = "";
		} else {
			document.getElementById("coatColorSpan").style.display = "block";
			coatColorSpan.innerHTML = "只能輸入英文字母、中文";
		}
	}
}
