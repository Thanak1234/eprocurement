"use strict";

import Page from "./tableControls/page.js";
import prTable from "./customTableControls/purchaseRequestsTable.js";

document.addEventListener("DOMContentLoaded",function(){
	
	class PrPage extends Page{
		constructor(url){
			super(url);
		}
		
		getPageProps(){
			return super.getPageProps()+`&startDate=${this.startDate}&endDate=${this.endDate}`;
		}
	}
	
	
	//input start date
	const inputStartDate = document.getElementById("inputStartDate");
	let startDate = new Date();
	startDate.setDate(startDate.getDate()-30);
	inputStartDate.valueAsDate = startDate;
	
	//end date
	const inputEndDate = document.getElementById("inputEndDate");
	let endDate = new Date();
	inputEndDate.valueAsDate = endDate;
	
	const department = document.getElementById("department").value;
	let prPage = new PrPage(`/api/pr/${department}`);
	prPage.startDate = inputStartDate.value;
	prPage.endDate = inputStartDate.value;
	
	console.log(prPage.getPageProps());
	prTable(prPage.url, prPage.getPageProps()+`&page=${prPage.page}`);
	
	
});