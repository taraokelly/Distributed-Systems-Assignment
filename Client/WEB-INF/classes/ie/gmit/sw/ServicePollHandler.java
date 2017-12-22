package ie.gmit.sw;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServicePollHandler extends HttpServlet {

	// Run on servlet class initialized.
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
	}

	// HTTP Methods.

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); 
		PrintWriter out = resp.getWriter(); 
		String str = req.getParameter("searchStr");
		String taskNumber = req.getParameter("frmTaskNumber");
		int counter = 1;

		if (req.getParameter("counter") != null){
			counter = Integer.parseInt(req.getParameter("counter"));
			counter++;
		}

		Client c = new Client();
		String result = null;
		try{
			result = c.getDesc(str);
		} catch(Exception e){
			result = "Uh oh";
		}

		printHeader(out);
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<H3>Search query: " + str + "</H3>");
		out.print("<b><font color=\"ff0000\">A total of " + counter + " polls have been made for this request.</font></b> ");
		out.print(result);
		out.print("<br><br><a class=\"button\" href=\"index.jsp\">Make Another Query</a>");
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"searchStr\" type=\"hidden\" value=\"" + str + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("<input name=\"counter\" type=\"hidden\" value=\"" + counter + "\">");
		out.print("</form>");								
		printFooter(out);
		printScript(out);
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
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 5000);"); //Refresh every 5 seconds
		out.print("</script>");
	}
}