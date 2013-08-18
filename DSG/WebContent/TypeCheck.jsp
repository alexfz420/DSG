<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Product Type List</title>
</head>
<script language ="javascript"  >
    function Go_Submit() {
        var tmp = "";
        for (var i = 0; i < document.getElementsByName("chk").length; i++) {
            if (document.getElementsByName("chk")[i].checked) {
                if(i < 10) {
                    var table = document.getElementById("tb1");
                    var str = table.rows[i].cells[1].innerHTML;
                    tmp += str + ", ";  
                } else if(i >= 10 && i < 20) {
                    var table = document.getElementById("tb2");
                    var str = table.rows[i-10].cells[1].innerHTML;
                    tmp += str + ", ";
                }  else if(i >= 20 && i < 30){
                    var table = document.getElementById("tb3");
                    var str = table.rows[i-20].cells[1].innerHTML;
                    tmp += str + ", ";
                } else
                    ;              
            }
        }
        
        var returnvalue = tmp;
        window.returnValue = returnvalue;
        top.window.close();
    }

</script>
<body>
    <FieldSet style="font-size:9pt;width:450px; margin:10,10,10,10;">
        <legend >Product Type</legend>
        <div style="float:left;"><table id="tb1">
            <tr>
                <td><input id="Checkbox1" name = "chk" type="checkbox" /></td>
                <td>All</td>
            </tr>
            <tr>
                <td><input id="Checkbox2" name = "chk" type="checkbox" /></td>
                <td>Shirts</td>
            </tr>
            <tr>
                <td><input id="Checkbox3" name = "chk" type="checkbox" /></td>
                <td>Shoes</td>
            </tr>
            <tr>
                <td><input id="Checkbox4" name = "chk" type="checkbox" /></td>
                <td>Bags</td>
            </tr>
            <tr>
                <td><input id="Checkbox5" name = "chk" type="checkbox" /></td>
                <td>Kayaks</td>
            </tr>
            <tr>
                <td><input id="Checkbox6" name = "chk" type="checkbox" /></td>
                <td>Fishing Apparel</td>
            </tr>
            <tr>
                <td><input id="Checkbox7" name = "chk" type="checkbox" /></td>
                <td>Socks</td>
            </tr>
            <tr>
                <td><input id="Checkbox8" name = "chk" type="checkbox" /></td>
                <td>Accessories</td>
            </tr>
            <tr>
                <td><input id="Checkbox9" name = "chk" type="checkbox" /></td>
                <td>Bikes</td>
            </tr>
            <tr>
                <td><input id="Checkbox10" name = "chk" type="checkbox" /></td>
                <td>Camp</td>
            </tr>
            <tr>
                <td colspan ="2">
                    <input id="Button1" type="button" value="button" onclick ="Go_Submit();" />
                </td>
            </tr>
        </table></div>
        <div style="float:left;"><table id="tb2">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Basketball</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Graphic Tees</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Shorts</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Pants</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Swimsuits</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Sweatshirts and Hoodies</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Jackets and Vests</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Extended Sizes</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Sports Bras</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Skirts, Skorts and Dresses</td>
            </tr>
            <tr>
                <td colspan ="2">
                </td>
            </tr>
        </table></div>
        <div style="float:left;"><table id="tb3">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Pants and Capris</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Yoga</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Toddler and Infant Jackets</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Backpacks and Duffles</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Sunglasses</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Hats</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Headbands</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Heart Rate Monitors</td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td colspan ="2">
                </td>
            </tr>
        </table></div>
    </FieldSet> 
</body>
</html>
