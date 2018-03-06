"use strict";

import {loadData} from "../tableControls/ajax.js";

function renderPoItemTable(data){
	
	const table = document.getElementById("dtPoItems");
	
	table.deleteCaption();
	let caption = table.createCaption();
	caption.textContent = `${data["totalElements"]} ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1)
	
	for(let i=0; i < data["content"].length; i++){
		let row = table.insertRow();
		
		let cellChkBox = row.insertCell();
		let chkBox = document.createElement("input");
		chkBox.type="checkbox";
		chkBox.name = "item";
		chkBox.value = data["content"][i]["id"];
		cellChkBox.appendChild(chkBox);
		
		for(let d in data["content"][i]["quotationItem"]["purchaseRequestItem"]["item"]){
			let cell = row.insertCell();		
			cell.textContent = data["content"][i]["quotationItem"]["purchaseRequestItem"]["item"][d];
		}
		
		let cellUnit = row.insertCell();
		cellUnit.textContent = data["content"][i]["quotationItem"]["purchaseRequestItem"]["unit"];
		
		let cellQuantity = row.insertCell();
		cellQuantity.textContent =  data["content"][i]["quotationItem"]["purchaseRequestItem"]["quantity"];
		
		let cellPrice = row.insertCell();
		cellPrice.textContent = data["content"][i]["quotationItem"]["price"];		
	}	
}

export default function createPoItemTable(url, params){
	loadData(url+params, renderPoItemTable);
}