<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
   <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li class="" id="bizrulelist"><a href="<%=basePath%>gotorulelist.action">Business Rule List</a><span class="normal">&nbsp;</span></li>
                <li class="" id="newbizrulelist"><a href="<%=basePath%>gotonewbizrulelist.action">New Business Rule</a><span class="normal">&nbsp;</span></li>
                <li class="" id="ruleprioritylist"><a href="<%=basePath%>gotoruleprioritylist.action">Business Rule Priority</a><span class="normal">&nbsp;</span></li> 
            </ul>
        </li>   
        <li class=""><a class="recordable open" href="#" id="togglethree"
            memo="{id:'21',type:'menu',global:1,status:''}">Visualization Dashboard</a>
            <ul class="nav-two" id="navthree">
                <li class="" ><a id="orderlist" onclick="f(this)" href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
                <li class="" id="routelist"><a href="#">Routing visualization</a><span class="normal">&nbsp;</span></li>
                <li class="" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li>
                
            </ul>
        </li>
        <li class=""><a class="recordable open" href="#" id="togglefour"
            memo="{id:'21',type:'menu',global:1,status:''}">Place New Order</a>
            <ul class="nav-two" id="navtwo">
                <li class="" id="neworderlist"><a href="<%=basePath%>gotoplaceorder.action">New Order</a><span class="normal">&nbsp;</span></li>
                
            </ul>
		</li>   
    </ul>
    </div>
 <script>
  $(function() {
    $( "#accordion" ).accordion({
        collapsible: true,
        active: 0,
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
            
            <!-- form starts Ying edit according to html -->
            <div style="padding-left:5px;width:890px;">
                <div id="accordion" style="float:left;width:280px;margin-top:-3px;">
                        <h3>Order Details</h3>
                        <div style="border: 1px solid #aaaaaa;">
                            <ul>
                                <li class="" style="height:30px;"><a href="VisualizationOrderDetail.html">Order Details</a></li>
                            </ul>
                        </div>

                        <h3>Stage 1 - Fulfillment Eligibility</h3>
                        <div style="border: 1px solid #aaaaaa;">
                            <ul>
                                <li class="" style="height:30px;"><a href="VisualizationStage1AllRules.html">All Rules</a></li>
                                <li class="" style="height:30px;"><a href="VisualizationStage1Rule1.html">Rule 1</a></li>
                                <li class="" style="height:30px;"><a href="VisualizationStage1Rule2.html">Rule 2</a></li>
                                <li class="" style="height:30px;"><a href="VisualizationStage1Rule3.html">Rule 3</a></li>
                            </ul>
                        </div>
                        
                        <h3>Stage 2 - Candidate Determination</h3>
                        <div style="border: 1px solid #aaaaaa;">
                            <ul>
                                <li style="height:30px;"><a href="VisualizationStage2-1.html">Package 1</a></li>
                                <li style="height:30px;"><a href="VisualizationStage2-2.html">Package 2</a></li>
                                <li style="height:30px;"><a href="VisualizationStage2-3.html">Package 3</a></li> 
                            </ul>
                        </div>
                    
                        <h3>Stage 3 - Evaluation</h3>
                        <div style="border: 1px solid #aaaaaa;">
                            <ul>
                                <li class="" style="height:30px;"><a href="VisualizationStage3-1.html">Route 1</a></li>
                                <li class="" style="height:30px;"><a href="VisualizationStage3-2.html">Route 2</a></li>
                                <li class="" style="height:30px;"><a href="VisualizationStage3-3.html">Route 3</a></li>
                            </ul>
                        </div>
                    </div>
                
                <div id="block2" style="float:left;height:465px;width:600px;border:1px solid #ccc;border-radius:5px;overflow-y:scroll;">
                    <form>
                        <div style="padding-left:30px;padding-top:30px;">
                            <div style="padding-bottom:30px;">
                                <div style="float:left; width:150px">Order Date:</div>
                                <div id="orderdate" style="float:left; width:350px">${order.orderDate}</div>
                            </div>
                            <div style="padding-bottom:30px;">
                                <div style="float:left; width:150px">Shipping Address:</div>
                                <div id="address" style="float:left; width:350px">${order.shippingAddr}</div>
                            </div>
                            <div style="padding-bottom:30px;">
                                <div style="float:left; width:150px">Order Details:</div>
                                <div id="products" style="float:left; width:350px" class="table-list">
                                    <br/>
                                    <table cellspacing="0" cellpadding="0" class="list">
                                        <tr class="title">
                                            <th style="width:200px;">Product</th>
                                            <th>Quantity</th>
                                        </tr>
                                        <c:forEach items="${details}" var="orderdetail">
                    						<tr>
                        						<td>${orderdetail.getProduct().getProdName()}</td>
                        						<td>${orderdetail.qty}</td>
                    						</tr>
                  						</c:forEach>
                                    </table>
                                </div>
                                <div style="display: block; width: 500px">
                                	
                                	<h1><b> Stage 1 </b></h1>
                                	<c:forEach var="name" items="${stage1.getRuleNames()}">
										<div> 
											<h1> ${name} </h1>
											<c:forEach var="logdetail" items="${stage1.getLogsByName(name)} ">
												<p> 
													${logdetail}
												</p>
											</c:forEach>
											<br/>
									    </div>
									</c:forEach>
									
									<h1><b> Stage 2 </b></h1>
									<c:forEach var="name" items="${stage2.getRuleNames()}">				
										<div> 
											<h1> ${name} </h1>
											<c:forEach var="logdetail" items="${stage2.getLogsByName(name)} ">
												<p> 
													${logdetail}
												</p>
											</c:forEach>
											<br/>
									    </div>
									</c:forEach>
									
									<h1><b> Stage 3 </b></h1>
									<c:forEach var="name" items="${stage3.getRuleNames()}">				
										<div> 
											<h1> ${name} </h1>
											<c:forEach var="logdetail" items="${stage3.getLogsByName(name)} ">
												<p> 
													${logdetail}
												</p>
											</c:forEach>
											<br/>
									    </div>
									</c:forEach>
									
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- form ends -->
            <!-- form ends -->

        </div>

        </div>
    
    <!-- content ends -->        
    <!-- footer starts -->
        </div>
            <div class="footer"><span>Â©2013 eBusiness Team</span></div>
        </div>
    <!-- footer ends -->

    </div>
</body>
</html>
