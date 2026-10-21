# ICCD632 – Evaluación XP (120 minutos)

**Proyecto genérico Java 17/Maven**, compatible con IntelliJ IDEA, VS Code y NetBeans. No contiene solución de HU-01, HU-02 ni CR-01. **No modificar** las firmas públicas de `Reservations`.

## Abrir y compilar
1. Extraer **todos** los archivos de este ZIP (incluye carpeta oculta `.github`).
2. Abrir la carpeta donde está `pom.xml` como proyecto Maven.
3. En una terminal ejecutar `java -version`, `mvn -version` y `mvn test` cuando Maven esté instalado. En el IDE, ejecutar las pruebas de la misma manera.
4. `InfrastructureTest` comprueba solamente la infraestructura y no cuenta como prueba de negocio.

## Organización
- Dos parejas: pareja 1 desarrolla HU-01 en `BookingPolicy.java`; pareja 2 desarrolla HU-02 en `CancellationPolicy.java`.
- Las parejas añaden pruebas en archivos distintos para minimizar conflictos.
- `Reservations.java` contiene el estado compartido en memoria; normalmente no necesitarán editarlo.
- Mantener `XP_REGISTRO.md` ligero, con historias, tablero, evidencia de PR, CI y cambio.

## GitHub (configuración DURANTE el examen)
1. Una persona crea repositorio **vacío**, sin README, sin `.gitignore` y sin licencia, y agrega a las otras tres como colaboradores.
2. Desde carpeta raíz:
   ```bash
   git init
   git branch -M main
   git add .
   git commit -m "chore: proyecto base XP"
   git remote add origin https://github.com/USUARIO/NOMBRE_REPO.git
   git push -u origin main
   ```
3. Crear ramas separadas `feature/hu01` y `feature/hu02` partiendo de `main`, subirlas y crear PR hacia `main`.
4. Revisar PR cruzados: un estudiante distinto del autor verifica el cambio. **No se exige protección automática de ramas.**
5. Realizar merge a `main`. Ver pestaña **Actions** o sección de comprobaciones del PR y anotar resultado.

## CI
`.github/workflows/ci.yml` ya está completo. No se evalúa escribir YAML. El workflow se activa al realizar push a `main` o abrir/actualizar un PR a `main`. Compila y ejecuta JUnit con Maven. Si GitHub falla por infraestructura, registrar incidencia y probar `mvn test` local.

## Entregables
URL del repo, historial de PR, main integrada, `XP_REGISTRO.md`, pruebas JUnit y respuestas individuales entregadas por el medio indicado por el docente. Todas las tareas deben terminar dentro de los 120 minutos.
