<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html lang="en-us" xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Your Shopping Cart | Dick&#039;s Sporting Goods</title>
<link href="http://DSP.imageg.net/min-cat/mvc-cart-css.xml.min.css" media="all" type="text/css" rel="stylesheet" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery-1.9.1.js"></script><style type="text/css"></style>
  <link rel="stylesheet" type="text/css" href="css/normalize.css">
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>
	<script type="text/javascript" src="js/progressbar.js"></script>

	<link rel="stylesheet" type="text/css" href="css/progressbar.css">
  
  <link rel="stylesheet" type="text/css" href="css/result-light.css">
  
  <style type="text/css">
    #element_to_pop_up { 
    background-color:#fff;
    border-radius:15px;
    color:#000;
    display:none; 
    padding:20px;
    min-width:400px;
    min-height: 180px;
}
.b-close{
    cursor:pointer;
    position:absolute;
    right:10px;
    top:5px;
}

  </style>
  


<script type="text/javascript">//<![CDATA[ 
$(window).load(function(){
    // Semicolon (;) to ensure closing of earlier scripting
    // Encapsulation
    // $ is assigned to jQuery
    ;/* (function($) {

         // DOM Ready
        $(function() {

            // Binding a click event
            // From jQuery v.1.7.0 use .on() instead of .bind()
            $("button:submit[name='checkpop']").bind('click', function(e) {

                // Prevents the default action to be triggered. 
                e.preventDefault();

                // Triggering bPopup when click event is fired
                //$('#element_to_pop_up').bPopup();
				$('#element_to_pop_up').bPopup({
            		
        		});
            });

        });

    })(jQuery); */

/*================================================================================
 * @name: bPopup - if you can't get it up, use bPopup
 * @author: (c)Bjoern Klinggaard (twitter@bklinggaard)
 * @demo: http://dinbror.dk/bpopup
 * @version: 0.9.0.min
 ================================================================================*/
 (function(b){b.fn.bPopup=function(u,C){function v(){a.modal&&b('<div class="b-modal '+d+'"></div>').css({backgroundColor:a.modalColor,position:"fixed",top:0,right:0,bottom:0,left:0,opacity:0,zIndex:a.zIndex+l}).each(function(){a.appending&&b(this).appendTo(a.appendTo)}).fadeTo(a.speed,a.opacity);A();c.data("bPopup",a).data("id",d).css({left:"slideIn"===a.transition?-1*(h+g):m(!(!a.follow[0]&&n||f)),position:a.positionStyle||"absolute",top:"slideDown"===a.transition?-1*(j+g):p(!(!a.follow[1]&&q||f)),"z-index":a.zIndex+l+1}).each(function(){a.appending&&b(this).appendTo(a.appendTo)});D(!0)}function r(){a.modal&&b(".b-modal."+c.data("id")).fadeTo(a.speed,0,function(){b(this).remove()});D();return!1}function E(s){var b=s.width(),d=s.height();a.contentContainer.css({height:d,width:b});d<=c.height()&&(d=c.height());b<=c.width()&&(b=c.width());t=c.outerHeight(!0);g=c.outerWidth(!0);a.contentContainer.css({height:"auto",width:"auto"});A();c.dequeue().animate({left:m(!(!a.follow[0]&&n||f)),top:p(!(!a.follow[1]&&q||f)),height:d,width:b},250,function(){s.show();w=B()})}function D(b){switch(a.transition){case "slideIn":c.show().animate({left:b?m(!(!a.follow[0]&&n||f)):-1*(h+g)},a.speed,a.easing,function(){x(b)});break;case "slideDown":c.show().animate({top:b?p(!(!a.follow[1]&&q||f)):-1*(j+t)},a.speed,a.easing,function(){x(b)});break;default:b?c.fadeIn(a.speed,function(){x(b)}):c.stop().fadeOut(a.speed,a.easing,function(){x(b)})}}function x(s){s?(e.data("bPopup",l),c.delegate("."+a.closeClass,"click."+d,r),a.modalClose&&b(".b-modal."+d).css("cursor","pointer").bind("click",r),!F&&(a.follow[0]||a.follow[1])&&e.bind("scroll."+d,function(){w&&c.dequeue().animate({left:a.follow[0]?m(!f):"auto",top:a.follow[1]?p(!f):"auto"},a.followSpeed,a.followEasing)}).bind("resize."+d,function(){if(w=B())A(),c.dequeue().each(function(){f?b(this).css({left:h,top:j}):b(this).animate({left:a.follow[0]?m(!0):"auto",top:a.follow[1]?p(!0):"auto"},a.followSpeed,a.followEasing)})}),a.escClose&&y.bind("keydown."+d,function(a){27==a.which&&r()}),k(C)):(a.scrollBar||b("html").css("overflow","auto"),b(".bModal."+d).unbind("click"),y.unbind("keydown."+d),e.unbind("."+d).data("bPopup",0<e.data("bPopup")-1?e.data("bPopup")-1:null),c.undelegate("."+a.closeClass,"click."+d,r).data("bPopup",null).hide(),k(a.onClose),a.loadUrl&&(a.contentContainer.empty(),c.css({height:"auto",width:"auto"})))}function m(a){return a?h+y.scrollLeft():h}function p(a){return a?j+y.scrollTop():j}function k(a){b.isFunction(a)&&a.call(c)}function A(){var b;q?b=a.position[1]:(b=((window.innerHeight||e.height())-t)/2-a.amsl,b=b<z?z:b);j=b;h=n?a.position[0]:((window.innerWidth||e.width())-g)/2;w=B()}function B(){return(window.innerHeight||e.height())>c.outerHeight(!0)+z&&(window.innerWidth||e.width())>c.outerWidth(!0)+z}b.isFunction(u)&&(C=u,u=null);var a=b.extend({},b.fn.bPopup.defaults,u);a.scrollBar||b("html").css("overflow","hidden");var c=this,y=b(document),e=b(window),F=/OS 6(_\d)+/i.test(navigator.userAgent),z=20,l=0,d,w,q,n,f,j,h,t,g;c.close=function(){a=this.data("bPopup");d="__b-popup"+e.data("bPopup")+"__";r()};return c.each(function(){if(!b(this).data("bPopup"))if(k(a.onOpen),l=(e.data("bPopup")||0)+1,d="__b-popup"+l+"__",q="auto"!==a.position[1],n="auto"!==a.position[0],f="fixed"===a.positionStyle,t=c.outerHeight(!0),g=c.outerWidth(!0),a.loadUrl)switch(a.contentContainer=b(a.contentContainer||c),a.content){case "iframe":b('<iframe class="b-iframe" scrolling="no" frameborder="0"></iframe>').attr("src",a.loadUrl).appendTo(a.contentContainer);k(a.loadCallback);t=c.outerHeight(!0);g=c.outerWidth(!0);v();break;case "image":v();b("<img />").load(function(){k(a.loadCallback);E(b(this))}).attr("src",a.loadUrl).hide().appendTo(a.contentContainer);break;default:v(),b('<div class="b-ajax-wrapper"></div>').load(a.loadUrl,a.loadData,function(){k(a.loadCallback);E(b(this))}).hide().appendTo(a.contentContainer)}else v()})};b.fn.bPopup.defaults={amsl:50,appending:!0,appendTo:"body",closeClass:"b-close",content:"ajax",contentContainer:!1,easing:"swing",escClose:!0,follow:[!0,!0],followEasing:"swing",followSpeed:500,loadCallback:!1,loadData:!1,loadUrl:!1,modal:!0,modalClose:!0,modalColor:"#000",onClose:!1,onOpen:!1,opacity:0.7,position:["auto","auto"],positionStyle:"absolute",scrollBar:!0,speed:250,transition:"fadeIn",zIndex:9997}})(jQuery);
});//]]>  

