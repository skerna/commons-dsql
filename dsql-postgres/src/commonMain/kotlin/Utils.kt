package io.skerna.dsql.dialect.postgres

/**
 * Postgres utilidades
 */
object Postgres {
    fun mapToHstore(map: Map<String, String>): String {
        return insertHstore(map)
    }

    /**
     * Crea una sentencia Insert usando hstore format
     * @param map
     * @return String
     */
    fun insertHstore(map: Map<String, String>): String {
        val builder = StringBuilder()

        map.keys.forEachIndexed { index, key ->
            var value = map[key]
            builder.append("$key => $value,")
        }

        return builder.toString().removeSuffix(",")
    }

    /**
     * crea una instrucción de actualización para uno o varios
     * elementos usando Hstore format
     * @param map
     * @return
     */
    fun updateHstore(map: Map<String, String>): String {
        val builder = StringBuilder()
        map.keys.forEachIndexed { index, key ->
            val value = map[key]
            builder.append("hstore('$key','${value}')")
            if(index > 0 && index < map.size){
                builder.append("||")
            }
        }

        return builder.toString()
    }
}
