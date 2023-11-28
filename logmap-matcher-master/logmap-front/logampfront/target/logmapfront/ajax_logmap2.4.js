// Have a function run after the page loads:
window.onload = init;

// Function that adds the Ajax layer:
function init() {



  // Get an XMLHttpRequest object (ajax to init the combos for IRIs)
  var ajax3 = getXMLHttpRequestObject();

  ajax3.open("POST", "library.do", true);
  ajax3.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		

  // Function that handles the response:
  ajax3.onreadystatechange = function() {
  // Pass it this request object:
	  handleResponseLibrary(ajax3);
  } 
  ajax3.send(null);




  // Get an XMLHttpRequest object:
  var ajax = getXMLHttpRequestObject();
  
  if (ajax) {
    // Check for DOM support:
    if (document.getElementById('results')) {
  
      // Add an onclick event handler to the form:
      document.getElementById('button_action').onclick=buttonMatchingAction(ajax);
      
    } // End of DOM check.
    
   }// End of ajax IF.

   // Get an XMLHttpRequest object for the uploading of files:
  var ajax2 = getXMLHttpRequestObject();


  if (ajax2){
      // Add an onclick event handler to the form:
      document.getElementById('upload_onto1_button').onclick=buttonUploadOntoAction(ajax2, 'upload_onto1_button', 'local_onto1', 'out_upload1', 'uploaded_ontology1');
      document.getElementById('upload_onto2_button').onclick=buttonUploadOntoAction(ajax2, 'upload_onto2_button', 'local_onto2', 'out_upload2', 'uploaded_ontology2');	
  }

	//Diable button if...
	document.getElementById('button_action').disabled = true;
	document.getElementById('uri_onto1').onmouseout = disableButton();
	document.getElementById('uri_onto2').onmouseout = disableButton();
	document.getElementById('local_onto1').onmouseout = disableButton();
	document.getElementById('local_onto2').onmouseout = disableButton();

	document.getElementById('uri_onto1').onchange = disableButton();
	document.getElementById('uri_onto2').onchange = disableButton();
	document.getElementById('local_onto1').onchange = disableButton();
	document.getElementById('local_onto2').onchange = disableButton();

	document.getElementById('uploaded_ontology1').onchange = disableButton();
	document.getElementById('uploaded_ontology2').onchange = disableButton();

	document.getElementById('upload_onto1_button').onmouseout = disableButton();
	document.getElementById('upload_onto2_button').onmouseout = disableButton();
	
	document.getElementById('upload_onto1_button').onmouseover = disableButton();
	document.getElementById('upload_onto2_button').onmouseover = disableButton();

	document.getElementById('uploaded_ontology1').onmouseover = disableButton();
	document.getElementById('uploaded_ontology2').onmouseover = disableButton();

	document.getElementById('logmap_params').onmouseover = disableButton();
	

	document.getElementById('name').onmouseout = disableButton();
	document.getElementById('name').onchange = disableButton();
	
	document.getElementById('org').onmouseout = disableButton();
	document.getElementById('org').onchange = disableButton();

	document.getElementById('email').onmouseout = disableButton();
	document.getElementById('email').onchange = disableButton();

	document.getElementById('webiri1').onchange = disableButton();
	document.getElementById('localiri1').onchange = disableButton();
	document.getElementById('webiri2').onchange = disableButton();
	document.getElementById('localiri2').onchange = disableButton();
	
	document.getElementById("hermit").checked=false;
	document.getElementById("elk").checked=false;
	//document.getElementById("more").checked=false;	


	document.getElementById('hermit').onchange = select_hermit();
	document.getElementById('elk').onchange = select_elk();
	//document.getElementById('more').onchange = select_more();

	//document.getElementById('name').value='Ernesto';
	//document.getElementById('org').value='Univ oxford';
	//document.getElementById('prj').value='LogMap';
	//document.getElementById('email').value='ernesto.jimenez.ruiz@gmail.com';
	

	// Add an onclick event handler to the form:
	document.getElementById('webiri1').onclick = function() {

      		
		document.getElementById('local_onto1').disabled=true; 		
		
		document.getElementById('upload_onto1_button').disabled=true; 		
		
		document.getElementById('uri_onto1').disabled=false; 

		document.getElementById('ontos_lib1').disabled=false; 
		


	} // 

	document.getElementById('localiri1').onclick = function() {

      		
		document.getElementById('local_onto1').disabled=false; 
		
		document.getElementById('upload_onto1_button').disabled=false; 
		
		document.getElementById('uri_onto1').disabled=true; 	

		document.getElementById('ontos_lib1').disabled=true; 	

	} // 

	// Add an onclick event handler to the form:
	document.getElementById('webiri2').onclick = function() {

      		
		document.getElementById('local_onto2').disabled=true; 

		document.getElementById('upload_onto2_button').disabled=true; 
		
		document.getElementById('uri_onto2').disabled=false; 

		document.getElementById('ontos_lib2').disabled=false; 
		


	} // 

	document.getElementById('localiri2').onclick = function() {

      		document.getElementById('local_onto2').disabled=false; 
		
		document.getElementById('upload_onto2_button').disabled=false; 

		document.getElementById('uri_onto2').disabled=true; 

		document.getElementById('ontos_lib2').disabled=true; 


	} // 



} // End of init() functi



