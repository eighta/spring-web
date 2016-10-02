<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>
	<fmt:message key="accounts.edit.title" />
</h1>

<style>
    label { font-weight: bold; }
    .control { margin-left: 1em; }
    fieldset li { list-style: none; }
</style>

<div id="accountDetails">
	<spring:url value="/accounts/{number}" var="accountUrl">
		<spring:param name="number" value="${account.number}" />
	</spring:url> 
	<form:form action="${accountUrl}" method="post" modelAttribute="account">
		<fieldset>
			<legend>
				<fmt:message key="accounts.edit.legend" />
			</legend>
			<ul>
				<!--  Show the account number if there is one.
				      When creating a new account it will be empty.  -->
				<c:if test="${account.number != ''}">
					<li>
						<label for="name">
							<fmt:message key="label.Account.number" />
						</label>
						<div class="control">
							${account.number}
						</div>
					</li>
				</c:if>
				<li>
					<label for="name">
						<fmt:message key="label.Account.name" />
					</label>
					<div class="control">
						<form:input path="name" />
					</div>
				</li>
				<li>
					<label for="dateOfBirth">
						<fmt:message key="label.Account.dateOfBirth" />
					</label>
					<div class="control">
						<form:input path="dateOfBirth" />
					</div>
				</li>
				<li>
					<label for="email">
						<fmt:message key="label.Account.email" />
					</label>
					<div class="control">
						<form:input path="email" />
					</div>
				</li>
				<li class="confirm">
					<label for="receiveNewsletter">
						<form:checkbox path="receiveNewsletter"/>
						<fmt:message key="label.Account.receiveNewsletter" />
					</label>
				</li>
				<li class="confirm">
					<label for="receiveMonthlyEmailUpdate">
						<form:checkbox path="receiveMonthlyEmailUpdate"/>
						<fmt:message key="label.Account.receiveMonthlyEmailUpdate" />
					</label>
				</li>
				
				<!--  Show the beneficiaries if there are any. -->
				<c:if test="${fn:length(account.beneficiaries) > 0}">
					<br/>
					<li>
						<label for="beneficiaries">
							<fmt:message key="label.Account.beneficiaries" />
						</label>
						<div class="control">
							<c:forEach var="beneficiary" items="${account.beneficiaries}">
							    &nbsp; ${beneficiary.name}
							</c:forEach>
						</div>
					</li>
				</c:if>
			</ul>
				<button id="saveButton" type="submit">
					<fmt:message key="command.save" />
				</button>
				&nbsp;
				<a href="${accountUrl}">
					<fmt:message key="command.cancel" />
				</a>
		
		</fieldset>
	</form:form>
</div>