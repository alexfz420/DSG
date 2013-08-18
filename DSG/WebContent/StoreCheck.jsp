<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">   
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Store List</title>
</head>
<script language ="javascript"  >
    /*function insertTable(str) {
        str = "store11, store12, store13, store14, store15, store16, store17, store18, store19, store20";
        var str_array = str.split(',')

        for(var i = 0; i < str_array.length; i++) {

            var table = document.getElementById("tb");
            var rows = table.rows.length;
            alert("rows length: " + rows);
            var cells = table.rows[0].cells.length;
            alert("cells length: " + cells);
            
            for(var j=0; j<10; j++){
                if(table.rows[j].cells[cells-1].innerHTML == null) {
                    alert("in row_count if");
                    var row_count = j;
                    break;
                }
            }

            if(rows_count >= 9) {
                alert("if");
                rows_count = 0;
                var insert = table.insertCell(++cells);
                var row = insert.insertRow(rows);
                table.rows[rows_count].cells[cells].innerHTML = str_array[i];
            } else {
                alert("else");
                var insert = table.insertRow(++rows_count);
                table.rows[rows_count].cells[cells].innerHTML = str_array[i];
            }
        }
    } */   

    function Go_Submit() {
        var tmp = "";
        for (var i = 0; i < document.getElementsByName("chk").length; i++) {
            if (document.getElementsByName("chk")[i].checked) {
            	var chkname = document.getElementsByClassName("chkname");
			    var str = chkname[i].innerHTML;
                tmp += str + ", "; 
            }
        }
        
        var returnvalue = tmp;
        window.returnValue = returnvalue;
        top.window.close();
    }

</script>
<body>
    <!--div style="display:none;">
        <input type="text" value="${store.name}">
        $size=${store.name}.length;
    </div-->

    <FieldSet style="font-size:9pt;width:700px;height:500px;margin:10,10,10,10;">
        <legend >Stores</legend>
    <div id="storelist">
        <div style="float:left;">
        <table id="tb1">
           <c:forEach items="${storeList}" var="store" varStatus="loop">
             	<c:if test="${not loop.first and loop.index % 3 == 0}">
                	</tr><tr>
           		</c:if>
                    <td class="chkbox"><input name = "chk" type="checkbox" /></td>
                    <td class="chkname">${store.storeName}</td>
           </c:forEach>
            <tr>
                <td colspan ="2">
                    <input type="button" value="button" onclick="Go_Submit();" />
                    <!--input type="button" value="add table" onClick="insertTable('test');"-->
                </td>
            </tr>
        </table>
        </div>
    </div>
    </FieldSet> 
</body>
</html>
