package uk.parser;

import org.apache.http.util.TextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ParserUK {
    public static List<ResultUK> receiveAllNewsResults() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/").get();

        Elements aLinks = document.getElementsByClass("rail__item-anchor");
        Elements headers = document.getElementsByClass("rail__item-headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aLinks.forEach(a -> {
            String url = (TextUtils.isEmpty(a.attr("href"))) ? "https://www.thesun.co.uk/news/" : a.attr("href");
            String text = (TextUtils.isEmpty(headers.get(i.get()).text())) ? "News" : headers.get(i.get()).text();

            results.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return results;
    }

    public static List<ResultUK> receiveResultsNewsOfUK() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/uknews/").get();

        Elements aLinks = document.getElementsByClass("teaser-anchor");
        Elements headers = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aLinks.forEach(a -> {
            String url = (TextUtils.isEmpty(a.attr("href"))) ? "https://www.thesun.co.uk/news/uknews/" : a.attr("href");
            String text = (TextUtils.isEmpty(headers.get(i.get()).text())) ? "News of UK" : headers.get(i.get()).text();

            results.add(new ResultUK(url, text));

            i.getAndIncrement();
        });


        return results;
    }

    public static List<ResultUK> receiveWorldNewsResults() throws IOException{
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/worldnews/").get();

        Elements aLinks = document.getElementsByClass("teaser-anchor");
        Elements headers = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aLinks.forEach(aLink -> {
            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/news/worldnews/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(headers.get(i.get()).text())) ? "World News" : headers.get(i.get()).text();

            results.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return results;
    }

    public static List<ResultUK> receivePoliticNewsResults() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/politics/").get();

        Elements aLinks = document.getElementsByClass("teaser-anchor");
        Elements headers = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aLinks.forEach(aLink -> {
            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/news/politics/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(headers.get(i.get()).text())) ? "Politic News" : headers.get(i.get()).text();

            results.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return results;
    }

    public static List<ResultUK> receiveMotorsNewsResults() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/motors/").get();

        Elements aLinks = document.getElementsByClass("teaser-anchor");
        Elements headers = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aLinks.forEach(aLink -> {
            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/motors/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(headers.get(i.get()).text())) ? "Motors" : headers.get(i.get()).text();

            results.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return results;
    }

    public static List<ResultUK> receiveSportNewsResults() throws IOException {
        List<ResultUK> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/sport/").get();

        Elements aLinks = document.getElementsByClass("teaser-anchor");
        Elements headers = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aLinks.forEach(aLink -> {
            String url = (TextUtils.isEmpty(aLink.attr("href"))) ? "https://www.thesun.co.uk/sport/" : aLink.attr("href");
            String text = (TextUtils.isEmpty(headers.get(i.get()).text())) ? "Sport" : headers.get(i.get()).text();

           results.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return results;
    }


}
