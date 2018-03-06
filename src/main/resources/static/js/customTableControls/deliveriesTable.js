"use strict";

import {loadData} from "../tableControls/ajax.js";

function renderDeliveriesTable(data){
	const table = document.getElementById("dtDeliveries");
	
	table.deleteCaption();
	let caption = table.createCaption();
	caption.textContent = `${data["totalElements"]} ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1);
	
	for(let i=0; i < data["content"].length;i++){
		let row = table.insertRow();
		
		let cellId = row.insertCell();
		cellId.textContent = data["content"][i]["id"];
		
		let cellDate = row.insertCell();
		cellDate.textContent = data["content"][i]["date"];
		
		let linkDetails = document.createElement("a");
		linkDetails.text = "Details";
		linkDetails.href = `/delivery/${data["content"][i]["id"]}/details`;		
		linkDetails.className = "btn btn-info";
		
		let cellAction = row.insertCell();
		cellAction.appendChild(linkDetails);
	}	
}

export default function(url, params){
	loadData(url+params, renderDeliveriesTable);
}