<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<title>Ajouter un client</title>
</head>
<body>
	<table>
		<tr>
			<td><jsp:include page="index.jsp" flush="true" /></td>
			<td><f:form modelAttribute="clientModel" method="post"	action="saveClient">
					<c:if test="${not empty clientModel.erreur}">
						<div class="errorblock">${clientModel.erreur}</div>
					</c:if>
					<f:errors path="*" element="div" cssClass="errorblock" />
					<table>
						<tr>
							<td>Nom de client :</td>
							<td><f:input path="nomClient" /></td>
							<td><f:errors path="nomClient" cssClass="error" /></td>
						<tr>
							<td><input type="submit" value="Ajouter le client"><input
								type="reset" value="Effacer" /></td>
						</tr>
					</table>
				</f:form></td>
		</tr>
	</table>
</body>
</html>