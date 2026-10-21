package edu.epn.iccd632.xp;

import java.util.Map;

/** HU-01. PUNTO DE TRABAJO PARA LA PAREJA 1. */
final class BookingPolicy {
    boolean book(Map<String, String> ownerBySlot, String studentId, String slot) {
        if (!Reservations.validSlot(slot) || !Reservations.validStudent(studentId)
                || ownerBySlot.containsKey(slot)) {
            return false;
        }
        ownerBySlot.put(slot, studentId);
        return true;
    }
}