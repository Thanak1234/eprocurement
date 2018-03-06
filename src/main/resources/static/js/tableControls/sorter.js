"use strict";

export default class Sorter{
	constructor(tableControls,startIndex,noOfColumns){
		this.startIndex = startIndex;
		this.noOfColumns = noOfColumns;
		this.dir = "";	
		this.tableControls = tableControls;
	}
	
	
	sortTable(header){
		if(this.tableControls.page.sort == `${header},ASC` || this.tableControls.page.sort == ""){
			this.tableControls.page.sort = `${header},DESC`;
			this.dir = "DESC";
		}else{
			this.tableControls.page.sort = `${header},ASC`;
			this.dir = "ASC";
		}
			this.tableControls.updateTable(this.tableControls.page.url,this.tableControls.page.getPageProps()+`&page=${this.tableControls.page.page}`)	
			this.tableControls.addPagination();
	}
	
	setSortedColumn(index){	
		const tableHeader = document.getElementsByTagName("th");
		for(let i = this.startIndex;i < this.noOfColumns;i++){		
			if(i === index){
				tableHeader[i].className = "text-primary";
				if(this.dir === "ASC"){			
					tableHeader[i].firstElementChild.className= "glyphicon glyphicon-sort-by-attributes";
				}else if(this.dir==="DESC"){					
					tableHeader[i].firstElementChild.className = "glyphicon glyphicon-sort-by-attributes-alt";
				}
			}else{
				tableHeader[i].removeAttribute("class");
				tableHeader[i].firstElementChild.className="glyphicon glyphicon-sort";
			}
		}
	}
	
	addSortIcons(){
		const tableHeader = document.getElementsByTagName("th");
		for(let i = this.startIndex;i < this.noOfColumns;i++){
			let span = document.createElement("span");
			span.className="glyphicon glyphicon-sort";
			tableHeader[i].insertBefore(span,tableHeader[i].firstChild);
		}
	}	
}

