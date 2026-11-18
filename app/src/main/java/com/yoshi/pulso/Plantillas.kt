package com.yoshi.pulso

/**
 * Plantillas listas. Una rutina llena los tres bloques de golpe.
 * Un paquete de frases reemplaza o agrega a la lista de frases del perfil.
 */
object Plantillas {

    data class Rutina(val nombre: String, val descripcion: String, val bloques: List<Bloque>)
    data class Paquete(val nombre: String, val descripcion: String, val frases: List<String>)

    /** Para ordenar y agrupar la lista de paquetes en pantalla. */
    val ordenCategorias = listOf("Tuyas", "Brian Tracy", "Ventas y negocio", "Mentalidad", "Espiritual", "Personal")

    fun categoriaDe(nombrePaquete: String): String = when (nombrePaquete) {
        "Mis frases" -> "Tuyas"
        "Tracy 1 — Programa tu mente", "Tracy 2 — Tareas del día", "Tracy 3 — Ventas" -> "Brian Tracy"
        "Vendedor tiburón", "Grabovoi — combo de ventas" -> "Ventas y negocio"
        "Mentalidad estoica" -> "Mentalidad"
        "Jacobo Grinberg" -> "Espiritual"
        else -> "Personal"
    }

    /**
     * Cinco ejercicios por bloque, 45 segundos cada uno. Con 15 segundos
     * de descanso automático entre ellos, un bloque completo de cinco
     * ejercicios llena exactamente los cinco minutos (5 × 60 = 300).
     */
    private fun b(nombre: String, vararg ejs: String): Bloque =
        Bloque(nombre, ejs.map { Ejercicio(it, 45, explicacionDe(it)) })

    // ------------------------------------------------------------------ explicaciones

