<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Product Brand List</title>
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
                }  else if(i >= 30 && i < 40) {
                    var table = document.getElementById("tb4");
                    var str = table.rows[i-30].cells[1].innerHTML;
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
    <FieldSet style="font-size:9pt;width:500px; margin:10,10,10,10;">
        <legend >Product brand</legend>
        <div style="float:left;"><table id="tb1">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Adidas</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Antigua</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Asics</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>anon</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Brooks</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Burton</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Columbia</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Dallas Cowboys</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Fit 2 Win</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Flow Society</td>
            </tr>
            <tr>
                <td colspan ="2">
                    <input type="button" value="button" onclick ="Go_Submit();" />
                </td>
            </tr>
        </table></div>
        <div style="float:left;"><table id="tb2">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Glamorise</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Jordan</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>KÖPPEN</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Marmot</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Mountain Hardwear</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Moving Comfort</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>NFL Team Apparel</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Nike</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Patagonia</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Patagonia</td>
            </tr>
            <tr>
                <td colspan ="2">
                </td>
            </tr>
        </table></div>
        <div style="float:left;"><table id="tb3">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Puma</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>R.E.D</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Reebok</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>SKINS</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Saucony</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Moving Comfort</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Shock Absorber</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Spanx</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Spyder</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>TaylorMade</td>
            </tr>
            <tr>
                <td colspan ="2">
                </td>
            </tr>
        </table></div>
        <div style="float:left;"><table id="tb4">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>The North Face</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Under Armour</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Warrior</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>lucy</td>
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
                <td></td>
                <td></td>
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