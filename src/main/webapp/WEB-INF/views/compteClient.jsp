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
<title>Comptes d'un client</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style.css" />
</head>
<body>
	<table>
		<tr>
			<td><jsp:include page="index.jsp" flush="true" /></td>
			<td><f:form modelAttribute="rechercheCompteModel" method="post"
					action="chargerComptes">
					<c:if test="${not empty rechercheCompteModel.erreur}">
						<div class="errorblock">${rechercheCompteModel.erreur}</div>
					</c:if>
					<f:errors path="*" element="div" cssClass="errorblock" />
					<table>
						<tr>
							<td>Id de client :</td>
							<td><f:input path="idClient" /></td>
							<td><f:errors path="idClient" cssClass="error" /></td>
						<tr>
							<td><input type="submit" value="Charger les comptes"><input
								type="reset" value="Effacer" /></td>
						</tr>
					</table>
					<c:if test="${not empty rechercheCompteModel.comptes}">
						<div>
							<table class="table1">
								<tr>
									<th>Numéro</th>
									<th>Type</th>
									<th>Date</th>
									<th>Solde</th>
								</tr>
								<c:forEach items="${rechercheCompteModel.comptes}" var="cp">
									<tr>
										<td>${cp.numCompte}</td>
										<td>${cp}</td>
										<td>${cp.dateCreation}</td>
										<td>${cp.solde}</td>
								</c:forEach>
							</table>
						</div>
					</c:if>
				</f:form></td>
		</tr>
	</table>
</body>
</html>