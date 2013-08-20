<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
                <li class="selected" ><a id="orderlist"  href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
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
            <div style="padding-left:5px;width:760px;">
                <div id="block1" style="float:left;height:465px;width:150px;border:1px solid #ccc;border-radius:5px;margin-right:1px;">
                <div id="accordion" style="float:left;width:150px;margin-top:-3px;">
                
                    <h3>Stage 1</h3>
                    <div>
                        <ul>
                            <li class="" style="height:30px;"><a href="#">All Rules</li>
                            <li class="" style="height:30px;"><a href="#">Rule 1</li>
                            <li class="" style="height:30px;"><a href="#">Rule 2</li>
                            <li class="" style="height:30px;"><a href="#">Rule 3</li>
                            
                        </ul>
                    </div>
                    <h3>Stage 2</h3>
                    <div>
                        <ul>
                            <li class="" style="height:30px;"><a href="#">Package 1</a></li>
                            <li class="" style="height:30px;"><a href="#">Package 2</a></li>
                            <li class="" style="height:30px;"><a href="#">Package 3</a></li> 
                        </ul>
                    </div>
                    <h3>Stage 3</h3>
                    <div>
                        <ul>
                            <li class="" style="height:30px;"><a href="#">Route 1</a></li>
                            <li class="" style="height:30px;"><a href="#">Route 2</a></li>
                            <li class="" style="height:30px;"><a href="#">Route 3</a></li>
                            <li class="" style="height:30px;"><a href="#">Route 4</a></li>
                            <li class="" style="height:30px;"><a href="#">Route 5</a></li>
                        </ul>
                    </div>
                </div>
                </div>
                
                <div id="block2" style="float:left;height:465px;width:600px;border:1px solid #ccc;border-radius:5px;overflow-y:scroll;">
                    <form action="#">
                    <c:forEach var="ruleResult" items="${ruleResults}">
                        <div style="padding-left:30px;padding-top:30px;">
                            <div id="rule1">
                                <div style="padding-bottom:30px;">
                                    <div style="float:left; width:100px">Rule 1:</div>
                                    <div id="rule1" style="float:left; width:350px">${ruleResult.getRuleName()}</div>
                                </div>
                                <div style="padding-bottom:30px;padding-left:100px;">${ruleResult.getResult()}</div>
                            </div>
					</c:forEach>
					<!--  
                            <div id="rule2">
                                <div style="padding-bottom:30px;">
                                    <div style="float:left; width:100px">Rule 2:</div>
                                    <div id="rule1" style="float:left; width:350px">Special Route</div>
                                </div>
                                <div style="padding-bottom:30px;padding-left:100px;">Minimum package number changed from 2 to 3.</div>
                            </div>

                            <div id="rule3">
                                <div style="padding-bottom:30px;">
                                    <div style="float:left; width:100px">Rule 3:</div>
                                    <div id="rule1" style="float:left; width:350px">Store Threshold</div>
                                </div>
                                <div style="padding-bottom:30px;padding-left:100px;">Candidate fulfillment source count changed from 580 to 300.</div>
                            </div>
                      -->      
                            <div style="padding-bottom:30px;">
                                <div style="float:left; width:100px">Result:</div>
                                <div id="result" style="float:left; width:350px" class="table-list">
                                    <br/>
                                    <table cellspacing="0" cellpadding="0" class="list">
                                        <tr class="title">
                                            <th style="width:100px;">Package #</th>
                                            <th style="width:150px;">Product</th>
                                            <th style="width:100px;">Quantity</th>
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>Kayak</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>Shoes</td>
                                            <td>2</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>Hat</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>Shirt</td>
                                            <td>1</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- form ends -->


           
        </div>

        </div>
    
    <!-- content ends -->        
    <!-- footer starts -->
        </div>
            <div class="footer"><span>©2013 eBusiness Team</span></div>
        </div>
    <!-- footer ends -->

    </div>
</body>
</html>