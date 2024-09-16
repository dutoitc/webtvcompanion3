package ch.mno.wc3.services.sandbox;

import ch.mno.wc3.services.facebook.FacebookServiceImpl;
import ch.mno.wc3.services.facebook.config.FacebookConfig;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

public class FacebookServiceMain {


    public static void main(String[] args) throws IOException, InterruptedException {
        var config = FacebookConfig.builder()
                .appId("webtvnordvaudois")
                .tokenApp("EABJ2om8HpxgBOwZBKaNZCkZBZCsWgWxO5Xs171kLsqIIDNGcjEPscuuODoxgZBZCbybDFTZBFpxYZCn7tsmB0w2l3TPgTaacD2imU9UDAXBi4nGJAq8JGcDCusOIQl5SR1XdW6bxz7PmB48dHHvzaZAYmn3aeqxEJPuWiWtROl3L3OFxZAMj0X0gZDZD")
                .tokenPage("EABJ2om8HpxgBOwqiiM4vDZBQZCEupfhm6Euu2qRdOPdTW0zex3Vz3jHVlEa2Cf4DjqJ3jFP8IZBgZBCLktrCwpKvgciDpK9LyayJKEZAm8BZCefrjQLtCEFAao5HdOCQqlvMllADb0orms6FISGZAXLZAZCZATKsYRtWjCNwkDPeOeIUzAkf911lzqrleg")
                .build();

        var service = new FacebookServiceImpl(WebClient.builder().build(), config);
        //service.getVideos().dump();
        //System.out.println(service.getVideoViews("1669578873436310"));
        //service.getVideoInsights("1669578873436310");
        //service.get

//        new FacebookServiceImpl().publishedPosts();
//        service.getVideos(false).dump();

//        service.getVideos(true).getData().forEach(d -> {
//            float videoConsumptionRateOperation = 0.0f;
//            try {
//                videoConsumptionRateOperation = service.getVideoConsumptionRateOperation(d.getId());
//            } catch (Exception e) {
//                System.err.println("Cannot get video consumption rate for video id=" + d.getId() + ": " + e.getMessage());
//            }
//            var description = "?";
//            if (d.getDescription() != null) {
//                description = d.getDescription().split("\n")[0];
//            }
//
//            System.out.println(d.getId() + ";" + videoConsumptionRateOperation + ";" + description);
//        });
//
//
//        System.out.println("Consumption rate mines 1: " + service.getVideoConsumptionRateOperation("2210231625848346"));

//        System.out.println("Vues RB-val: " + service.getVideoViews("1669578873436310"));
//        System.out.println("Vues mines 1: " + service.getVideoViews("2210231625848346"));
//        System.out.println("Vues mines 2: " + service.getVideoViews("1244454269663839"));
//        System.out.println("Vues herboriste: " + service.getVideoViews("805723080578052"));
//        if (1 < 2) System.exit(1);

        // TODO: test
        //service.publishVideo("webtvnordvaudois", new File("test.mp4"));

//        service.getScheduledPosts().getData().forEach(d -> {
//            System.out.printf("---------------------------%nScheduled #%s %s @%s%n%s%n", d.getId(), d.getCreated_time(), d.getScheduled_publish_timeAsObject(), d.getMessage());
//            try {
//                var att = service.getPostAttachment(d.getId());
//                att.getData().forEach(data -> {
//                    System.out.printf("- Attachment %s(%s): %s%n", data.getTarget().getId(), data.getType(), data.getTitle());
//                });
//            } catch (RuntimeException e) {
//                e.printStackTrace();
//            }
//        });
    }


}
