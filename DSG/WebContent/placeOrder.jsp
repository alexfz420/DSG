

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />   
 <title>DSG-Place Order</title>
 <link href="css/common.css" rel="stylesheet" type="text/css" />
 <link href="css/overview.css" rel="stylesheet" type="text/css" /> 
 <link href="css/pop.css" rel="stylesheet" type="text/css" href=""/>
 <script src="js/jquery.min.js" type="text/javascript"> </script>
 <script src="js/animation.js" type="text/javascript"></script>
 <script src="js/pop.js" type="text/javascript"></script>
</head>

<body>
    <div class="header">
    <div class="header-content">
        <div class="logo">
            <a href="OrderList.jsp"><img src="image/logo.png" /></a></div>
        <div class="top-nav">
            <a href="#" target="_blank" class="trackable">
            <span class="nav-icon help-icon"></span>Help</a>|
            <a href="#" target="_blank" class="trackable">Account</a>|
            <a href="#">Log out</a>
        </div>
    </div>
    </div>
    
   
    <div class="body clearfix">
    
    <!-- menu bar starts -->
    <div class="aside" id="aside-menu">
    <div class="title"><h1>My Tool</h1>
       <!-- <a href="javascript:void(0)" class="fold recordable" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;0&quot;}" id="menu-toggle"></a>
        -->
    </div>
    <ul class="nav">
        <li class=""><a class="recordable open" id="toggleone" href="#" 
            memo="{id:'21',type:'menu',global:1,status:''}">Manage Category</a>
            <ul class="nav-two" id="navone">
                <li class=""><a href="categorylist.jsp">Category List</a><span class="normal">&nbsp;</span></li>
                <li class=""><a href="#">New Category</a><span class="normal">&nbsp;</span></li>  
            </ul>
        </li>
        <li class=""><a class="recordable open" href="#" id="toggletwo"
            memo="{id:'21',type:'menu',global:1,status:''}">Manage Business Rule</a>
            <ul class="nav-two" id="navtwo">
                <li class="selected"><a href="rulelist.jsp">Business Rule List</a><span class="normal">&nbsp;</span></li>
                <li class=""><a href="newrule.jsp">New Business Rule</a><span class="normal">&nbsp;</span></li>
                
            </ul>
        </li>   
        <li class=""><a class="recordable open" href="#" id="togglethree"
            memo="{id:'21',type:'menu',global:1,status:''}">Visualization Dashboard</a>
            <ul class="nav-two" id="navthree">
                <li class=""><a href="orderlist.jsp">Order List</a><span class="normal">&nbsp;</span></li>
                <li class=""><a href="#">Routing visualization</a><span class="normal">&nbsp;</span></li>
                <li class=""><a href="statistics.jsp">Statistics</a><span class="normal">&nbsp;</span></li>
                
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
            <h1 class="l">Place Order</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

            <li style="list-style:none;">
                <span><a href="#">Home</a><span> &gt; </span></span>
                <span>Place Order<span>   
            </li>

            <!-- Success Message and Error Message -->
            <div class="success_area" style="display:none;">successMessage</div>
            <div class="warning" style="display:none;">errorMessage</div>
            <!-- Success Message and Error Message -->
            <br/>
            <!-- form starts-->
            <div>
            
            <form action="placeorder">
            <table class="text" style="width:450px;">
                <tr>
                    <td style="width:150px;">Product Name&#58;</td>
                    <td><input type="text" name="product" style="width:100px;"></td>
                    <td style="width:80px;">Quantity&#58;</td>
                    <td><input type="text" name="quantity" style="width:50px;"></td>
                </tr>   
                <tr>
                    <td style="width:150px;">Product Name&#58;</td>
                    <td><input type="text" name="product" style="width:100px;"></td>
                    <td style="width:80px;">Quantity&#58;</td>
                    <td><input type="text" name="quantity" style="width:50px;"></td>
                </tr>  
                <tr>
                    <td style="width:150px;">Product Name&#58;</td>
                    <td><input type="text" name="product" style="width:100px;"></td>
                    <td style="width:80px;">Quantity&#58;</td>
                    <td><input type="text" name="quantity" style="width:50px;"></td>
                </tr>  

                <tr>
                    <td >Shipping Type&#58;</td>
                    <td colspan="2">
                     
                    	<select style="width:150px;" name = "shippingtype">
                        <option value="#">Next Day Air</option> 
                        <option value="#">Second Day Air</option>
                        <option value="#">Ground shipping</option>
                        </select></td>
                        <td></td>
                </tr>
                <tr>
                    <td >Customer Address&#58;</td>
                    <td colspan="3"><input type="text" name="shippingaddress" style="width:250px;"> 
                    </td>
                    
                </tr>
                <tr>
                    <td >Customer Zipcode&#58;</td>
                    <td><input type="text" name="shippingzipcode" style="width:100px;"> 
                    </td>
                    
                </tr>

                <tr>
                    <td>
                    </td>
                    
                    
                    <td><input type="submit" value="Place Order" class="button"></td>  
                    <td><a class="button" href="#">Cancel</a></td>
                    
                   
                </tr>
            </table>   

            </form>

            </div>
            <!-- form ends -->


           
        </div>

        </div>
    
    <!-- content ends -->        
    <!-- footer starts -->
        </div>
            <div class="footer"><span>©2013 eBusiness Team</span></div>
        </div>
    <!-- footer ends -->

    </div>
</body>
</html>
