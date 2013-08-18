<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
     
 <jsp:include page="template_top.jsp" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="js/validation.js"></script>
 <ul class="nav">
        <li class=""><a class="recordable open" id="toggleone" href="#" 
            memo="{id:'21',type:'menu',global:1,status:''}">Manage Group</a>
            <ul class="nav-two" id="navone">
                <li class="" id="catelist"><a href="<%=basePath%>gotocategorylist.action?act=store">Group List</a><span class="normal">&nbsp;</span></li>
                <li class="selected" id="newcatelist"><a href="<%=basePath%>gotonewcategory.action">New Group</a><span class="normal">&nbsp;</span></li>  
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
                <li class="" ><a id="orderlist" onclick="f(this)" href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
                <li class="" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li>
            </ul>
        </li>   
    </ul>
    </div>
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
 
    $( "#storeTypeTags" )
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
    var availableTags = [
      "All",
      "Northeast (New England, Mid-Atlantic)",
      "Midwest (East/West North Central)",
      "South (South Atlantic, East/West South Central)",
      "West (Mountain, Pacific)"
    ];
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#regionTags" )
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
    var availableTags = [
      "All", "Alabama", "Alaska", "Arizona", "Arkansas",
      "California", "Colorado", "Connecticut", 
      "Delaware", "District of Columbia",
      "Florida",
      "Georgia",
      "Hawaii",
      "Idaho", "Illinois", "Indiana", "Iowa",
      "Kansas", "Kentucky",
      "Louisiana",
      "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
      "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
      "Ohio", "Oklahoma", "Oregon",
      "Pennsylvania",
      "Rhode island",
      "South Carolina", "South Dakota",
      "Tennessee", "Texas",
      "Utah",
      "Vermont", "Virginia",
      "Washington", "West Virginia", "Wisconsin", "Wyoming"
    ];

    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#stateTags" )
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
	  /* var availableTags = [
      "All",
      "Store01",
      "Store02",
      "Store03",
      "Store04",
      "Store05",
      "Store06",
      "Store07",
      "Store08",
      "Store09"
    ]; */
	var slist =document.getElementsByClassName("slist");
	   var availableTags= new Array();
	   for(var i = 0; i < slist.length; i++){
	   	availableTags[i]=slist[i].value;
	}
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#storeTags" )
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
    var availableTags = [
      "All",
      "Shirts",
      "Basketball",
      "Shoes",
      "Bags",
      "Kayaks",
      "Socks",
      "Accessories",
      "Bikes",
      "Camp",
      "Graphic Tees",
      "Shorts",
      "Pants",
      "Swimsuits",
      "Sweatshirts & Hoodies",
      "Jackets & Vests",
      "Extended Sizes",
      "Sports Bras",
      "Skirts, Skorts & Dresses",
      "Pants & Capris",
      "Yoga",
      "Toddler & Infant Jackets",
      "Backpacks & Duffles",
      "Sunglasses",
      "Hats",
      "Headbands",
      "Heart Rate Monitors",
      "Fishing Apparel"
    ];
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#typeTags" )
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
    var availableTags = [
      "All", "Adidas", "Antigua", "Asics", "anon",
      "Brooks", "Burton",
      "Columbia", 
      "Dallas Cowboys", 
      "Fit 2 Win", "Flow Society", 
      "Glamorise",
      "Jordan", 
      "KÃPPEN",
      "Marmot", "Mountain Hardwear", "Moving Comfort",
      "NFL Team Apparel", "Nike", 
      "Patagonia", "Patagonia", "Puma",
      "R.E.D", "Reebok", "Russell Athletic",
      "SKINS", "Saucony", "Shock Absorber", "Spanx", "Spyder",
      "TaylorMade", "The North Face",
      "Under Armour",
      "Warrior",
      "lucy"
    ];

    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#brandTags" )
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
    var availableTags = [
      "All",
      "Rule01",
      "Rule02",
      "Rule03",
      "Rule04",
      "Rule05",
      "Rule06",
      "Rule07",
      "Rule08",
      "Rule09"
    ];
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $('textarea[name="ruleTags"]')
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
      o.style.height = (25+o.scrollHeight)+"px";
  }

  $(function() {
    $( "#tabs" ).tabs({
      collapsible: true
    });
  });

  function getStoreTypeCheck() {
      var sPath = "<%=basePath%>storetypecheck.action";
      strFeatures = "dialogWidth=300px;dialogHeight=400px;center=yes;help=no;directories=no;status=no;scrollbars=no;resizable=1;menubar=no";
      var strReturn = showModalDialog(sPath, "", strFeatures);
      if (strReturn != null) {
          strReturn = document.getElementById("storeTypeTags").value + strReturn;
          document.getElementById("storeTypeTags").value = strReturn;
      }
  }

