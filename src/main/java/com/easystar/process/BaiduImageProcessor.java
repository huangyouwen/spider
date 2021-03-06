package com.easystar.process;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.stream.Collectors;

public class BaiduImageProcessor implements PageProcessor {

    public static final String URL_TOP_BAIDU_DETAIL = "https://image\\.baidu\\.com/search/detail.*";
    public static final String URL_BAIDU_LINK = "http://gimg2\\.baidu\\.com/image_search/src=.*";

    private Site site = Site
            .me()
            .setDomain("image.baidu.com")
            .setSleepTime(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().xpath("//div[@class='imgbox']").links().regex(URL_TOP_BAIDU_DETAIL).all());
        page.putField("src",page.getHtml().xpath("//div[@class='img-wrapper']//img[@id='currentImg']").css("img","src").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}