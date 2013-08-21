
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="template_top.jsp" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> 
<ul class="nav">
        <li class=""><a class="recordable open" id="toggleone" href="#" 
            memo="{id:'21',type:'menu',global:1,status:''}">Manage Group</a>
            <ul class="nav-two" id="navone">
                <li class="" id="catelist"><a href="<%=basePath%>gotocategorylist.action?act=store">Group List</a><span class="normal">&nbsp;</span></li>
                <li class="" id="newcatelist"><a href="<%=basePath%>gotonewcategory.action">New Group</a><span class="normal">&nbsp;</span></li>  
            </ul>
        </li>
        <li class=""><a class="recordable open" href="#" id="toggletwo"
            memo="{id:'21',type:'menu',global:1,status:''}">Manage Business Rule</a>
            <ul class="nav-two" id="navtwo">
                <li class="" id="bizrulelist"><a href="<%=basePath%>gotorulelist.action">Business Rule List</a><span class="normal">&nbsp;</span></li>
                <li class="" id="newbizrulelist"><a href="<%=basePath%>gotonewbizrulelist.action">New Business Rule</a><span class="normal">&nbsp;</span></li>
                <li class="selected" id="ruleprioritylist"><a href="<%=basePath%>gotoruleprioritylist.action">Business Rule Priority</a><span class="normal">&nbsp;</span></li> 
            </ul>
        </li>   
        <li class=""><a class="recordable open" href="#" id="togglethree"
            memo="{id:'21',type:'menu',global:1,status:''}">Visualization Dashboard</a>
            <ul class="nav-two" id="navthree">
                <li class="" ><a id="orderlist" onclick="f(this)" href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
				<li class="" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li>

			</ul>


        </li>
		<li class=""><a class="recordable open" href="#" id="togglefour"
            memo="{id:'21',type:'menu',global:1,status:''}">Simulation</a>
            <ul class="nav-two" id="navtwo">
                <li class="" id="neworderlist"><a href="<%=basePath%>gotoplaceorder.action">New Order</a><span class="normal">&nbsp;</span></li>
                
            </ul>
		</li>   
    </ul>
</div>
<script>
	$(function() {
		$("#sort tbody").sortable().disableSelection();

	});

	//var rule = allRule;

	function show() {
		if (document.getElementById('benefits').style.display == 'none') {
			document.getElementById('benefits').style.display = 'block';
		}
	}

	function close() {
		if (document.getElementById('benefits').style.display == 'block') {
			document.getElementById('benefits').style.display = 'none';
		}
	}

	function pageOnLoad() {
		console.log("logged");
	}

	function submitForm() {
		var nameList = "";
		var table = document.getElementById("sort");
		if (table != null && table.rows[0] != null) {
			var row = table.rows[0];
			var col = row.cells[1];
			nameList += col.innerText;
	
			//iterate through rows
			//rows would be accessed using the "row" variable assigned in the for loop
			for ( var i = 1, row; row = table.rows[i]; i++) {
				//iterate through rows
				//rows would be accessed using the "row" variable assigned in the for loop
				nameList += ",";
				col = row.cells[1];
				nameList += col.innerText;
			}
	
			console.log("rule list" + nameList);
			document.getElementById('ruleString').value = nameList;
			
		}
		document.forms['myForm'].submit();
		
	}
	window.onload = pageOnLoad;
</script>
<!-- menu bar ends -->

<!-- content starts -->
<div class="minibar recordable" id="minibar"
	memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}"
	style="display: none;">
	<a id="menu-untoggle" href="javascript:void(0)" class="unfold"></a>
