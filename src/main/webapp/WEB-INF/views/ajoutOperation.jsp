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
<script type="text/javascript">
	function f() {
		if (document.getElementById("ver").checked) {
			document.getElementById('labCompte2').style.display = 'none';
			document.getElementById('idCompte2').style.display = 'none';
		}
		if (document.getElementById("ret").checked) {
			document.getElementById('labCompte2').style.display = 'none';
			document.getElementById('idCompte2').style.display = 'none';
		}
		if(document.getElementById("vir").checked) {
			document.getElementById('labCompte2').style.display = 'block';
			document.getElementById('idCompte2').style.display = 'block';
		}
	}
</script>
<title>Ajout d'une opération</title>
</head>
<body onload='f()'>

	<table>
		<tr>
			<td><jsp:include page="index.jsp" flush="true" /></td>
			<td>
				<div>
					<f:form modelAttribute="operationModel" method="post"
						action="saveOperation">
						<c:if test="${not empty compteModel.erreur}">
							<div class="errorblock">${compteModel.erreur}</div>
						</c:if>
						<f:errors path="*" element="div" cssClass="errorblock" />
						<table>
							<tr>
								<td>Type d'opération: <br> 
								<f:radiobutton id="ver"	path="typeOperation" value="VER" onclick="f()" /> Versement 
								<f:radiobutton id="ret" path="typeOperation" value="RET" onclick="f()" /> Retrait 
								<f:radiobutton id="vir" path="typeOperation" value="VIR" onclick="f()" /> Virement
							</tr>
							<tr>
								<td>Montant:</td>
								<td><f:input path="montant" /></td>
								<td><f:errors path="montant" cssClass="error" /></td>
							</tr>
							<tr>
								<td><label id="labCompte">Id de Compte:</label></td>
								<td><f:input id="idCompte" path="idCompte" /></td>
								<td><f:errors path="idCompte" cssClass="error" /></td>
							<tr>
								<td><label id="labCompte2">Id de Compte 2:</label></td>
								<td><f:input id="idCompte2" path="idCompte2" /></td>
								<td><f:errors path="idCompte2" cssClass="error" /></td>
							</tr>
							<tr>
								<td>Id d'employé:</td>
								<td><f:input path="idEmploye" /></td>
								<td><f:errors path="idEmploye" cssClass="error" /></td>
							</tr>
							<tr>
								<td><input type="submit" value="Ajouter l'opération"><input
									type="reset" value="Effacer" /></td>
							</tr>
						</table>
					</f:form>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>