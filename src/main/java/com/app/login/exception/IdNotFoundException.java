package com.app.login.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class IdNotFoundException extends Exception {

        public IdNotFoundException(String message) {
            super(message);
        }

}
