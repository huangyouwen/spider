<?php
// 设置返回json格式数据
header('content-type:application/json;charset=utf8');
 
//连接数据库
$link = mysql_connect("bdm714070431.my3w.com:3306", "bdm714070431", "Geobim123") or die('Could not connect: ' . mysql_error());
 
mysql_query("SET NAMES 'UTF8'");
 
mysql_select_db("bdm714070431_db", $link) or die('Could not connect: ' . mysql_error());
 
// 获取分页参数
$page = 0 ;
$pageSize = 3;
 
if(!is_null($_GET["page"])) {
$page = $_GET["page"];
}
 
if(!is_null($_GET["pageSize"])) {
$pageSize = $_GET["pageSize"];
}
 
// 查询数据到数组中
$result = mysql_query("select * from tb_image limit " . $page . ", ". $pageSize ."");
 
$results = array();
while ($row = mysql_fetch_assoc($result)) {
$results[] = $row;
}
 
// 将数组转成json格式
echo json_encode($results);
 
// 关闭连接
mysql_free_result($result);
 
mysql_close($link);
?>