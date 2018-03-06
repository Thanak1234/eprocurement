"use strict";

import PageSearch from "./tableControls/pageSearch.js";
import createUpdateTable from "./tableControls/updateTable.js";
import TableControls from "./tableControls/tableControls.js";
import SearchControls from "./tableControls/searchControls.js";
import Sorter from "./tableControls/sorter.js";

document.addEventListener("DOMContentLoaded",function(){
	
	let supplierPageSearch = new PageSearch("/api/suppliers");
	
	createUpdateTable(supplierPageSearch.url,supplierPageSearch.getPageProps()+`&page=${supplierPageSearch.page}`);
	
	let supplierTableControls = new TableControls(createUpdateTable,supplierPageSearch);
	
	const pagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	
	supplierTableControls.pagination = pagination;
	supplierTableControls.selectPageSize = selectPageSize;
	
	supplierTableControls.addPagination();
	supplierTableControls.addPageSizeControls();
	
	//inititalize search controls
	const selectSearchBy = document.getElementById("selectSearchBy");
	const inputSearch = document.getElementById("inputSearch");
	const btnSearch = document.getElementById("btnSearch");
	let supplierSearchControls = new SearchControls(selectSearchBy,inputSearch,btnSearch,supplierTableControls);
	supplierSearchControls.addSearchControls();
	
	let supplierSorter = new Sorter(supplierTableControls,0,5);
	supplierSorter.addSortIcons();
	const th = document.getElementsByTagName("th");
	
	th[0].addEventListener("click",function(){
		supplierSorter.sortTable("id");
		supplierSorter.setSortedColumn(0);
	});
	
	th[1].addEventListener("click",function(){
		supplierSorter.sortTable("supplierName");
		supplierSorter.setSortedColumn(1);
	});
	
	th[2].addEventListener("click",function(){
		supplierSorter.sortTable("tin");
		supplierSorter.setSortedColumn(2)
	})
	
	th[3].addEventListener("click",function(){
		supplierSorter.sortTable("address");
		supplierSorter.setSortedColumn(3);
	});
	
	th[4].addEventListener("click",function(){
		supplierSorter.sortTable("contactNumber");
		supplierSorter.setSortedColumn(4);
	});
		
});