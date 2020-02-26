package controleur;

import javax.servlet.*;
import javax.servlet.http.*;
import controleur.commands.ICommand;
import controleur.commands.InitCommand;
import controleur.commands.WriteCommand;
import java.io.*;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("serial")
public class ControleurServlet_v2 extends HttpServlet{
        
	// Tableau des commandes
	// Rempli lors de la phase d'initialisation de la servlet : méthode init()
	private Map<String, Class<?>> commands = new HashMap<String, Class<?>>();
	
    public void init(){
    	     
    	// Ajouter les commandes à la collection
     	commands.put("login", InitCommand.class);
     	commands.put("logout", WriteCommand.class);
    }

    public void processCommand(HttpServletRequest  request, 
                               HttpServletResponse response) 
                           throws ServletException, IOException {

    	ICommand command = null;
    	
		// Obtenir l'action à exécuter passée dans l'url (GET) ou dans le corps de la requête (POST)
        String formAction = request.getParameter("form_action");

        // Si pas d'action spécifiée, init par défaut 
        if (formAction == null)
            formAction = "init";

		// Récupérer la classe commande correspondante à l'action demandée
		Class<?> classCommand = (Class<?>)commands.get(formAction);

        // SI il n'existe pas de commande correspondante dans la table des commandes
        if(classCommand == null){
        	// Lever une exception
            throw new IllegalArgumentException(
                "No command for form action : " + formAction);
        }
        
    	// Créer une instance de la commande
		try {
			command = (ICommand)classCommand.newInstance();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
        
        // Exécuter la commande correspondante
        command.execute(request, response);
    }

    public void doPost(HttpServletRequest  request, 
                       HttpServletResponse response) 
                  throws ServletException, IOException{

        processCommand(request, response);
    }

    public void doGet(HttpServletRequest  request, 
                      HttpServletResponse response) 
                  throws ServletException, IOException{

        processCommand(request, response);
    }
}
