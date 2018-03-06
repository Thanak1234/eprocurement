"use strict";

import {loadData} from "../tableControls/ajax.js";

function renderQuotationItemsSelectionTable(data){
	
	const table = document.getElementById("dtQuotationItems");
	
	table.deleteCaption();
	let caption = table.createCaption();
	caption.textContent = data["totalElements"]+` ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1);
	
	for(let i=0;i < data["content"].length;i++){
		let row  = table.insertRow();
		
		//check box
		let cellChkBox = row.insertCell();
		let chkBoxId = document.createElement("input");
		chkBoxId.type="checkbox";
		chkBoxId.value=data["content"][i]["id"];
		chkBoxId.name = "item";
		cellChkBox.appendChild(chkBoxId);
		
		//item 
		for(let d in data["content"][i]["purchaseRequestItem"]["item"]){
			let cellItem = row.insertCell();
			cellItem.textContent = data["content"][i]["purchaseRequestItem"]["item"][d];
		}
		
		let cellUnit = row.insertCell();
		cellUnit.textContent = data["content"][i]["purchaseRequestItem"]["unit"];
		
		let cellQuantity = row.insertCell();
		cellQuantity.textContent = data["content"][i]["purchaseRequestItem"]["quantity"];
				
		let cellPrice = row.insertCell();
		cellPrice.textContent = data["content"][i]["price"];	
	}
}

export default function createQuotationItemsSelectionTable(url,params){
	loadData(url+params,renderQuotationItemsSelectionTable);
}