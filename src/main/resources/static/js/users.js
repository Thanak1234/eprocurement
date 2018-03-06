"use strict";

import userTable from "./customTableControls/usersTable.js";
import Page from "./tableControls/page.js";
import TableControls from "./tableControls/tableControls.js";
import Sorter from "./tableControls/sorter.js";

document.addEventListener("DOMContentLoaded",function(){

	let usersPage = new Page("/api/users");
	
	userTable(usersPage.url,usersPage.getPageProps()+`&page=${usersPage.page}`);
	//init table controls
	let userTableControls = new TableControls(userTable, usersPage);
	
	const pagination = document.getElementById("pagination");
	const selectPageSize = document.getElementById("selectPageSize");
	
	userTableControls.pagination = pagination;
	userTableControls.selectPageSize = selectPageSize;
	
	userTableControls.addPagination();
	userTableControls.addPageSizeControls();
	
	let userSorter = new Sorter(userTableControls, 0 ,5);
	userSorter.addSortIcons();
	
	const th = document.getElementsByTagName("th");
	
	th[0].addEventListener("click",function(){
		userSorter.sortTable("id");
		userSorter.setSortedColumn(0);
	});
	
	th[1].addEventListener("click",function(){
		userSorter.sortTable("username");
		userSorter.setSortedColumn(1);
	});
	
	th[2].addEventListener("click",function(){
		userSorter.sortTable("firstName");
		userSorter.setSortedColumn(2);
	});
	
	th[3].addEventListener("click",function(){
		userSorter.sortTable("lastName");
		userSorter.setSortedColumn(3);
	});
	
	th[4].addEventListener("click",function(){
		userSorter.sortTable("role");
		userSorter.setSortedColumn(4);
	});
	
});