</script>
</head>

<body class="dspCartPage">
	<div id="top"></div>
	<div id="cart-wrapper">
	<div id="monetatetrackingPixels" style="visibility:hidden">
	</div><div class="displayNone">Thank you for visiting Dick's Sporting Goods. If you need assistance with shopping on our site, please call us at 1-877-846-9997 and a customer care representative will be happy to assist you. Please inform the Customer Service representative that you require assistance.</div>

<link href="http://DSP.imageg.net/min-cat/site-head-css.xml.min.css" media="all" type="text/css" rel="stylesheet" />
<link href="http://DSP.imageg.net/min-cat/site-css.xml.min.css" type="text/css" rel="stylesheet" />

<div id="wrapper">
	<div id="header">
		<a id="brand-link" href="#" title="Dick's Sporting Goods"><img src="http://DSP.imageg.net/images/dspLogo.png" width="198" height="115" alt="Dick's Sporting Goods Logo" border="0" /></a>
		<div class="auxNavigation">
			<ul class="findNavi">
				<li><a title="Find a Store" href="">Find a Store</a></li>
				<li><a title="Weekly Circular" href="#">Weekly Circular</a></li>
				<li class="last ffffff">ORDER BY PHONE: 1-877-846-9997</li></ul>
				<ul class="helpNavi">
				<li><a title="Help" href="#">Help</a></li>
				<li class="last"><a title="My Account" rel="nofollow" href="#">My Account</a></li>
			</ul>
		</div>

		<div class="mainNavigation" id="mainNav">
			<ul>
				<li id="mainNav1" >
					<a class="mainNavLink" title="Team Sports">Team Sports<span></span></a>		
				</li>

				<li id="mainNav2">	
					<a class="mainNavLink" title="Exercise">Exercise<span></span></a>
				</li>
				
				<li id="mainNav3" >	
					<a class="mainNavLink" title="Footwear">Footwear<span></span> </a>
				</li>
				
				<li id="mainNav4">
					<a class="mainNavLink"title="Apparel">Apparel<span></span></a>
				</li>
				
				<li id="mainNav5">
					<a class="mainNavLink"title="Apparel">Golf<span></span></a>
				</li>

				<li id="mainNav6">
					<a class="mainNavLink"title="Apparel">OURDOORS<span></span></a>
				</li>	

				<li id="mainNav7">
					<a class="mainNavLink"title="Apparel">Fan Shop<span></span></a>
				</li>
						
				<li id="mainNav8">
					<a class="mainNavLink" title="Clearance">Clearance<span></span></a>
				</li>
				
			</ul>
		</div>

		<form id="search" method="post">
			<inputtype="text" id="search-box" maxlength="150" value="Start your search here..." onfocus="this.value=''" onblur="if(this.value=='') { this.value = 'Start your search here...'; }" /><input type="submit" id="search-button" class="input-button" value="Search" title="Search" />
		</form>
		</div>
	
	<div id="globalPromo">
	
		<div id="dsp_globalPromo_1_v2" style="width:960px;border:0px;overflow:hidden;">
		<img border="0" src="http://DSP.imageg.net/cms_widgets/20/55/2055165_assets/1x1_blank.jpg" height="1" width="1" align="Default" valign="Default" /></div><!-- END CMS SLOT --></div>
		<div id="hdrEmail"><iframe width="286" id="hdrIframe" frameborder="0" src="http://DSP.imageg.net/include/entry.html" scrolling="no" allowtransparency="true"></iframe></div>

