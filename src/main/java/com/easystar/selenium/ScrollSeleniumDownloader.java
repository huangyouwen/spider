package com.easystar.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ScrollSeleniumDownloader implements Downloader, Closeable {
    private volatile MyWebDriverPool webDriverPool;
    private Logger logger = Logger.getLogger(this.getClass());
    private int sleepTime = 0;
    private int poolSize = 1;
    private static final String DRIVER_PHANTOMJS = "phantomjs";

    public ScrollSeleniumDownloader(String chromeDriverPath) {
        System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    public ScrollSeleniumDownloader() {
    }

    public ScrollSeleniumDownloader setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public Page download(Request request, Task task) {
        this.checkInit();

        WebDriver webDriver;
        try {
            webDriver = this.webDriverPool.get();
        } catch (InterruptedException var10) {
            this.logger.warn("interrupted", var10);
            return null;
        }

        this.logger.info("downloading page " + request.getUrl());
        webDriver.get(request.getUrl());

        try {
            Thread.sleep((long)this.sleepTime);
        } catch (InterruptedException var9) {
            var9.printStackTrace();
        }

        WebDriver.Options manage = webDriver.manage();
        Site site = task.getSite();
        if (site.getCookies() != null) {
            Iterator var6 = site.getCookies().entrySet().iterator();

            while(var6.hasNext()) {
                Map.Entry<String, String> cookieEntry = (Map.Entry)var6.next();
                Cookie cookie = new Cookie((String)cookieEntry.getKey(), (String)cookieEntry.getValue());
                manage.addCookie(cookie);
            }
        }

        if(request.getUrl().indexOf("/sf/vsearch?pd=video") > 0) {
            for (int i = 0; i < 2; i++) {
                System.out.println("休眠1s");
                try {
                    //滚动到最底部
                    ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
                    //休眠，等待加载页面
                    Thread.sleep(2000);
                    //往回滚一点，否则不加载
                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        String content = webElement.getAttribute("outerHTML");
        Page page = new Page();
        page.setRawText(content);
        page.setHtml(new Html(content, request.getUrl()));
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        this.webDriverPool.returnToPool(webDriver);
        return page;
    }

    private void checkInit() {
        if (this.webDriverPool == null) {
            synchronized(this) {
                this.webDriverPool = new MyWebDriverPool(this.poolSize);
            }
        }

    }

    public void setThread(int thread) {
        this.poolSize = thread;
    }

    public void close() throws IOException {
        this.webDriverPool.closeAll();
    }
}
