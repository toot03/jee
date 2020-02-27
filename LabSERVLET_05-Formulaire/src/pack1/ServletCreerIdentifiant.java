package pack1;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import utilitaires.Utils;


public class ServletCreerIdentifiant extends HttpServlet {
 
		private static final long serialVersionUID = -1031966908477155121L;
		
		// D�claration de la variable de session
		private HttpSession m_session;

		public void doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		// R�cup�rer ou cr�er l�objet session associ� � la requ�te cliente
		// Param�tre : true ou rien = la session est r�cup�r�e ou cr��e automatiquement si elle n�existe pas
		m_session = req.getSession(true);

		// Positionnement du type Mime du corps de la r�ponse
		res.setContentType("text/html");
		
		// R�cup�ration d'un printWriter (package java.io)
		PrintWriter out = res.getWriter();
		
		// Lire le param�tre "identifiant" contenu dans la requ�te
		// Associer l'objet identifiant � la session
        m_session.setAttribute("identifiant",req.getParameter("identifiant"));
   
		// Lire le param�tre "motdepasse" contenu dans la requ�te
		// Associer l'objet motdepasse � la session
        m_session.setAttribute("motdepasse",req.getParameter("motdepasse"));
  
        // Ecriture de l'ent�te de la page HTML retourn�e
        Utils.ecritDebut(out,"Les param�tres du formulaire :");
        
        out.println("<table border=\"1\" align=left>\n" +
                "<th bgcolor=\"#EECC00\">Nom du param�tre</th><th bgcolor=\"#EECC00\">Valeur(s) du param�tre</th>");
        out.println("<tr bgcolor=\"#EECC00\"><td colspan=\"2\">Utilisation de getParameterNames()</td></tr>");
    
	    // 
        // R�cup�ration de l'ensemble des param�tres
        // Utilisation de getParameterNames()
        //
        
        // R�cup�ration des param�tres de la requ�te
        Enumeration<String> enumParams = req.getParameterNames();
        
        while (enumParams.hasMoreElements()) {
        	String strNomParam = enumParams.nextElement();
        	out.print("<tr><td>" + strNomParam + "</td>");
        	String[] tabstrValParam = req.getParameterValues(strNomParam);
        	if (tabstrValParam.length == 1) {
        		String strValeurParam = tabstrValParam[0];
        		if (strValeurParam.length() == 0)
        			out.println("<td>Pas de valeur</td>");
        		else
        			out.println("<td>" + strValeurParam + "</td>");
        	}
        	else
        	{
        		out.println("<td>");
        		out.println("<ul>");
        		for (int i=0; i<tabstrValParam.length; i++)
        			out.println("<li>" + tabstrValParam[i] + "</li>");
        		
        		out.println("</ul>");
        		out.println("</td>");
        	}
        	out.println("</tr>");
        } 
        out.println("</table>\n");
    
	    // 
        // R�cup�ration de l'ensemble des param�tres
        // Utilisation getParameterMap()
        //
        
     	Map<String, String[]> hMap = new HashMap<>();
     	
     	hMap = req.getParameterMap();

	    out.println("<table border=\"1\" align=left>\n");
	    out.println("<th bgcolor=\"#EECC00\">Nom du param�tre</th><th bgcolor=\"#EECC00\">Valeur(s) du param�tre</th>");
	    out.println("<tr bgcolor=\"#EECC00\"><td colspan=\"2\">Utilisation de getParameterMap()</td></tr>");
     	
        // Parcourir la map
     	for (Map.Entry<String, String[]> entry : hMap.entrySet()) {

     		// R�cup�rer la cl� et la valeur
     		String key = entry.getKey().toUpperCase();
     		String[] value = entry.getValue();
     			
     		out.println("<tr>");
     			
     		// R�cup�rer le titre 
     		out.println("<td>" + key.toLowerCase() + "</td>");
     			
     		out.println("<td>");
     		
     		// R�cup�rer les valeurs
     		for (String val : value)
     				out.println(val);

     		out.println("</td></tr>");

     	}
	    out.println("</table>\n");
        
	    // 
        // R�cup�ration par le nom du param�tre avec getParameter()
        //
        
	    out.println("<table border=\"1\" align=left>\n");
	    out.println("<th bgcolor=\"#EECC00\">Nom du param�tre</th><th bgcolor=\"#EECC00\">Valeur(s) du param�tre</th>");
	    out.println("<tr bgcolor=\"#EECC00\"><td colspan=\"2\">Utilisation de getParameter()</td></tr>");
	    
	    // R�cup�ration du genre  (combo box -> simple s�lection)
	    // sgenre = contenu de l'attribut value <option value="masculin">Masculin</option>
	    String sgenre = req.getParameter("genre");
	    out.print("<tr><td>Genre : </td><td>" + sgenre + "</td>");
	    
	    // R�cup�ration du nom (champ texte -> simple s�lection)
	    String snom = req.getParameter("nom");
	    out.print("<tr><td>Nom : </td><td>" + snom + "</td>");
	    
	    // R�cup�ration du pr�nom (champ texte -> simple s�lection)
	    String sprenom = req.getParameter("prenom");
	    out.print("<tr><td>Pr�nom : </td><td>" + sprenom + "</td>");
	    
	    // R�cup�ration du t�l�phone (champ texte -> simple s�lection)
	    // stelephone = contenu de l'attribut value 
	    String stelephone = req.getParameter("telephone");
	    out.print("<tr><td>T�l�phone : </td><td>" + stelephone + "</td>");
	    
	    // R�cup�ration de la pr�f�rence (cases � cocher -> multi s�lection)
	    String [] tabval = req.getParameterValues("preference");
	    
	    // Construire la r�ponse sous forme de liste � bullets
	    out.print("<tr><td>Preference : </td>");
		out.println("<td>");
		out.println("<ul>");
		
		// Parcourir le tableau des valeurs
		// Valeur = on ou off si pas d'attribut value dans la balise
		if (tabval != null)
			for (int i=0; i<tabval.length; i++)
				out.println("<li>" + tabval[i] + "</li>");
		
	    // R�cup�ration du centre (combo box -> simple s�lection)
	    String scentre = req.getParameter("centre");
	    out.print("<tr><td>Centre : </td><td>" + scentre + "</td>");
	    
	    // R�cup�ration de la fonction (combo box -> simple s�lection)
	    // <option value="animateur">Animateur</option>
	    String sfonction = req.getParameter("fonction");
	    out.print("<tr><td>Fonction : </td><td>" + sfonction + "</td>");
    
	    out.println("</table>\n");
    
	    // Ecrire la fin de la page HTML renvoy�e
	    Utils.ecritFin(out);
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
	}
  
	// SI requ�te de type POST -> appeler doProcess()
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	  		throws ServletException, IOException {
			doProcess(req, res);
	}
		
	// SI requ�te de type GET -> appeler doProcess() 
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	  		throws ServletException, IOException {
			doProcess(req, res);
	}
}