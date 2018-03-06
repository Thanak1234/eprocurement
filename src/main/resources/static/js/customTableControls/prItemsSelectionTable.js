import {loadData} from "../tableControls/ajax.js"

function renderPrItemsTable(data){
	const table = document.getElementById("dtPrItems");
	
	table.deleteCaption();
	let tableCaption = table.createCaption();
	tableCaption.textContent= data["totalElements"]+` ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	
	while(table.rows[1])
		table.deleteRow(1);
	
	for(let i=0;i < data["content"].length;i++){
		let row = table.insertRow();
		
		let cellChkBoxId = row.insertCell();
		let chkBoxId = document.createElement("input");
		chkBoxId.type = "checkbox";
		chkBoxId.name = "purchaseRequestItem";
		chkBoxId.value = data["content"][i]["id"];
		cellChkBoxId.appendChild(chkBoxId);
		
		for(let d in data["content"][i]["item"]){
			let cell = row.insertCell();
			cell.textContent = data["content"][i]["item"][d];
		}
		
		let unitCell = row.insertCell();
		unitCell.textContent = data["content"][i]["unit"];
		
		let quantityCell = row.insertCell();
		quantityCell.textContent = data["content"][i]["quantity"];				
	}
}

export default function createPrItemSelectionTable(url,params){
	loadData(url+params,renderPrItemsTable);
}

