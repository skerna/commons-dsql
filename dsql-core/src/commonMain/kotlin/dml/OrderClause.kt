package dml

import io.skerna.dsql.core.Subject
import io.skerna.dsql.core.Table

class OrderClause<T: Table>(
        val orderings: Iterable<Ordering>,
        val subject: Subject<T>,
        val whereClause: WhereClause<T>?,
        val groupClause: GroupClause<T>?,
        val havingClause: HavingClause<T>?) {

    inline fun limit(limit: () -> Any): LimitClause<T> {
        return LimitClause(
                limit(),
                subject,
                whereClause,
                this,
                groupClause,
                havingClause)
    }

    inline fun offset(offset: () -> Any): OffsetClause<T> {
        return OffsetClause(
                offset(),
                limit { "-1" },
                subject,
                whereClause,
                this,
                groupClause,
                havingClause)
    }

    inline fun select(projection: (T) -> Iterable<Projection>): SelectStatement<T> {
        return SelectStatement(
                projection(subject.table),
                subject,
                whereClause,
                this,
                null,
                null,
                groupClause,
                havingClause)
    }

}

class Order2Clause<T: Table, T2: Table>(
        val orderings: Iterable<Ordering>,
        val joinOn2Clause: JoinOn2Clause<T, T2>,
        val where2Clause: Where2Clause<T, T2>?,
        val group2Clause: Group2Clause<T, T2>?,
        val having2Clause: Having2Clause<T, T2>?) {

    inline fun limit(limit: () -> Any): Limit2Clause<T, T2> {
        return Limit2Clause(
                limit(),
                joinOn2Clause,
                where2Clause,
                this,
                group2Clause,
                having2Clause)
    }

    inline fun offset(offset: () -> Any): Offset2Clause<T, T2> {
        return Offset2Clause(
                offset(),
                limit { "-1" },
                joinOn2Clause,
                where2Clause,
                this,
                group2Clause,
                having2Clause)
    }

    inline fun select(projection: (T, T2) -> Iterable<Projection>): Select2Statement<T, T2> {
        return Select2Statement(
                projection(joinOn2Clause.subject.table, joinOn2Clause.table2),
                joinOn2Clause,
                where2Clause,
                this,
                null,
                null,
                group2Clause,
                having2Clause)
    }

}

class Order3Clause<T: Table, T2: Table, T3: Table>(
        val orderings: Iterable<Ordering>,
        val joinOn3Clause: JoinOn3Clause<T, T2, T3>,
        val where3Clause: Where3Clause<T, T2, T3>?,
        val group3Clause: Group3Clause<T, T2, T3>?,
        val having3Clause: Having3Clause<T, T2, T3>?) {

    inline fun limit(limit: () -> Any): Limit3Clause<T, T2, T3> {
        return Limit3Clause(
                limit(),
                joinOn3Clause,
                where3Clause,
                this,
                group3Clause,
                having3Clause)
    }

    inline fun offset(offset: () -> Any): Offset3Clause<T, T2, T3> {
        return Offset3Clause(
                offset(),
                limit { "-1" },
                joinOn3Clause,
                where3Clause,
                this,
                group3Clause,
                having3Clause)
    }

    inline fun select(projection: (T, T2, T3) -> Iterable<Projection>): Select3Statement<T, T2, T3> {
        return Select3Statement(
                projection(joinOn3Clause.joinOn2Clause.subject.table, joinOn3Clause.joinOn2Clause.table2, joinOn3Clause.table3),
                joinOn3Clause,
                where3Clause,
                this,
                null,
                null,
                group3Clause,
                having3Clause)
    }

}

class Order4Clause<T: Table, T2: Table, T3: Table, T4: Table>(
        val orderings: Iterable<Ordering>,
        val joinOn4Clause: JoinOn4Clause<T, T2, T3, T4>,
        val where4Clause: Where4Clause<T, T2, T3, T4>?,
        val group4Clause: Group4Clause<T, T2, T3, T4>?,
        val having4Clause: Having4Clause<T, T2, T3, T4>?) {

    inline fun limit(limit: () -> Any): Limit4Clause<T, T2, T3, T4> {
        return Limit4Clause(
                limit(),
                joinOn4Clause,
                where4Clause,
                this,
                group4Clause,
                having4Clause)
    }

    inline fun offset(offset: () -> Any): Offset4Clause<T, T2, T3, T4> {
        return Offset4Clause(
                offset(),
                limit { "-1" },
                joinOn4Clause,
                where4Clause,
                this,
                group4Clause,
                having4Clause)
    }

    inline fun select(projection: (T, T2, T3, T4) -> Iterable<Projection>): Select4Statement<T, T2, T3, T4> {
        return Select4Statement(
                projection(
                        joinOn4Clause.joinOn3Clause.joinOn2Clause.subject.table,
                        joinOn4Clause.joinOn3Clause.joinOn2Clause.table2,
                        joinOn4Clause.joinOn3Clause.table3,
                        joinOn4Clause.table4
                ),
                joinOn4Clause,
                where4Clause,
                this,
                null,
                null,
                group4Clause,
                having4Clause)
    }

}