//-------------------------
//Action of the button
//-------------------------
function buttonMatchingAction(ajax) {

    //The action requires to return a function
    return function() {
	
	//var reasoner = document.getElementById('hermit').checked;
	//alert(reasoner);

	if (areValidInput()){

		var webiri1 = document.getElementById('webiri1').checked;
		//var localiri1 = document.getElementById('localiri1').checked;

		var webiri2 = document.getElementById('webiri2').checked;
		//var localiri2 = document.getElementById('localiri2').checked;

		var uri1="";
		var uri2="";
		if(webiri1){
		    //var uri_onto1 = document.getElementById('uri_onto1').value;	    	    
		    uri1=document.getElementById('uri_onto1').value;
		}
		else{
	 	    //var local_onto1 = document.getElementById('uploaded_ontology1').value;
		    uri1=document.getElementById('uploaded_ontology1').value;
		}
		
		if(webiri2){
		    //var uri_onto2 = document.getElementById('uri_onto2').value;
		    uri2=document.getElementById('uri_onto2').value;	
		}
		else{
		    //var local_onto2 = document.getElementById('uploaded_ontology2').value;
		    uri2=document.getElementById('uploaded_ontology2').value;
		}
		


		var nombre = document.getElementById('name').value;
		var org = document.getElementById('org').value;
		//var prj = document.getElementById('prj').value;
		var prj = "LogMap";
		var email = document.getElementById('email').value;

		//var logmap_version = document.getElementsByName('logmap').value;
		var logmap_version=getRadioValue('logmap');

		var reasoner = document.getElementById('hermit').checked;
		var elk = document.getElementById('elk').checked;
		//var more = document.getElementById('more').checked;
		var more = false;
		
		var store = document.getElementById('store').checked;



		//Disable button to avoid futher clicks
		document.getElementById('button_action').disabled = true;

		// Open the connection:
		
		//ajax.open('get','test.php');
		ajax.open("POST", "logmap.do", true);
		ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		

		// Send the request:		
		/*if (webiri){
			ajax.send("action=web&uri1="+uri_onto1+"&uri2="+uri_onto2+"&logmap="+logmap_version+"&name="+nombre+"&org="+org+"&email="+email+"&prj="+prj);
		}
		else{ //local 
			ajax.send("action=local&uri1="+local_onto1+"&uri2="+local_onto2+"&logmap="+logmap_version+"&name="+nombre+"&org="+org+"&email="+email+"&prj="+prj);			
		}*/
		
		ajax.send("uri1="+uri1+"&uri2="+uri2+"&logmap="+logmap_version+"&name="+nombre+"&org="+org+"&email="+email+"&prj="+prj+"&reasoner="+reasoner+"&elk="+elk+"&more="+more+"&store="+store);

		// Function that handles the response:
		ajax.onreadystatechange = function() {
		  // Pass it this request object:
		  handleResponse(ajax);
		} 

		//ajax.send(null);

		return false; // So form isn't submitted.
	}

    } // End of anonymous function.

}


