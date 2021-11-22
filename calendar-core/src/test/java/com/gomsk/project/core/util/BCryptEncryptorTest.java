package com.gomsk.project.core.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BCryptEncryptorTest {

    @Test
    void test(){
        final String origin = "password";
        final Encrypter encrypter = new BCryptEncryptor();
        final String hash = encrypter.encrypt(origin);

        assertTrue(encrypter.isMatch(origin, hash));

        final String origin2 = "passworddd";
        assertFalse(encrypter.isMatch(origin2, hash));

    }
}
