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
public class ControleurServlet extends HttpServlet{
        
	// Collection de commandes
	// Remplie lors de la phase d'initialisation de la servlet
    private Map<String, ICommand> commands = new HashMap<String, ICommand>();
    
    public void init(){
    	
    	// Ajouter les commandes � la collection de commandes
        this.commands.put("init",  new InitCommand());
        this.commands.put("write", new WriteCommand());
    }

    public void processCommand(HttpServletRequest  request, 
                               HttpServletResponse response) 
                           throws ServletException, IOException{

		// Obtenir l'action � ex�cuter pass�e dans l'url (GET) ou dans le corps de la requ�te (POST)
        String formAction = request.getParameter("form_action");

        // Si pas d'action sp�cifi�e, init par d�faut 
        if (formAction == null)
            formAction = "init";

        // R�cup�rer la commande dans la table des commandes
        ICommand command = (ICommand)commands.get(formAction);

        // SI il n'existe pas de commande correspondante dans la table des commandes
        if(command == null){
        	// Lever une exception
            throw new IllegalArgumentException(
                "No command for form action : " + formAction);
        }
        
        // Ex�cuter la commande correspondante
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
