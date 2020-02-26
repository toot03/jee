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
		
		// Entier utilisé comme attribut de session
		Integer count;
		
		// Positionnement du type Mime du corps de la réponse
		res.setContentType("text/html");
		
		// Créer un objet PrinterWriter permettant d'écrire des données au format texte dans
		// la réponse
		PrintWriter out = res.getWriter();
		
		// Récupérer ou créer l’objet session associé à la requête cliente
		// Paramètre : true ou rien = la session est récupérée ou créée automatiquement si elle n’existe pas
		HttpSession session = req.getSession();
		
		System.out.println ("ID de session : "  + session.getId());
		
		// Retourne la valeur de l'attribut "count" de la session précédemment ajouté
		count = (Integer)session.getAttribute("count");
		
		// SI aucun attribut "count" associé à la session
		if (count == null)
			// Créer l'attribut avec une valeur de 1
			count = new Integer(1);
		// SINON un attribut "count" est associé à la session
		else
			// Incrémenter la valeur
			count = new Integer(count.intValue() + 1);
		
		// Associer l'objet count à la session
		session.setAttribute("count", count);
		
	    // Ecriture de l'entête de la page HTML retournée
	    Utils.ecritDebut(out,"LabSERVLET_04-Session");
	    
		// Ecrire dans la flux pour afficher le compteur
		out.println("Vous avez visité cette page " + count + " fois.");
		
	    // Ecrire la fin de la page HTML retournée
	    Utils.ecritFin(out);
	    
	    // Vider le flux
	    out.flush();
	    
	    // Fermeture du flux
	    out.close();
	}
}