package com.gomsk.project.core.util;

public interface Encrypter {
    String encrypt(String origin);
    boolean isMatch(String origin, String hashed);
}
