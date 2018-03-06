import addPaginationImpl from "./pagination.js";
import {loadData} from "./ajax.js";
import addPageSizeImpl from "./size.js";

export default class TableControls{
	
	constructor(updateTable,page){
		this.updateTable = updateTable;
		this.page = page;
	}

	addPageSizeControls(){

		addPageSizeImpl(this.page,
				this.updateTable,
				addPaginationImpl,
				this.selectPageSize,
				this.pagination);
	}

	addPagination(){
		
		addPaginationImpl(this.page,this.updateTable,this.pagination);
	}	
}

