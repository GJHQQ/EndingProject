  function getvalues(){
  	var val=document.getElementById("user_pwd");
  	var name=document.getElementById("user_name");
  	if(isNaN(val.value)){
  		alert("密码非法字符");
  		val.value="";
  	}
  	if(!isNaN(name.value)){
  		alert("用户名非法字符");
  		name.value="";
  	}
  	if(val.value=="" || name.value==""){
  		alert("密码或用户名为空");
  	}
  }
  function getvalue(){
		var val=document.getElementById("user_pwd");
	  	var reval=document.getElementById("user_repwd");
	  	if(isNaN(val.value)){
	  		alert("密码非法字符");
	  		val.value="";
	  	}
	  	if(val.value!==reval.value || val.value=="" || reval.value=="")
	  		alert("修改用户失败，密码报错，重新编辑");
	 }
  