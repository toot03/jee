package pack1;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

@SuppressWarnings("serial")
public class CoolServlet4 extends HttpServlet {
	
	private ServletConfig cfg;
	
	public void doProcess (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
	  
		// Positionner le type mime du corps de  la réponse qui sera envoyée au navigateur
		// text/html pour du HTML, texte/plain pour du texte brut,  application/pdf pour un fichier pdf
		res.setContentType ("text/html");				

		// Créer un objet PrinterWriter permettant d'écrire des données au format texte dans
		// la réponse
		PrintWriter out = res.getWriter();	
		
		// Ecrire dans le flux
	    out.println("<!DOCTYPE html>");
	    out.println( "<html lang=\"fr\">");
	    out.println( "<head>");
	    out.println("<meta charset=\"utf-8\">");
	    out.println( "<title>Page generee par une servlet</title>");
	    out.println( "</head>");
	    out.println( "<body>");
	    out.println( "<h1>LabSERVLET_01 : paramètre d'initialisation dans web.xml</h1>");
	    out.println("<p>Nom de la servlet : " + cfg.getServletName() + "</p>");
	    // Récupération du parametre positionné dans web.xml
	    out.println( "<p>Valeur du parametre d'initialisation param1 : " + cfg.getInitParameter("param1") + "</p>");
	    	    
	    out.println( "</body>" );
	    out.println( "</html>" );
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
	}
  
	public void init(ServletConfig config) throws ServletException {
		
		// Si redéfinition de la méthode init(ServletConfig), vous devez :
		//	soit mémoriser le servletConfig, 
		// 	soit ctx = cfg.getServletContext();
		
		// ou appeler la méthode init() de la classe parents (HttpServlet)
		// Soit super.init(cfg);

		super.init(config);
	    cfg = config;
	}
	
	// Traitement d'un formulaire transmis par la méthode GET ou
	// Traitement d'un lien de type <a></a>
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	// Traitement d'un formulaire transmis par la méthode POST
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
	        doProcess(request, response);
	}
}