<jsp:useBean id="modelBean" class="modele.ModeleBean" scope="request" />
<html>
  <head>
  	<meta charset="utf-8">
    <title>Simple Pattern Command</title>
  </head>
  <body>
    <h2>Simple Pattern Command</h2>  
    <form method="post" action="controller.do">
      <input type="hidden" name="form_action" value="write" />
      <table>
        <tr>
          <td>Prénom :</td>
          <td>
            <input type="text" 
                   name="first_name" 
                   value="<jsp:getProperty name="modelBean" property="firstName" />" />
          </td>
        </tr>
        <tr>
          <td>Nom :</td>
          <td>
            <input type="text" 
                   name="last_name" 
                   value="<jsp:getProperty name="modelBean" property="lastName" />" />
          </td>
        </tr>
        <tr>
          <td>Mail :</td>
          <td>
            <input type="text" 
                   name="email" 
                   value="<jsp:getProperty name="modelBean" property="email" />" />
          </td>
        </tr>
        <tr>
          <td>Téléphone :</td>
          <td>
            <input type="text" 
                   name="phone"
                   value="<jsp:getProperty name="modelBean" property="phone" />" />
          </td>
        </tr>
        <tr>
          <td>
            <input type="submit" name="enter_button" value="Valider" />
          </td>
          <td>
          </td>
        </tr>
      </table>
    </form>
    
    <!-- Apple de la méthode getMessage() de la classe ModelBean -->
    <pre>
      <jsp:getProperty name="modelBean" property="message" />
    </pre>
    
  </body>
</html>
