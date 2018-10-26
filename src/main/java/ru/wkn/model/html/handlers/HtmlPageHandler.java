package ru.wkn.model.html.handlers;

import ru.wkn.model.html.page.elements.Element;
import ru.wkn.model.html.page.elements.image.Image;

import java.util.ArrayList;
import java.util.List;

public class HtmlPageHandler {

    public static List<Image> imagesFromTheSameSite(List<Image> htmlImages, String uriAddress) {
        if (htmlImages != null) {
            List<Image> imagesFromTheSameSite = new ArrayList<>();
            for (Image currentImage : htmlImages) {
                String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
                if (currentHrefToLowerCase.startsWith(uriAddress.toLowerCase())) {
                    imagesFromTheSameSite.add(currentImage);
                }
            }
            return imagesFromTheSameSite;
        }
        return null;
    }

    public static List<Image> imagesFromTheOtherSite(List<Image> htmlImages, String uriAddress) {
        if (htmlImages != null) {
            List<Image> imagesFromTheOtherSite = new ArrayList<>();
            for (Image currentImage : htmlImages) {
                String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
                if (!currentHrefToLowerCase.startsWith(uriAddress.toLowerCase())) {
                    imagesFromTheOtherSite.add(currentImage);
                }
            }
            return imagesFromTheOtherSite;
        }
        return null;
    }

    public static List<Image> selectImagesFromHtmlElements(List<Element> elements) {
        List<Image> images = new ArrayList<>();
        for (Element element : elements) {
            if (element instanceof Image) {
                images.add((Image) element);
            }
        }
        return images;
    }
}