//-------------------------
//Action UPLOAD ONTO
//-------------------------
function buttonUploadOntoAction(ajax2, button_upload, input_file_field, output_upload, uploaded_uri) {

    //The action requires to return a function
    return function() {
	

	var fileInput = document.getElementById(input_file_field); //'local_onto1'

	if (fileInput.files.length==0){
		alert('Please select a file first');
		return false;
	}
	
	
	var file = fileInput.files[0];
	//var foo = file.fileName;

	
	//alert(file.fileName + '  ' + file.size + 'bytes  type: ' + file.type);


	 var filename = file.fileName;

         if ((typeof filename)!='string'){
        	filename = file.name.toString();
         }
        //alert("'" + filename + "'");
        


	//Disable button to avoid futher clicks
	document.getElementById(button_upload).disabled = true; //'upload_onto1_button'
	
	//Update process...
	document.getElementById(output_upload).innerHTML = 'Uploading... please be patient';


	// Open the connection:	
	ajax2.open("POST", "upload.do", true); // method, url, async
	ajax2.setRequestHeader("Content-Type", "multipart/form-data");
	ajax2.setRequestHeader("Cache-Control", "no-cache");
	ajax2.setRequestHeader("X-File-Type", file.type);	
	ajax2.setRequestHeader("X-File-Name", filename);
	ajax2.setRequestHeader("X-File-Size", file.fileSize);	
	ajax2.send(file);

		

	// Function that handles the response:
	ajax2.onreadystatechange = function() {	
	  handleResponseUpload(ajax2, button_upload, output_upload, uploaded_uri); //Send done!
	} 



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
	
    //Default
    return 'LogMap with reasoning';
}




function updateURI1(selected_onto) {
	document.getElementById('uri_onto1').value = selected_onto;
	//document.getElementById('ontos_lib1').value ="";

}

function updateURI2(selected_onto) {
	document.getElementById('uri_onto2').value = selected_onto;
}





//-------------------------
//Checks if the email address and URIS are valid
//-------------------------
function areValidInput(){
	
	var email = document.getElementById('email').value;
	var atpos=email.indexOf("@");
	var dotpos=email.lastIndexOf(".");

	
	var uri_onto1 = document.getElementById('uri_onto1').value;
	var uri_onto2 = document.getElementById('uri_onto2').value;

	//var webiri = document.getElementById('webiri').checked;
	//var localiri = document.getElementById('localiri').checked;

	var webiri1 = document.getElementById('webiri1').checked;
	var webiri2 = document.getElementById('webiri2').checked;


	var http_pos1 = uri_onto1.indexOf("http://");
	var http_pos2 = uri_onto2.indexOf("http://"); 
	var ftp_pos1 = uri_onto1.indexOf("ftp://");
	var ftp_pos2 = uri_onto2.indexOf("ftp://"); 
	
	

	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
	  alert("Not a valid e-mail address");
	  return false;
  	}
	else if (webiri1 && http_pos1<0 && ftp_pos1<0) {
	  alert("Not a valid input ontology URI 1. Must start with 'http://' or 'ftp://'");
	  return false;
  	}
	else if (webiri2 && http_pos2<0 && ftp_pos2<0) {
	  alert("Not a valid input ontology URI 2. Must start with 'http://' or 'ftp://'");
	  return false;
  	}
	else {	
	  return true;
	}
	
	
	//iF UPLOADED ONTOS THEN WE CREATE THE uri AND IS SUPPOSE TO BE ALWAYS GOOD
	
}

//-------------------------
//ONLY ENABLE BUTTON IF THE FILDS CONTAIN DATA
//-------------------------
function disableButton(){
	//Must return a function
	return function () {

		/*var webiri = document.getElementById('webiri').checked;
		var localiri = document.getElementById('localiri').checked;

		var uri_onto1 = document.getElementById('uri_onto1').value;
		var uri_onto2 = document.getElementById('uri_onto2').value;
		var uploaded_ontology1 = document.getElementById('uploaded_ontology1').value;
		var uploaded_ontology2 = document.getElementById('uploaded_ontology2').value;*/


		var webiri1 = document.getElementById('webiri1').checked;
		var webiri2 = document.getElementById('webiri2').checked;

		var uri1="";
		var uri2="";
		if(webiri1){		   
		    uri1=document.getElementById('uri_onto1').value;
		}
		else{
		    uri1=document.getElementById('uploaded_ontology1').value;
		}
		
		if(webiri2){
		    uri2=document.getElementById('uri_onto2').value;	
		}
		else{
		    uri2=document.getElementById('uploaded_ontology2').value;
		}
		

		var nombre = document.getElementById('name').value;
		var org = document.getElementById('org').value;
		var email = document.getElementById('email').value;

		if ( uri1 !="" && uri2 !="" && nombre !="" && org !="" && email !="")
			document.getElementById('button_action').disabled = false;
		else 
			document.getElementById('button_action').disabled = true;
	
	//	document.getElementById('button_action').disabled = false;
	
	}

}


