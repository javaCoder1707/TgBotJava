package uk.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ParserUK {
    public static List<ResultUK> allNewsResults() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/").get();

        Elements aS = document.getElementsByClass("rail__item-anchor");
        Elements hS = document.getElementsByClass("rail__item-headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();

            if(text == null) resultUKS.add(new ResultUK(url, "News"));
            else resultUKS.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return resultUKS;
    }

    public static List<ResultUK> newsOfUKResults() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/uknews/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();

            if(text == null) resultUKS.add(new ResultUK(url, "NewsUK"));
            else resultUKS.add(new ResultUK(url, text));

            i.getAndIncrement();
        });


        return resultUKS;
    }

    public static List<ResultUK> worldNewsResults() throws IOException{
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/worldnews/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();

            if(text == null) resultUKS.add(new ResultUK(url, "News World"));
            else resultUKS.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return resultUKS;
    }

    public static List<ResultUK> politicNewsResults() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/politics/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();

            if(text == null) resultUKS.add(new ResultUK(url, "News Politic"));
            else resultUKS.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return resultUKS;
    }

    public static List<ResultUK> motorsNewsResults() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/motors/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();

            if(text == null) resultUKS.add(new ResultUK(url, "Motors"));
            else resultUKS.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return resultUKS;
    }

    public static List<ResultUK> sportNewsResults() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/sport/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();

            if(text == null) resultUKS.add(new ResultUK(url, "Sport"));
            else resultUKS.add(new ResultUK(url, text));

            i.getAndIncrement();
        });

        return resultUKS;
    }


}