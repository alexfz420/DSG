<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="template_top.jsp" />
<style>
/*progressbar*/
.progressbar {
	margin-bottom: 30px;
	overflow: hidden;
	/*CSS counters to number the steps*/
	counter-reset: step;
}

.progressbar li {
	list-style-type: none;
	color: black;
	text-transform: uppercase;
	font-size: 12px;
	width: 33.33%;
	text-align: center;
	float: left;
	position: relative;
}

.progressbar li:before {
	content: counter(step);
	counter-increment: step;
	width: 20px;
	line-height: 20px;
	display: block;
	font-size: 10px;
	text-align: center;
	color: white;
	background: #D3D3D3;
	border-radius: 3px;
	margin: 0 auto 5px auto;
}
/*progressbar connectors*/
.progressbar li:after {
	content: '';
	width: 100%;
	height: 2px;
	background: #D3D3D3;
	position: absolute;
	left: -50%;
	top: 9px;
	z-index: -1; /*put it behind the numbers*/
}

.progressbar li:first-child:after {
	/*connector not needed before the first step*/
	content: none;
}
/*marking active/completed steps green*/
/*The number of the step and the connector before it = green*/
.progressbar li.active:before,.progressbar li.active:after {
	background: #0965B8;
	color: white;
}
</style>
<ul class="nav">
	<li class=""><a class="recordable open" id="toggleone" href="#"
		memo="{id:'21',type:'menu',global:1,status:''}">Manage Group</a>
		<ul class="nav-two" id="navone">
			<li class="" id="catelist"><a
				href="<%=basePath%>gotocategorylist.action?act=store">Group List</a><span
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
			<li class="selected" id="newbizrulelist"><a
				href="<%=basePath%>gotonewbizrulelist.action">New Business Rule</a><span
				class="normal">&nbsp;</span></li>
			<li class="" id="ruleprioritylist"><a
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
			<li class="" id="statlist"><a
				href="<%=basePath%>statistics.action">Statistics</a><span
				class="normal">&nbsp;</span></li>

		</ul></li>

</ul>
</div>
<script>
	$(function() {
		$("#sort tbody").sortable().disableSelection();
	});
</script>
<script>
	$(function() {

		var name = '${prodCate}';
		var ch = new Array;
		ch = name.split(",");
		for ( var i = 0; i < ch.length; i++) {
			console.log(ch[i]);
		}

		availableTags = ch;

		function split(val) {
			return val.split(/,\s*/);
		}
		function extractLast(term) {
			return split(term).pop();
		}

		$("#tags")
		// don't navigate away from the field on tab when selecting an item
		.bind(
				"keydown",
				function(event) {
					if (event.KeyCode === $.ui.keyCode.TAB
							&& $(this).data("ui-autocomplete").menu.active) {
						event.preventDefault();
					}
				}).autocomplete(
				{
					minLength : 0,
					source : function(request, response) {
						// delegate back to autocomplete, but extract the last term
						response($.ui.autocomplete.filter(availableTags,
								extractLast(request.term)));
					},
					focus : function() {
						// prevent value inserted on focus
						return false;
					},
					select : function(event, ui) {
						var terms = split(this.value);
						// remove the current input
						terms.pop();
						// add the selected item
						terms.push(ui.item.value);
						// add placeholder to get the comma-and-space at the end
						terms.push("");
						this.value = terms.join(",");

						return false;
					}
				});
	});
</script>

<script>
	//var rule = allRule;
	function show() {

		document.getElementById('secondStep').style.display = 'block';
		document.getElementById('firstStep').style.display = 'none';
		document.getElementById('buttons').style.display = 'none';
		document.getElementById('progressbar2').style.display = 'none';
		document.getElementById('progressbar3').style.display = 'block';
	}

	function close() {
		document.getElementById('secondStep').style.display = 'none';
		document.getElementById('firstStep').style.display = 'block';
		document.getElementById('buttons').style.display = 'block';
		document.getElementById('progressbar2').style.display = 'block';
		document.getElementById('progressbar3').style.display = 'none';

	}
	function textAreaAdjust(o) {
		o.style.height = "1px";
		o.style.height = (15 + o.scrollHeight) + "px";
	}

	function pageOnLoad() {
		close();
	}

	function SubmitForm() {
		console.log("start");
		var rulename = '${rulename.replace("%20"," ")}';
		console.log("searching" + rulename);
		var table = document.getElementById("sort");
		for ( var i = 0, row; row = table.rows[i]; i++) {
			//iterate through rows
			//rows would be accessed using the "row" variable assigned in the for loop
			for ( var j = 0, col; col = row.cells[j]; j++) {
				if (col.innerText == rulename) {

					document.getElementById('priorityId').value = i;

				}
				//iterate through columns
				//columns would be accessed using the "col" variable assigned in the for loop
			}
		}
		document.forms['myForm'].submit();
	}
	window.onload = pageOnLoad;
