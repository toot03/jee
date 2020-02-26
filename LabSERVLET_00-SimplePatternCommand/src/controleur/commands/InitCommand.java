package controleur.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import modele.ModeleBean;
import java.io.IOException;

// InitCommand impl�mente l'interface ICommand et 
// doit donc d�finir la m�thode execute()
public class InitCommand implements ICommand{

    public void execute(HttpServletRequest request, 
                        HttpServletResponse response) 
                    throws ServletException, IOException{

        request.setAttribute("modelBean", new ModeleBean());

        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/view.jsp").forward(request, response);
    }
}
