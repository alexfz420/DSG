<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
                <li class="selected" id="bizrulelist"><a href="<%=basePath%>gotorulelist.action">Business Rule List</a><span class="normal">&nbsp;</span></li>
                <li class="" id="newbizrulelist"><a href="<%=basePath%>gotonewbizrulelist.action">New Business Rule</a><span class="normal">&nbsp;</span></li>
                <li class="" id="ruleprioritylist"><a href="<%=basePath%>gotoruleprioritylist.action">Business Rule Priority</a><span class="normal">&nbsp;</span></li> 
            </ul>
        </li>   
        <li class=""><a class="recordable open" href="#" id="togglethree"
            memo="{id:'21',type:'menu',global:1,status:''}">Visualization Dashboard</a>
            <ul class="nav-two" id="navthree">
                <li class="" ><a id="orderlist" onclick="f(this)" href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
				<li class="" id="statlist"><a href="<%=basePath %>statistics.action">Statistics</a><span class="normal">&nbsp;</span></li>

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

function chooseFormat(flag, storeType, costId) {
	console.log("in choose format");
	
	if (flag == 'p') {
		var hideTd = document.getElementById(storeType+'V'+costId);
		var showTd = document.getElementById(storeType+'P'+costId);
		console.log("show: " + hideTd.id);
		console.log("hide: " + showTd.id);
		hideTd.hidden= "true";
		showTd.removeAttribute("hidden");	
	} else if (flag == 'v') {
		var hideTd = document.getElementById(storeType+'P'+costId);
		var showTd = document.getElementById(storeType+'V'+costId);
		console.log("show: " + storeType+'V'+costId);
		console.log("hide: " + storeType+'P'+costId);
		hideTd.hidden= "true";
		showTd.removeAttribute("hidden");
	}	
}

