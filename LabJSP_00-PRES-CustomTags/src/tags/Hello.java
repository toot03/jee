package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.*;

@SuppressWarnings("serial")
public class Hello extends TagSupport{
  public int doStartTag() throws JspException {
    try {  
    	pageContext.getOut().print("Hello");
    } catch (Exception ex) { 
    	throw new JspException("Probleme I/O"); 
    } 
    
    return SKIP_BODY; 
  } 
}