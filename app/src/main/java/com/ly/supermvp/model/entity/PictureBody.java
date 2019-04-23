package com.ly.supermvp.model.entity;

import java.util.List;

/**
 * <Pre>
 * 美图大全图片
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 15;//45
 */
public class PictureBody {
    private String ct;// 2016-03-10 04;//12;//06.606,
    private String itemId;// 39889571,
    private String title;// 清纯美女头像壁纸 葵花丛中的女孩,
    private String type;// 4001,
    private String typeName;// 清纯
    private List<PictureImage> list;//图片数组

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<PictureImage> getList() {
        return list;
    }

    public void setList(List<PictureImage> list) {
        this.list = list;
    }
}
