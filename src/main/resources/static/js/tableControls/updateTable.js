"use strict";

import {loadData} from "./ajax.js";

function renderUpdateTable(data){
	//cache table
	const table = document.getElementById("dtUpdatable");
	
	//caption
	table.deleteCaption();
	let totalItems = table.createCaption();	
	totalItems.textContent =`${data["totalElements"]} ${data["totalElements"] > 1 ? "items" : "item"} found`
	
	//clear tbody
	while(table.rows[1])
		table.deleteRow(1);

	for(let i=0; i < data["content"].length;i++){
		let row = table.insertRow();
		for(let d in data["content"][i]){	
			let cell = row.insertCell();
			cell.textContent = data["content"][i][d];
		}
		let actionCell = row.insertCell();
		let linkUpdate = document.createElement("a");
		linkUpdate.href = `./${data["content"][i].id}`
		linkUpdate.text = "Update";
		linkUpdate.className="btn btn-default";
		actionCell.appendChild(linkUpdate);
	}		
}

export default function createUpdateTable(url,params){
	loadData(url+params,renderUpdateTable);
}


