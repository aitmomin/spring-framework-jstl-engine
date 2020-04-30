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
<title>Ajouter un employé</title>
</head>
<body>
	<table>
		<tr>
			<td><jsp:include page="index.jsp" flush="true" /></td>
			<td><f:form modelAttribute="employeModel" method="post"
					action="saveEmploye">
					<c:if test="${not empty employeModel.erreur}">
						<div class="errorblock">${employeModel.erreur}</div>
					</c:if>
					<f:errors path="*" element="div" cssClass="errorblock" />
					<table>
						<tr>
							<td>Nom employé :</td>
							<td><f:input path="nomEmploye" /></td>
							<td><f:errors path="nomEmploye" cssClass="error" /></td>
						<tr>
						<tr>
							<td>Id employé supérieur</td>
							<td><f:input path="idEmploye" /></td>
						<tr>
							<td><input type="submit" value="Ajouter l'employé"><input
								type="reset" value="Effacer" /></td>
						</tr>
					</table>
				</f:form></td>
		</tr>
	</table>
</body>
</html>