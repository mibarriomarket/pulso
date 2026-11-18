# Pulso — contexto para Claude Code

App Android nativa (Kotlin) de Yoshi (Guadalajara). Ciclos fijos de 25 min de enfoque + 5 min de ejercicio, con avisos de voz (TTS) y notificaciones que corren en segundo plano, incluso con pantalla apagada o en llamada.

## Sobre el usuario
- Habla español; responder siempre en español, directo e informal.
- No técnico: solo Windows y navegador, sin Android Studio ni git en su máquina. Hasta ahora compilaba subiendo archivos por la web de GitHub y bajando el APK de GitHub Actions. Con Claude Code se pueden hacer commits y push directo.
- Repo: github.com/mibarriomarket/pulso (rama main, cuenta del usuario). El repo viejo senorarisuena-glitch/pulso ya no se usa.

## Build
- Workflow `.github/workflows/main.yml` (Temurin 17, Gradle 8.7 vía setup-gradle, `gradle assembleDebug --no-daemon`, artifact `pulso-apk` = app/build/outputs/apk/debug/app-debug.apk). Se dispara con push.
- AGP 8.5.2, Kotlin 1.9.24, compileSdk/targetSdk 34, minSdk 26, applicationId com.yoshi.pulso.
- SUBIR `versionCode`/`versionName` en app/build.gradle.kts en cada release (ahora 11 / "1.10"), si no Android no deja instalar encima.
- El APK es debug-signed. Cambiar a una firma release obliga a los testers a desinstalar y pierden datos locales. Antes de Google Play hay que definir keystore propia.
- Nunca se pudo compilar en local en sesiones previas: verificar con CI tras cada cambio.

## Arquitectura (app/src/main/java/com/yoshi/pulso)
- `Config.kt`: singleton sobre SharedPreferences ("pulso"). Perfiles múltiples; ajustes por perfil con clave `perfilId + "__" + clave`. Data classes: Ejercicio(nombre, seg, explicacion), Bloque, Frase(id, texto, audio?), Perfil, ListaGuardada. Bloques, frases, "Mis listas", repeticiones diarias, voz elegida (`vozElegida`/`guardarVoz`). Ciclo fijo: MINUTOS_ENFOQUE = 25.
- `PulsoService.kt`: Foreground Service (START_STICKY, WakeLock parcial, TextToSpeech con USAGE_ASSISTANCE_NAVIGATION_GUIDANCE para sonar sobre llamadas/otro audio, MediaPlayer para audios propios). Máquina de fases "enfoque"/"mover". Bloque de movimiento = 5 ejercicios x (45 s trabajo + 15 s descanso) = 300 s exactos; en el descanso dice el siguiente ejercicio y su explicación. Frases cada N min (defecto 4). Cuenta regresiva final del enfoque: de 5 en 5 y al final 3, 2, 1. `digitosSueltos()` lee números dígito por dígito. Dos canales de notificación: estado (persistente) y aviso (deslizable, con acciones).
- `MainActivity.kt`: UI programática sobre activity_main.xml: perfiles, avisos, voz (lista de voces es-* con latinas primero), bloques editables ("nombre | segundos | cómo se hace"), frases con audio propio, paquetes y rutinas con lista agrupada (`mostrarListaBonita`), Mis listas, repeticiones, exención de batería.
- `Plantillas.kt`: 10 rutinas listas, 7 paquetes de frases (Mis frases, Antinarcisismo, Jacobo Grinberg, Grabovoi combo de ventas, Vendedor tiburón, Mentalidad estoica y la secuencia Tracy 1-2-3: Programa tu mente → Tareas del día → Ventas; las frases suenan en orden), categorías (`categoriaDe`) y biblioteca `explicaciones` de ~88 ejercicios.

## Decisiones a respetar
- 25/5 fijo; lo editable: ejercicios, frases, intervalo de frases, descanso.
- Grabovoi: los grupos con pausa de respiración se escriben con puntos ("419. 488. 71."); el resto va seguido.
- Frases del paquete Antinarcisismo: 3 fueron suavizadas a propósito para evitar etiquetas de identidad (el usuario lo aceptó). No cambiar sin hablarlo.
- No volver a proponer vender paquetes de voz de terceros (descartado).
- Monetización: Google Play al final (freemium); por ahora APK directo a 2–3 personas de confianza. Los anuncios no encajan (poco tiempo en pantalla).

## Pendiente / ideas
- Comprobar en teléfono real: voz sobre llamadas, cuántas voces latinas aparecen, que la explicación de ejercicios no se corte con descansos cortos.
- Firma release + Google Play (ficha, política de privacidad, permisos de servicio en primer plano).