<div class="displayNone">
<!-- THIS TAG REMOVED - SMP 15490 -->
</div>

	<div id="frame">

			<div id="cart">
				<div class="cart-inner-wrap">
				<div id="main">
					<div class="outer-wrap">
						<div id="cart-help">
							<h2>Your Shopping Cart</h2>
							<a href="" id="print-cart">print cart</a>
							<a rel="popup helpdesk" class="help-info" href="/helpdesk/popup.jsp?display=store&amp;subdisplay=order#how_add">How does my Shopping Cart work?</a>
						</div>
						<fieldset id="itemSummary">
						
							<button type="button" onClick="javascript:history.back();" class="previous-page"></button>
							<div class="checkoutOptions">
								<form class="proceedToCheckoutForm"  method="post" action="placeorder">
									<button type="submit" class="checkout" name="checkpop">Proceed to secure Checkout</button>
								</form>
								<div class="checkoutOptions-paypal">
									<span class="checkoutOptions-separator">-or-</span>
									<form method="post" >
								    <input type="hidden" value="ecs">
								    <input type="hidden" value="paypal">
									<INPUT type="hidden" value="dsp-checkout-flow">
									<a href="">
									<img src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" id="paypalactive" /></a>
									</form>
								</div>
							</div>
						
							<form id="cartCommand" action="updatecartquantity.jsp" method="post">
									<input type="hidden" id="warrantyUpdate" value="">
									

									<table summary="Shopping Cart Contents" class="cart" id="table0" border="0">
										<thead>
											<tr class="cart-headers">
												<th class="quantity">QTY</th>
												
												<th class="description">Item</th>
												<th class="price"><a href="/cart/index.jsp?cSortKey=uprice_desc">Price</a>&nbsp;</th>
												<th class="total"><a href="/cart/index.jsp?cSortKey=price_desc">Total</a>&nbsp;</th>
											</tr>
										</thead>
										<tbody>
											
											<input type="hidden" id="warrantyUpdate" value="">
											
											
											
												
											
												
											
												
											
												
											
												
											
											
												
												
																								
												<tr class=" item-entry item-entry-0 ">
													<td class="quantity">
														
															
															
															
																
																
																	<input id="cartItemMap1922162739.quantity" name="cartItemMap[1922162739].quantity" class="quantity" type="text" value="2" maxlength="3">
																
															
														
														<ul>
															<li><a class="details" href="javascript:cart.removeItem('1922162739');">- Remove</a></li>
															
																<li><a href="javascript:cart.addToWishList('1922162739','0');">+ Wish List</a></li>
															
														</ul>
													</td>
													
													<td class="description">
														<div class="item-details">
	
		<a class="product-thumbnail" href="http://www.dickssportinggoods.com/product/index.jsp?productId=12586136&amp;prodFindSrc=cart"><img src="http://DSP.imageg.net/graphics/product_images/pDSP1-12202216t50.jpg" alt="Ocean Kayak Prowler 13 Angler Kayak" width="50" height="50"></a>
	
	<a class="product-title" href="http://www.dickssportinggoods.com/product/index.jsp?productId=12586136&amp;prodFindSrc=cart">Ocean Kayak Prowler 13 Angler Kayak</a>
	
	
		
		
			
				<div class="color item-property"><strong>Color</strong>: BLUE/WHITE</div>
			
		
	
	
		<div class="size item-property"><strong>Size</strong>: 13 FT. 4 IN.</div>
	
	
	
	<div class="item-number item-property"><strong>Item#:</strong> 12586136</div>
