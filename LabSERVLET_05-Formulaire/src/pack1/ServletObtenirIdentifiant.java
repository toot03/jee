package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utilitaires.Utils;


public class ServletObtenirIdentifiant extends HttpServlet {
 
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
		
		// Lire le paramètre "courriel" contenu dans la requête
		// Associer l'objet courriel à la session
        m_session.setAttribute("courriel",req.getParameter("courriel"));
   
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