</script>
<script>
	function goBack() {
		window.history.go(-1);
	}
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

		<div class="title-bar clearfix" style="height: 100px;">
			<h1 class="l">New Business Rule</h1>
			<div id="Date" class="date l"></div>
			<a id='ReportTipIco' class="report-help open l recordable"
				memo="{id:'ReportTipIco',type:'page-tip',global:0}"
				href="javascript:void(0);">&nbsp;</a> <br />
			<br />
			<div>
				<hr />
			</div>

			<li style="list-style: none;"><span><a
					href="<%=basePath%>gotoorderlist.action">Home</a><span> &gt;
				</span></span> <span><a href="<%=basePath%>gotorulelist.actio">Manage
						Business Rule</a><span> &gt; </span></span> <span>New Business Rule</span></li>

			<!-- Success Message and Error Message -->
			<div class="success_area" style="display: none;">successMessage</div>
			<div class="warning" style="display: none;">errorMessage</div>
			<!-- Success Message and Error Message -->
		</div>

		<ul class="progressbar" id="progressbar2">
			<li class="active">Step 1</li>
			<li class="active">Step 2</li>
			<li>Step 3</li>
		</ul>


		<ul class="progressbar" id="progressbar3">
			<li class="active">Step 1</li>
			<li class="active">Step 2</li>
			<li class="active">Step 3</li>
		</ul>


		<form name="myForm" action="storeThreshold">

			<table class="text" style="width: 80%;">
				<tr>
					<td>Rule Name&#58;</td>
					<td>${rulename.replace("%20"," ")}</td>
				</tr>
				<tr>
					<td>Rule Description&#58;</td>
					<td>${des.replace("%20"," ")}</td>
				</tr>
				<tr>
					<td>Group<span class="red">*</span>&#58;
					</td>
					<td>${categoryname.replace("%20"," ")}</td>

				</tr>
				<tr>
					<td>Stage&#58;</td>
					<td>Stage 1 - Fulfillment Eligibility</td>
				</tr>
				<tr>
					<td>Rule Template&#58;</td>
					<td>${templatename }</td>
				</tr>
				<tr class="drl-height">

					<td colspan="2" style="width: 100%;">
						<div id="firstStep" class="drl">
							<div style="padding-left: 20px">
								<div>
									If <select style="width: 50px;" name="conditions">
										<option value="all">All</option>
										<option value="any">Any</option>
									</select> of the following conditions are met&#58;
								</div>
								<br/>
								<div>
									<textarea id="tags" name="storeProduct"
										style="overflow: hidden; max-width: 300px; width: 300px; height: 15px;"
										onkeyup="textAreaAdjust(this)"
										placeholder="Type in product category;"></textarea>
								</div>
								<br />
								<div>
									<select style="width: 180px;" name="attribute">
										<option value="Distance">Shipping Distance</option>
										<option value="Margin">Inventory Margin</option>
										<option value="Competition">Competition Rate</option>
									</select>&nbsp;&nbsp; <select style="width: 40px;" name="operator">
										<option value=">">&gt;</option>
										<option value="=">=</option>
										<option value="<">&lt;</option>
									</select>&nbsp;&nbsp; <input style="width: 50px;" type="text"
										name="value">&nbsp;&nbsp;<select style="width: 90px;">
										<option value="#">Miles</option>
										<option value="#">Quantity</option>
										<option value="#">Rate</option>
									</select>
								</div>
								<br />
								<div>
									<select style="width: 180px;" name="attribute">
										<option value="Margin">Inventory Margin</option>
										<option value="Distance">Shipping Distance</option>
										<option value="Competition">Competition Rate</option>
									</select>&nbsp;&nbsp; <select style="width: 40px;" name="operator">
										<option value=">">&gt;</option>
										<option value="<">&lt;</option>
										<option value="=">=</option>

									</select>&nbsp;&nbsp; <input style="width: 50px;" type="text"
										name="value">&nbsp;&nbsp;<select style="width: 90px;">
										<option value="#">Quantity</option>
										<option value="#">Miles</option>
										<option value="#">Rate</option>
									</select>
								</div>
								<br />
								<div>
									<select style="width: 180px;" name="attribute">
										<option value="Competition">Competition Rate</option>
										<option value="Distance">Shipping Distance</option>
										<option value="Margin">Inventory Margin</option>

									</select>&nbsp;&nbsp; <select style="width: 40px;" name="operator">
										<option value=">">&gt;</option>
										<option value="=">=</option>
										<option value="<">&lt;</option>
									</select>&nbsp;&nbsp; <input style="width: 50px;" type="text"
										name="value">&nbsp;&nbsp;<select style="width: 90px;">
										<option value="#">Rate</option>
										<option value="#">Miles</option>
										<option value="#">Quantity</option>

									</select>
								</div>
								<br />
								<div>Perform the following action&#58;</div>
								<br />
								<div>
									<select name="actions">
										<option value="miniumPackage">Retract this store</option>
										<option value="diffpackage">Lower Priority of this store</option>
									</select>
								</div>
							</div>
						</div>
				</tr>
				<tr />
				<tr id="buttons">
					<td style="text-align: right"><a class="button"
						onclick='show()'>Continue</a></td>
					<td><a class="button" href="<%=basePath%>gotorulelist.action">Cancel</a></td>

					<input type="hidden" name="rulename" id="rulename"
						value=<%=request.getAttribute("rulename")%> />
					<input type="hidden" name="templatename"
						value=<%=request.getAttribute("templatename")%> />
					<input type="hidden" name="categoryname"
						value=<%=request.getAttribute("categoryname")%> />
					<input type="hidden" name="des"
						value=<%=request.getAttribute("des")%> />
					<input type="hidden" id="priorityId" name="priority">


				</tr>
			</table>

			<div id="secondStep" style="padding-left: 50px;">
				<!--             <table id="sort" class="grid" border="0" style="border-collapse:collapse;width:100%;font-size:12px;">
			<thead>
                        <tr style="height:30px;background-color:#f1f1f1;border-bottom:none;">
                            <th style="text-align:center;color:#666;">Rule Number</th>
                            <th style="text-align:left;color:#666;">Rule Name</th>
                            <th style="text-align:left;color:#666;">Rule Description</th>
                        </tr>
                    </thead>
   			<c:set var="ruleNum" value ="1" />
            <c:forEach var="allRule" items="${allRule}" >
            
            		<tr style="height:30px;">
            				
                            <td style="border-bottom:1px #E5E5E5 solid;padding: 6px 10px 6px 5px;text-align: center;color:#666;">${ruleNum}</td>
                            <td style="border-bottom:1px #E5E5E5 solid;padding: 6px 10px 6px 5px;text-align: left;color:#666;">${allRule.ruleName}</td>
                            <td style="border-bottom:1px #E5E5E5 solid;padding: 6px 10px 6px 5px;text-align: left;color:#666;">${allRule.ruleDescr}</td>
                        </tr>
                 <c:set var="ruleNum" value="${ruleNum+1}" />   
				</c:forEach>
				<tr style="height:30px;background-color:8CEEF5">
                            <td style="border-bottom:1px #E5E5E5 solid;padding: 6px 10px 6px 5px;text-align: center;color:#666;background-color:#75a8d8">Your New Rule</td>

                            <td style="border-bottom:1px #E5E5E5 solid;padding: 6px 10px 6px 5px;text-align: left;color:#666;background-color:#75a8d8">${rulename.replace("%20"," ")}</td>

                            <td style="border-bottom:1px #E5E5E5 solid;padding: 6px 10px 6px 5px;text-align: left;color:#666;background-color:#75a8d8">Please drag and drop the rule</td>
                </tr>
                
            </table>
 -->
				<c:set var="ruleNum" value="1" />
				<table class="list" border="0" cellspacing="0" cellpadding="0"
					style="border-collapse: collapse; width: 750px; font-size: 12px;">
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
							<td style="width: 15%;">${ruleNum}</td>
							<td style="width: 30%;"><div
									style="width: 80%; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${preRule.ruleName}</div></td>
							<td style="width: 35%;"><div
									style="width: 80%; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${preRule.ruleDescr}</div></td>
							<td style="width: 10%;">${preRule.stage}</td>
							<c:choose>
								<c:when test="${(preRule.able == true)}">
									<td style="width: 10%;">Active</td>
								</c:when>
								<c:otherwise>
									<td style="width: 10%;">Disabled</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<c:set var="ruleNum" value="${ruleNum+1}" />
					</c:forEach>



				</table>
				<table id="sort" class="list" border="0" cellspacing="0"
					cellpadding="0"
					style="border-collapse: collapse; width: 750px; font-size: 12px;">
					<tbody>
						<c:forEach var="midRule" items="${midRule}">
							<tr style="height: 30px;">
								<td style="width: 15%; background-color: #75a8d8;">${ruleNum}</td>
								<td style="width: 30%; background-color: #75a8d8;">${midRule.ruleName}</td>
								<td style="width: 35%; background-color: #75a8d8;">${midRule.ruleDescr}</td>
								<td style="width: 10%; background-color: #75a8d8;">${midRule.stage}</td>
								<c:choose>
									<c:when test="${ (midRule.able == true) }">
										<td style="width: 10%; background-color: #75a8d8;">Active&nbsp;&nbsp;&nbsp;&nbsp;&uarr;&darr;</td>
									</c:when>
									<c:otherwise>
										<td style="width: 10%; background-color: #75a8d8;">Disabled&nbsp;&nbsp;&nbsp;&nbsp;&uarr;&darr;</td>
									</c:otherwise>
								</c:choose>


							</tr>
							<c:set var="ruleNum" value="${ruleNum+1}" />
						</c:forEach>
						<tr style="height: 30px; background-color: #E6CFE6;">
							<td style="width: 15%; background-color: #E6CFE6;">Your New
								Rule</td>
							<td style="width: 30%; background-color: #E6CFE6;">${rulename.replace("%20"," ")}</td>
							<td style="width: 35%; background-color: #E6CFE6;">Please
								drag and drop the rule</td>
							<td style="width: 10%; background-color: #E6CFE6;">1</td>
							<td style="width: 10%; background-color: #E6CFE6;">Active&nbsp;&nbsp;&nbsp;&nbsp;&uarr;&darr;</td>

						</tr>
						<c:set var="ruleNum" value="${ruleNum+1}" />
					</tbody>
				</table>
				<table class="list" border="0" cellspacing="0" cellpadding="0"
					style="border-collapse: collapse; width: 750px; font-size: 12px;">
					<c:forEach var="lastRule" items="${lastRule}">
						<tr style="height: 30px;">
							<td style="width: 15%;">${ruleNum}</td>
							<td style="width: 30%;"><div
									style="width: 80%; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${lastRule.ruleName}</div></td>
							<td style="width: 35%;"><div
									style="width: 80%; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${lastRule.ruleDescr}</div></td>
							<td style="width: 10%;">${lastRule.stage}</td>
							<c:choose>
								<c:when test="${ (lastRule.able == true) }">
									<td style="width: 10%;">Active</td>
								</c:when>
								<c:otherwise>
									<td style="width: 10%;">Disabled</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<c:set var="ruleNum" value="${ruleNum+1}" />
					</c:forEach>
				</table>
				<a class="button" onclick="goBack()">Back</a> <a class="button"
					href="<%=basePath%>gotorulelist.action">Cancel</a> <a
					class="button" onclick='SubmitForm()'>Create</a>

			</div>
		</form>
	</div>





	<!-- content ends -->


	<!-- footer starts -->

	<div class="footer">
		<span>&copy;2013 eBusiness Team</span>
	</div>

	<!-- footer ends -->

</div>
</body>
</html>