</div>

														
														
														
														
															
																 
																	<p class="availability item-info">Leaves warehouse in 2 - 3 weeks.
																			- (<a rel="popup promo" class="promodetails" href="javascript:void(0);">details</a>)
																		<span class="tooltip">
																			




































																		</span>
																	</p>
																	<p class="shipping-restrictions item-info">Note: Shipping restrictions may apply.</p>
																
															
																													
															
														
														
														
															
															
																
																
																	<font class="alert">Take 10% Off Single Vest OR Paddle or 15% Off Paddle &amp; Vest with Purchase of Canoe, Kayak or Stand Up Paddleboard! </font>
																		
																		(<a rel="popup promo" class="promodetails" href="javascript:void(0);">details</a>)<span class="tooltip">
																		
																			
																		
																		</span><p></p>
																
															
														
													</td>
													<td class="price">
														<div>$799.99</div>
														
													</td>
													<td class="total prod-total">
														<div>
															
															
															
															<strong>
																
																	
																	
																		
																		$799.99
																	
																
															</strong>
														</div>
														
													</td>
												</tr>
											
												
												
												
													
																								
												<tr class=" item-entry item-entry-1 alt">
													<td class="quantity">
														
															
															
															
																
																
																	<input id="cartItemMap3959698152.quantity" name="cartItemMap[3959698152].quantity" class="quantity" type="text" value="1" maxlength="3">
																
															
														
														<ul>
															<li><a class="details" href="javascript:cart.removeItem('3959698152');">- Remove</a></li>
															
																<li><a href="javascript:cart.addToWishList('3959698152','1');">+ Wish List</a></li>
															
														</ul>
													</td>
													
													<td class="description">
														<div class="item-details">
	
		<a class="product-thumbnail" href="http://www.dickssportinggoods.com/product/index.jsp?productId=4447829&amp;prodFindSrc=cart"><img src="http://DSP.imageg.net/graphics/product_images/pDSP1-8607274t50.jpg" alt="TYR Women's Hurricane Category 5 Wetsuit" width="50" height="50"></a>
	
	<a class="product-title" href="http://www.dickssportinggoods.com/product/index.jsp?productId=4447829&amp;prodFindSrc=cart">TYR Women's Hurricane Category 5 Wetsuit</a>
	
	
		
		
			
				<div class="color item-property"><strong>Color</strong>: BLACK/RED</div>
			
		
	
	
		<div class="size item-property"><strong>Size</strong>: XS</div>
	
	
	
	<div class="item-number item-property"><strong>Item#:</strong> 4447829</div>
