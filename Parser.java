package ru.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {


    public static List<Result> getPars() throws IOException {
        List<Result> results = new ArrayList<>();
        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();
            results.add(new Result(url, text));
        });

        return results;
    }

    public static List<Result> getParsPolicy() throws IOException {
        List<Result> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/politics?from=index").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();
            results.add(new Result(url, text));
        });


        return results;
    }

    public static List<Result> getParsMobile() throws IOException {
        List<Result> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/computers?from=rubric").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();
            results.add(new Result(url, text));
        });


        return results;
    }

    public static List<Result> getParsAuto() throws IOException {
        List<Result> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/auto?from=rubric").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();
            results.add(new Result(url, text));
        });


        return results;
    }

    public static List<Result> getParsSport() throws IOException {
        List<Result> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/sport").get();

        Elements cardTitle = document.getElementsByClass("card__title");

        cardTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();
            results.add(new Result(url, text));
        });


        return results;
    }

    public static List<Result> getParsArt() throws IOException {
        List<Result> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/culture?from=rubric").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();
            results.add(new Result(url, text));
        });


        return results;
    }
}
