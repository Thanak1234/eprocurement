"use strict";

export default class Page{
	constructor(url){
		this.url = url;
		this.page = 0;
		this.size = 20;
		this.sort = "";
	}
	
	getPageProps(){
		return `?size=${this.size}&sort=${this.sort}`;
	}
}

