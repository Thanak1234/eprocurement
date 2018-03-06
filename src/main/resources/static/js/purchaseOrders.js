import purchaseOrderTable from "./customTableControls/purchaseOrdersTable.js";
import PageSearch from "./tableControls/pageSearch.js";
import TableControls from "./tableControls/tableControls.js";
import Sorter from "./tableControls/sorter.js";

document.addEventListener("DOMContentLoaded",function(){
	
	class POPage extends PageSearch{
		constructor(url){
			super(url);
		}
		getPageProps(){
			return super.getPageProps()+`&startDate=${this.startDate}&endDate=${this.endDate}`;
		}
	}
	
	let startDateValue = new Date();
	startDateValue.setDate(startDateValue.getDate()-30);
	const startDate = document.getElementById("startDate");
	startDate.valueAsDate = startDateValue;
	startDate.addEventListener("change",function(){
		
		poPage.startDate = this.value;
		update();
	});
	
	const endDate = document.getElementById("endDate");
	endDate.valueAsDate = new Date();
	endDate.addEventListener("change",function(){

		poPage.endDate = this.value;
		update();
	});
	
	/*
	 * custom update implementation to
	 * clear, enable/disable search field,
	 * empty searchBy,
	 * disable search button,
	 * update pagination and table
	 */
	function update(){
		inputSearch.value = "";
		btnSearch.disabled = true;
		poPage.searchBy = "";
		poPage.page = 0;
		purchaseOrderTableControls.addPagination();
		purchaseOrderTable(poPage.url,poPage.getPageProps()+`&page=${poPage.page}`);
	}
	
	//search controls
	const inputSearch = document.getElementById("search");
	inputSearch.addEventListener("input",function(){
		btnSearch.disabled = false;
	});
	const btnSearch = document.getElementById("btnSearch");
	btnSearch.addEventListener("click",function(){
		poPage.searchBy = "prNo";
		poPage.searchValue = inputSearch.value;
		purchaseOrderTableControls.addPagination();
		purchaseOrderTable(poPage.url,poPage.getPageProps()+`&page=${poPage.page}`);
	});
	//end search controls code 
	
	let poPage = new POPage("/api/po/all");
	poPage.startDate = startDate.value;
	poPage.endDate = endDate.value;
	//initialize purchase order table
	purchaseOrderTable(poPage.url,poPage.getPageProps()+`&page=${poPage.page}`);
	//initialize table controls
	let purchaseOrderTableControls = new TableControls(purchaseOrderTable,poPage);
	
	//get pagination and page size selector
	const pagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	//set table controls
	purchaseOrderTableControls.pagination  = pagination;
	purchaseOrderTableControls.selectPageSize = selectPageSize;
	//add table controls
	purchaseOrderTableControls.addPagination();
	purchaseOrderTableControls.addPageSizeControls();
	//initialize sorter
	let sorter = new Sorter(purchaseOrderTableControls,0,4);
	sorter.addSortIcons();
	//get th
	const th = document.getElementsByTagName("th");
	//add sorter
	th[0].addEventListener("click",function(){
		sorter.sortTable("poNo");
		sorter.setSortedColumn(0);
	});
	
	th[1].addEventListener("click",function(){
		sorter.sortTable("quotation.purchaseRequest.prNo");
		sorter.setSortedColumn(1);
	});
	
	th[2].addEventListener("click",function(){
		sorter.sortTable("date");
		sorter.setSortedColumn(2);
	});
	
	th[3].addEventListener("click",function(){
		sorter.sortTable("quotation.supplier.supplierName");
		sorter.setSortedColumn(3);
	})
	
});