</div>

														
														
														
														
															
																 
																	<p class="availability item-info"><b>IN STOCK</b><br>Leaves warehouse in 1 - 2 full bus days.
																			- (<a rel="popup promo" class="promodetails" href="javascript:void(0);">details</a>)
																		<span class="tooltip">
																			




































																		</span>
																	</p>
																	<p class="shipping-restrictions item-info">Note: Shipping restrictions may apply.</p>
																
															
																													
															
														
														
														
															
																
																
																	<font class="alert">Free Shipping on Apparel!</font>
																		
																		(<a rel="popup promo" class="promodetails" href="javascript:void(0);">details</a>)<span class="tooltip">
																		
																			
																		
																		</span><p></p>
																
															
															
														
													</td>
													<td class="price">
														<div>$625.00</div>
														
													</td>
													<td class="total prod-total">
														<div>
															
															
															
															<strong>
																
																	
																	
																		
																		$625.00
																	
																
															</strong>
														</div>
														
													</td>
												</tr>
											
												
												
																								
												<tr class=" item-entry item-entry-2 ">
													<td class="quantity">
														
															
															
															
																
																
																	<input id="cartItemMap26310345.quantity" name="cartItemMap[26310345].quantity" class="quantity" type="text" value="1" maxlength="3">
																
															
														
														<ul>
															<li><a class="details" href="javascript:cart.removeItem('26310345');">- Remove</a></li>
															
																<li><a href="javascript:cart.addToWishList('26310345','2');">+ Wish List</a></li>
															
														</ul>
													</td>
													
													<td class="description">
														<div class="item-details">
	
		<a class="product-thumbnail" href="http://www.dickssportinggoods.com/product/index.jsp?productId=3948451&amp;prodFindSrc=cart"><img src="http://DSP.imageg.net/graphics/product_images/pDSP1-7028908t50.jpg" alt="O'Neill Men's Hammer 3-2 Full Wetsuit" width="50" height="50"></a>
	
	<a class="product-title" href="http://www.dickssportinggoods.com/product/index.jsp?productId=3948451&amp;prodFindSrc=cart">O'Neill Men's Hammer 3-2 Full Wetsuit</a>
	
	
		
		
			
				<div class="color item-property"><strong>Color</strong>: BLACK</div>
			
		
	
	
		<div class="size item-property"><strong>Size</strong>: S</div>
	
	
	
	<div class="item-number item-property"><strong>Item#:</strong> 3948451</div>
