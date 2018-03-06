"use strict";

/*
 * Ajax
 */
function loadData(url, callback){
	let request = new XMLHttpRequest();
	request.open("GET",url);
	request.onload = function(){
		let data = JSON.parse(request.responseText);
		callback(data);	
	}	
	request.send();
}

function postData(url,data,callback){
	const csrfToken = document.getElementById("_csrf_token").content;
	let request = new XMLHttpRequest();
	request.open("POST",url);
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
	request.onload = function(){
		callback();
	}
	request.send(`_csrf=${csrfToken}&`+data);	
}

function sendFormData(form,url,callback){
	let itemFormData = new FormData(form);
	let request = new XMLHttpRequest();
	request.open("POST",url);
	request.onload = function(){
		callback();
	}
	request.send(itemFormData);	
}

export {loadData,postData,sendFormData};