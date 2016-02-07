

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
	<div class="container">

	<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
	</c:if>
	<c:choose>
		<c:when test="${bankAccountForm['new']}">
			<h1>Add Account</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit Account</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/accounts/save" var="addAccountUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="bankAccountForm" action="${addAccountUrl}">

		<form:hidden path="id" />

		<spring:bind path="iban">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Iban</label>
			<div class="col-sm-10">
				<form:input path="iban" type="text" class="form-control" 
                                id="iban" placeholder="Iban" />
				<form:errors path="iban" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		<spring:bind path="businessCode">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Business Code</label>
			<div class="col-sm-10">
				<form:input path="businessCode" class="form-control" 
                                id="businessCode" placeholder="BusinessCode" />
				<form:errors path="businessCode" class="control-label" />
			</div>
		  </div>
		</spring:bind>

		

		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			<c:choose>
			  <c:when test="${bankAccountForm['new']}">
			     <button type="submit" class="btn-lg btn-primary pull-left">Add
                             </button>
			  </c:when>
			  <c:otherwise>
			     <button type="submit" class="btn-lg btn-primary pull-left">Update
                             </button>
			  </c:otherwise>
			</c:choose>
		  </div>
		</div>
	</form:form>

</div>
</body>
</html>
