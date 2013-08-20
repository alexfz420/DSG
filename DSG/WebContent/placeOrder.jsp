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
    <!-- menu bar ends -->


    <!-- content starts -->
    <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix">
            <h1 class="l">Place Order</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

            <li style="list-style:none;">
                <span><a href="#">Home</a><span> &gt; </span></span>
                <span>Place Order<span>   
            </li>

            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none;">successMessage</div>
            <div class="warning" style="display:none;">errorMessage</div>
            <!-- Success Message and Error Message -->
            <br/>
            <!-- form starts-->
            <div>
            
            <form action="placeorder">
            <table class="text" style="width:450px;">
                <tr>
                    <td style="width:150px;">Product Name&#58;</td>
                    <td><input type="text" name="product" style="width:100px;"></td>
                    <td style="width:80px;">Quantity&#58;</td>
                    <td><input type="text" name="quantity" style="width:50px;"></td>
                </tr>   
                <tr>
                    <td style="width:150px;">Product Name&#58;</td>
                    <td><input type="text" name="product" style="width:100px;"></td>
                    <td style="width:80px;">Quantity&#58;</td>
                    <td><input type="text" name="quantity" style="width:50px;"></td>
                </tr>  
                <tr>
                    <td style="width:150px;">Product Name&#58;</td>
                    <td><input type="text" name="product" style="width:100px;"></td>
                    <td style="width:80px;">Quantity&#58;</td>
                    <td><input type="text" name="quantity" style="width:50px;"></td>
                </tr>  

                <tr>
                    <td >Shipping Type&#58;</td>
                    <td colspan="2">
                     
                    	<select style="width:150px;" name = "shippingtype">
                        <option value="#">Next Day Air</option> 
                        <option value="#">Second Day Air</option>
                        <option value="#">Ground shipping</option>
                        </select></td>
                        <td></td>
                </tr>
                <tr>
                    <td >Customer Address&#58;</td>
                    <td colspan="3"><input type="text" name="shippingaddress" style="width:250px;"> 
                    </td>
                    
                </tr>
                <tr>
                    <td >Customer Zipcode&#58;</td>
                    <td><input type="text" name="shippingzipcode" style="width:100px;"> 
                    </td>
                    
                </tr>

                <tr>
                    <td>
                    </td>
                    
                    
                    <td><input type="submit" value="Place Order" class="button"></td>  
                    <td><a class="button" href="#">Cancel</a></td>
                    
                   
                </tr>
            </table>   

            </form>

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
