<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="referrer" content="never">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1,viewport-fit=cover">
    <meta itemprop="dateUpdate" content="${.now?string('yyyy-MM-dd HH:mm:ss')}">
    <meta name="title" content="${article.title}">
    <meta name="keywords" content="${article.title}">
    <meta name="description" content="${article.title}">
    <title>${article.title}</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="common.css"/>
</head>
<body>
<div class="container">
    <h1>${article.title}</h1>
    <div class="panel panel-info">${article.content}</div>
</div>
<footer class="footer">
    <div class="container">
        <a href="https://beian.miit.gov.cn" target="_blank">湘ICP备2021008098号-1</a>
    </div>
</footer>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</body>
</html>