<%@ taglib prefix="hwa" uri="WEB-INF/tld/HelloWithAttribute.tld"%>
<%@ taglib prefix="h" uri="WEB-INF/tld/Hello.tld"%>

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>LabJSP_00-PRES-BALISESPERSONNALISEES</title>
</head>

<body>
<p><h:hello/></p>

<p><hwa:HelloWithAttribute message="Bob"/></p>

<p><hwa:HelloWithAttribute>Hello sans attribut message</hwa:HelloWithAttribute></p>

</body>
</html>