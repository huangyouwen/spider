<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="referrer" content="never">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1,viewport-fit=cover">
    <meta itemprop="dateUpdate" content="${.now?string('yyyy-MM-dd HH:mm:ss')}">
    <meta name="title" content="${video.title},今马学习，视频">
    <meta name="keywords" content="${video.title},今马学习，视频">
    <meta name="description" content="${video.title},今马学习，视频">
    <title>${video.title}</title>
    <link href="http://libs.baidu.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="./../common.css"/>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="./../clipboard/clipboard.min.js"></script>
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

    /* .btn-share{
        position: fixed;
        top:70px;
        right: 10px;
    } */
    .panel-heading {
        text-align: right;
    }
    video{
        width:100%;
    }

</style>
<script>
    $(function () {
        $("#myAlert").hide();
        var clipboard = new ClipboardJS('.btn-share', {
            text: function (trigger) {
                return window.location.href + '  '+ trigger.getAttribute('data-clipboard-text') + ' 好看花鼓戏 ';
            }
        });
        clipboard.on('success', function (e) {
            console.info('Action:', e.action);
            console.info('Text:', e.text);
            console.info('Trigger:', e.trigger);

            e.clearSelection();
            $("#myAlert").show();
        });

        clipboard.on('error', function (e) {
        });
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
                    <li ><a href="http://www.jinma-online.cn/index.html#article">新闻 <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="active"><a href="http://www.jinma-online.cn/index.html#video">视频</a></li>
                    <li><a href="http://www.jinma-online.cn/index.html#image">图片</a></li>
                    <!-- <li><a href="#">小说</a></li> -->
                </ul>

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</header>
<div id="myAlert" class="alert alert-success">
    <a href="#" class="close" onclick="$('#myAlert').hide();">&times;</a>
    <strong>复制成功，去粘贴给你的好友吧！</strong>
</div>
<div class="container-fluid">

    <video controls="" autoplay="autoplay" loop="loop" muted="muted" x5-video-player-fullscreen="true"
           x5-playsinline
           playsinline
           webkit-playsinline poster="${video.icon}" name="media"><source src="${video.src}" type="video/mp4"></video>
    <div class="row">
        <div class="col col-md-8">${video.title}</div>
        <div class="col col-md-2"><button class="btn btn-default btn-share" data-clipboard-text="${video.title}">转发</button></div>
    </div>
<#--    <div class="row">-->
<#--        <div class="col col-md-6"><a href="">上一个</a></div>-->
<#--        <div class="col col-md-6"><a href="">下一个</a></div>-->
<#--    </div>-->

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