import java.io.FileWriter;
import java.io.IOException;

public class HTMLGenerator {
	HTMLGenerator(){
		
	}
	
	public void createHTML(String[] names, String[][] values, String[][] scores, String[][] total, String[] criteria, int[] weighting, String[][] colors) {
		try {
			
		      FileWriter report = new FileWriter("./DSSReport/report.html", false);
		      report.write(
		    		  "<!DOCTYPE html>"
		    		  + "<html lang=\"en\">"
		    		  + "<head>"
		    		  + 	"<title>DSS Project</title>"
		    		  + 	"<meta charset=\"utf-8\">"
		    		  + 	"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
		    		  + 	"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">"
		    		  + 	"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>"
		    		  + 	"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>"
		    		  + 	"<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
		    		  + 	"<link rel=\"stylesheet\" href=\"report.css\">"
		    		  + "</head>"
		    		  + "<body>"
		    		  +		"<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">"
		    		  + 		"<a class=\"navbar-brand\" href=\"report.html\">DSS</a>"
		    		  + 		" <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">"
		    		  + 			"<span class=\"navbar-toggler-icon\"></span>"
		    		  + 		"</button>"
		    		  + 		"<div class=\"collapse navbar-collapse\" id=\"navbarNav\">"
		    		  + 			"<ul class=\"navbar-nav\">"
		    		  + 				"<li class=\"nav-item active\">"
		    		  + 					"<a class=\"nav-link\" href=\"report.html\">Report table <span class=\"sr-only\">(current)</span></a>"
		    		  + 				"</li>"
		    		  + 				"<li class=\"nav-item\">"
		    		  + 					"<a class=\"nav-link\" href=\"criteria.html\">Criteria description</a>"
		    		  + 				"</li>"
		    		  + 			"</ul>"
		    		  + 		"</div>"
		    		  + 	"</nav>"
		    		  + 	"<div class=\"container\">"
		    		  + 		"<h2>Report table</h2>"
		    		  + 		"<table class=\"table table-bordered\">"
				      + 		"<thead>"
		    		  + 			"<tr>"
				      + 				"<th>Criteria</th>"
		    		  + 				"<th>Weighting</th>");
//////////////////////////////////////////////////////////////////////////////////
		      for(int i=0; i < names.length;i++) {
		    	  report.write("<th colspan=\"3\">"+names[i]+"</th>");
		      }
		    
		      report.write(			"</tr>"
		    		  + 		"</thead>"
		    		  + 		"<tbody>"
		    		  + 			"<tr>"
		    		  + 				"<td></td>"
		    		  + 				"<td></td>");
		      for(int i=0; i < names.length;i++) {
		    	  report.write("<td>Value</td>" + 
		    			  		"<td>Score</td>" + 
		    	  				"<td>Total</td>");
		      }
		    		  
    		  for(int i=0; i < criteria.length;i++) {
    			  if(i != 28) {
    				  report.write(		"<tr>"
		    		  +					"<td>"+criteria[i]+"</td>"
		    		  +					"<td>"+weighting[i]+"</td>");
    			  }else {
    				  report.write(		"<tr>"
    				  +					"<td colspan=\"2\">"+criteria[i]+"</td>");
    			  }
		    	  
		    	  for(int j = 0; j<names.length;j++) {
		    		  if(i != 28) {
		    			  report.write( "<td class=\""+colors[j][i]+" nw\">"+values[j][i]+"</td>"
				    		  +			"<td class=\""+colors[j][i]+" nw\">"+scores[j][i]+"</td>"
		    		  		  +			"<td class=\""+colors[j][i]+" nw\">"+total[j][i]+"</td>");
		    		  }else
		    			  report.write("<td colspan=\"3\" class=\""+colors[j][i]+"\">"+total[j][i]+"</td>");
		    	  }
		    		  
		    	  report.write(		"</tr>");
		      }
    		  
		    		  
////////////////////////////////////////////////////////
    		  report.write(		"</tbody>"
			    	+		"</table>"
			    	+ 	"</div>"
			    	+ "</body>"
			    	+ "</html>");
		      report.close();
		      
		      
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