function getRegionCheck() {
      var sPath = "RegionChk.html";
      strFeatures = "dialogWidth=400px;dialogHeight=300px;center=yes;help=no;directories=no;status=no;scrollbars=no;resizable=1;menubar=no";
      var strReturn = showModalDialog(sPath, "", strFeatures);
      if (strReturn != null) {
          strReturn = document.getElementById("regionTags").value + strReturn;
          document.getElementById("regionTags").value = strReturn;
      }
  }

function getStateCheck() {
      var sPath = "<%=basePath%>statecheck.action";
      strFeatures = "dialogWidth=650px;dialogHeight=400px;center=yes;help=no;directories=no;status=no;scrollbars=no;resizable=1;menubar=no";
      var strReturn = showModalDialog(sPath, "", strFeatures);
      if (strReturn != null) {
          strReturn = document.getElementById("stateTags").value + strReturn;
          document.getElementById("stateTags").value = strReturn;
      }
  }

function getStoreCheck() {
      var sPath = "<%=basePath%>storecheck.action";
      strFeatures = "dialogWidth=750px;dialogHeight=550px;center=yes;help=no;directories=no;status=no;scrollbars=no;resizable=1;menubar=no";
      var strReturn = showModalDialog(sPath, "", strFeatures);
      if (strReturn != null) {
          strReturn = document.getElementById("storeTags").value + strReturn;
          document.getElementById("storeTags").value = strReturn;
      }
  }

