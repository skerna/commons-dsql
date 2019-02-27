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

interface Predicate

/**
 * Not expression
 */
class NotExpression(val param: Any?) : Predicate

fun not(predicate: Predicate): NotExpression {
    return NotExpression(predicate)
}

/**
 * And expression
 */
class AndExpression(val left: Any?, val right: Any?) : Predicate

infix fun Predicate.and(predicate: Predicate): AndExpression {
    return AndExpression(this, predicate)
}

/**
 * Or expression
 */
class OrExpression(val left: Any?, val right: Any?) : Predicate

infix fun Predicate.or(predicate: Predicate): OrExpression {
    return OrExpression(this, predicate)
}

/**
 * Equals expression
 */
class EqExpression(val left: Any?, val right: Any?) : Predicate


infix fun Table.Column.eq(column: Table.Column): EqExpression {
    return EqExpression(this, column)
}

infix fun Table.Column.eq(str: String?): EqExpression {
    return EqExpression(this, str)
}
infix fun Table.Column.eq(charSequence: CharSequence?): EqExpression {
    return EqExpression(this, charSequence.toString())
}

infix fun Table.Column.eq(num: Number): EqExpression {
    return EqExpression(this, num)
}

infix fun Table.Column.eq(flag: Boolean): EqExpression {
    return EqExpression(this, flag)
}

/**
 * Not equals expression
 */
class NeExpression(val left: Any?, val right: Any?) : Predicate

infix fun Table.Column.ne(column: Table.Column): NeExpression {
    return NeExpression(this, column)
}

infix fun Table.Column.ne(str: String?): NeExpression {
    return NeExpression(this, str)
}

infix fun Table.Column.ne(num: Number): NeExpression {
    return NeExpression(this, num)
}

infix fun Table.Column.ne(flag: Boolean): NeExpression {
    return NeExpression(this, flag)
}

/**
 * Less than expression
 */
class LtExpression(val left: Any?, val right: Any?) : Predicate

infix fun Table.Column.lt(column: Table.Column): LtExpression {
    return LtExpression(this, column)
}

infix fun Table.Column.lt(str: String?): LtExpression {
    return LtExpression(this, str)
}

infix fun Table.Column.lt(num: Number): LtExpression {
    return LtExpression(this, num)
}

/**
 * Less than or equal expression
 */
class LteExpression(val left: Any?, val right: Any?) : Predicate

infix fun Table.Column.lte(column: Table.Column): LteExpression {
    return LteExpression(this, column)
}

infix fun Table.Column.lte(str: String?): LteExpression {
    return LteExpression(this, str)
}

infix fun Table.Column.lte(num: Number): LteExpression {
    return LteExpression(this, num)
}

/**
 * Greater than expression
 */
class GtExpression(val left: Any?, val right: Any?) : Predicate

infix fun Table.Column.gt(column: Table.Column): GtExpression {
    return GtExpression(this, column)
}

infix fun Table.Column.gt(str: String?): GtExpression {
    return GtExpression(this, str)
}

infix fun Table.Column.gt(num: Number): GtExpression {
    return GtExpression(this, num)
}

/**
 * Greater than or equal expression
 */
class GteExpression(val left: Any?, val right: Any?) : Predicate

infix fun Table.Column.gte(column: Table.Column): GteExpression {
    return GteExpression(this, column)
}

infix fun Table.Column.gte(str: String?): GteExpression {
    return GteExpression(this, str)
}

infix fun Table.Column.gte(num: Number): GteExpression {
    return GteExpression(this, num)
}
