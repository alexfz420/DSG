<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
 <jsp:include page="template_top.jsp" />
 <style>
 /*progressbar*/
.progressbar {
     margin-bottom: 30px;
     overflow: hidden;
     /*CSS counters to number the steps*/
     counter-reset: step;
}
.progressbar li {
     list-style-type: none;
     color: black;
     text-transform: uppercase;
     font-size: 12px;
     width: 33.33%;
     text-align: center;
     float: left;
     position: relative;
}
.progressbar li:before {
     content: counter(step);
     counter-increment: step;
     width: 20px;
     line-height: 20px;
     display: block;
     font-size: 10px;
     text-align: center;
     color: white;
     background: #D3D3D3;
     border-radius: 3px;
     margin: 0 auto 5px auto;
}
/*progressbar connectors*/
.progressbar li:after {
     content: '';
     width: 100%;
     height: 2px;
     background: #D3D3D3;
     position: absolute;
     left: -50%;
     top: 9px;
     z-index: -1; /*put it behind the numbers*/
}

.progressbar li:first-child:after {
     /*connector not needed before the first step*/
     content: none;
}
/*marking active/completed steps green*/
/*The number of the step and the connector before it = green*/
.progressbar li.active:before,  .progressbar li.active:after{
     background: #0965B8;
     color: white;
}
</style>
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
		
	    var name = '${storeCate}';
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
                
        <div class="title-bar clearfix" style="height:100px;">
            <h1 class="l">New Business Rule</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

            <li style="list-style:none;">
            	<span><a href="<%=basePath%>gotoorderlist.action">Home</a><span> &gt; </span></span>
                <span><a href="<%=basePath%>gotorulelist.actio">Manage Business Rule</a><span> &gt; </span></span>
                <span>New Business Rule</span>
            </li>
            
            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none;">successMessage</div>
            <div class="warning" style="display:none;">errorMessage</div>
            <!-- Success Message and Error Message -->
        </div>

     


            <form name ="myForm" action="updateStoreThreshold">
            
            <table class="text">
                <tr>
                    <td>Rule Name&#58;</td> 
                    <td><input style="width:200px;" type="text" name="rulenames" value ='${rulename.replace("%20"," ")}' >&nbsp;&nbsp;</td>
                </tr>   
                <tr>
                    <td>Rule Description&#58;</td>
                    <td style="width:200px;"><textarea name ="des" style="overflow:hidden;max-width:200px;width:200px;height:50px;" >${ruleDess.replace("%20"," ")}</textarea></td>
                </tr>
                <tr>
                    <td>Group<span class="red">*</span>&#58;</td>	
                    <td><textarea name="categoryname" id="tags" style="overflow:hidden;max-width:200px;width:200px;height:30px;" onkeyup="textAreaAdjust(this)" placeholder="Group name" >${cates.replace("%20"," ")}</textarea>
                    
                    </td>
                </tr> 
                <tr>
                    <td>Rule Template&#58;</td>
                    <td>Store Threshold</td>
                </tr>
                <tr class="drl-height">
                    <td>Rule Editor&#58;</td>
                    <td style="width:500px;">
                    <div class="drl">
                         <div> If
                            <select style="width:50px;" name="conditions">
                                <option value="all">All</option> 
                                <option value="any">Any</option>
                            </select> of the following conditions are met&#58;
                        </div>  
                        <br/>
                       <div>
                            <select style="width:70px;"name="attribute">
                                <option value="Length" <c:if test="${attribute[0] == 'Distance'}">selected="selected"</c:if>>Shipping Distance</option> 
                                <option value="Width" <c:if test="${attribute[0] == 'Margin'}">selected="selected"</c:if>>Inventory Margin</option>
                                <option value="Height" <c:if test="${attribute[0] == 'Competition'}">selected="selected"</c:if>>Competition</option>
                                
                            </select>&nbsp;&nbsp;
                            <select style="width:40px;" name="operator">
                            	<option value="&gt" <c:if test="${operator[0] == '>'}">selected="selected"</c:if>>&gt;</option> 
                                <option value="=" <c:if test="${operator[0] == '='}">selected="selected"</c:if>>=</option>
                                <option value="&lt;" <c:if test="${operator[0] == '<'}">selected="selected"</c:if>>&lt;</option>
                            </select>&nbsp;&nbsp;
                            <input style="width:50px;" type="text" name="value" value ="${value[0]}">&nbsp;&nbsp;
                            <select style="width:70px;">
                                <option value="#"<c:if test="${attribute[0] == 'Distance'}">selected="selected"</c:if>>Miles</option>
                                <option value="#"<c:if test="${attribute[0] == 'Margin'}">selected="selected"</c:if>>Quantity</option>
                                <option value="#"<c:if test="${attribute[0] == 'Competition'}">selected="selected"</c:if>>Rate</option> 
                            
                            </select>
                        </div>
                        <br/>
                        <div>
                            <select style="width:70px;"name="attribute">
                                <option value="Length" <c:if test="${attribute[0] == 'Distance'}">selected="selected"</c:if>>Shipping Distance</option> 
                                <option value="Width" <c:if test="${attribute[0] == 'Margin'}">selected="selected"</c:if>>Inventory Margin</option>
                                <option value="Height" <c:if test="${attribute[0] == 'Competition'}">selected="selected"</c:if>>Competition</option>
                                
                            </select>&nbsp;&nbsp;
                            <select style="width:40px;" name="operator">
                            	<option value="&gt" <c:if test="${operator[1] == '>'}">selected="selected"</c:if>>&gt;</option> 
                                <option value="=" <c:if test="${operator[1] == '='}">selected="selected"</c:if>>=</option>
                                <option value="&lt;" <c:if test="${operator[1] == '<'}">selected="selected"</c:if>>&lt;</option>
                            </select>&nbsp;&nbsp;
                            <input style="width:50px;" type="text" name="value" value ="${value[1]}">&nbsp;&nbsp;
                            <select style="width:70px;">
                                <option value="#"<c:if test="${attribute[1] == 'Distance'}">selected="selected"</c:if>>Miles</option>
                                <option value="#"<c:if test="${attribute[1] == 'Margin'}">selected="selected"</c:if>>Quantity</option>
                                <option value="#"<c:if test="${attribute[1] == 'Competition'}">selected="selected"</c:if>>Rate</option> 
                            </select>
                        </div>
                        <br/>
                        <div>
                            <select style="width:70px;"name="attribute">
                                <option value="Length" <c:if test="${attribute[2] == 'Distance'}">selected="selected"</c:if>>Shipping Distance</option> 
                                <option value="Width" <c:if test="${attribute[2] == 'Margin'}">selected="selected"</c:if>>Inventory Margin</option>
                                <option value="Height" <c:if test="${attribute[2] == 'Competition'}">selected="selected"</c:if>>Competition</option>
                                
                            </select>&nbsp;&nbsp;
                            <select style="width:40px;" name="operator">
                            	<option value="&gt" <c:if test="${operator[2] == '>'}">selected="selected"</c:if>>&gt;</option> 
                                <option value="=" <c:if test="${operator[2] == '='}">selected="selected"</c:if>>=</option>
                                <option value="&lt;" <c:if test="${operator[2] == '<'}">selected="selected"</c:if>>&lt;</option>
                            </select>&nbsp;&nbsp;
                            <input style="width:50px;" type="text" name="value" value ="${value[2]}">&nbsp;&nbsp;
                            <select style="width:70px;">
                                <option value="#"<c:if test="${attribute[2] == 'Distance'}">selected="selected"</c:if>>Miles</option>
                                <option value="#"<c:if test="${attribute[2] == 'Margin'}">selected="selected"</c:if>>Quantity</option>
                                <option value="#"<c:if test="${attribute[2] == 'Competition'}">selected="selected"</c:if>>Rate</option> 
                            </select>
                        </div>
                        <br/> 
                        <div>Perform the following action&#58;</div>
                        <br/>
                        <div>
                            <select>
                                <option value="#">Ship this product separately</option>
                                <option value="#">Inform the vendor to ship this product</option>
                                <option value="#">Route the product to warehouse</option>
                                <option value="#">Cancel this order</option>
                            </select>
                        </div>
               
                    
                </tr>
                <tr id = "buttons">
                   <td><a class="button" href="<%=basePath%>gotorulelist.action">Cancel</a></td>  
                   <td><input type="submit" value="Update" class="button"></td>
                    
                <input type="hidden" name="rulename" id="rulename" value=<%=request.getAttribute("rulename")%> />
                <input type="hidden" name="categoryname"  value=<%=request.getAttribute("categoryname")%> />
                
                    
                </tr>
                 </table>  
            </form>
        </div>




    
    <!-- content ends -->

                
    <!-- footer starts -->
 
            <div class="footer"><span>&copy;2013 eBusiness Team</span></div>
        
    <!-- footer ends -->

    </div>
</body>
</html>
