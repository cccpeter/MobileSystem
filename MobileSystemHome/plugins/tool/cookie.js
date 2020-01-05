var ROOT = getRootPath();
var REST = "http://localhost:7800"
/**
 * [getCookie description]
 * @author cpeter
 * @param  {[type]} cname [description]
 * @return {[type]}       [description]
 */
function getCookie(cname)
{
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) 
  {
    var c = ca[i].trim();
    if (c.indexOf(name)==0) return c.substring(name.length,c.length);
  }
  return "";
}
function setCookie(cname,cvalue,sec)
{
  var d = new Date();
  d.setTime(d.getTime()+(sec*999));
  var expires = "Expires="+d.toGMTString();
  // alert(cname + "=" + cvalue + ";Path=/; " + expires);
  document.cookie = cname + "=" + cvalue + ";Path=/; " + expires;
}
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
    document.cookie= name + "="+cval+";Path=/;expires="+exp.toGMTString();
}
function getRootPath(){ 
  var strFullPath=window.document.location.href; 
  var strPath=window.document.location.pathname; 
  var pos=strFullPath.indexOf(strPath); 
  var prePath=strFullPath.substring(0,pos); 
  var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1); 
  return(prePath+postPath); 
} 
/**
* 根据变量名获取匹配值
*/
function getQueryString(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
  var r = window.location.search.substr(1).match(reg);
  if (r != null) return unescape(r[2]); return null;
} 
/**
 * @desc      [闭包封装异步请求]
 * @Author    cpeter
 * @copyright [copyright]
 * @version   [version]
 * @param     {[type]}    url      [description]
 * @param     {[type]}    type     [description]
 * @param     {[type]}    data     [description]
 * @param     {Function}  callback [description]
 * @return    {[type]}             [description]
 */
var $http = function(url,type,data,callback){
  let username = getCookie("username");
  let token = getCookie("token");
  $.ajax({
    type: type,
    url: REST+url,
    contentType: "application/x-www-form-urlencoded",
    beforeSend: function(request) {
        request.setRequestHeader("Authorization", token);
    },
    data:data,
    success: function(res) { 
      switch(res.code){
        case 200:
        break;
        case 401:
          alert(res.msg);
        break;
        case 500:
          alert(res.msg);
        break;
        case 404:
          alert(res.msg);
        break;
        default:
          alert(res.msg);
        break;
      }
      // 回调函数
      callback(res);
    }
  })
}

