</div>

														
														
														
														
															
																 
																	<p class="availability item-info"><b>IN STOCK</b><br>Leaves warehouse in 1 - 2 full bus days.
																			- (<a rel="popup promo" class="promodetails" href="javascript:void(0);">details</a>)
																		<span class="tooltip">
																			




































																		</span>
																	</p>
																	<p class="shipping-restrictions item-info">Note: Shipping restrictions may apply.</p>
																
															
																													
															
														
														
														
															
																
																
																	<font class="alert">Take 50% off Select Water Sports Equipment!!  Discount applied in cart!</font>
																		
																		(<a rel="popup promo" class="promodetails" href="javascript:void(0);">details</a>)<span class="tooltip">
																		
																			
																		
																		</span><p></p>
																
															
															
														
													</td>
													<td class="price">
														<div>$129.99</div>
														
													</td>
													<td class="total prod-total">
														<div>
															
															
															
															<strong>
																
																	
																		
																			
																			
																			
																				
																			
																		
																		<strike>$129.00</strike>
																		$64.99
																	
																	
																
															</strong>
														</div>
														
													</td>
												</tr>
											
												
												
												
													
																								
												<tr class=" item-entry item-entry-3 alt">
													<td class="quantity">
														
															
															
															
																
																
																	<input id="cartItemMap2308183055.quantity" name="cartItemMap[2308183055].quantity" class="quantity" type="text" value="2" maxlength="3">
																
															
														
														<ul>
															<li><a class="details" href="javascript:cart.removeItem('2308183055');">- Remove</a></li>
															
																<li><a href="javascript:cart.addToWishList('2308183055','3');">+ Wish List</a></li>
															
														</ul>
													</td>
													
													<td class="description">
														<div class="item-details">
	
		<a class="product-thumbnail" href="http://www.dickssportinggoods.com/product/index.jsp?productId=19082586&amp;prodFindSrc=cart"><img src="http://DSP.imageg.net/graphics/product_images/pDSP1-15115470t50.jpg" alt="Fitness Gear Kick Fins" width="50" height="50"></a>
	
	<a class="product-title" href="http://www.dickssportinggoods.com/product/index.jsp?productId=19082586&amp;prodFindSrc=cart">Fitness Gear Kick Fins</a>
	
	
		
		
			
				<div class="color item-property"><strong>Color</strong>: BLUE</div>
			
		
	
	
		<div class="size item-property"><strong>Size</strong>: 7-10</div>
	
	
	
	<div class="item-number item-property"><strong>Item#:</strong> 19082586</div>
</div>

														
														
														
														
															
																 
																	<p class="availability item-info"><b>IN STOCK</b><br>Leaves warehouse in 1 - 2 full bus days.
																			- (<a rel="popup promo" class="promodetails" href="javascript:void(0);">details</a>)
																		<span class="tooltip">
																			




































																		</span>
																	</p>
																	<p class="shipping-restrictions item-info">Note: Shipping restrictions may apply.</p>
																
															
																													
															
														
														
														
															
															
																
																
															
														
													</td>
													<td class="price">
														<div>$39.99</div>
														
													</td>
													<td class="total prod-total">
														<div>
															
															
															
															<strong>
																
																	
																	
																		
																		$39.99
																	
																
															</strong>
														</div>
														
													</td>
												</tr>
											
												
												
																								
												<tr class=" item-entry item-entry-4 ">
													<td class="quantity">
														
															
															
															
																
																
																	<input id="cartItemMap828042027.quantity" name="cartItemMap[828042027].quantity" class="quantity" type="text" value="2" maxlength="3">
																
															
														
														<ul>
															<li><a class="details" href="javascript:cart.removeItem('828042027');">- Remove</a></li>
															
																<li><a href="javascript:cart.addToWishList('828042027','4');">+ Wish List</a></li>
															
														</ul>
													</td>
													
													<td class="description">
														<div class="item-details">
	
		<a class="product-thumbnail" href="http://www.dickssportinggoods.com/product/index.jsp?productId=18253406&amp;prodFindSrc=cart"><img src="http://DSP.imageg.net/graphics/product_images/pDSP1-14858652t50.jpg" alt="Aqua Lung Sport Adult Magellan Mask" width="50" height="50"></a>
	
	<a class="product-title" href="http://www.dickssportinggoods.com/product/index.jsp?productId=18253406&amp;prodFindSrc=cart">Aqua Lung Sport Adult Magellan Mask</a>
	
	
		
		
			
				<div class="color item-property"><strong>Color</strong>: BLACK</div>
			
		
	
	
		<div class="size item-property"><strong>Size</strong>: ADULT</div>
	
	
	
	<div class="item-number item-property"><strong>Item#:</strong> 18253406</div>
