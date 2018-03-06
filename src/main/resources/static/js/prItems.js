"use strict";

import PageSearch from "./tableControls/pageSearch.js";
import Page from "./tableControls/page.js";
import createSelectionTable from "./customTableControls/selectionTable.js";
import TableControls from "./tableControls/tableControls.js";
import SearchControls from "./tableControls/searchControls.js";
import Sorter from "./tableControls/sorter.js";
import createPRTable from "./customTableControls/prTable.js";
import {sendFormData, postData} from "./tableControls/ajax.js";
document.addEventListener("DOMContentLoaded",function(){
	
	const prNo = document.getElementById("txtPRNo").value;
	
	let prItemPage = new Page("/api/pritems/"+prNo);
	
	/*
	 * PR TABLE 
	 */
	createPRTable(prItemPage.url,prItemPage.getPageProps()+`&page=${prItemPage.page}`);
	
	const prItemsPagination = document.getElementById("itemspagination");
	const selectPrItemPageSize = document.getElementById("selectPrItemPageSize");
	
	//initialize table controls
	let prItemTableControls = new TableControls(createPRTable, prItemPage);
	prItemTableControls.pagination = prItemsPagination;
	prItemTableControls.selectPageSize = selectPrItemPageSize;
	
	//add controls
	prItemTableControls.addPageSizeControls();
	prItemTableControls.addPagination();
	
	//add sorter
	const th = document.getElementsByTagName("th");
	let prItemSorter = new Sorter(prItemTableControls,0,3);
	prItemSorter.addSortIcons();
	
	th[0].addEventListener("click",function(){
		prItemSorter.sortTable("item.id");
		prItemSorter.setSortedColumn(0);
	});
	
	th[1].addEventListener("click",function(){
		prItemSorter.sortTable("item.name");
		prItemSorter.setSortedColumn(1);
	});
	
	th[2].addEventListener("click",function(){
		prItemSorter.sortTable("item.description");
		prItemSorter.setSortedColumn(2);
	});
	
	/*
	 * Item selection
	 */
	let itemPage = new PageSearch("/api/items");
	
	createSelectionTable(itemPage.url,itemPage.getPageProps()+`&page=${itemPage.page}`);
	
	//get pagination and page size elements
	const itemPagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	
	//initialize table controls
	let itemTableControls = new TableControls(createSelectionTable,itemPage);
	itemTableControls.pagination = itemPagination;
	itemTableControls.selectPageSize = selectPageSize;
	
	//add controls
	itemTableControls.addPageSizeControls();
	itemTableControls.addPagination();
	
	//get search elements
	const selectSearchBy = document.getElementById("selectSearchBy");
	const inputSearch = document.getElementById("inputSearch");
	const btnSearch = document.getElementById("btnSearch");
	
	//initialize search controls
	let itemSearchControls = new SearchControls(selectSearchBy,inputSearch,btnSearch,itemTableControls);
	itemSearchControls.addSearchControls();
	
	//Sorter
	let itemSorter = new Sorter(itemTableControls,7,10);
	itemSorter.addSortIcons();

	//add sorters
	th[7].addEventListener("click",function(){
		itemSorter.sortTable("id");
		itemSorter.setSortedColumn(7);
	});
	
	th[8].addEventListener("click",function(){
		itemSorter.sortTable("name");
		itemSorter.setSortedColumn(8);
	});
	
	th[9].addEventListener("click",function(){
		itemSorter.sortTable("description");
		itemSorter.setSortedColumn(9);
	});
	
	const formAddItems = document.getElementById("formAddItems");
	formAddItems.addEventListener("submit",function(e){
		sendFormData(this,"/api/pritems/add",function(){
			window.location.reload(true);
		});
		e.preventDefault();
	});

	const formPrItems = document.getElementById("formPrItems");
	formPrItems.addEventListener("submit",function(e){
		sendFormData(this,"/api/pritems/save",function(){});
		e.preventDefault();
	});
	
	const formPr = document.getElementById("formPr");
	formPr.addEventListener("submit",function(e){
		sendFormData(this,"/api/pr/"+prNo,function(){});
		e.preventDefault();
	})
	
});