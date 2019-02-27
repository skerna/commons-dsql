package io.skerna.dsql.dialect.postgres

import io.skerna.dsql.core.Table
import ddl.Definition


class PostgresDefinition(column: Table.Column, val meta: Meta) : Definition.Column(column, meta.type) {

    fun primaryKey(autoIncrement: Boolean = false): PostgresDefinition {
        if (autoIncrement && meta.type != "INTEGER")
            throw UnsupportedOperationException("Autoincrement is only supported on INTEGER columns")

        return PostgresDefinition(column, meta.copy(primaryKeyConstraint = PrimaryKeyConstraint(autoIncrement)))
    }

    fun foreignKey(references: Table.Column): PostgresDefinition {
        return PostgresDefinition(column, meta.copy(foreignKeyConstraint = ForeignKeyConstraint(references)))
    }

    fun unique(): PostgresDefinition {
        return PostgresDefinition(column, meta.copy(uniqueConstraint = UniqueConstraint()))
    }

    fun notNull(): PostgresDefinition {
        return PostgresDefinition(column, meta.copy(notNullConstraint = NotNullConstraint()))
    }

    data class Meta(
        val type: String,
        val primaryKeyConstraint: PrimaryKeyConstraint? = null,
        val foreignKeyConstraint: ForeignKeyConstraint? = null,
        val uniqueConstraint: UniqueConstraint? = null,
        val notNullConstraint: NotNullConstraint? = null
    )

    class PrimaryKeyConstraint(val autoIncrement: Boolean = false)

    class ForeignKeyConstraint(val references: Table.Column)

    class UniqueConstraint

    class NotNullConstraint
}

fun integer(column: Table.Column): PostgresDefinition {
    return PostgresDefinition(column, PostgresDefinition.Meta(type = "INTEGER"))
}

fun real(column: Table.Column): PostgresDefinition {
    return PostgresDefinition(column, PostgresDefinition.Meta(type = "REAL"))
}

fun text(column: Table.Column): PostgresDefinition {
    return PostgresDefinition(column, PostgresDefinition.Meta(type = "TEXT"))
}

fun blob(column: Table.Column): PostgresDefinition {
    return PostgresDefinition(column, PostgresDefinition.Meta(type = "BLOB"))
}

fun hstore(column: Table.Column): PostgresDefinition {
    return PostgresDefinition(column, PostgresDefinition.Meta(type = "hstore"))
}
