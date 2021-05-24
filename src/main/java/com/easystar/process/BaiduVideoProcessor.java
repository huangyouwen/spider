package com.easystar.process;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class BaiduVideoProcessor implements PageProcessor {
    public static final String URL_TOP_BAIDU_DETAIL = "https://baijiahao\\.baidu\\.com/s.*";
    public static final String URL_BAIDU_LINK = "http://gimg2\\.baidu\\.com/image_search/src=.*";

    private Site site = Site
            .me()
            .setDomain("www.baidu.com")
            .setSleepTime(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().xpath("//div[@class='video_small_intro']").links().regex(URL_TOP_BAIDU_DETAIL).all());
        page.putField("img",page.getHtml().xpath("//div[@class='c-img c-img-radius-large short-video-img']//img").css("img","src").nodes());
        page.putField("src",page.getHtml().xpath("//video").toString());
        page.putField("title",page.getHtml().xpath("//h1[@class='videoinfo-title']/text()").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
