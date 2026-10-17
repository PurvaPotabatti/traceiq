package io.traceiq.core.fingerprint.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultFingerprintGeneratorTest {

    private final DefaultFingerprintGenerator generator =
            new DefaultFingerprintGenerator();

    @Test
    void sameInputShouldProduceSameFingerprint() {

        String exceptionType = "java.lang.NullPointerException";

        String stackTrace =
                "at io.traceiq.OrderService.processOrder(OrderService.java:82)\n"
                        + "at io.traceiq.OrderController.createOrder(OrderController.java:45)";

        String firstFingerprint =
                generator.generate(exceptionType, stackTrace);

        String secondFingerprint =
                generator.generate(exceptionType, stackTrace);

        assertEquals(firstFingerprint, secondFingerprint);
    }

    @Test
    void differentStackTraceShouldProduceDifferentFingerprint() {

        String exceptionType = "java.lang.NullPointerException";

        String firstStackTrace =
                "at io.traceiq.OrderService.processOrder(OrderService.java:82)";

        String secondStackTrace =
                "at io.traceiq.PaymentService.processPayment(PaymentService.java:91)";

        String firstFingerprint =
                generator.generate(exceptionType, firstStackTrace);

        String secondFingerprint =
                generator.generate(exceptionType, secondStackTrace);

        assertNotEquals(firstFingerprint, secondFingerprint);
    }

    @Test
    void differentExceptionTypeShouldProduceDifferentFingerprint() {

        String stackTrace =
                "at io.traceiq.OrderService.processOrder(OrderService.java:82)";

        String firstFingerprint =
                generator.generate(
                        "java.lang.NullPointerException",
                        stackTrace);

        String secondFingerprint =
                generator.generate(
                        "java.lang.IllegalArgumentException",
                        stackTrace);

        assertNotEquals(firstFingerprint, secondFingerprint);
    }

    @Test
    void fingerprintShouldNotBeBlank() {

        String fingerprint =
                generator.generate(
                        "java.lang.NullPointerException",
                        "at io.traceiq.OrderService.processOrder(OrderService.java:82)");

        assertNotNull(fingerprint);
        assertFalse(fingerprint.isBlank());
    }
}