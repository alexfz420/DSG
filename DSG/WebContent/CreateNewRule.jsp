<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
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
                <li class="" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li>
                
            </ul>
        </li>   
    </ul>
    </div>
 <script>
 
    
 	$(function() {
 		
	    
	    
	    function split( val ) {
	      return val.split( /,\s*/ );
	    }
	    function extractLast( term ) {
	      return split( term ).pop();
	    }
	 
	    $( "#tags" ) 
	      // don't navigate away from the field on tab when selecting an item
	      .bind( "keydown", function( event ) {
	        if ( event.KeyCode === $.ui.keyCode.TAB &&
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
	          this.value = terms.join( "," );
	          return false;
	        }
	      });
	  });
 	
 	
 	</script>
 	
 	<script>
      var type = 1;
      var availableTags ;
	  function textAreaAdjust(o) {
	      o.style.height = "1px";
	      o.style.height = (15+o.scrollHeight)+"px";
	  }
	  
	  function pageOnLoad() {
		  
		  console.log("logged");
		  type = 1;
		  var name = '${prodCate}';
		    var ch = new Array;
			 ch = name.split(",");
			 for(var i=0 ;i<ch.length;i++){
			  console.log(ch[i]);
			 }
		    console.log("a is "+name);
		    console.log("type is "+type);
		   availableTags = ch;
		 	
		  
	  }
	  
	  function chooseFormat(type) {
	  if (type == "product_threshold" ){
		  type = 1;
		  var name = '${prodCate}';
		    var ch = new Array;
			 ch = name.split(",");
			 for(var i=0 ;i<ch.length;i++){
			  console.log(ch[i]);
			 }
		    console.log("a is "+name);
		    console.log("type is "+type);
		   availableTags = ch;
	  }
	  else if (type == "store_threshold" ){
		  type = 2;
		  var name = '${storeCate}';
		    var ch = new Array;
			 ch = name.split(",");
			 for(var i=0 ;i<ch.length;i++){
			  console.log(ch[i]);
			 }
		    console.log("a is "+name);
		    console.log("type is "+type);
		   availableTags = ch;
	  }
	  else if (type == "special_route"){
		  type = 1;
		  var name = '${prodCate}';
		    var ch = new Array;
			 ch = name.split(",");
			 for(var i=0 ;i<ch.length;i++){
			  console.log(ch[i]);
			 }
		    console.log("a is "+name);
		    console.log("type is "+type);
		   availableTags = ch;
	  }
	  console.log("type !!!!!"+type);
	  
	  
	  }
	  
	  window.onload =pageOnLoad;

 </script>
 <script src="js/animation.js" type="text/javascript"></script>
    <!-- menu bar ends -->

    <!-- content starts -->
    <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix">
            <h1 class="l">New Business Rule</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

            <li style="list-style:none;"><span><a href="#">Home</a><span> &gt; </span></span>
                <span><a href="<%=basePath%>gotorulelist.action">Manage Business Rule</a><span> &gt; </span></span>
                <span>New Business Rule</span>
            </li>
            
            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none;">successMessage</div>
            <div class="warning" style="display:none;">errorMessage</div>
            
            <!-- Success Message and Error Message -->
        </div>
		
            <div>
          <div style="float:top; font-size:15px; padding-bottom:10px;">3 Steps to Create a Rule&#58;&nbsp;Step 1</div>

          
		</div>
		<div class="progress progress-striped" style="float:top;" >
          		 <div class="bar" style="width: 30%;"></div>
          </div>
            <div>
            <form action="gototemplate" method="get">
            <table class="text" style="width:600px;">
                <tr>
                    <td style="width:200px;">Rule Name<span class="red">*</span>&#58;</td>
                    <td><input type="text"  name="rulename" style="width:200px;"></td>
                </tr>   
                <tr>
                    <td>Rule Description&#58;</td>
                    <td style="width:200px;"><textarea name ="des" style="overflow:hidden;max-width:200px;width:200px;height:20px;" onkeyup="textAreaAdjust(this)" ></textarea></td>
                </tr>
                <tr>
                    <td>Rule Template<span class="red">*</span>&#58;</td>
                    
                    <td><select class="big" name="templatename" onchange="chooseFormat(this.value)">
                        <option value="product_threshold">Product Threshold</option> 
                        <option value="store_threshold">Store Threshold</option>
                        <option value="special_route">Special Route</option>
                        <!--<option value="#">Cost Calculate</option>
                        <option value="#">Candidate Evaluate</option>-->
                        </select></td>
                </tr>
                <tr>
                    <td>Group<span class="red">*</span>&#58;</td>
                    <td><textarea name="categoryname" id="tags" style="overflow:hidden;max-width:200px;width:200px;height:20px;" onkeyup="textAreaAdjust(this)" placeholder="Group name"></textarea></td>
                    <td class="grey">&nbsp;&nbsp;If not found, <a href="createcategory.html">new Group</a>.</td>
                </tr>
                
                <tr></tr>
                <tr>  
                    <td><a class="button" href="<%=basePath%>gotorulelist.action">Cancel</a></td>
                    <td><input type="submit" value="Continue" class="button"></td>
                    <input type="hidden" id="prodCate" name="prodCate"> 
           
                </tr>
            </table>   
            

            </form>
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
