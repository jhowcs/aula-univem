package com.univem.aula03_imagemdaweb;


public class ActionFigure {

    private String name;
    private String urlImage;

    public ActionFigure(String name, String urlImage) {
        this.name = name;
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
