$(document).ready(function() {
	function check() {
		if (validateForm()) {
	        return true;
	    }
	    return false;
	}
	function checkGroup() {
		if (validategroupForm()) {
	        return true;
	    }
	    return false;
	}
});

function validateForm(){
	var a=document.forms["createruleform"]["rulename"].value;
	if (a==null || a==""){
	  $('#errorMessage').css("display","block");
	  document.getElementById('errorMessage').innerHTML = "Rule name is required.";
	  return false;
	 }
	var b=document.forms["createruleform"]["des"].value;
	if (b==null || b==""){
	  $('#errorMessage').css("display","block");
	  document.getElementById('errorMessage').innerHTML = "Description is required.";
	  return false;
	}
	var c=document.forms["createruleform"]["templatename"].value;
	if (c==null || c==""){
	  $('#errorMessage').css("display","block");
	  document.getElementById('errorMessage').innerHTML = "Template name is required.";
	  return false;
	}
	var d=document.forms["createruleform"]["templatename"].value;
	if (d==null || d==""){
	  $('#errorMessage').css("display","block");
	  document.getElementById('errorMessage').innerHTML = "Group name is required.";
	  return false;
	}
	var e=document.forms["creategroupform"]["categoryName"].value;
	if (e==null || e==""){
	  $('#errorMessage').css("display","block");
	  document.getElementById('errorMessage').innerHTML = "Group name is required.";
	  return false;
	}
	}

function validategroupForm(){
	var e=document.forms["creategroupform"]["categoryName"].value;
	if (e==null || e==""){
	  $('#errorMessage').css("display","block");
	  document.getElementById('errorMessage').innerHTML = "Group name is required.";
	  return false;
	}
	var f=document.forms["creategroupform"]["storeTypeTags"].value;
	if (f==null || f==""){
	  $('#errorMessage').css("display","block");
	  document.getElementById('errorMessage').innerHTML = "Fulfillment method is required.";
	  return false;
	}
	var f=document.forms["creategroupform"]["stateTags"].value;
	if (f==null || f==""){
	  $('#errorMessage').css("display","block");
	  document.getElementById('errorMessage').innerHTML = "State is required.";
	  return false;
	}
}