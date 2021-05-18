package com.easystar.process;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.stream.Collectors;

public class BaiduImageProcessor implements PageProcessor {

    //https://image.baidu.com/search/detail?ct=503316480&z=9&ipn=d&word=%E5%85%B3%E6%99%93%E5%BD%A4%E5%86%99%E7%9C%9F&step_word=&hs=0&pn=0&spn=0&di=55330&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=449398392%2C248736163&os=2986822860%2C2874992717&simid=3448728295%2C4275678377&adpicid=0&lpn=0&ln=570&fr=&fmq=1621318316263_R&fm=&ic=undefined&s=undefined&hd=0&latest=0&copyright=0&se=&sme=&tab=0&width=0&height=0&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F10532582266%2F0.jpg%26refer%3Dhttp%3A%2F%2Finews.gtimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1623910780%26t%3D12395d4cc5349213fe892b5bd69ee401&fromurl=ippr_z2C%24qAzdH3FAzdH3Fh7wtkw5_z%26e3Bqq_z%26e3Bv54AzdH3FfAzdH3Fdadaa0acAa9PIYaa%3F6juj6%3Dfrt1j6_r7fi&gsm=1&rpstart=0&rpnum=0&islist=&querylist=&force=undefined
    //https://image.baidu.com/search/index?ct=201326592&z=9&tn=baiduimage&word=%E5%85%B3%E6%99%93%E5%BD%A4%E5%86%99%E7%9C%9F&pn=0&ie=utf-8&oe=utf-8&cl=2&lm=-1&fr=ala&se=&sme=&width=0&height=0&hd=0&latest=0&copyright=0
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
//        page.addTargetRequests(page.getHtml().xpath("//div[@class='c-group-wrapper']//div[@id='3']").links().regex(URL_BAIDU_LINK).all().stream().distinct().collect(Collectors.toList()));
//        page.putField("title", page.getHtml().xpath("//h2[@class='index-module_articleTitle_28fPT']/text()").toString());
//        page.putField("content", page.getHtml().xpath("//div[@class='app-module_leftSection_EaCvy']").toString());
//        page.putField("icon",page.getHtml().xpath("//div[@class='app-module_leftSection_EaCvy']").css("img","src").toString());
//        page.addTargetRequests(page.getHtml().xpath("//div[@class='img-wrapper']").links().regex(URL_BAIDU_LINK).all());
        page.putField("src",page.getHtml().xpath("//div[@class='img-wrapper']//img[@id='currentImg']").css("img","src").toString());
//        page.putField("content",page.getHtml().xpath("body").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}