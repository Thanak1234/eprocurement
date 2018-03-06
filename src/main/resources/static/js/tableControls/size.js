"use strict";

export default function addPageSizeImpl(page,updateTable,updatePagination,select,pagination){
	select.addEventListener("change",function(){
		page.size = this.value;
		updatePagination(page,updateTable,pagination);
		updateTable(page.url,page.getPageProps());
	});
}

