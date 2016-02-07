

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<body>
	 <script type="text/javascript">
        $(document).ready(function() {
            $('#addButton').click( function(){
                location.href='/accounts/add';
              });
        });
    </script>
	<c:if test="${empty bankAccounts}">
	   <p><b>You currently do not have any bank accounts.</b></p>
	</c:if>
    
    <c:if test="${not empty bankAccounts}">
	    <h1>Bank Accounts</h1>
			
			<table class="table table-striped">
				<thead>
				 <tr>
						<th>Id</th>
						<th>Iban</th>
						<th>BusinessCode</th>
						<th>Action</th>
				</tr>
				</thead>
				<c:forEach items="${bankAccounts}" var="account">
				  <tr>
						<td>${account.getId()}</td>
						<td>${account.getIban()}</td>
						<td>${account.getBusinessCode()}</td>
						<td>
						  <spring:url value="/accounts/${account.getId()}/delete" var="deleteUrl" /> 
						  <spring:url value="/accounts/${account.getId()}/edit" var="editUrl" />
		
						  <button id="editButton" class="btn btn-info" 
		                                          onclick="location.href='${editUrl}'">Edit</button>
						  <button id="deleteButton" class="btn btn-primary" 
		                                          onclick="location.href='${deleteUrl}'">Delete</button>
						  
		                </td>
	               </tr> 
				</c:forEach>
			</table>
		</c:if>
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			<button id="addButton" class="btn btn-info" align="left">Add</button>
          </div>
        </div>
</body>
</html>