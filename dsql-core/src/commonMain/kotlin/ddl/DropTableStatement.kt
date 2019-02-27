package ddl

import io.skerna.dsql.core.Subject
import io.skerna.dsql.core.Table

class DropTableStatement<T: Table>(
        val subject: Subject<T>) {

    fun toString(dialect: io.skerna.dsql.core.Dialect): String {
        return dialect.build(this)
    }
}
