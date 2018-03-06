"use strict";

import addPurchaseRequestsTableData from "./customTableControls/purchaseRequestsTable.js";
import Page from "./tableControls/page.js";
import TableControls from "./tableControls/tableControls.js";
import Sorter from "./tableControls/sorter.js";
document.addEventListener("DOMContentLoaded",function(){
	
	//custom page
	class PrPage extends Page{
		constructor(url){
			super(url);
			this.searchBy = "";
			this.department = "";
			this.pr = "";
		}
		
		getPageProps(){
			return super.getPageProps()+`&searchBy=${this.searchBy}&startDate=${this.startDate}&endDate=${this.endDate}&department=${this.department}&pr=${this.pr}`;
		}
	}
	
	/*
	 * custom update implementation to 
	 * enable/ disable search button,
	 * clear search field and update
	 * table and pagination
	 */
	function update(){
		inputSearch.value ="";
		btnSearch.disabled= true;
		prPage.searchBy = "";
		prPage.page =0;
		prTableControls.addPagination();
		addPurchaseRequestsTableData(prPage.url,prPage.getPageProps()+`&page=${prPage.page}`);	
	}
	/*
	 * Search controls block
	 */
	//start date
	const inputStartDate = document.getElementById("inputStartDate");
	//set start date 30 days before current date
	let startDate = new Date();
	startDate.setDate(startDate.getDate()-30);
	inputStartDate.valueAsDate = startDate;
	inputStartDate.addEventListener("change",function(){
		prPage.startDate = this.value;
		update();
	});
	//end date
	const inputEndDate = document.getElementById("inputEndDate");
	inputEndDate.valueAsDate = new Date();
	inputEndDate.addEventListener("change",function(){
		prPage.endDate = this.value;
		update();		
	});
	//filter department
	const selectDepartment = document.getElementById("selectDepartment");
	selectDepartment.addEventListener("change",function(){
		
		if(this.value==""){
			prPage.searchBy = "";		
		}else{
			prPage.searchBy = "department";
			prPage.department = this.value;		
		}	
		btnSearch.disabled= true;
		prPage.page =0;
		prTableControls.addPagination();
		addPurchaseRequestsTableData(prPage.url,prPage.getPageProps()+`&page=${prPage.page}`);
		inputSearch.value ="";
	})
	//search by purchase request
	const inputSearch = document.getElementById("inputPrSearch");
	inputSearch.addEventListener("input",function(){
		btnSearch.disabled = false;
	});
	btnSearch.addEventListener("click",function(){
		prPage.searchBy = "prNo";
		prPage.pr = inputSearch.value;
		prPage.page =0;
		prTableControls.addPagination();
		addPurchaseRequestsTableData(prPage.url,prPage.getPageProps()+`&page=${prPage.page}`);
		selectDepartment.value="";
	});
	
	//end search controls block

	/*
	 * Table controls
	 */
	let prPage = new PrPage("/api/pr");
	prPage.startDate = inputStartDate.value;
	prPage.endDate = inputEndDate.value;

	addPurchaseRequestsTableData(prPage.url,prPage.getPageProps()+`&page=${prPage.page}`);
	
	let prTableControls = new TableControls(addPurchaseRequestsTableData,prPage);
	
	const pagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	
	prTableControls.pagination = pagination;
	prTableControls.selectPageSize = selectPageSize;
	
	prTableControls.addPagination();
	prTableControls.addPageSizeControls();
	//end table controls block
	
	/*
	 * Sort controls
	 */
	let prSorter = new Sorter(prTableControls,0,4);
	prSorter.addSortIcons();
	
	const th = document.getElementsByTagName("th");
	
	th[0].addEventListener("click",function(){
		prSorter.sortTable("prNo");
		prSorter.setSortedColumn(0);
	});
	
	th[2].addEventListener("click",function(){
		prSorter.sortTable("prDate");
		prSorter.setSortedColumn(2);
	})
	
	th[1].addEventListener("click",function(){
		prSorter.sortTable("department.departmentName");
		prSorter.setSortedColumn(1);
	})
	
	th[3].addEventListener("click",function(){
		prSorter.sortTable("modeOfProcurement");
		prSorter.setSortedColumn(3);
	})
	
	//end sort controls block
});


