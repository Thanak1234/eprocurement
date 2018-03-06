"use strict";

export default class SearchControls{
	constructor(selectSearchBy,inputSearch,btnSearch,tableControls){
		this.selectSearchBy = selectSearchBy;
		this.inputSearch = inputSearch;
		this.btnSearch = btnSearch;
		this.tableControls = tableControls;
	}
	
	addSearchControls(){
		
		let tableControls = this.tableControls;
		
		this.selectSearchBy.addEventListener("change",function(){
			inputSearch.disabled = false;
		});

		this.inputSearch.addEventListener("input",function(){
			btnSearch.disabled = false;
		})

		this.btnSearch.addEventListener("click",function(){
			tableControls.page.searchBy = selectSearchBy.value;
			tableControls.page.searchValue = inputSearch.value;
			tableControls.page.page = 0;
			tableControls.addPagination();
			tableControls.updateTable(tableControls.page.url,tableControls.page.getPageProps()+`&page=${tableControls.page.page}`);
		});
	}
}