function addOtherCost(myDiv, storeType) {
  var ni = document.getElementById(myDiv.id);
  var numi = document.getElementById('theValue');
  var num = (document.getElementById('theValue').value -1)+2;
  numi.value = num;
  var newdiv = document.createElement('div');

  var divIdName = 'othercost'+num;
  newdiv.setAttribute('id',divIdName);
  
  var inputId = document.createElement("input");
  inputId.type = "hidden";
  inputId.name = storeType+'Id';
  inputId.value = "new";
  newdiv.appendChild(inputId);
  
  var inputName = document.createElement("input");
  inputName.type = "text";
  inputName.name = storeType+'Name';
  inputName.style.width = '150px';
  inputName.placeholder= 'Input Cost Name';  
  newdiv.appendChild(inputName);
  
  var selectFlag = document.createElement("select");
  selectFlag.name = storeType + "Flag";
  selectFlag.style.width = '120px';
  selectFlag.style.margin = '5px';
  var option = document.createElement("option");
  option.value = 'p';
  option.innerHTML = "Percentage";
  selectFlag.onchange = function() {
	  if (this.value == 'p') {
			var hideTd = document.getElementById(divIdName+"V");
			var showTd = document.getElementById(divIdName+"P");
			console.log("show: " + hideTd.id);
			console.log("hide: " + showTd.id);
			hideTd.hidden= "true";
			showTd.removeAttribute("hidden");
 	  } else if (this.value == 'v') {
			var hideTd = document.getElementById(divIdName+"P");
			var showTd = document.getElementById(divIdName+"V");
			console.log("show: " + hideTd.id);
			console.log("hide: " + showTd.id);
			hideTd.hidden= "true";
			showTd.removeAttribute("hidden");
 	  }
  };
  selectFlag.appendChild(option);
  
  option = document.createElement("option");
  option.value = 'v';
  option.innerHTML = "Value";
  selectFlag.appendChild(option);
  newdiv.appendChild(selectFlag);
  
  var divPercentage = document.createElement("span");
  divPercentage.id = divIdName+"P";
  var inputP = document.createElement("input");
  inputP.type = "text";
  inputP.name = storeType+'Percentage';
  inputP.style.width = '40px';
  divPercentage.appendChild(inputP);
  
  divPercentage.innerHTML = divPercentage.innerHTML + ' % of ';
  divPercentage.style.width = '200px';
  var selectAttribute = document.createElement("select");
  selectAttribute.style.width = '200px';
  selectAttribute.style.margin = '5px';
  selectAttribute.name = storeType + "Attribute";
  option = document.createElement("option");
  option.value = 'retailPrice,inventory';
  option.innerHTML = "Net Merchandise Sales";
  selectAttribute.appendChild(option); 
  
  option = document.createElement("option");
  option.value = 'unitPrice,orderDetail';
  option.innerHTML = "Order Unit Price";
  selectAttribute.appendChild(option); 
  
  option = document.createElement("option");
  option.value = 'totAmt,order';
  option.innerHTML = "Order Total Amount";
  selectAttribute.appendChild(option); 
  
  option = document.createElement("option");
  option.value = 'factoryPrice,product';
  option.innerHTML = "Product Factory Price";
  selectAttribute.appendChild(option); 
  
  divPercentage.appendChild(selectAttribute);
  
  newdiv.appendChild(divPercentage);
  
  var divValue = document.createElement("span");
  divValue.id = divIdName+"V";
  divValue.hidden = "true";
  var inputV = document.createElement("input");
  inputV.type = "text";
  inputV.name = storeType+'Value';
  inputV.style.width = '40px';
  divValue.appendChild(inputV);
  
  divValue.innerHTML = divValue.innerHTML + ' dollar per unit ';
  
  newdiv.appendChild(divValue);
  
  var a = document.createElement("a");
  a.href = 'javascript:;';
  a.onclick = function() {
	  removeElement(divIdName);
  };
  console.log(divIdName);
  a.innerHTML = "Remove";
  a.style.color = '#2e7bc9';
  a.style.textDecoration = 'underline';
  newdiv.appendChild(a);
  
  ni.appendChild(newdiv);
  
  /*  newdiv.innerHTML = '<input type=\'text\' style=\'width:150px;\'placeholder=\'Input Cost Name\'>&nbsp;&#42;&nbsp;&#58;&nbsp;&nbsp;<input type=\'text\' style=\'width:50px;\'>&nbsp;<select style=\'width:240px;\'><option value=\'#\'>dollar per unit</option><option value=\'#\'>&#37; of Net Merchandise Sales</option></select>&nbsp;<a href=\'javascript:;\' onclick=\'removeElement('+divIdName+')\'>Remove</a>';
*/


}

function removeElement(divNum) {
  //var d = document.getElementById("myDiv");
  console.log("in remove element");
  var olddiv = document.getElementById(divNum);

  olddiv.parentNode.removeChild(olddiv);

  //d.removeChild(olddiv);

};

function textAreaAdjust(o) {
    o.style.height = "1px";
    o.style.height = (15+o.scrollHeight)+"px";
}

</script>

<script>

  $(function() {
    $( "#tabs" ).tabs({
      //event: "mouseover"
      collapsible: true
    });
  });


