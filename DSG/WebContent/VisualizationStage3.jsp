<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
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
                <li class="" id="catelist"><a href="<%=basePath%>gotocategorylist.action?act=store">Group List</a><span class="normal">&nbsp;</span></li>
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
                <li class="selected" ><a id="orderlist" onclick="f(this)" href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
				<li class="" id="statlist"><a href="<%=basePath %>statistics.action">Statistics</a><span class="normal">&nbsp;</span></li>

			</ul>


        </li>
   
    </ul>
</div>
 <script>
  $(function() {
    $( "#accordion" ).accordion({
        collapsible: true,
        active: 2,
    });
  });
  </script>
    <!-- menu bar ends -->


    <!-- content starts -->
    <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix">
            <h1 class="l">Routing Visualization</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

            <li style="list-style:none;">
                <span><a href="#">Home</a><span> &gt; </span></span>
                <span><a href="#">Visualization Dashboard</a><span> &gt; </span></span>
                <span><a href="#">Order List</a><span> &gt; </span></span>                
                <span>Order Routing Visualization<span>   
            </li>

            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none;">successMessage</div>
            <div class="warning" style="display:none;">errorMessage</div>
            <!-- Success Message and Error Message -->
            <br/>
            <!-- form starts-->
            <form action="">
            <div style="padding-left:5px;width:760px;">
                <div id="block1" style="float:left;height:465px;width:150px;border:1px solid #ccc;border-radius:5px;margin-right:1px;">
                    <div id="accordion" style="float:left;width:150px;margin-top:-3px;">
                        <h3>Stage 1</h3>
                        <div>
                            <ul>
                            	<c:forEach items="${appliedRules}" var="appliedrule">
                                	<li style="height:30px;"><a href="#">${appliedrule}</li>
                            	</c:forEach>
                            </ul>
                        </div>
                        
                        <h3>Stage 2</h3>
                        <div>
                            <ul>
                                <c:forEach items="${packages}" var="package">
                                	<li style="height:30px;"><a href="#">${package}</li>
                            	</c:forEach>
                            </ul>
                        </div>
                    
                        <h3>Stage 3</h3>
                        <div>
                            <ul>
                                <c:forEach items="${routes}" var="route">
                                	<li style="height:30px;"><a href="#">${route}</li>
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                
                <div id="block2" style="float:left;height:465px;width:600px;border:1px solid #ccc;border-radius:5px;overflow-y:scroll;">
                    <div style="padding-left:20px;">
                        <div id="route" style="height:30px;font-size:18px;margin-top:10px;padding-bottom:10px;">
                            Route 1
                        </div>
                        
                        <div id="route_detail" style="height:30px;">
                            <div style="height:20px;">
                                <div style="float:left;height:10px;width:50px;font-size:12px;">
                                    From:
                                </div>
                                <div id="source" style="float:left;height:10px;width:60px;font-size:12px;">
                                    Store 010
                                </div>
                            </div>
                            <div style="height:20px;">
                                <div style="float:left;height:10px;width:50px;font-size:12px;">
                                    To:
                                </div>
                                <div id="destination" style="float:left;height:10px;width:300px;font-size:12px;">
                                    5000 Forbes Ave, Pitsburgh, PA 15213
                                </div>
                            </div>
                        </div>

                        <div id="included" style="height:60px;margin-top:30px;">
                            <div style="height:20px;font-size:14px;">
                                Included Packages:
                            </div>
                            
                            <div>
                                <div name="packages" style="float:left;padding-left:20px;height:20px;font-size:12px;">Package 1: </div>
                                <div name="items" style="float:left;">
                                	<c:forEach items="${items}" var="item">
                                    	<div name="item" style="padding-left:30px;height:20px;font-size:12px;">${item.name}&#58; quantity ${item.quantity}</div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                        <div id="cost" style="height:100px;margin-top:20px;">
                            <div name="splitNo" style="float:left;height:20px;font-size:14px;">
                                Cost Detail:
                            </div>
                            <div id="products" style="float:left; width:350px; padding-left:10px;" class="table-list">
                                <table cellspacing="0" cellpadding="0" class="list">
                                    <tr class="title">
                                        <th>Cost</th>
                                        <th>Amount</th>
                                    </tr>
                                    <c:forEach items="${cost}" var="cost">
                                    	<tr>
                                        	<td>Transaction Fees</td>
                                        	<td>${cost.transaction}</td>
                                    	</tr>
                                    	<tr>
                                        	<td>Freight In to Store Cost</td>
                                        	<td>${cost.freight}</td>
                                    	</tr>
                                    	<tr>
                                        	<td>Distribution Center Cost</td>
                                        	<td>${cost.dc}</td>
                                    	</tr>
                                    	<tr>
                                        	<td>Packaging Cost</td>
                                        	<td>${cost.packaging}</td>
                                    	</tr>
                                    	<tr>
                                    	    <td>Labor Cost</td>
                                        	<td>${cost.labor}</td>
                                    	</tr>
                                    	<tr>
                                        	<td></td>
                                        	<td></td>
                                    	</tr>
                                    	<tr>
                                        	<td>Total Cost</td>
                                        	<td>${cost.total}</td>
                                    	</tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </form>
            <!-- form ends -->


           
        </div>

        </div>
    
    <!-- content ends -->        
    <!-- footer starts -->
        </div>
            <div class="footer"><span>&copy;2013 eBusiness Team</span></div>
        </div>
    <!-- footer ends -->

    </div>
</body>
</html>