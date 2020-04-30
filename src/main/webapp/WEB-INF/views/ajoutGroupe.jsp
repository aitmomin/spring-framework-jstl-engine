<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>L'ajout d'un groupe</title>
</head>
<body>
	<table>
		<tr>
			<td><jsp:include page="index.jsp" flush="true" /></td>
			<td><f:form modelAttribute="groupeModel" method="post"
					action="ajoutGroupe">
					<table>
						<tr>
							<td>Nom de Groupe:</td>
							<td><f:input path="nomGroupe" /></td>
						<tr>
							<td><input type="submit" value="Ajouter le groupe"><input
								type="reset" value="Effacer" /></td>
						</tr>
					</table>
				</f:form></td>
		</tr>
	</table>
</body>
</html>