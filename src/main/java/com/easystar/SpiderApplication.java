package com.easystar;

import com.easystar.dao.ArticleDao;
import com.easystar.db.MyBatisUtils;
import com.easystar.entity.Article;
import com.easystar.pipeline.ArticlePipeline;
import com.easystar.process.BaiduImageProcessor;
import com.easystar.process.BaiduTopProcessor;
import com.easystar.utils.FtpCli;
import com.easystar.utils.HttpUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class SpiderApplication {

    public static SqlSessionFactory sqlSessionFactory = null;
    public static Configuration configuration = null;
    public static FtpCli ftpCli = null;
    public static boolean isModifyIndex = false;
    public static boolean isUploadFtp = true;
    public static HttpUtil httpUtil = null;
    static {
        sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
        configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(SpiderApplication.class,"/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setClassicCompatible(true);
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        ftpCli = FtpCli.createFtpCli("39.106.226.161","wh-nb2tcpf0eofuq2yj91x","Geobim123");
        httpUtil = new HttpUtil();
    }

    static void modifyIndex(){
        try {
            Map<String,List> map = new HashMap<>();
            SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
            Template template = SpiderApplication.configuration.getTemplate("index.ftl");
            ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
            List<Article> list = articleDao.findArticlesByLastMonth();
            File file = new File("C://Program Files//Spider//index.html");
            map.put("articles",list);
            template.process(map,new FileWriter(file));
            if(isUploadFtp)
                SpiderApplication.ftpCli.uploadFileToDailyDir(file.getName(), new FileInputStream(file));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try{
//                    isModifyIndex = false;
//                    ftpCli.connect();
//                    Spider.create(new BaiduTopProcessor()).addPipeline(new ArticlePipeline()).addUrl("http://top.baidu.com/").run();
//                    if(isModifyIndex){
//                        modifyIndex();
//                    }
//                    ftpCli.disconnect();
//                }catch (IOException e){
//
//                }
//
//            }
//        },1000,1000*60*60*2);
        System.setProperty("selenuim_config", "D:\\Spider\\config.ini");
        Spider.create(new BaiduImageProcessor()).
                addUrl("https://image.baidu.com/search/index?ct=201326592&z=9&tn=baiduimage&word=%E5%85%B3%E6%99%93%E5%BD%A4%E5%86%99%E7%9C%9F%E7%85%A7%E7%89%87&pn=0&ie=utf-8&oe=utf-8&cl=2&lm=-1&fr=ala&se=&sme=&width=0&height=0")
                .setDownloader(new SeleniumDownloader("D:\\Spider\\chromedriver.exe").setSleepTime(1000)).run();



//        System.setProperty("webdriver.chrome.driver", "D://Spider//chromedriver.exe");
//
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.baidu.com");
//        // 获取 网页的 title
//        System.out.println("The testing page title is: " + driver.getTitle());
//
//        driver.quit();
    }
}