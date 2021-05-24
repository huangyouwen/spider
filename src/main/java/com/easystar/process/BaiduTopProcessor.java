package com.easystar.process;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.stream.Collectors;

public class BaiduTopProcessor implements PageProcessor {

    public static final String URL_TOP_BAIDU_DETAIL = "http://top\\.baidu\\.com/detail.*";
    public static final String URL_BAIDU_LINK = "http://www\\.baidu\\.com/link.*";

    private Site site = Site
            .me()
            .setDomain("top.baidu.com")
            .setSleepTime(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().xpath("//ul[@id='hot-list']").links().regex(URL_TOP_BAIDU_DETAIL).all());
        page.addTargetRequests(page.getHtml().xpath("//div[@class='c-group-wrapper']//div[@id='3']//div[@class='c-row op_sp_realtime_new_group_gap op_sp_realtime_new_overflow'][1]").links().regex(URL_BAIDU_LINK).all().stream().distinct().collect(Collectors.toList()));
        page.putField("title", page.getHtml().xpath("//h2[@class='index-module_articleTitle_28fPT']/text()").toString());
        page.putField("content", page.getHtml().xpath("//div[@class='app-module_leftSection_EaCvy']").toString());
        page.putField("icon",page.getHtml().xpath("//div[@class='app-module_leftSection_EaCvy']").css("img","src").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
