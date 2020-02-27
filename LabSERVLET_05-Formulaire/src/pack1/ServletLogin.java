package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utilitaires.Utils;


public class ServletLogin extends HttpServlet {
 
	private static final long serialVersionUID = -1031966908477155121L;
	
	// Déclaration de la variable de session
	private HttpSession m_session;

	public void doProcess (HttpServletRequest req, HttpServletResponse res)
      	throws ServletException, IOException
    {
		// Récupérer ou créer l’objet session associé à la requête cliente
		// Paramètre : true ou rien = la session est récupérée ou créée automatiquement si elle n’existe pas
		m_session = req.getSession();
		
		// Timeout de 5 sec -> ServletAfficherAttributsSession ne récupère rien
		// m_session.setMaxInactiveInterval(5);
		// Pas de timeout
		m_session.setMaxInactiveInterval(-1);

		// Positionnement du type Mime du corps de la réponse
		res.setContentType("text/html");
		
		// Récupération d'un printWriter (package java.io)
		PrintWriter out = res.getWriter();
		
		// Lire le paramètre "identifiant" contenu dans la requête
		// Correspond au nom de la propriété name de la balise input
		// <input type="text" name="identifiant" class="input" maxlength="50" />
		String sidentifiant = req.getParameter("identifiant");
		
		// Associer l'objet identifiant à la session
        m_session.setAttribute("identifiant",sidentifiant);
   
		// Lire le paramètre "motdepasse" contenu dans la requête
		// Correspond au nom de la propriété name de la balise input
        // <input type="password" name="motdepasse" class="input" maxlength="50" />
        String smotdepasse = req.getParameter("motdepasse");
        
        // Associer l'objet motdepasse à la session
        m_session.setAttribute("motdepasse",smotdepasse);
  
        // Ecrire le début de la page HTML
        Utils.ecritDebut(out,"Suivi de session dans les servlets");
        
        // Ecrire le contenu de la page HTML
        // Contient un lien sur la servlet ServletAfficherAttributsSession
        out.println(
		       "Cette servlet a lié 2 objets à la session :<br>" +
		       "   Un String pour le nom de l'utilisateur<br>" +
		       "   Un String pour le mot de passe de l'utilisateur<br>" +
		       "Pour les voir, cliquez ici\n" +
		       "<a href=\"ServletAfficherAttributsSession\">\n" +
		       "ServletAfficherAttributsSession </a>.\n" +
		       "<p>Si vous quittez le navigateur et activez la servlet "+
		       "ServletAfficherAttributsSession, vous verrez que la servlet ne récupère rien de la session. </p>");
        
        // Ecrire la fin de la page HTML renvoyée
        Utils.ecritFin(out);
        
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