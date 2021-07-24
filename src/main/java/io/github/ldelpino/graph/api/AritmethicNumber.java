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

/**
 * La clase permite establecer el concepto de un numero aritmetico.
 * <p>
 * Un numero aritmetico, es un objeto numerico que herede de la clase
 * {@link java.lang.Number}, que pudiese ser
 * {@link java.lang.Integer}, {@link java.lang.Float}, {@link java.lang.Double},
 * etc... La interfaz esta pensada para permitir operaciones aritmeticas con el
 * peso de las aristas y los vertices, donde si se desea el peso de las aristas
 * y/o vertices queden heredar de esta clase y reimplementar los metodo de suma,
 * resta y comparacion, pudiendo ser utilizados.</p>
 * <p>
 * por ejemplo el metodo
 * {@link ldelpino.graph.Graph#djisktra(java.lang.Object, java.lang.Object, java.util.List)}
 * devuelve el camino mas corto entre dos vertices, si el grafo es un grafo con
 * peso en las aristas y el peso de las mismas es instancia de esta interfaz,
 * entonces es posible sumar el peso de las arists y devolver un camino mas
 * preciso, en caso de que el grafo no posea con estas particularidades se
 * devuelve como camino mas corto la cantidad de saltos ue ocurren entre el
 * vertice inicial y el vertice final.</p>
 *
 * @param <T> el tipo de dato mas especifico del numero
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public interface AritmethicNumber<T extends Number> extends Comparable<T> {

    /**
     * realiza una suma aritmetica entre dos numeros.
     *
     * @param otherNumber el otro numero con el cual realizar la suma
     * aritmetica.
     * @return un numero que representa la suma aritmetica entre estos dos
     * numeros.
     */
    public T sum(T otherNumber);

    /**
     * Suma y almacena un numero aritmetico a este numero.
     *
     * @param otherAritmethicNumber el numero con el cual realizar la suma
     * aritmetica y la asignacion.
     */
    public void sumAndAsign(AritmethicNumber<T> otherAritmethicNumber);

    /**
     * realiza una resta aritmetica entre dos numeros.
     *
     * @param otherNumber el otro numero con el cual realizar la resta
     * aritmetica.
     * @return un numero que representa la resta aritmetica entre estos dos
     * numeros.
     */
    public T substract(T otherNumber);

    /**
     * Devuelve el numero como tipo de dato conocido.
     *
     * @return el numero encapsulado.
     */
    public T getNumber();

    /**
     * Incrementa el numero en una unidad.
     */
    public void increment();

    /**
     * Decrementa el numero en una unidad.
     */
    public void decrement();
}
