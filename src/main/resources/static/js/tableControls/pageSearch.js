import Page from "./page.js";

export default class PageSearch extends Page{
	constructor(url){
		super(url);
		this.searchBy = "";
		this.searchValue = "";
	}
	
	getPageProps(){
		return super.getPageProps()+`&searchBy=${this.searchBy}&searchValue=${this.searchValue}`;
	}	
}