</div>

														
														
														
														
															
																 
																	<p class="availability item-info"><b>IN STOCK</b><br>Leaves warehouse in 1 - 2 full bus days.
																			- (<a rel="popup promo" class="promodetails" href="javascript:void(0);">details</a>)
																		<span class="tooltip">
																			




































																		</span>
																	</p>
																	<p class="shipping-restrictions item-info">Note: Shipping restrictions may apply.</p>
																
															
																													
															
														
														
														
															
															
																
																
															
														
													</td>
													<td class="price">
														<div>$29.99</div>
														
													</td>
													<td class="total prod-total">
														<div>
															
															
															
															<strong>
																
																	
																	
																		
																		$29.99
																	
																
															</strong>
														</div>
														
													</td>
												</tr>
											
										</tbody>
									</table>
									<input name="ecscheckout" id="ecscheckoutField" type="hidden" value="">
									<input name="checkout" id="checkoutField" type="hidden" value="">
								</form>

									<form id="moveToWishListCommand" >
										method="post">
										<input name="uniqueLineId" id="uniqueLineId" type="hidden"
											value="" /> <input name="lineNumberOnPage"
											id="lineNumberOnPage" type="hidden" value="" />
									</form>
									<form id="removeItemCommand" method="post">
																  <input name="remove" id="remove" type="hidden" value="" />
																</form>
								<div class="cart-tools">
									<a href="#" class="details update-cart">Update Cart</a>
									<div id="promoCodeWrapper">
										<form id="promoCodeCommand" method="post">
											<label for="promoCode"><span class="promo-message">Using a promotion / discount code?</span> <a rel="popup helpdesk" class="help" href="/helpdesk/popup.jsp?display=payment&amp;subdisplay=discounts" class="pagelink">Help</a></label>
											<input id="promoCode" name="promoCode" class="text" type="text" value=""/>
											<input name="successView" type="hidden" value="redirect:/cart/index.jsp"/>
											<input name="formView" type="hidden" value="redirect:/cart/index.jsp"/>
											<a href="#" id="promoApply" class="apply inactive">Apply</a>
										</form>
									</div>
								</div>
							
						

						</fieldset>
						</div>
					
						
						
							<div class="outer-wrap">
							<form name="checkOutForm" action="placeorder">
								
								<button type="submit" name="checkpop" class="previous-page">?? label.mvcCart.continueShopping ??</button>
								
							</form>
	<!-- <script type="text/javascript" async src="js/vglnk.js"></script> -->
	
		<div id="order-summary" class="order-summary module">
			
			<div class="content">
			<fieldset>

	<table>
		
		<tbody>
			<tr class="subtotal">
				<th>Merchandise Subtotal</th>
				<td>$2,094.97</td>
			</tr>

			<tr>
				<th>
				<span class="cart-property"></span>Estimated Shipping and Handling
				</th>
				<td>$137.49</td>
			</tr>

		
		<tr class="promo">
			<th>
			
				
				<div class="shipPromo">Free Shipping on Apparel!
				<span>(</span><a href="/promo/index.jsp?promoId=23428266" rel="popup promo">details</a><span>)</span></br>
				</div>
			
				
				<div class="shipPromo">Free Shipping Eligible on Qualifying Purchases Over $49! Up to $15 Shipping Credit.
				<span>(</span><a href="/promo/index.jsp?promoId=21389586" rel="popup promo">details</a><span>)</span></br>
				</div>
			
			</th>
			<td>-$15.59</td>
		</tr>		

			<tr class="sales-tax">
				<th>
					<span class="cart-property">Estimated Tax</span>
				</th>
				<td>$0.00</td>
			</tr>

			<tr class="total">
				<th><span class="cart-property"><b>Estimated Total</b></span>
				</th>
				<td>$2,216.87</td>
			</tr>

	
		</tbody>
	</table>


	
	
				</fieldset>
			</div>
		</div>
	


							</div>

							<div class="checkoutOptions checkoutOptionsBottom">
								<form class="proceedToCheckoutForm"  method="post" action="placeorder">
									<button type="submit" class="checkout" name="checkpop">Proceed to secure Checkout</button>
								</form>
								<div id="element_to_pop_up" style="left: 500px; position: absolute; top: 195.5px; z-index: 9999; display: none;">
    <a class="b-close">x</a>
    <div style="padding-top:50px;padding-right:40px;padding-left:40px;">
	<div id="progressBar" class="jquery-ui-like" style="width:300px;"><div style="height:15px;"></div></div>
	<script>
	
	$("button:submit[name='checkpop']").click(function (e){

            // Prevents the default action to be triggered. 
            e.preventDefault();

            // Triggering bPopup when click event is fired
            //$('#element_to_pop_up').bPopup();
			$('#element_to_pop_up').bPopup({
        		
    		});
					var i=1;
					//window.setTimeout('window.location="http://localhost:8080/Struts/placeorder"; ',100);
					function barSecond(){
						/* $.getJSON("http://localhost:8080/Struts/getPercentage",function(result){
							i=result.percentage;
							//console.log(i);
    					});  */
						if(i<=100)
						{
							progressBar(i, $('#progressBar'));
							i++;
							//console.log(i);
							setTimeout(barSecond, 30);
						}
						else{
							redirecting("redirecting", $('#progressBar'));
							setTimeout(barSecond, 30);
						}
					}
					barSecond();
					document.forms['checkOutForm'].submit();
					
				}
	);
	</script>
    </div>
