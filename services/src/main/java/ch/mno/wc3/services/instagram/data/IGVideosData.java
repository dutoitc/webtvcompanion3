package ch.mno.wc3.services.instagram.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Data
public class IGVideosData {


    public ArrayList<Datum> data = new ArrayList<>();
    public Paging paging;


    @Setter
    @Getter
    public static class Value{
        public int value;
    }

    @Setter
    @Getter
    public static class Datum{
        public String id;
        public String media_type;
        public Owner owner;
        public Date timestamp;
        public String caption;
        public int like_count;
        public String media_product_type;
        public String permalink;
        public String thumbnail_url;
        public String media_url;
        public Insights insights;
        public String name;
        public String period;
        public ArrayList<Value> values;
        public String title;
        public String description;

        @Override
        public String toString() {
            return "Datum{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        public int getViews() {
            if (insights==null || insights.getData()==null) return 0;
            var lst = insights.getData().stream()
                    .filter(d->d.getName()!=null)
                    .filter(d->d.getName().equals("video_views") || d.getName().equals("plays"))
                    .toList();
            if (!lst.isEmpty()) {
                return lst.getFirst().getValues().getFirst().getValue();
            }
            return 0;
        }
    }

    @Setter
    @Getter
    public static class Insights{
        public ArrayList<Datum> data;
    }

    @Setter
    @Getter
    public static class Owner{
        public String id;
    }

    @Setter
    @Getter
    public static class Paging{
        public Cursors cursors;
        public String next;
        public String previous;
    }

    @Setter
    @Getter
    public static  class Cursors{
        public String before;
        public String after;
    }

}