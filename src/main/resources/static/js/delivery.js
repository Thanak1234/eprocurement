"use strict";

import poTable from "./customTableControls/poItemsSelection.js";
import Page from "./tableControls/page.js";
import TableControls from "./tableControls/tableControls.js";
import Sorter from "./tableControls/sorter.js";
import {sendFormData} from "./tableControls/ajax.js";
import deliveryItemTable from "./customTableControls/deliveryItemsTable.js";
 
document.addEventListener("DOMContentLoaded",function(){
	const poNo = document.getElementById("poNo").textContent;
	const th = document.getElementsByTagName("th");
	const deliveryid = document.getElementById("deliveryid").textContent;
	/*
	 * Delivery table controls
	 */
	//initialize delivery item page
	let deliveryItemPage = new Page("/api/deliveryitem/"+deliveryid);
	//initialize delivery item table
	deliveryItemTable(deliveryItemPage.url, deliveryItemPage.getPageProps()+`&page=${deliveryItemPage.page}`)
	//get table controls
	const selectPageSizeDeliveryItem = document.getElementById("selectPageSizeDeliveryItem");
	const paginationDeliveryItems = document.getElementById("paginationDeliveryItems");
	//initialize table controls
	let deliveryItemTableControls = new TableControls(deliveryItemTable,deliveryItemPage);
	//set table controls
	deliveryItemTableControls.pagination = paginationDeliveryItems;
	deliveryItemTableControls.selectPageSize = selectPageSizeDeliveryItem;
	//add table controls
	deliveryItemTableControls.addPagination();
	deliveryItemTableControls.addPageSizeControls();
	
	/*
	 *Delivery item sorter 
	 */
	let deliveryItemSorter = new Sorter(deliveryItemTableControls,0,3);
	deliveryItemSorter.addSortIcons();
	
	th[0].addEventListener("click",function(){
		deliveryItemSorter.sortTable("purchaseOrderItem.quotationItem.purchaseRequestItem.item.id");
		deliveryItemSorter.setSortedColumn(0);
	});
	
	th[1].addEventListener("click",function(){
		deliveryItemSorter.sortTable("purchaseOrderItem.quotationItem.purchaseRequestItem.item.name");
		deliveryItemSorter.setSortedColumn(1);
	});
	
	th[2].addEventListener("click",function(){
		deliveryItemSorter.sortTable("purchaseOrderItem.quotationItem.purchaseRequestItem.item.description");
		deliveryItemSorter.setSortedColumn(2);
	});
	
	const formDeliveryItem = document.getElementById("formDeliveryItem");
	formDeliveryItem.addEventListener("submit",function(e){
		if(confirm("Save items?")){
			sendFormData(this,"/api/deliveryitem/save",function(){});
		}
		e.preventDefault();
	});
	
	/*
	 * PO table controls
	 */
	
	//initialize po page
	let poPage = new Page("/api/poitems/"+poNo);
	//initialize po table
	poTable(poPage.url,poPage.getPageProps()+`&page=${poPage.page}`);
	
	/*
	 * Table Controls
	 */
	//initialize table controls
	let poTableControls = new TableControls(poTable,poPage);
	//select pagination and page size control
	const paginationPo = document.getElementById("paginationPo");
	const selectPageSizePo = document.getElementById("selectPageSizePo");
	//set pagination and page size
	poTableControls.pagination = paginationPo;
	poTableControls.selectPageSize = selectPageSizePo;
	//add table controls
	poTableControls.addPagination();
	poTableControls.addPageSizeControls();
	
	/*
	 * Sorter
	 */	
	let poSorter = new Sorter(poTableControls,9,12);
	poSorter.addSortIcons();
	//add sorters
	th[9].addEventListener("click",function(){
		poSorter.sortTable("quotationItem.purchaseRequestItem.item.id");
		poSorter.setSortedColumn(9);
	});
	
	th[10].addEventListener("click",function(){
		poSorter.sortTable("quotationItem.purchaseRequestItem.item.name");
		poSorter.setSortedColumn(10);
	});
	
	th[11].addEventListener("click",function(){
		poSorter.sortTable("quotationItem.purchaseRequestItem.item.description");
		poSorter.setSortedColumn(11);
	});
	
	const formPoItems = document.getElementById("formPoItems");
	formPoItems.addEventListener("submit", function(e){
		if(confirm("Add items?")){
			sendFormData(this,`/api/deliveryitem/${deliveryid}/add`,function(){
				window.location.reload(true);
			});
		}
		e.preventDefault();
	});

	const formDelivery = document.getElementById("formDelivery");
	formDelivery.addEventListener("submit",function(e){
		if(confirm("Save delivery?")){
			sendFormData(this,`/api/delivery/${deliveryid}`,function(){
				window.location.reload(true);
			});
		}
		e.preventDefault();
	});
	
	const btnSaveAll = document.getElementById("btnSaveAll");
	btnSaveAll.addEventListener("click",function(e){
		if(confirm("Save changes?")){
			sendFormData(formDelivery,`/api/delivery/${deliveryid}`,function(){
				sendFormData(formDeliveryItem,"/api/deliveryitem/save",function(){
					window.location.href=`/po/${poNo}/delivery/all`;
				});
			});
		}
		e.preventDefault();
	});
})