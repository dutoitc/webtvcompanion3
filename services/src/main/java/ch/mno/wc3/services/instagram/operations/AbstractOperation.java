package ch.mno.wc3.services.instagram.operations;

import ch.mno.wc3.services.instagram.InstagramServiceImpl;

public abstract class AbstractOperation<E> {

    protected InstagramServiceImpl service;

    protected AbstractOperation(InstagramServiceImpl service) {
        this.service = service;
    }

    public abstract E execute();


}
