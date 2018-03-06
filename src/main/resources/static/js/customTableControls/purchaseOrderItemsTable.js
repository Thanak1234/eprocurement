"use strict";

import {loadData,postData} from "../tableControls/ajax.js";

function renderPurchaseOrderItemTable(data){
	
	const table = document.getElementById("dtPurchaseOrderItems");
	
	table.deleteCaption();
	let caption = table.createCaption();
	caption.textContent = data["totalElements"]+` ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1)
		
	for(let i =0; i < data["content"].length; i++){
		let row = table.insertRow();
		
		for(let d in data["content"][i]["quotationItem"]["purchaseRequestItem"]["item"]){
			let cell = row.insertCell();		
			cell.textContent = data["content"][i]["quotationItem"]["purchaseRequestItem"]["item"][d];
		}
		
		let cellUnit = row.insertCell();
		cellUnit.textContent = data["content"][i]["quotationItem"]["purchaseRequestItem"]["unit"];
		
		let cellQuantity = row.insertCell();
		cellQuantity.textContent = data["content"][i]["quotationItem"]["purchaseRequestItem"]["quantity"];
		
		let cellPrice = row.insertCell();
		cellPrice.textContent = data["content"][i]["quotationItem"]["price"];
		
		let cellAction = row.insertCell();
		let btnRemove = document.createElement("button");
		btnRemove.value = data["content"][i]["id"];
		btnRemove.type = "button"; 
		btnRemove.textContent = "Remove";
		btnRemove.className = "btn btn-danger";
		cellAction.appendChild(btnRemove);
		btnRemove.addEventListener("click",function(){
			postData("/api/poitems/delete",`item= ${this.value}`,function(){
				window.location.reload(true);
			})
		});
	}
}

export default function createPuchaseOrderItemTable(url,params){
	loadData(url+params,renderPurchaseOrderItemTable)
}