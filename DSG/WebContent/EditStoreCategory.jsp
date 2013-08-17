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
				href="<%=basePath%>gotocategorylist.action?act=store">Group List</a><span class="normal">&nbsp;</span></li>
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
    <!-- menu bar ends -->
 <script>
 $(function() {
	    var availableTags = [
	      "All",
	      "GSI Warehouse",
	      "Vendor",
	      "Dick's Stores"
	    ];
	    function split( val ) {
	      return val.split( /,\s*/ );
	    }
	    function extractLast( term ) {
	      return split( term ).pop();
	    }
	 
	    $( "#tags" )
	      // don't navigate away from the field on tab when selecting an item
	      .bind( "keydown", function( event ) {
	        if ( event.keyCode === $.ui.keyCode.TAB &&
	            $( this ).data( "ui-autocomplete" ).menu.active ) {
	          event.preventDefault();
	        }
	      })
	      .autocomplete({
	        minLength: 0,
	        source: function( request, response ) {
	          // delegate back to autocomplete, but extract the last term
	          response( $.ui.autocomplete.filter(
	            availableTags, extractLast( request.term ) ) );
	        },
	        focus: function() {
	          // prevent value inserted on focus
	          return false;
	        },
	        select: function( event, ui ) {
	          var terms = split( this.value );
	          // remove the current input
	          terms.pop();
	          // add the selected item
	          terms.push( ui.item.value );
	          // add placeholder to get the comma-and-space at the end
	          terms.push( "" );
	          this.value = terms.join( ", " );
	          return false;
	        }
	      });
	  });
 	$(function() {
 		alert["!!!"];
	    var name = "a,adf,fdsf,adf,aer";
	    alert["!!!"];
	    var ch = new Array;
		 ch = name.split(",");
		 for(var i=0 ;i<ch.length;i++){
		  console.log(ch[i]);
		 }
	    console.log("a is "+name);
	    var availableTags = ch;
	    
	    function split( val ) {
	      return val.split( /,\s*/ );
	    }
	    function extractLast( term ) {
	      return split( term ).pop();
	    }
	 
	    $( "#tags" ) 
	      // don't navigate away from the field on tab when selecting an item
	      .bind( "keydown", function( event ) {
	        if ( event.keyCode === $.ui.keyCode.TAB &&
	            $( this ).data( "ui-autocomplete" ).menu.active ) {
	          event.preventDefault();
	        }
	      })
	      .autocomplete({
	        minLength: 0,
	        source: function( request, response ) {
	          // delegate back to autocomplete, but extract the last term
	          response( $.ui.autocomplete.filter(
	            availableTags, extractLast( request.term ) ) );
	        },
	        focus: function() {
	          // prevent value inserted on focus
	          return false;
	        },
	        select: function( event, ui ) {
	          var terms = split( this.value );
	          // remove the current input
	          terms.pop();
	          // add the selected item
	          terms.push( ui.item.value );
	          // add placeholder to get the comma-and-space at the end
	          terms.push( "" );
	          this.value = terms.join( ", " );
	          return false;
	        }
	      });
	  });

	  function textAreaAdjust(o) {
	      o.style.height = "1px";
	      o.style.height = (15+o.scrollHeight)+"px";
	  }
	  
	  function pageOnLoad() {
		  
		  console.log("logged");	  
	  }
	  
	  window.onload =pageOnLoad;

 </script>
 <script src="js/animation.js" type="text/javascript"></script>
    
    
    <!-- content starts -->
    <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix">
            <h1 class="l">Edit Group</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

        </div>
        <div >
            <li style="list-style:none;"><span><a href="#">Home</a><span> &gt; </span></span></span>
                <span><a href="#">Manage Group</a><span> &gt; </span></span>
                <span>Edit Group</span>
            </li>
            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none">successMessage</div>
            <div class="warning" style="display:none">errorMessage</div>
            <!-- Success Message and Error Message -->
        </div>
        <div><br/><br/>
            <form action="editStoreCategory.action">
            <input type="hidden" name="categoryId" value="${categoryId}">
             <div id="tabs">
              <ul>
                <li><a href="#tabs-1">Group: ${categoryName}</a></li>
              </ul>
              <div id="tabs-1">
              <table id="category" border="0" style="margin-left:50px;width:750px;">
                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Group Name<font color="red">*</font>:</div></td>
                    <td style="width:600px;"><input type="text" name="categoryName" style="width:300px;" value="${categoryName}"></td>
                    <td></td>
                </tr>
                    
                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Group Description:</div></td>
                    <td style="width:500px;">
                      <textarea  name="categoryDescr" placeholder="Type category description&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;">${categoryDescr}</textarea>
                    </td>
                    <td></td>
                </tr>
                
                 <tr style="height:40px%;">
                    <td><div class="form-title" style="width:150px;">Fulfillment Methord:<font color="red">*</font>:</div></td>
                    <td style="width:500px;">
                      <textarea id="storeTypeTags" placeholder="Type store type to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;">Dick's Stores&#44;&nbsp;</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStoreTypeCheck()">See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px%;">
                    <td><div class="form-title" style="width:150px;">Region:<font color="red">*</font>:</div></td>
                    <td style="width:500px;">
                      <textarea id="storeTypeTags" placeholder="Type store type to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;">Dick's Stores&#44;&nbsp;</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStoreTypeCheck()">See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px%;">
                    <td><div class="form-title" style="width:150px;">State:<font color="red">*</font>:</div></td>
                    <td style="width:500px;">
                      <textarea id="stateTags" placeholder="Type store state to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;">Pennsylvania&#44;&nbsp;</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStateCheck()">See All Options</a>
                    </td>
                </tr>
                
                 <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Store ID:</div></td>
                    <td style="width:500px;">
                      <textarea name="storeIdString" placeholder="Type store id to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;">${storeIdString}</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStoreCheck()">See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Applied Rules:</div></td>
                    <td style="width:500px;">
                      <textarea id="tags" name="appliedRuleString" placeholder="Type store id to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;">${appliedRuleString}</textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStoreCheck()">See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px;">
                    <td></td>
                    <td>
                      <input type="hidden" name="previousAppliedRuleString" value="${appliedRuleString}"/>
                      <span style="margin-left:10px;argin-top:10px;"><a class="button" href="ViewCategory.html" onclick=""/>Back</a></span>
                      <span style="margin-left:10px;argin-top:10px;"><input type="submit" value="Update"></span>
                    </td>  
                    <td></td>
                </tr>
              </table>   
              </div>
            </div>
            </form>
        </div>
    
    <!-- content ends -->
    

<jsp:include page="template_bottom.jsp" />
