package io.github.talelin.latticy.laver.model;


import lombok.Data;

import java.util.List;

@Data
public class SpuDetailDO extends SpuDO{

    private String categoryName;

    private String sketchSpecKeyName;

    private String defaultSkuTitle;

    private List<String> bannerImgs;

    private List<String> detailImgs;





}
