"use strict";

import Page from "./tableControls/page.js";
import prTable from "./customTableControls/purchaseRequestsTable.js";
import TableControls from "./tableControls/tableControls.js";
import Sorter from "./tableControls/sorter.js";

document.addEventListener("DOMContentLoaded",function(){
	
	class PrPage extends Page{
		constructor(url){
			super(url);
		}
		
		getPageProps(){
			return super.getPageProps()+`&startDate=${this.startDate}&endDate=${this.endDate}`;
		}
	}
	
	function update(){
		prPage.page =0;
		prTableControls.addPagination();
		prTable(prPage.url, prPage.getPageProps()+`&page=${prPage.page}`);
	}
	
	//input start date
	const inputStartDate = document.getElementById("inputStartDate");
	let startDate = new Date();
	startDate.setDate(startDate.getDate()-30);
	inputStartDate.valueAsDate = startDate;
	inputStartDate.addEventListener("change",function(){
		prPage.startDate = this.value;
		update();
	});
	
	//end date
	const inputEndDate = document.getElementById("inputEndDate");
	let endDate = new Date();
	inputEndDate.valueAsDate = endDate;
	inputEndDate.addEventListener("change",function(){
		prPage.endDate = this.value;
		update();
	});
	
	const department = document.getElementById("department").value;
	let prPage = new PrPage(`/api/pr/${department}/all`);
	prPage.startDate = inputStartDate.value;
	prPage.endDate = inputEndDate.value;
	//initialize table
	prTable(prPage.url, prPage.getPageProps()+`&page=${prPage.page}`);
	//initialize table controls
	let prTableControls = new TableControls(prTable,prPage);
	const pagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	//set table controls
	prTableControls.pagination = pagination;
	prTableControls.selectPageSize = selectPageSize;
	//add table controls
	prTableControls.addPagination();
	prTableControls.addPageSizeControls();
	
	//sort
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
});