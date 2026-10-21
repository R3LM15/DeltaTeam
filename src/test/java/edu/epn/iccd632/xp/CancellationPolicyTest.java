package edu.epn.iccd632.xp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CancellationPolicyTest {

    private Map<String, String> ownerBySlot;
    private CancellationPolicy policy;

    @BeforeEach
    void prepararMapaConReserva() {
        ownerBySlot = new HashMap<>();
        ownerBySlot.put("A", "E01");
        policy = new CancellationPolicy();
    }

    @Test
    void titularCancelaSuReservaYElBloqueQuedaLibre() {
        assertTrue(policy.cancel(ownerBySlot, "E01", "A"));
        assertFalse(ownerBySlot.containsKey("A"));
    }

    @Test
    void cancelarBloqueLibreDevuelveFalseYNoModificaEstado() {
        assertFalse(policy.cancel(ownerBySlot, "E01", "B"));
        assertEquals(Map.of("A", "E01"), ownerBySlot);
    }

    @Test
    void otroEstudianteNoPuedeCancelarYNoModificaEstado() {
        assertFalse(policy.cancel(ownerBySlot, "E02", "A"));
        assertEquals("E01", ownerBySlot.get("A"));
    }

    @Test
    void datosInvalidosDevuelvenFalseYNoModificanEstado() {
        assertFalse(policy.cancel(ownerBySlot, null, "A"));
        assertFalse(policy.cancel(ownerBySlot, "   ", "A"));
        assertFalse(policy.cancel(ownerBySlot, "E01", "X"));
        assertFalse(policy.cancel(ownerBySlot, "E01", null));
        assertEquals(Map.of("A", "E01"), ownerBySlot);
    }
}