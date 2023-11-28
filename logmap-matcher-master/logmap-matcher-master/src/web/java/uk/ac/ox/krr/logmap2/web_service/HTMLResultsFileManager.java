/*******************************************************************************
 * Copyright 2012 by the Department of Computer Science (University of Oxford)
 * 
 *    This file is part of LogMap.
 * 
 *    LogMap is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 * 
 *    LogMap is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 * 
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with LogMap.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package uk.ac.ox.krr.logmap2.web_service;

import java.io.File;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;


import uk.ac.ox.krr.logmap2.io.ReadFile;
import uk.ac.ox.krr.logmap2.io.WriteFile;



/**
 * This class will manage the creation and update of the output file
 * @author Ernesto
 *
 */
public class HTMLResultsFileManager {

	private String file_output;
	
	private String uri_output;
	
	private WriteFile writer;
	
	private String date4folder;
	private String date2show;
	private String base_path;
	private String base_uri;
	
	private String day;
	private String month;
	private String year;
	private String hour;
	private String minute;
	private String second;
	private String milisecond;
	
	private String name;
	private String version;
	
	private String relative_output_path;
	
	private String uri1;
	private String uri2;
	
	//private String integratedOntologyIRIStr="";
	//private String integratedOntologyModulesIRIStr="";
	
	private String output_folder;
	private String uri_folder;
	
	
	private int num_current_mappings = 0;
	
	
	//From LogMapRequest thread
	public HTMLResultsFileManager duplicate() {
		return new HTMLResultsFileManager(
				this.file_output, this.uri_output, this.relative_output_path, this.output_folder, this.uri_folder, this.uri1, this.uri2);
	}
	
	
	//From EndInteractivity thread (InteractiveProcessRequest)
	public HTMLResultsFileManager duplicate2() {
		return new HTMLResultsFileManager(this.output_folder, this.uri_folder, this.uri1, this.uri2);
	}
	
	
	
	//Reload manager 4 interactivity
	public HTMLResultsFileManager(
				String output_folder,
				String uri_folder,
				String uri1,
				String uri2){
			
			
			this.output_folder=output_folder;//
			this.uri_folder=uri_folder;//
			
			//We also keep original uris in case modules cannot be loaded
			this.uri1 = uri1;
			this.uri2 = uri2;
			
			file_output = this.output_folder + "/index.html";
			uri_output = this.uri_folder + "/index.html";
			
		}
	
	
	
	//To clone class
	public HTMLResultsFileManager(
			String T_file_output,
			String T_uri_output,
			String T_relative_output_path,
			String output_folder,
			String uri_folder,
			String uri1,
			String uri2){
		
		
		this.file_output=T_file_output;//
		this.uri_output=T_uri_output;//
		this.relative_output_path=T_relative_output_path;//
		this.output_folder = output_folder;
		this.uri_folder = uri_folder;
		
		this.uri1 = uri1;
		this.uri2 = uri2;
		
		
	}
	
	
	
	
	
	public HTMLResultsFileManager(String base_path, String base_uri, String name, String version, String uri1, String uri2){
		System.out.println("HTMLResultsFileManager");
		
		day = DateManager.getCurrentDay();
		month = DateManager.getCurrentMonth();
		year = DateManager.getCurrentYear();
		
		hour = DateManager.getCurrentHour();
		minute = DateManager.getCurrentMinute();
		second = DateManager.getCurrentSecond();
		milisecond = DateManager.getCurrentMilisecond();
		
		this.name=name;
		this.version=version;
		
		this.uri1=uri1;
		this.uri2=uri2;
		
		this.date4folder = day + "_" + month + "_" + year  + "__" + hour  + "_" + minute  + "_" + second  + "_" + milisecond;
		this.date2show = day + "/" + month + "/" + year;
		this.base_path =  base_path;
		this.base_uri=base_uri;
		
		createHTMLFile();
	}

	
	
	
	
	public String getHTMLResultsFile(){
		return file_output;
	}
	
	public String getHTMLResultsURI(){
		return uri_output;
	}
	
	
	public String getRelativeOutputPath(){
		return relative_output_path;
	}
	
	
	
	
	/*public void setIntegratedOntologyIRIStr(String IRIStr){
		integratedOntologyIRIStr=IRIStr;
	}
	
	public void setIntegratedOntologyModulesIRIStr(String IRIStr){
		integratedOntologyModulesIRIStr=IRIStr;
	}*/
	
	
	
