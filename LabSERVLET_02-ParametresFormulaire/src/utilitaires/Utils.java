package utilitaires;

import java.io.PrintWriter;

public class Utils {
	public static final String DOCTYPE = "<!DOCTYPE html>";

	// Ecriture du d�but de la page HTML
	// Titre de la page pass� en param�tre de la m�thode
	public static void ecrireDebut(PrintWriter p, String titre) {
		p.print(DOCTYPE + "\n" +
				"<html lang=\"fr\">\n" +
				"<head>\n" +
				"<meta charset=\"utf-8\">\n" +
				"<title>" + titre + "</title>\n</head>\n<body>\n");
	}

	// Ecriture de la fin de la page HTML
	public static void ecrireFin(PrintWriter p) {
		p.print("</body>\n</html>");
	}  
}