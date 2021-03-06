<!doctype html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta name="referrer" content="never">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1,viewport-fit=cover">
    <meta itemprop="dateUpdate" content="2021-05-18 17:33:31">
    <meta name="title" content="首页">
    <meta name="keywords" content="今马学习,新闻,女明星照片,女明星高清照片,小说">
    <meta name="description" content="今马学习,新闻,女明星照片,女明星高清照片,小说">
    <title>首页</title>
    <link href="http://libs.baidu.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="common.css"/>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<style>
    body {
        padding-top: 70px;
        position: relative;
    }

    footer {
        padding-top: 50px;
        padding-bottom: 50px;
        margin-top: 100px;

        text-align: center;
        background-color: #2a2730;


    }

    footer > .container > a {
        color: #99979c !important;
    }

    .back-to-top {
        position: fixed;
        padding: 4px 10px;
        bottom: 50px;
        right: 10px;
        font-size: 12px;
        font-weight: 500;
        color: #999;
    }

    .media img {
        width: 64px;
        height: 64px;
    }
    #image .list-group-item{
        display: inline;
        border: none;
    }
    #article{
        font-size: 16px;
    }
</style>
<script>
    $(function () {
        $('body').scrollspy({target: '#bs-example-navbar-collapse-1'});
        $('[data-spy="scroll"]').each(function () {
            var $spy = $(this).scrollspy('refresh')
        })

    })
</script>

<body data-spy="scroll" data-target="#bs-example-navbar-collapse-1">
<header id="top">
    <nav class="navbar  navbar-inverse  navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="http://www.jinma-online.cn">今马学习</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#article">新闻 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#video">视频</a></li>
                    <li><a href="#image">图片</a></li>
                    <!-- <li><a href="#">小说</a></li> -->
                </ul>

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</header>
<div class="container">
    <div class="panel panel-primary" name="article" id="article">
        <div class="panel-heading">
            <h3 class="panel-title">新闻</h3>
        </div>
        <div class="panel-body">


            <ul class="media-list">
                <#list articles as article>

                    <li class="media">
                        <#if article.icon !=null>
                            <div class="media-left">
                                <a href="article/${article.fileName}">
                                    <img class="media-object" src="${article.icon}"
                                         alt="${article.title}">
                                </a>
                            </div>
                        </#if>
                        <div class="media-body">
                            <h4 class="media-heading"> <a href="article/${article.fileName}">${article.title}</a></h4>
                            ${article.createDate?string('yyyy-MM-dd HH:mm:ss')}
                        </div>
                    </li>

                </#list>
            </ul>


        </div>
    </div>
    <div class="panel panel-success" name="video" id="video">
        <div class="panel-heading">
            <h3 class="panel-title">视频</h3>
        </div>
        <div class="panel-body">


            <ul class="media-list">
                <#list videos as video>

                    <li class="media">
                        <div class="media-left">
                            <a href="video/${video.fileName}">
                                <img class="media-object" src="${video.icon}"
                                     alt="${video.title}">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading"> <a href="video/${video.fileName}">${video.title}</a></h4>
                            ${video.createDate?string('yyyy-MM-dd HH:mm:ss')}
                        </div>
                    </li>

                </#list>
            </ul>


        </div>
    </div>
    <div class="panel panel-info" name="image" id="image">
        <div class="panel-heading">
            <h3 class="panel-title">图片</h3>
        </div>
        <div class="panel-body">
            <ul class="list-group">
                <#list imageConfigs as imageConfig>
                    <li class="list-group-item"><a  href="${imageConfig.name}/index.html">${imageConfig.cnName}</a></li>
                </#list>
            </ul>
        </div>
    </div>

</div>
<footer class="footer">
    <div class="container">
        <a href="https://beian.miit.gov.cn" target="_blank">湘ICP备2021008098号-1</a>
    </div>
</footer>
<a class="back-to-top" href="#top">
    返回顶部
</a>
</body>

</html>