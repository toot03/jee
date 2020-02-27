package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utilitaires.Utils;


public class ServletLogin extends HttpServlet {
 
	private static final long serialVersionUID = -1031966908477155121L;
	
	// D�claration de la variable de session
	private HttpSession m_session;

	public void doProcess (HttpServletRequest req, HttpServletResponse res)
      	throws ServletException, IOException
    {
		// R�cup�rer ou cr�er l�objet session associ� � la requ�te cliente
		// Param�tre : true ou rien = la session est r�cup�r�e ou cr��e automatiquement si elle n�existe pas
		m_session = req.getSession();
		
		// Timeout de 5 sec -> ServletAfficherAttributsSession ne r�cup�re rien
		// m_session.setMaxInactiveInterval(5);
		// Pas de timeout
		m_session.setMaxInactiveInterval(-1);

		// Positionnement du type Mime du corps de la r�ponse
		res.setContentType("text/html");
		
		// R�cup�ration d'un printWriter (package java.io)
		PrintWriter out = res.getWriter();
		
		// Lire le param�tre "identifiant" contenu dans la requ�te
		// Correspond au nom de la propri�t� name de la balise input
		// <input type="text" name="identifiant" class="input" maxlength="50" />
		String sidentifiant = req.getParameter("identifiant");
		
		// Associer l'objet identifiant � la session
        m_session.setAttribute("identifiant",sidentifiant);
   
		// Lire le param�tre "motdepasse" contenu dans la requ�te
		// Correspond au nom de la propri�t� name de la balise input
        // <input type="password" name="motdepasse" class="input" maxlength="50" />
        String smotdepasse = req.getParameter("motdepasse");
        
        // Associer l'objet motdepasse � la session
        m_session.setAttribute("motdepasse",smotdepasse);
  
        // Ecrire le d�but de la page HTML
        Utils.ecritDebut(out,"Suivi de session dans les servlets");
        
        // Ecrire le contenu de la page HTML
        // Contient un lien sur la servlet ServletAfficherAttributsSession
        out.println(
		       "Cette servlet a li� 2 objets � la session :<br>" +
		       "   Un String pour le nom de l'utilisateur<br>" +
		       "   Un String pour le mot de passe de l'utilisateur<br>" +
		       "Pour les voir, cliquez ici\n" +
		       "<a href=\"ServletAfficherAttributsSession\">\n" +
		       "ServletAfficherAttributsSession </a>.\n" +
		       "<p>Si vous quittez le navigateur et activez la servlet "+
		       "ServletAfficherAttributsSession, vous verrez que la servlet ne r�cup�re rien de la session. </p>");
        
        // Ecrire la fin de la page HTML renvoy�e
        Utils.ecritFin(out);
        
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