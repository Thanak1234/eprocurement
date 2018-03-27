"use strict";

import prQuotationTable from "./customTableControls/prQuotationsTable.js";
import Page from "./tableControls/page.js";
import TableControls from "./tableControls/tableControls.js";
import Sorter from "./tableControls/sorter.js";
document.addEventListener("DOMContentLoaded",function(){
		
	const prNo = document.getElementById("txtPrNo").textContent;
	//initialize page
	let quotationPage = new Page(`/api/pr/${prNo}/quotation`);
	//initialize table
	prQuotationTable(quotationPage.url,quotationPage.getPageProps()+`&page=${quotationPage.page}`);
	
	let prQuotationTableControls = new TableControls(prQuotationTable, quotationPage);
	
	const pagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	
	prQuotationTableControls.pagination = pagination;
	prQuotationTableControls.selectPageSize = selectPageSize;
	
	prQuotationTableControls.addPagination();
	prQuotationTableControls.addPageSizeControls();
	
	let sorter = new Sorter(prQuotationTableControls,0,3);
	sorter.addSortIcons();
	
	const th = document.getElementsByTagName("th");
	
	th[0].addEventListener("click",function(){
		sorter.sortTable("id");
		sorter.setSortedColumn(0);
	})
	
	th[1].addEventListener("click",function(){
		sorter.sortTable("quotationDate");
		sorter.setSortedColumn(1);
	})
	
	th[2].addEventListener("click",function(){
		sorter.sortTable("supplier.supplierName");
		sorter.setSortedColumn(2);
	})

	const formQuotation = document.addEventListener("submit",function(e){
		if(!confirm("Create New Quotation?")){
			e.preventDefault();
		}
	});
});