package controleur.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Les objet commande h�ritent de l�interface ICommand, qui est une classe avec 
 * une m�thode execute() qui n�est pas cod�e. 
 * Pour chaque traitement que l�on veut effectuer, on va cr�er un objet commande
 * impl�mentant ICommand, dont on ira d�finir la m�thode execute().
 * @author pascal & bob
 *
 */
public interface ICommand{

    public void execute(HttpServletRequest request, 
                        HttpServletResponse response) 
                    throws ServletException, IOException;
}
