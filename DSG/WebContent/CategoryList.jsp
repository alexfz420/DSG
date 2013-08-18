<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
 <jsp:include page="template_top.jsp" />
 <ul class="nav">
        <li class=""><a class="recordable open" id="toggleone" href="#" 
            memo="{id:'21',type:'menu',global:1,status:''}">Manage Group</a>
            <ul class="nav-two" id="navone">
                <li class="selected" id="catelist"><a href="<%=basePath%>gotocategorylist.action?act=store">Group List</a><span class="normal">&nbsp;</span></li>
                <li class="" id="newcatelist"><a href="<%=basePath%>gotonewcategory.action">New Group</a><span class="normal">&nbsp;</span></li>  
            </ul>
        </li>
        <li class=""><a class="recordable open" href="#" id="toggletwo"
            memo="{id:'21',type:'menu',global:1,status:''}">Manage Business Rule</a>
            <ul class="nav-two" id="navtwo">
                <li class="" id="bizrulelist"><a href="<%=basePath%>gotorulelist.action">Business Rule List</a><span class="normal">&nbsp;</span></li>
                <li class="" id="newbizrulelist"><a href="<%=basePath%>gotonewbizrulelist.action">New Business Rule</a><span class="normal">&nbsp;</span></li>
                <li class="" id="ruleprioritylist"><a href="<%=basePath%>gotoruleprioritylist.action">Business Rule Priority</a><span class="normal">&nbsp;</span></li> 
                
            </ul>
        </li>   
        <li class=""><a class="recordable open" href="#" id="togglethree"
            memo="{id:'21',type:'menu',global:1,status:''}">Visualization Dashboard</a>
            <ul class="nav-two" id="navthree">
                <li class="" ><a id="orderlist" onclick="f(this)" href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
                <li class="" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li> 
            </ul>
        </li>
    </ul>
    </div>

  <script defer="defer" >
 	 window.onload=function(){
 		var act =  document.getElementById('act').value;
        if(act=="store") {
            document.getElementById('store_category_list').style.display = 'block';
            document.getElementById('product_category_list').style.display = 'none';
            document.getElementById('Paging').style.display = 'block';
            document.getElementById('category_store').checked = true;            
        } else if(act=="product") {
            document.getElementById('product_category_list').style.display = 'block';
            document.getElementById('store_category_list').style.display = 'none';
            document.getElementById('Paging').style.display = 'block';            
            document.getElementById('category_product').checked =true;
        }
 	 }   
 	</script>
<script>
 function displayCategoryList(obj) {
    var type = obj.value;
 
    
    if(type=="Store") {
     	window.location.href="displayCategoryList.action?act=store";  

    } else if(type=="Product") {
        window.location.href="displayCategoryList.action?act=product";   
    };
 }
 
 function deleteSelect(){
	if(document.getElementById('category_store').checked ==true){
		var storeCateList = document.getElementsByName('storeList');
		var string="";
		for(var i=0; i<storeCateList.length;i++){
			if(storeCateList[i].checked ==true){
				string += storeCateList[i].value+",";
			}
		}
		var l = string.length;
		if(l!=0){
			string=string.substring(0,l-1);
			window.location.href="deleteCategory.action?act=store&ids="+string;  
		}
     	
	}else if(document.getElementById('category_product').checked ==true){
		var prodCateList = document.getElementsByName('prodList');
		var string="";
		for(var i=0; i<prodCateList.length;i++){
			if(prodCateList[i].checked ==true){
				string += prodCateList[i].value+",";
			}
		}
		var l = string.length;
		if(l!=0){
			string=string.substring(0,l-1);
		    window.location.href="deleteCategory.action?act=product&ids="+string; 
		}
		 
	}
		
	 
 }
