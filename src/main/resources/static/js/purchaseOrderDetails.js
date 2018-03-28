"use strict";

import quotationItemsTable from "./customTableControls/quotationItemsSelectionTable.js";
import Page from "./tableControls/page.js";
import tableControls from "./tableControls/tableControls.js";
import Sorter from "./tableControls/sorter.js";
import {sendFormData} from "./tableControls/ajax.js";
import poItemTable from "./customTableControls/purchaseOrderItemsTable.js";

document.addEventListener("DOMContentLoaded",function(){
	
	const th = document.getElementsByTagName("th");
	
	/*
	 *Purchase order items 
	 */
	//get purchase order item number
	const poNo = document.getElementById("poNo").textContent;
	//po item page
	let purchaseOrderItemPage = new Page("/api/poitems/"+poNo);
	//initialize po item table
	poItemTable(purchaseOrderItemPage.url,purchaseOrderItemPage.getPageProps()+`&page=${purchaseOrderItemPage.page}`);
	//initialize table controls
	let poItemTableControls = new tableControls(poItemTable, purchaseOrderItemPage);
	//pagination and select page size
	const poItemPagination = document.getElementById("poItemPagination");
	const selectPageSizePurchaseOrderItems = document.getElementById("selectPageSizePurchaseOrderItems");
	//set pagination and page size
	poItemTableControls.pagination = poItemPagination;
	poItemTableControls.selectPageSize = selectPageSizePurchaseOrderItems;
	//add table controls
	poItemTableControls.addPagination();
	poItemTableControls.addPageSizeControls();
	//sorting
	let poItemSorter = new Sorter(poItemTableControls,0,3);
	poItemSorter.addSortIcons();
	//add sorter to columns
	th[0].addEventListener("click",function(){
		poItemSorter.sortTable("quotationItem.purchaseRequestItem.item.id");
		poItemSorter.setSortedColumn(0);
	});
	
	th[1].addEventListener("click",function(){
		poItemSorter.sortTable("quotationItem.purchaseRequestItem.item.name");
		poItemSorter.setSortedColumn(1);
	});
	
	th[2].addEventListener("click",function(){
		poItemSorter.sortTable("quotationItem.purchaseRequestItem.item.description");
		poItemSorter.setSortedColumn(2);
	});
	
	/*
	 * Quotation items
	 */
	//get quotation id
	const quotationId = document.getElementById("quotation.id").value;
	//initialize quotation item page
	let quotationItemsPage = new Page("/api/quotationitems/"+quotationId);
	//initiliaze quotation item table
	quotationItemsTable(quotationItemsPage.url, quotationItemsPage.getPageProps()+`&page=${quotationItemsPage.page}`);

	//initialize quotation table controls
	let quotationItemTableControls = new tableControls(quotationItemsTable, quotationItemsPage);
	const pagination = document.getElementById("pagination");
	const selectQuotationItemsPageSize = document.getElementById("selectQuotationItemsPageSize");
	//set table controls
	quotationItemTableControls.pagination = pagination;
	quotationItemTableControls.selectPageSize = selectQuotationItemsPageSize;
	//add table controls
	quotationItemTableControls.addPagination();
	quotationItemTableControls.addPageSizeControls();
	//initialize sorter
	let quotationItemSorter = new Sorter(quotationItemTableControls,8,11);
	//sort by id
	quotationItemSorter.addSortIcons();
	th[8].addEventListener("click",function(){
		quotationItemSorter.sortTable("purchaseRequestItem.item.id");
		quotationItemSorter.setSortedColumn(8);
	});
	//sort by item name
	th[9].addEventListener("click",function(){
		quotationItemSorter.sortTable("purchaseRequestItem.item.name");
		quotationItemSorter.setSortedColumn(9);
	});
	//sort by description
	th[10].addEventListener("click",function(){
		quotationItemSorter.sortTable("purchaseRequestItem.item.description");
		quotationItemSorter.setSortedColumn(10);
	});
	
	const formAddItems = document.getElementById("formAddItems");
	formAddItems.addEventListener("submit", function(e){
		if(confirm("Add items?")){
			sendFormData(this,"/api/poitems/add",function(){
				window.location.reload(true);
			})	
		}
		e.preventDefault();
	})
	
	const formPo = document.getElementById("formPo");
	formPo.addEventListener("submit",function(e){
		if(confirm("Save purchase order?")){
			sendFormData(this,"/api/po/"+poNo,function(){
				window.location.href="/po/all";
			})
		}	
		e.preventDefault();
	});
});
