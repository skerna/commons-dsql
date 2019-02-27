/*
 * Copyright (c)  2019  SKERNA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.skerna.dsql.core

import ddl.CreateTableStatement
import ddl.Definition
import ddl.DropTableStatement
import dml.*

open class Subject<T: Table> private constructor(val table: T) {

    override fun toString(): String {
        return table.toString()
    }

    class Over<T: Table>(table: T) : Subject<T>(table) {
        inline fun create(definition: (T) -> Iterable<Definition>): CreateTableStatement<T> {
            return CreateTableStatement(definition(table), this)
        }

        fun drop(): DropTableStatement<T> {
            return DropTableStatement(this)
        }
    }

    class From<T: Table>(table: T) : Subject<T>(table) {
        fun <T2: Table> join(table2: T2): Join2Clause<T, T2> {
            return Join2Clause(this, table2)
        }

        fun <T2: Table> outerJoin(table2: T2): Join2Clause<T, T2> {
            return Join2Clause(this, table2, JoinType.OUTER)
        }

        inline fun where(predicate: (T) -> Predicate): WhereClause<T> {
            return WhereClause(predicate(table), this)
        }

        inline fun groupBy(projection: (T) -> Iterable<Projection>): GroupClause<T> {
            return GroupClause(projection(table), this, null)
        }

        inline fun orderBy(order: (T) -> Iterable<Ordering>): OrderClause<T> {
            return OrderClause(order(table), this, null, null, null)
        }

        inline fun limit(limit: () -> String): LimitClause<T> {
            return LimitClause(
                    limit(),
                    this,
                    null,
                    null,
                    null,
                    null)
        }

        inline fun offset(offset: () -> String): OffsetClause<T> {
            return OffsetClause(
                    offset(),
                    limit { "-1" },
                    this,
                    null,
                    null,
                    null,
                    null)
        }

        inline fun select(projection: (T) -> Iterable<Projection>): SelectStatement<T> {
            return SelectStatement(
                    projection(table),
                    this,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null)
        }

        inline fun update(update: (T) -> Iterable<Assignment>): UpdateStatement<T> {
            return UpdateStatement(
                    update(table),
                    this,
                    null)
        }

        fun delete(): DeleteStatement<T> {
            return DeleteStatement(
                    this,
                    null
            )
        }
    }

    class Into<T: Table>(table: T) : Subject<T>(table) {
        inline fun insert(insert: (T) -> Iterable<Assignment>): InsertStatement<T> {
            return InsertStatement(insert(table), this)
        }
    }
}

fun <T: Table> over(table: T): Subject.Over<T> {
    return Subject.Over(table)
}

fun <T: Table> from(table: T): Subject.From<T> {
    return Subject.From(table)
}

fun <T: Table> into(table: T): Subject.Into<T> {
    return Subject.Into(table)
}
