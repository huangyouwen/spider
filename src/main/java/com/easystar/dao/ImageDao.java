package com.easystar.dao;

import com.easystar.entity.Image;

import java.util.List;

public interface ImageDao {

    void insertImage(Image image);

    List<Image> getImageByConfigId(long configId);
}
