import {loadData,postData} from "../tableControls/ajax.js";
/*
 * Create PR table
 */
function prTableCallback(data){
	const table = document.getElementById("dtRemove");
	
	//table caption
	table.deleteCaption();
	let totalItems = table.createCaption();
	totalItems.textContent = `${data["totalElements"]} ${data["totalElements"] > 1 ? "items" : "item"} found`;
	
	while(table.rows[1])
		table.deleteRow(1);
	
	if(data["totalElements"]){
		for(let i=0; i < data["content"].length;i++){
			let row = table.insertRow();
			
			for(let d in data["content"][i]["item"]){
				let cell = row.insertCell();
				cell.textContent = data["content"][i]["item"][d];
			}
			
			//Unit
			let selectUnit = document.createElement("select");
			selectUnit.name = "unit";
			let units = ["bottle", "pack", "box", "cart", "bundle", "piece", "ream", "unit", "can", 
				"set", "book", "pad", "stack", "pouch", "sachet", "gal", "kilo", "liter"];
			let option = document.createElement("option");
			option.value = null;
			option.text = "Select Unit";
			option.disabled = true;
			selectUnit.add(option);
			for(let u=0;u < units.length;u++){
				let optionUnit = document.createElement("option")
				optionUnit.text= units[u];
				optionUnit.value = units[u];
				selectUnit.add(optionUnit);		
			}
			selectUnit.value = data["content"][i]["unit"];
			let unitCell = row.insertCell();
			unitCell.appendChild(selectUnit);
			
			//for pr item id
			let inputPrItemId = document.createElement("input");
			inputPrItemId.type= "hidden";
			inputPrItemId.name= "item";
			inputPrItemId.value = data["content"][i]["id"];
			unitCell.appendChild(inputPrItemId);
			
			//input quantity
			let inputQuantity = document.createElement("input");
			inputQuantity.name = "quantity";
			inputQuantity.type = "number";
			inputQuantity.value = data["content"][i]["quantity"]
			let quantityCell = row.insertCell();
			quantityCell.appendChild(inputQuantity);

			//input abc
			let inputABC = document.createElement("input");
			inputABC.name="abc";
			inputABC.type="number";
			inputABC.step=0.01;
			inputABC.value=data["content"][i]["abc"];
			let abcCell = row.insertCell();
			abcCell.appendChild(inputABC);
		
			//remove button
			let btnRemove = document.createElement("button");
			btnRemove.type="button";
			btnRemove.textContent = "Remove";
			btnRemove.className = "btn btn-danger";
			btnRemove.value = data["content"][i]["id"];
			let actionCell = row.insertCell();
			actionCell.appendChild(btnRemove);
			
			btnRemove.addEventListener("click",function(){
				if(confirm(`Remove this item?`)){
					postData("/api/pritems/delete", `id= ${this.value}`,function(){
						window.location.reload(true);
					});		
				}					
			});
		}
	}	
}

export default function createPRTable(url,params){
	loadData(url+params,prTableCallback);	
}