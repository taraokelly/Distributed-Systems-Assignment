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

	private String serverAddress = null;
	private String serviceName = null;
	private static long jobNumber = 0;
	//An Asynchronous Message Facade
	private static Queue<Request> inqueue = new LinkedList<Request>();
	private static Map<String, String> outqueue = new LinkedHashMap<String, String>();
	//Start Client thread & pass in queues.
	private Thread client;

	// Run on servlet class initialized.
	public void init() throws ServletException {
		ServletContext ctx = getServletContext(); 
		// Load in server address and service name from the <context-param> tags in web.xml. Any application scope variables 
		serverAddress = ctx.getInitParameter("SERVER_ADDRESS"); 
		serviceName = ctx.getInitParameter("SERVICE_NAME"); 
		//Start Client thread & pass in queues.
		client = new Thread(new Client(serverAddress+serviceName,inqueue, outqueue));
		client.start();
	}

	// HTTP Methods.

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); 
		PrintWriter out = resp.getWriter(); 
		String str = req.getParameter("searchStr");
		String taskNumber = req.getParameter("frmTaskNumber");
		int counter = 1;
		String result = outqueue.get(taskNumber);
		String query = "Search";
		
		//Check if task number does not exist 
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			//Add job to in-queue
			Request r = new SearchRequest(taskNumber, str);
			inqueue.offer(r);
			printLoadingPage(out, str, taskNumber, counter);						
		} else{
			//Check out-queue for finished job with the given taskNumber and add to queue
			if(result !=null)
			{	
				printResultPage(out, query, str, taskNumber, result);	
			} else{
				if (req.getParameter("counter") != null){
					counter = Integer.parseInt(req.getParameter("counter"));
					counter++;
				}
				printLoadingPage(out, str, taskNumber, counter);	
			}				
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//doGet(req, resp);
 	}

	// Handler Methods

	public void printHeader(PrintWriter out){
		out.print("<html><head><title>A JEE Application for Measuring Document Similarity</title>");
		out.print("<style>h1{color:#990000;} h3{color:grey;} .wrapper{margin: 0 auto; width:50%; min-width: 600px; margin-top: 50px; text-align:center;} html{font-size:18px;}</style>");
		out.print("</head>");		
		out.print("<body>");
	}
	public void printFooter(PrintWriter out){
		out.print("</body>");	
		out.print("</html>");	
	}
	public void printScript(PrintWriter out){
		out.print("<script>");
		//Refresh by submitting every 10 seconds.
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
	}
	public void printLoadingPage(PrintWriter out, String str, String taskNumber, Integer counter){
		printHeader(out);
		//Output some headings at the top of the generated page.
		out.print("<div class=\"wrapper\">");
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<H3>String: " + str + "</H3>");
		//We can also dynamically write out a form using hidden form fields. The form itself is not
		//visible in the browser, but the JavaScript below can see it.
		out.print("<form name=\"frmRequestDetails\" action=\"doProcess\">");
		out.print("<input name=\"searchStr\" type=\"hidden\" value=\"" + str + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("<input name=\"counter\" type=\"hidden\" value=\"" + counter + "\">");
		out.print("</form>");	
		out.print("</div>");
		printFooter(out);
		printScript(out);
	}
	public void printResultPage(PrintWriter out, String query, String str, String taskNumber, String result){
		printHeader(out);
		out.print("<div class=\"wrapper\">");
		out.print("<H1>Request processed for Job#: " + taskNumber + "</H1>");
		out.print("<H3>"+ query + " query: " + str + "</H3>");
		out.print("<font size=\"+2\"><b>Response:</b> " + result +"</font>");
		out.print("<br><br><a class=\"button\" href=\"index.jsp\">Make Another Query</a>");
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"searchStr\" type=\"hidden\" value=\"" + str + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");
		out.print("</div>");
		printFooter(out);
	}
}