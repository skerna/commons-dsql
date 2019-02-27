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

import ddl.Definition
import dml.Assignment
import dml.Ordering
import dml.Projection


open class Table(private val name: String) {


    open inner class Column(val name: String) : Projection,CharSequence by name{

        val table: Table
            get() = this@Table

        val asc: Ordering
            get() = Ordering.By(this, true)

        val desc: Ordering
            get() = Ordering.By(this, false)

        operator fun invoke(column: Column): Assignment {
            return Assignment.Value(this, column)
        }

        operator fun invoke(value: String?): Assignment {
            return Assignment.Value(this, value)
        }

        operator fun invoke(value: CharSequence?): Assignment {
            return Assignment.Value(this, value.toString())
        }

        operator fun invoke(value: Number): Assignment {
            return Assignment.Value(this, value)
        }

        operator fun invoke(value: Boolean): Assignment {
            return Assignment.Value(this, value)
        }

        operator fun invoke(value: Enum<*>):Assignment{
            return Assignment.Value(this,value.toString())
        }
        override fun toString(): String {
            return name
        }

        open fun fetch():String{
            return table.name+""+name
        }
    }
    open inner class FunctionColun(name:String,val apply:()->String): Column(name){
        override fun fetch():String{
            return table.name+""+name+apply()
        }
    }



    override fun toString(): String {
        return name
    }

}

operator fun Table.Column.rangeTo(column: Table.Column): Iterable<Table.Column> {
    return listOf(this, column)
}

operator fun Iterable<Table.Column>.rangeTo(column: Table.Column): Iterable<Table.Column> {
    return this.plusElement(column)
}

operator fun Iterable<Ordering>.rangeTo(ordering: Ordering): Iterable<Ordering> {
    return if (this is Ordering) listOf(this, ordering) else this.plusElement(ordering)
}

operator fun Iterable<Assignment>.rangeTo(assignment: Assignment): Iterable<Assignment> {
    return if (this is Assignment) listOf(this, assignment) else this.plusElement(assignment)
}

operator fun Iterable<Definition>.rangeTo(definition: Definition): Iterable<Definition> {
    return if (this is Definition) listOf(this, definition) else this.plusElement(definition)
}
