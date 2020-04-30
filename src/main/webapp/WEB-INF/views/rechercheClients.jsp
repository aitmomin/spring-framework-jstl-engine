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
<title>Rechercher des clients</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style.css" />
</head>
<body>
	<table>
		<tr>
			<td><jsp:include page="index.jsp" flush="true" /></td>
			<td><f:form modelAttribute="rechercheClientModel" method="post"
					action="chargerClients">
					<c:if test="${not empty rechercheClientModel.erreur}">
						<div class="errorblock">${rechercheClientModel.erreur}</div>
					</c:if>
					<f:errors path="*" element="div" cssClass="errorblock" />
					<div>
						<table>
							<tr>
								<td>Nom de client :</td>
								<td><f:input path="nomClient" /></td>
								<td><f:errors path="nomClient" cssClass="error" /></td>
							<tr>
								<td><input type="submit" value="Rechercher"><input
									type="reset" value="Effacer" /></td>
							</tr>
						</table>
					</div>
					<c:if test="${not empty rechercheClientModel.clients}">
						<div>
							<table class="table1">
								<tr>
									<th>id Client</th>
									<th>Nom</th>
								</tr>
								<c:forEach items="${rechercheClientModel.clients}" var="cl">
									<tr>
										<td>${cl.idClient}</td>
										<td>${cl.nomClient}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</c:if>
				</f:form></td>
		</tr>
	</table>
</body>
</html>