</script>
<script>
function goBack(){
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

		<div class="title-bar clearfix">
			<h1 class="l">Edit Business Rule</h1>
			<div id="Date" class="date l"></div>
			<a id='ReportTipIco' class="report-help open l recordable"
				memo="{id:'ReportTipIco',type:'page-tip',global:0}"
				href="javascript:void(0);">&nbsp;</a> <br />
			<br/>
			<div>
				<hr />
			</div>
		</div>
		<li style="list-style: none;">
			<span><a href="<%=basePath%>gotoorderlist.action">Home</a><span>&gt;</span></span>
			<span><a href="<%=basePath%>gotorulelist.action">Manage Business Rule</a><span> &gt; </span></span>
			<span> Edit Business Rule</span>
		</li>

		<!-- Success Message and Error Message -->
		<div class="success_area" style="display: none">successMessage</div>
		<div class="warning" style="display: none">errorMessage</div>
		<!-- Success Message and Error Message -->

		<form action=#>
			<input name="ruleId" type="hidden" value="${rule.getRuleId()}"disabled/>
			<table class="text" style="width: 850px;">
				<tr>
					<td style="width: 150px;">Rule Name&#58;</td>
					<td> <input style="width:200px;" type="text" name="ruleName" value ="${ruleName}" disabled>&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>Rule Description&#58;</td>
					<td><textarea name ="ruleDescription" style="overflow:hidden;max-width:200px;width:200px;height:50px;" onkeyup="textAreaAdjust(this)" disabled>${ruleDescription}</textarea></td>
				</tr>
				<tr>
					<td>Group<span class="red">*</span>&#58;</td>
					<td><textarea name="categoryname" id="tags" style="overflow:hidden;max-width:200px;width:200px;height:30px;" onkeyup="textAreaAdjust(this)" placeholder="Group name" readonly> All </textarea>
                    If not found, <a href="<%=basePath%>gotonewcategory.action">new Group</a>.
                    </td>
				</tr>
				<tr>
					<td>Stage&#58;</td>
					<td>Stage3: Candidate Optimization</td>
				</tr>
				<tr>
					<td>Rule Template&#58;</td>
					<td>Cost Calculate</td>
				</tr>
				<tr class="drl-height">
					<td>Rule Editor&#58;</td>
					<td>
						<div class="drl" >
							<div id="tabs">
								<ul>
									<li><a href="#tabs-SFS">Ship from Store</a></li>
									<li><a href="#tabs-warehouse">Fulfilled by Warehouse</a></li>
									<li><a href="#tabs-Vendor">Vendor Direct</a></li>
								</ul>
								<div id="tabs-SFS">
									<table>
										<c:forEach var="storeFee" items="${storeFeeList}">
											<tr id="storeFee${storeFee.costId}">
												<td ><input style="width:150px;" type="text"
													name="storeFeeName" value="${storeFee.getCostName()}" disabled/></td>
												<c:choose>
													<c:when test="${ (storeFee.flag == 'p') }">
														&nbsp;<input type="hidden"  name="storeFeeId"
															value="${storeFee.costId}" disabled>
														<td style="width:120px;">
															<select name="storeFeeFlag" onchange="chooseFormat(this.value, 'storeFee', '${storeFee.costId}')" style="margin-left:5px;"disabled>
																<option selected="selected" value="p">Percentage</option>
																<option value="v">Value</option>
															</select>
														</td>
														<td id="storeFeeP${storeFee.costId}"><input style="width:40px; margin-left:10px;"
															name="storeFeePercentage" type="text" value="${storeFee.percentage/100.0}" disabled> % of 
															<select style="width:200px; margin:5px;"name="storeFeeAttribute" disabled>
																<option value="retailPrice,inventory"
																	<c:if test="${storeFee.attribute == 'retailPrice,inventory'}">selected="selected"</c:if>>
																	Net Merchandise Sales</option>
																<option value="unitPrice,orderDetail"
																	<c:if test="${storeFee.attribute == 'unitPrice,orderDetail'}">selected="selected"</c:if>>
																	Order Unit Price</option>
																<option value="totAmt,order"
																	<c:if test="${storeFee.attribute == 'totAmt,order'}">selected="selected"</c:if>>
																	Order Total Amount</option>
																<option value="factoryPrice,product"
																	<c:if test="${storeFee.attribute == 'factoryPrice,product'}">selected="selected"</c:if>>
																	Product Factory Price</option>
															</select></td>
														<td hidden="true" style="width: 600px;"
															id="storeFeeV${storeFee.costId}"><input
															name="storeFeeValue" type="text" value=""
															style="width: 50px;"disabled> dollar per unit</td>
													</c:when>
													<c:otherwise>
														<input type="hidden" type="text" name="storeFeeId"
															value="${storeFee.costId}" style="width:150px;"disabled/>
														<td><select style="width:120px;"name="storeFeeFlag"
															onchange="chooseFormat(this.value, 'storeFee', '${storeFee.costId}')" disabled>
																<option value="p">Percentage</option>
																<option selected="selected" value="v">Value</option>
														</select></td>
														<td hidden="true" style="width: 600px;"
															id="storeFeeP${storeFee.costId}"><input
															name="storeFeePercentage" type="text" value=""
															style="width: 40px;"disabled> % of 
															<select name="storeFeeAttribute" style="width:200px;" disabled>
																<option value="retailPrice,inventory">Net Merchandise Sales</option>
																<option value="unitPrice,orderDetail">Order Unit Price</option>
																<option value="totAmt,order">Order Total Amount</option>
																<option value="factoryPrice,product">Product Factory Price</option>
															</select></td>
														<td style="width: 400px;" id="storeFeeV${storeFee.costId}"><input
															name="storeFeeValue" type="text"
															value="${storeFee.value/100.0}" style="width: 50px;"disabled> dollar per unit</td>
													</c:otherwise>
												</c:choose>
												</tr>
										</c:forEach>
									</table>
									
								</div>
								<div id="tabs-warehouse">
									<table>
										<c:forEach var="warehouseFee" items="${warehouseFeeList}">
											<tr id="warehouseFee${warehouseFee.costId}">
												<td style="width: 150px;"><input type="text"
													name="warehouseFeeName" value="${warehouseFee.getCostName()}" disabled></td>
												<c:choose>
													<c:when test="${ (warehouseFee.flag == 'p') }">
														<input type="hidden"  name="warehouseFeeId" value="${warehouseFee.costId}" disabled/>
														<td><select name="warehouseFeeFlag"
															onchange="chooseFormat(this.value, 'warehouseFee', '${warehouseFee.costId}')" style="width:120px; margin-left:5px;" disabled>
																<option selected="selected" value="p"> Percentage</option>
																<option value="v">Value</option>
															</select></td>
														<td style="width: 600px;" id="warehouseFeeP${warehouseFee.costId}">
														<input name="warehouseFeePercentage" type="text" value="${warehouseFee.percentage/100.0}" style="width: 40px; margin-left:10px;"disabled>
															% of <select style="width:200px; margin:5px;"name="warehouseFeeAttribute" disabled>
																<option value="retailPrice,inventory"
																	<c:if test="${warehouseFee.attribute == 'retailPrice,inventory'}">selected="selected"</c:if>>
																	Net Merchandise Sales</option>
																<option value="unitPrice,orderDetail"
																	<c:if test="${warehouseFee.attribute == 'unitPrice,orderDetail'}">selected="selected"</c:if>>
																	Order Unit Price</option>
																<option value="totAmt,order"
																	<c:if test="${warehouseFee.attribute == 'totAmt,order'}">selected="selected"</c:if>>
																	Order Total Amount</option>
																<option value="factoryPrice,product"
																	<c:if test="${warehouseFee.attribute == 'factoryPrice,product'}">selected="selected"</c:if>>
																	Product Factory Price</option>
														</select></td>
														<td hidden="true" style="width: 600px;"
															id="warehouseFeeV${warehouseFee.costId}"><input
															name="warehouseFeeValue" type="text" value=""
															style="width: 50px;"disabled> dollar per unit</td>
													</c:when>
													<c:otherwise>
														<input style="width: 150px;" type="hidden" type="text" name="warehouseFeeId"
															value="${warehouseFee.costId}" disabled/>
														<td><select style="width: 120px;"name="warehouseFeeFlag"
															onchange="chooseFormat(this.value, 'warehouseFee', '${warehouseFee.costId}')" disabled>
																<option value="p">Percentage</option>
																<option selected="selected" value="v">Value</option>
														</select></td>
														<td hidden="true" style="width: 600px;"
															id="warehouseFeeP${warehouseFee.costId}"><input
															name="warehouseFeePercentage" type="text" value=""
															style="width: 40px;"disabled> % of <select
															name="warehouseFeeAttribute" style="width: 200px;" disabled>
																<option value="retailPrice,inventory">Net Merchandise Sales</option>
																<option value="unitPrice,orderDetail">Order Unit Price</option>
																<option value="totAmt,order">Order Total Amount </option>
																<option value="factoryPrice,product">Product Factory Price</option>
														</select></td>
														<td style="width: 400px;" id="warehouseFeeV${warehouseFee.costId}">
															<input name="warehouseFeeValue" type="text" value="${warehouseFee.value/100.0}" style="width: 50px;" disabled>
															dollar per unit</td>
													</c:otherwise>
												</c:choose>
												</tr>
										</c:forEach>
									</table>
									
								</div>
								<div id="tabs-Vendor" >
									<table>
										<c:forEach var="vendorFee" items="${vendorFeeList}">
											<tr id="vendorFee${vendorFee.costId}">
												<td style="width: 150px;"><input type="text"
													name="vendorFeeName" value="${vendorFee.getCostName()}" disabled/></td>
												<c:choose>
													<c:when test="${ (vendorFee.flag == 'p') }">
														<input type="hidden"  name="vendorFeeId"
															value="${vendorFee.costId}" disabled/>
														<td><select style="width: 120px;"name="vendorFeeFlag"
															onchange="chooseFormat(this.value, 'vendorFee', '${vendorFee.costId}')" style="margin-left:5px;" disabled>
																<option selected="selected" value="p"> Percentage</option>
																<option value="v">Value</option>
														</select></td>
														<td style="width: 600px;" id="vendorFeeP${vendorFee.costId}">
															<input name="vendorFeePercentage" type="text"
															value="${vendorFee.percentage/100.0}" style="width: 40px;" disabled> % of 
															<select style="width: 200px; margin:5px;"name="vendorFeeAttribute" disabled>
																<option value="retailPrice,inventory"
																	<c:if test="${vendorFee.attribute == 'retailPrice,inventory'}">selected="selected"</c:if>>
																	Net Merchandise Sales</option>
																<option value="unitPrice,orderDetail"
																	<c:if test="${vendorFee.attribute == 'unitPrice,orderDetail'}">selected="selected"</c:if>>
																	Order Unit Price</option>
																<option value="totAmt,order"
																	<c:if test="${vendorFee.attribute == 'totAmt,order'}">selected="selected"</c:if>>
																	Order Total Amount</option>
																<option value="factoryPrice,product"
																	<c:if test="${vendorFee.attribute == 'factoryPrice,product'}">selected="selected"</c:if>>
																	Product Factory Price</option>
														</select></td>
														<td hidden="true" style="width: 600px;"
															id="vendorFeeV${vendorFee.costId}"><input
															name="vendorFeeValue" type="text" value=""
															style="width: 50px;" disabled> dollar per unit</td>
													</c:when>
													<c:otherwise>
														<input type="hidden" type="text" name="vendorFeeId"
															value="${vendorFee.costId}" disabled/>
														<td><select style="width: 120px;"name="vendorFeeFlag"
															onchange="chooseFormat(this.value, 'vendorFee', '${vendorFee.costId}')"disabled>
																<option value="p">Percentage</option>
																<option selected="selected" value="v">Value</option>
														</select></td>
														<td hidden="true" style="width: 600px;"
															id="vendorFeeP${vendorFee.costId}"><input
															name="vendorFeePercentage" type="text" value=""
															style="width: 40px;"disabled> % of 
															<select name="vendorFeeAttribute" style="width: 200px;" disabled>
																<option value="retailPrice,inventory">Net
																	Merchandise Sales</option>
																<option value="unitPrice,orderDetail">Order
																	Unit Price</option>
																<option value="totAmt,order">Order Total Amount
																</option>
																<option value="factoryPrice,product">Product
																	Factory Price</option>
														</select></td>
														<td style="width: 400px;" id="vendorFeeV${vendorFee.costId}"><input
															name="vendorFeeValue" type="text"
															value="${vendorFee.value/100.0}" style="width: 50px;"disabled>
															dollar per unit</td>
													</c:otherwise>
												</c:choose>
												</tr>
										</c:forEach>
									</table>
									<div>
										<div id="VendorDiv"><br/><br/></div>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td><input type="button" class="button" value="Back" onclick="goBack()">
						
					</td>
				
				</tr>
			</table>
		</form>
	</div>
</div>

</div>

<!-- content ends -->


<!-- footer starts -->

<div class="footer">
	<span>&copy;2013 eBusiness Team</span>
</div>
<!-- footer ends -->
</body>
</html>
