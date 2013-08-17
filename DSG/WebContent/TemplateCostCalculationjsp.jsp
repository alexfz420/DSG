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
                <li class="" id="bizrulelist"><a href="<%=basePath%>gotorulelist.action">Business Rule List</a><span class="normal">&nbsp;</span></li>
                <li class="selected" id="newbizrulelist"><a href="<%=basePath%>gotonewbizrulelist.action">New Business Rule</a><span class="normal">&nbsp;</span></li>
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
<script>
function goBack(){
	window.history.go(-1);
}
</script>
    <!-- menu bar ends -->

    <!-- content starts -->
    <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
    <div class="content clearfix">
                
       <div class="title-bar clearfix">
            <h1 class="l">New Business Rule</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>
        </div>
            <li style="list-style:none;"><span><a href="#">Home</a><span> > </span></span></span>
                <span><a href="#">Manage Business Rule</a><span> > </span></span>
                <span>New Business Rule</span>
            </li>
            
            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none">successMessage</div>
            <div class="warning" style="display:none">errorMessage</div>
            <!-- Success Message and Error Message -->

            <form action="costcalculation">
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
                    <td>Category&#58;</td>
                    <td>All</td>
                </tr>
                <tr>
                    <td>Stage&#58;</td>
                    <td>Stage2: Candidate Evaluate</td>
                </tr>
                <tr>
                    <td>Rule Template&#58;</td>
                    <td>Cost Calculate</td>
                </tr>
                <tr class="drl-height">
                    <td colspan="2">Rule Editor&#58;</td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                    <div class="drl"> 
                            <div id="tabs">
                                <ul>
                                    <li><a href="#tabs-SFS">Ship from Store</a></li>
                                    <li><a href="#tabs-GSI">Fulfilled by GSI</a></li>
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
                                    <a class="button" href="javascript:;" onclick="addOtherCost(SFSDiv);">Add Other Costs</a>
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
                                    <a class="button" href="javascript:;" onclick="addOtherCost(GSIDiv);">Add Other Costs</a>
                                    <div id="GSIDiv">
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
                                    <a class="button" href="javascript:;" onclick="addOtherCost(VendorDiv);">Add Other Costs</a>
                                    <div id="VendorDiv">
                                    </div>
                                </div>
                                </div>
                            </div>
                    </div>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Create" class="button"></td>  
                    <td><input type="button" value="Back" onclick="goBack()" class="button"><a class="button" href="<%=basePath%>gotorulelist.action">Cancel</a></td>
                </tr>
            </table>   
            </form>
        </div>
    </div>
  
    </div>
    
    <!-- content ends -->

                
    <!-- footer starts -->
       
        <div class="footer"><span>©2013 eBusiness Team</span>
        </div>
    <!-- footer ends -->
</body>
</html>