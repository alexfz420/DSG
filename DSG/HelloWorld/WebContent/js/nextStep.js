 function show() {
        
	    document.getElementById('secondStep').style.display='block';
	    document.getElementById('firstStep').style.display='none';
	    document.getElementById('buttons').style.display='none';
}
  
  function close() {
	  document.getElementById('secondStep').style.display='none';
	  document.getElementById('firstStep').style.display='block';
      document.getElementById('buttons').style.display='block';    
      


      
  } 

  function pageOnLoad() {
	  close();
  }
 
 function SubmitForm()
 {
	 console.log("start");
	 var rulename = '${rulename.replace("%20"," ")}';
	 console.log("searching"+rulename);
	 var table = document.getElementById("sort");
	 for (var i = 0, row; row = table.rows[i]; i++){
	    //iterate through rows
	    //rows would be accessed using the "row" variable assigned in the for loop
	    for (var j = 0, col; col = row.cells[j]; j++) {
	      if (col.innerText == rulename){

	    	  document.getElementById('priorityId').value= i;

	      }
	    	//iterate through columns
	     //columns would be accessed using the "col" variable assigned in the for loop
	    }  
	 }
   document.forms['myForm'].submit() ;
 }
 window.onload =pageOnLoad;
 

function goBack(){
	window.history.go(-1);
}