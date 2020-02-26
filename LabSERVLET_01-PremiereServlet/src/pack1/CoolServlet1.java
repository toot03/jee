package pack1;
/**
	Servlet qui implemente l'interface Servlet
	Les m�thodes � red�finir sont :
	destroy()  
	getServletConfig() Returns a servlet config object, which contains any initialization parameters and startup configuration for this servlet. 
 	getServletInfo()  Returns a string containing information about the servlet, such as its author, version, and copyright. 
 	init(ServletConfig) Initializes the servlet. 
 	service(ServletRequest, ServletResponse) Supporte une simple demande du client 
*/
	
import java.io.*;
import javax.servlet.*;

public class CoolServlet1 implements Servlet {
	private ServletConfig cfg;

	public void init(ServletConfig config) throws ServletException {
		// Objet utilis� par le conteneur de servlets pour passer
		// des informations � la servlet
	    cfg = config;
	    //ServletContext  ctx = cfg.getServletContext();
	}
	
	public ServletConfig getServletConfig() {
	    return cfg;
	}
	
	public String getServletInfo() {
	    return "Une servlet de test";
	}	
	
	public void destroy() {
	}

	public void service (ServletRequest req,  ServletResponse res) 
	throws ServletException, IOException  {
	  
		// Positionnement du type Mime de la r�ponse
	    res.setContentType( "text/html" );
	    //res.setContentType("text/html;charset=UTF-8");
	    	 
	    // R�cup�ration du contexte de la servlet
	    // Vous obtenez le contexte de la servlet � partir de la m�thode 
	    // getServletContext() de ServletConfig. 
	    // Cette m�thode retourne un objet ServletContext.
	    ServletContext scontext = cfg.getServletContext();
		
	    // R�cup�ration d'un printWriter (package java.io)
	    PrintWriter out = res.getWriter();
	    
	    // R�cup�rer l'image avec son chemin d'acc�s par rapport au contexte
	    String imgpath = scontext.getContextPath() + "/images/Java_logo.jpg";
	
	    // Eciture de la page HTML dans le flux
	    out.println("<!DOCTYPE html>");
	    out.println( "<html lang=\"fr\">");
	    out.println( "<head>");
	    out.println("<meta charset=\"utf-8\">");
	    out.println( "<title>Page generee par une servlet</title>");
	    out.println( "</head>");
	    out.println( "<body bgcolor=\"white\">");
	    out.println( "<img src=\""+ imgpath + "\">" );
	    out.println( "<h1>LabSERVLET_01 : impl�mentation Servlet + d�claration dans web.xml</h1>");
	    out.println("<p>Nom de la servlet : " + cfg.getServletName() + "</p>");
	    
	    // L�objet ServletContext est contenu � l�int�rieur de l�objet ServletConfig.
	    // Vous obtenez le contexte de la servlet � partir de la m�thode 
	    // getServletContext() de ServletConfig. 
	    // Cette m�thode retourne un objet ServletContext.

	    out.println("<p>Nom et version du conteneur : " + scontext.getServerInfo() + "</p>");
	
	    out.println("<p>Chemin r�el : " + scontext.getRealPath("/CoolServlet.html")  + "</p>");
	    out.println("<p>Chemin r�el : " + scontext.getRealPath("/CoolServlet1")  + "</p>");
	    
	    
	    out.println("<p>Version API Servlets : " + scontext.getMajorVersion() + "." + scontext.getMinorVersion() + "</p>");
	    out.println("</body>");
	    out.println("</html>");
	    
	    // Vider le flux cr�� avec getWriter()
	    out.flush(); 
	    
	    // Fermer le flux
	    out.close();
	}
}
