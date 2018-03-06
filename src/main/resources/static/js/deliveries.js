"use strict";

import deliveriesTable  from "./customTableControls/deliveriesTable.js";
import Page from "./tableControls/page.js";
import tableControls from "./tableControls/tableControls.js";
import Sorter from "./tableControls/sorter.js";

document.addEventListener("DOMContentLoaded", function(){
	
	const poNo = document.getElementById("poNo").textContent;
	//initialize deliveries page
	let deliveriesTablePage = new Page(`/api/po/${poNo}/delivery`);
	//initialize table
	deliveriesTable(deliveriesTablePage.url, deliveriesTablePage.getPageProps()+`&page=${deliveriesTablePage.page}`);
	//initialize table controls
	let deliveriesTableControls = new tableControls(deliveriesTable, deliveriesTablePage);
	//get table controls
	const pagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	//set table controls
	deliveriesTableControls.pagination = pagination;
	deliveriesTableControls.selectPageSize = selectPageSize;
	//add table controls
	deliveriesTableControls.addPagination();
	deliveriesTableControls.addPageSizeControls();
	//initialize sorter
	let deliveriesSorter = new Sorter(deliveriesTableControls, 0 ,2);
	deliveriesSorter.addSortIcons();
	
	const th = document.getElementsByTagName("th");
	//add sorters
	th[0].addEventListener("click", function(){
		deliveriesSorter.sortTable("id");
		deliveriesSorter.setSortedColumn(0);
	});
	
	th[1].addEventListener("click", function(){
		deliveriesSorter.sortTable("date");
		deliveriesSorter.setSortedColumn(1);
	});
	
});