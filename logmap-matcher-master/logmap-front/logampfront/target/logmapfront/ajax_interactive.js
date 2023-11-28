// Have a function run after the page loads:
window.onload = init;
// Function that adds the Ajax layer:
function init() {

	
  // Get an XMLHttpRequest object:
  var ajax4 = getXMLHttpRequestObject();
  
  if (ajax4) {
    // Check for DOM support:
    if (document.getElementById('mappings')) {  //Div for outputs

	//Note that when we will remove the dic we will also remove the buttons
  
      // Add an onclick event handler to the form:
      document.getElementById('send_feedback').onclick=buttonSendFeedbackAction(ajax4); //buttons 
      document.getElementById('end_interaction').onclick=buttonEndInteractionAction(ajax4); //buttons 
      
    } // End of DOM check.
    
   }// End of ajax IF.

} // End of init() function




//-------------------------
//Action of the button send feedback
//-------------------------
function buttonSendFeedbackAction(ajax4) {

    //The action requires to return a function
    return function() {
	
	

	//relativepath	
	var folder = document.getElementById('relativepath').innerHTML;

	//numcurrentmappings
	var max_num = Number(document.getElementById('numcurrentmappings').innerHTML);

	//Onto1
	var ontouri1 = document.getElementById('onto1').innerHTML;
	//Onto2
	var ontouri2 = document.getElementById('onto2').innerHTML;	

	//alert(folder);
	//alert(max_num);	
	
	//Get combo values and check box
	//mapping + num

	//Checkboxes "amb = num"
	
	//var userFeedback=new Array(); 

	var feedback = ""; //will contain the action (del/add) and the mapping id	
	
	for (num = 0; num < max_num; num++) {
		
		var mapping_id = 'mapping' + num.toString(); 
		var amb_id = 'amb' + num.toString(); 	

		feedback = feedback + getRadioValue(mapping_id).toString() + "_" + document.getElementById(amb_id).checked.toString() + ";";
	}

	//alert(feedback);

	
	// Open the connection:
		
	
	ajax4.open("POST", "../../interactivity.do", true);
	ajax4.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		
		
	//ajax4.send("folder="+folder+"&feedback="+feedback);
	ajax4.send("folder="+folder+"&feedback="+feedback+"&ontouri1="+ontouri1+"&ontouri2="+ontouri2);

	// Function that handles the response:
	ajax4.onreadystatechange = function() {
	   // Pass it this request object:
	   handleResponse(ajax4);
	} 

	//ajax4.send(null);
	
	
		
	return false; // So form isn't submitted.

    } // End of anonymous function.

}


//-------------------------
//Action of the button end interaction
//-------------------------
function buttonEndInteractionAction(ajax4) {

    //The action requires to return a function
    return function() {
	
	//relativepath	
	var folder = document.getElementById('relativepath').innerHTML;

	
	var feedback = "end"; //end oif interaction
	
	//Onto1
	var ontouri1 = document.getElementById('onto1').innerHTML;
	//Onto2
	var ontouri2 = document.getElementById('onto2').innerHTML;

	//alert(feedback);

	
	// Open the connection:
		
	ajax4.open("POST", "../../interactivity.do", true);
	ajax4.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		
		
	//ajax4.send("folder="+folder+"&feedback="+feedback);
	ajax4.send("folder="+folder+"&feedback="+feedback+"&ontouri1="+ontouri1+"&ontouri2="+ontouri2);

	// Function that handles the response:
	ajax4.onreadystatechange = function() {
	   // Pass it this request object:
	   handleResponse(ajax4);
	} 

	//ajax4.send(null);
	
	
		
	return false; // So form isn't submitted.

    } // End of anonymous function.

}







function getRadioValue(name) {
    var group = document.getElementsByName(name);

    for (var i=0;i<group.length;i++) {
        if (group[i].checked) {
            return group[i].value;
        }
    }
}




//-------------------------
// Function that handles the response from JAVA and displays the output
//-------------------------
function handleResponse(ajax4) {

  // Check that the transaction is complete:
  if (ajax4.readyState == 4) {

	var results = document.getElementById('mappings');

	//Deletes elements in ID	
	while (results.hasChildNodes()) {
	     	results.removeChild(results.lastChild);
    	}

    // Check for a valid HTTP status code:
    if ((ajax4.status == 200) || (ajax4.status == 304) ) {


	//Adds results form PHP to HTMLS
   	results.innerHTML = ajax4.responseText;
      // Reset all the labels:
      // Make the results box visible:
    results.style.display = 'block';

	if (results.innerHTML.indexOf("<b>The interactivity process has finished.</b>")!=-1){
		//end of process
		document.getElementById('send_feedback').style.display='none';
		document.getElementById('end_interaction').style.display='none';
	
	}
	else{
		document.getElementById('send_feedback').innerHTML='Send feedback';
		document.getElementById('end_interaction').style.display='block';
	}	
	
	window.scrollTo(0,0);


    } else { // Bad status code, submit the form.
	//do nothing
	//To debug    
	//results.innerHTML = ajax4.status + " " +  ajax4.responseText +  " " + errorMessage();
	}	
    
  } // End of readyState IF.
  
} // End of handleResponse() function.




function errorMessage(){
   
   return "<fieldset><p><FONT COLOR=\"red\">There was an error.</FONT> The server might be down. Please contact 'ernesto@cs.ox.ac.uk'.</p></fieldset>" 	

}





function ShowHide(divId)
{
if(document.getElementById(divId).style.display == 'none')
{
document.getElementById(divId).style.display='block';
}
else
{
document.getElementById(divId).style.display = 'none';
}
}



