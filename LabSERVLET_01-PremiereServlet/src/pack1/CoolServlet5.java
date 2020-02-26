package pack1;

import java.io.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/Servlet5"
		   ,initParams=@WebInitParam(name="param1",value="Valeur de param1")
)
public class CoolServlet5 extends HttpServlet {
	private ServletConfig cfg;
	
	public void doProcess (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
	  
		// Positionner le type mime du corps de  la r�ponse qui sera envoy�e au navigateur
		// text/html pour du HTML, texte/plain pour du texte brut,  application/pdf pour un fichier pdf
		res.setContentType ("text/html");		
		
	    // R�cup�rer l'image avec son chemin d'acc�s par rapport au contexte
	    String imgpath = req.getContextPath() + "/images/Java_logo.jpg";
	    
		// Cr�er un objet PrinterWriter permettant d'�crire des donn�es au format texte dans
		// la r�ponse
		PrintWriter out = res.getWriter();	
		
		// Ecrire dans le flux
	    out.println("<!DOCTYPE html>");
	    out.println("<html lang=\"fr\">");
	    out.println("<head>");
	    out.println("<meta charset=\"utf-8\">");
	    out.println("<title>Page generee par une servlet</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<img src=\""+ imgpath + "\">");
	    out.println("<h1>LabSERVLET_01 : Param�tre d'intialisation avec @WebInitParam</h1>");
	    out.println("<p>Nom de la servlet : " + cfg.getServletName() + "</p>");
	    
	    // R�cup�ration du parametre positionn� dans web.xml
	    out.println( "<p>Valeur du parametre d'initialisation param1 : " + cfg.getInitParameter("param1") + "</p>");
	   
	    out.println("</body>");
	    out.println("</html>");
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
	}
  
	public void init(ServletConfig config) throws ServletException {
		
		// Si red�finition de la m�thode init(ServletConfig), vous devez :
		//	soit m�moriser le servletConfig, 
		// 	Soit ctx = cfg.getServletContext();
		
		// ou appeler la m�thode init() de la classe parents (HttpServlet)
		// Soit super.init(cfg);

		super.init(config);
	    cfg = config;
	}
	
	// Traitement d'un formulaire transmis par la m�thode GET ou
	// Traitement d'un lien de type <a></a>
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	// Traitement d'un formulaire transmis par la m�thode POST
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
	        doProcess(request, response);
	}
}