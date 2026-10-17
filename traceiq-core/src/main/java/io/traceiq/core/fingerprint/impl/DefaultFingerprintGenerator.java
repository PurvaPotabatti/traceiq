package io.traceiq.core.fingerprint.impl;

import io.traceiq.core.fingerprint.FingerprintGenerator;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class DefaultFingerprintGenerator implements FingerprintGenerator {

    @Override
    public String generate(String exceptionType, String stackTrace) {

        Objects.requireNonNull(
                exceptionType,
                "exceptionType must not be null");

        String normalizedExceptionType =
                exceptionType.trim();

        if (normalizedExceptionType.isEmpty()) {
            throw new IllegalArgumentException(
                    "exceptionType must not be empty");
        }

        String normalizedStackTrace =
                normalizeStackTrace(stackTrace);

        String input =
                normalizedExceptionType
                        + "\n"
                        + normalizedStackTrace;

        return sha256(input);
    }

    private String normalizeStackTrace(String stackTrace) {

        if (stackTrace == null || stackTrace.isBlank()) {
            return "";
        }

        return stackTrace
                .replace("\r\n", "\n")
                .replace('\r', '\n')
                .trim();
    }

    private String sha256(String input) {

        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash =
                    digest.digest(
                            input.getBytes(StandardCharsets.UTF_8));

            StringBuilder result =
                    new StringBuilder(hash.length * 2);

            for (byte b : hash) {
                result.append(
                        String.format("%02x", b));
            }

            return result.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "SHA-256 algorithm is not available",
                    e);
        }
    }
}