	public String getOutputFolder(){
		return output_folder;
	}
	
	public String getURIFolder(){
		return uri_folder;
	}
	
	
	public String getURIModule1(){
		return uri_folder + "/module1.owl";
	}
	
	public String getURIModule2(){
		return uri_folder + "/module2.owl";
	}
		
	
	public String getURIMappings(){
		return uri_folder + "/mappings.owl";
	}
	
	public String getURIMappingsOWL(){
		return uri_folder + "/mappings.owl";
	}
	
	public String getURIMappingsTXT(){
		return uri_folder + "/mappings.txt";
	}
	
	public String getURIMappingsRDF(){
		return uri_folder + "/mappings.rdf";
	}
	
	public String getIntegratedOntologyIRIStr(){
		//return integratedOntologyIRIStr;
		return uri_folder  + "/integratedOntology.owl";
	}
	
	public String getIntegratedOntologyModulesIRIStr(){
		//return integratedOntologyModulesIRIStr;
		return uri_folder  + "/integratedOntologyWithModules.owl";
	}
	
	
	public String getBasePath4Mappings(){ //withou extension
		return output_folder + "/mappings";
	}
	
	public String getPathReliableMappings(){
		return output_folder + "/reliable_mappings.txt";
	}
	
	public String getPathMappings2Review(){  //By Logmap
		return output_folder + "/mappings2review.txt";
	}
	
	
	public String getPathMappings2Assess(){   //By user
		return output_folder + "/session.log";
	}
	
	
	public String getURIOntology1(){
		return uri1;
	}
	
	public String getURIOntology2(){
		return uri2;
	}
	
	
	
