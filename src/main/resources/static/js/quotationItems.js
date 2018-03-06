"use strict";

import Page from "./tableControls/page.js";
import createPrItemSelectionTable from "./customTableControls/prItemsSelectionTable.js";
import {sendFormData} from "./tableControls/ajax.js";
import createQuotationItemsTable from "./customTableControls/quotationItemsTable.js";
import TableControls from "./tableControls/tableControls.js";
import Sorter from "./tableControls/sorter.js";

document.addEventListener("DOMContentLoaded",function(){
	
	/*
	 * PR item controls
	 */
	const prNo = document.getElementById("txtPrNo").innerText;
	
	let prItemsPage = new Page("/api/pritems/"+prNo);
	//initialize table
	createPrItemSelectionTable(prItemsPage.url,prItemsPage.getPageProps()+`&page=${prItemsPage.page}`);
	//initalize table controls
	let prItemTableControls = new TableControls(createPrItemSelectionTable,prItemsPage);
	
	//pr item table controls
	const paginationPrItems = document.getElementById("paginationPrItems");
	const selectPageSizePrItems = document.getElementById("selectPageSizePrItems");
	
	//configure table controls
	prItemTableControls.pagination = paginationPrItems;
	prItemTableControls.selectPageSize = selectPageSizePrItems;
	//add table controls
	prItemTableControls.addPagination();
	prItemTableControls.addPageSizeControls();
	
	const th= document.getElementsByTagName("th");
	let prSorter = new Sorter(prItemTableControls,8,11);
	prSorter.addSortIcons();
	
	th[8].addEventListener("click",function(){
		prSorter.sortTable("item.id");
		prSorter.setSortedColumn(8);
	});
	
	th[9].addEventListener("click",function(){
		prSorter.sortTable("item.name");
		prSorter.setSortedColumn(9);
	});
	
	th[10].addEventListener("click",function(){
		prSorter.sortTable("item.description");
		prSorter.setSortedColumn(10);
	});
	
	//add quotation items
	const formAddItems = document.getElementById("formAddItems");
	formAddItems.addEventListener("submit",function(e){
		sendFormData(this,"/api/quotationitems/add",function(){
			window.location.reload(true);
		})
		e.preventDefault();
	});
		
	/*
	 * Quoatation items controls
	 */
	
	//initialize page properties
	const quotationId = document.getElementById("inputQuotation").value;
	let quotationItemsPage = new Page("/api/quotationitems/"+quotationId);
	
	//initialize table
	createQuotationItemsTable(quotationItemsPage.url,quotationItemsPage.getPageProps()+`&page=${quotationItemsPage.page}`);
	//initialize table controls
	let quotationItemsTableControl = new TableControls(createQuotationItemsTable,quotationItemsPage);
	//get table control elements
	const paginationQuotationItems = document.getElementById("paginationQuotationItems");
	const selectPageSizeQuotationItems = document.getElementById("selectPageSizeQuotationItems");
	//configure table control to elements
	quotationItemsTableControl.pagination = paginationQuotationItems;
	quotationItemsTableControl.selectPageSize = selectPageSizeQuotationItems;
	//add table controls
	quotationItemsTableControl.addPagination();
	quotationItemsTableControl.addPageSizeControls();
	
	//save quotation
	const formQuotationItems = document.getElementById("formQuotationItems");
	formQuotationItems.addEventListener("submit",function(e){
		sendFormData(this,"/api/quotationitems/save",function(){});
		e.preventDefault();
	})
	
	const formQuotation = document.getElementById("formQuotation");
	formQuotation.addEventListener("submit",function(e){
		sendFormData(this,"/api/quotation/"+quotationId,function(){});
		e.preventDefault();
	})
	//initialize sorter
	let sorter = new Sorter(quotationItemsTableControl,0,3);
	sorter.addSortIcons();
	
	//add sort
	th[0].addEventListener("click",function(){
		sorter.sortTable("purchaseRequestItem.item.id");
		sorter.setSortedColumn(0);
	});
	th[1].addEventListener("click",function(){
		sorter.sortTable("purchaseRequestItem.item.name");
		sorter.setSortedColumn(1);
	});
	th[2].addEventListener("click",function(){
		sorter.sortTable("purchaseRequestItem.item.description");
		sorter.setSortedColumn(2);
	});
});