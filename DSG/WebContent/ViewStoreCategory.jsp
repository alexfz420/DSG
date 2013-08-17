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
			<li class="" id="catelist"><a
				href="<%=basePath%>gotocategorylist.action?act=store">Group List</a><span
				class="normal">&nbsp;</span></li>
			<li class="" id="newcatelist"><a
				href="<%=basePath%>gotonewcategory.action">New Group</a><span
				class="normal">&nbsp;</span></li>
		</ul></li>
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
                <li class="" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li>
                
            </ul>
        </li>   
    </ul>
    </div>

<script>
  function textAreaAdjust(o) {
      o.style.height = "1px";
      o.style.height = (25+o.scrollHeight)+"px";
  }

  $(function() {
    $( "#tabs" ).tabs({
      collapsible: true
    });
  });
</script>
	
	 <!-- content starts -->
	 
    <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix">
            <h1 class="l">View Group</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

        </div>
        
            <li style="list-style:none;">
            	<span><a href="#">Home</a><span> &gt; </span></span>
                <span><a href="<%=basePath%>gotocategorylist.action?act=store">Manage Group</a><span> &gt; </span></span>
                <span>View Group</span>
            </li>
            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none">successMessage</div>
            <div class="warning" style="display:none">errorMessage</div>
            <!-- Success Message and Error Message -->
        
        <div><br/><br/>
            <form action="#">
            <div id="tabs">
              <ul>
                <li><a href="#tabs-1">Group Name: ${categoryName}</a></li>
              </ul>
              <div id="tabs-1">
              <table id="category" border="0" style="margin-left:50px;width:750px;">
                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Group Name<span class="red">*</span>:</div></td>
                    <td style="width:600px;"><input type="text" style="width:300px;" name="" value="${categoryName}" disabled></td>
                    <td></td>
                </tr>
                    
                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Group Description:</div></td>
                    <td style="width:500px;">
                      <textarea placeholder="Type category description&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;" disabled>${categoryDescr}</textarea>
                    </td>
                    <td></td>
                </tr>

                <tr style="height:40px%;">
                    <td><div class="form-title" style="width:150px;">Fulfillment Method<span class="red">*</span>:</div></td>
                    <td style="width:500px;">
                      <textarea id="storeTypeTags" placeholder="Type store type to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;" disabled>Dick's Stores&#44;&nbsp;</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStoreTypeCheck()" disabled>See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px%;">
                    <td><div class="form-title" style="width:150px;">Region:</div></td>
                    <td style="width:500px;">
                      <textarea id="regionTags" placeholder="Type store region to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;" disabled>Northeast (New England, Mid-Atlantic)&#44;&nbsp;</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getRegionCheck()" disabled>See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px%;">
                    <td><div class="form-title" style="width:150px;">State<span class="red">*</span>:</div></td>
                    <td style="width:500px;">
                      <textarea id="stateTags" placeholder="Type store state to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;" disabled>Pennsylvania&#44;&nbsp;</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStateCheck()" disabled>See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Store ID:</div></td>
                    <td style="width:500px;">
                      <textarea id="storeTags" placeholder="Type store id to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;" disabled>${StoreIdString}</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStoreCheck()" disabled>See All Options</a>
                    </td>
                </tr>
                
                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Applied Rules:</div></td>
                    <td style="width:500px;">
                      <textarea id="storeTags" placeholder="Type store id to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;" disabled>${appliedRuleString}</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStoreCheck()" disabled>See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px;">
                    <td><a class="button" href="categorylist.html" onclick="">Cancel</a></td>
                    <td><a class="button" href="viewStoreCate2EditStoreCate.action?categoryId=${categoryId}&categoryName=${categoryName}&categoryDescr=${categoryDescr}&StoreIdString=${StoreIdString}&appliedRuleString=${appliedRuleString}" onclick="">Update</a></td>
                    <td></td>
                </tr>
              </table>   
              </div>
            </div>
            </form>
        </div>
        </div>
     </div>

    <!-- content ends -->

<jsp:include page="template_bottom.jsp" />