package ru.parser;


import org.apache.http.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserRU {

    public static List<ResultRU> receiveAllNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news").get();

        Elements headers = document.getElementsByClass("story__title");

        headers.forEach(header -> {
            Element aLink = header.child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href")) ? "https://yandex.ru/news" : aLink.attr("href"));
            String text = (TextUtils.isEmpty(header.text())) ? "Новости" : header.text();

            results.add(new ResultRU(url, text, "News"));
        });


        return results;
    }

    public static List<ResultRU> receivePolicyNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/politics?from=index").get();

        Elements headers = document.getElementsByClass("story__title");

        headers.forEach(header -> {
            Element aLink = header.child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://yandex.ru/news/rubric/politics?from=index" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Политика" : aLink.text();

            results.add(new ResultRU(url, text, "Policy"));
        });


        return results;
    }

    public static List<ResultRU> receiveMobileNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/computers?from=rubric").get();

        Elements headers = document.getElementsByClass("story__title");

        headers.forEach(header -> {
            Element aLink = header.child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://yandex.ru/news/rubric/computers?from=rubric" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Электроника" : aLink.text();

            results.add(new ResultRU(url, text, "Mobile"));
        });


        return results;
    }

    public static List<ResultRU> receiveCarsNewsResults() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/auto?from=rubric").get();

        Elements headers = document.getElementsByClass("story__title");

        headers.forEach(header -> {
            Element aLink = header.child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://yandex.ru/news/rubric/auto?from=rubric" : aLink.attr("href");
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

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/culture?from=rubric").get();

        Elements headers = document.getElementsByClass("story__title");

        headers.forEach(header -> {
            Element aLink = header.child(0);

            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://yandex.ru/news/rubric/culture?from=rubric" : aLink.attr("href");
            String text = (TextUtils.isEmpty(aLink.text())) ? "Искусство" : aLink.text();

            results.add(new ResultRU(url, text, "Art"));
        });


        return results;
    }
}
