package pack1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utilitaires.Utils;

/**

 * @author Pascal
 *
 */
public class ServletEcritCookie extends HttpServlet
{

	private static final long serialVersionUID = -3052783038354938451L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException
    {
		Cookie cookie;
		
		// Nombre de cookies à créer
		final int nbCookies = 60;
		
		// Cookies sont rattachés au nom du domaine (ex : localhost) 
		// et au path (= contexte) (ex : LabSERVLET_03-Cookies)
	    for(int i=1; i <= nbCookies; i++) 
	    {
	      // Par défaut maxAge est -1, dans ce cas, le cookie est
	      // valide uniquement pour la session en cours du
	      // navigateur
	      cookie = new Cookie("Cookie_Session_" + i,"V_S_" + i);
	      
	      // Pas de maxAge (maxAge = -1)
	      res.addCookie(cookie);	      
	      
	      // Créer le cookie en tant qu'objet
	      cookie = new Cookie("Cookie_Persistant_" + i,"V_P_" + i);
	      
	      // le cookie est valide 1 heure (3600 sec), même si l'utilisateur
	      // quitte le navigateur, redemarre l'ordinateur, ...
	      cookie.setMaxAge(3600);
	      
	      // Ajouter le cookie à la réponse
	      res.addCookie(cookie);
	    }
	    
	    // Positionner le type mime du corps de la réponse qui sera envoyée au navigateur
	    res.setContentType("text/html");
    
	    // Récupération d'un printWriter (package java.io)
	    PrintWriter out = res.getWriter();
	    
	    int size = res.getBufferSize();
	    res.setBufferSize(15000);
	    
	    // Ecriture de l'entête de la page HTML retournée
	    Utils.ecrireDebut(out,"Servlet et Cookies");
	    
	    out.println(
	       "<p>Il y a " + nbCookies +" cookies associes à cette page.</p>" +
	       "<p>Taille du buffer " + size +"</p>" +
	       "<p>Pour les visualiser, cliquez ici\n" +
	       "<a href=\"Servlet2\"> Executer ServletLitCookie</a>.</p>" +
	       "<p>\n" +
	       "Si vous quittez le navigateur et activez la servlet " +
	       "ServletlitCookie, vous ne verrez que les cookies persistants.</p>\n");
	    	    
	    // Ecriture de la fin de la page HTML retournée
	    Utils.ecrireFin(out);
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
  }
}