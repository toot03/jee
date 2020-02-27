package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utilitaires.Utils;


public class ServletObtenirIdentifiant extends HttpServlet {
 
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
		
		// Lire le param�tre "courriel" contenu dans la requ�te
		// Associer l'objet courriel � la session
        m_session.setAttribute("courriel",req.getParameter("courriel"));
   
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