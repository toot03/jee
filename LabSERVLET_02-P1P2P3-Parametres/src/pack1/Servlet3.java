package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import utilitaires.Utils;

@WebServlet(urlPatterns="/Servlet3"
,initParams=@WebInitParam(name="param5",value="Valeur de param5"))
public class Servlet3 extends HttpServlet
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
	    Utils.ecrireDebut(out,"Paramètres dans annotation");
	    
	    out.println("<p> Utilisation de getInitParameter()</p>");
	    out.println("<p> Utilisation de getInitParameterNames()</p>");
	    
	    out.println("<table border=\"1\" align=left>\n" +
	                "<tr bgcolor=\"#EECC00\">\n" +
	                "<th>Nom du paramètre</th><th>Valeur(s) du paramètre</th>");
	    
	    //
	    // Lecture du paramètre nommé param5 défini dans l'annotation
	    //   
	    out.println("<tr><td>param5</td><td>" + cfg.getInitParameter("param5") + "</td></tr>");
	    
	    //
	    // Lecture de tous les paramètres de la servlet
	    //
	    Enumeration<String> enumParams = cfg.getInitParameterNames();
	    
	    // TANT QU'IL y a des paramètres
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