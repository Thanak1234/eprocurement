"use strict";

import PageSearch from "./tableControls/pageSearch.js";
import createUpdateTable from "./tableControls/updateTable.js";
import TableControls from "./tableControls/tableControls.js";
import SearchControls from "./tableControls/searchControls.js";
import Sorter from "./tableControls/sorter.js";

document.addEventListener("DOMContentLoaded",function(){
	
	let departmentPageSearch = new PageSearch("/api/departments");
	
	createUpdateTable(departmentPageSearch.url,departmentPageSearch.getPageProps()+`&page=${departmentPageSearch.page}`)
	
	//initialize table controls
	let departmentTableControls = new TableControls(createUpdateTable,departmentPageSearch);
	const pagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	
	departmentTableControls.pagination = pagination;
	departmentTableControls.selectPageSize = selectPageSize;
	
	//add table controls
	departmentTableControls.addPagination();
	departmentTableControls.addPageSizeControls();
	
	//initialize search
	const selectSearchBy = document.getElementById("selectSearchBy");
	const inputSearch = document.getElementById("inputSearch");
	const btnSearch = document.getElementById("btnSearch");
	
	let departmentSearchControls = new SearchControls(selectSearchBy, inputSearch,btnSearch,departmentTableControls);
	departmentSearchControls.addSearchControls();
	
	//inititalize sorter
	let departmentSorter = new Sorter(departmentTableControls,0,3);
	departmentSorter.addSortIcons();
	
	const th = document.getElementsByTagName("th");
	
	//add sorters
	th[0].addEventListener("click",function(){
		departmentSorter.sortTable("id");
		departmentSorter.setSortedColumn(0);
	});
	
	th[1].addEventListener("click",function(){
		departmentSorter.sortTable("departmentName");
		departmentSorter.setSortedColumn(1);
	});
	
	th[2].addEventListener("click",function(){
		departmentSorter.sortTable("departmentHead");
		departmentSorter.setSortedColumn(2);
	});
});