    /**
     * Cómo se hace cada ejercicio, en una frase corta que la app dice en
     * voz alta antes de que empiece (durante el descanso previo, o al
     * arrancar si es el primero del bloque). Se busca por nombre, sin
     * importar mayúsculas ni espacios de sobra. Si un ejercicio no está
     * aquí, la app simplemente dice su nombre, sin explicación.
     */
    private val explicaciones: Map<String, String> = mapOf(
        "abdominales con los pies" to "Acostado, pies apoyados y rodillas dobladas, sube el torso hacia las rodillas sin jalar el cuello.",
        "apertura de pecho en marco de puerta" to "Antebrazos en el marco de una puerta, da un paso al frente y deja que el pecho se abra.",
        "apertura de pecho en pared" to "De espaldas a una pared, abre los brazos a los lados y empuja el pecho hacia adelante.",
        "brincos altos" to "Desde sentadilla, salta lo más alto que puedas y aterriza suave doblando las rodillas.",
        "brincos de tijera" to "Salta abriendo brazos y piernas a la vez, y ciérralos al aterrizar.",
        "burpees" to "Baja a sentadilla, manos al piso, avienta los pies atrás en plancha, regresa y salta arriba.",
        "burpees a ritmo lento" to "El mismo burpee, pero controlado: sin prisa, cuidando cada paso del movimiento.",
        "colgarse o estirarse hacia arriba" to "Estira los brazos hacia arriba lo más que puedas, como si te fueras a colgar de algo.",
        "círculos de brazos sostenidos" to "Brazos extendidos a los lados, haz círculos pequeños y constantes sin bajarlos.",
        "círculos de cadera" to "De pie, manos en la cintura, gira la cadera en círculos amplios, mitad para cada lado.",
        "círculos de hombro" to "Brazos relajados, haz círculos con los hombros hacia atrás y luego hacia adelante.",
        "círculos de muñeca" to "Extiende los brazos y gira las muñecas en círculos, hacia un lado y luego al otro.",
        "desplante con rotación" to "Da un paso al frente en desplante y gira el torso hacia la pierna de enfrente.",
        "desplante estático por lado" to "Un pie muy adelante y otro atrás, bajas y subes sin cambiar de pierna.",
        "desplantes alternados" to "Paso al frente, bajas hasta que ambas rodillas hagan noventa grados, alternando pierna.",
        "desplantes caminando" to "Ve caminando hacia adelante alternando desplantes con cada paso.",
        "desplantes con pausa" to "Baja al desplante y aguanta un segundo abajo antes de subir.",
        "elevación de piernas" to "Acostado boca arriba, sube las piernas rectas hasta el techo y bájalas sin tocar el piso.",
        "elevación de talones" to "De pie, sube los talones despacio hasta pararte de puntas y baja controlado.",
        "elevación de talones parado" to "Sube de puntas y baja despacio, cuantas veces puedas.",
        "escaladores" to "En posición de plancha, lleva las rodillas al pecho alternando rápido, como si corrieras horizontal.",
        "escaladores continuos" to "Los mismos escaladores pero sin parar, manteniendo un ritmo parejo.",
        "estiramiento de cuello lento" to "Inclina la cabeza suave hacia un hombro, aguanta, y luego hacia el otro lado.",
        "estiramiento de cuádriceps de pie" to "De pie, jala un tobillo hacia el glúteo con la mano y sostén el equilibrio.",
        "estiramiento de espalda sentado" to "Sentado, inclínate hacia adelante con la espalda relajada, dejando que cuelgue el peso.",
        "estiramiento de isquiotibiales" to "Con una pierna extendida al frente, inclínate hacia esa pierna sin forzar la rodilla.",
        "estiramiento de muñecas y dedos" to "Extiende un brazo al frente y jala los dedos hacia atrás suavemente con la otra mano.",
        "estiramiento de psoas en desplante" to "En desplante bajo, empuja la cadera hacia adelante sintiendo el estiramiento en la pierna de atrás.",
        "estiramiento de cadera" to "En desplante bajo, empuja la cadera hacia adelante sintiendo el estiramiento en la pierna de atrás.",
        "estiramiento largo hacia el techo" to "Estira ambos brazos hacia arriba y alárgate como si quisieras tocar el techo.",
        "extensión de brazos al frente" to "Extiende los brazos al frente a la altura del pecho y sostenlos firmes.",
        "fondos de tríceps en silla" to "Manos en el borde de una silla, baja el cuerpo doblando los codos y sube.",
        "fondos de tríceps lentos" to "El mismo fondo de tríceps, pero bajando y subiendo despacio y con control.",
        "gato y vaca en cuatro puntos" to "En cuatro puntos, arquea la espalda hacia arriba y luego hunde el ombligo hacia el piso.",
        "giro ruso sentado" to "Sentado con el torso inclinado atrás, gira de un lado a otro tocando el piso con las manos.",
        "golpes al aire" to "Da golpes al aire alternando los brazos, como si estuvieras boxeando.",
        "hueco abdominal sostenido" to "Acostado boca arriba, despega ligeramente hombros y piernas del piso y aguanta la posición.",
        "lagartija parado de manos" to "Con los pies apoyados en la pared, baja la cabeza hacia el piso y empuja para subir.",
        "lagartijas abiertas" to "Manos más separadas que los hombros, baja el pecho al piso y empuja para subir.",
        "lagartijas con pausa abajo" to "Baja como en una lagartija normal y aguanta un segundo con el pecho cerca del piso antes de subir.",
        "lagartijas diamante" to "Junta los pulgares e índices formando un diamante bajo el pecho y haz la lagartija.",
        "lagartijas inclinadas en pared" to "Manos en la pared, cuerpo inclinado, baja el pecho hacia la pared y empuja para regresar.",
        "lagartijas lentas" to "Baja y sube controlando cada segundo del movimiento, sin dejarte caer.",
        "lagartijas normales" to "Manos a la altura de los hombros, baja el pecho casi al piso y empuja para subir.",
        "lagartijas" to "Manos a la altura de los hombros, baja el pecho casi al piso y empuja para subir.",
        "marcha en el lugar" to "Camina sin avanzar, subiendo bien las rodillas, a tu propio ritmo.",
        "patada de glúteo en cuatro puntos" to "En cuatro puntos, empuja un talón hacia el techo apretando el glúteo, y cambia de pierna.",
        "plancha alta" to "Apoyado en manos y puntas de pie, cuerpo recto como tabla, aguanta la posición.",
        "plancha alta con desplazamiento" to "Desde plancha alta, camina las manos hacia un lado y luego hacia el otro.",
        "plancha con elevación de pierna" to "En plancha, levanta una pierna recta sin mover la cadera, y cambia de lado.",
        "plancha con toque de hombro" to "En plancha alta, toca el hombro contrario con una mano sin mover la cadera, alternando.",
        "plancha firme" to "Apoyado en antebrazos y pies, cuerpo recto, aprieta abdomen y glúteo y aguanta.",
        "plancha frontal" to "Apoyado en antebrazos, cuerpo en línea recta de cabeza a talones, aguanta sin hundir la cadera.",
        "plancha lateral alternada" to "Plancha de lado apoyado en un antebrazo, y cambia de lado a la mitad del tiempo.",
        "plancha sostenida" to "Aguanta la posición de plancha lo más quieto y firme que puedas.",
        "plancha" to "Apoyado en antebrazos y pies, cuerpo recto, aguanta la posición sin hundir la cadera.",
        "postura del niño" to "De rodillas, siéntate sobre los talones y estira los brazos al frente, relajando la espalda.",
        "puente de glúteo" to "Acostado boca arriba, rodillas dobladas, sube la cadera apretando el glúteo y baja.",
        "puente de glúteo a una pierna" to "Igual que el puente, pero con una pierna estirada en el aire, alternando lado.",
        "puente de glúteo sostenido" to "Sube la cadera como en el puente y aguanta arriba apretando el glúteo.",
        "puente suave de espalda" to "Sube la cadera despacio y sin forzar, sintiendo cómo se estira la espalda baja.",
        "respiración profunda con brazos arriba" to "Sube los brazos mientras inhalas profundo, y bájalos despacio mientras exhalas.",
        "respiración rápida de pie" to "De pie, inhala y exhala rápido y fuerte varias veces para activar el cuerpo.",
        "retracción de escápulas" to "De pie o sentado, junta los omóplatos hacia atrás como si sostuvieras un lápiz entre ellos.",
        "rodillas al pecho corriendo" to "Corre en tu lugar subiendo las rodillas lo más alto que puedas hacia el pecho.",
        "rotación de cadera de pie" to "De pie, gira la cadera en círculos, la mitad del tiempo para cada lado.",
        "rotación de columna sentado" to "Sentado con la espalda recta, gira el torso hacia un lado sosteniendo, y luego hacia el otro.",
        "rotación de hombros" to "Rueda los hombros hacia atrás en círculos amplios y lentos.",
        "rotación de tobillos" to "Levanta un pie y gira el tobillo en círculos, luego cambia de pie.",
        "sacudida de brazos y piernas" to "Sacude brazos y piernas sueltos, como para relajar los músculos entre esfuerzos.",
        "saltos de rana" to "Baja a sentadilla profunda y salta hacia adelante como rana, aterrizando suave.",
        "sentadilla búlgara en silla" to "Un pie apoyado atrás en una silla, baja con la otra pierna al frente y sube.",
        "sentadilla isométrica en pared" to "Espalda pegada a la pared, baja como si te sentaras y aguanta ahí quieto.",
        "sentadilla profunda sostenida" to "Baja a sentadilla lo más profundo que puedas y quédate ahí sin moverte.",
        "sentadilla sumo" to "Pies bien separados y apuntando afuera, baja como sentadilla normal pero más ancho.",
        "sentadilla sumo con salto" to "La sentadilla sumo, pero al subir das un pequeño salto.",
        "sentadillas" to "Pies al ancho de los hombros, baja como si te sentaras en una silla y sube.",
        "sentadillas a la silla sin peso" to "Baja hasta casi tocar una silla con los glúteos y sube sin sentarte del todo.",
        "sentadillas con impulso" to "Baja a sentadilla y sube con un poco de impulso, sin llegar a saltar.",
        "sentadillas con salto" to "Baja a sentadilla y sube saltando, aterrizando suave para la siguiente repetición.",
        "sentadillas continuas" to "Sentadillas seguidas sin pausa, manteniendo un ritmo constante.",
        "sentadillas profundas" to "Baja lo más que puedas, más abajo que una sentadilla normal, y sube controlado.",
        "sentadillas rápidas" to "Sentadillas normales pero a ritmo rápido, sin perder la forma.",
        "sombra de boxeo" to "Da golpes al aire moviéndote como si estuvieras boxeando contra alguien.",
        "superman acostado" to "Boca abajo, levanta brazos y piernas al mismo tiempo como si volaras, y baja.",
        "tijeras acostado" to "Acostado, piernas rectas un poco levantadas, ábrelas y ciérralas como tijeras.",
        "trote en el lugar" to "Trota sin avanzar, a tu propio ritmo, moviendo bien los brazos.",
        "posición de caballo" to "Pies separados, rodillas dobladas como si montaras a caballo, espalda recta, aguanta la posición."
    )