</script>
 

		<!-- content starts -->
		<input type="hidden" value="${act}" id="act"/>
		<div class="minibar recordable" id="minibar"
			memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}"
			style="display: none;">
			<a id="menu-untoggle" href="javascript:void(0)" class="unfold"></a>
		</div>
		<div class="main" id="main-body">
			<div class="content clearfix">
				<div class="title-bar clearfix" style="height:80px;">
					<h1 class="l">Manage Group</h1>
					<div id="Date" class="date l"></div>
					<a id='ReportTipIco' class="report-help open l recordable"
						memo="{id:'ReportTipIco',type:'page-tip',global:0}"
						href="javascript:void(0);">&nbsp;</a> <br />
					<br />
					<div>
						<hr/>
					</div>

					<li style="list-style: none;">
						<span><a href="#">Home</a><span> &gt; </span></span>
						<span><a href="">Manage Group</a></span><span> &gt; </span>
						<span>Group List</span>
					</li>

					<!-- Success Message and Error Message -->
					<div class="success_area" style="display: none">successMessage</div>
					<div class="warning" style="display: none">errorMessage</div>
					<!-- Success Message and Error Message -->

				</div>

					<div id="Outline" class="table-list">
						<form action="">
							Group Type: <input type="radio" name="category_type" value="Store" id="category_store" onClick="displayCategoryList(this);" checked="checked">Fulfillment Method
							<input type="radio" name="category_type" value="Product" id="category_product" onClick="displayCategoryList(this);">Product
						<br/><hr/>
						<br/>
						
						<div>
						<a class="button" id="add-to-cart" href="#">Delete Selected </a>&nbsp;| &nbsp; 
						<a href="<%=basePath%>gotonewcategory.action" class="button"> + New Group</a>
						<div class="r">
							<input type="text" class="searchtext" placeholder="Category name" />
							<a href="#" class="button">Search</a>
						</div>
					</div>
					<br />
							<div id="store_category_list">
								<table cellspacing="0" cellpadding="0" class="list">
									<tbody>
										<tr class="title">
											<th><input type="checkbox" id="selectall" /></th>
											<th>Group Name</th>
											<th>Description</th>
											<th>Applied Rules</th>
											<th>Action</th>
										</tr>
										
										<c:forEach var="storeCategory" items="${storeCategoryList}">
											<tr>
												<td class=""><input name="storeList" value="${storeCategory.id.cateStoreId}" type="checkbox" class="case" /></td>
												<td class="">${storeCategory.cateName}</td>
												<td class="">${storeCategory.cateDescr}</td>
												<td class="">${storeCategory.appliedRuleList}</td>
												<td class="">
													<a class="button" href="viewStoreCategory.action?categoryId=${storeCategory.id.cateStoreId}">View</a>
													<a class="button" href="<%=basePath%>gotocreatecategory.action">Edit</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

							<div id="product_category_list">
								<table cellspacing="0" cellpadding="0" class="list">
									<tbody>
										<tr class="title">
											<th><input type="checkbox" id="selectalltwo" /></th>
											<th>Group Name</th>
											<th>Description</th>
											<th>Applied Rules</th>
											<th>Action</th>
										</tr>
										<c:choose>
											<c:when test="${ (empty prodCategoryList) }">
											</c:when>
											<c:otherwise>
												<c:forEach var="prodCategory" items="${prodCategoryList}">
												<tr>
												<td class=""><input type="checkbox" name="prodList" value="${prodCategory.id.cateProdId}" class="case" /></td>
												<td class="">${prodCategory.cateName}</td>
												<td class="">${prodCategory.cateDescr}</td>
												<td class="">${prodCategory.appliedRuleList}</td>
												<td class="">
													<a class="button" href="<%=basePath%>viewProdCategory.action?categoryId=${prodCategory.id.cateProdId}">View</a>
													<a class="button" href="<%=basePath%>gotocreatecategory.action">Edit</a>
												</td>
											</tr>
										</c:forEach>
				       							 
			       							</c:otherwise>
										
										</c:choose>
										
									</tbody>
								</table>
							</div>

						</form>
						<div id="Paging" >
							<div class="paging clearfix">
								<div class="page-size">Items per page:10</div>
								<div class="page-number">
									<a class="number selected" href="javascript:void(0);" data="1">1</a>
									<a href="javascript:void(0);" class="next" data="2">Next&gt;</a>Page
									<input class="text" type="text"> <a
										href="javascript:void(0)" class="button"><span>Go</span></a>
								</div>
							</div>
						</div>

					</div>

					<!-- Deletion Pop up -->
					<div id="pop-back"></div>
					<div id="cart1" class="cart">
						<div class="popheader">
							<span class="title"><strong>&nbsp;Delete Group</strong></span>
						</div>
						<br>
						<div>&nbsp;&nbsp;Are you sure you want to delete the
							group?</div>

						<div class="r" style="margin-right: 20px;">
							<a class="button" onclick="deleteSelect()" type="submit">Yes</a> <a
								class="button" onclick="closePop()" type="button">No</a>
						</div>
					</div>
					<!-- Deletion pop-up ends -->

				</div>

				<!-- content ends -->

<jsp:include page="template_bottom.jsp" />
			
