package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utilitaires.Utils;

public class ServletLitCookie extends HttpServlet
{

	private static final long serialVersionUID = 3078297483344569957L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException
    {
		// Positionner le type mime du corps de  la réponse qui sera envoyée au navigateur
		// text/html pour du HTML, texte/plain pour du texte brut,  application/pdf pour un fichier pdf
	    res.setContentType("text/html");
	    
	    // Vous pouvez ajouter le charset
	    //res.setContentType("text/html;charset=UTF-8");
	    
	    // Récupération d'un printWriter (package java.io)
	    PrintWriter out = res.getWriter();
	    
	    // Ecriture de l'entête de la page HTML retournée
	    Utils.ecrireDebut(out,"Cookies actifs");
	    
	    out.println("<table border=\"1\" align=\"center\">\n" +
	                "<tr bgcolor=\"#FFAD00\">\n" +
	                "<th>Nom du Cookie</th>\n" +
	                "<th>Valeur du Cookie</th>\n" +
	                "<th>Durée du Cookie</th>\n" +
	                "<th>Domaine</th>\n" +
	                "</tr>");
	    
	    // Récupérer dans un tableau les cookies transmis dans la requête
	    Cookie[] tabcookies = req.getCookies();
	    
	    // SI il existe des cookies
	    if (tabcookies != null)  {
	    	Cookie cookie;
	      
	    	// Parcourir l'assiette des cookies
	    	for (int i=0; i<tabcookies.length; i++) {
	    		cookie = tabcookies[i];
	    		
	    		// Récupération du nom, de la valeur et de la durée de vie du cookie
	    		// getMaxAge() retourne -1 -> cookie persistant jusqu'à l'arrêt du navigateur
	    		// Impossible de récupérer la durée de vie du cookie : navigateur ne retourne pas l'information
	    		// Impossible de récupérer le domaine : navigateur ne retourne pas l'information
	    		out.println("<tr>\n" +
	                    "<td>" + cookie.getName() + "</td>" +
	                    "<td>" + cookie.getValue() + "</td>" +
	        			"<td>" + cookie.getMaxAge() + "</td>" +
	        			"<td>" + cookie.getDomain() + "</td>" +
	        					"</tr>");
	    	}
	    }
	    out.println("</table>");
	    
	    // Ecriture de la fin de la page HTML retournée
	    Utils.ecrireFin(out);
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
    }
}