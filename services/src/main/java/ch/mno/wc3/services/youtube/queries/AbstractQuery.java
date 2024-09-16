package ch.mno.wc3.services.youtube.queries;

import com.google.api.services.youtube.YouTube;

import java.math.BigInteger;

public abstract class AbstractQuery<E> {

    protected YouTube youtubeService;

    public AbstractQuery(YouTube youtubeService) {
        this.youtubeService = youtubeService;
    }

    public abstract E execute();


    protected int safeInt(BigInteger c) {
        if (c==null) return 0;
        return c.intValue();
    }

}
