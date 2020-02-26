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
		// Positionnement du type Mime du corps de la r�ponse
		res.setContentType("text/html");
	    
		// Cr�er un objet PrinterWriter permettant d'�crire des donn�es au format texte dans
		// la r�ponse
	    PrintWriter out = res.getWriter();
	    
	    // Ecriture de l'ent�te de la page HTML retourn�e
	    Utils.ecrireDebut(out,"Les param�tres re�us :");
	    
	    out.println("<table border=\"1\" align=left>\n" +
	                "<tr bgcolor=\"#EECC00\">\n" +
	                "<th>Nom du param�tre</th><th>Valeur(s) du param�tre</th>");
    
	    //
	    // Lecture de tous les param�tres pr�sents dans l'URL
	    //
	    
	    // R�cup�ration des param�tres de l'URL
	    Enumeration<String> enumParams = req.getParameterNames();
    
	    // TANT QU'IL y a encore des param�tres
	    while (enumParams.hasMoreElements()) {
	    	// El�ment suivant
	    	String strNomParam = enumParams.nextElement();
	    	
	    	// Affichage du nom
	    	out.print("<tr><td>" + strNomParam + "</td>");
	    	
	    	// R�cup�ration de la valeur du param�tre
	    	String[] tabstrValParam = req.getParameterValues(strNomParam);
	    	
	    	// SI une seule valeur pour ce param�tre 
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
	    
	    // Ecrire la fin de la page HTML renvoy�e
	    Utils.ecrireFin(out);
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
  }

	// SI requ�te de type GET -> appeler doProcess()
	public void doGet(HttpServletRequest req, HttpServletResponse res)
  			throws ServletException, IOException {
		
		System.out.println ("doGet()");
		doProcess(req, res);
	}
	
	// SI requ�te de type POST -> appeler doProcess()
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		System.out.println ("doPost()");
		doProcess(req, res);
	}
}