package io.traceiq.core.fingerprint;

public interface FingerprintGenerator {

    String generate(String exceptionType, String stackTrace);
}