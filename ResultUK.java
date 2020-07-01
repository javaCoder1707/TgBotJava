package uk.parser;

public class ResultUK {
    private String url;
    private String text;

    public ResultUK(String url, String text){
        this.url = url;
        this.text = text;
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
        return this.text + "\n" + this.url;
    }
}