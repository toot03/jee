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
		

		// Positionnement du type Mime du corps de la r�ponse
		res.setContentType("text/html");
		
		// Possibilit� de positionnner aussi le type d'encodage de caract�res
		//res.setContentType("text/html;charset=UTF-8");
		
		// Cr�er un objet PrinterWriter permettant d'�crire des donn�es au format texte dans
		// la r�ponse
	    PrintWriter out = res.getWriter();
	    
	    // Ecriture de l'ent�te de la page HTML retourn�e
	    Utils.ecrireDebut(out,"Les param�tres re�us :");
	    
	    //
	    // R�cup�ration des param�tres avec getParameter(nom)
	    //
	    
	    out.println("<table border=\"1\" align=left>\n" +
        "<th bgcolor=\"#EECC00\">Nom du param�tre</th>" +
        "<th bgcolor=\"#EECC00\">Valeur(s) du param�tre</th>");
	    out.print("<tr bgcolor=yellow><td colspan=2 >AVEC getParameter(nom)</td></tr>");
	    
	    // R�cup�ration de la valeur du param�tre nomm� "firstname"
	    String valfirstname = req.getParameter("firstname");

	    out.print("<tr><td>firstname</td>");
	    out.print("<td>" + valfirstname + "</td></tr>");
	    
	    // R�cup�ration des  valeurs du param�tre nomm� "preference"
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
	    // R�cup�ration des param�tres avec getParameterNames()
	    //
	    
	    out.println("<table border=\"1\" align=left>\n" +
	                "<th bgcolor=\"#EECC00\">Nom du param�tre</th><th bgcolor=\"#EECC00\">Valeur(s) du param�tre</th>");
	    out.print("<tr bgcolor=yellow><td colspan=2 >AVEC getParameterNames()</td></tr>");
	    
	    // R�cup�rer les noms des param�tres envoy�s par le navigateur dans la requ�te
	    Enumeration<String> enumParams = req.getParameterNames();
	    
	    // TANT Qu'il y a des param�tres
	    while (enumParams.hasMoreElements()) {
	    	
	    	// R�cup�rer le nom du param�tre
	    	String strNomParam = enumParams.nextElement();
	    	
	    	out.print("<tr><td>" + strNomParam + "</td>");
	    	
	    	// R�cup�rer les valeurs du param�tre dans un tableau de String
	    	String[] tabstrValParam = req.getParameterValues(strNomParam);
	    	
	    	// SI une seule valeur
	    	if (tabstrValParam.length == 1) {
	    		// R�cup�rer la valeur
	    		String strValeurParam = tabstrValParam[0];
	    		
	    		// SI pas de valeur
	    		if (strValeurParam.length() == 0)
	    			// Indiquer Pas de valeur dans la cellule
	    			out.println("<td>Pas de valeur</td>");
	    		else
	    			// Mettre la valeur du param�tre dans la cellule
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
	    } // FIN TANT Qu'il y a des param�tres
    
	    out.println("</table>");
    
	    //
	    // R�cup�ration des param�tres d'initialisation  avec getInitParameterNames()
	    //
	    
	    out.println("<table border=\"1\" align=left>\n" +
	            "<th bgcolor=\"#EECC11\">Nom du param�tre init</th><th bgcolor=\"#EECC11\">Valeur(s) du param�tre init</th>");
	    out.print("<tr bgcolor=yellow><td colspan=2 >AVEC getInitParameterNames()</td></tr>");
	    
	    // R�cup�rer les noms des param�tres pr�sents dans le fichier web.xml
	    Enumeration<String> enumInitParams = cfg.getInitParameterNames();
	    
	    // Parcourir l'�num�ration
	    while(enumInitParams.hasMoreElements()) {
	    		      String name = enumInitParams.nextElement();
	    		      out.println("<tr><td>"+ name + "</td><td>" + cfg.getInitParameter(name) + "</td></tr>");
	     }
	    out.println("</table>");
    
	    // R�cup�ration � partir du nom
	    //out.println("<tr><td>param1</td><td>" + cfg.getInitParameter("param1") + "</td></tr>");
	    //out.println("<tr><td>param1</td><td>" + cfg.getInitParameter("param2") + "</td></tr>");
	    
	    //
	    // R�cup�ration des param�tres avec getParameterMap()
	    //
	    
	    out.println("<table border=\"1\" align=left>\n" +
	            "<th bgcolor=\"#EECC11\">Nom du param�tre</th><th bgcolor=\"#EECC11\">Valeur(s) du param�tre</th>");
	    
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
	    		// Affiche une virgule apr�s chaque multiple valeur
	    		// Except� pour la derni�re
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

	// Transf�rer le traitement � doProcess(req, res);
	public void doPost(HttpServletRequest req,HttpServletResponse res)
      	throws ServletException, IOException {
	  doProcess(req, res);
	}
  
	// Transf�rer le traitement � doProcess(req, res);
	public void doGet(HttpServletRequest req,HttpServletResponse res)
  		throws ServletException, IOException {
		doProcess(req, res);
	}
}