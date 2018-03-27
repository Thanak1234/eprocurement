"use strict";

import {loadData,postData} from "../tableControls/ajax.js";

function renderQuotationItemsTable(data){
	
	const table = document.getElementById("dtQuotationItems");
	
	table.deleteCaption();
	let tableCaption = table.createCaption();
	tableCaption.textContent =  data["totalElements"]+` ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1);
	
	for(let i=0; i < data["content"].length;i++){
		let row = table.insertRow();
		
		for(let d in data["content"][i]["purchaseRequestItem"]["item"]){
			let cell = row.insertCell();
			cell.textContent = data["content"][i]["purchaseRequestItem"]["item"][d];
		}
		
		let cellUnit = row.insertCell();
		cellUnit.textContent = data["content"][i]["purchaseRequestItem"]["unit"];
		
		
		let cellQuantity = row.insertCell();
		cellQuantity.textContent = data["content"][i]["purchaseRequestItem"]["quantity"];
		
		//cell price
		let cellPrice = row.insertCell();
		let inputPrice = document.createElement("input");
		inputPrice.type="number";
		inputPrice.name="price";
		inputPrice.step = 0.01;
		inputPrice.value = data["content"][i]["price"];
		cellPrice.appendChild(inputPrice);
		
		//quotation id
		let inputQuotationId = document.createElement("input");
		inputQuotationId.type="hidden";
		inputQuotationId.name = "item";
		inputQuotationId.value = data["content"][i]["id"];
		cellPrice.appendChild(inputQuotationId);
		
		let cellAction = row.insertCell();
		let btnRemove = document.createElement("button");
		btnRemove.type= "button";
		btnRemove.textContent = "Remove";
		btnRemove.value = data["content"][i]["id"];
		btnRemove.className = "btn btn-danger";
		cellAction.appendChild(btnRemove);
		
		btnRemove.addEventListener("click",function(){
			if(confirm("Remove item?")){
				postData("/api/quotationitems/delete",`quotationItem=${this.value}`,function(){
					window.location.reload(true);
				});	
			}	
		});
	}
}

export default function createQuotationItemsTable(url, params){
	loadData(url+params,renderQuotationItemsTable);
}
