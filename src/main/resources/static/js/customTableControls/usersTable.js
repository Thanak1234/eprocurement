"use strict";

import {loadData} from "../tableControls/ajax.js";

function renderUsersTable(data){
	const table = document.getElementById("dtUsers");
	
	table.deleteCaption();
	let caption = table.createCaption();
	caption.textContent = `${data["totalElements"]} ${data["totalElements"] > 1? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1);
	
	for(let i=0; i < data["content"].length;i++){
		let row = table.insertRow();
		
		let cellId = row.insertCell();
		cellId.textContent = data["content"][i]["id"];
		
		let cellUserName = row.insertCell();
		cellUserName.textContent = data["content"][i]["username"];
		
		let cellFirstName = row.insertCell();
		cellFirstName.textContent = data["content"][i]["firstName"];
		
		let cellLastName = row.insertCell();
		cellLastName.textContent = data["content"][i]["lastName"];
		
		let cellRole = row.insertCell();
		cellRole.textContent = data["content"][i]["role"];
		
		let linkUpdate = document.createElement("a");
		linkUpdate.text = "Update";
		linkUpdate.className = "btn btn-default";
		linkUpdate.href= `/user/${data["content"][i]["id"]}`;
		
		let cellAction = row.insertCell();
		cellAction.appendChild(linkUpdate);
	}
}

export default function createUsersTable(url, params){
	loadData(url+params, renderUsersTable);
}