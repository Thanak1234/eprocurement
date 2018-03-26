document.addEventListener("DOMContentLoaded",function(){

    const btnGeneratePdf = document.getElementsByClassName("btn-generate-pdf");
    for (let index = 0; index < btnGeneratePdf.length; index++) {
        btnGeneratePdf[index].addEventListener("click",function(e){
            if(!confirm("Generate Pdf?")){
                e.preventDefault();
            }
        });
    }

    const btnGenerateXls = document.getElementsByClassName("btn-generate-xls");
    for (let index = 0; index < btnGenerateXls.length; index++) {
        btnGenerateXls[index].addEventListener("click",function (e) {
           if(!confirm("Generate Xls?")){
               e.preventDefault();
           } 
        });
    }

});