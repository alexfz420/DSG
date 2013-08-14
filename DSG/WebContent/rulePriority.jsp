
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="template_top.jsp" />
<ul class="nav">
	<li class=""><a class="recordable open" id="toggleone" href="#"
		memo="{id:'21',type:'menu',global:1,status:''}">Manage Group</a>
		<ul class="nav-two" id="navone">
			<li class="" id="catelist"><a
				href="<%=basePath%>gotocategorylist.action">Group List</a><span
				class="normal">&nbsp;</span></li>
			<li class="" id="newcatelist"><a
				href="<%=basePath%>gotonewcategory.action">New Group</a><span
				class="normal">&nbsp;</span></li>
		</ul></li>
	<li class=""><a class="recordable open" href="#" id="toggletwo"
		memo="{id:'21',type:'menu',global:1,status:''}">Manage Business
			Rule</a>
		<ul class="nav-two" id="navtwo">
			<li class="" id="bizrulelist"><a
				href="<%=basePath%>gotorulelist.action">Business Rule List</a><span
				class="normal">&nbsp;</span></li>
			<li class="" id="newbizrulelist"><a
				href="<%=basePath%>gotonewbizrulelist.action">New Business Rule</a><span
				class="normal">&nbsp;</span></li>
			<li class="selected" id="ruleprioritylist"><a
				href="<%=basePath%>gotoruleprioritylist.action">Business Rule
					Priority</a><span class="normal">&nbsp;</span></li>
		</ul></li>
	<li class=""><a class="recordable open" href="#" id="togglethree"
		memo="{id:'21',type:'menu',global:1,status:''}">Visualization
			Dashboard</a>
		<ul class="nav-two" id="navthree">
			<li class=""><a id="orderlist" onclick="f(this)"
				href="<%=basePath%>gotoorderlist.action">Order List</a><span
				class="normal">&nbsp;</span></li>
			<li class="" id="statlist"><a href="statistics.html">Statistics</a><span
				class="normal">&nbsp;</span></li>
		</ul></li>
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

				<h4 class="grey" style="padding-bottom:10px;">Hint: Please only drag and drop the rules with blue background to sort
					the rule priority. <br/>Do not drag other rules, otherwise the system function will be interrupted.</h4>

				<div id="haha">
					<c:set var="ruleNum" value="1" />
					<table class="grid" border="0"
						style="border-collapse: collapse; width:100%; font-size: 12px;">
						<thead>
							<tr
								style="height: 30px; background-color: #f1f1f1; border-bottom: none;">
								<th style="text-align: center; color: #666;">Rule#</th>
								<th style="text-align: left; color: #666;">Rule Name</th>
								<th style="text-align: left; color: #666;">Rule Description</th>
								<th style="text-align: left; color: #666;">Stage</th>
							</tr>
						</thead>

						<c:forEach var="preRule" items="${preRule}">
							<tr style="height: 30px;">
								<td
									style="width:20%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: center; color: #666;">${ruleNum}</td>
								<td
									style="width:30%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: left; color: #666;">${preRule.ruleName}</td>
								<td
									style="width:30%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: left; color: #666;">${preRule.ruleDescr}</td>
								<td style="width:20%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: left; color: #666;">${preRule.stage}</td>
							</tr>
							<c:set var="ruleNum" value="${ruleNum+1}" />
						</c:forEach>



					</table>
					<table id="sort" class="grid" border="0"
						style="border-collapse: collapse; width:100%; font-size: 12px;">
						<tbody>
							<c:forEach var="midRule" items="${midRule}">
								<tr style="height: 30px;">
									<td
										style="width:20%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: center; color: #666; background-color: #75a8d8;">${ruleNum}</td>
									<td
										style="width:30%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: left; color: #666; background-color: #75a8d8 ">${midRule.ruleName}</td>
									<td
										style="width:30%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: left; color: #666; background-color: #75a8d8 ">${midRule.ruleDescr}</td>
									<td style="width:20%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: left; color: #666; background-color: #75a8d8">${midRule.stage}</td>
								</tr>
								<c:set var="ruleNum" value="${ruleNum+1}" />
							</c:forEach>
						</tbody>
					</table>
					<table class="grid" border="0"
						style="border-collapse: collapse; width: 100%; font-size: 12px;">
						<c:forEach var="lastRule" items="${lastRule}">
							<tr style="height: 30px;">
								<td
									style="width:20%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: center; color: #666;">${ruleNum}</td>
								<td
									style="width:30%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: left; color: #666;">${lastRule.ruleName}</td>
								<td
									style="width:30%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: left; color: #666;">${lastRule.ruleDescr}</td>
								<td style="width:20%; border-bottom: 1px #E5E5E5 solid; padding: 6px 10px 6px 5px; text-align: left; color: #666;">${lastRule.stage}</td>
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
