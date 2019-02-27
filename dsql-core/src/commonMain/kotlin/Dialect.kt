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
import ddl.DropTableStatement
import dml.*

interface Dialect {

    fun <T: io.skerna.dsql.core.Table> build(statement: CreateTableStatement<T>): String
    fun <T: io.skerna.dsql.core.Table> build(statement: DropTableStatement<T>): String

    fun <T: io.skerna.dsql.core.Table> build(statement: SelectStatement<T>): String
    fun <T: io.skerna.dsql.core.Table, T2: io.skerna.dsql.core.Table> build(statement: Select2Statement<T, T2>): String
    fun <T: io.skerna.dsql.core.Table, T2: io.skerna.dsql.core.Table, T3: io.skerna.dsql.core.Table> build(statement: Select3Statement<T, T2, T3>): String
    fun <T: io.skerna.dsql.core.Table, T2: io.skerna.dsql.core.Table, T3: io.skerna.dsql.core.Table, T4: io.skerna.dsql.core.Table> build(statement: Select4Statement<T, T2, T3, T4>): String

    fun <T: io.skerna.dsql.core.Table> build(statement: InsertStatement<T>): String
    fun <T: io.skerna.dsql.core.Table> build(statement: UpdateStatement<T>): String
    fun <T: io.skerna.dsql.core.Table> build(statement: DeleteStatement<T>): String

}
