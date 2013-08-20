<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
   <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    
     <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
 <script src="http://code.highcharts.com/highcharts.js"></script>
 <script src="http://code.highcharts.com/highcharts.js"></script>
 <script src="http://code.highcharts.com/modules/exporting.js"></script>
 <style>
    div.title{font-size:14px;margin-left:15px;margin-top:15px;}
 </style>
 <script>
    function getDay() {
        document.getElementById('day').style.display = 'block';
        document.getElementById('day1').style.display = 'block';
        document.getElementById('day2').style.display = 'block';

        document.getElementById('week').style.display = 'none';
        document.getElementById('week1').style.display = 'none';
        document.getElementById('week2').style.display = 'none';

        document.getElementById('month').style.display = 'none';
        document.getElementById('month1').style.display = 'none';
        document.getElementById('month2').style.display = 'none';

        document.getElementById('year').style.display = 'none';
        document.getElementById('year1').style.display = 'none';
        document.getElementById('year2').style.display = 'none';
    }

    function getWeek() {
        document.getElementById('day').style.display = 'none';
        document.getElementById('day1').style.display = 'none';
        document.getElementById('day2').style.display = 'none';

        document.getElementById('week').style.display = 'block';
        document.getElementById('week1').style.display = 'block';
        document.getElementById('week2').style.display = 'block';

        document.getElementById('month').style.display = 'none';
        document.getElementById('month1').style.display = 'none';
        document.getElementById('month2').style.display = 'none';

        document.getElementById('year').style.display = 'none';
        document.getElementById('year1').style.display = 'none';
        document.getElementById('year2').style.display = 'none';
    }

    function getMonth() {
        document.getElementById('day').style.display = 'none';
        document.getElementById('day1').style.display = 'none';
        document.getElementById('day2').style.display = 'none';

        document.getElementById('week').style.display = 'none';
        document.getElementById('week1').style.display = 'none';
        document.getElementById('week2').style.display = 'none';

        document.getElementById('month').style.display = 'block';
        document.getElementById('month1').style.display = 'block';
        document.getElementById('month2').style.display = 'block';

        document.getElementById('year').style.display = 'none';
        document.getElementById('year1').style.display = 'none';
        document.getElementById('year2').style.display = 'none';
    }

    function getYear() {
        document.getElementById('day').style.display = 'none';
        document.getElementById('day1').style.display = 'none';
        document.getElementById('day2').style.display = 'none';

        document.getElementById('week').style.display = 'none';
        document.getElementById('week1').style.display = 'none';
        document.getElementById('week2').style.display = 'none';

        document.getElementById('month').style.display = 'none';
        document.getElementById('month1').style.display = 'none';
        document.getElementById('month2').style.display = 'none';

        document.getElementById('year').style.display = 'block';
        document.getElementById('year1').style.display = 'block';
        document.getElementById('year2').style.display = 'block';

    }
 </script>
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
                <li class="" ><a id="orderlist" onclick="f(this)" href="<%=basePath%>gotoorderlist.action">Order List</a><span class="normal">&nbsp;</span></li>
				<li class="selected" id="statlist"><a href="statistics.html">Statistics</a><span class="normal">&nbsp;</span></li>

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

   <!-- menu bar ends -->


    <!-- content starts -->
     <div class="minibar recordable" id="minibar" memo="{&quot;id&quot;:&quot;menu-toggle&quot;,&quot;type&quot;:&quot;menu-toggle&quot;,&quot;status&quot;:&quot;1&quot;}" style="display:none;"><a id="menu-untoggle" href="javascript:void(0)" class="unfold" ></a></div> 
    <div class="main"  id="main-body">
        <div class="content clearfix">
                
        <div class="title-bar clearfix" style="height:80px;">
            <h1 class="l">Statistics</h1><div id="Date" class="date l"></div>
            <a id='ReportTipIco' class="report-help open l recordable" memo="{id:'ReportTipIco',type:'page-tip',global:0}" href="javascript:void(0);">&nbsp;</a>
            <br/><br/><div><hr/></div>

            <li style="list-style:none;">
                <span><a href="#">Home</a><span> &gt; </span></span>
                <span><a href="">Visualization Dashboard</a><span><span> &gt; </span>
                <span>Statistics<span>
            </li>

            <br/>
        </div>
        <!-- Control Bar -->
        <div class='control-bar-wrapper' id='ControlBarWrapper'>
        <div class="control-bar bg-iframe" id="ControlBar">
            <div class="l date-select-bar" id="DateSelectBar">
                <a href="javascript:;" onclick="getDay();">Today</a>
                <a href="javascript:;" onclick="getWeek();">Weekly</a>
                <a href="javascript:;" onclick="getMonth();">Monthly</a>
                <a href="javascript:;" onclick="getYear();">Yearly</a>
            </div>
        </div>
        </div>
        <!-- Control Bar ends -->
        <!--weekly-->
        <div id="day" class="title" style="display:block;">Today: 08/23/13 00:00&nbsp;&#45;&nbsp;08/23/13 23:59</div>
        <script type="text/javascript">
      $(function () {
        $('#day1').highcharts({
            title: {
                text: 'Total order number and allocation number',
                x: -20 //center
            },
            subtitle: {
                text: '',
                x: -20
            },
            xAxis: {
                categories: ['00:00', '02:00', '04:00', '06:00', '08:00', '10:00', '12:00', '14:00', '16:00', '18:00' , '20:00', '22:00']
            },
            yAxis: {
                title: {
                    text: 'Amount'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ''
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Allocation',
                data: [1316, 1221, 1207, 1298, 1305, 1385, 1321, 1399, 1430, 1465, 1481, 1483]
            }, {
                name: 'Order',
                data: [658, 535, 491, 626, 667, 651, 689, 704, 702, 713, 855, 881]
            }]
        });
    });
        </script>
        <div id="day1" style="min-width: 1010px; height: 250px; margin: 0 auto; display:block;"></div>

        <script type="text/javascript">
          $(function () {
        $('#day2').highcharts({
            chart: {
                type: 'column',
                margin: [ 50, 50, 100, 80]
            },
            title: {
                text: 'Total fulfillment cost'
            },
            xAxis: {
                categories: [
                    '00:00',
                    '02:00',
                    '04:00',
                    '06:00',
                    '08:00',
                    '10:00',
                    '12:00',
                    '14:00',
                    '16:00',
                    '18:00',
                    '20:00',
                    '22:00'
                ],
                labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Fulfillment cost ($)'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: 'Fulfillment cost: <b>{point.y:.1f}</b>',
            },
            series: [{
                name: 'Cost',
                data: [6868, 6912, 6758, 6904, 6911, 6893, 6877, 6892, 6799, 6884, 6712, 6610],
                dataLabels: {
                    enabled: true,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    x: 4,
                    y: 10,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif',
                        textShadow: '0 0 3px black'
                    }
                }
            }]
        });
    });
        </script>
        <div id="day2" style="min-width: 1010px; height: 350px; margin: 0 auto; display:block;"></div>

        <!--weekly-->
        <div id="week" class="title" style="display:none;">Weekly: 08/17/13&nbsp;&#45;&nbsp;08/23/13</div>
        <script type="text/javascript">
      $(function () {
        $('#week1').highcharts({
            title: {
                text: 'Total order number and allocation number',
                x: -20 //center
            },
            subtitle: {
                text: '',
                x: -20
            },
            xAxis: {
                categories: ['08/17', '08/18', '08/19', '08/20', '08/21', '08/22', '08/23']
            },
            yAxis: {
                title: {
                    text: 'Amount'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ''
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Allocation',
                data: [9210, 9105, 9020, 9034, 9155, 9053, 9036]
            }, {
                name: 'Order',
                data: [7886, 7982, 7821, 7908, 8002, 7962, 7992]
            }]
        });
    });
        </script>
        <div id="week1" style="min-width: 1010px; height: 250px; margin: 0 auto; display:none;"></div>

        <script type="text/javascript">
          $(function () {
        $('#week2').highcharts({
            chart: {
                type: 'column',
                margin: [ 50, 50, 100, 80]
            },
            title: {
                text: 'Total fulfillment cost'
            },
            xAxis: {
                categories: [
                    '08/17',
                    '08/18',
                    '08/19',
                    '08/20',
                    '08/21',
                    '08/22',
                    '08/23'
                ],
                labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Fulfillment cost ($)'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: 'Fulfillment cost: <b>{point.y:.1f}</b>',
            },
            series: [{
                name: 'Cost',
                data: [82417, 82432, 83015, 82133, 82691, 81700, 80593],
                dataLabels: {
                    enabled: true,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    x: 4,
                    y: 10,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif',
                        textShadow: '0 0 3px black'
                    }
                }
            }]
        });
    });
        </script>
        <div id="week2" style="min-width: 1010px; height: 350px; margin: 0 auto; display:none;"></div>

        <!--monthly-->
        <div id="month" class="title" style="display:none;">Monthly: 07/22/13&nbsp;&#45;&nbsp;08/23/13</div>
        <script type="text/javascript">
      $(function () {
        $('#month1').highcharts({
            title: {
                text: 'Total order number and allocation number',
                x: -20 //center
            },
            subtitle: {
                text: '',
                x: -20
            },
            xAxis: {
                categories: ['07/22', '07/26', '07/30', '08/03', '08/07', '08/11', '08/15', '08/19', '08/23']
            },
            yAxis: {
                title: {
                    text: 'Amount'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ''
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Allocation',
                data: [33858, 34084, 34125, 35291, 37325, 36921, 36995, 37284, 37053]
            }, {
                name: 'Order',
                data: [31542, 31928, 31290, 31508, 32845, 33968, 33021, 33859, 34032]
            }]
        });
    });
        </script>
        <div id="month1" style="min-width: 1010px; height: 250px; margin: 0 auto; display:none;"></div>

        <script type="text/javascript">
          $(function () {
        $('#month2').highcharts({
            chart: {
                type: 'column',
                margin: [ 50, 50, 100, 80]
            },
            title: {
                text: 'Total fulfillment cost'
            },
            xAxis: {
                categories: [
                    '07/22',
                    '07/26',
                    '07/30',
                    '08/03',
                    '08/07',
                    '08/11',
                    '08/15',
                    '08/19',
                    '08/23'
                ],
                labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Fulfillment cost ($)'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: 'Fulfillment cost: <b>{point.y:.1f}</b>',
            },
            series: [{
                name: 'Cost',
                data: [329668, 329876, 329782, 329885, 329692, 329710, 325721, 320832, 319853],
                dataLabels: {
                    enabled: true,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    x: 4,
                    y: 10,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif',
                        textShadow: '0 0 3px black'
                    }
                }
            }]
        });
    });
        </script>
        <div id="month2" style="min-width: 1010px; height: 350px; margin: 0 auto; display:none;"></div>

        <!--yearly-->
        <div id="year" class="title" style="display:none;">Yearly: September 2012&nbsp;&#45;&nbsp;August 2013</div>
        <script type="text/javascript">
      $(function () {
        $('#year1').highcharts({
            title: {
                text: 'Total order number and allocation number',
                x: -20 //center
            },
            subtitle: {
                text: '',
                x: -20
            },
            xAxis: {
                categories: ['Sep', 'Oct', 'Nov', 'Dec', 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug']
            },
            yAxis: {
                title: {
                    text: 'Amount'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ''
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Allocation',
                data: [278921, 259263, 251892, 240265, 268120, 265912, 280922, 264599, 271023, 275112, 279001, 268921]
            }, {
                name: 'Order',
                data: [239460, 236580, 225321, 219630, 239760, 230021, 229456, 219982, 228931, 235320, 240021, 235320]
            }]
        });
    });
        </script>
        <div id="year1" style="min-width: 1010px; height: 250px; margin: 0 auto; display:none;"></div>

        <script type="text/javascript">
          $(function () {
        $('#year2').highcharts({
            chart: {
                type: 'column',
                margin: [ 50, 50, 100, 80]
            },
            title: {
                text: 'Total fulfillment cost'
            },
            xAxis: {
                categories: [
                    'Sep',
                    'Oct',
                    'Nov',
                    'Dec',
                    'Jan',
                    'Feb',
                    'Mar',
                    'Apr',
                    'May',
                    'Jun',
                    'Jul',
                    'Aug'
                ],
                labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Fulfillment cost ($)'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: 'Fulfillment cost: <b>{point.y:.1f}</b>',
            },
            series: [{
                name: 'Cost',
                data: [2372510, 2472433, 2282398, 2472432, 2362521, 2472508, 2372520, 2482498, 2372485, 2162479, 2072507, 1972601],
                dataLabels: {
                    enabled: true,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    x: 4,
                    y: 10,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif',
                        textShadow: '0 0 3px black'
                    }
                }
            }]
        });
    });
        </script>
        <div id="year2" style="min-width: 1010px; height: 350px; margin: 0 auto; display:none;"></div>


    </div>
    </div>
        
    <!-- content ends -->


    <!-- footer starts -->
        <div>
            <div class="footer"><span>©2013 eBusiness Team</span></div>
        </div>
    <!-- footer ends -->

    </div>  
    
</body>
</html>