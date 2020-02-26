package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import utilitaires.Utils;

public class Servlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
	    
	    out.println("<table border=\"1\" align=left>\n" +
	                "<tr bgcolor=\"#EECC00\">\n" +
	                "<th>Nom du paramètre</th><th>Valeur(s) du paramètre</th>");
    
	    //
	    // Lecture de tous les paramètres présents dans l'URL
	    //
	    
	    // Récupération des paramètres de l'URL
	    Enumeration<String> enumParams = req.getParameterNames();
    
	    // TANT QU'IL y a encore des paramètres
	    while (enumParams.hasMoreElements()) {
	    	// Elément suivant
	    	String strNomParam = enumParams.nextElement();
	    	
	    	// Affichage du nom
	    	out.print("<tr><td>" + strNomParam + "</td>");
	    	
	    	// Récupération de la valeur du paramètre
	    	String[] tabstrValParam = req.getParameterValues(strNomParam);
	    	
	    	// SI une seule valeur pour ce paramètre 
	    	if (tabstrValParam.length == 1) {
	    		String strValeurParam = tabstrValParam[0];
	    		if (strValeurParam.length() == 0)
	    			out.println("<td>pas de valeur</td>");
	    		else
	    			out.println("<td>" + strValeurParam + "</td>");
	    	}
	    	// SINON plusieurs valeurs
	    	else
	    	{
	    		// Affichage des valeurs sous forme d'une liste
	    		out.println("<td><ul>");
	    		
	    		for (int i=0; i<tabstrValParam.length; i++) {
	    			out.println("<li>" + tabstrValParam[i] + "</li>");
	    		}
	    		out.println("</ul>");
	    		out.println("</td>");
	    	}
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
		
		System.out.println ("doGet()");
		doProcess(req, res);
	}
	
	// SI requête de type POST -> appeler doProcess()
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		System.out.println ("doPost()");
		doProcess(req, res);
	}
}