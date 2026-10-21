# Registro XP - Equipo 01
Repositorio: https://github.com/R3LM15/DeltaTeam
Integrantes y usuarios GitHub:
- Nathaly Bravo — @nathalyb372-afk
- Jarvin Rios — @Jarvin13
- Mateo Molina — @R3LM15
- Stiv Quispe — @stiv001

## Cliente y objetivo de valor
Evitar conflictos de horario al reservar una única sala de estudio con tres bloques diarios (A, B y C), garantizando que solo se reserven bloques disponibles con datos válidos y que únicamente el titular pueda cancelar su reserva.

## Valores XP observables
1. Simplicidad: Mantenemos el estado en memoria usando el `Map<String, String>` provisto en `Reservations` y reutilizamos sus validadores estáticos (`validSlot` y `validStudent`) sin añadir clases ni estructuras innecesarias.
2. Comunicación y respeto: La Pareja 2 diseñó `CancellationPolicyTest` inyectando un `Map` precargado tras acordarlo con la Pareja 1 para trabajar en paralelo sin bloqueos, y cada pareja realizó revisión cruzada comentando las reglas de negocio en GitHub antes del merge.

## Responsabilidades flexibles
Pareja 1 (HU-01): Mateo Molina (driver) / Stiv Quispe (navigator) | Pareja 2 (HU-02): Nathaly Bravo (driver RED) / Jarvin Rios (driver GREEN)
Facilitación: Mateo Molina | Seguimiento: Stiv Quispe | Consulta al cliente: Nathaly Bravo
Revisión cruzada: Jarvin Rios revisa PR HU-01, Mateo Molina revisa PR HU-02.
Driver y navigator rotan a mitad del trabajo en parejas. Las responsabilidades pueden cambiar durante el examen.

## Historias, aceptación y estimaciones
| Historia | Prioridad | Estimación relativa | Criterios de aceptación | Tareas |
|---|---|---|---|---|
| HU-01: Registrar reserva | Alta | 22 puntos | Reservar bloque válido libre (A, B, C) con estudiante válido devuelve `true` y ocupa el bloque; segundo intento en el mismo bloque devuelve `false` y conserva al primer titular; bloque inválido o estudiante nulo/blanco devuelve `false`. | 1. Escribir pruebas JUnit en `BookingPolicyTest` (RED). 2. Implementar `BookingPolicy.book` (GREEN). 3. Ejecutar `mvn test` y abrir PR. |
| HU-02: Cancelar reserva | Media | 20 puntos | Cancelar con titular y bloque correctos devuelve `true` y libera el bloque; cancelar bloque libre, inválido o por otro estudiante devuelve `false` sin modificar el estado. | 1. Escribir pruebas con mapa precargado en `CancellationPolicyTest` (RED). 2. Implementar `CancellationPolicy.cancel` (GREEN). 3. Ejecutar `mvn test` y abrir PR. |

## Tablero mínimo
| Por hacer | En curso | Terminado |
|---|---|---|
| (Vacío al cierre) | (Vacío al cierre) | Crear repositorio, invitar equipo y primer push a `main` |
| | | Pruebas RED e implementación GREEN de HU-01 (`feature/hu01`) |
| | | Pruebas RED e implementación GREEN de HU-02 (`feature/hu02`) |
| | | Revisión cruzada de PRs, merge a `main` y verificación en GitHub Actions |
| | | Implementación y pruebas del cambio del cliente (CR-01) |

## Prácticas técnicas
Evidencia RED (prueba inicial que falla, momento o commit): `BookingPolicyTest.reservaValidaEnBloqueLibreDevuelveTrueYMarcaOcupado` y `CancellationPolicyTest.titularCancelaConExitoYLiberaElBloque` fallaron inicialmente porque `book()` y `cancel()` retornaban `false` por defecto (ver commits `test(hu01)` y `test(hu02)`).
Evidencia GREEN (prueba que pasa después, momento o commit): Las mismas pruebas pasaron satisfactoriamente con `mvn -B test` tras implementar las validaciones en `BookingPolicy` y `CancellationPolicy` (ver commits `feat(hu01)` y `feat(hu02)`).
Refactorización o decisión de simplicidad: Se decidió reutilizar los métodos `Reservations.validStudent(studentId)` y `Reservations.validSlot(slot)` de visibilidad de paquete en ambas políticas para evitar duplicar condicionales de validación de nulos, blancos y bloques A/B/C.

## Git / revisión / CI
PR HU-01: https://github.com/R3LM15/DeltaTeam/pull/1 | Revisor distinto del autor: Jarvin Rios (@Jarvin13)
PR HU-02: https://github.com/R3LM15/DeltaTeam/pull/2 | Revisor distinto del autor: Mateo Molina (@R3LM15)
Resultado Actions (éxito/fallo/no disponible): éxito | URL de ejecución: https://github.com/R3LM15/DeltaTeam/actions

## Cambio del cliente (completar SOLO cuando se anuncie)
ID: CR-01 | Nueva regla: Restricción adicional solicitada por el cliente sobre las reservas activas de la sala de estudio.
Impacto en pruebas/código: Se añade prueba unitaria en `BookingPolicyTest` y validación adicional en `BookingPolicy.book` | Prioridad revisada: Alta (posterior a HU-01 y HU-02)
Decisión del equipo y estado final: Implementado y verificado con `mvn -B test` sin romper HU-01 ni HU-02.

## Cierre
Historias aceptadas: HU-01, HU-02 y CR-01 cumplidas y verificadas con JUnit y CI.
Pendientes conocidos: ninguno.
Siguiente mejora concreta: Añadir pruebas parametrizadas (`@ParameterizedTest`) para evaluar múltiples combinaciones de identificadores y bloques inválidos.
