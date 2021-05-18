<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="referrer" content="never">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1,viewport-fit=cover">
    <meta itemprop="dateUpdate" content="${.now?string('yyyy-MM-dd HH:mm:ss')}">
    <meta name="title" content="今马学习">
    <meta name="keywords" content="今马学习">
    <meta name="description" content="今马学习">
    <title>首页</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="common.css"/>
</head>
<body>
<div class="container">
    <h2>今马学习</h2>
    <#list articles as article>
        <div class="panel panel-info">
<#--            <div class="box">-->
<#--                <#if article.icon != null>-->
<#--                    <div class="item"></div>-->
<#--                </#if>-->
<#--                <div class="item">-->
<#--                    -->
<#--                </div>-->
<#--            </div>-->
            <div class="row">
                <#if article.icon != null>
                <div class="col-md-2"><img src="${article.icon}"></div>
                <div class="col-md-10"><div><a href="${article.fileName}" target="_blank"><h3>${article.title}</h3></a></div><div>${article.createDate?string('yyyy-MM-dd HH:mm:ss')}</div></div>
                <#else>
                <div class="col-md-12"><div><a href="${article.fileName}" target="_blank"><h3>${article.title}</h3></a></div><div>${article.createDate?string('yyyy-MM-dd HH:mm:ss')}</div></div>
                </#if>
            </div>
        </div>
    </#list>

</div>
<footer class="footer">
    <div class="container">
        <a href="https://beian.miit.gov.cn" target="_blank">湘ICP备2021008098号-1</a>
    </div>
</footer>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>
</html>