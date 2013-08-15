<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="warning">
	<c:forEach var="error" items="${errors}">
		<div style="font-size:medium; color:red; "> ${error} </div>
	</c:forEach>
</div>
