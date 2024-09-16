package ch.mno.wc3.services.youtube.data;

import lombok.Data;

@Data
public class YTVideoStats {

    private int viewCount;
    private int likeCount;
    private int dislikeCount;
    private int favoriteCount;
    private int commentCount;

}