package com.easystar.pipeline;

import com.easystar.SpiderApplication;
import com.easystar.dao.VideoDao;
import com.easystar.entity.Video;
import com.easystar.utils.HtmlUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class VideoPipeline implements Pipeline {

    private static List<Selectable> imgs;
    private static int index;

    public VideoPipeline (){

    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String src = resultItems.get("src");
        String title = resultItems.get("title");
        List<Selectable> imgs = resultItems.get("img");
        if(imgs.size() > 0) {
            VideoPipeline.imgs = imgs;
            VideoPipeline.index = 0;
        }
        if(StringUtils.isNotBlank(src)) {
            VideoPipeline.imgs.get(VideoPipeline.index);
            VideoPipeline.index ++;
            SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
            try{
                VideoDao videoDao = sqlSession.getMapper(VideoDao.class);
                String fileName  = System.currentTimeMillis() + ".html";
                Video video = videoDao.getVideoBySrc(src);
                if(video == null){
                    video = new Video();
                    video.setCreateDate(new Date());
                    video.setIcon( VideoPipeline.imgs.get(VideoPipeline.index).toString());
                    video.setSrc(src);
                    video.setTitle(title);
                    video.setFileName(fileName);
                    Map<String,Object> map = new HashMap<>();
                    Template template = SpiderApplication.configuration.getTemplate("video.ftl");
                    map.put("video",video);
//                    File file = new File("C://Program Files//Spider//"+fileName);
                    File file = new File("D://Spider//"+fileName);
                    FileWriter fileWriter = new FileWriter(file);
                    template.process(map,fileWriter);
                    fileWriter.close();
                    if(SpiderApplication.isUploadFtp)
                        SpiderApplication.ftpCli.uploadFileToDailyDir("htdocs//video",fileName, new FileInputStream(file));
                    file.delete();

                    videoDao.insertVideo(video);
                    sqlSession.commit();
                }
            }catch (TemplateException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                sqlSession.close();
            }
        }
    }
}