    fun explicacionDe(nombre: String): String = explicaciones[nombre.trim().lowercase()] ?: ""

    // ------------------------------------------------------------------ rutinas

    val rutinas: List<Rutina> = listOf(

        Rutina(
            "Bajar de peso",
            "Cardio corto y constante. Sudas y el corazón se acelera.",
            listOf(
                b("Bloque 1", "Brincos de tijera", "Rodillas al pecho corriendo", "Sentadillas rápidas", "Escaladores", "Brincos altos"),
                b("Bloque 2", "Burpees", "Sombra de boxeo", "Sentadillas con salto", "Desplantes alternados", "Plancha con toque de hombro"),
                b("Bloque 3", "Trote en el lugar", "Rodillas al pecho corriendo", "Sentadilla sumo con salto", "Burpees", "Escaladores")
            )
        ),

        Rutina(
            "Ganar músculo",
            "Fuerza con tu propio peso. Movimientos lentos y controlados.",
            listOf(
                b("Bloque 1", "Lagartijas lentas", "Sentadillas profundas", "Plancha firme", "Desplantes con pausa", "Puente de glúteo a una pierna"),
                b("Bloque 2", "Lagartijas diamante", "Sentadilla búlgara en silla", "Fondos de tríceps en silla", "Plancha con toque de hombro", "Puente de glúteo sostenido"),
                b("Bloque 3", "Lagartija parado de manos", "Sentadilla isométrica en pared", "Lagartijas con pausa abajo", "Desplante estático por lado", "Plancha con elevación de pierna")
            )
        ),

        Rutina(
            "Pecho y brazos",
            "Empuje. Todo lo que trabaja pecho, hombro y tríceps.",
            listOf(
                b("Bloque 1", "Lagartijas normales", "Lagartijas abiertas", "Plancha alta", "Fondos de tríceps en silla", "Círculos de brazos sostenidos"),
                b("Bloque 2", "Lagartijas diamante", "Fondos de tríceps en silla", "Lagartijas inclinadas en pared", "Plancha con toque de hombro", "Extensión de brazos al frente"),
                b("Bloque 3", "Lagartija parado de manos", "Lagartijas con pausa abajo", "Círculos de brazos sostenidos", "Fondos de tríceps lentos", "Plancha alta con desplazamiento")
            )
        ),

        Rutina(
            "Piernas y glúteos",
            "Tren inferior. Vas a sentirlas al día siguiente.",
            listOf(
                b("Bloque 1", "Sentadillas", "Desplantes alternados", "Puente de glúteo", "Sentadilla sumo", "Elevación de talones"),
                b("Bloque 2", "Sentadilla búlgara en silla", "Elevación de talones", "Saltos de rana", "Puente de glúteo a una pierna", "Sentadilla isométrica en pared"),
                b("Bloque 3", "Saltos de rana", "Sentadilla isométrica en pared", "Patada de glúteo en cuatro puntos", "Desplantes caminando", "Puente de glúteo sostenido")
            )
        ),

        Rutina(
            "Abdomen y core",
            "Centro fuerte. Cuida la espalda baja, no jalones el cuello.",
            listOf(
                b("Bloque 1", "Abdominales con los pies", "Plancha frontal", "Tijeras acostado", "Elevación de piernas", "Plancha lateral alternada"),
                b("Bloque 2", "Escaladores", "Plancha lateral alternada", "Elevación de piernas", "Giro ruso sentado", "Hueco abdominal sostenido"),
                b("Bloque 3", "Giro ruso sentado", "Hueco abdominal sostenido", "Plancha con toque de hombro", "Tijeras acostado", "Escaladores")
            )
        ),

        Rutina(
            "Oficina sin sudar",
            "Mueve la sangre sin despeinarte. Sirve con camisa puesta.",
            listOf(
                b("Bloque 1", "Marcha en el lugar", "Rotación de hombros", "Estiramiento de cuello lento", "Círculos de muñeca", "Respiración profunda con brazos arriba"),
                b("Bloque 2", "Sentadillas a la silla sin peso", "Elevación de talones parado", "Apertura de pecho en marco de puerta", "Rotación de cadera de pie", "Estiramiento de espalda sentado"),
                b("Bloque 3", "Rotación de cadera de pie", "Estiramiento de muñecas y dedos", "Marcha en el lugar", "Elevación de talones parado", "Respiración profunda con brazos arriba")
            )
        ),

        Rutina(
            "Espalda y postura",
            "Para el que pasa el día sentado. Abre lo que la silla cierra.",
            listOf(
                b("Bloque 1", "Gato y vaca en cuatro puntos", "Superman acostado", "Retracción de escápulas", "Apertura de pecho en pared", "Estiramiento de psoas en desplante"),
                b("Bloque 2", "Estiramiento de psoas en desplante", "Plancha frontal", "Apertura de pecho en pared", "Superman acostado", "Retracción de escápulas"),
                b("Bloque 3", "Rotación de columna sentado", "Puente de glúteo", "Colgarse o estirarse hacia arriba", "Gato y vaca en cuatro puntos", "Apertura de pecho en pared")
            )
        ),

        Rutina(
            "Energía rápida",
            "Despierta el cuerpo en segundos. Ideal para la mañana.",
            listOf(
                b("Bloque 1", "Brincos de tijera", "Sacudida de brazos y piernas", "Respiración rápida de pie", "Rodillas al pecho corriendo", "Golpes al aire"),
                b("Bloque 2", "Rodillas al pecho corriendo", "Sentadillas con impulso", "Golpes al aire", "Brincos de tijera", "Sacudida de brazos y piernas"),
                b("Bloque 3", "Brincos altos", "Escaladores", "Estiramiento largo hacia el techo", "Sombra de boxeo", "Respiración rápida de pie")
            )
        ),

        Rutina(
            "Movilidad y flexibilidad",
            "Articulaciones sueltas. Nada de fuerza, todo rango.",
            listOf(
                b("Bloque 1", "Círculos de cadera", "Círculos de hombro", "Rotación de tobillos", "Rotación de columna sentado", "Postura del niño"),
                b("Bloque 2", "Sentadilla profunda sostenida", "Estiramiento de isquiotibiales", "Postura del niño", "Círculos de cadera", "Rotación de tobillos"),
                b("Bloque 3", "Desplante con rotación", "Puente suave de espalda", "Estiramiento de cuádriceps de pie", "Círculos de hombro", "Sentadilla profunda sostenida")
            )
        ),

        Rutina(
            "Resistencia",
            "Aguante. Bloques largos, ritmo constante, sin parar.",
            listOf(
                b("Bloque 1", "Trote en el lugar", "Sentadillas continuas", "Plancha sostenida", "Escaladores continuos", "Desplantes caminando"),
                b("Bloque 2", "Burpees a ritmo lento", "Escaladores continuos", "Desplantes caminando", "Trote en el lugar", "Sentadillas continuas"),
                b("Bloque 3", "Brincos de tijera", "Sombra de boxeo", "Sentadilla isométrica en pared", "Burpees a ritmo lento", "Plancha sostenida")
            )
        )
    )

