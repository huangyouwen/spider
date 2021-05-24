package com.easystar.pipeline;

import com.easystar.utils.HtmlUtil;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Collections;
import java.util.List;

public class VideoPipeline implements Pipeline {

    public VideoPipeline (){

    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String src = resultItems.get("src");
        String title = resultItems.get("title");
        List<Selectable> imgs = resultItems.get("img");
        for(Selectable img : imgs ){
            System.out.println(img.toString());
        }
        if(StringUtils.isNotBlank(src)) {
            System.out.println(HtmlUtil.getValueByKeyInHtml(src,"src"));
            System.out.println(title);
//            SqlSession sqlSession = SpiderApplication.sqlSessionFactory.openSession();
//            try{
//                ImageDao imageDao = sqlSession.getMapper(ImageDao.class);
//                Image image = new Image();
//                image.setCreateDate(new Date());
//                image.setKey("");
//                image.setSrcPath("");
//                image.setPath(src);
//                image.setConfigId(configId);
//                imageDao.insertImage(image);
//                sqlSession.commit();
//            }finally {
//                sqlSession.close();
//            }
        }
    }
}
