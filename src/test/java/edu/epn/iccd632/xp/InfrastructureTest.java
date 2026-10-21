package edu.epn.iccd632.xp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/** Prueba de infraestructura provista. NO se cuenta como prueba de negocio. */
class InfrastructureTest {
    @Test void recognizeDefinedSlots() {
        Reservations r = new Reservations();
        assertTrue(r.isAvailable("A"));
        assertTrue(r.isAvailable("B"));
        assertFalse(r.isAvailable("X"));
    }
}
