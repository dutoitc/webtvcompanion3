package ch.mno.wc3.services.facebook.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FBVideoInsights {

    private List<Datum> data = new ArrayList<>();


    public class Datum{
        private ArrayList<Item> items;
    }

    public static class Item{
        private String name;
        private List<Value> values;
    }

    public static class Value {
        private String value;
    }

}
