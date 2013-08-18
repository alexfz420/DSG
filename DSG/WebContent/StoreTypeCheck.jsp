<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Fulfillment Channel List</title>
</head>
<script language ="javascript"  >
    function Go_Submit() {
        var tmp = "";
        for (var i = 0; i < document.getElementsByName("chk").length; i++) {
            if (document.getElementsByName("chk")[i].checked) {
                var table = document.getElementById("tb");
                var str = table.rows[i].cells[1].innerHTML;
                tmp += str + ", ";                
            }
        }
        
        var returnvalue = tmp;
        window.returnValue = returnvalue;
        top.window.close();
    }

</script>
<body>
    <FieldSet style="font-size:9pt;width:120px; margin:10,10,10,10;">
        <legend >Fulfillment Channel</legend>
        <table id="tb">
            <tr>
                <td><input id="Checkbox1" name = "chk" type="checkbox" /></td>
                <td>All</td>
            </tr>
            <tr>
                <td><input id="Checkbox2" name = "chk" type="checkbox" /></td>
                <td>Warehouse</td>
            </tr>
            <tr>
                <td><input id="Checkbox3" name = "chk" type="checkbox" /></td>
                <td>Vendor</td>
            </tr>
            <tr>
                <td><input id="Checkbox4" name = "chk" type="checkbox" /></td>
                <td>Stores</td>
            </tr>
            <tr>
                <td colspan ="2">
                    <input id="Button1" type="button" value="button" onclick ="Go_Submit();" />
                </td>
            </tr>
        </table>
    </FieldSet> 
</body>
</html>
