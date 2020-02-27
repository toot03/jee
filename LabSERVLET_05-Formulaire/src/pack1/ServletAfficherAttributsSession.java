package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utilitaires.Utils;


public class ServletAfficherAttributsSession extends HttpServlet {

	private static final long serialVersionUID = 8513567765539734747L;
	
	// D�claration de la variable de session
	private HttpSession m_session;

	public void doProcess (HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{
		String strIdentifiant, strmotdepasse;

		// R�cup�rer ou cr�er l�objet session associ� � la requ�te cliente
		// Param�tre : true ou rien = la session est r�cup�r�e ou cr��e automatiquement si elle n�existe pas
		m_session = req.getSession();
		
		// Positionnement du type Mime du corps de la r�ponse
		res.setContentType("text/html");
		
		// R�cup�ration d'un printWriter (package java.io)
		PrintWriter out = res.getWriter();
		
		// Ecrire le d�but de la page HTML retourn�e
		Utils.ecritDebut(out,"Tentative de r�cup�ration de 2 objets de la session.");
		
		// Lire le param�tre "identifiant" contenu dans la session
		strIdentifiant=(String)m_session.getAttribute("identifiant");
		
		// SI il y a un identifiant
		if (strIdentifiant != null) {
			out.println("Voici ce que la servlet 2 a r�cup�r� de la session :<br>");
			out.println("\tIdentifiant : " +strIdentifiant+"<br />");
		}
		// SINON pas d'identifiant
		else
			out.println("La servlet n'a rien r�cup�r� de la session<br>");
		
		// Lire le param�tre "motdepasse" contenu dans la session
		strmotdepasse=(String)m_session.getAttribute("motdepasse");
		
		// SI il y a un mot de passe
		if (strmotdepasse != null) {
			out.println("\tMot de passe : " +strmotdepasse+"<br />");
		}
    
		// Ecrire la fin de la page HTML retourn�e
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