import {loadData,postData} from "../tableControls/ajax.js";

function selectionTableCallback(data){
	
	//table
	const table = document.getElementById("dtSelection");
	
	table.deleteCaption();
	let totalItems = table.createCaption();
	
	totalItems.textContent = `${data["totalElements"]} ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	//clear rows
	while(table.rows[1])
		table.deleteRow(1);
	
	for(let i = 0;i < data["content"].length;i++){
		let row = table.insertRow();
		let chkBoxCell = row.insertCell();
		
		let chkBox = document.createElement("input");
	
		chkBox.type="checkbox";
		chkBox.class="chkBoxItem";
		chkBox.value = data["content"][i]["id"];
		chkBox.name = "items"
		chkBoxCell.appendChild(chkBox);
		
		for(let d in data["content"][i]){
			let cell = row.insertCell();
			cell.innerHTML = data["content"][i][d];
		}		
	}		
}

export default function createSelectionTable(url,params){
	loadData(url+params,selectionTableCallback);
}