<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser;"%>
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
                <li class="selected" ><a id="orderlist" onclick="f(this)" href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
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
<style>
    div.title{font-size:16px;padding-bottom:30px;}
    div.left{float:left; width:150px}
    div.right{float:left; width:350px}
    div.include{float:left;height:auto;width:210px;font-size:14px;padding-bottom:15px;"}
    div.item{height:auto;font-size:12px;width:230px;padding-left:10px;padding-bottom:5px;}
    div.split{height:auto;font-size:14px;width:120px;padding-bottom:15px;}
    div.product{float:left;height:auto;font-size:14px;width:210px;padding-left:10px;padding-bottom:5px;}
    div.message{height:auto;font-size:12px;padding-bottom:5px;}
    div.address{float:left;height:auto;width:500px;font-size:12px;padding-bottom:10px;}
    div.source{float:left;height:auto;width:50px;font-size:12px;padding-bottom:10px;}
    div.package{float:left;padding-left:20px;height:auto;font-size:12px;padding-bottom:5px;}
    div.item2{padding-left:30px;height:auto;font-size:12px;padding-bottom:5px;width:450px;}
    div.subtitle{height:auto;font-size:14px;padding-bottom:15px;}
</style>
<script >
  function changeDiv(obj) {
        var className = obj.className;
        var name = obj.name;
        if(className=="button"){
            var block = document.getElementsByClassName("block");
            console.log("name: " + name);
            for(var i in block){
                if(name==block[i].id){
                    console.log("show block id: " + block[i].id);
                    $('#'+block[i].id).show();
                }
                else{
                    console.log("hide block id: " + block[i].id);
                    $('#'+block[i].id).hide();
                }
            }
        }
        else{
            var block = document.getElementsByClassName("block");
            console.log("class name: " + className);
            for(var i in block){
                if(className==block[i].id){
                    console.log("show block id: " + block[i].id);
                    $('#'+block[i].id).show();
                }
                else{
                    console.log("hide block id: " + block[i].id);
                    $('#'+block[i].id).hide();
                }
            }
        }
  }
</script>    

 <script>
  $(function() {
    $( "#accordion" ).accordion({
        collapsible: true,
        active: 0,
    });
  });
  
  $(function() {
    $('.ui-widget-content').css("height","310px"); 
  });
  
  // Add javascript


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
                <span>Order Routing Visualization</span>   
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
                                 <li style="height:30px;"><a onClick="changeDiv(this)" class="detail">Order Details</a></li>
                            </ul>
                        </div>

                        <h3>Stage 1 - Fulfillment Eligibility</h3>
                        <div style="border: 1px solid #aaaaaa;">
                            <ul>
                            <li style="height:30px;"><a onClick="changeDiv(this)" class="stage1AllRule">All Rules</a></li>
                            <c:forEach var="log" items="${stage1.getLogs()}" varStatus="index">
                            	<li style="height:30px;padding-bottom:10px;"><a onClick="changeDiv(this)" class="stage1rule${index.index}">Rule${index.count}: ${log.getName()}</a></li>                                </tr>                               
                            </c:forEach>
                            </ul>
                        </div>
                        
                        <h3>Stage 2 - Delivery Option</h3>
                        <div style="border: 1px solid #aaaaaa;" id="stage2Header">
                            <ul>
                                <c:forEach var="pack" items="${packages}" varStatus="index">
                                    <li style="height:30px;"><a onClick="changeDiv(this)" class="stage2package${index.index}">Package${index.count}</a></li>                                            
                                </c:forEach>
                            </ul>
                        </div>
                    
                        <h3>Stage 3 - Allocation Optimization</h3>
                        <div style="border: 1px solid #aaaaaa;">
                            <ul>
                                <c:forEach var="pack" items="${packages}" varStatus="index">
                                    <li style="height:30px;"><a onClick="changeDiv(this)" class="stage3route${index.index}"> Package${index.count} </a></li>                                
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                
                <div id="block2" style="float:left;height:465px;width:600px;border:1px solid #ccc;border-radius:5px;overflow-y:scroll;">
                        <div style="padding-left:30px;padding-top:30px;" id="detail" class="block">
                            <div style="padding-bottom:30px;">
                                <div class="left">Order Date:</div>
                                <div id="orderdate" class="right">${order.orderDate}</div>
                            </div>
                            <div style="padding-bottom:30px;">
                                <div class="left">Shipping Address:</div>
                                <div id="address" class="right">${order.shippingAddr}</div>
                            </div>
                            <div style="padding-bottom:30px;">
                                <div class="left">Order Details:</div>
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
                            </div>
                        </div>
                        
                        <!-- Add block for stage 1 -->
                        <div id="stage1AllRule" class="block" style="display:none;padding-left:30px;padding-top:30px;">
                            <div class="title">All Rules</div>
                            <c:forEach var="log" items="${stage1.getLogs()}" varStatus="index">
                                <div id="rule${log.getIndex()}">
                                    <div style="padding-bottom:30px;">
                                        <div style="float:left; width:100px;">Rule ${index.count}:</div>
                                        <div id="rule${index.index}" class="right">${log.getName()}</div>
                                    </div>
                                    <div style="padding-bottom:30px;padding-left:100px;"> 
                                        <c:forEach var="logdetail" items="${log.getLogs()} ">
                                            ${logdetail} 
                                            <br/>
                                        </c:forEach>
                                    </div>
                                </div>                                                              
                            </c:forEach>

                            <div style="padding-bottom:30px;">
                                <div style="float:left; width:100px;">Result:</div>
                                <div style="float:left; width:350px" class="table-list">
                                    <br/>
                                    <table cellspacing="0" cellpadding="0" class="list">
                                        <tr class="title">
                                            <th style="width:100px;">Package #</th>
                                            <th style="width:150px;">Product</th>
                                            <th style="width:100px;">Quantity</th>
                                        </tr>
                                        <c:forEach var="pack" items="${packages}" varStatus="index">
                                            <c:forEach var="product" items='${pack.get("products")}'>
                                                <tr>
                                                    <td>${index.count}</td>
                                                    <td>${product.get("prodName")}</td>
                                                    <td>${product.get("quantity")}</td>
                                                </tr>                               
                                            </c:forEach>                                
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
               
                        <c:forEach var="log" items="${stage1.getLogs()}" varStatus="index">
                            <div id="stage1rule${index.index}" class="block" style="display:none;padding-left:30px;padding-top:30px;">
                                <div class="title">Rule ${index.count}</div>
                                <div style="padding-bottom:30px;">
                                    <div class="left">Rule Name&#58;</div>
                                    <div id="orderdate" class="right">${log.getName()}</div>
                                </div>
                                <div style="padding-bottom:30px;">
                                    <div class="left">Category&#58;</div>
                                    <div id="orderdate" class="right">${log.getCategories()}</div>
                                </div>
                                <div style="padding-bottom:30px;">
                                    <div class="left">Condition&#58;</div>
                                    <div id="orderdate" class="right">${log.getConditions()}</div>
                                </div>
                                <div style="padding-bottom:30px;">
                                    <div class="left">Action&#58;</div>
                                    <div id="orderdate" class="right">${log.getActions()}</div>
                                </div>
                                <div style="padding-bottom:30px;">
                                    <div class="left">Result of this rule&#58;</div>
                                    <div id="orderdate" class="right"> 
                                        <c:forEach var="logdetail" items="${log.getLogs()} ">
                                            ${logdetail} 
                                            <br/>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>                              
                        </c:forEach>                        


                        <!-- Add package for stage 2 -->                            
                        <c:forEach var="pack" items="${packages}" varStatus="index">                
                            
                         <div id="stage2package${index.index}" class="block" style="display:none;padding-left:30px;padding-top:30px;">
                                <div name="package" class="title">
                                    Package ${index.count}
                                </div>
    
                                <div id="included" style="height:40px;">
                                    <div class="include">
                                        Included Items&#58;
                                    </div>
    
                                    <div style="float:left;">   
                                       <c:forEach var="product" items='${pack.get("products")}' varStatus="index">
                                            <div name="items" class="item">
                                                ${product.get("prodName")} - quantity ${product.get("quantity")}                                
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                
                                <c:forEach var="split" items='${pack.get("splits")}' varStatus="splitIndex">
                                    <div name="splitNo" class="split">
                                        Split ${splitIndex.index}
                                    </div>                              
                                    <c:forEach var="obj" items='${split}'>  
                                        <div name="product" class="product">
                                            {<c:forEach var="p" items='${obj.get("products")}' varStatus="index">
                                                ${p.get("prodName")} (${p.get("quantity")}) 
                                            </c:forEach>}
                                        </div>                              
                                        <div style="float:left;width:200px;">
                                            <div name="failed" class="message">
                                                Failed&#58; ${stage2Obj.get("totalStores") - obj.get("storeCount")}/${stage2Obj.get("totalStores")} stores
                                            </div>
                                            <div name="success" class="message">
                                                Success&#58; ${obj.get("storeCount")}/${stage2Obj.get("totalStores")} stores
                                            </div>
                                        </div>                                          
                                    </c:forEach>
                                </c:forEach>
                                <c:if test="${pack.unable == true}" >
                                  <div style="height:20px;font-size:14px;width:250px;">
                                    <h2> Unable to handle this package. </h2>
                                  </div>
                                </c:if>     
                            </div>             
                        </c:forEach>
                        
                    <!-- Add route for stage 3 -->
                        <c:forEach var="pack" items="${packages}" varStatus="index">    
                            <div id="stage3route${index.index}" class="block" style="display:none;padding-left:30px;padding-top:30px;">
                                <div class="route" style="height:30px;font-size:18px;margin-top:10px;padding-bottom:10px;">
                                    Route ${index.count}
                                </div>  
                                
                                <div id="route_detail" style="height:30px;">
                                    <div style="height:20px;">
                                        <div class="source">
                                            From:
                                        </div>
                                        <div id="source" class="address">
                                            <c:forEach var="testResult" items='${stage3Arrays.get(index.index)}' varStatus="testIndex">
                                                <c:if test="${testIndex.index == 0}" >
                                                    <c:forEach var="parcelR" items='${testResult.get("results")}' varStatus="parcelIndex">
                                                        ${parcelR.get("source")}
                                                    </c:forEach>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div style="height:20px;">
                                        <div class="source">
                                            To:
                                        </div>
                                        <div id="destination" class="address">
                                            ${order.shippingAddr}
                                        </div>
                                    </div>
                                </div>                          
                                 
                                <div id="included" style="height:60px;margin-top:30px;">
                                    <div class="subtitle">
                                        Included Packages:
                                    </div>
                                    
                                    <div>
                                        <div name="packages" class="package">Package ${index.count}: </div>
                                        <div name="items" style="float:left;">
                                            <c:forEach var="product" items='${pack.get("products")}'>
                                                <div name="item" class="item2">${product.get("prodName")} - quantity ${product.get("quantity")}</div>
                                            </c:forEach>    
                                        </div>
                                    </div>

                                <c:choose>
                                    <c:when test='${pack.get("unable") == true}'>
                                      <br/>
                                      <div style="height:20px;font-size:14px;width:250px;">
                                        <h2> Unable to handle this package. </h2>
                                      </div>                                       
                                    </c:when>
                                    <c:otherwise>
                                
                                     <div id="rank" style="height:auto;margin-top:20px;">
                                        <div name="splitNo" class="subtitle">
                                            Top Ranking Route&#58;
                                        </div>
                                        <c:forEach var="testResult" items='${stage3Arrays.get(index.index)}' varStatus="testIndex">
                                            <div style="float:left;width:350px;padding-bottom:15px;" class="table-list">
                                                Rank # ${testIndex.count} Route&#58;
                                                <table cellspacing="0" cellpadding="0" class="list">
                                                    <tr class="title">
                                                        <th>Store ID</th>
                                                        <th>Product</th>
                                                        <th>Total Cost</th>
                                                    </tr>
                                                    <c:forEach var="parcelR" items='${testResult.get("results")}' >
                                                        <tr>
                                                            <td> ${parcelR.get("source")} </td>
                                                            <td>
                                                                <c:forEach var="parcelP" items='${parcelR.get("products")}' > 
                                                                    ${parcelP.get("prodName")} (${parcelP.get("quantity")}) 
                                                                </c:forEach>
                                                            </td>
                                                            <td> ${parcelR.get("totalCost")} </td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                            <div style="float:left;margin-top:20px;margin-left:5px;">
                                                
                                                <input name="cost${index.index}${testIndex.index}" class="button" style="width:96px;" value="View Cost Detail" onClick="changeDiv(this)">
                                            </div>          
                                        </c:forEach>                                                
                                    </div>  
                                    </c:otherwise>
                                </c:choose>                 
                            </div>
                        </div>
                            <c:forEach var="testResult" items='${stage3Arrays.get(index.index)}' varStatus="testIndex">
                                <div id="cost${index.index}${testIndex.index}" style="padding-left:20px;display:none;" class="block">
                                    <div class="route" style="height:30px;font-size:18px;margin-top:10px;padding-bottom:10px;">
                                        Route ${index.count}
                                    </div>  
                                    <div id="route_detail" style="height:30px;">
                                        <div style="height:20px;">
                                            <div style="float:left;height:10px;width:50px;font-size:12px;">
                                                From:
                                            </div>
                                            <div id="source" style="float:left;height:10px;width:60px;font-size:12px;">
                                                <c:forEach var="testResult" items='${stage3Arrays.get(index.index)}' varStatus="testIndex">
                                                    <c:if test="${testIndex.index == 0}" >
                                                        <c:forEach var="parcelR" items='${testResult.get("results")}' varStatus="parcelIndex">
                                                            ${parcelR.get("source")}
                                                        </c:forEach>
                                                    </c:if>
                                                </c:forEach>
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
                                            <div name="packages" style="float:left;padding-left:20px;height:20px;font-size:12px;">Package ${index.count}: </div>
                                            <div name="items" style="float:left;">
                                                <c:forEach var="product" items='${pack.get("products")}'>
                                                    <div name="item" style="padding-left:30px;height:20px;font-size:12px;">${product.get("prodName")} - quantity ${product.get("quantity")}</div>
                                                </c:forEach>    
                                            </div>
                                        </div>
                                    </div>
            
                                    <c:forEach var="parcelR" items='${testResult.get("results")}' varStatus="parcelIndex">
                                        <div style="height:100px;margin-top:20px;">
                                            <div style="float:left;height:20px;font-size:14px;">
                                                Cost Detail:
                                            </div>
                                            <div style="float:left; width:350px; padding-left:10px;" class="table-list">
                                                <table cellspacing="0" cellpadding="0" class="list">
                                                    <tr class="title">
                                                        <th>Cost</th>
                                                        <th>Amount</th>
                                                    </tr>
                                                    <c:forEach var="cost" items='${parcelR.get("costs")}'>
                                                        <tr>
                                                            <td> ${cost.get("name")} </td>
                                                            <td> ${cost.get("value")}  </td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </div>
                                    </c:forEach>
                                 </div>
                            </c:forEach>               
                        
                        </c:forEach>
                        
                    </div>
                </div>
       

            </div>
     
            <!-- form ends -->
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