function getTypeCheck() {
      var sPath = "<%=basePath%>typecheck.action";
      strFeatures = "dialogWidth=500px;dialogHeight=400px;center=yes;help=no;directories=no;status=no;scrollbars=no;resizable=1;menubar=no";
      var strReturn = showModalDialog(sPath, "", strFeatures);
      if (strReturn != null) {
          strReturn = document.getElementById("typeTags").value + strReturn;
          document.getElementById("typeTags").value = strReturn;
      }
  }

  function getBrandCheck() {
      var sPath = "<%=basePath%>brandcheck.action";
      strFeatures = "dialogWidth=550px;dialogHeight=400px;center=yes;help=no;directories=no;status=no;scrollbars=no;resizable=1;menubar=no";
      var strReturn = showModalDialog(sPath, "", strFeatures);
      if (strReturn != null) {
          strReturn = document.getElementById("brandTags").value + strReturn;
          document.getElementById("brandTags").value = strReturn;
      }
  }
 </script>

    <!-- menu bar ends -->

    <!-- content starts -->
    <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix">
            <h1 class="l">New Group</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>
        	
            <li style="list-style:none;">
            	<span><a href="#">Home</a><span> &gt; </span></span>
                <span><a href="<%=basePath%>gotocategorylist.action?act=store">Manage Group</a><span> &gt; </span></span>
                <span>New Group</span>
            </li>
            
            <!-- Success Message and Error Message -->
            <div class="success_area" id="successMessage" style="display:none">successMessage</div>
            <div class="warning" id="errorMessage" style="display:none">errorMessage</div>
            <!-- Success Message and Error Message -->
        </div>     	
        <div><br/><br/>
            <div id="tabs">
              <ul>
                <li><a href="#tabs-1">Fulfillment Group</a></li>
                <li><a href="#tabs-2">Product Group</a></li>
              </ul>
              <div id="tabs-1">
              <form action="createCategory.action" name="creategroupform" onsubmit="return validategroupForm()" >
              <table id="category" border="0" style="margin-left:50px;width:750px;">
                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Group Name<font color="red">*</font>:</div></td>
                    <td style="width:600px;"><input name="categoryName" type="text" style="width:300px;"></td>
                    <td></td>
                </tr>
                    
                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Group Description:</div></td>
                    <td style="width:500px;"><textarea name="categoryDes" placeholder="Type category description&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;"></textarea>
                      
                    </td>
                    <td></td>
                </tr>

                <tr style="height:40px%;">
                    <td><div class="form-title" style="width:150px;">Fulfillment Method<font color="red">*</font>:</div></td>
                    <td style="width:500px;">
                      <textarea name="storeType" id="storeTypeTags" placeholder="Type fulfillment type to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;"></textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStoreTypeCheck()">See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px%;">
                    <td><div class="form-title" style="width:150px;"><span class="grey">-OR-</span> &nbsp;State<font color="red">*</font>:</div></td>
                    <td style="width:500px;">
                      <textarea name="storeState" id="stateTags" placeholder="Type state to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;"></textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStateCheck()">See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Fulfillment Location:</div></td>
                    <td style="width:500px;">
                      <textarea name="storeId" id="storeTags" placeholder="Type fulfillment location to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;"></textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getStoreCheck()">See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px;">
                    <td><a class="button" href="<%=basePath%>gotocategorylist.action" onclick="">Cancel</a></td>
                    <td><input type="submit" value="Create" class="button"><input type="hidden" name="act" value="store"/>
                    </td>  
                    <td></td>
                </tr>
              </table> 
				</form> 
              </div>

              <div id="tabs-2">
              <form action="createCategory.action" name="CreateProductGroupForm" onsubmit="return validateProductGroupForm()">
              <table id="category2" border="0" style="margin-left:50px;width:760px;">
                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Group Name<font color="red">*</font>:</div></td>
                    <td style="width:600px;"><input name="categoryName" type="text" style="width:300px;"></td>
                    <td style="width:30px;"></td>
                </tr>
                    
                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Group Description:</div></td>
                    <td style="width:500px;"><textarea name="categoryDes" placeholder="Type category description&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;"></textarea>
                    </td>
                    <td style="width:30px;"></td>
                </tr>

                <tr style="height:40px%;">
                    <td><div class="form-title" style="width:150px;">Product Categories<font color="red">*</font>:</div></td>
                    <td style="width:500px;">
                      <textarea name="productType" id="typeTags" placeholder="Type product category to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;"></textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getTypeCheck()">See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;"><span class="grey">-OR-</span> &nbsp;Brand<font color="red">*</font>:</div></td>
                    <td style="width:500px;">
                      <textarea name="brand" id="brandTags" placeholder="Type brand name to start autocomplete&hellip;" onkeyup="textAreaAdjust(this)" style="overflow:hidden;width:500px;max-width:500px;min-width:500px;margin-top:10px;"></textarea>
                    </td>
                    <td style="width:110px;">
                      <a class="button" href="#" style="margin-left:5px;margin-top:10px;width:102px;" onclick="getBrandCheck()">See All Options</a>
                    </td>
                </tr>

                <tr style="height:40px;">
                    <td><div class="form-title" style="width:150px;">Product SKU:</div></td>
                    <td style="width:500px;">
                      <textarea name="productSKU"  placeholder="Type product SKU&hellip;" style="width:500px;max-width:500px;min-width:500px;margin-top:10px;"></textarea>
                    </td>
                    <td style="width:110px;"></td>
                </tr>
                <tr style="height:40px;">
                    <td><a class="button" href="<%=basePath%>gotocategorylist.action" >Cancel</a></td>
                    <td>
                      <input type="submit" value="Create" class="button"/>
                      <input type="hidden" name="act" value="product"/>
                    </td>  
                    <td></td>
                </tr>
              </table>   
              </form>
              <c:forEach items="${storeList}" var="slist">
             	<input type="hidden" class="slist" value="${slist.storeName}">
           	  </c:forEach>
              </div>   
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
