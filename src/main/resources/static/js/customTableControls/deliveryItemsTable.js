"use strict";

import {postData, loadData} from "../tableControls/ajax.js";

function renderDeliveryItemTable(data){
	
	const table = document.getElementById("dtDeliveryItems");
	
	table.deleteCaption();
	let caption = table.createCaption();
	caption.textContent = `${data["totalElements"]} ${data["totalElements"] > 1? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1);
	
	for(let i=0; i< data["content"].length;i++){
		let row = table.insertRow();
		
		for(let d in data["content"][i]["purchaseOrderItem"]["quotationItem"]["purchaseRequestItem"]["item"]){			
			let cell = row.insertCell();
			cell.textContent = data["content"][i]["purchaseOrderItem"]["quotationItem"]["purchaseRequestItem"]["item"][d];		
		}
		
		let cellUnit = row.insertCell();
		cellUnit.textContent = data["content"][i]["purchaseOrderItem"]["quotationItem"]["purchaseRequestItem"]["unit"];
		
		let cellQuantity = row.insertCell();
		cellQuantity.textContent = data["content"][i]["purchaseOrderItem"]["quotationItem"]["purchaseRequestItem"]["quantity"];
		
		let cellPrice = row.insertCell();
		cellPrice.textContent = data["content"][i]["purchaseOrderItem"]["quotationItem"]["price"];
		
		let inputQuantity = document.createElement("input");
		inputQuantity.type = "number";
		inputQuantity.name = "quantity"
		inputQuantity.value = data["content"][i]["quantity"];
		
		let inputItem = document.createElement("input");
		inputItem.type="hidden";
		inputItem.name = "item";
		inputItem.value = data["content"][i]["id"];
		
		let cellQuantityDelivered = row.insertCell();
		cellQuantityDelivered.appendChild(inputItem);
		cellQuantityDelivered.appendChild(inputQuantity);
		
		let btnRemove = document.createElement("button");
		btnRemove.type="button";
		btnRemove.textContent = "Remove";
		btnRemove.value = data["content"][i]["id"];
		btnRemove.className = "btn btn-danger";
		btnRemove.addEventListener("click", function(){
			postData("/api/deliveryitem/delete",`item=${this.value}`,function(){
				window.location.reload(true);
			})
		});
		let cellAction = row.insertCell();
		cellAction.appendChild(btnRemove);		
	}
}

export default function createDeliveryItemTable(url, params){
	loadData(url+params, renderDeliveryItemTable);
}