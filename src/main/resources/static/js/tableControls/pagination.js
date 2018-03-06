"use strict";

import {loadData} from "./ajax.js";

//callback function to create table controls
function renderPagination(data,page,updateTable,pagination){
	
	//Clear page numbers
	while(pagination.firstChild){
		pagination.removeChild(pagination.firstChild);
	}
	
	if(data["totalPages"] > 1){
	let liFirst = document.createElement("li");
	let linkFirst = document.createElement("a");
	liFirst.appendChild(linkFirst);
	linkFirst.text = "First";
	linkFirst.href="#";
	linkFirst.addEventListener("click",function(){
		if(liFirst.className !== "disabled"){
		page.page = 0;
		updateTable(page.url,page.getPageProps()+`&page=${page.page}`);
		addPaginationImpl(page,updateTable,pagination);	
		}
	})
	
	//Create previous 
	let liPrev = document.createElement("li");
	let linkPrev = document.createElement("a");
	linkPrev.href="#";
	linkPrev.text = "Previous";
	liPrev.appendChild(linkPrev);
	
	//enable/ disable first and previous button
	if(data["first"]){
		liPrev.className = "disabled";
		liFirst.className = "disabled";
	}
	
	linkPrev.addEventListener("click",function(){
		if(liPrev.className !== "disabled"){
			page.page -= 1;
			updateTable(page.url,page.getPageProps()+`&page=${page.page}`);
			addPaginationImpl(page,updateTable,pagination);			
		}
	});	
	
	let liLast = document.createElement("li");
	let linkLast = document.createElement("a");
	linkLast.href="#";
	liLast.appendChild(linkLast);
	linkLast.text = "Last";
	linkLast.addEventListener("click",function(){
		if(liLast.className != "disabled"){
			page.page = data["totalPages"]-1;
			updateTable(page.url,page.getPageProps()+`&page=${page.page}`);
			addPaginationImpl(page,updateTable,pagination);		
		}
	});
	
	//Create next 
	let liNext = document.createElement("li");
	let linkNext = document.createElement("a");
	linkNext.textContent ="Next";
	linkNext.href="#";
	liNext.appendChild(linkNext);
	
	//enable/disable last and next button
	if(data["last"]){
		liLast.className= "disabled";
		liNext.className = "disabled";
	}
	//add next events
	linkNext.addEventListener("click",function(){
		if(liNext.className !== "disabled"){
			page.page += 1;
			updateTable(page.url,page.getPageProps()+`&page=${page.page}`);
			addPaginationImpl(page,updateTable,pagination);
		}	
	});
	
	let liCurrent = document.createElement("li");
	let spa = document.createElement("label");
	let span = document.createElement("label");
	//input page number
	let pageNum = document.createElement("input");
	pageNum.type="text";
	pageNum.size = 1; 
	pageNum.value = data["number"]+1;
	//add input page number event
	pageNum.addEventListener("input",function(){
		page.page = this.value -1;
		updateTable(page.url,page.getPageProps()+`&page=${page.page}`);
		addPaginationImpl(page,updateTable,pagination);
	})
	spa.textContent = "Page ";
	span.textContent = ` of ${data["totalPages"]}`;
	//assemble page number into 'Page {currentPage} of {totalPages}'
	liCurrent.appendChild(spa);
	liCurrent.appendChild(pageNum);
	liCurrent.appendChild(span);
	
		/*
		 * assemble pagination
		 */
		pagination.appendChild(liFirst);
		pagination.appendChild(liPrev);
		pagination.appendChild(liCurrent);
		pagination.appendChild(liNext);
		pagination.appendChild(liLast);		
	}	
}

//use this function to add pagination
export default function addPaginationImpl(page, updateTable,pagination){
	loadData(page.url+page.getPageProps()+`&page=${page.page}`,function(data){
		renderPagination(data,page,updateTable,pagination);
	});
}