</div>
<div class="main" id="main-body">
	<div class="content clearfix">

		<div class="title-bar clearfix">
			<h1 class="l">Business Rule Priority</h1>
			<div id="Date" class="date l"></div>
			<a id='ReportTipIco' class="report-help open l recordable"
				memo="{id:'ReportTipIco',type:'page-tip',global:0}"
				href="javascript:void(0);">&nbsp;</a> <br />
			<br />
			<div>
			<hr />
			</div>

			<li style="list-style: none;">
				<span><a href="#">Home</a><span> &gt; </span></span> 
				<span><a href="<%=basePath%>gotorulelist.action">Manage Business Rule</a><span> &gt; </span></span>
				<span>Business Rule Priority</span>
			</li>
			
			<!-- Success Message and Error Message -->
			<div class="success_area" style="display: none">successMessage</div>
			<div class="warning" style="display: none">errorMessage</div>
			<!-- Success Message and Error Message -->
		</div>

		<div>
			<br />
			<form name="myForm" action="reRank">
				<br/>
				<h4 class="grey" style="padding-bottom:10px;">Hint: Please only drag and drop the rules with blue background to sort
					the rule priority.</h4>

				<div id="haha" class="table-list">				
				
				
					<c:set var="ruleNum" value="1" />
					<table align="top" class="list" border="0" cellspacing="0" cellpadding="0"
						style="border-collapse: collapse; width:800px; font-size: 12px;">
						<thead>
							<tr class="title"
								style="height: 30px; background-color: #f1f1f1; border-bottom: none;">
								<th>Rule#</th>
								<th>Rule Name</th>
								<th>Rule Description</th>
								<th>Stage</th>
								<th>Status</th>
							</tr>
						</thead>

						<c:forEach var="preRule" items="${preRule}">
							<tr style="height: 30px;">
								<td
									style="width:5%;">${ruleNum}</td>
								<td
									style="width:30%;">${preRule.ruleName}</td>
								<td
									style="width:35%;">${preRule.ruleDescr}</td>
								<td style="width:15%;">${preRule.stage}</td>
								<c:choose>
										<c:when test="${(preRule.able == true)}">
											<td style="width:15%;">Active</td>
										</c:when>
										<c:otherwise>
											<td style="width:15%;">Disabled</td>
										</c:otherwise>
									</c:choose>
							</tr>
							<c:set var="ruleNum" value="${ruleNum+1}" />
						</c:forEach>



					</table>
					<table align="top" id="sort" class="list" border="0"  cellspacing="0" cellpadding="0"
						style="border-collapse: collapse; width:800px; font-size: 12px;">
						<tbody>
							<c:forEach var="midRule" items="${midRule}">
								<tr style="height: 30px;">
									<td
										style="width:5%; background-color: #75a8d8;">${ruleNum}</td>
									<td

										style="width:30%; background-color: #75a8d8;">${midRule.ruleName}</td>

									<td

										style="width:35%; background-color: #75a8d8;">${midRule.ruleDescr}</td>

									<td style="width:15%; background-color: #75a8d8;">${midRule.stage}</td>
									<c:choose>
										<c:when test="${ (midRule.able == true) }">
											<td style="width:15%;background-color: #75a8d8;">Active&nbsp;&nbsp;&nbsp;&nbsp;&uarr;&darr;</td>
										</c:when>
										<c:otherwise>
											<td style="width:15%;background-color: #75a8d8;">Disabled&nbsp;&nbsp;&nbsp;&nbsp;&uarr;&darr;</td>
										</c:otherwise>
									</c:choose>
									
									
								</tr>
								<c:set var="ruleNum" value="${ruleNum+1}" />
							</c:forEach>
						</tbody>
					</table>
					<table align="top" class="list" border="0" cellspacing="0" cellpadding="0"
						style="border-collapse: collapse; width: 800px; font-size: 12px;">
						<c:forEach var="lastRule" items="${lastRule}">
							<tr style="height: 30px;">
								<td
									style="width:5%;">${ruleNum}</td>
								<td
									style="width:30%;">${lastRule.ruleName}</td>
								<td
									style="width:35%;">${lastRule.ruleDescr}</td>
								<td style="width:15%;">${lastRule.stage}</td>
								<c:choose>
										<c:when test="${ (lastRule.able == true) }">
											<td style="width:15%;">Active</td>
										</c:when>
										<c:otherwise>
											<td style="width:15%;">Disabled</td>
										</c:otherwise>
									</c:choose>
							</tr>
							<c:set var="ruleNum" value="${ruleNum+1}" />
						</c:forEach>
					</table>
					<br /> <input type="hidden" id="ruleString" name="ruleString">
					<a class="button" href="#">Cancel</a> <a class="button" id="add-to-cart" >Submit</a>
				</div>

				<div id="priorityTable"></div>
				<input type="hidden" name="rulename"
					value=<%=request.getAttribute("rulename")%> />

			</form>
		</div>

		<!-- content ends -->

		<!-- Update priority Pop up -->
		<div id="pop-back"></div>
		<div id="cart1" class="cart">
			<div class="popheader">
				<span class="title"><strong>&nbsp;Update Priority</strong></span>
			</div>
			<br>
			<div>&nbsp;&nbsp;Are you sure you want to update business
				rule's priority?</div>

			<div class="r" style="margin-right: 20px;">
				<input class="button" onclick="submitForm()" type="submit" value="Yes"  />
				<a class="button" onclick="closePop()" type="button">No</a>
			</div>
		</div>
		<!-- Update priority pop-up ends -->


		<!-- footer starts -->
	</div>
	<div class="footer">
		<span>&copy;2013 eBusiness Team</span>
	</div>
</div>
<!-- footer ends -->

</div>
</body>
</html>
