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
		</ul>
	</li>
	<li class=""><a class="recordable open" href="#" id="toggletwo"
		memo="{id:'21',type:'menu',global:1,status:''}">Manage Business Rule</a>
		<ul class="nav-two" id="navtwo">
			<li class="" id="bizrulelist"><a class="normal">&nbsp;</span></li>
			<li class="" id="newbizrulelist"><a
				href="<%=basePath%>gotonewbizrulelist.action">New Business Rule</a><span class="normal">&nbsp;</span></li>
			<li class="" id="ruleprioritylist"><a
				href="<%=basePath%>gotoruleprioritylist.action">Business Rule Priority</a><span class="normal">&nbsp;</span></li>
		</ul>
	</li>
	<li class=""><a class="recordable open" href="#" id="togglethree"
		memo="{id:'21',type:'menu',global:1,status:''}">Visualization Dashboard</a>
		<ul class="nav-two" id="navthree">
			<li class=""><a id="orderlist" onclick="f(this)"
				href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
			<li class="" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li>
		</ul>
	</li>
</ul>
</div>
<script>

function addOtherCost(myDiv) {

  var ni = document.getElementById(myDiv.id);

  var numi = document.getElementById('theValue');

  var num = (document.getElementById('theValue').value -1)+2;

  numi.value = num;

  var newdiv = document.createElement('div');

  var divIdName = 'othercost'+num;

  newdiv.setAttribute('id',divIdName);

  newdiv.innerHTML = '<input type=\'text\' style=\'width:150px;\'placeholder=\'Input Cost Name\'>&nbsp;&#42;&nbsp;&#58;&nbsp;&nbsp;<input type=\'text\' style=\'width:50px;\'>&nbsp;<select style=\'width:240px;\'><option value=\'#\'>dollar per unit</option><option value=\'#\'>&#37; of Net Merchandise Sales</option></select>&nbsp;<a href=\'javascript:;\' onclick=\'removeElement('+divIdName+')\'>Remove</a>';

  ni.appendChild(newdiv);

}

function removeElement(divNum) {
  //var d = document.getElementById("myDiv");

  var olddiv = document.getElementById(divNum.id);

  olddiv.parentNode.removeChild(olddiv);

  //d.removeChild(olddiv);

};
</script>

<script>

  $(function() {
    $( "#tabs" ).tabs({
      //event: "mouseover"
      collapsible: true
    });
  });


