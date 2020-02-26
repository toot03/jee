package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilitaires.Utils;

@SuppressWarnings("serial")
public class HttpSessionServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		// Entier utilis� comme attribut de session
		Integer count;
		
		// Positionnement du type Mime du corps de la r�ponse
		res.setContentType("text/html");
		
		// Cr�er un objet PrinterWriter permettant d'�crire des donn�es au format texte dans
		// la r�ponse
		PrintWriter out = res.getWriter();
		
		// R�cup�rer ou cr�er l�objet session associ� � la requ�te cliente
		// Param�tre : true ou rien = la session est r�cup�r�e ou cr��e automatiquement si elle n�existe pas
		HttpSession session = req.getSession();
		
		System.out.println ("ID de session : "  + session.getId());
		
		// Retourne la valeur de l'attribut "count" de la session pr�c�demment ajout�
		count = (Integer)session.getAttribute("count");
		
		// SI aucun attribut "count" associ� � la session
		if (count == null)
			// Cr�er l'attribut avec une valeur de 1
			count = new Integer(1);
		// SINON un attribut "count" est associ� � la session
		else
			// Incr�menter la valeur
			count = new Integer(count.intValue() + 1);
		
		// Associer l'objet count � la session
		session.setAttribute("count", count);
		
	    // Ecriture de l'ent�te de la page HTML retourn�e
	    Utils.ecritDebut(out,"LabSERVLET_04-Session");
	    
		// Ecrire dans la flux pour afficher le compteur
		out.println("Vous avez visit� cette page " + count + " fois.");
		
	    // Ecrire la fin de la page HTML retourn�e
	    Utils.ecritFin(out);
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
	}
}