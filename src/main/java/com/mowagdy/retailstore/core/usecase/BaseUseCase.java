package com.mowagdy.retailstore.core.usecase;

abstract class BaseUseCase<ResponseT> {

    abstract void validate();

    abstract ResponseT process();

    public ResponseT execute() {
        validate();
        return process();
    }
}
