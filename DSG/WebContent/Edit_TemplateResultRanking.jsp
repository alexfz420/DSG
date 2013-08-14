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
			<li class="" id="catelist"><a
				href="<%=basePath%>gotocategorylist.action">Group List</a><span class="normal">&nbsp;</span></li>
			<li class="" id="newcatelist"><a
				href="<%=basePath%>gotonewcategory.action">New Group</a><span class="normal">&nbsp;</span></li>
		</ul></li>
	<li class=""><a class="recordable open" href="#" id="toggletwo"
		memo="{id:'21',type:'menu',global:1,status:''}">Manage Business Rule</a>
		<ul class="nav-two" id="navtwo">
			<li class="" id="bizrulelist"><a
				href="<%=basePath%>gotorulelist.action">Business Rule List</a><span class="normal">&nbsp;</span></li>
			<li class="" id="newbizrulelist"><a
				href="<%=basePath%>gotonewbizrulelist.action">New Business Rule</a><span class="normal">&nbsp;</span></li>
			<li class="" id="ruleprioritylist"><a
				href="<%=basePath%>gotoruleprioritylist.action">Business Rule Priority</a><span class="normal">&nbsp;</span></li>
		</ul></li>
	<li class=""><a class="recordable open" href="#" id="togglethree"
		memo="{id:'21',type:'menu',global:1,status:''}">Visualization
			Dashboard</a>
		<ul class="nav-two" id="navthree">
			<li class=""><a id="orderlist" onclick="f(this)"
				href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
			<li class="" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li>
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

            <li style="list-style:none;"><span><a href="#">Home</a><span> &gt; </span></span>
                <span><a href="#">Manage Business Rule</a><span> &gt; </span></span>
                <span><a href="#">Business Rule List</a><span> &gt; </span></span>
                <span>Edit Rule</span>
            </li>
            
            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none">successMessage</div>
            <div class="warning" style="display:none">errorMessage</div>
            <!-- Success Message and Error Message -->
        </div>
            <form action="#">
            <table class="text">
                <tr>
                    <td>Rule Name:</td>
                    <td>Result Ranking</td>
                </tr>   
                <tr>
                    <td>Rule Description:</td>
                    <td>Set ranking methods of the result.</td>
                </tr>
                <tr>
                    <td>Group:</td>
                    <td>All</td>
                </tr>
                <tr>
                    <td>Stage:</td>
                    <td>Stage3: Evaluation</td>
                </tr>
                <tr>
                    <td>Rule Template:</td>
                    <td>Candidate Evaluate</td>
                </tr>
                <tr class="drl-height">
                    <td>Rule Editor:</td>
                    <td>
                    <div class="drl"> 
                        
                        <div>Rank the routing candidates according to&#58;<br/><br/></div> 
                        <div><input type="radio" name="cost" value="cost">Fulfillment cost<br>
                             <input type="radio" name="margin" value="margin">Total margin of the order 
                         </div>
                         <br/>
                          
                    </div>
                    </td>
                </tr>
                <tr>
                    <td><a class="button" href="rulelist.html">Cancel</a></td>  
                    <td><input type="submit" class="button" />Update</td>
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