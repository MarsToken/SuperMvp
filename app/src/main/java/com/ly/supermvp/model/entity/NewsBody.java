package com.ly.supermvp.model.entity;

import java.util.List;

/**
 * <Pre>
 *     单条新闻
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 15:38
 */
public class NewsBody {
    private String channelId;// 5572a109b3cdc86cf39001db,
    private String channelName;// 国内最新,
    private String chinajoy;// 0,
    private String desc;// 令越来越多的上市公司因为大股东股权质押触及预警线或平仓线面临危机，截至目前已经有6家上市公司因...,
    private List<NewsImage> imageurls;// [],
    private String link;// http://finance.qq.com/a/20160128/011948.htm,
    private String long_desc;// 来自国金证券策略团队的梳理数据显示，截至1月26日，A股股权质押在押市值规模约为2.7万亿~3.0万亿元，占A股总市值约为6.5%，这一数值甚至高于2015年8月中旬的4.2%的水平。从股权质押增量数据看，2014H1、2014年H2、2015年H1、2015年H2发生的股权质押市值分别约为6647.4亿元、8781.2亿元、14236亿元、17081.2亿元，规模不断扩张，增长迅速。,
    private String nid;// 11430626766375070847,
    private String pubDate;// 2016-01-28 11:47:17,
    private String source;// 腾讯财经,
    private String title;// 80家上市公司陷股权质押危局 市值接近3万亿

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChinajoy() {
        return chinajoy;
    }

    public void setChinajoy(String chinajoy) {
        this.chinajoy = chinajoy;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<NewsImage> getImageurls() {
        return imageurls;
    }

    public void setImageurls(List<NewsImage> imageurls) {
        this.imageurls = imageurls;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLong_desc() {
        return long_desc;
    }

    public void setLong_desc(String long_desc) {
        this.long_desc = long_desc;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
