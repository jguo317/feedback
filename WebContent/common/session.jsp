<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript">
function openwindow(url,name,iWidth,iHeight)
{
    var url;                             //转向网页的地址;
    var name;                            //网页名称，可为空;
    var iWidth;                          //弹出窗口的宽度;
    var iHeight;                         //弹出窗口的高度;
    //获得窗口的垂直位置
    var iTop = (window.screen.availHeight-30-iHeight)/2;       
    //获得窗口的水平位置
    var iLeft = (window.screen.availWidth-10-iWidth)/2;          
    window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
}
	function login(){
		openwindow ('login.jsp','login', 400,100);
	}
</script>