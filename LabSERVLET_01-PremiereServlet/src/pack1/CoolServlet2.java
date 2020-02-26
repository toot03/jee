package pack1;

import java.io.*;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.*;
import javax.servlet.*;

@SuppressWarnings("serial")
public class CoolServlet2 extends HttpServlet {
	private ServletConfig cfg;
	
	public void doProcess (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
	  
		// Positionner le type mime du corps de  la r�ponse qui sera envoy�e au navigateur
		// text/html pour du HTML, texte/plain pour du texte brut,  application/pdf pour un fichier pdf
		res.setContentType ("text/html");		
		
	    // R�cup�rer l'image avec son chemin d'acc�s par rapport au contexte
	    String imgpath = req.getContextPath() + "/images/Java_logo.jpg";
	    
	    System.out.println (imgpath);
		// Cr�er un objet PrinterWriter permettant d'�crire des donn�es au format texte dans
		// la r�ponse
		PrintWriter out = res.getWriter();	
		
		// Ecrire dans le flux
	    out.println("<!DOCTYPE html>");
	    out.println( "<html lang=\"fr\">");
	    out.println( "<head>");
	    out.println("<meta charset=\"utf-8\">");
	    out.println( "<title>Page generee par une servlet</title>");
	    out.println( "</head>");
	    out.println( "<body >");
	    out.println( "<img src=\""+ imgpath + "\">" );
	    out.println( "<h1>LabSERVLET_01 : h�ritage HttpServlet + d�claration dans web.xml</h1>");
	    out.println("<p>Nom de la servlet : " + cfg.getServletName() + "</p>");
	    
	    // ContextPath : le chemin du contexte de d�ploiement
	    // ServletPath : la section du chemin qui a d�clench� le mapping
	    // PathInfo : la partie de la requ�te qui n�est ni le ContextPath ni le ServletPath

	    out.println("<p>Nom de l'application h�bergeant la servlet : " +  req.getContextPath() +  "</p>");
	    out.println("<p>Chemin de la Servlet  : " +  req.getServletPath() +  "</p>");
	    out.println("<p>Type de requ�te HTTP utilis�e  : " +  req.getMethod() +  "</p>");
	    out.println("<p>Path Info : " +  req.getPathInfo() +  "</p>");

	    ServletContext scontext = cfg.getServletContext(); 
	    out.println("<p>Document root : " + scontext.getRealPath("/�") + "</p>");
	    
	    // Autre facon pour r�cup�rer le contexte
	    // Est ok si super.init(config) dans la m�thode init()
	    scontext = getServletContext(); 
	    
	    out.println("<p>Nom et version du conteneur : " + scontext.getServerInfo() + "</p>");

	    out.println("<p>Version API Servlets : " + scontext.getMajorVersion() + "." + scontext.getMinorVersion() + "</p>");
	
	    // URL utilis�e pour contacter  la servlet    
	    String url = req.getRequestURL().toString(); 
	    out.println("<p>URL utilis�e pour contacter  la servlet " + url + "</p>");
    
	    // Param�tres pass�s dans l'URL    
	    String queryString = req.getQueryString(); 
	    out.println("<p>Param�tres pass�s dans l'URL : " + queryString + "</p>");
	    
	    // URI de la requ�te sans le hostname    
	    String uri = req.getRequestURI(); 
	    out.println("<p>URI de la requ�te sans le hostname : " + uri + "</p>");

	    // Retourne le nom du protocole utilis� par le client 
	    String scheme = req.getScheme();  
	    out.println("<p>Protocole  : " + scheme + "</p>");
	    
	    String serverName = req.getServerName();
	    out.println("<p>Nom du serveur : " + serverName + "</p>");
	    
	    int portNumber = req.getServerPort();  
	    out.println("<p>Num�ro de port : " + portNumber + "</p>");
	    
	    // Retourne sous la forme d'une chaine commencant par /, la portion
	    // de l'URL de la requete correspondant au nom
	    // du contexte de l'application
	    String contextPath = req.getContextPath();   
	    out.println("<p>Chemin du contexte : " + contextPath + "</p>");
	    
	    // Retourne la partie de l'URL qui invoque la servlet
	    // compos� du chemin et du nom de la servlet
	    String servletPath = req.getServletPath();   
	    out.println("<p>Chemin de la Servlet : " + servletPath + "</p>");
	    
	    
	    String pathInfo = req.getPathInfo(); 
	    out.println("<p>Path Info : " + pathInfo + "</p>");

	    out.println("<p>INFOS SUR L'EN-TETE</p>");
	    
	    // Retourne l'ensemble des noms des en-t�tes contenus dans la requ�te
	    Enumeration<String> enTetes = req.getHeaderNames();

	    // Affichage du contenu
	    while (enTetes.hasMoreElements()) {
	    	// R�cup�ration du nom
	    	String nomEntete = enTetes.nextElement();

	    	// getHeader() retourne la valeur du nom de l'en-t�te pass� en param�tre
	    	out.println("<p>" + nomEntete + " : " + req.getHeader(nomEntete) + " : "  + "</p>");
	    }

	    // Retourne le nombre d'octets dans la requ�te
	    int lg =  req.getContentLength();   
	    out.println("<p>Nombre d'octets dans la requ�te : " + lg + "</p>");
      
        // Retourne le type mime du contenu de la requ�te
	    String type = req.getContentType();
	    out.println("<p>Type mime du contenu de la requ�te : " + type + "</p>");
      
	    // Retourne la langue privil�gi�e
	    Locale loc = req.getLocale();
	    // R�cup�ration du pays
	    String pays = loc.getCountry();
	    out.println("<p>Pays : " + pays + "</p>");
       
        // Retourne la liste des langues
	    Enumeration<Locale> enLocales = req.getLocales();     
       
	    // Affichage du contenu
	    while (enLocales.hasMoreElements()) {
	    	// R�cup�ration du nom
	    	Locale loca = enLocales.nextElement();
	    	if (!loca.getCountry().isEmpty())
	    			out.println("<p>Langue : " + loca.getCountry() + "</p>");
	    }
	    
	    out.println( "</body>" );
	    out.println( "</html>" );
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
	}
  
	public void init(ServletConfig config) throws ServletException {
		
		// Si red�finition de la m�thode init(ServletConfig), vous devez :
		//	soit m�moriser le servletConfig, 
		// 	soit ctx = cfg.getServletContext();
		
		// ou appeler la m�thode init() de la classe parents (HttpServlet)
		// Soit super.init(cfg);

		super.init(config);
	    cfg = config;
	}
	
	// Traitement d'un formulaire transmis par la m�thode GET ou
	// Traitement d'un lien de type <a></a>
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		doProcess(request, response);
	}
	
	// Traitement d'un formulaire transmis par la m�thode POST
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
	        doProcess(request, response);
	}
}
