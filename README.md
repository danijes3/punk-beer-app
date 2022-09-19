# Punk Beer App :beer:

Punk Beer App es una aplicación para Android que muestra el listado de todas las cervezas utilizando [Punk API](https://developer.marvel.com/)


## Características y *Frameworks* :books:

- Lenguaje Kotlin
- Arquitectura Clean - MVVM
- Retrofit para consumo de API
- Hilt para inyección de dependencias
- Tests unitarios con JUnit y Mockk

## Posibles vías de desarrollo  :muscle:
- Añadir una base de datos local (con *Room*, por ejemplo). De esta forma no es necesario realizar tantas peticiones a la API, además de la posibilidad de poder utilizar la aplicación sin conexión
- Añadir una caché para guardar temporalmente la lista de datos, incrementando el rendimiento
- Utilizar la biblioteca *Paging3* para añadir paginación y que la lista se vaya actualizando conforme el usuario hace scroll
- Implementar UI Test 