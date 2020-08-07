package ru.parser;


import org.apache.http.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParserRU {

    public static List<ResultRU> receiveAllNewsResults() throws IOException{
        List<ResultRU> results = new ArrayList<>();
        results.addAll(receivePolicyNewsResults());
        results.addAll(receiveMobileNewsResults());
        results.addAll(receiveCarsNewsResults());
        results.addAll(receiveSportNewsResults().subList(0, 25));
        results.addAll(receiveArtNewsResults());

        Collections.shuffle(results);

        return results;
    }

    public static List<ResultRU> receivePolicyNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://ria.ru/politics/").get();

        Elements divs = document.getElementsByClass("list-item__content");

        divs.forEach(div -> {
            Element aLink = div.child(1);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://ria.ru/politics/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Политика" : aLink.text();

            results.add(new ResultRU(url, text, "Policy"));
        });

        return results;
    }

    public static List<ResultRU> receiveMobileNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://ria.ru/technology/").get();

        Elements divs = document.getElementsByClass("list-item__content");

        divs.forEach(div -> {
            Element aLink = div.child(1);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://ria.ru/technology/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Технологии" : aLink.text();

            results.add(new ResultRU(url, text, "Mobile"));
        });

        return results;
    }

    public static List<ResultRU> receiveCarsNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://ria.ru/auto/").get();

        Elements divs = document.getElementsByClass("list-item__content");

        divs.forEach(div -> {
            Element aLink = div.child(1);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://ria.ru/auto/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Авто" : aLink.text();

            results.add(new ResultRU(url, text, "Cars"));
        });

        return results;
    }

    public static List<ResultRU> receiveSportNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.sports.ru/news").get();

        Elements aLinks = document.getElementsByClass("short-text");

        aLinks.forEach(aLink -> {
            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.sports.ru/news" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Спорт" : aLink.text();

            results.add(new ResultRU(url, text, "Sport"));
        });

        return results;
    }

    public static List<ResultRU> receiveArtNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();
        results.addAll(receiveFilmsNewsResults());
        results.addAll(receiveMusicNewsResults());
        
        Collections.shuffle(results);
        
        return results;
    }

    private static List<ResultRU> receiveFilmsNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://ria.ru/category_kino/").get();

        Elements divs = document.getElementsByClass("list-item__content");

        divs.forEach(div -> {
            Element aLink = div.child(1);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://ria.ru/category_kino/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Искусство" : aLink.text();

            results.add(new ResultRU(url, text, "Art"));
        });

        return results;
    }

    private static List<ResultRU> receiveMusicNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://ria.ru/category_muzyka/").get();

        Elements divs = document.getElementsByClass("list-item__content");

        divs.forEach(div -> {
            Element aLink = div.child(1);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://ria.ru/category_muzyka/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Искусство" : aLink.text();

            results.add(new ResultRU(url, text, "Art"));
        });

        return results;
    }
}
