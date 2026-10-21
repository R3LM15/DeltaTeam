package edu.epn.iccd632.xp;

import java.util.HashMap;
import java.util.Map;

/** Contrato público: NO cambiar nombres ni firmas durante la evaluación. */
public final class Reservations {
    // Estado compartido en memoria. No exponer directamente a quien usa la clase.
    private final Map<String, String> ownerBySlot = new HashMap<>();
    private final BookingPolicy bookingPolicy = new BookingPolicy();
    private final CancellationPolicy cancellationPolicy = new CancellationPolicy();

    public boolean book(String studentId, String slot) {
        return bookingPolicy.book(ownerBySlot, studentId, slot);
    }

    public boolean cancel(String studentId, String slot) {
        return cancellationPolicy.cancel(ownerBySlot, studentId, slot);
    }

    public boolean isAvailable(String slot) {
        return validSlot(slot) && !ownerBySlot.containsKey(slot);
    }

    static boolean validSlot(String slot) {
        return "A".equals(slot) || "B".equals(slot) || "C".equals(slot);
    }

    static boolean validStudent(String studentId) {
        return studentId != null && !studentId.isBlank();
    }
}
