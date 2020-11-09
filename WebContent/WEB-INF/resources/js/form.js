const form = document.getElementById('form');
const id = document.getElementById('id');
const name = document.getElementById('name');
const balance = document.getElementById('balance');
const birthday = document.getElementById('birthday');
var	allDataValid = true;
form.addEventListener('submit', (e)=>{
	if (!checkInputs()){
		 e.preventDefault();
	}
});


function checkInputs(){
	const idValue = id.value.trim(); 
	const nameValue = name.value.trim(); 
	const balanceValue = balance.value.trim(); 
	const birthdayValue = birthday.value.trim();
    allDataValid = true;
 
 
	if (idValue === ''){
		setErrorFor(id, '帳號欄不能是空白');
	} else {
	    if (checkId(idValue)){
			setSuccessFor(id);	
	    } else {
	      setErrorFor(id, '該帳號已有人使用，請更換新帳號');
	    }
	}
	
	if (nameValue === ''){
		setErrorFor(name, '姓名欄不能是空白');
	} else {
		setSuccessFor(name);	
	}
	
	if (balanceValue === ''){
		setErrorFor(balance, '餘額欄不能是空白');
	} else {
	    var objRegex = /^\d+$|(^-?\d\d*\.\d\d*$)|(^-?\.\d\d*$)/;  
	    
	    if(!objRegex.test(balanceValue))    {  
            setErrorFor(balance, '餘額欄必須是數值');
        } else { 
		  setSuccessFor(balance);
		}	
	}
	
	if (birthdayValue === ''){
		setErrorFor(birthday, '生日欄不能是空白');
	} else {
	    if (dateValidation(birthdayValue)){
			setSuccessFor(birthday);	
	    } else {
	    	setErrorFor(birthday, '生日欄格式錯誤');
	    }
	}
	return	allDataValid;
	
}

function checkId(id){
    idValid = false;
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/SpringRest20/_03/ajax/_02/CheckMemberId", false);
	xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
	xhr.send("id=" + id);
	var message = "";
    var result = JSON.parse(xhr.responseText);
	if (result.id.length == 0) {
		 // message = "帳號可用";
		 idValid = true;
	} else if (	result.id.startsWith("Error") ) {
		// message = result.id ;
		idValid = false;
	} else {
		// message = "帳號重複，請重新輸入帳號";
		idValid = false;
	}
	return idValid;
}


function setErrorFor(input, message){
	const formControl = input.parentElement;
	const small = formControl.querySelector('small');
	small.innerHTML = message;
	formControl.className = 'form-control error'; 
	allDataValid = false;
}

function setSuccessFor(input) {
	const formControl = input.parentElement;
	formControl.className = 'form-control success';
}

function dateValidation(str) {
  var re = new RegExp("^([0-9]{4})[.-]{1}([0-9]{1,2})[.-]{1}([0-9]{1,2})$");
  var days = [0, 31, 28, 31, 30,  31, 30, 31, 31, 30, 31, 30, 31];
  var strDataValue;
  var valid = true;
  if ((strDataValue = re.exec(str)) != null) {
    var y, m, d;
    y = parseFloat(strDataValue[1]);
    if (y <= 0 || y > 9999) { /*年*/
      return false;
    } 
    m = parseFloat(strDataValue[2]);
    
    if (m < 1 || m > 12) { /*月*/
        return false;
    }
    d = parseFloat(strDataValue[3]);
    if ( y % 4 == 0 && y % 100 != 0 || y % 400 == 0 ){
       days[2] = 29;
    }  else {
       days[2] = 28;
    }
    if (d <= 0 || d > days[m]) { /*日*/
      valid = false;
    }
  } else {
    valid = false;
  }  
  return valid;
}

function isEmail(email) {
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}