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
                <li class="" id="catelist"><a href="<%=basePath%>gotocategorylist.action">Group List</a><span class="normal">&nbsp;</span></li>
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
                <li class="" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li>
            </ul>
        </li>   
    </ul>
    </div>
    
    
    <script>
    function SubmitForm(rule,view)
    {
   	 console.log(rule);
   	 document.getElementById('viewEdit').value= view;
   	 document.getElementById('ruleId').value= rule;

      document.forms['myForm'].submit() ;
    }
    </script>
    <!-- menu bar ends -->

    <!-- content starts -->
    <form name ="myForm" action="goToEdit">
    <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix" style="height:80px;">
            <h1 class="l">Manage Business Rules</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

            <li style="list-style:none;">
                <span><a href="#">Home</a><span> &gt; </span></span>
                <span>Manage Business rules</span>   
            </li>

            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none;">successMessage</div>
            <div class="warning" style="display:none;">errorMessage</div>
            <!-- Success Message and Error Message -->

        </div>
        
            <div>
            <a class="button" id="add-to-cart" href="#">Delete Selected </a>&nbsp; |  &nbsp;
            <a href="<%=basePath%>gotonewbizrulelist.action" class="button"> + New Rule</a>
            <div class="r"><input type="text" class="searchtext" placeholder="Rule name"/>
                <a href="#" class="button">Search</a>
            </div>
            </div>
        <br/>
        
        <div id="Outline" class="table-list">
        <table cellspacing="0" cellpadding="0" class="list">
            <tbody><tr class="title">
                <th><input type="checkbox" id="selectall"/></th>
                <th>Rule Number</th>
                <th>Rule Name</th>
                <th>Description</th>
                <th>Type</th>
                <th>Status</th>
                <th>Action</th>
                </tr>
                
                <c:set var="ruleNum" value="1" />
                 <c:forEach var="allRule" items="${allRule}" >
            
            		<tr style="height:30px;">
            				<td class=""><input type="checkbox" class="case"/></td>
            				<td style="width:10px;"class="">${ruleNum}</td>
		                    <td class="">${allRule.ruleName.replace("%20"," ")}</td>
		                    <td class="">${allRule.ruleDescr}</td>
		                    <c:if test="${allRule.type == '9'}">
		                    <td class="">Default</td>
		                    <c:choose>
								<c:when test="${ (allRule.able == true) }">
									<td> <a class="button toggle disabled">&nbsp;&nbsp;Active&nbsp;&nbsp;</a> </td>
								</c:when>
								<c:otherwise>
									<td> <a class="button toggle disabled">Disabled</a> </td>
								</c:otherwise>
							</c:choose>
		                    <td class="">
		                    <a class="button toggle disabled" >View</a> 
		                    <a class="button toggle disabled" >Edit</a>
						</c:if>
						<c:if test="${allRule.type == '1'}">
		                    <td class="">Product Threshold</td>
		                    <c:choose>
								<c:when test="${ (allRule.able == true) }">
									<td> <a class="button toggle" onclick='SubmitForm(${allRule.ruleId},"ables")'>&nbsp;&nbsp;Active&nbsp;&nbsp;</a> </td>
								</c:when>
								<c:otherwise>
									<td> <a class="button toggle disabled" onclick='SubmitForm(${allRule.ruleId},"ables")'>Disabled</a> </td>
								</c:otherwise>
							</c:choose>
		                    <td class="">
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"view")'>View</a> 
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"edit")'>Edit</a>
						</c:if>
						<c:if test="${allRule.type == '2'}">
		                    <td class="">Store Threshold</td>
		                    <c:choose>
								<c:when test="${ (allRule.able == true) }">
									<td> <a class="button toggle" onclick='SubmitForm(${allRule.ruleId},"ables")'>&nbsp;&nbsp;Active&nbsp;&nbsp;</a> </td>
								</c:when>
								<c:otherwise>
									<td> <a class="button toggle disabled" onclick='SubmitForm(${allRule.ruleId},"ables")'>Disabled</a> </td>
								</c:otherwise>
							</c:choose>
		                    <td class="">
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"view")'>View</a> 
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"edit")'>Edit</a>
						</c:if>
						<c:if test="${allRule.type == '3'}">
		                    <td class="">Special Route</td>
		                    <c:choose>
								<c:when test="${ (allRule.able == true) }">
									<td> <a class="button toggle" onclick='SubmitForm(${allRule.ruleId},"ables")'>&nbsp;&nbsp;Active&nbsp;&nbsp;</a> </td>
								</c:when>
								<c:otherwise>
									<td> <a class="button toggle disabled" onclick='SubmitForm(${allRule.ruleId},"ables")'>Disabled</a> </td>
								</c:otherwise>
							</c:choose>
		                    <td class="">
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"view")'>View</a> 
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"edit")'>Edit</a>
		                    </td>
						</c:if>
						
						<c:if test="${allRule.type == '5'}">
		                    <td class="">Cost Calculation</td>
		                    <c:choose>
								<c:when test="${ (allRule.able == true) }">
									<td> <a class="button toggle disabled" >&nbsp;&nbsp;Active&nbsp;&nbsp;</a> </td>
								</c:when>
								<c:otherwise>
									<td> <a class="button toggle disabled">Disabled</a> </td>
								</c:otherwise>
							</c:choose>
		                    <td class="">
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"view")'>View</a> 
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"edit")'>Edit</a>
		                    </td>
						</c:if>
						<c:if test="${allRule.type == '6'}">
		                    <td class="">Evaluation</td>
		                    <c:choose>
								<c:when test="${ (allRule.able == true) }">
									<td> <a class="button toggle disabled">&nbsp;&nbsp;Active&nbsp;&nbsp;</a> </td>
								</c:when>
								<c:otherwise>
									<td> <a class="button toggle disabled">Disabled</a> </td>
								</c:otherwise>
							</c:choose>
		                    <td class="">
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"view")'>View</a> 
		                    <a class="button" onclick='SubmitForm(${allRule.ruleId},"edit")'>Edit</a>
		                    </td>
						</c:if>
		            	 </tr>
                <c:set var="ruleNum" value="${ruleNum+1}" />
				</c:forEach>
                <input type="hidden" id="ruleId" name="ruleId"> 
                <input type="hidden" id="viewEdit" name="viewEdit"> 
                
                
                </tbody>
            </table>
            <div id="Paging">
                <div class="paging clearfix">
                    <div class="page-size">Items per page: 10
                    </div>
                    <div class="page-number">
                        <a class="number selected" href="javascript:void(0);" data="1">1</a><a class="number" href="javascript:void(0);" data="2">2</a><a class="number" href="javascript:void(0);" data="3">3</a>...<a class="number" href="javascript:void(0);" data="5">5</a><a href="javascript:void(0);" class="next" data="2">Next&gt;</a>Page <input class="text" type="text"> <a href="javascript:void(0)" class="button"><span>Go</span></a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Deletion Pop up -->
        <div id="pop-back"></div>
        <div id="cart1" class="cart">
            <div class="popheader"> 
              <span class="title"><strong>&nbsp;Delete Category</strong></span>          
            </div><br>
            <div>&nbsp;&nbsp;Are you sure you want to delete all selected categry(categories)?</div>
            
            <div class="r" style="margin-right:20px;">
              <a class="button" onclick="closePop()" type="submit">Yes</a>
              <a class="button" onclick="closePop()" type="button">No</a>
            </div>
        </div>
        <!-- Deletion pop-up ends -->

        </div>
    
    <!-- content ends -->        
    <!-- footer starts -->
        </div>
            <div class="footer"><span>&copy;2013 eBusiness Team</span></div>
        </div>
        </form>
    <!-- footer ends -->

    </div>
</body>

</html>
