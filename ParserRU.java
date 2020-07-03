package ru.parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserRU {


    public static List<ResultRU> getPars() throws IOException {
        List<ResultRU> results = new ArrayList<>();
        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();

            if(text == null) results.add(new ResultRU(url, "Новости"));
            else results.add(new ResultRU(url, text));
        });

        return results;
    }

    public static List<ResultRU> getParsPolicy() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/politics?from=index").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();

            if(text == null) results.add(new ResultRU(url, "Политика"));
            else results.add(new ResultRU(url, text));
        });


        return results;
    }

    public static List<ResultRU> getParsMobile() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/computers?from=rubric").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();

            if(text == null) results.add(new ResultRU(url, "Электроника"));
            else results.add(new ResultRU(url, text));
        });


        return results;
    }

    public static List<ResultRU> getParsCars() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/auto?from=rubric").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();

            if(text == null) results.add(new ResultRU(url, "Автомобили"));
            else results.add(new ResultRU(url, text));
        });


        return results;
    }

    public static List<ResultRU> getParsSport() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/sport").get();

        Elements cardTitle = document.getElementsByClass("card__title");

        cardTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();

            if(text == null) results.add(new ResultRU(url, "Спорт"));
            else results.add(new ResultRU(url, text));
        });


        return results;
    }

    public static List<ResultRU> getParsArt() throws IOException {
        List<ResultRU> results = new ArrayList<>();

        System.out.println("Parsing...");
        Document document = Jsoup.connect("https://yandex.ru/news/rubric/culture?from=rubric").get();

        Elements storyTitle = document.getElementsByClass("story__title");

        storyTitle.forEach(page -> {
            Element a = page.child(0);
            String url = a.attr("href");
            String text = a.text();

            if(text == null) results.add(new ResultRU(url, "Искусство "));
            else results.add(new ResultRU(url, text));
        });


        return results;
    }
}
