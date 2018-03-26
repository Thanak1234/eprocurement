"use strict";

import PageSearch from "./tableControls/pageSearch.js";
import createUpdateTable from "./tableControls/updateTable.js";
import TableControls from "./tableControls/tableControls.js";
import SearchControls from "./tableControls/searchControls.js";
import Sorter from "./tableControls/sorter.js";

document.addEventListener("DOMContentLoaded",function(){
	
	let itemPage = new PageSearch("/api/items");
	itemPage.sort="id,DESC";
	
	createUpdateTable(itemPage.url,itemPage.getPageProps()+`&page=${itemPage.page}`);
	
	const pagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	
	//initialize table controls
	let itemTableControls = new TableControls(createUpdateTable,itemPage);
	itemTableControls.pagination = pagination;
	itemTableControls.selectPageSize = selectPageSize;
	
	//add table controls
	itemTableControls.addPagination();
	itemTableControls.addPageSizeControls();
	
	const selectSearchBy = document.getElementById("selectSearchBy");
	const inputSearch = document.getElementById("inputSearch");
	const btnSearch = document.getElementById("btnSearch");
	
	//Search controls
	let itemSearchControls = new SearchControls(selectSearchBy,inputSearch,btnSearch,itemTableControls);
	itemSearchControls.addSearchControls();
	
	//sorter
	let itemSorter = new Sorter(itemTableControls,0,3);
	itemSorter.addSortIcons();
	
	const th = document.getElementsByTagName("th");
	
	//add sorters
	th[0].addEventListener("click",function(){
		itemSorter.sortTable("id");
		itemSorter.setSortedColumn(0);
	});
	
	th[1].addEventListener("click",function(){
		itemSorter.sortTable("name");
		itemSorter.setSortedColumn(1);	
	});
	
	th[2].addEventListener("click",function(){
		itemSorter.sortTable("description");
		itemSorter.setSortedColumn(2);
	});
	
});