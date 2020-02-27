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
		
		// Déclaration de la variable de session
		private HttpSession m_session;

		public void doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		// Récupérer ou créer l’objet session associé à la requête cliente
		// Paramètre : true ou rien = la session est récupérée ou créée automatiquement si elle n’existe pas
		m_session = req.getSession(true);

		// Positionnement du type Mime du corps de la réponse
		res.setContentType("text/html");
		
		// Récupération d'un printWriter (package java.io)
		PrintWriter out = res.getWriter();
		
		// Lire le paramètre "identifiant" contenu dans la requête
		// Associer l'objet identifiant à la session
        m_session.setAttribute("identifiant",req.getParameter("identifiant"));
   
		// Lire le paramètre "motdepasse" contenu dans la requête
		// Associer l'objet motdepasse à la session
        m_session.setAttribute("motdepasse",req.getParameter("motdepasse"));
  
        // Ecriture de l'entête de la page HTML retournée
        Utils.ecritDebut(out,"Les paramètres du formulaire :");
        
        out.println("<table border=\"1\" align=left>\n" +
                "<th bgcolor=\"#EECC00\">Nom du paramètre</th><th bgcolor=\"#EECC00\">Valeur(s) du paramètre</th>");
        out.println("<tr bgcolor=\"#EECC00\"><td colspan=\"2\">Utilisation de getParameterNames()</td></tr>");
    
	    // 
        // Récupération de l'ensemble des paramètres
        // Utilisation de getParameterNames()
        //
        
        // Récupération des paramètres de la requête
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
        // Récupération de l'ensemble des paramètres
        // Utilisation getParameterMap()
        //
        
     	Map<String, String[]> hMap = new HashMap<>();
     	
     	hMap = req.getParameterMap();

	    out.println("<table border=\"1\" align=left>\n");
	    out.println("<th bgcolor=\"#EECC00\">Nom du paramètre</th><th bgcolor=\"#EECC00\">Valeur(s) du paramètre</th>");
	    out.println("<tr bgcolor=\"#EECC00\"><td colspan=\"2\">Utilisation de getParameterMap()</td></tr>");
     	
        // Parcourir la map
     	for (Map.Entry<String, String[]> entry : hMap.entrySet()) {

     		// Récupérer la clé et la valeur
     		String key = entry.getKey().toUpperCase();
     		String[] value = entry.getValue();
     			
     		out.println("<tr>");
     			
     		// Récupérer le titre 
     		out.println("<td>" + key.toLowerCase() + "</td>");
     			
     		out.println("<td>");
     		
     		// Récupérer les valeurs
     		for (String val : value)
     				out.println(val);

     		out.println("</td></tr>");

     	}
	    out.println("</table>\n");
        
	    // 
        // Récupération par le nom du paramètre avec getParameter()
        //
        
	    out.println("<table border=\"1\" align=left>\n");
	    out.println("<th bgcolor=\"#EECC00\">Nom du paramètre</th><th bgcolor=\"#EECC00\">Valeur(s) du paramètre</th>");
	    out.println("<tr bgcolor=\"#EECC00\"><td colspan=\"2\">Utilisation de getParameter()</td></tr>");
	    
	    // Récupération du genre  (combo box -> simple sélection)
	    // sgenre = contenu de l'attribut value <option value="masculin">Masculin</option>
	    String sgenre = req.getParameter("genre");
	    out.print("<tr><td>Genre : </td><td>" + sgenre + "</td>");
	    
	    // Récupération du nom (champ texte -> simple sélection)
	    String snom = req.getParameter("nom");
	    out.print("<tr><td>Nom : </td><td>" + snom + "</td>");
	    
	    // Récupération du prénom (champ texte -> simple sélection)
	    String sprenom = req.getParameter("prenom");
	    out.print("<tr><td>Prénom : </td><td>" + sprenom + "</td>");
	    
	    // Récupération du téléphone (champ texte -> simple sélection)
	    // stelephone = contenu de l'attribut value 
	    String stelephone = req.getParameter("telephone");
	    out.print("<tr><td>Téléphone : </td><td>" + stelephone + "</td>");
	    
	    // Récupération de la préférence (cases à cocher -> multi sélection)
	    String [] tabval = req.getParameterValues("preference");
	    
	    // Construire la réponse sous forme de liste à bullets
	    out.print("<tr><td>Preference : </td>");
		out.println("<td>");
		out.println("<ul>");
		
		// Parcourir le tableau des valeurs
		// Valeur = on ou off si pas d'attribut value dans la balise
		if (tabval != null)
			for (int i=0; i<tabval.length; i++)
				out.println("<li>" + tabval[i] + "</li>");
		
	    // Récupération du centre (combo box -> simple sélection)
	    String scentre = req.getParameter("centre");
	    out.print("<tr><td>Centre : </td><td>" + scentre + "</td>");
	    
	    // Récupération de la fonction (combo box -> simple sélection)
	    // <option value="animateur">Animateur</option>
	    String sfonction = req.getParameter("fonction");
	    out.print("<tr><td>Fonction : </td><td>" + sfonction + "</td>");
    
	    out.println("</table>\n");
    
	    // Ecrire la fin de la page HTML renvoyée
	    Utils.ecritFin(out);
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
	}
  
	// SI requête de type POST -> appeler doProcess()
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	  		throws ServletException, IOException {
			doProcess(req, res);
	}
		
	// SI requête de type GET -> appeler doProcess() 
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	  		throws ServletException, IOException {
			doProcess(req, res);
	}
}