/** To change selected reasoner */
function select_hermit() {

	//Must return a function
	return function () {

		if (document.getElementById("hermit").checked){
			document.getElementById("elk").checked=false;
			document.getElementById("more").checked=false;
		}

	}

}

function select_elk() {

	//Must return a function
	return function () {
	
		if (document.getElementById("elk").checked){
			document.getElementById("hermit").checked=false;
			document.getElementById("more").checked=false;
		}
		
	}

}

function select_more() {

        //Must return a function
        return function () {
                if (document.getElementById("more").checked){
                	document.getElementById("hermit").checked=false;
			document.getElementById("elk").checked=false;
                }
         }
}





function errorMessage(){
   
   return "<fieldset><p><FONT COLOR=\"red\">There was an error.</FONT> The server might be down. Please contact 'ernesto@cs.ox.ac.uk'.</p></fieldset>" 	

}


function errorMessageUpload(){
   
   return "<p><FONT COLOR=\"red\">There was an error uploading the file.</FONT> Please contact 'ernesto@cs.ox.ac.uk'.</p>" 	

}




//-------------------------
// Function that handles the response from JAVA and displays the output
//-------------------------
function handleResponse(ajax) {

  // Check that the transaction is complete:
  if (ajax.readyState == 4) {

	document.getElementById('form').style.visibility='hidden'; // hide  
	//document.getElementById('button_action').style.visibility='visible'; // show  

	var results = document.getElementById('results');

	//Deletes elements in ID	
	while (results.hasChildNodes()) {
	     	results.removeChild(results.lastChild);
    	}

    // Check for a valid HTTP status code:
    if ((ajax.status == 200) || (ajax.status == 304) ) {


	//Adds results form PHP to HTMLS
   	results.innerHTML = ajax.responseText;
      // Reset all the labels:
      // Make the results box visible:
    results.style.display = 'block';


    } else { // Bad status code, submit the form.
	//do nothing    
	results.innerHTML = errorMessage();
	}	
    
  } // End of readyState IF.
  
} // End of handleResponse() function.



//-------------------------
// Function that handles the response from JAVA and displays the output
//-------------------------
function handleResponseUpload(ajax2, button_upload, output_upload, uploaded_uri) {

  // Check that the transaction is complete:
  if (ajax2.readyState == 4) {
	
	//enable button
	document.getElementById(button_upload).disabled = false;

	var results = document.getElementById(output_upload);

	//Deletes elements in ID	
	//document.getElementById('form').style.visibility='hidden'; // hide  
	//while (results.hasChildNodes()) {
	//     	results.removeChild(results.lastChild);
    	//}

    // Check for a valid HTTP status code:
    if ((ajax2.status == 200) || (ajax2.status == 304) ) {


	//Adds results form PHP to HTMLS
   	results.innerHTML = ajax2.responseText.split("\|")[0];

	document.getElementById(uploaded_uri).value = ajax2.responseText.split("\|")[1];
	
	// Reset all the labels:
      	// Make the results box visible:
    	results.style.display = 'block';
	
	disableButton();	


    } else { // Bad status code, submit the form.
	//do nothing    
	results.innerHTML = errorMessageUpload();
	}	
    
  } // End of readyState IF.
  
} // End of handleResponse() function.



//-------------------------
// Function that handles the query to the ontology library
//-------------------------
function handleResponseLibrary(ajax) {

  // Check that the transaction is complete:
  if (ajax.readyState == 4) {

	var results1 = document.getElementById('ontos_lib1');
	var results2 = document.getElementById('ontos_lib2');


    // Check for a valid HTTP status code:
    if ((ajax.status == 200) || (ajax.status == 304) ) {

   	results1.innerHTML = ajax.responseText;
    	results1.style.display = 'block';
	
   	results2.innerHTML = ajax.responseText;
    	results2.style.display = 'block';


    } else { // Bad status code, submit the form.
	//do nothing    
	}	
    
  } // End of readyState IF.
  
} // End of handleResponse() function.


