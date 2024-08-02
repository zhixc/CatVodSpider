package com.github.catvod.spider;

import com.github.catvod.bean.Class;
import com.github.catvod.bean.Result;
import com.github.catvod.bean.Vod;
import com.github.catvod.net.OkHttp;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhixc
 */
public class Wogg extends Ali {

    private final String siteUrl = "https://www.wogg.net";
    private final Pattern regexPageTotal = Pattern.compile("\\$\\(\"\\.mac_total\"\\)\\.text\\('(\\d+)'\\);");


    private final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:102.0) Gecko/20100101 Firefox/102.0";


    private Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("User-Agent", userAgent);
        return header;
    }


    @Override
    public String homeContent(boolean filter) throws Exception {

        // 爬取二级筛选数据用的，部分需要额外处理一下，如排序的值
        /*String url = "https://tvfan.xxooo.cf/index.php/vodshow/5-----------.html";
        String typeId = "5";
        String typeName = "音乐";
        LinkedHashMap<String, List<Filter>> filters = new LinkedHashMap<>();
        String html = OkHttp.string(url, getHeader());
        Elements elements = Jsoup.parse(html).select(".scroll-content");

        List<Filter> filterList = new ArrayList<>();
        // vodshow/1-地1区-排2序-剧3情-语4言-字5母------时11间.html
        for (int i = 0; i < elements.size(); i++) {
            if (i == 0) continue;
            Element e = elements.get(i);
            e.child(0).text();
            List<Filter.Value> values = new ArrayList<>();
            values.add(new Filter.Value(e.child(0).text(), ""));
            for (Element a : e.select(".library-list > .library-item")) {
                values.add(new Filter.Value(a.text(), a.text()));
            }
            String extendV = e.child(0).text().replace("全部", "");
            String extendK = "";
            if (extendV.contains("剧情")) extendK = "3";
            if (extendV.contains("地区")) extendK = "1";
            if (extendV.contains("语言")) extendK = "4";
            if (extendV.contains("时间")) extendK = "11";
            if (extendV.contains("字母")) extendK = "5";
            if (extendV.contains("排序")) extendK = "2";
            if (extendK.equals("")) extendK = "未知的键位";
            filterList.add(new Filter(extendK, extendV, values));
        }
        filters.put(typeId, filterList);*/


        List<Class> classes = new ArrayList<>();
        List<String> typeIds = Arrays.asList("1", "2", "3", "4", "5", "6");
        List<String> typeNames = Arrays.asList("电影", "剧集", "动漫", "综艺", "音乐", "短剧");
        for (int i = 0; i < typeIds.size(); i++) classes.add(new Class(typeIds.get(i), typeNames.get(i)));
        String f = "{\"1\": [{\"key\": \"3\", \"name\": \"剧情\", \"value\": [{\"n\": \"全部剧情\", \"v\": \"\"}, {\"n\": \"喜剧\", \"v\": \"喜剧\"}, {\"n\": \"爱情\", \"v\": \"爱情\"}, {\"n\": \"恐怖\", \"v\": \"恐怖\"}, {\"n\": \"动作\", \"v\": \"动作\"}, {\"n\": \"科幻\", \"v\": \"科幻\"}, {\"n\": \"剧情\", \"v\": \"剧情\"}, {\"n\": \"战争\", \"v\": \"战争\"}, {\"n\": \"警匪\", \"v\": \"警匪\"}, {\"n\": \"犯罪\", \"v\": \"犯罪\"}, {\"n\": \"古装\", \"v\": \"古装\"}, {\"n\": \"奇幻\", \"v\": \"奇幻\"}, {\"n\": \"武侠\", \"v\": \"武侠\"}, {\"n\": \"冒险\", \"v\": \"冒险\"}, {\"n\": \"枪战\", \"v\": \"枪战\"}, {\"n\": \"恐怖\", \"v\": \"恐怖\"}, {\"n\": \"悬疑\", \"v\": \"悬疑\"}, {\"n\": \"惊悚\", \"v\": \"惊悚\"}, {\"n\": \"经典\", \"v\": \"经典\"}, {\"n\": \"青春\", \"v\": \"青春\"}, {\"n\": \"文艺\", \"v\": \"文艺\"}, {\"n\": \"微电影\", \"v\": \"微电影\"}, {\"n\": \"历史\", \"v\": \"历史\"}]}, {\"key\": \"1\", \"name\": \"地区\", \"value\": [{\"n\": \"全部地区\", \"v\": \"\"}, {\"n\": \"大陆\", \"v\": \"大陆\"}, {\"n\": \"香港\", \"v\": \"香港\"}, {\"n\": \"台湾\", \"v\": \"台湾\"}, {\"n\": \"美国\", \"v\": \"美国\"}, {\"n\": \"法国\", \"v\": \"法国\"}, {\"n\": \"英国\", \"v\": \"英国\"}, {\"n\": \"日本\", \"v\": \"日本\"}, {\"n\": \"韩国\", \"v\": \"韩国\"}, {\"n\": \"德国\", \"v\": \"德国\"}, {\"n\": \"泰国\", \"v\": \"泰国\"}, {\"n\": \"印度\", \"v\": \"印度\"}, {\"n\": \"意大利\", \"v\": \"意大利\"}, {\"n\": \"西班牙\", \"v\": \"西班牙\"}, {\"n\": \"加拿大\", \"v\": \"加拿大\"}, {\"n\": \"其他\", \"v\": \"其他\"}]}, {\"key\": \"4\", \"name\": \"语言\", \"value\": [{\"n\": \"全部语言\", \"v\": \"\"}, {\"n\": \"国语\", \"v\": \"国语\"}, {\"n\": \"英语\", \"v\": \"英语\"}, {\"n\": \"粤语\", \"v\": \"粤语\"}, {\"n\": \"闽南语\", \"v\": \"闽南语\"}, {\"n\": \"韩语\", \"v\": \"韩语\"}, {\"n\": \"日语\", \"v\": \"日语\"}, {\"n\": \"法语\", \"v\": \"法语\"}, {\"n\": \"德语\", \"v\": \"德语\"}, {\"n\": \"其它\", \"v\": \"其它\"}]}, {\"key\": \"11\", \"name\": \"时间\", \"value\": [{\"n\": \"全部时间\", \"v\": \"\"}, {\"n\": \"2023\", \"v\": \"2023\"}, {\"n\": \"2022\", \"v\": \"2022\"}, {\"n\": \"2021\", \"v\": \"2021\"}, {\"n\": \"2020\", \"v\": \"2020\"}, {\"n\": \"2019\", \"v\": \"2019\"}, {\"n\": \"2018\", \"v\": \"2018\"}, {\"n\": \"2017\", \"v\": \"2017\"}, {\"n\": \"2016\", \"v\": \"2016\"}, {\"n\": \"2015\", \"v\": \"2015\"}, {\"n\": \"2014\", \"v\": \"2014\"}, {\"n\": \"2013\", \"v\": \"2013\"}, {\"n\": \"2012\", \"v\": \"2012\"}, {\"n\": \"2011\", \"v\": \"2011\"}, {\"n\": \"2010\", \"v\": \"2010\"}]}, {\"key\": \"5\", \"name\": \"字母查找\", \"value\": [{\"n\": \"全部字母\", \"v\": \"\"}, {\"n\": \"A\", \"v\": \"A\"}, {\"n\": \"B\", \"v\": \"B\"}, {\"n\": \"C\", \"v\": \"C\"}, {\"n\": \"D\", \"v\": \"D\"}, {\"n\": \"E\", \"v\": \"E\"}, {\"n\": \"F\", \"v\": \"F\"}, {\"n\": \"G\", \"v\": \"G\"}, {\"n\": \"H\", \"v\": \"H\"}, {\"n\": \"I\", \"v\": \"I\"}, {\"n\": \"J\", \"v\": \"J\"}, {\"n\": \"K\", \"v\": \"K\"}, {\"n\": \"L\", \"v\": \"L\"}, {\"n\": \"M\", \"v\": \"M\"}, {\"n\": \"N\", \"v\": \"N\"}, {\"n\": \"O\", \"v\": \"O\"}, {\"n\": \"P\", \"v\": \"P\"}, {\"n\": \"Q\", \"v\": \"Q\"}, {\"n\": \"R\", \"v\": \"R\"}, {\"n\": \"S\", \"v\": \"S\"}, {\"n\": \"T\", \"v\": \"T\"}, {\"n\": \"U\", \"v\": \"U\"}, {\"n\": \"V\", \"v\": \"V\"}, {\"n\": \"W\", \"v\": \"W\"}, {\"n\": \"X\", \"v\": \"X\"}, {\"n\": \"Y\", \"v\": \"Y\"}, {\"n\": \"Z\", \"v\": \"Z\"}, {\"n\": \"0-9\", \"v\": \"0-9\"}]}, {\"key\": \"2\", \"name\": \"排序\", \"value\": [{\"n\": \"默认排序\", \"v\": \"\"}, {\"n\": \"时间排序\", \"v\": \"time\"}, {\"n\": \"人气排序\", \"v\": \"hits\"}, {\"n\": \"评分排序\", \"v\": \"score\"}]}], \"2\": [{\"key\": \"3\", \"name\": \"剧情\", \"value\": [{\"n\": \"全部剧情\", \"v\": \"\"}, {\"n\": \"喜剧\", \"v\": \"喜剧\"}, {\"n\": \"爱情\", \"v\": \"爱情\"}, {\"n\": \"恐怖\", \"v\": \"恐怖\"}, {\"n\": \"动作\", \"v\": \"动作\"}, {\"n\": \"科幻\", \"v\": \"科幻\"}, {\"n\": \"剧情\", \"v\": \"剧情\"}, {\"n\": \"战争\", \"v\": \"战争\"}, {\"n\": \"警匪\", \"v\": \"警匪\"}, {\"n\": \"犯罪\", \"v\": \"犯罪\"}, {\"n\": \"古装\", \"v\": \"古装\"}, {\"n\": \"奇幻\", \"v\": \"奇幻\"}, {\"n\": \"武侠\", \"v\": \"武侠\"}, {\"n\": \"冒险\", \"v\": \"冒险\"}, {\"n\": \"枪战\", \"v\": \"枪战\"}, {\"n\": \"恐怖\", \"v\": \"恐怖\"}, {\"n\": \"悬疑\", \"v\": \"悬疑\"}, {\"n\": \"惊悚\", \"v\": \"惊悚\"}, {\"n\": \"经典\", \"v\": \"经典\"}, {\"n\": \"青春\", \"v\": \"青春\"}, {\"n\": \"文艺\", \"v\": \"文艺\"}, {\"n\": \"微电影\", \"v\": \"微电影\"}, {\"n\": \"历史\", \"v\": \"历史\"}]}, {\"key\": \"1\", \"name\": \"地区\", \"value\": [{\"n\": \"全部地区\", \"v\": \"\"}, {\"n\": \"大陆\", \"v\": \"大陆\"}, {\"n\": \"香港\", \"v\": \"香港\"}, {\"n\": \"台湾\", \"v\": \"台湾\"}, {\"n\": \"美国\", \"v\": \"美国\"}, {\"n\": \"法国\", \"v\": \"法国\"}, {\"n\": \"英国\", \"v\": \"英国\"}, {\"n\": \"日本\", \"v\": \"日本\"}, {\"n\": \"韩国\", \"v\": \"韩国\"}, {\"n\": \"德国\", \"v\": \"德国\"}, {\"n\": \"泰国\", \"v\": \"泰国\"}, {\"n\": \"印度\", \"v\": \"印度\"}, {\"n\": \"意大利\", \"v\": \"意大利\"}, {\"n\": \"西班牙\", \"v\": \"西班牙\"}, {\"n\": \"加拿大\", \"v\": \"加拿大\"}, {\"n\": \"其他\", \"v\": \"其他\"}]}, {\"key\": \"4\", \"name\": \"语言\", \"value\": [{\"n\": \"全部语言\", \"v\": \"\"}, {\"n\": \"国语\", \"v\": \"国语\"}, {\"n\": \"英语\", \"v\": \"英语\"}, {\"n\": \"粤语\", \"v\": \"粤语\"}, {\"n\": \"闽南语\", \"v\": \"闽南语\"}, {\"n\": \"韩语\", \"v\": \"韩语\"}, {\"n\": \"日语\", \"v\": \"日语\"}, {\"n\": \"法语\", \"v\": \"法语\"}, {\"n\": \"德语\", \"v\": \"德语\"}, {\"n\": \"其它\", \"v\": \"其它\"}]}, {\"key\": \"11\", \"name\": \"时间\", \"value\": [{\"n\": \"全部时间\", \"v\": \"\"}, {\"n\": \"2023\", \"v\": \"2023\"}, {\"n\": \"2022\", \"v\": \"2022\"}, {\"n\": \"2021\", \"v\": \"2021\"}, {\"n\": \"2020\", \"v\": \"2020\"}, {\"n\": \"2019\", \"v\": \"2019\"}, {\"n\": \"2018\", \"v\": \"2018\"}, {\"n\": \"2017\", \"v\": \"2017\"}, {\"n\": \"2016\", \"v\": \"2016\"}, {\"n\": \"2015\", \"v\": \"2015\"}, {\"n\": \"2014\", \"v\": \"2014\"}, {\"n\": \"2013\", \"v\": \"2013\"}, {\"n\": \"2012\", \"v\": \"2012\"}, {\"n\": \"2011\", \"v\": \"2011\"}, {\"n\": \"2010\", \"v\": \"2010\"}]}, {\"key\": \"5\", \"name\": \"字母查找\", \"value\": [{\"n\": \"全部字母\", \"v\": \"\"}, {\"n\": \"A\", \"v\": \"A\"}, {\"n\": \"B\", \"v\": \"B\"}, {\"n\": \"C\", \"v\": \"C\"}, {\"n\": \"D\", \"v\": \"D\"}, {\"n\": \"E\", \"v\": \"E\"}, {\"n\": \"F\", \"v\": \"F\"}, {\"n\": \"G\", \"v\": \"G\"}, {\"n\": \"H\", \"v\": \"H\"}, {\"n\": \"I\", \"v\": \"I\"}, {\"n\": \"J\", \"v\": \"J\"}, {\"n\": \"K\", \"v\": \"K\"}, {\"n\": \"L\", \"v\": \"L\"}, {\"n\": \"M\", \"v\": \"M\"}, {\"n\": \"N\", \"v\": \"N\"}, {\"n\": \"O\", \"v\": \"O\"}, {\"n\": \"P\", \"v\": \"P\"}, {\"n\": \"Q\", \"v\": \"Q\"}, {\"n\": \"R\", \"v\": \"R\"}, {\"n\": \"S\", \"v\": \"S\"}, {\"n\": \"T\", \"v\": \"T\"}, {\"n\": \"U\", \"v\": \"U\"}, {\"n\": \"V\", \"v\": \"V\"}, {\"n\": \"W\", \"v\": \"W\"}, {\"n\": \"X\", \"v\": \"X\"}, {\"n\": \"Y\", \"v\": \"Y\"}, {\"n\": \"Z\", \"v\": \"Z\"}, {\"n\": \"0-9\", \"v\": \"0-9\"}]}, {\"key\": \"2\", \"name\": \"排序\", \"value\": [{\"n\": \"默认排序\", \"v\": \"\"}, {\"n\": \"时间排序\", \"v\": \"time\"}, {\"n\": \"人气排序\", \"v\": \"hits\"}, {\"n\": \"评分排序\", \"v\": \"score\"}]}], \"3\": [{\"key\": \"3\", \"name\": \"剧情\", \"value\": [{\"n\": \"全部剧情\", \"v\": \"\"}, {\"n\": \"情感\", \"v\": \"情感\"}, {\"n\": \"科幻\", \"v\": \"科幻\"}, {\"n\": \"热血\", \"v\": \"热血\"}, {\"n\": \"推理\", \"v\": \"推理\"}, {\"n\": \"搞笑\", \"v\": \"搞笑\"}, {\"n\": \"冒险\", \"v\": \"冒险\"}, {\"n\": \"萝莉\", \"v\": \"萝莉\"}, {\"n\": \"校园\", \"v\": \"校园\"}, {\"n\": \"动作\", \"v\": \"动作\"}, {\"n\": \"机战\", \"v\": \"机战\"}, {\"n\": \"运动\", \"v\": \"运动\"}, {\"n\": \"战争\", \"v\": \"战争\"}, {\"n\": \"少年\", \"v\": \"少年\"}, {\"n\": \"少女\", \"v\": \"少女\"}, {\"n\": \"社会\", \"v\": \"社会\"}, {\"n\": \"原创\", \"v\": \"原创\"}, {\"n\": \"亲子\", \"v\": \"亲子\"}, {\"n\": \"益智\", \"v\": \"益智\"}, {\"n\": \"励志\", \"v\": \"励志\"}, {\"n\": \"其他\", \"v\": \"其他\"}]}, {\"key\": \"1\", \"name\": \"地区\", \"value\": [{\"n\": \"全部地区\", \"v\": \"\"}, {\"n\": \"大陆\", \"v\": \"大陆\"}, {\"n\": \"香港\", \"v\": \"香港\"}, {\"n\": \"台湾\", \"v\": \"台湾\"}, {\"n\": \"美国\", \"v\": \"美国\"}, {\"n\": \"法国\", \"v\": \"法国\"}, {\"n\": \"英国\", \"v\": \"英国\"}, {\"n\": \"日本\", \"v\": \"日本\"}, {\"n\": \"韩国\", \"v\": \"韩国\"}, {\"n\": \"德国\", \"v\": \"德国\"}, {\"n\": \"泰国\", \"v\": \"泰国\"}, {\"n\": \"印度\", \"v\": \"印度\"}, {\"n\": \"意大利\", \"v\": \"意大利\"}, {\"n\": \"西班牙\", \"v\": \"西班牙\"}, {\"n\": \"加拿大\", \"v\": \"加拿大\"}, {\"n\": \"其他\", \"v\": \"其他\"}]}, {\"key\": \"4\", \"name\": \"语言\", \"value\": [{\"n\": \"全部语言\", \"v\": \"\"}, {\"n\": \"国语\", \"v\": \"国语\"}, {\"n\": \"英语\", \"v\": \"英语\"}, {\"n\": \"粤语\", \"v\": \"粤语\"}, {\"n\": \"闽南语\", \"v\": \"闽南语\"}, {\"n\": \"韩语\", \"v\": \"韩语\"}, {\"n\": \"日语\", \"v\": \"日语\"}, {\"n\": \"法语\", \"v\": \"法语\"}, {\"n\": \"德语\", \"v\": \"德语\"}, {\"n\": \"其它\", \"v\": \"其它\"}]}, {\"key\": \"11\", \"name\": \"时间\", \"value\": [{\"n\": \"全部时间\", \"v\": \"\"}, {\"n\": \"2023\", \"v\": \"2023\"}, {\"n\": \"2022\", \"v\": \"2022\"}, {\"n\": \"2021\", \"v\": \"2021\"}, {\"n\": \"2020\", \"v\": \"2020\"}, {\"n\": \"2019\", \"v\": \"2019\"}, {\"n\": \"2018\", \"v\": \"2018\"}, {\"n\": \"2017\", \"v\": \"2017\"}, {\"n\": \"2016\", \"v\": \"2016\"}, {\"n\": \"2015\", \"v\": \"2015\"}, {\"n\": \"2014\", \"v\": \"2014\"}, {\"n\": \"2013\", \"v\": \"2013\"}, {\"n\": \"2012\", \"v\": \"2012\"}, {\"n\": \"2011\", \"v\": \"2011\"}, {\"n\": \"2010\", \"v\": \"2010\"}]}, {\"key\": \"5\", \"name\": \"字母查找\", \"value\": [{\"n\": \"全部字母\", \"v\": \"\"}, {\"n\": \"A\", \"v\": \"A\"}, {\"n\": \"B\", \"v\": \"B\"}, {\"n\": \"C\", \"v\": \"C\"}, {\"n\": \"D\", \"v\": \"D\"}, {\"n\": \"E\", \"v\": \"E\"}, {\"n\": \"F\", \"v\": \"F\"}, {\"n\": \"G\", \"v\": \"G\"}, {\"n\": \"H\", \"v\": \"H\"}, {\"n\": \"I\", \"v\": \"I\"}, {\"n\": \"J\", \"v\": \"J\"}, {\"n\": \"K\", \"v\": \"K\"}, {\"n\": \"L\", \"v\": \"L\"}, {\"n\": \"M\", \"v\": \"M\"}, {\"n\": \"N\", \"v\": \"N\"}, {\"n\": \"O\", \"v\": \"O\"}, {\"n\": \"P\", \"v\": \"P\"}, {\"n\": \"Q\", \"v\": \"Q\"}, {\"n\": \"R\", \"v\": \"R\"}, {\"n\": \"S\", \"v\": \"S\"}, {\"n\": \"T\", \"v\": \"T\"}, {\"n\": \"U\", \"v\": \"U\"}, {\"n\": \"V\", \"v\": \"V\"}, {\"n\": \"W\", \"v\": \"W\"}, {\"n\": \"X\", \"v\": \"X\"}, {\"n\": \"Y\", \"v\": \"Y\"}, {\"n\": \"Z\", \"v\": \"Z\"}, {\"n\": \"0-9\", \"v\": \"0-9\"}]}, {\"key\": \"2\", \"name\": \"排序\", \"value\": [{\"n\": \"默认排序\", \"v\": \"\"}, {\"n\": \"时间排序\", \"v\": \"time\"}, {\"n\": \"人气排序\", \"v\": \"hits\"}, {\"n\": \"评分排序\", \"v\": \"score\"}]}], \"4\": [{\"key\": \"1\", \"name\": \"地区\", \"value\": [{\"n\": \"全部地区\", \"v\": \"\"}, {\"n\": \"大陆\", \"v\": \"大陆\"}, {\"n\": \"香港\", \"v\": \"香港\"}, {\"n\": \"台湾\", \"v\": \"台湾\"}, {\"n\": \"美国\", \"v\": \"美国\"}, {\"n\": \"法国\", \"v\": \"法国\"}, {\"n\": \"英国\", \"v\": \"英国\"}, {\"n\": \"日本\", \"v\": \"日本\"}, {\"n\": \"韩国\", \"v\": \"韩国\"}]}, {\"key\": \"11\", \"name\": \"时间\", \"value\": [{\"n\": \"全部时间\", \"v\": \"\"}, {\"n\": \"2023\", \"v\": \"2023\"}, {\"n\": \"2022\", \"v\": \"2022\"}, {\"n\": \"2021\", \"v\": \"2021\"}, {\"n\": \"2020\", \"v\": \"2020\"}, {\"n\": \"2019\", \"v\": \"2019\"}, {\"n\": \"2018\", \"v\": \"2018\"}, {\"n\": \"2017\", \"v\": \"2017\"}, {\"n\": \"2016\", \"v\": \"2016\"}, {\"n\": \"2015\", \"v\": \"2015\"}, {\"n\": \"2014\", \"v\": \"2014\"}, {\"n\": \"2013\", \"v\": \"2013\"}, {\"n\": \"2012\", \"v\": \"2012\"}, {\"n\": \"2011\", \"v\": \"2011\"}, {\"n\": \"2010\", \"v\": \"2010\"}]}, {\"key\": \"5\", \"name\": \"字母查找\", \"value\": [{\"n\": \"全部字母\", \"v\": \"\"}, {\"n\": \"A\", \"v\": \"A\"}, {\"n\": \"B\", \"v\": \"B\"}, {\"n\": \"C\", \"v\": \"C\"}, {\"n\": \"D\", \"v\": \"D\"}, {\"n\": \"E\", \"v\": \"E\"}, {\"n\": \"F\", \"v\": \"F\"}, {\"n\": \"G\", \"v\": \"G\"}, {\"n\": \"H\", \"v\": \"H\"}, {\"n\": \"I\", \"v\": \"I\"}, {\"n\": \"J\", \"v\": \"J\"}, {\"n\": \"K\", \"v\": \"K\"}, {\"n\": \"L\", \"v\": \"L\"}, {\"n\": \"M\", \"v\": \"M\"}, {\"n\": \"N\", \"v\": \"N\"}, {\"n\": \"O\", \"v\": \"O\"}, {\"n\": \"P\", \"v\": \"P\"}, {\"n\": \"Q\", \"v\": \"Q\"}, {\"n\": \"R\", \"v\": \"R\"}, {\"n\": \"S\", \"v\": \"S\"}, {\"n\": \"T\", \"v\": \"T\"}, {\"n\": \"U\", \"v\": \"U\"}, {\"n\": \"V\", \"v\": \"V\"}, {\"n\": \"W\", \"v\": \"W\"}, {\"n\": \"X\", \"v\": \"X\"}, {\"n\": \"Y\", \"v\": \"Y\"}, {\"n\": \"Z\", \"v\": \"Z\"}, {\"n\": \"0-9\", \"v\": \"0-9\"}]}, {\"key\": \"2\", \"name\": \"排序\", \"value\": [{\"n\": \"默认排序\", \"v\": \"\"}, {\"n\": \"时间排序\", \"v\": \"time\"}, {\"n\": \"人气排序\", \"v\": \"hits\"}, {\"n\": \"评分排序\", \"v\": \"score\"}]}], \"6\": [{\"key\": \"3\", \"name\": \"剧情\", \"value\": [{\"n\": \"全部剧情\", \"v\": \"\"}, {\"n\": \"古装\", \"v\": \"古装\"}, {\"n\": \"战争\", \"v\": \"战争\"}, {\"n\": \"青春偶像\", \"v\": \"青春偶像\"}, {\"n\": \"喜剧\", \"v\": \"喜剧\"}, {\"n\": \"家庭\", \"v\": \"家庭\"}, {\"n\": \"犯罪\", \"v\": \"犯罪\"}, {\"n\": \"动作\", \"v\": \"动作\"}, {\"n\": \"奇幻\", \"v\": \"奇幻\"}, {\"n\": \"剧情\", \"v\": \"剧情\"}, {\"n\": \"历史\", \"v\": \"历史\"}, {\"n\": \"经典\", \"v\": \"经典\"}, {\"n\": \"乡村\", \"v\": \"乡村\"}, {\"n\": \"情景\", \"v\": \"情景\"}, {\"n\": \"商战\", \"v\": \"商战\"}, {\"n\": \"网剧\", \"v\": \"网剧\"}, {\"n\": \"其他\", \"v\": \"其他\"}]}, {\"key\": \"1\", \"name\": \"地区\", \"value\": [{\"n\": \"全部地区\", \"v\": \"\"}, {\"n\": \"内地\", \"v\": \"内地\"}]}, {\"key\": \"11\", \"name\": \"时间\", \"value\": [{\"n\": \"全部时间\", \"v\": \"\"}, {\"n\": \"2023\", \"v\": \"2023\"}, {\"n\": \"2022\", \"v\": \"2022\"}, {\"n\": \"2021\", \"v\": \"2021\"}, {\"n\": \"2020\", \"v\": \"2020\"}, {\"n\": \"2019\", \"v\": \"2019\"}, {\"n\": \"2018\", \"v\": \"2018\"}, {\"n\": \"2017\", \"v\": \"2017\"}, {\"n\": \"2016\", \"v\": \"2016\"}, {\"n\": \"2015\", \"v\": \"2015\"}, {\"n\": \"2014\", \"v\": \"2014\"}, {\"n\": \"2013\", \"v\": \"2013\"}, {\"n\": \"2012\", \"v\": \"2012\"}, {\"n\": \"2011\", \"v\": \"2011\"}, {\"n\": \"2010\", \"v\": \"2010\"}]}, {\"key\": \"5\", \"name\": \"字母查找\", \"value\": [{\"n\": \"全部字母\", \"v\": \"\"}, {\"n\": \"A\", \"v\": \"A\"}, {\"n\": \"B\", \"v\": \"B\"}, {\"n\": \"C\", \"v\": \"C\"}, {\"n\": \"D\", \"v\": \"D\"}, {\"n\": \"E\", \"v\": \"E\"}, {\"n\": \"F\", \"v\": \"F\"}, {\"n\": \"G\", \"v\": \"G\"}, {\"n\": \"H\", \"v\": \"H\"}, {\"n\": \"I\", \"v\": \"I\"}, {\"n\": \"J\", \"v\": \"J\"}, {\"n\": \"K\", \"v\": \"K\"}, {\"n\": \"L\", \"v\": \"L\"}, {\"n\": \"M\", \"v\": \"M\"}, {\"n\": \"N\", \"v\": \"N\"}, {\"n\": \"O\", \"v\": \"O\"}, {\"n\": \"P\", \"v\": \"P\"}, {\"n\": \"Q\", \"v\": \"Q\"}, {\"n\": \"R\", \"v\": \"R\"}, {\"n\": \"S\", \"v\": \"S\"}, {\"n\": \"T\", \"v\": \"T\"}, {\"n\": \"U\", \"v\": \"U\"}, {\"n\": \"V\", \"v\": \"V\"}, {\"n\": \"W\", \"v\": \"W\"}, {\"n\": \"X\", \"v\": \"X\"}, {\"n\": \"Y\", \"v\": \"Y\"}, {\"n\": \"Z\", \"v\": \"Z\"}, {\"n\": \"0-9\", \"v\": \"0-9\"}]}, {\"key\": \"2\", \"name\": \"排序\", \"value\": [{\"n\": \"默认排序\", \"v\": \"\"}, {\"n\": \"时间排序\", \"v\": \"time\"}, {\"n\": \"人气排序\", \"v\": \"hits\"}, {\"n\": \"评分排序\", \"v\": \"score\"}]}], \"5\": [{\"key\": \"5\", \"name\": \"字母查找\", \"value\": [{\"n\": \"全部字母\", \"v\": \"\"}, {\"n\": \"A\", \"v\": \"A\"}, {\"n\": \"B\", \"v\": \"B\"}, {\"n\": \"C\", \"v\": \"C\"}, {\"n\": \"D\", \"v\": \"D\"}, {\"n\": \"E\", \"v\": \"E\"}, {\"n\": \"F\", \"v\": \"F\"}, {\"n\": \"G\", \"v\": \"G\"}, {\"n\": \"H\", \"v\": \"H\"}, {\"n\": \"I\", \"v\": \"I\"}, {\"n\": \"J\", \"v\": \"J\"}, {\"n\": \"K\", \"v\": \"K\"}, {\"n\": \"L\", \"v\": \"L\"}, {\"n\": \"M\", \"v\": \"M\"}, {\"n\": \"N\", \"v\": \"N\"}, {\"n\": \"O\", \"v\": \"O\"}, {\"n\": \"P\", \"v\": \"P\"}, {\"n\": \"Q\", \"v\": \"Q\"}, {\"n\": \"R\", \"v\": \"R\"}, {\"n\": \"S\", \"v\": \"S\"}, {\"n\": \"T\", \"v\": \"T\"}, {\"n\": \"U\", \"v\": \"U\"}, {\"n\": \"V\", \"v\": \"V\"}, {\"n\": \"W\", \"v\": \"W\"}, {\"n\": \"X\", \"v\": \"X\"}, {\"n\": \"Y\", \"v\": \"Y\"}, {\"n\": \"Z\", \"v\": \"Z\"}, {\"n\": \"0-9\", \"v\": \"0-9\"}]}, {\"key\": \"2\", \"name\": \"排序\", \"value\": [{\"n\": \"默认排序\", \"v\": \"\"}, {\"n\": \"时间排序\", \"v\": \"time\"}, {\"n\": \"人气排序\", \"v\": \"hits\"}, {\"n\": \"评分排序\", \"v\": \"score\"}]}]}";
        return Result.string(classes, null, filter ? new JSONObject(f) : null);
    }

    @Override
    public String categoryContent(String tid, String pg, boolean filter, HashMap<String, String> extend) {
        String[] urlParams = new String[]{tid, "", "", "", "", "", "", "", pg, "", "", ""};
        if (extend != null && extend.size() > 0) {
            for (String key : extend.keySet()) {
                urlParams[Integer.parseInt(key)] = extend.get(key);
            }
        }
        Document doc = Jsoup.parse(OkHttp.string(String.format("%s/index.php/vodshow/%s.html", siteUrl, String.join("-", urlParams)), getHeader()));
        int page = Integer.parseInt(pg), limit = 72, total = 0;
        Matcher matcher = regexPageTotal.matcher(doc.html());
        if (matcher.find()) total = Integer.parseInt(matcher.group(1));
        int count = total <= limit ? 1 : ((int) Math.ceil(total / (double) limit));
        return Result.get().vod(parseVodListFromDoc(doc)).page(page, count, limit, total).string();
    }

    private List<Vod> parseVodListFromDoc(Document doc) {
        List<Vod> list = new ArrayList<>();
        Elements elements = doc.select(".module-item");
        for (Element e : elements) {
            String vodId = e.selectFirst(".video-name a").attr("href");
            String vodPic = e.selectFirst(".module-item-pic > img").attr("data-src");
            if (!vodPic.startsWith("http")) vodPic = siteUrl + vodPic;
            String vodName = e.selectFirst(".video-name").text();
            String vodRemarks = e.selectFirst(".module-item-text").text();
            list.add(new Vod(vodId, vodName, vodPic, vodRemarks));
        }
        return list;
    }

    @Override
    public String detailContent(List<String> ids) throws Exception {
        String vodId = ids.get(0);
        Document doc = Jsoup.parse(OkHttp.string(siteUrl + vodId, getHeader()));

        Vod item = new Vod();
        item.setVodId(vodId);
        item.setVodName(doc.selectFirst(".video-info-header > .page-title").text());
        item.setVodPic(doc.selectFirst(".module-item-pic img").attr("data-src"));
        item.setVodArea(doc.select(".video-info-header a.tag-link").last().text());
        item.setTypeName(String.join(",", doc.select(".video-info-header div.tag-link a").eachText()));

        List<String> shareLinks = doc.select(".module-row-text").eachAttr("data-clipboard-text");
        for (int i = 0; i < shareLinks.size(); i++) shareLinks.set(i, shareLinks.get(i).trim());

        item.setVodPlayFrom(super.detailContentVodPlayFrom(shareLinks));
        item.setVodPlayUrl(super.detailContentVodPlayUrl(shareLinks));

        Elements elements = doc.select(".video-info-item");
        for (Element e : elements) {
            String title = e.previousElementSibling().text();
            if (title.contains("导演")) {
                item.setVodDirector(String.join(",", e.select("a").eachText()));
            } else if (title.contains("主演")) {
                item.setVodActor(String.join(",", e.select("a").eachText()));
            } else if (title.contains("年代")) {
                item.setVodYear(e.selectFirst("a").text().trim());
            } else if (title.contains("备注")) {
                item.setVodRemarks(e.text().trim());
            } else if (title.contains("剧情")) {
                item.setVodContent(e.selectFirst(".sqjj_a").text().replace("[收起部分]", "").trim());
            }
        }

        return Result.string(item);
    }

    @Override
    public String searchContent(String key, boolean quick) throws Exception {
        return searchContent(key, "1");
    }

    @Override
    public String searchContent(String key, boolean quick, String pg) throws Exception {
        return searchContent(key, pg);
    }

    private String searchContent(String key, String pg) {
        String searchURL = siteUrl + String.format("/index.php/vodsearch/%s----------%s---.html", URLEncoder.encode(key), pg);
        String html = OkHttp.string(searchURL, getHeader());
        Elements items = Jsoup.parse(html).select(".module-search-item");
        List<Vod> list = new ArrayList<>();
        for (Element item : items) {
            String vodId = item.select(".video-serial").attr("href");
            String name = item.select(".video-serial").attr("title");
            String pic = item.select(".module-item-pic > img").attr("data-src");
            if (!pic.startsWith("http")) pic = siteUrl + pic;
            String remark = item.select(".video-tag-icon").text();
            list.add(new Vod(vodId, name, pic, remark));
        }
        return Result.string(list);
    }
}