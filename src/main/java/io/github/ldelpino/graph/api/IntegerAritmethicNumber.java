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
package io.github.ldelpino.graph.api;

import java.util.Objects;

/**
 * Permite la creacion de un numero entero aritmetico.
 * <p>
 * Un numero aritmetico es un objeto numerico que herede de la clase
 * {@link java.lang.Number}, que permite operaciones aritmetcas basicas.</p>
 * <p>
 * La clase permite la creacion de un numero entero no decimal, el cual se puede
 * sumar, restar, comparar, incrementar, decrementar, etc..</p>
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 */
public class IntegerAritmethicNumber implements AritmethicNumber<Integer> {

    private Integer weight;

    /**
     *
     * @param weight
     */
    public IntegerAritmethicNumber(Integer weight) {
        this.weight = weight;
    }

    /**
     *
     * @param otherNumber
     * @return
     */
    @Override
    public Integer sum(Integer otherNumber) {
        return weight + otherNumber;
    }

    /**
     *
     * @param otherNumber
     * @return
     */
    @Override
    public Integer substract(Integer otherNumber) {
        return weight - otherNumber;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getNumber() {
        return weight;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Integer o) {
        return weight.compareTo(o);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return weight.toString();
    }

    /**
     *
     * @param o
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.weight);
        return hash;
    }

    /**
     *
     */
    @Override
    public void increment() {
        weight += 1;
    }

    /**
     *
     */
    @Override
    public void decrement() {
        weight -= 1;
    }

    /**
     *
     * @param otherAritmethicNumber
     */
    @Override
    public void sumAndAsign(AritmethicNumber<Integer> otherAritmethicNumber) {
        weight = sum(otherAritmethicNumber.getNumber());
    }
}
