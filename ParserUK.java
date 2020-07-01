package uk.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ParserUK {
    public static List<ResultUK> getParsAll() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/").get();

        Elements aS = document.getElementsByClass("rail__item-anchor");
        Elements hS = document.getElementsByClass("rail__item-headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();
            resultUKS.add(new ResultUK(url, text));
            i.getAndIncrement();
        });

        return resultUKS;
    }

    public static List<ResultUK> getParsUK() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/uknews/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();
            resultUKS.add(new ResultUK(url, text));
            i.getAndIncrement();
        });

        return resultUKS;
    }

    public static List<ResultUK> getParsWorld() throws IOException{
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/worldnews/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();
            resultUKS.add(new ResultUK(url, text));
            i.getAndIncrement();
        });

        return resultUKS;
    }

    public static List<ResultUK> getParsPolitic() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/news/politics/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();
            resultUKS.add(new ResultUK(url, text));
            i.getAndIncrement();
        });

        return resultUKS;
    }

    public static List<ResultUK> getParsMotors() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/motors/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();
            resultUKS.add(new ResultUK(url, text));
            i.getAndIncrement();
        });

        return resultUKS;
    }

    public static List<ResultUK> getParsSport() throws IOException {
        List<ResultUK> resultUKS = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://www.thesun.co.uk/sport/").get();

        Elements aS = document.getElementsByClass("teaser-anchor");
        Elements hS = document.getElementsByClass("teaser__headline theme__copy-color");
        AtomicInteger i = new AtomicInteger(0);

        aS.forEach(a -> {
            String url = a.attr("href");
            String text = hS.get(i.get()).text();
            resultUKS.add(new ResultUK(url, text));
            i.getAndIncrement();
        });

        return resultUKS;
    }


}