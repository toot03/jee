package controleur.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import modele.ModeleBean;
import java.io.IOException;

// WriteCommand implémente l'interface ICommand et 
// doit donc définir la méthode execute()
public class WriteCommand implements ICommand{

    public void execute(HttpServletRequest request, 
                        HttpServletResponse response) 
                    throws ServletException, IOException{

        ModeleBean modelBean = new ModeleBean();

        modelBean.setFirstName(request.getParameter("first_name")); 
        modelBean.setLastName(request.getParameter("last_name")); 
        modelBean.setEmail(request.getParameter("email")); 
        modelBean.setPhone(request.getParameter("phone")); 

        request.setAttribute("modelBean", modelBean);

        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/view.jsp").forward(request, response);
    }
}