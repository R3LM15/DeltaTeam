package edu.epn.iccd632.xp;

import java.util.Map;

/** HU-02. PUNTO DE TRABAJO PARA LA PAREJA 2. */
final class CancellationPolicy {
    boolean cancel(Map<String, String> ownerBySlot, String studentId, String slot) {
        // TODO HU-02: solo el titular puede liberar una reserva existente.
        return false;
    }
}
