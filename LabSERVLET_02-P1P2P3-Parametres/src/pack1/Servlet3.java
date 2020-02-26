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
		// Positionnement du type Mime du corps de la r�ponse
		res.setContentType("text/html");
	    
		// Cr�er un objet PrinterWriter permettant d'�crire des donn�es au format texte dans
		// la r�ponse
	    PrintWriter out = res.getWriter();
	    
	    // Ecriture de l'ent�te de la page HTML retourn�e
	    Utils.ecrireDebut(out,"Param�tres dans annotation");
	    
	    out.println("<p> Utilisation de getInitParameter()</p>");
	    out.println("<p> Utilisation de getInitParameterNames()</p>");
	    
	    out.println("<table border=\"1\" align=left>\n" +
	                "<tr bgcolor=\"#EECC00\">\n" +
	                "<th>Nom du param�tre</th><th>Valeur(s) du param�tre</th>");
	    
	    //
	    // Lecture du param�tre nomm� param5 d�fini dans l'annotation
	    //   
	    out.println("<tr><td>param5</td><td>" + cfg.getInitParameter("param5") + "</td></tr>");
	    
	    //
	    // Lecture de tous les param�tres de la servlet
	    //
	    Enumeration<String> enumParams = cfg.getInitParameterNames();
	    
	    // TANT QU'IL y a des param�tres
	    while (enumParams.hasMoreElements()) {
	    	// El�ment suivant
	    	String strNomParam = enumParams.nextElement();
	    	
	    	// Affichage du nom
	    	out.print("<tr><td>" + strNomParam + "</td>");
	    	
	    	// R�cup�ration de la valeur du param�tre
	    	String strValeurParam = cfg.getInitParameter(strNomParam);
	    	  
	    	out.println("<td>" + strValeurParam + "</td>");
	    	
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
				doProcess(req, res);
	}
	
	// SI requ�te de type POST -> appeler doProcess()
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			doProcess(req, res);
	}
}