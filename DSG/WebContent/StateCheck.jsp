<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Store State List</title>
</head>
<script>
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
                }  else if(i >= 40 && i < 50) {
                    var table = document.getElementById("tb5");
                    var str = table.rows[i-40].cells[1].innerHTML;
                    tmp += str + ", ";
                }  else {
                    var table = document.getElementById("tb6");
                    var str = table.rows[i-50].cells[1].innerHTML;
                    tmp += str + ", ";
                }                
            }
        }
        
        var returnvalue = tmp;
        window.returnValue = returnvalue;
        top.window.close();
    }

</script>
<body>
    <FieldSet style="font-size:9pt;width:600px;height:260px;margin:10,10,10,10;">
        <legend >Store State</legend>
        <div style="float:left;"><table id="tb1">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>All</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Alabama</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Alaska</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Arizona</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Arkansas</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>California</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Colorado</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Connecticut</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Delaware</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>District of Columbia</td>
            </tr>
            <tr>
                <td colspan ="2">
                    <input type="button" value="button" onclick ="Go_Submit();" />
                </td>
            </tr>
        </table>
        </div>
        <div style="float:left;"><table id="tb2">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Florida</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Georgia</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Hawaii</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Idaho</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Illinois</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Indiana</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Iowa</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Kansas</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Kentucky</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Louisiana</td>
            </tr>
            <tr>
                <td colspan ="2">
                </td>
            </tr>
        </table>
        </div>
        <div style="float:left;"><table id="tb3">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Maine</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Maryland</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Massachusetts</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Michigan</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Minnesota</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Mississippi</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Missouri</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Montana</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Nebraska</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Nevada</td>
            </tr>
            <tr>
                <td colspan ="2">
                </td>
            </tr>
        </table>
        </div>
        <div style="float:left;"><table id="tb4">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>New Hampshire</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>New Jersey</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>New Mexico</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>New York</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>North Carolina</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>North Dakota</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Ohio</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Oklahoma</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Oregon</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Pennsylvania</td>
            </tr>
            <tr>
                <td colspan ="2">
                </td>
            </tr>
        </table>
        </div>
        <div style="float:left;"><table id="tb5">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Rhode island</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>South Carolina</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>South Dakota</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Tennessee</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Texas</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Utah</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Vermont</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Virginia</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Washington</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>West Virginia</td>
            </tr>
            <tr>
                <td colspan ="2">
                </td>
            </tr>
        </table>
        </div>
        <div style="float:left;"><table id="tb6">
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Wisconsin</td>
            </tr>
            <tr>
                <td><input name = "chk" type="checkbox" /></td>
                <td>Wyoming</td>
            </tr>
        </table>
        </div>
    </FieldSet> 
</body>
</html>
