package ie.gmit.sw;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ServiceHandler extends HttpServlet {
	/* Declare any shared objects here. For example any of the following can be handled from 
	 * this context by instantiating them at a servlet level:
	 *   1) An Asynchronous Message Facade: declare the IN and OUT queues or MessageQueue
	 *   2) An Chain of Responsibility: declare the initial handler or a full chain object
	 *   1) A Proxy: Declare a shared proxy here and a request proxy inside doGet()
	 */

	/*****
	DON'T FORGET TO DELETE
	******/
	Boolean test = false;

	private String environmentalVariable = null; //Demo purposes only. Rename this variable to something more appropriate
	private static long jobNumber = 0;


	// Run on servlet class initialized.
	public void init() throws ServletException {
		ServletContext ctx = getServletContext(); //The servlet context is the application itself.
		
		//Reads the value from the <context-param> in web.xml. Any application scope variables 
		//defined in the web.xml can be read in as follows:
		environmentalVariable = ctx.getInitParameter("SOME_GLOBAL_OR_ENVIRONMENTAL_VARIABLE"); 
	}


	// HTTP Methods.

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); 
		PrintWriter out = resp.getWriter(); 
		String str = req.getParameter("searchStr");
		String taskNumber = req.getParameter("frmTaskNumber");
		
		//We could use the following to track asynchronous tasks. Comment it out otherwise...
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;

			int counter = 1;

			/*if (req.getParameter("counter") != null){
				counter = Integer.parseInt(req.getParameter("counter"));
				counter++;
			}*/

			printLoadingPage(out, str, taskNumber, counter);						
			
			//Add job to in-queue
		}else{
			//RequestDispatcher dispatcher = req.getRequestDispatcher("/poll");
			//dispatcher.forward(req,resp);
			
			//Check out-queue for finished job with the given taskNumber
			
			//Let's pretend for now.
			if(test)
			{
				Client c = new Client();
				String result = null;
				try{
					result = c.getDesc(str);
				} catch(Exception e){
					result = "Uh oh";
				}
				printResultPage(out, str, taskNumber, result);	
			}else{

				test = true;
				int counter = 1;

				if (req.getParameter("counter") != null){
					counter = Integer.parseInt(req.getParameter("counter"));
					counter++;
				}

				printLoadingPage(out, str, taskNumber, counter);	
			}					
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}

	// Handler Methods

	public void printHeader(PrintWriter out){
		out.print("<html><head><title>A JEE Application for Measuring Document Similarity</title>");	
		out.print("</head>");		
		out.print("<body>");
	}
	public void printFooter(PrintWriter out){
		out.print("</body>");	
		out.print("</html>");	
	}
	public void printScript(PrintWriter out){
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);"); //Refresh every 5 seconds
		out.print("</script>");
	}
	public void printLoadingPage(PrintWriter out, String str, String taskNumber, Integer counter){
		printHeader(out);
		//Output some headings at the top of the generated page
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<H3>String: " + str + "</H3>");
		//Output some useful information for you (yes YOU!)
		out.print("<div id=\"r\"></div>");
		//We can also dynamically write out a form using hidden form fields. The form itself is not
		//visible in the browser, but the JavaScript below can see it.
		out.print("<form name=\"frmRequestDetails\" action=\"doProcess\">");
		out.print("<input name=\"searchStr\" type=\"hidden\" value=\"" + str + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("<input name=\"counter\" type=\"hidden\" value=\"" + counter + "\">");
		out.print("</form>");	
		printFooter(out);
		printScript(out);
	}
	public void printResultPage(PrintWriter out, String str, String taskNumber, String result){
		printHeader(out);
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<H3>Search query: " + str + "</H3>");
		out.print(result);
		out.print("<br><br><a class=\"button\" href=\"index.jsp\">Make Another Query</a>");
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"searchStr\" type=\"hidden\" value=\"" + str + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");
		printFooter(out);
	}
}