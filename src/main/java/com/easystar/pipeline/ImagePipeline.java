package com.easystar.pipeline;

import com.easystar.SpiderApplication;
import com.easystar.dao.ImageDao;
import com.easystar.entity.Image;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;

public class ImagePipeline implements Pipeline {

    private Long configId;

    public ImagePipeline (Long configId){
        this.configId = configId;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String src = resultItems.get("src");
        if(StringUtils.isNotBlank(src)) {
            SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
            try{
                ImageDao imageDao = sqlSession.getMapper(ImageDao.class);
                Image image = new Image();
                image.setCreateDate(new Date());
                image.setKey("");
                image.setSrcPath("");
                image.setPath(src);
                image.setConfigId(configId);
                imageDao.insertImage(image);
                sqlSession.commit();
            }finally {
                sqlSession.close();
            }
        }
    }
}

