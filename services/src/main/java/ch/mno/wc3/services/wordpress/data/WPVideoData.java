package ch.mno.wc3.services.wordpress.data;

import lombok.Data;

import java.util.regex.Pattern;

@Data
public class WPVideoData {

    private int id;
    private String date;
    private String status; // published
    private String link;
    private Rendered title;
    private Rendered content;
    private Rendered excerpt;

    public int getViews() {
        var m = Pattern.compile("(vues|views).*?:\\s*(?<vues>\\d+)").matcher(content.getRendered().toLowerCase());
        if (m.find()) {
            return Integer.parseInt(m.group("vues"));
        }
        m = Pattern.compile("(visits|visites).*?:\\s*(?<vues>\\d+)").matcher(content.getRendered().toLowerCase());
        if (m.find()) {
            return Integer.parseInt(m.group("vues"));
        }
        return 0;
    }

    @Data
    public static class Rendered {
        private String rendered;
    }

}