</script>
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
            <table class="text" style="width:750px;">
                <tr>
                    <td style="width:150px;">Rule Name&#58;</td>
                    <td style="width:600px;">Cost Calculate</td>
                </tr>   
                <tr>
                    <td>Rule Description&#58;</td>
                    <td>Set the formula for fulfillment cost calculation.</td>
                </tr>
                <tr>
                    <td>Group<span class="red">*</span>&#58;</td>
                    <td><textarea name="categoryname" id="tags" style="overflow:hidden;max-width:200px;width:200px;height:30px;" onkeyup="textAreaAdjust(this)" placeholder="Group name" >${cates}</textarea>
                   </td>
                </tr> 
                <tr>
                    <td>Stage&#58;</td>
                    <td>Stage 3: Evaluation</td>
                </tr>
                <tr>
                    <td>Rule Template&#58;</td>
                    <td>Cost Calculate</td>
                </tr>
                <tr class="drl-height">
                    <td>Rule Editor&#58;</td>
                    <td>
                    <div> 
                        <div id="tabs">
                            <ul>
                                <li><a href="#tabs-SFS">Ship from Store</a></li>
                                <li><a href="#tabs-GSI">Ship from Warehouse</a></li>
                                <li><a href="#tabs-Vendor">Vendor Direct</a></li>
                            </ul>
                            <div id="tabs-SFS" style="min-height:250px;">
                            <table >
                                <tr>
                                    <td style="width:200px;">Transaction Fees&nbsp;&#42;&nbsp;&#58;</td>
                                    <td style="width:400px;"><input type="text" style="width:50px;">&nbsp;&#37;&nbsp;of Net Merchandise Sales</td>
                                </tr>
                                <tr>
                                    <td style="width:200px;">Freight In to Store Cost&nbsp;&#42;&nbsp;&#58;</td>
                                    <td style="width:400px;"><input type="text" style="width:50px;">&nbsp;&#37;&nbsp;of Net Merchandise Sales</td>
                                </tr>
                                <tr>
                                    <td style="width:200px;">Distribution Center Cost&nbsp;&#42;&nbsp;&#58;</td>
                                    <td style="width:400px;"><input type="text" style="width:50px;">&nbsp;&#37;&nbsp;of Net Merchandise Sales</td>
                                </tr>
                                <tr>
                                    <td style="width:200px;">Packaging Cost&nbsp;&#42;&nbsp;&#58;</td>
                                    <td style="width:400px;"><input type="text" style="width:50px;">&nbsp;dollar per unit</td>
                                </tr>
                                <tr>
                                    <td style="width:200px;">Labor Cost&nbsp;&#42;&nbsp;&#58;</td>
                                    <td style="width:400px;"><input type="text" style="width:50px;">&nbsp;dollar per unit</td>
                                </tr>
                            </table>
                            <div>
                                <input type="hidden" value="0" id="theValue"/>
                                <a class="button" href="javascript:;" onclick="addOtherCost(SFSDiv);">+ Add Cost</a>
                                <div id="SFSDiv">
                                </div>
                            </div>
                            </div>
                            <div id="tabs-GSI" style="min-height:250px;">
                            <table >
                                <tr>
                                    <td style="width:200px;">Transaction Fees&nbsp;&#42;&nbsp;&#58;</td>
                                    <td style="width:400px;"><input type="text" style="width:50px;">&nbsp;&#37;&nbsp;of Net Merchandise Sales</td>
                                </tr>
                                <tr>
                                    <td style="width:200px;">Freight In to Store Cost&nbsp;&#42;&nbsp;&#58;</td>
                                    <td style="width:400px;"><input type="text" style="width:50px;">&nbsp;&#37;&nbsp;of Net Merchandise Sales</td>
                                </tr>
                                <tr>
                                    <td style="width:200px;">Distribution Center Cost&nbsp;&#42;&nbsp;&#58;</td>
                                    <td style="width:400px;"><input type="text" style="width:50px;">&nbsp;&#37;&nbsp;of Net Merchandise Sales</td>
                                </tr>
                            </table>
                            <div>
                                <input type="hidden" value="0" id="theValue"/>
                                <a class="button" href="javascript:;" onclick="addOtherCost(warehouseDiv);">+ Add Cost</a>
                                <div id="warehouseDiv">
                                </div>
                            </div>
                            </div>
                            <div id="tabs-Vendor" style="min-height:250px;">
                                <table >
                                <tr>
                                    <td style="width:200px;">Transaction Fees&nbsp;&#42;&nbsp;&#58;</td>
                                    <td style="width:400px;"><input type="text" style="width:50px;">&nbsp;&#37;&nbsp;of Net Merchandise Sales</td>
                                </tr>
                            </table>
                            <div>
                                <input type="hidden" value="0" id="theValue"/>
                                <a class="button" href="javascript:;" onclick="addOtherCost(VendorDiv);">+ Add Cost</a>
                                <div id="VendorDiv">
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                    </td>
                </tr>
                <tr>
                    <td><a class="button" href="rulelist.html">Cancel</a></td>  
                    <td><a class="button" href="rulelist.html">Update</a></td>
                </tr>
            </table>   
            </form>
        </div>
    </div>
  
    </div>
    
    <!-- content ends -->

                
    <!-- footer starts -->
       
        <div class="footer"><span>&copy;2013 eBusiness Team</span>
        </div>
    <!-- footer ends -->
</body>
</html>                 