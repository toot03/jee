package tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

/*
 * You can use various attributes along with your custom tags. 
 * To accept an attribute value, a custom tag class needs to 
 * implement the setter methods, identical to the JavaBean 
 * setter methods
 */
public class HelloWithAttribute extends SimpleTagSupport {
   private String message;
   StringWriter sw = new StringWriter();

   public void setMessage(String msg) {
      this.message = msg;
   }
   
   public void doTag() throws JspException, IOException {
      if (message != null) {
         /* Use message from attribute */
         JspWriter out = getJspContext().getOut();
         out.println("Hello " + message );
      } else {
         /* use message from the body */
         getJspBody().invoke(sw);
         getJspContext().getOut().println(sw.toString());
      }
   }
}