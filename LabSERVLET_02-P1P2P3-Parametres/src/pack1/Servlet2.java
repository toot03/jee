package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import utilitaires.Utils;

public class Servlet2 extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	private ServletConfig cfg;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	     cfg = config;
	}
	
	public void doProcess (HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException
    {
		// Positionnement du type Mime du corps de la réponse
		res.setContentType("text/html");
	    
		// Créer un objet PrinterWriter permettant d'écrire des données au format texte dans
		// la réponse
	    PrintWriter out = res.getWriter();
	    
	    // Ecriture de l'entête de la page HTML retournée
	    Utils.ecrireDebut(out,"Les paramètres reçus :");
	    
	    out.println("<p> Utilisation de getInitParameter()</p>");
	    out.println("<p> Utilisation de getInitParameterNames()</p>");
	    
	    out.println("<table border=\"1\" align=left>\n" +
	                "<tr bgcolor=\"#EECC00\">\n" +
	                "<th>Nom du paramètre</th><th>Valeur(s) du paramètre</th>");
	    
	    //
	    // Lecture du paramètre nommé param1 présent dans le fichier web.xml
	    //   
	    out.println("<tr><td>param1</td><td>" + cfg.getInitParameter("param1") + "</td></tr>");
	    
	    //
	    // Lecture de tous les paramètres présents dans le fichier web.xml
	    //
	    Enumeration<String> enumParams = cfg.getInitParameterNames();
	    
	    // TANT QU'IL y a encore des paramètres
	    while (enumParams.hasMoreElements()) {
	    	// Elément suivant
	    	String strNomParam = enumParams.nextElement();
	    	
	    	// Affichage du nom
	    	out.print("<tr><td>" + strNomParam + "</td>");
	    	
	    	// Récupération de la valeur du paramètre
	    	String strValeurParam = cfg.getInitParameter(strNomParam);
	    	  
	    	out.println("<td>" + strValeurParam + "</td>");
	    	
	    	out.println("</tr>");
	    }
	    
	    out.println("</table>\n");
	    
	    // Ecrire la fin de la page HTML renvoyée
	    Utils.ecrireFin(out);
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
  }

	// SI requête de type GET -> appeler doProcess()
	public void doGet(HttpServletRequest req, HttpServletResponse res)
  			throws ServletException, IOException {
				doProcess(req, res);
	}
	
	// SI requête de type POST -> appeler doProcess()
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			doProcess(req, res);
	}
}