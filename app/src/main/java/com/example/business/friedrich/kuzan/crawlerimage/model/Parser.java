package com.example.business.friedrich.kuzan.crawlerimage.model;

import android.util.Log;

import com.example.business.friedrich.kuzan.crawlerimage.ui.web_search.WebFragmentPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Parser {

    private static final int NUMBER_ATTEMPT = 5;

    private int mCounterWentPage;
    private int mMaxPage;
    private int mMaxDepth;
    private int mAttempt;

    public Parser() {
        mCounterWentPage = 0;
        mAttempt = 0;
    }

    public int getmMaxPage() {
        return mMaxPage;
    }

    public void setmMaxPage(int mMaxPage) {
        this.mMaxPage = mMaxPage;
    }

    public int getmCounterWentPage() {
        return mCounterWentPage;
    }

    public void setmCounterWentPage(int mCounterWentPage) {
        this.mCounterWentPage = mCounterWentPage;
    }

    public ParsedData beginParse(ArrayList<String> urls, String url){
        ++mCounterWentPage;

        Document doc;
        double startTime = System.nanoTime();

        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {

            if (mAttempt < NUMBER_ATTEMPT) {
                ++mAttempt;
                return beginParse(urls, url);
            }

            mAttempt = 0;
            Log.d("ErrorParser", e.getMessage());
            return null;
        }

        if (mMaxDepth == -1) {
            return parse(urls, doc, url, startTime);
        } else {
            return parseWithDepth(urls, doc, url, startTime);
        }
    }

    public ParsedData parse(ArrayList<String> urls, Document doc, String url, double startTime) {
        int counterTags = doc.getAllElements().size();

        int numberImg = doc.select("img[src*=" + getNameDOM(url) + "]").size();
        counterTags -= doc.select("img").size() - numberImg;

        Elements links = doc.select("a[href]");

        for (Element link : links) {
            if (mMaxPage >= urls.size() + mCounterWentPage) {
                String slink = link.attr("abs:href");
                if (slink.equals("")) {
                    slink = url;
                }
                urls.add(slink);
            } else {
                break;
            }
        }

        return new ParsedData(counterTags, getTime(startTime, System.nanoTime()), url);
    }

    public ParsedData parseWithDepth(ArrayList<String> urls, Document doc, String url, double startTime) {
        int counterTags = doc.select("*:lt(" + mMaxDepth + ")").size();

        int numberImg = doc.select("img[src*=" + getNameDOM(url) + "]:lt(" + mMaxDepth + ")").size();
        counterTags -= doc.select("img:lt(" + mMaxDepth + ")").size() - numberImg;

        Elements links = doc.select("a[href]:lt(" + mMaxDepth + ")");

        for (Element link : links) {
            if (mMaxPage >= urls.size() + mCounterWentPage) {
                String slink = link.attr("abs:href");
                if (slink.equals("")) {
                    slink = url;
                }
                urls.add(slink);
            } else {
                break;
            }
        }

        return new ParsedData(counterTags, getTime(startTime, System.nanoTime()), url);
    }

    private double getTime(double startTime, double endTime) {
        double number = (endTime - startTime) / 1000000000;
        return Math.round(number * 100.0) / 100.0;
    }

    private String getNameDOM(String url) {
        String dom = url.contains(url)
                ? url.replace("https://", "")
                : url.replace("http://", "");

        int end = dom.indexOf("/");

        if (end == -1) {
            return dom;
        } else {
            return dom.substring(0, end);
        }
    }

    public int getmMaxDepth() {
        return mMaxDepth;
    }

    public void setmMaxDepth(int mMaxDepth) {
        this.mMaxDepth = mMaxDepth;
    }
}
