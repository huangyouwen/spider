<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="referrer" content="never">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1,viewport-fit=cover">
    <meta itemprop="dateUpdate" content="${.now?string('yyyy-MM-dd HH:mm:ss')}">
    <meta name="title" content="首页">
    <meta name="keywords" content="金马在线">
    <meta name="description" content="金马在线">
    <title>首页</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <link rel="stylesheet" href="common.css"/>
</head>
<body>
<div class="container">
    <h2>金马在线</h2>
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
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>