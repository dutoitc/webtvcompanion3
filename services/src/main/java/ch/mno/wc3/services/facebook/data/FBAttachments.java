package ch.mno.wc3.services.facebook.data;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class FBAttachments {

    private List<Data> data = new ArrayList<>();

    @Setter
    @Getter
    public static class Data {
        private Media media;
        private Target target;
        private String title;
        private String type;
        private String url;
    }

    @Setter
    @Getter
    public static class Target {
        private String id;
        private String url;
    }

    @Setter
    @Getter
    public static class Media {
        private Image image;
        private String source;
    }

    @Setter
    @Getter
    public static class Image {
        private int width;
        private int height;
        private String src;
    }

}