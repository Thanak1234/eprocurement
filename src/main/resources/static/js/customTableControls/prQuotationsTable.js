"use strict";

import {loadData} from "../tableControls/ajax.js";

function renderPrQuotationsTable(data){
	
	const table = document.getElementById("dtPrQuotations");
	
	while(table.rows[1])
		table.deleteRow(1);
	
	table.deleteCaption();
	let caption = table.createCaption();
	caption.textContent = data["totalElements"]+ ` ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	for(let i=0; i < data["content"].length;i++){
		let row = table.insertRow();
		
		let cellId = row.insertCell();
		cellId.textContent = data["content"][i]["id"];
		
		let cellDate = row.insertCell();
		cellDate.textContent = data["content"][i]["quotationDate"];
		
		let cellSupplier = row.insertCell();
		cellSupplier.textContent = data["content"][i]["supplier"]["supplierName"];	
		
		let actionCell  = row.insertCell();
		
		let itemsLink = document.createElement("a");
		itemsLink.href = `/quotation/${data["content"][i]["id"]}/details`;
		itemsLink.text =" Details";
		itemsLink.className = "btn btn-info";
		
		let purchaseOrderLink = document.createElement("a");
		purchaseOrderLink.href = `/quotation/${data["content"][i]["id"]}/po/new`;
		purchaseOrderLink.text = " New Purchase Order";
		purchaseOrderLink.className = "btn btn-primary";
		
		let span = document.createElement("span");
		span.textContent = " ";
		actionCell.appendChild(itemsLink);
		actionCell.appendChild(span);
		actionCell.appendChild(purchaseOrderLink);
	}
}

export default function createPrQuotationTable(url,params){
	loadData(url+params,renderPrQuotationsTable);
}