package ru.parser;

public class ResultRU {
    private String url;
    private String text;
    private String type;

    public ResultRU(String url, String text, String type){
        this.url = url;
        this.text = text;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        if(this.type.equals("Sport")) return this.text + "\n" + "https://www.sports.ru/news" + this.url;

        return this.text + "\n" + "https://yandex.ru" + this.url;
    }
}
