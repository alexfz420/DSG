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
  <script>
  
  $(function() {
    $("#sort tbody").sortable().disableSelection();
  });

  //var rule = allRule;
    function show() {
	    document.getElementById('secondStep').style.display='block';
	    document.getElementById('firstStep').style.display='none';
	    document.getElementById('buttons').style.display='none';
}
  
  function close() {
	  document.getElementById('secondStep').style.display='none';
	  document.getElementById('firstStep').style.display='block';
      document.getElementById('buttons').style.display='block';    
  } 

  function pageOnLoad() {
	  console.log("logged");
	  close();
  }
  
  /*function deleteRow(tableID) {
    try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
 
        for(var i=0; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && true == chkbox.checked) {
                table.deleteRow(i);
                rowCount--;
                i--;
            }
        }
    }catch(e) {
        alert(e);
    }
  }*/

//style="margin-left:50px;float:left;width:800px;"
 function deleteRow(row){
    var i = row.parentNode.parentNode.rowIndex;

    var x;
    var r = confirm("Do you really want to delete this rule?");
    if(r == true){
        document.getElementById('sort').deleteRow(i);
    }
    else{
        ;
    }
 }
 
 
 function SubmitForm()
 {
	 console.log("start");
	 var rulename = '${rulename}';
	 var table = document.getElementById("sort");
	 for (var i = 0, row; row = table.rows[i]; i++){
	    //iterate through rows
	    //rows would be accessed using the "row" variable assigned in the for loop
	    for (var j = 0, col; col = row.cells[j]; j++) {
	    	console.log("caonimabi"+j);
	      if (col.innerText == rulename){

	    	  document.getElementById('priorityId').value= i;

	      }
	    	//iterate through columns
	     //columns would be accessed using the "col" variable assigned in the for loop
	    }  
	 }
   document.forms['myForm'].submit() ;
 }
 window.onload =pageOnLoad;
 
 </script>
<script>
function goBack(){
	window.history.go(-1);
}
</script>
    <!-- content starts -->
   <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix">
            <h1 class="l">New Business Rule</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>
        
        <div>
            <li style="list-style:none;">
            	<span><a href="<%=basePath%>gotoorderlist.action">Home</a><span> &gt; </span></span>
                <span><a href="<%=basePath%>gotorulelist.action">Manage Business Rule</a><span> &gt; </span></span>
                <span>New Business Rule</span>
            </li>
        </div>
            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none">successMessage</div>
            <div class="warning" style="display:none">errorMessage</div>
            <!-- Success Message and Error Message -->
        </div>
        <div>
            <form action="storeCapacity">
            <table class="text">
                <tr>
                    <td>Rule Name&#58;</td>
                    <td>${rulename}</td>
                </tr>   
                <tr>
                    <td>Rule Description&#58;</td>
                    <td>${des}</td>
                </tr>
                <tr>
                    <td>Group<span class="red">*</span>&#58;</td>
                    <td>${cates} </td>
                    
                </tr>
                <tr>
                    <td>Stage&#58;</td>
                    <td>Stage 1 - Fulfillment Eligibility</td>
                </tr>
                <tr>
                    <td>Rule Template&#58;</td>
                    <td>${templatename }</td>
                </tr>
                <tr class="drl-height">
                    <td>Rule Editor&#58;</td>
                    <td style="width:500px;">
                    <div class="drl">
                         
                         <div> If the specific fulfillment source shipped 
                            <select style="width:90px;">
                                <option value="#">more than</option>
                                <option value="#">less than</option>
                            </select> 
                            <input type="text" name="storethreshold" style="width:50px;"> orders on the day&#44;
                            <br/><br/>
                        </div>
                        <div> Perform the following action&#58;</div>
                        <br/>
                        <div>
                            <select style="width:300px;">
                                <option value="#">Lower the priority of this fulfillment source</option>
                                <option value="#">Increase the priority of this fulfillment source</option>
                                <option value="#">Filter out the fulfillment source</option>
                            </select> 
                            <br/><br/>
                        </div>
   			    <input type="hidden" name="rulename"  value=<%=request.getAttribute("rulename")%> />
                <input type="hidden" name="templatename"  value=<%=request.getAttribute("templatename")%> />
                <input type="hidden" name="categoryname"  value=<%=request.getAttribute("categoryname")%> />
           		<input type="hidden" id="priorityId" name="priority">

                    </div>
                </tr>
                <tr>
                    <td><input type="button" value="Back" onclick="goBack()" class="button">  <a class="button" href="<%=basePath%>gotorulelist.action">Cancel</a></td>
                    <td><input type="submit" value="Create" class="button"></td>
                </tr>
            </table>
            </form>
        </div>
            
     
    </div>  

    
    <!-- content ends -->

                
    <!-- footer starts -->
 
            <div class="footer"><span>&copy;2013 eBusiness Team</span></div>
        
    <!-- footer ends -->

    </div>
</body>
</html>