</div>
								<!-- span class="checkoutOptions-separator">-or-</span -->
								<!-- PPE button	-->
								<div class="checkoutOptions-paypal">
									




<SCRIPT LANGUAGE="javascript">
function ecsCheckout() {
	//alert("=="+document.ecs.action);
document.ecs.submit();
}
</SCRIPT>


<span class="checkoutOptions-separator">-or-</span>

	  <form name="ecs" method="post" ACTION="/ecscheckout.jsp">
      <input type="hidden" name="step" value="ecs">
      <input type="hidden" name="cc" value="paypal">
	  <INPUT type="hidden" name="_flowId" value="dsp-checkout-flow">



		<a href="javascript: cart.proceedToECSCheckout();">
			<img src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" id="paypalactive" />
			
		</a>


	  
	  </form>
</div>
</div>
<div class="cart-notices">
<p class="cart-notice">All transactions are safe and secure.(<a rel="popup helpdesk" href="/helpdesk/popup.jsp?display=safety&amp;subdisplay=secure&amp;feedback">details</a>)</p>
						
					</div>
				</div>

				<div id="support">
						<div class="cart-wishlist">


<script type="text/javascript">
function viewList(){

    document.wl.viewSwitch.value = "null";
    document.wl.viewSwitch.name = "wlName";
    document.wl.submit();
}
</script>
<form NAME="wl" ACTION="/cartHandler/index.jsp" METHOD="post" style="margin:0; padding:0;">
	<input type="hidden" name="userPrefs" id="userPrefs" value="" />
	<INPUT TYPE="hidden" NAME="action" VALUE="changeQuantity" />
	<INPUT TYPE="hidden" NAME="secondaryAction" VALUE="" />
	<INPUT TYPE="hidden" NAME="viewSwitch" VALUE="" />
	<INPUT TYPE="hidden" NAME="checkout" VALUE="" />
	<INPUT TYPE="hidden" NAME="itemCount" VALUE="5" />
	<INPUT TYPE="hidden" NAME="remove" VALUE="N" />
	<INPUT TYPE="hidden" NAME="outOfStockSku" VALUE="" />
</form>

		<div bgcolor="null"><div id="davisCartOtherViewLink"bgcolor="#FFFFFF" >&nbsp;<a href="javascript:viewList()" class="pagelink">View Your Wish List</a>&nbsp;<span style="font-size:10px;">(Sign In)</span></div><!-- Close #davisCartOtherViewLink --></div>

						</div>

					<div class="outer-wrap crossSells">
					
						
						<div id="cart_rr">
						</div>
						
						
						
					</div>
				</div>
			</div>
		</div>
	

<div id="trackingPixels" style="visibility:hidden">
<script type="text/javascript" src="http://DSP.imageg.net/js/resxclsa.js"></script>
<script type="text/javascript">
	var resx = new Object();
	resx.appid = "DicksSportingGoods01";
	resx.top1 = 100000;
	resx.top2 = 100000;
	resx.customerid = ""; 

	resx.event = "shopping+cart";
	resx.itemid = "12586136;4447829;11906485;19082586;18253406";
	resx.links = "12586136;4447829;11906485;19082586;18253406";
	resx.rrec = true;
	
	resx.rrelem = "cart_rr";
		
	resx.rrnum = "4;";

	
//allow for environmental testing

certonaResx.run();
</script>
<!-- Resonance -->

<!-- End SMP 13832 DSP Certona Implementation -->
</div>

	</div>


	<div id="footer">
		<div id="footer-contents">
		<div id="copy">&copy;2013 DICK'S Sporting Goods</div>
	</div>

</body>
</html>