    // ------------------------------------------------------------------ frases

    val paquetes: List<Paquete> = listOf(

        Paquete(
            "Mis frases",
            "Las frases originales, tal como las escribiste.",
            listOf(
                "Respira. Concéntrate. Recuerda quién eres y a dónde vas. Eres un victor, y la victoria está dentro de ti, no allá afuera esperándote. Eres un vencedor. No porque nunca te caigas, sino porque siempre te vuelves a levantar.",
                "Agradece. Conecta con tu ser superior. 1, 1, 9, 8, 1. Dios habita en ti, en tu respiración, en tus manos, en lo que estás construyendo justo ahora. No estás solo en esto y nunca lo has estado.",
                "Yo soy la fuente en completa expresión. Lo integro, lo comparto, libero todo límite. Manifiesto la realidad perfecta aquí y ahora, en este momento, en este cuerpo, en esta vida que estoy eligiendo.",
                "Tu percepción cambia tu realidad. Cambia cómo estás percibiendo las cosas hacia lo positivo, porque tu mente es un proyector y afuera solo ves lo que traes adentro. Tu desafío es ser millonario, estar presente y disfrutar de una hermosa familia. Los problemas y los obstáculos no son castigos, son oportunidades disfrazadas.",
                "Yo soy las riquezas de Dios fluyendo a mis manos y a mi uso, que nada ni nadie puede detener. Lo que es mío llega a tiempo, por el camino correcto, y llega para quedarse.",
                "Estás creando una nueva identidad. Obsérvala. Obsérvala con calma, sin exigirle que ya esté terminada. Agradécele. Agradécele por aparecer, aunque todavía sea frágil, aunque a ratos se te olvide. Ya empezó.",
                "Ángeles y guías, les doy permiso de asistirme en todas las áreas de mi vida. Límpienme y protéjanme de toda negatividad, la de afuera y la que yo mismo me genero. Acompáñenme hoy, en lo grande y en lo pequeño.",
                "5, 2, 0. 7, 4, 1, 8."
            )
        ),

        Paquete(
            "Antinarcisismo",
            "Duras y honestas. Para reconocer el patrón antes de repetirlo.",
            listOf(
                "Los arranques de drama y las exigencias de definiciones en caliente no son amor. Son la forma desesperada de mantener el foco en ti para no aguantar el silencio ni la indiferencia. Cuando sientas esa urgencia de exigir una respuesta ya, esa es justo la señal de esperar. El silencio no te está matando.",
                "Cuando te topas con el vacío de su rechazo, lo que asusta no es perderla a ella. Es quedarte solo con el trabajo que tienes pendiente contigo. Ese trabajo no lo puede hacer ella ni lo puede hacer la relación: te toca a ti, y se hace despacio.",
                "Deja de operar bajo esa transacción oculta donde te portas como un santo y tragas saliva esperando que, como recompensa, ella te devuelva el estatus, el aplauso y el perdón. Lo que haces esperando cobro no es un cambio, es una factura con retraso.",
                "Cada vez que montas un espectáculo, te bajas de la camioneta o le exiges que te valide, estás demostrando que el cambio superficial se cae en cuanto la presión sube y las cosas no salen según tu guion. La prueba no son los días buenos. La prueba es hoy, cuando duele.",
                "Le estabas pidiendo que fuera tu terapeuta, tu espejo y tu guía correctora. Le estabas delegando a la mujer que lastimaste la tarea de sostener tu conciencia. Esa carga no es de ella. Busca a alguien capacitado para eso y quítasela de encima.",
                "El costo de seguir forzando una reconciliación desde este lugar es brutal: vas a seguir desgastando tu energía, dándoles a tus hijas un entorno tenso, y volviéndote una presencia resentida en tu propia casa. El ambiente de esa casa hoy depende de ti.",
                "Cumple con tu parte económica y con tus deberes de padre con absoluta dignidad. Pero deja de mendigar migajas de afecto e intimidad de una mujer que te está pidiendo a gritos un espacio que tú te niegas a soltar. Cumplir no te da derecho a cobrar cercanía.",
                "La culpa real no se limpia con discursos bonitos ni con promesas de redención de última hora. Se quema lentamente, con una consistencia silenciosa que no cobra facturas ni exige testigos. Nadie tiene que enterarse de que cambiaste. Se nota solo, con el tiempo.",
                "Cuando notes que las aguas se calman y tu cabeza empiece a susurrarte que ya cambiaste suficiente, o que ella ya debería valorarte, reconoce la trampa. Es el mismo patrón de siempre buscando cobrar su comisión justo cuando bajaste la guardia.",
                "Deja de buscar una respuesta rápida de sí o no para calmar tu ansiedad. Esa exigencia de certeza es el torniquete que termina de asfixiar cualquier posibilidad de sanación. Aguanta la incertidumbre. Ahí es donde se construye la confianza que rompiste.",
                "El dolor de saber que ella estuvo con otros no es amor herido. Es la parte de ti que quiere seguir teniendo el control del tablero y ser el centro de su mundo. Reconócelo por lo que es, y luego regresa a lo tuyo.",
                "Dejar de usar el despecho como combustible significa aceptar que su vida privada ya no te pertenece. Rascarle a esa herida solo demuestra que todavía quieres poseerla en lugar de respetarla. Respetarla se ve así: no preguntar, no revisar, no imaginar."
            )
        ),

        Paquete(
            "Jacobo Grinberg",
            "Sobre cómo pedir: gratitud, desprendimiento y coherencia interna.",
            listOf(
                "No pido desde la carencia ni desde la necesidad desesperada, sino desde el reconocimiento de que lo que necesito ya me ha sido concedido. Esto no es autoengaño ni pensamiento mágico: en las dimensiones más sutiles todas las posibilidades ya existen simultáneamente. Pido como quien le recuerda al universo algo que ya había sido acordado.",
                "No me pierdo en los detalles externos, sino en la firma energética de lo que deseo. El universo responde mucho más a las frecuencias que a las palabras. Cuando pido salud, genero la sensación de vitalidad completa, en lugar de enumerar los síntomas que quiero eliminar.",
                "Formulo mi petición con intención clara y, de inmediato, la suelto por completo, confiando en que el universo sabe cómo y cuándo manifestarla. Es el equilibrio perfecto entre intención clara y rendición total. Planto la semilla y confío en la tierra y el agua; si la desenterrara cada día para ver si crece, la mataría.",
                "Me abro a que la respuesta llegue desde cualquier dirección. El universo tiene recursos que mi mente limitada no puede ni imaginar, y una petición desde la conciencia expandida se propaga por múltiples dimensiones. Cuando me aferro a ideas específicas sobre cómo debe llegar, limito severamente las posibilidades.",
                "Alineo mi petición no solo con mis deseos conscientes, sino con todos mis niveles. Si conscientemente deseo algo pero inconscientemente lo temo, creo un campo de interferencia: es como tener un freno mientras piso el acelerador. Con honestidad brutal identifico y sano las partes que se resisten, y así la potencia de mi petición se multiplica exponencialmente.",
                "Reconozco que existe un ritmo en la realidad: ciclos de expansión y contracción, momentos de siembra y momentos de cosecha. Desarrollo la sensibilidad para percibir cuándo las corrientes universales fluyen en la dirección de mis intenciones. Remar contra la corriente agota; cuando aprendo a usarlas, el viaje se vuelve infinitamente más fácil.",
                "Mis peticiones no son demandas. En lugar de decir dame, digo estoy disponible para servir, si sirve al propósito mayor. Así me convierto en cocreador consciente, no en alguien que ruega favores a una fuerza externa, y activo la reciprocidad cósmica: el universo se vuelve tan generoso conmigo como yo con él.",
                "El universo no me da lo que pido, me da lo que soy. Mi estado de ser es mi petición más poderosa, mucho más que cualquier palabra. Por eso emano ya una frecuencia de confianza y gratitud, y pido no como quien carece, sino como quien ya lo está viviendo y solo permite que se manifieste.",
                "No pido cosas aisladas, pido la transformación completa de mi vida en la dirección que deseo. En lugar de pedir simplemente dinero, pido una vida de abundancia: financiera, pero también salud, creatividad y propósito que se refuerzan mutuamente. Ya que estoy transformando mi vida, lo hago completamente, y los cambios resultan más duraderos.",
                "Las peticiones más irresistibles son las que benefician no solo a mí, sino a la red más amplia. Cuando pido desde una motivación de servicio, trasciendo el ego personal y accedo a fuerzas de apoyo, oportunidades y sincronismos reservados para lo que beneficia a otros. No niego mis necesidades: reconozco el vínculo entre mi bien personal y el bienestar del todo.",
                "No soy un mendigo del universo pidiendo favores: soy una expresión de la conciencia explorando sus propias posibilidades infinitas. La verdadera maestría no está en obtener siempre lo que quiero, sino en querer siempre lo que recibo. Y muchas veces solo estaba esperando recordar cómo pedir."
            )
        ),

        Paquete(
            "Grabovoi — combo de ventas",
            "Las ocho secuencias numéricas del combo de ventas, tal cual se dicen, para repetir seguidas.",
            listOf(
                "Combo Grabovoi para aumentar las ventas. Ocho secuencias numéricas, en este orden. " +
                    "Primera secuencia, conexión con el creador: 11981. " +
                    "Segunda secuencia, atraer circunstancias favorables: 419. 488. 71. " +
                    "Tercera secuencia, abundancia y prosperidad: 71427321893. " +
                    "Cuarta secuencia, aumentar ventas: 54121381948. " +
                    "Quinta secuencia, eliminar obstáculos: 91688. " +
                    "Sexta secuencia, resultados rápidos: 741. " +
                    "Séptima secuencia, protección divina: 8888. " +
                    "Octava y última secuencia, todo es posible: 519. 7148."
            )
        ),

        Paquete(
            "Vendedor tiburón",
            "Para vendedores: atraer al cliente, manejar objeciones, cerrar y no soltar la mentalidad.",
            listOf(
                "Un tiburón no espera a que la comida llegue sola. Se mueve, huele, busca. Hoy tu trabajo no es esperar a que el cliente te llame: es salir a buscarlo. Cada llamada, cada mensaje y cada visita es una brazada más cerca de la venta. Muévete primero y piensa después.",
                "El cliente no compra un producto, compra la solución a algo que le duele o que quiere lograr. Antes de hablar de lo tuyo, haz preguntas y escucha de verdad. Quien más escucha, más vende. Averigua qué le quita el sueño y entonces muéstrale cómo tu producto se lo resuelve.",
                "Un no no es un rechazo a ti. Es un todavía no, o un no con la información que tenía. Un tiburón no se ofende cuando la presa se le escapa: sigue nadando hacia la siguiente. Cada no te acerca al sí, porque las ventas son un juego de números y tú estás sumando.",
                "Para atraer al cliente, primero genera confianza. Sonríe, mira a los ojos, habla claro y no prometas lo que no puedes cumplir. La gente le compra a quien le cae bien y le inspira seguridad. Vende primero la confianza, y el producto se vende casi solo.",
                "Cuando el cliente dice que está caro, casi nunca habla del precio. Habla de que todavía no ve suficiente valor. No bajes el precio de inmediato: regresa al beneficio. Pregunta qué costaría seguir con el problema que tiene hoy. El valor bien explicado justifica cualquier precio.",
                "Cerrar la venta no es presionar, es ayudar a decidir. Si ya mostraste el valor y resolviste las dudas, pide la venta con seguridad: «¿Lo dejamos listo hoy?». El que no pide, no recibe. Muchas ventas se pierden porque el vendedor tuvo miedo de hacer la pregunta final.",
                "Tu energía entra a la sala antes que tú. Si llegas con duda, el cliente duda. Si llegas con seguridad, el cliente se tranquiliza. Respira, endereza la espalda, sube la voz un poco y entra como quien sabe que lo que ofrece de verdad le sirve a la otra persona.",
                "El dinero está en el seguimiento. La mayoría de las ventas se cierran después del cuarto o quinto contacto, y casi nadie llega hasta ahí. Si tú sí llegas, ya le ganaste a la mayoría. Escribe, llama, vuelve a escribir, con respeto y con constancia. La persistencia amable vende.",
                "Los grandes vendedores no son los que nunca fallan, son los que se levantan más rápido. Perdiste una venta hoy: analiza qué pasó, aprende una cosa y suéltala. No cargues el rechazo a la siguiente llamada. Cada conversación empieza en cero, con toda la energía.",
                "Atrae al cliente con una pregunta, no con un discurso. Una buena pregunta abre la puerta: «¿Qué te gustaría lograr con esto?». Cuando el cliente habla de lo que quiere, él mismo se va convenciendo. Tú solo guías la conversación hasta que él mismo dice que sí.",
                "No vendas a todos, vende a los que de verdad necesitan lo que ofreces. Un buen vendedor sabe descalificar rápido y dedicar su tiempo a quien sí puede y quiere comprar. Tu tiempo es tu dinero. Invierte tu mejor energía en los clientes con más posibilidad de cerrar.",
                "Manejar una objeción es una oportunidad, no un ataque. Cuando el cliente pone una duda, está interesado, porque si no le importara no diría nada. Escucha, reconoce lo que siente, responde con calma y confirma: «¿Eso resuelve tu duda?». Después de la objeción viene la venta.",
                "Yo soy un vendedor que aporta valor. No molesto, ayudo. No ruego, propongo. Mi producto cambia la vida de quien lo usa, y es mi responsabilidad llevárselo a quien lo necesita. Hablo con seguridad, cierro con respeto y me preparo todos los días para vender mejor.",
                "Hoy vas por una meta clara. Escríbela, ponle número y ponle fecha. Divídela en llamadas, citas y cierres por día. Un tiburón no nada sin rumbo: sabe a dónde va. Cuando tu meta es clara, cada acción se vuelve más fácil, y cada día cuenta. Ahora, a cazar la siguiente venta."
            )
        ),

        Paquete(
            "Tracy 1 — Programa tu mente",
            "Ideas de Brian Tracy en palabras propias. Arranque del día: identidad, metas y autoimagen. Sigue con Tracy 2.",
            listOf(
                "Programa tu mente antes de que el mundo la programe por ti. Lo que te repites una y otra vez, tu subconsciente lo acepta como orden y empieza a trabajar para cumplirlo. Hoy tú decides qué instrucciones le das.",
                "Me gusto. Me gusto. Me gusto. Dilo con convicción, aunque al principio suene raro. Tu autoestima es el motor de todo lo demás: entre más te gustas y te respetas, mejor trabajas, mejor vendes y mejor tratas a los demás.",
                "Yo soy responsable. Yo soy responsable de mi vida, de mis resultados y de cómo respondo a lo que me pasa. En el momento en que dejo de culpar a otros, recupero el control. Nadie viene a hacerlo por mí, y eso me hace libre.",
                "Te conviertes en lo que piensas la mayor parte del tiempo. Si piensas en lo que quieres y en cómo conseguirlo, te acercas. Si piensas en lo que temes y en lo que te falta, también te acercas a eso. Elige con cuidado dónde pones la cabeza.",
                "Tus metas se escriben en presente, en positivo y en primera persona. No digas quiero ganar más. Di: yo gano tal cantidad al mes. Tu mente no sabe distinguir entre lo que imaginas con fuerza y lo que vives, y empieza a moverse hacia eso.",
                "Escribe tus diez metas cada día, sin mirar la lista de ayer. Lo que escribes a mano se graba más hondo. Las que se te olvidan no eran tan importantes; las que se repiten solas son las que de verdad quieres.",
                "Si pudieras lograr una sola meta en las próximas veinticuatro horas, la que más impacto tendría en tu vida, ¿cuál sería? Esa es tu meta principal. Rodéala, escríbela arriba de todo y piensa en ella mañana, tarde y noche.",
                "Visualízalo como si ya fuera tuyo. Mira la escena, siente la emoción, escucha lo que te dicen. Tu autoimagen manda: nunca vas a ganar de forma sostenida más de lo que crees que vales. Sube la imagen, y los resultados la siguen.",
                "Actúa como si ya fueras la persona que quieres ser. Camina, habla y decide como ella. Si actúas así el tiempo suficiente, el sentimiento te alcanza y ya no estás actuando: ya eres.",
                "Tu mente ya está programada para hoy. Ahora la idea tiene que volverse acción. Lo siguiente es decidir qué haces primero. Vamos a las tareas del día."
            )
        ),

        Paquete(
            "Tracy 2 — Tareas del día",
            "Ideas de Brian Tracy en palabras propias. Priorizar y atacar primero lo difícil. Sigue con Tracy 3.",
            listOf(
                "Cómete primero el sapo. Si tienes que hacer algo desagradable hoy, hazlo a primera hora. Y si tienes dos sapos, empieza por el más feo. Después de eso, lo demás del día se siente ligero.",
                "Planea tu día la noche anterior. Haz la lista antes de dormir y tu subconsciente trabaja en ella mientras descansas. Cada minuto que inviertes en planear te ahorra diez en ejecutar.",
                "Usa el método A B C D E. A es lo que debes hacer, con consecuencias serias si no lo haces. B es lo que deberías hacer. C es lo que estaría bien hacer. D es lo que puedes delegar. E es lo que puedes eliminar. Nunca hagas una B mientras quede una A pendiente.",
                "El veinte por ciento de tus tareas produce el ochenta por ciento de tus resultados. De una lista de diez, dos valen más que las otras ocho juntas. Encuentra esas dos y trabaja en ellas primero.",
                "Pregúntate: ¿cuál es el uso más valioso de mi tiempo justo ahora? Hazte esa pregunta cada vez que termines algo, y cada vez que te distraigas. La respuesta siempre te regresa a lo importante.",
                "No puedes hacerlo todo, pero sí puedes hacer lo más importante. Procrastina a propósito las tareas de poco valor. Decir que no a lo pequeño es lo que te deja tiempo para lo grande.",
                "Una vez que empiezas tu tarea principal, termínala. No brinques a otra cosa. Cada vez que la dejas y la retomas, pagas otra vez el costo de arrancar. Concentración total hasta acabar.",
                "Si una tarea te pesa, pártela en pedazos chicos y haz solo el primero. Al sapo grande se le come a mordidas. Avanzar un poco te da el empuje para el siguiente pedazo.",
                "Trabaja con sentido de urgencia. Muévete rápido en lo importante, decide rápido y corrige sobre la marcha. La gente que logra mucho no es más lista: simplemente se mueve más rápido hacia lo que importa.",
                "Terminar una tarea importante libera energía. Esa sensación de logro es combustible para lo siguiente. Hoy tu tarea más importante probablemente es vender. Vamos a eso."
            )
        ),

        Paquete(
            "Tracy 3 — Ventas",
            "Ideas de Brian Tracy en palabras propias. Prospectar, presentar, cerrar y dar seguimiento. Cierra la secuencia.",
            listOf(
                "Vender es una habilidad, y toda habilidad se aprende. Nadie nace vendedor. Los mejores vendedores fueron un día los peores, y se volvieron buenos practicando y estudiando un poco cada día.",
                "El veinte por ciento de los vendedores gana el ochenta por ciento del dinero. La diferencia entre estar arriba y estar abajo no es enorme: son unas pocas habilidades clave, hechas un poco mejor. Mejora una de ellas esta semana.",
                "Tu trabajo más importante es prospectar. Pasa más tiempo frente a clientes con capacidad de comprar. Cuenta tus llamadas, tus citas y tus presentaciones: lo que mides, mejora.",
                "Antes de presentar, pregunta. El cliente no compra un producto, compra lo que el producto hace por él. Descubre qué quiere lograr y qué le duele, y después muéstrale cómo lo tuyo le resuelve exactamente eso.",
                "La gente compra con la emoción y después lo justifica con la lógica. Habla de cómo se va a sentir, de lo que va a ganar y de lo que va a dejar de perder. Después dale los números para que esté tranquilo con su decisión.",
                "La confianza vende. El cliente necesita sentir que tú quieres ayudarlo, no solo cobrarle. Escucha más de lo que hablas. Entre más hablas tú de él, y menos de ti, más confía.",
                "Las objeciones son señales de interés. Cuando el cliente pone un pero, está pidiendo más información para poder decir que sí. Agradécela, aclárala y confirma que quedó resuelta.",
                "Pide la venta. Muchos vendedores hacen todo bien y al final no se atreven a preguntar. Pregunta con seguridad: ¿empezamos? Y después de preguntar, guarda silencio. El primero que habla, pierde.",
                "Cuida a cada cliente como si fuera el único. Un cliente contento te vuelve a comprar y te recomienda. Las referencias son las ventas más fáciles que vas a cerrar: pídelas siempre.",
                "Aprende algo nuevo de ventas cada día. Escucha audios en el carro y lee treinta minutos diarios. El que invierte en su mente gana más. Mañana programamos la mente otra vez, y el ciclo sigue."
            )
        ),

        Paquete(
            "Mentalidad estoica",
            "Inspiradas en Marco Aurelio y Epicteto, escritas en palabras propias.",
            listOf(
                "Lo que no depende de ti, suéltalo. Lo que sí depende de ti, hazlo bien. Casi todo tu sufrimiento vive en la frontera entre esas dos cosas, tratando de controlar lo que nunca fue tuyo.",
                "No es el problema el que te tumba, es lo que te dices del problema. Dos personas viven lo mismo y una se levanta y la otra no. La diferencia está en la historia que se cuentan.",
                "El obstáculo enseña el camino. Lo que se te atraviesa no está impidiendo tu avance: está mostrándote por dónde tienes que crecer. Si no hubiera resistencia, no habría fuerza.",
                "Hoy te vas a topar con gente difícil, con groserías y con gente que no cumple. Ya lo sabes de antemano, así que no te agarre de sorpresa ni te arruine el día.",
                "Vive como si esto fuera lo último que haces, pero sin drama y sin prisa. No significa correr: significa hacerlo con atención, como si valiera la pena hacerlo bien.",
                "Te pueden quitar el trabajo, el dinero, la salud y la razón. Lo único que nadie te puede quitar es la forma en que respondes a lo que te pasa. Ahí vives tú.",
                "Deja de discutir cómo debería ser un hombre bueno. Sé uno. Las horas que gastas explicando en quién te vas a convertir son las mismas que podrías gastar convirtiéndote."
            )
        )
    )
}