	public void setNumCurrentMappings(int num){
		num_current_mappings = num;
	}
	
	
	
	
	private void createHTMLFile(){
		
		relative_output_path = "/matching_" 
				+ date4folder;
		
		
		//Create output folder
		output_folder = base_path + relative_output_path; //to stotr in web
		File f = new File(output_folder);
		f.mkdir();
		
		
		//Create file
		file_output = output_folder + "/index.html";
		
		writer = new WriteFile(file_output);
		
		writeHeadersHTMLFile();
		
		writeBodyHTMLFile();
		
		writeCloseHTMLFile();
		
		
		writer.closeBuffer();
	       
		
		//Link to file
		uri_folder = base_uri + "/output" + relative_output_path;//to store in web
		uri_output = base_uri + "/output" + relative_output_path + "/index.html";
		
	}
	
	
	private void writeHeadersHTMLFile(){
		
		
		writer.writeLine("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		writer.writeLine("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
		writer.writeLine("<head>");
		writer.writeLine("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>");
		writer.writeLine("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>");
		writer.writeLine("<title>LogMap Web Access</title>");
		writer.writeLine("<script src=\"../../ajax_logmap2.4.js\" type=\"text/javascript\"></script>");
		writer.writeLine("<script src=\"../../ajax_http.js\" type=\"text/javascript\"></script>");
		writer.writeLine("<script src=\"../../ajax_interactive.js\" type=\"text/javascript\"></script>");
		writer.writeLine("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../interactivity.css\">");
		writer.writeLine("<link rel=\"shortcut icon\" href=\"../../favicon.ico\" />");
		writer.writeLine("<link rel=\"stylesheet\" href=\"../../css/custom.css\" />");
		writer.writeLine("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../css/bootstrap-5.3.1/bootstrap.min.css\" />");
		writer.writeLine("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css\" />");
		writer.writeLine("</head>");
		
		
		/*
		 * 
		 * writer.writeLine("<html>"); writer.writeLine("<head>");
		 * writer.writeLine("<title>LogMap Web Access</title>"); writer.
		 * writeLine("<script src=\"../../ajax_http.js\" type=\"text/javascript\"></script>"
		 * ); writer.
		 * writeLine("<script src=\"../../ajax_interactive.js\" type=\"text/javascript\"></script>"
		 * ); writer.
		 * writeLine("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../interactivity.css\">"
		 * );
		 * 
		 * 
		 * writer.writeLine("<link rel=\"shortcut icon\" href=\""+ base_uri
		 * +"/favicon.ico\" />"); writer.writeLine("</head>");
		 * 
		 * writer.writeLine("<body>");
		 * 
		 * //writer.writeLine("<a href=\""+base_uri+"\" target=\"blank\"><img src=\""+
		 * base_uri +"/LM_logo.jpg\" height=\"50\" border=\"0\"></a>"); writer.
		 * writeLine("<a href=\"http://www.cs.ox.ac.uk/isg/tools/LogMap/\" target=\"blank\"><img src=\""
		 * + base_uri +"/LM_logo.jpg\" height=\"50\" border=\"0\"></a>");
		 * writer.writeLine("<br> <br>");
		 */
	}
	
	private void writeBodyHTMLFile(){
		
		
		writer.writeLine("<body>");
		writer.writeLine("<div class=\"container py-3\">");
		
		writer.writeLine("<div class=\"d-flex flex-column flex-md-row align-items-center pb-3 mb-4 border-bottom\">");
		writer.writeLine("<div><img src=\""+base_uri+"/LM_logo.png\" height=\"50\" border=\"0\"></div>");
		
		writer.writeLine("<nav class=\"d-inline-flex mt-2 mt-md-0 ms-md-auto\">");
		writer.writeLine("<a class=\"me-3 py-2 link-body-emphasis text-decoration-none\" href=\"https://www.cs.ox.ac.uk/isg/tools/LogMap/\" target=\"_blank\" title=\"Website\"><i class=\"bi bi-house-fill fs-4\"></i></a>");
		writer.writeLine("<a class=\"me-3 py-2 link-body-emphasis text-decoration-none\" href=\"https://code.google.com/archive/p/logmap-matcher/\" target=\"_blank\" title=\"Google Code\"><i class=\"bi bi-archive-fill fs-4\"></i></a>");
		writer.writeLine("<a class=\"me-3 py-2 link-body-emphasis text-decoration-none\" href=\"https://github.com/ernestojimenezruiz/logmap-matcher\" target=\"_blank\" title=\"GitHub\"><i class=\"bi bi-github fs-4\"></i></a>");
		writer.writeLine("<a class=\"me-3 py-2 link-body-emphasis text-decoration-none\" href=\"https://twitter.com/LogmapMatcher\" target=\"_blank\" title=\"Twitter\"><i class=\"bi bi-twitter fs-4\"></i></a>");
		writer.writeLine("<a class=\"py-2 link-body-emphasis text-decoration-none\" href=\"https://www.facebook.com/logmapmatcher\" target=\"_blank\" title=\"Facebook\"><i class=\"bi bi-facebook fs-4\"></i></a>");
		writer.writeLine("</nav>");
		writer.writeLine("</div>");
		
		writer.writeLine("<div class=\"pricing-header p-3 pb-md-4 mx-auto text-center\">");
		writer.writeLine("<h1 class=\"display-4 fw-normal text-body-emphasis\">LogMap</h1>");
		writer.writeLine("<p class=\"fs-6 text-body-secondary\">LogMap's web facility (compliant with");
		writer.writeLine("<a href=\"http://code.google.com/p/logmap-matcher/wiki/ReleaseNotes\" target=\"_blank\">version 2.4</a>");
		writer.writeLine(") has been successfully tested <br/>");
		writer.writeLine("with <a href=\"http://www.mozilla.org/firefox/\" target=\"_blank\">Firefox</a>,");
		writer.writeLine("<a href=\"https://www.google.com/chrome\" target=\"_blank\">Chrome</a> and <a href=\"http://www.konqueror.org/\" target=\"_blank\">Konqueror</a>.");
		writer.writeLine("<font color=\"red\">It may fail with iExplorer.</font><br/>");
		writer.writeLine("Please report any issue related to LogMap in our <a href=\"https://groups.google.com/forum/#!forum/logmap-matcher-discussion\" target=\"_blank\">discussion group</a> or in our <a href=\"https://github.com/ernestojimenezruiz/logmap-matcher/issues\" target=\"_blank\">issue tracker</a>.");
		writer.writeLine("</p>");
		writer.writeLine("</div>");
		
		
		writer.writeLine("<div class=\"col\">");
		writer.writeLine("<div class=\"card mb-4 rounded-3 shadow-sm\">");
		writer.writeLine("<div class=\"card-body\">");
		writer.writeLine("<h4 class=\"my-0 fw-normal text-center mb-5\">Progress of the matching task on "+ date2show +"</br>");
		writer.writeLine("By "+ name +" using "+ version);
		writer.writeLine("<span onClick=\"window.location.reload()\"><i class=\"bi bi-arrow-clockwise\"></i></span></h4>");
		
		
		writer.writeLine("<div class=\"row table-responsive\">" );
		writer.writeLine("<table class=\"table w70 table-center mb-5\">");
		writer.writeLine("<tbody>");
		writer.writeLine("<tr>");
		writer.writeLine("<th scope=\"row\">"+ hour + ":" + minute + ":" + second+"</th>");
		writer.writeLine("<td>LogMap is processing the request.");
		
		writer.writeLine("<ul class=\"mb0\">");
		writer.writeLine("<li><b>Ontology 1:</b> " + "<a href=\""+uri1 +"\" target=\"_blank\" id=\"onto1\">" + uri1 +  "</a></li>");
		writer.writeLine("<li><b>Ontology 2:</b> " + "<a href=\""+uri2 +"\" target=\"_blank\" id=\"onto2\">" + uri2 +  "</a></li>");
		writer.writeLine("</ul></td></tr>");
		
		
		/*
		 * writer.
		 * writeLine("<p><input type=\"button\" value=\"Go to request form\" onClick=\"window.location.href='"
		 * + base_uri + "'\"></p>");
		 * 
		 * writer.writeLine("<fieldset>");
		 * writer.writeLine("<p><b>Progress of the matching task on " + date2show +
		 * " by " + name + " using " + version +
		 * ":</b>  <input type=\"button\" value=\"Refresh\" onClick=\"window.location.reload()\"></p>"
		 * ); writer.writeLine("<ul>");
		 * 
		 * 
		 * 
		 * writer.writeLine("<li>" + hour + ":" + minute + ":" + second +
		 * ": LogMap is processing the request." + "</li>"); writer.writeLine("<ul>");
		 * //writer.writeLine("<li><u>Ontology 1</u>: " + uri1 + "</li>");
		 * //writer.writeLine("<li><u>Ontology 2</u>: " + uri2 + "</li>");
		 * writer.writeLine("<li><b>Ontology 1:</b> " + "<a href=\""+uri1
		 * +"\" target=\"_blank\" id=\"onto1\">" + uri1 + "</a></li>");
		 * writer.writeLine("<li><b>Ontology 2:</b> " + "<a href=\""+uri2
		 * +"\" target=\"_blank\" id=\"onto2\">" + uri2 + "</a></li>");
		 * 
		 * writer.writeLine("</ul>");
		 */
	
	}
	
	private void writeCloseHTMLFile(){

		writer.writeLine("</tbody></table></div></div></div></div></div></body></html>");
		
	}
	
	public void updateProgress(String progressInfo){
		
		hour = DateManager.getCurrentHour();
		minute = DateManager.getCurrentMinute();
		second = DateManager.getCurrentSecond();
		
		String newline = "<tr><th scope=\"row\">"+ hour + ":" + minute + ":" + second+"</th>"
						+"<td>"+ progressInfo +"</td></tr>";
		
		
		try{
			//READ FILE
			List<String> file_lines = new ArrayList<String>();
			ReadFile reader = new ReadFile(file_output);
			String line;
			while ((line = reader.readLine()) != null){
				file_lines.add(line);
			}
			reader.closeBuffer();
			
			
			
			//Rewrite file
			//-----
			writer = new WriteFile(file_output);//new one
			
			//Modify
			for (int i=0; i<file_lines.size()-1; i++){
				writer.writeLine(file_lines.get(i));
			}
			
			writer.writeLine(newline);
			
			writer.writeLine(file_lines.get(file_lines.size()-1));//last line
			
			//close file
			writer.closeBuffer();
		}
		catch(Exception e){
			//System.err.println("");
		}
	}
	
	
	public void updateProgressEndInteractivity(String progressInfo){
		
		hour = DateManager.getCurrentHour();
		minute = DateManager.getCurrentMinute();
		second = DateManager.getCurrentSecond();
		
//		String newline = "<li>" + hour + ":" + minute + ":" + second + ": " + progressInfo + "</li>";
		String newline = "<tr><th scope=\"row\">"+ hour + ":" + minute + ":" + second+"</th>"
				+"<td>"+ progressInfo +"</td></tr>";
		
		try{
			//READ FILE
			List<String> file_lines = new ArrayList<String>();
			ReadFile reader = new ReadFile(file_output);
			String line;
			while ((line = reader.readLine()) != null){
				
				if (line.equals("</tbody></table></div>")) //just before interactivity div (<div id="mappings">)
//				if (line.equals("</ul></fieldset><br>")) //just before interactivity div (<div id="mappings">)
					break;
				
				System.out.println("line======="+line);
				
				file_lines.add(line);
			}
			reader.closeBuffer();
			
			
			
			//Rewrite file
			//-----
			writer = new WriteFile(file_output);//new one
			
			//Modify
			for (int i=0; i<file_lines.size(); i++){
				writer.writeLine(file_lines.get(i));
			}
			
			//New info
			writer.writeLine(newline);
			
			
			//Closing
//			writer.writeLine("</ul></fieldset></body></html>");

			writer.writeLine("</tbody></table></div></div></div></div></div></body></html>");
			
			
			//close file
			writer.closeBuffer();
		}
		catch(Exception e){
			//System.err.println("");
		}
	}
	
	
	
	
	////////////////////수정
	public void addOutputMappingsDiv(int num_mappings){

		try{
			//READ FILE
			List<String> file_lines = new ArrayList<String>();
			ReadFile reader = new ReadFile(file_output);
			String line;
			while ((line = reader.readLine()) != null){
				file_lines.add(line);
			}
			reader.closeBuffer();
			
			//Rewrite file
			//-----
			writer = new WriteFile(file_output);//new one
			
			//Modify
			for (int i=0; i<file_lines.size()-1; i++){
				writer.writeLine(file_lines.get(i));
			}
			
			writer.writeLine("</tbody></table></div>");

			
			writer.writeLine("<div class=\"row\" id=\"mappings\">");
			
			
			writer.writeLine("<h4 class=\"my-0 fw-normal text-center mb-5\">A subset of the mappings computed by LogMap requires your feedback</h4>");
	
			
			//HIDDEN DIV 
			writeNumberOfCurrentMappings();
			
			writer.writeLine("</div>");
			
			//HIDDEN DIV: Data about the path and uri of output folder
			writer.writeLine("<div id=\"hiddeninfo2\"  style=\"DISPLAY: none\" >"); 			
			writer.writeLine("<label id=\"relativepath\">" + relative_output_path + "</label>");
			writer.writeLine("</div>");
			
			writer.writeLine("<button type=\"button\" class=\"w-100 btn btn-lg btn-primary\" id=\"start_interaction\">Start interactivity session</button>");
			
			writer.writeLine("<div class=\"d-grid gap-2 d-sm-flex justify-content-sm-center\">");
			writer.writeLine("<button class=\"btn btn-lg btn-primary\" type=\"button\" id=\"send_feedback\" style=\"DISPLAY: none\">Send feedback</button>");
			writer.writeLine("<button class=\"btn btn-lg btn-primary\" type=\"button\" id=\"end_interaction\" style=\"DISPLAY: none\">Finish interaction</button>");
			writer.writeLine("</div>");
			
//			writer.writeLine("<button type=\"button\" class=\"w-100 btn btn-lg btn-primary\" id=\"end_interaction\" >Send feedback</button>");
//			writer.writeLine("<button type=\"button\" class=\"w-100 btn btn-lg btn-primary\" id=\"end_interaction\" >Finish interaction</button>");
//			writer.writeLine("<button type=\"button\" class=\"w-100 btn btn-lg btn-primary\" id=\"end_interaction\" style=\"DISPLAY: none\">Finish interaction</button>");
			
			
			
			/*writer.writeLine("<table>");
			writer.writeLine("<tr>");
			writer.writeLine("<td>");
//			writer.writeLine("<button id=\"send_feedback\">(Re)Start interactivity session</button>");
			writer.writeLine("</td>");
			writer.writeLine("<td>");
			writer.writeLine("<button id=\"end_interaction\" >Finish interaction</button>");
//			writer.writeLine("<button id=\"end_interaction\" style=\"DISPLAY: none\">Finish interaction</button>");
			//writer.writeLine("<button id=\"save_session\">Save session</button>");
			writer.writeLine("</td>");
			writer.writeLine("</tr>");
			writer.writeLine("</table>");*/
			
			
			
			writer.writeLine("</div></div></div></div></body></html>");

			
			//			writer.writeLine("</tbody></table></div></div></div></div></div></body></html>");
			
			//close file
			writer.closeBuffer();
		
		}
		catch(Exception e){
			//System.err.println("");
		}
		
		
	}
	
	
	
	private void writeNumberOfCurrentMappings(){
		
		writer.writeLine("<div id=\"hiddeninfo1\"  style=\"DISPLAY: none\" >"); //this div will be modified!
		writer.writeLine("<label id=\"numcurrentmappings\">" + num_current_mappings + "</label>"); //					
		writer.writeLine("</div>");
	}
	
	
	
	/**
	 * @deprecated
	 * @param showing
	 * @param num_mappinsg2ask
	 */
	private void addHeaderMappings2Ask_Form(int showing, int num_mappinsg2ask){
		
		try{
			//READ FILE
			List<String> file_lines = new ArrayList<String>();
			ReadFile reader = new ReadFile(file_output);
			String line;
			while ((line = reader.readLine()) != null){
				file_lines.add(line);
			}
			reader.closeBuffer();
			
			//Rewrite file
			//-----
			writer = new WriteFile(file_output);//new one
			
			//Modify
			for (int i=0; i<file_lines.size()-1; i++){
				writer.writeLine(file_lines.get(i));
			}
			
			writer.writeLine("</ul></fieldset><br>");
			
			
			writer.writeLine("<div id=\"mappings\">"); //this div will be modified!
			
			writer.writeLine("<fieldset>");
			writer.writeLine("<legend><b>Mappings requiring user feedback</b> (assessing "+ showing + " out of " + num_mappinsg2ask + " remaining mappings)</legend>");
			
			
			
			//writer.writeLine("<p><b>Remaining mappings to assess: " + num_mappinsg2ask + "</b></p>");
			
			
			writer.writeLine("<ol>");
			
			
			
			
			
			
		}
		catch(Exception e){
			//System.err.println("");
		}
			
	}
	
	
	/**
	 * @deprecated
	 */
	private void addTailMappings2Ask_Form(){
		writer.writeLine("</ol>");
				
		writer.writeLine("</fieldset>");
		writer.writeLine("</div>");
		
		//Data about the path and uri of output folder
		//storePathData();
		
		
		
		writer.writeLine("<table>");
		writer.writeLine("<tr>");
		writer.writeLine("<td>");
		writer.writeLine("<button id=\"send_feedback\">Start interactivity</button>");
		//writer.writeLine("<button id=\"end_interaction\">Finish interaction</button>");
		//writer.writeLine("<button id=\"save_session\">Save session</button>");
		writer.writeLine("</td>");
		writer.writeLine("</tr>");
		writer.writeLine("</table>");
		
		
		

		
		writer.writeLine("</body></html>");
		
		//close file
		writer.closeBuffer();
	}
	
	
	/**
	 * @deprecated
	 * @param id_mapping
	 * @param num_mapping
	 * @param uri1
	 * @param uri2
	 * @param label1
	 * @param label2
	 * @param ide1
	 * @param ide2
	 * @param dir
	 * @param scope
	 * @param lex
	 * @param superclasses1
	 * @param superclasses2
	 * @param subclasses1
	 * @param subclasses2
	 * @param synonyms1
	 * @param synonyms2
	 * @param mappingsInConflict
	 * @param mappingsAmbiguous
	 */
	private void addMapping2Form(
			int id_mapping,//identifier mapping (its order in structure)
			int num_mapping, ///the number of mapping in form
			String uri1, 
			String uri2, 
			String label1, 
			String label2,
			int ide1,
			int ide2,
			String dir, 
			double scope,
			double lex,			
			String superclasses1,
			String superclasses2,
			String subclasses1,
			String subclasses2,
			String synonyms1,
			String synonyms2,
			Set<String> mappingsInConflict,
			Set<String> mappingsAmbiguous){
		
		
		//Put in the value
		
		//We access mapping by name (mapping0...mapping10)
		//The the selected value give as if a mapping is selected to be added or deleted (add_21_56)
		String radio_add = "<input type=\"radio\" name=\"mapping" + num_mapping + "\" id=\"add" + num_mapping + "\" value=\"add_" + id_mapping +  "\" checked/>";
		String radio_add_label = "<label for=\"add" + num_mapping + "\"><FONT COLOR=\"green\">Add</FONT></label>";
		
		//String radio_addheur = "<input type=\"radio\" name=\"mapping" + id_mapping + "\" id=\"addheur" + id_mapping + "\" value=\"addheur_" + ide1 + "-" + ide2 +  "\" checked/>";
		//String radio_addheur_label = "<label for=\"addheur" + id_mapping + "\"><FONT COLOR=\"green\">Add (ambiguity criteria)</FONT></label>";
		
		String radio_del = "<input type=\"radio\" name=\"mapping" + num_mapping + "\" id=\"del" + num_mapping + "\" value=\"del_" + id_mapping + "\"/>";
		String radio_del_label = "<label for=\"del" + num_mapping + "\"><FONT COLOR=\"red\">Discard</FONT></label>";
		
		//String radio_delheur = "<input type=\"radio\" name=\"mapping" + id_mapping + "\" id=\"delheur" + id_mapping + "\" value=\"delheur_" + ide1 + "-" + ide2 + "\"/>";
		//String radio_delheur_label = "<label for=\"delheur" + id_mapping + "\"><FONT COLOR=\"red\">Discard (ambiguity criteria)</FONT></label>";
		
		
		String check_amb_criteria = "<input type=\"checkbox\" name=\"amb" + num_mapping + "\" id=\"amb" + num_mapping + "\" value=\"Ambiguity criteria\" />";
        String check_amb_criteria_label = "<label for=\"amb" + num_mapping + "\">Use the ambiguity criteria</label>";
		
		writer.writeLine("<li class=\"mapping\">");
		writer.writeLine("<p>");
		
		writer.writeLine("<b>" + label1 + "   " + dir + "   " + label2 + "</b>" + "<br />" +
				//"semantic sim: <b>" + scope + "</b>,  lexical sim: <b>" + lex + "</b>&nbsp;&nbsp;&nbsp;&nbsp;" +
				"semantic sim: " + scope + "&nbsp;&nbsp;&nbsp;  lexical sim: " + lex + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				
				"&nbsp;&nbsp;&nbsp;&nbsp;" + radio_add + radio_add_label + 
				//"&nbsp;&nbsp;" + radio_addheur + radio_addheur_label + 
				"&nbsp;&nbsp;&nbsp;&nbsp;" + radio_del + radio_del_label + 
				//"&nbsp;&nbsp;" + radio_delheur + radio_delheur_label +
				"&nbsp;&nbsp;&nbsp;&nbsp;" + check_amb_criteria + check_amb_criteria_label);
		
		writer.writeLine("<br /><br />"); 
				
		writer.writeLine(
				"<a onclick =\"javascript:ShowHide('DivURIs" + id_mapping + "')\" href=\"javascript:;\" >Show/Hide full URIs</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"<a onclick =\"javascript:ShowHide('DivSynonyms" + id_mapping + "')\" href=\"javascript:;\" >Show/Hide synonyms</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"<a onclick =\"javascript:ShowHide('DivScope" + id_mapping + "')\" href=\"javascript:;\" >Show/Hide scope</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"<a onclick =\"javascript:ShowHide('DivConflict" + id_mapping + "')\" href=\"javascript:;\" >Show/Hide mappings in conflict</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"<a onclick =\"javascript:ShowHide('DivAmbiguity" + id_mapping + "')\" href=\"javascript:;\" >Show/Hide ambiguous mappings</a>"
				);
		
		writer.writeLine("</p>");
		
		
		//Div IRIS
		//-------------
		writer.writeLine("<div id=\"DivURIs" + id_mapping + "\" style=\"DISPLAY: none\" >");
		writer.writeLine("<fieldset class=\"uris\">");
		writer.writeLine("<legend>Full URIs</legend>");
		//writer.writeLine("<label>Put info about direct superclasses</label>");
		writer.writeLine("<ul>");
		writer.writeLine("<li><b>URI 1</b>: " + uri1 +"</li>");
		writer.writeLine("<li><b>URI 2</b>: " + uri2 +"</li>");
		writer.writeLine("</ul>");
		writer.writeLine("</fieldset>");
		writer.writeLine("<br />");
		writer.writeLine("</div>");

		
		//Div synonyms
		//-------------
		writer.writeLine("<div id=\"DivSynonyms" + id_mapping + "\" style=\"DISPLAY: none\" >");
		writer.writeLine("<fieldset class=\"synonyms\">");
		writer.writeLine("<legend>Synonyms and alternative labels</legend>");
		//writer.writeLine("<label>Put info about direct superclasses</label>");
		writer.writeLine("<ul>");
		writer.writeLine("<li><b>Synonyms 1</b>: " + synonyms1 +"</li>");
		writer.writeLine("<li><b>Synonyms 2</b>: " + synonyms2 +"</li>");
		writer.writeLine("</ul>");
		writer.writeLine("</fieldset>");
		writer.writeLine("<br />");
		writer.writeLine("</div>");
		
		//Div Scope
		//-------------
		writer.writeLine("<div id=\"DivScope" + id_mapping + "\" style=\"DISPLAY: none\" >");
		writer.writeLine("<fieldset class=\"scope\">");
		writer.writeLine("<legend>Scope information</legend>");
		//writer.writeLine("<label>Put info about direct superclasses</label>");
		writer.writeLine("<ul>");
		
		writer.writeLine("<li>Superclasses for...");
		writer.writeLine("<ul>");		
		writer.writeLine("<li><b>" + label1 + "</b>: " + superclasses1 +"</li>");
		writer.writeLine("<li><b>" + label2 + "</b>: " + superclasses2 +"</li>");
		writer.writeLine("</ul>");
		writer.writeLine("<li>Subclasses for...");
		writer.writeLine("<ul>");
		writer.writeLine("<li><b>" + label1 + "</b>: " + subclasses1 +"</li>");
		writer.writeLine("<li><b>" + label2 + "</b>: " + subclasses2 +"</li>");
		
		writer.writeLine("</ul>");
		writer.writeLine("</fieldset>");
		writer.writeLine("<br />");
		writer.writeLine("</div>");
				
		
		//Div Conflict
		//-------------
		writer.writeLine("<div id=\"DivConflict" + id_mapping + "\" style=\"DISPLAY: none\" >");
		writer.writeLine("<fieldset class=\"conflict\">");
		writer.writeLine("<legend>Mappings in conflict</legend>");
		
		
		writer.writeLine("<label>There are '" + mappingsInConflict.size() + "' mappings in conflict.</label>");
		
		if (mappingsInConflict.size()>0){
			writer.writeLine("<br />");
			writer.writeLine("<br />");
			writer.writeLine("<label>If the current mapping is ADDED the following mappings will be DISCARDED.</label>");
			writer.writeLine("<br />");
			writer.writeLine("<br />");
			
			writer.writeLine("<ul>");
			for (String mapping_str : mappingsInConflict){
				writer.writeLine("<li>" + mapping_str + "</li>");
			}
			writer.writeLine("</ul>");
		}
		writer.writeLine("</fieldset>");
		writer.writeLine("<br />");
		writer.writeLine("</div>");		
				
		
		//Div ambiguity
		//-------------
		writer.writeLine("<div id=\"DivAmbiguity" + id_mapping + "\" style=\"DISPLAY: none\" >");
		writer.writeLine("<fieldset class=\"ambiguity\">");
		writer.writeLine("<legend>Ambiguous mappings</legend>");
		
		writer.writeLine("<label>There are '" + mappingsAmbiguous.size() + "' ambiguous mappings.</label>");
		
		if (mappingsAmbiguous.size()>0){
			writer.writeLine("<br />");
			writer.writeLine("<br />");
			writer.writeLine("<label>If the AMBIGUITY CRITERIA is used and the current mapping is ADDED (respectively DISCARDED) the following mappings will be DISCARDED (respectively ADDED).</label>");
			
			writer.writeLine("<br />");
			writer.writeLine("<br />");
			
			writer.writeLine("<ul>");
			for (String mapping_str : mappingsAmbiguous){
				writer.writeLine("<li>" + mapping_str + "</li>");
			}
			writer.writeLine("</ul>");
		}
		
		writer.writeLine("</fieldset>");
		writer.writeLine("<br />");
		writer.writeLine("</div>");
				
		writer.writeLine("<br />");
		
		writer.writeLine("</li>");
		
		
		
	}
	
	

	
	
	
	
	
}
