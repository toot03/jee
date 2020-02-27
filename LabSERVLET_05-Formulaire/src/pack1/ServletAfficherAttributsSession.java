package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utilitaires.Utils;


public class ServletAfficherAttributsSession extends HttpServlet {

	private static final long serialVersionUID = 8513567765539734747L;
	
	// Déclaration de la variable de session
	private HttpSession m_session;

	public void doProcess (HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{
		String strIdentifiant, strmotdepasse;

		// Récupérer ou créer l’objet session associé à la requête cliente
		// Paramètre : true ou rien = la session est récupérée ou créée automatiquement si elle n’existe pas
		m_session = req.getSession();
		
		// Positionnement du type Mime du corps de la réponse
		res.setContentType("text/html");
		
		// Récupération d'un printWriter (package java.io)
		PrintWriter out = res.getWriter();
		
		// Ecrire le début de la page HTML retournée
		Utils.ecritDebut(out,"Tentative de récupération de 2 objets de la session.");
		
		// Lire le paramètre "identifiant" contenu dans la session
		strIdentifiant=(String)m_session.getAttribute("identifiant");
		
		// SI il y a un identifiant
		if (strIdentifiant != null) {
			out.println("Voici ce que la servlet 2 a récupéré de la session :<br>");
			out.println("\tIdentifiant : " +strIdentifiant+"<br />");
		}
		// SINON pas d'identifiant
		else
			out.println("La servlet n'a rien récupéré de la session<br>");
		
		// Lire le paramètre "motdepasse" contenu dans la session
		strmotdepasse=(String)m_session.getAttribute("motdepasse");
		
		// SI il y a un mot de passe
		if (strmotdepasse != null) {
			out.println("\tMot de passe : " +strmotdepasse+"<br />");
		}
    
		// Ecrire la fin de la page HTML retournée
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