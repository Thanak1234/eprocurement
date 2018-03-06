import {loadData} from "../tableControls/ajax.js";

function renderPurchaseOrdersTable(data){
	
	const table = document.getElementById("dtPurchaseOrders");
	
	table.deleteCaption();
	let caption = table.createCaption();
	caption.textContent = data["totalElements"] + 
	` ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1);
	
	for(let i=0;i < data["content"].length;i++){
		let row = table.insertRow();
		
		let cellPoNo = row.insertCell();
		cellPoNo.textContent = data["content"][i]["poNo"];
		
		let cellPrNo = row.insertCell();
		cellPrNo.textContent = data["content"][i]["quotation"]["purchaseRequest"]["prNo"];
		
		let cellDate = row.insertCell();
		cellDate.textContent = data["content"][i]["date"];
		
		let cellSupplier = row.insertCell();
		cellSupplier.textContent = data["content"][i]["quotation"]["supplier"]["supplierName"];
		
		let cellAction = row.insertCell();
		
		let linkDetails = document.createElement("a");
		linkDetails.href=`/po/${data["content"][i]["poNo"]}/details`;
		linkDetails.text = "Details";
		linkDetails.className = "btn btn-info";
		
		let linkDelivery = document.createElement("a");
		linkDelivery.href=`/po/${data["content"][i]["poNo"]}/delivery/all`;
		linkDelivery.text = "Deliveries";
		linkDelivery.className = "btn btn-primary";
		
		let span = document.createElement("span");
		span.textContent = " ";
		cellAction.appendChild(linkDetails);
		cellAction.appendChild(span);
		cellAction.appendChild(linkDelivery);
	}
}

export default function createPurchaseOrderTable(url,params){
	loadData(url+params,renderPurchaseOrdersTable);
}