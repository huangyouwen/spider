package com.easystar;

import com.easystar.dao.ArticleDao;
import com.easystar.dao.ImageConfigDao;
import com.easystar.dao.ImageDao;
import com.easystar.db.MyBatisUtils;
import com.easystar.entity.Article;
import com.easystar.entity.Image;
import com.easystar.entity.ImageConfig;
import com.easystar.pipeline.ArticlePipeline;
import com.easystar.pipeline.ImagePipeline;
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
        configuration.setClassForTemplateLoading(SpiderApplication.class, "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setClassicCompatible(true);
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        ftpCli = FtpCli.createFtpCli("39.106.226.161", "wh-nb2tcpf0eofuq2yj91x", "Geobim123");
        httpUtil = new HttpUtil();
//        try {
//            ftpCli.connect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void modifyIndex() {
        try {
            Map<String, List> map = new HashMap<>();
            SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
            Template template = SpiderApplication.configuration.getTemplate("index.ftl");
            ArticleDao articleDao = sqlSession.getMapper(ArticleDao.class);
            List<Article> list = articleDao.query(15);
            ImageConfigDao imageConfigDao = sqlSession.getMapper(ImageConfigDao.class);
            List<ImageConfig> imageConfigs = imageConfigDao.all();
//            File file = new File("C://Program Files//Spider//index.html");
            File file = new File("D://Spider//index.html");
            map.put("articles", list);
            map.put("imageConfigs",imageConfigs);
            template.process(map, new FileWriter(file));
//            if (isUploadFtp)
                SpiderApplication.ftpCli.uploadFileToDailyDir(file.getName(), new FileInputStream(file));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void createImageHtml(){
        try {
        Map<String, Object> map = new HashMap<>();
        SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
        Template template = SpiderApplication.configuration.getTemplate("image.ftl");
            ImageConfigDao imageConfigDao = sqlSession.getMapper(ImageConfigDao.class);
            ImageDao imageDao = sqlSession.getMapper(ImageDao.class);
            List<ImageConfig> imageConfigs = imageConfigDao.all();
            for(ImageConfig imageConfig : imageConfigs){
                List<Image> list = imageDao.getImageByConfigId(imageConfig.getId());
                map.put("list",list);
                map.put("imageConfig",imageConfig);
                File file = new File("D://Spider//"+imageConfig.getName()+"//index.html");
                File fileParent = file.getParentFile();
                if(!fileParent.exists()){
                    fileParent.mkdirs();
                }
                if(!file.exists()){
                    file.createNewFile();
                }
                template.process(map, new FileWriter(file));
                SpiderApplication.ftpCli.uploadFileToDailyDir("htdocs//"+imageConfig.getName(),file.getName(), new FileInputStream(file));
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void syncArtilce() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    isModifyIndex = false;
                    ftpCli.connect();
                    Spider.create(new BaiduTopProcessor()).addPipeline(new ArticlePipeline()).addUrl("http://top.baidu.com/").run();
                    if (isModifyIndex) {
                        modifyIndex();
                    }
                    ftpCli.disconnect();
                } catch (IOException e) {

                }

            }
        }, 1000, 1000 * 60 * 60 * 4);
    }

    public static void syncImage(){
        System.setProperty("selenuim_config", "D:\\Spider\\config.ini");
        SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
        try {
            ImageConfigDao imageConfigDao = sqlSession.getMapper(ImageConfigDao.class);
            List<ImageConfig> imageConfigs = imageConfigDao.all();

            for (ImageConfig imageConfig : imageConfigs) {
                Spider.create(new BaiduImageProcessor())
                        .addPipeline(new ImagePipeline(imageConfig.getId()))
                        .addUrl(imageConfig.getIndexPath())
                        .setDownloader(new SeleniumDownloader("D:\\Spider\\chromedriver.exe").setSleepTime(1000)).run();
            }
        }catch (Exception e){
        }finally {
            sqlSession.close();
        }
    }

    public static void main(String[] args) {
        //syncImage();
        syncArtilce();
        modifyIndex();
//        createImageHtml();
    }
}