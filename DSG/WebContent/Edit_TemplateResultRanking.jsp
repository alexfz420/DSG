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
  
    </ul>
</div>
    <!-- menu bar ends -->

    <!-- content starts -->
    <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix">
            <h1 class="l">Edit Business Rule</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

            <li style="list-style:none;">
            	<span><a href="<%=basePath%>gotoorderlist.action">Home</a><span> &gt; </span></span>
                <span><a href="<%=basePath%>gotorulelist.action">Manage Business Rule</a><span> &gt; </span></span>
                <span><a href="<%=basePath%>gotorulelist.action">Business Rule List</a><span> &gt; </span></span>
                <span>Edit Business Rule</span>
            </li>
            
            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none">successMessage</div>
            <div class="warning" style="display:none">errorMessage</div>
            <!-- Success Message and Error Message -->
        </div>
            <form action="resultRanking">
            <input type="hidden" name="ruleId" value="${rule.getRuleId()}"/>
            <table class="text">
                <tr>
                    <td>Rule Name&#58;</td>
                    <td><input style="width:200px;" type="text" name="ruleName" value ="${ruleName}">&nbsp;&nbsp;</td>
                </tr>   
                <tr>
                    <td>Rule Description&#58;</td>
                    <td><textarea name ="ruleDescription" style="overflow:hidden;max-width:200px;width:200px;height:50px;" onkeyup="textAreaAdjust(this)" >${ruleDescription}</textarea></td>
                </tr>
                <tr>
                    <td>Group<span class="red">*</span>&#58;</td>
                    <td><textarea id="tags" style="overflow:hidden;max-width:200px;width:200px;height:30px;" onkeyup="textAreaAdjust(this)" placeholder="Group name" readonly>All</textarea>
                    If not found, <a href="createcategory.html">new Group</a></td>
                </tr>
                <tr>
                    <td>Stage&#58;</td>
                    <td>Stage3: Allocation Optimization</td>
                </tr>
                <tr>
                    <td>Rule Template&#58;</td>
                    <td>Candidate Evaluate</td>
                </tr>
                <tr class="drl-height">
                    <td>Rule Editor&#58;</td>
                    <td>
                    <div class="drl"> 
                        
                        <div>Select the routing candidates which can&#58;<br/><br/></div> 
                        <div style="padding-left:50px;">
                        	<input type="radio" name="rankOption" value="max" <c:if test="${rule.getOperator() == 'max'}">checked="checked"</c:if>>
                        	maximize&nbsp;
                        	<select name="maxOption" style="width:200px;">
                        	 	<option value="margin" <c:if test="${rule.getOperator() == 'max' && rule.getAttribute() == 'margin'}">selected="selected"</c:if>> Total Margin </option>                        	
                        		<option value="retailPrice" <c:if test="${rule.getOperator() == 'max' && rule.getAttribute() == 'retailPrice'}">selected="selected"</c:if>> Net Merchandise Sales </option>
                        		<option value="shippingCost" <c:if test="${rule.getOperator() == 'max' && rule.getAttribute() == 'shippingCost'}">selected="selected"</c:if>>Fulfillment Cost</option>
                         		<option value="otherCost" <c:if test="${rule.getOperator() == 'max' && rule.getAttribute() == 'otherCost'}">selected="selected"</c:if>>Other Cost</option>
                        		<option value="proximity" <c:if test="${rule.getOperator() == 'max' && rule.getAttribute() == 'proximity'}">selected="selected"</c:if>> Proximity to Customer </option>
                        		<option value="totalCost" <c:if test="${rule.getOperator() == 'max' && rule.getAttribute() == 'totalCost'}">selected="selected"</c:if>> Total Cost </option>
                        	</select>
                        </div>
                        <br/>
                        <div style="padding-left:50px;">
                             <input type="radio" name="rankOption" value="min" <c:if test="${rule.getOperator() == 'min'}">checked="checked"</c:if>>
                             minimize&nbsp;&nbsp;
                             <select name="minOption" style="width:200px;">
                                <option value="totalCost" <c:if test="${rule.getOperator() == 'min' && rule.getAttribute() == 'totalCost'}">selected="selected"</c:if>> Total Cost </option>
                           		<option value="shippingCost" <c:if test="${rule.getOperator() == 'min' && rule.getAttribute() == 'shippingCost'}">selected="selected"</c:if>>Fulfillment Cost</option>
                        		<option value="otherCost" <c:if test="${rule.getOperator() == 'min' && rule.getAttribute() == 'otherCost'}">selected="selected"</c:if>>Other Cost</option>
                        		<option value="proximity" <c:if test="${rule.getOperator() == 'min' && rule.getAttribute() == 'proximity'}">selected="selected"</c:if>> Proximity to Customer </option>
                        	 	<option value="margin" <c:if test="${rule.getOperator() == 'min' && rule.getAttribute() == 'margin'}">selected="selected"</c:if>> Total Margin </option>
                        		<option value="retailPrice" <c:if test="${rule.getOperator() == 'min' && rule.getAttribute() == 'retailPrice'}">selected="selected"</c:if>> Net Merchandise Sales </option>
                        	 </select>
                         </div>
                         <br/>
                          
                    </div>
                    </td>
                </tr>
                <tr>
                    <td><a class="button" href="<%=basePath%>gotorulelist.action">Cancel</a></td>  
                    <td><input type="submit" class="button" value="Update" /></td>
                </tr>
            </table>   
            </form>
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
