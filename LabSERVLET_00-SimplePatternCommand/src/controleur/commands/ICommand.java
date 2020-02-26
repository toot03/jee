package controleur.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Les objet commande héritent de l’interface ICommand, qui est une classe avec 
 * une méthode execute() qui n’est pas codée. 
 * Pour chaque traitement que l’on veut effectuer, on va créer un objet commande
 * implémentant ICommand, dont on ira définir la méthode execute().
 * @author pascal & bob
 *
 */
public interface ICommand{

    public void execute(HttpServletRequest request, 
                        HttpServletResponse response) 
                    throws ServletException, IOException;
}
