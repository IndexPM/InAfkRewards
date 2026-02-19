# InAfkRewards

Un plugin de Bukkit/Spigot que otorga recompensas automáticas a los jugadores que permanecen AFK (Away From Keyboard) en una región específica del servidor.

## 🎯 Características

- ✅ **Sistema de recompensas AFK** - Otorga recompensas a intervalos regulares
- ✅ **Región configurable** - Define un área donde se aplican las recompensas
- ✅ **Integración con PlaceholderAPI** - Usa placeholders en otros plugins
- ✅ **Comandos intuitivos** - `/afkrewards` e `/afkrewardsreload`
- ✅ **Mensajes personalizables** - Configura todos los mensajes del plugin
- ✅ **Sistema de permisos** - Control de acceso a comandos
- ✅ **Manejo robusto de errores** - Validación completa de configuración
- ✅ **Logging detallado** - Seguimiento de todas las acciones

## 📋 Requisitos

- **Java 17+**
- **Bukkit/Spigot 1.13+** (compatible hasta 1.20+)
- **PlaceholderAPI** (opcional, para usar placeholders)

## ⚙️ Instalación

1. Descarga el archivo JAR del plugin
2. Colócalo en la carpeta `plugins/` de tu servidor
3. Reinicia el servidor con `/reload confirm` o reinicia completamente
4. Configura el plugin en `plugins/InAfkRewards/config.yml`

## 🔧 Configuración

### config.yml

```yaml
# Configuración de la región AFK
afk-region:
  x: 0.0           # Coordenada X del centro
  y: 0.0           # Coordenada Y del centro
  z: 0.0           # Coordenada Z del centro
  world: lobby     # Nombre del mundo
  radius: 5.0      # Radio en bloques

# Configuración de recompensas
reward:
  interval: 300    # Intervalo en segundos
  command: "key give %player_name% Koth 3"  # Comando a ejecutar
```

### Placeholders disponibles

- `%inafkrewards_inafk%` - Muestra si el jugador está en la región AFK
- `%inafkrewards_radius%` - Radio de la región
- `%inafkrewards_location%` - Ubicación de la región

## 📝 Comandos

| Comando | Descripción | Permisos |
|---------|-------------|----------|
| `/afkrewards` | Muestra información del plugin | `inafkrewards.info` |
| `/afkrewardsreload` | Recarga la configuración | `inafkrewards.reload` |

## 🔐 Permisos

```
inafkrewards.*              - Acceso a todos los permisos
inafkrewards.info           - Usar /afkrewards
inafkrewards.reload         - Usar /afkrewardsreload
```

## 🏗️ Compilar desde código

```bash
# Con Gradle
gradle build

# Se generará un JAR en: build/libs/InAfkRewards-1.1.0.jar
```

## 📁 Estructura del Proyecto

```
InAfkRewards/
├── src/
│   └── com/index/inafkrewards/
│       ├── InAfkRewardsPlugin.java          # Clase principal
│       ├── commands/
│       │   └── CommandHandler.java          # Manejador de comandos
│       ├── managers/
│       │   ├── AfkRegionManager.java        # Gestión de región
│       │   └── RewardHandler.java           # Gestión de recompensas
│       ├── listeners/
│       │   └── PlayerAFKListener.java       # Listener de eventos
│       └── placeholders/
│           └── InAfkRewardsPlaceholder.java # Integración PlaceholderAPI
├── resources/
│   └── config.yml                           # Archivo de configuración
├── plugin.yml                               # Descriptor del plugin
├── build.gradle                             # Configuración de Gradle
└── README.md                                # Este archivo
```

## 🔄 Cómo funciona

1. **Inicialización**: El plugin carga la configuración y crea los managers
2. **Monitoreo**: Cada X segundos verifica qué jugadores están en la región AFK
3. **Recompensas**: Ejecuta el comando configurado para cada jugador en la región
4. **Notificación**: Notifica al jugador que ha recibido una recompensa

## 🐛 Solución de problemas

### El plugin no inicia
- Verifica que el `main` en `plugin.yml` sea: `com.index.inafkrewards.InAfkRewardsPlugin`
- Revisa los logs para mensajes de error

### Las recompensas no se otorgan
- Verifica la configuración de `afk-region` en `config.yml`
- Asegúrate de que los jugadores están exactamente en la región
- Revisa que el comando es válido

### PlaceholderAPI no funciona
- Instala PlaceholderAPI en el servidor
- Recarga el plugin con `/afkrewardsreload`

## 💡 Ejemplos de uso

### Dar moneda virtual
```yaml
reward:
  interval: 300
  command: "money give %player_name% 1000"
```

### Dar ítems
```yaml
reward:
  interval: 60
  command: "give %player_name% diamond 5"
```

### Ejecutar comandos complejos
```yaml
reward:
  interval: 300
  command: "execute as %player_name% run say Recibí recompensa!"
```

## 📊 Mejoras en v1.1.0

- ✅ Refactorización completa del código
- ✅ Corrección de errores de paquetes
- ✅ Manejo mejorado de errores
- ✅ Validación de configuración
- ✅ Sistema de comandos mejorado
- ✅ Placeholders expandidos
- ✅ Mejor logging y documentación

## 📄 Licencia

Este plugin está disponible bajo licencia libre. Siéntete libre de usarlo, modificarlo y distribuirlo.

## 👨‍💻 Autor

**IndexDev** - Desarrollo y mantenimiento del plugin

## 🤝 Contribuciones

Si encuentras bugs o tienes sugerencias, por favor reporta un issue o contribuye directamente con un PR.

---

**¿Necesitas ayuda?** Abre un issue en el repositorio o contacta al autor.