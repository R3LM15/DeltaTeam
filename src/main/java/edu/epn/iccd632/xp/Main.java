package edu.epn.iccd632.xp;

/** Demostración sencilla; el comportamiento real se verifica con JUnit. */
public final class Main {
    public static void main(String[] args) {
        Reservations r = new Reservations();
        System.out.println("A libre al inicio: " + r.isAvailable("A"));
        System.out.println("Reservar A para E01: " + r.book("E01", "A"));
        System.out.println("A libre despues: " + r.isAvailable("A"));
        System.out.println("Cancelar A para E01: " + r.cancel("E01", "A"));
    }
}
