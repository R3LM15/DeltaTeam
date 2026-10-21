# Registro XP - Equipo DeltaTeam
Repositorio: https://github.com/R3LM15/DeltaTeam

Integrantes y usuarios GitHub:
- Mateo Moisés Molina Estrella — @R3LM15
- Jarvin Antonio Ríos Mina — @Jarvin13
- Nathaly Paola Bravo Jami — @nathalyb372-afk
- Stiv Stalyn Quishpe Cabezas — @stiv001

## Cliente y objetivo de valor
Evitar conflictos al reservar una única sala de estudio con bloques A, B y C. Entregar registro y cancelación de reservas con controles de disponibilidad y titularidad.

## Valores XP observables
1. Simplicidad: mantenemos el estado en memoria con el `Map` de `Reservations` y reutilizamos `validSlot` y `validStudent`; no añadimos interfaz, base de datos ni clases nuevas.
2. Retroalimentación: cada historia empezó con una prueba JUnit que falla (RED) y luego el código mínimo para que pase (GREEN), comprobado con `mvn test`.

## Responsabilidades flexibles
Pareja 1 (HU-01 y CR-01): Mateo Molina / Stiv Quishpe | Pareja 2 (HU-02): Nathaly Bravo / Jarvin Ríos
Facilitación: [Nombre] | Seguimiento: [Nombre] | Consulta al cliente: [Nombre]
Rotación conductor/navegador: [registrar si se realizó]

## Historias, aceptación y estimaciones
| Historia | Prioridad | Estimación relativa | Criterios de aceptación | Tareas |
|---|---|---|---|---|
| HU-01: Registrar reserva | Alta | 3 puntos | Reservar un bloque libre devuelve true y lo ocupa; reservar uno ocupado devuelve false y conserva al primer titular; bloque inválido o estudiante nulo/blanco devuelve false | Pruebas en BookingPolicyTest (RED); implementar BookingPolicy (GREEN); ejecutar pruebas |
| HU-02: Cancelar reserva | Baja | 2 puntos | El titular cancela con éxito y libera el bloque; otra persona o bloque libre devuelve false sin alterar el estado | Pruebas en CancellationPolicyTest con mapa precargado (RED); implementar CancellationPolicy (GREEN); comprobar estado |
| CR-01: Una reserva por estudiante | Alta | 1 punto | Si E01 tiene A, reservar B devuelve false; E02 sí puede reservar B libre; si E01 libera A puede reservar B; se conservan las reglas anteriores | Prueba nueva en BookingPolicyTest (RED); agregar condición en BookingPolicy (GREEN); ejecutar todas las pruebas |

## Tablero mínimo
| Por hacer | En curso | Terminado |
|---|---|---|
| Merge a main y revisar Actions | PR de HU-01 y HU-02 con revisión cruzada | Crear repositorio y subir proyecto base |
| | | HU-01 RED y GREEN |
| | | HU-02 RED y GREEN |
| | | CR-01 RED y GREEN |

## Prácticas técnicas
Evidencia RED (prueba inicial que falla, momento o commit): BookingPolicyTest.reservaValidaDevuelveTrueYRegistraAlTitular falló porque `book` devolvía siempre false (commit `c17f0b9`). CancellationPolicyTest.titularCancelaSuReservaYElBloqueQuedaLibre falló porque `cancel` devolvía siempre false (commit `fac821f`). BookingPolicyTest.estudianteNoPuedeReservarDosBloques falló antes de CR-01 (commit `069c35d`).
Evidencia GREEN (prueba que pasa después, momento o commit): las mismas pruebas pasan con `mvn test` (HU-01 `d31b2e6`, HU-02 `def371a` y `7ec409b`, CR-01 `55ed726`).
Refactorización o decisión de simplicidad: reutilizar `Reservations.validSlot` y `Reservations.validStudent` en ambas políticas en vez de repetir validaciones; CR-01 se resolvió con `ownerBySlot.containsValue(studentId)`, sin estructuras nuevas.

## Git / revisión / CI
PR HU-01: [URL real] | Revisor distinto del autor: [nombre real]
PR HU-02: [URL real] | Revisor distinto del autor: [nombre real]
Resultado Actions (éxito/fallo/no disponible): éxito | URL de ejecución: https://github.com/R3LM15/DeltaTeam/actions/runs/39680303973

## Cambio del cliente
ID: CR-01 | Nueva regla: un mismo estudiante no puede tener más de una reserva activa en los bloques A, B o C.
Impacto en pruebas/código: nuevas pruebas en BookingPolicyTest y una condición más en BookingPolicy; no cambia la API de Reservations | Prioridad revisada: Alta, se trabaja sobre la rama de HU-01 sin detener HU-02.
Decisión del equipo y estado final: implementado y verificado con `mvn test`. "Tras cancelar puede reservar otro" se probó liberando el mapa para no depender de la rama de HU-02; al unir ambas ramas pasan las 11 pruebas.

## Cierre
Historias aceptadas: [HU-01 y CR-01; HU-02 solo si se termina y verifica].
Pendientes conocidos: [describir lo que quede sin terminar].
Siguiente mejora concreta: probar CR-01 junto con la cancelación real de HU-02.
