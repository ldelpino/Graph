/*
 * Copyright (C) 2021 Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ldelpino.graph.api;

import java.util.Objects;

/**
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 */
public class IntegerAritmethicNumber implements AritmethicNumber<Integer> {

    private Integer weight;

    public IntegerAritmethicNumber(Integer weight) {
        this.weight = weight;
    }

    @Override
    public Integer sum(Integer otherNumber) {
        return weight + otherNumber;
    }

    @Override
    public Integer substract(Integer otherNumber) {
        return weight - otherNumber;
    }

    @Override
    public Integer getNumber() {
        return weight;
    }

    @Override
    public int compareTo(Integer o) {
        return weight.compareTo(o);
    }

    @Override
    public String toString() {
        return weight.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Integer integer) {
            return weight.equals(integer);
        }
        if (o instanceof IntegerAritmethicNumber aritmethicNumer) {
            return weight.equals(aritmethicNumer.getNumber());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.weight);
        return hash;
    }

    @Override
    public void increment() {
        weight += 1;
    }

    @Override
    public void decrement() {
        weight -= 1;
    }

    @Override
    public void sumAndAsign(AritmethicNumber<Integer> otherAritmethicNumber) {
        weight = sum(otherAritmethicNumber.getNumber());
    }
}
