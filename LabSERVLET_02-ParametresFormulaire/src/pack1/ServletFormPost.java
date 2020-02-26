package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import utilitaires.Utils;

public class ServletFormPost extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private ServletConfig cfg;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	     cfg = config;
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		

		// Positionnement du type Mime du corps de la réponse
		res.setContentType("text/html");
		
		// Possibilité de positionnner aussi le type d'encodage de caractères
		//res.setContentType("text/html;charset=UTF-8");
		
		// Créer un objet PrinterWriter permettant d'écrire des données au format texte dans
		// la réponse
	    PrintWriter out = res.getWriter();
	    
	    // Ecriture de l'entête de la page HTML retournée
	    Utils.ecrireDebut(out,"Les paramètres reçus :");
	    
	    //
	    // Récupération des paramètres avec getParameter(nom)
	    //
	    
	    out.println("<table border=\"1\" align=left>\n" +
        "<th bgcolor=\"#EECC00\">Nom du paramètre</th>" +
        "<th bgcolor=\"#EECC00\">Valeur(s) du paramètre</th>");
	    out.print("<tr bgcolor=yellow><td colspan=2 >AVEC getParameter(nom)</td></tr>");
	    
	    // Récupération de la valeur du paramètre nommé "firstname"
	    String valfirstname = req.getParameter("firstname");

	    out.print("<tr><td>firstname</td>");
	    out.print("<td>" + valfirstname + "</td></tr>");
	    
	    // Récupération des  valeurs du paramètre nommé "preference"
	    //  Attention : il peut y  avoir plusieurs valeurs (check box)
	    // --> utilisation de getParameterValues() qui retourne un tableau
	    String [] tabval = req.getParameterValues("preference");
	    String valfirstname1 = req.getParameter("preference");
	    
	    out.print("<tr><td>preference</td>");
		out.println("<td>");
		out.println("<ul>");
		
		// Parcourir le tableau des valeurs
		for (int i=0; i<tabval.length; i++)
			out.println("<li>" + tabval[i] + "</li>");
		
		out.println("</ul>");
		out.println("</td>");
		out.println("</tr>");
	    
	    out.println("</table>");
	    		   
	    //
	    // Récupération des paramètres avec getParameterNames()
	    //
	    
	    out.println("<table border=\"1\" align=left>\n" +
	                "<th bgcolor=\"#EECC00\">Nom du paramètre</th><th bgcolor=\"#EECC00\">Valeur(s) du paramètre</th>");
	    out.print("<tr bgcolor=yellow><td colspan=2 >AVEC getParameterNames()</td></tr>");
	    
	    // Récupérer les noms des paramètres envoyés par le navigateur dans la requête
	    Enumeration<String> enumParams = req.getParameterNames();
	    
	    // TANT Qu'il y a des paramètres
	    while (enumParams.hasMoreElements()) {
	    	
	    	// Récupérer le nom du paramètre
	    	String strNomParam = enumParams.nextElement();
	    	
	    	out.print("<tr><td>" + strNomParam + "</td>");
	    	
	    	// Récupérer les valeurs du paramètre dans un tableau de String
	    	String[] tabstrValParam = req.getParameterValues(strNomParam);
	    	
	    	// SI une seule valeur
	    	if (tabstrValParam.length == 1) {
	    		// Récupérer la valeur
	    		String strValeurParam = tabstrValParam[0];
	    		
	    		// SI pas de valeur
	    		if (strValeurParam.length() == 0)
	    			// Indiquer Pas de valeur dans la cellule
	    			out.println("<td>Pas de valeur</td>");
	    		else
	    			// Mettre la valeur du paramètre dans la cellule
	    			out.println("<td>" + strValeurParam + "</td>");
	    	}
	    	// SINON plusieurs valeurs
	    	else
	    	{
	    		out.println("<td>");
	    		out.println("<ul>");
	    		
	    		// Parcourir le tableau des valeurs
	    		for (int i=0; i<tabstrValParam.length; i++)
	    			out.println("<li>" + tabstrValParam[i] + "</li>");
	    		
	    		out.println("</ul>");
	    		out.println("</td>");
	    	}
	    	out.println("</tr>");
	    } // FIN TANT Qu'il y a des paramètres
    
	    out.println("</table>");
    
	    //
	    // Récupération des paramètres d'initialisation  avec getInitParameterNames()
	    //
	    
	    out.println("<table border=\"1\" align=left>\n" +
	            "<th bgcolor=\"#EECC11\">Nom du paramètre init</th><th bgcolor=\"#EECC11\">Valeur(s) du paramètre init</th>");
	    out.print("<tr bgcolor=yellow><td colspan=2 >AVEC getInitParameterNames()</td></tr>");
	    
	    // Récupérer les noms des paramètres présents dans le fichier web.xml
	    Enumeration<String> enumInitParams = cfg.getInitParameterNames();
	    
	    // Parcourir l'énumération
	    while(enumInitParams.hasMoreElements()) {
	    		      String name = enumInitParams.nextElement();
	    		      out.println("<tr><td>"+ name + "</td><td>" + cfg.getInitParameter(name) + "</td></tr>");
	     }
	    out.println("</table>");
    
	    // Récupération à partir du nom
	    //out.println("<tr><td>param1</td><td>" + cfg.getInitParameter("param1") + "</td></tr>");
	    //out.println("<tr><td>param1</td><td>" + cfg.getInitParameter("param2") + "</td></tr>");
	    
	    //
	    // Récupération des paramètres avec getParameterMap()
	    //
	    
	    out.println("<table border=\"1\" align=left>\n" +
	            "<th bgcolor=\"#EECC11\">Nom du paramètre</th><th bgcolor=\"#EECC11\">Valeur(s) du paramètre</th>");
	    
	    out.print("<tr bgcolor=yellow><td colspan=2 >AVEC getParameterMap()</td></tr>");
	    
	    // getParameterMap() retourne une map de type (String -> String[]) 
	    // Map mapParams = req.getParameterMap();
	    Map<String,String[]> mapParams = req.getParameterMap();
	    Iterator iter = req.getParameterMap().entrySet().iterator();
	    while (iter.hasNext()) {
	    	Map.Entry entry = (Map.Entry) iter.next();
	    	
	    	out.println("<tr><td>" + entry.getKey() + "</td>");
	    	String[] arr = (String[]) entry.getValue();
	    	out.println("<td>");
	    	for (int i=0;i<arr.length;i++) {
	    		out.println(arr[i]);
	    		// Affiche une virgule après chaque multiple valeur
	    		// Excepté pour la dernière
	    		if (i > 0 && i != arr.length-1)
	    			out.println(", ");
            }
            out.println("</td></tr>");
	    } // FIN while

	    out.println("</table>");
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
	}

	// Transférer le traitement à doProcess(req, res);
	public void doPost(HttpServletRequest req,HttpServletResponse res)
      	throws ServletException, IOException {
	  doProcess(req, res);
	}
  
	// Transférer le traitement à doProcess(req, res);
	public void doGet(HttpServletRequest req,HttpServletResponse res)
  		throws ServletException, IOException {
		doProcess(req, res);
	}
}