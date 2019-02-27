#SQL DSL

DSL para escribir SQL seguro para los proyectos desarrollados
por R2B

#Caracteristicas
- Soporte POstgres Hstore
- Extensible
- Fast Render sin reflexión
- No generation code

```Kotlin

    fun insertHstore(map: Map<String, String>): String {
        val builder = StringJoiner(",")
        for (value in map) {
            builder.add("${value.key} => ${value.value}")
        }
        return builder.toString()
    }


    fun updateHstore(map: Map<String, String>): String {
        var multUpdate = StringJoiner("||")
        for (value in map) {
            multUpdate.add("hstore('${value.key}','${value.value}')")
        }

        return multUpdate.toString()
    }
```
