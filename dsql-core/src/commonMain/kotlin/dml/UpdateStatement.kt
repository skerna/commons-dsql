package dml

import io.skerna.dsql.core.Subject
import io.skerna.dsql.core.Table

class UpdateStatement<T: Table>(
        val assignments: Iterable<Assignment>,
        val subject: Subject<T>,
        val whereClause: WhereClause<T>?) {

    fun toString(dialect: io.skerna.dsql.core.Dialect): String {
        return dialect.build(this)
    }
}
