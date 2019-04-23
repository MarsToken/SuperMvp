package com.ly.supermvp.model.entity;

/**
 * <Pre>
 * 大中小图片
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 15;//47
 */
public class PictureImage {
    private String big;// http;////image.tianjimedia.com/uploadImages/2014/308/28/968836971LMM.JPEG,
    private String middle;// http;////image.tianjimedia.com/uploadImages/2014/308/28/968836971LMM_680x500.JPEG,
    private String small;// http;////image.tianjimedia.com/uploadImages/2014/308/28/968836971LMM_113.JPEG

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }
}
