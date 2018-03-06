"use strict";

import {loadData} from "../tableControls/ajax.js";

//custom purchase requests table
function renderPurchaseRequestsTableTable(data){
	const table = document.getElementById("dtPuchaseRequestsTable");
	
	table.deleteCaption();
	let totalItems = table.createCaption();
	totalItems.textContent = `${data["totalElements"]} ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1);
	
	for(let i=0; i < data["content"].length;i++){
		let row= table.insertRow();
		
		let prNoCell = row.insertCell();
		prNoCell.textContent  = data["content"][i]["prNo"];
		
		let departmentCell = row.insertCell();
		departmentCell.textContent = data["content"][i]["department"]["departmentName"];
		
		let dateCell= row.insertCell();
		dateCell.textContent  = data["content"][i]["prDate"];
		
		let modeOfProcurementCell = row.insertCell();
		modeOfProcurementCell.textContent = data["content"][i]["modeOfProcurement"];
		
		let actionCell = row.insertCell();
		
		let prItemsLink = document.createElement("a");
		prItemsLink.href = `/pr/${data["content"][i]["prNo"]}/details`;
		prItemsLink.text = " Details";
		prItemsLink.className = "btn btn-info";
		
		let quotationLink = document.createElement("a");
		quotationLink.href = `/pr/${data["content"][i]["prNo"]}/quotation/all`;
		quotationLink.text = " Quotations";
		quotationLink.className = "btn btn-primary";
		
		let span = document.createElement("span");
		span.textContent = " ";
		
		actionCell.appendChild(prItemsLink);
		actionCell.appendChild(span);
		actionCell.appendChild(quotationLink);
	}			
}

//use this function to add table data
export default function addPurchaseRequestsTableData(url,params){
	loadData(url+params, renderPurchaseRequestsTableTable);
}
