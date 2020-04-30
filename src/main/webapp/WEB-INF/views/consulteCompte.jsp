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
<title>Consulté un compte</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style.css" />
</head>
<body>
	<table>
		<tr>
			<td><jsp:include page="index.jsp" flush="true" /></td>
			<td><f:form modelAttribute="consulteCompteModel" method="post"
					action="chargerCompte">
					<c:if test="${not empty consulteCompteModel.erreur}">
						<div class="errorblock">${consulteCompteModel.erreur}</div>
					</c:if>
					<f:errors path="*" element="div" cssClass="errorblock" />
					<table>
						<tr>
							<td>Id Compte :</td>
							<td><f:input path="idCompte" /></td>
							<td><f:errors path="idCompte" cssClass="error" /></td>
						<tr>
							<td><input type="submit" value="Charger le compte"><input
								type="reset" value="Effacer" /></td>
						</tr>
					</table>
					<c:if test="${not empty consulteCompteModel.c}">
						<div>
							<table>
								<tr>
									<td>Numéro de compte:</td>
									<td>${consulteCompteModel.idCompte}</td>
								</tr>
								<tr>
									<td>Type de compte:</td>
									<td>${consulteCompteModel.typeCompte}</td>
								</tr>
								<tr>
									<td>Solde:</td>
									<td>${consulteCompteModel.c.solde}</td>
								</tr>
								<tr>
									<td>Nom de client:</td>
									<td>${consulteCompteModel.c.client.nomClient}</td>
								</tr>
							</table>
						</div>
						<div>
							<b><font color="#ff0000">Opérations:</font></b><br>
							<table class="table1">
								<tr>
									<th>Numéro</th>
									<th>Type</th>
									<th>Date</th>
									<th>Montant</th>
								</tr>
								<c:forEach items="${consulteCompteModel.ops}" var="op">
									<tr>
										<td>${op.numOperation}</td>
										<td>${op}</td>
										<td>${op.dateOperation}</td>
										<td>${op.montant}</td>
								</c:forEach>
							</table>
						</div>
					</c:if>
					<div>
						<c:forEach begin="0" end="${consulteCompteModel.nbPages-1}" var="p">
							<c:choose>
								<c:when test="${p==consulteCompteModel.page}">
								Page ${p}
							</c:when>
								<c:otherwise>
									<a
										href="chargerCompte?page=${p}&idCompte=${consulteCompteModel.idCompte}">Page
										${p}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</f:form></td>
		</tr>
	</table>
</body>
</html>