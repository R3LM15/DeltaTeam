package edu.epn.iccd632.xp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/** HU-01: como estudiante, quiero reservar un bloque libre para utilizar la sala. */
class BookingPolicyTest {
    private final Map<String, String> ownerBySlot = new HashMap<>();
    private final BookingPolicy policy = new BookingPolicy();

    @Test void reservaValidaDevuelveTrueYRegistraAlTitular() {
        assertTrue(policy.book(ownerBySlot, "E01", "A"));
        assertEquals("E01", ownerBySlot.get("A"));
    }

    @Test void bloqueOcupadoDevuelveFalseYConservaAlPrimerTitular() {
        policy.book(ownerBySlot, "E01", "A");
        assertFalse(policy.book(ownerBySlot, "E02", "A"));
        assertEquals("E01", ownerBySlot.get("A"));
    }

    @Test void bloqueInvalidoDevuelveFalse() {
        assertFalse(policy.book(ownerBySlot, "E01", "D"));
        assertFalse(policy.book(ownerBySlot, "E01", "a"));
        assertFalse(policy.book(ownerBySlot, "E01", null));
        assertTrue(ownerBySlot.isEmpty());
    }

    @Test void estudianteNuloOBlancoDevuelveFalse() {
        assertFalse(policy.book(ownerBySlot, null, "A"));
        assertFalse(policy.book(ownerBySlot, "", "A"));
        assertFalse(policy.book(ownerBySlot, "   ", "A"));
        assertTrue(ownerBySlot.isEmpty());
    }

    @Test void despuesDeReservarElBloqueYaNoEstaLibre() {
        Reservations r = new Reservations();
        assertTrue(r.book("E01", "B"));
        assertFalse(r.isAvailable("B"));
    }

    // Solicitud de cambio CR-01
    @Test
    void estudianteNoPuedeReservarDosBloques() {
        assertTrue(policy.book(ownerBySlot, "E01", "A"));

        assertFalse(policy.book(ownerBySlot, "E01", "B"));
    }

}
