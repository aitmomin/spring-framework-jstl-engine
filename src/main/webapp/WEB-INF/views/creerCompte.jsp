<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script type="text/javascript">
	function f() {
		if (document.getElementById("cc").checked)
		{
			document.getElementById('lab').innerHTML="Decouvert:";
			document.getElementById('decouvert').style.display='block';
			document.getElementById('tauxInteret').style.display = 'none';
		}
		if (document.getElementById("ce").checked)
		{
			document.getElementById('lab').innerHTML="Taux d'intérêt:";
			document.getElementById('decouvert').style.display = 'none';
			document.getElementById('tauxInteret').style.display = 'block';
		}
	}
</script>
<title>Création d'un compte</title>
</head>
<body onload='f()'>
	
	<table>
	<tr>
	<td><jsp:include page="index.jsp" flush="true" />
	</td>
	<td>
	<div>
		<f:form modelAttribute="compteModel" method="post"
			action="saveCompte">
			<c:if test="${not empty compteModel.erreur}">
			<div class="errorblock"> 
				${compteModel.erreur}
			</div>
			</c:if>
			<f:errors path="*" element="div" cssClass="errorblock"/>
			<table>
				<tr>
					<td>Type de compte: <br> 
					<f:radiobutton id="cc" path="typeCompte" value="CC" onclick="f()" /> Compte courant 
					<f:radiobutton id="ce" path="typeCompte" value="CE" onclick="f()" /> Compte epargne
					</td>
					<td><f:errors path="typeCompte" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Montant:</td>
					<td><f:input path="montant" /></td>
					<td> <f:errors path="montant" cssClass="error" /></td>
				</tr>
				<tr>
					<td><label id="lab">Decouvert:</label></td>
					<td>
					     <f:input id="decouvert" path="decouvert" /> 
					     <f:input id="tauxInteret" path="tauxInteret" />
					</td>
					<td>
						<f:errors path="decouvert" cssClass="error" />
						<f:errors path="tauxInteret" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Id de client:</td>
					<td><f:input path="idClient" /></td>
					<td> <f:errors path="idClient" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Id d'employé:</td>
					<td><f:input path="idEmploye" /></td>
					<td> <f:errors path="idEmploye" cssClass="error" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Ajouter le compte"><input
						type="reset" value="Effacer" /></td>
				</tr>
			</table>
		</f:form>
		</div>
		</td>
		</tr></table>
</body>
</html>