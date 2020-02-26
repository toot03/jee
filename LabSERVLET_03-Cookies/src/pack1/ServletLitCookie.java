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
		// Positionner le type mime du corps de  la r�ponse qui sera envoy�e au navigateur
		// text/html pour du HTML, texte/plain pour du texte brut,  application/pdf pour un fichier pdf
	    res.setContentType("text/html");
	    
	    // Vous pouvez ajouter le charset
	    //res.setContentType("text/html;charset=UTF-8");
	    
	    // R�cup�ration d'un printWriter (package java.io)
	    PrintWriter out = res.getWriter();
	    
	    // Ecriture de l'ent�te de la page HTML retourn�e
	    Utils.ecrireDebut(out,"Cookies actifs");
	    
	    out.println("<table border=\"1\" align=\"center\">\n" +
	                "<tr bgcolor=\"#FFAD00\">\n" +
	                "<th>Nom du Cookie</th>\n" +
	                "<th>Valeur du Cookie</th>\n" +
	                "<th>Dur�e du Cookie</th>\n" +
	                "<th>Domaine</th>\n" +
	                "</tr>");
	    
	    // R�cup�rer dans un tableau les cookies transmis dans la requ�te
	    Cookie[] tabcookies = req.getCookies();
	    
	    // SI il existe des cookies
	    if (tabcookies != null)  {
	    	Cookie cookie;
	      
	    	// Parcourir l'assiette des cookies
	    	for (int i=0; i<tabcookies.length; i++) {
	    		cookie = tabcookies[i];
	    		
	    		// R�cup�ration du nom, de la valeur et de la dur�e de vie du cookie
	    		// getMaxAge() retourne -1 -> cookie persistant jusqu'� l'arr�t du navigateur
	    		// Impossible de r�cup�rer la dur�e de vie du cookie : navigateur ne retourne pas l'information
	    		// Impossible de r�cup�rer le domaine : navigateur ne retourne pas l'information
	    		out.println("<tr>\n" +
	                    "<td>" + cookie.getName() + "</td>" +
	                    "<td>" + cookie.getValue() + "</td>" +
	        			"<td>" + cookie.getMaxAge() + "</td>" +
	        			"<td>" + cookie.getDomain() + "</td>" +
	        					"</tr>");
	    	}
	    }
	    out.println("</table>");
	    
	    // Ecriture de la fin de la page HTML retourn�e
	    Utils.ecrireFin(out);
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
    }
}