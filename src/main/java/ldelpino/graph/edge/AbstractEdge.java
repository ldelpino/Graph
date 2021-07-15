/*
 * Copyright (C) 2021 Lazaro Cesar del Pino Olivera
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
package ldelpino.graph.edge;

import ldelpino.graph.vertex.Vertex;

/**
 * Permite establecer las funcionalidades basicas de una arista.
 * <p>
 * La clase implementa la mayoria de los metodos necesarios para la correcta
 * implementacion de una arista.</p>
 * <p>
 * La clase funciona como extension para la creacion de distintos tipos de
 * aristas, predeterminadamente existen dos tipos de aristas
 * {@link roj.redcorp.graph.edge.DefaultEdge} que es la clase que permite crear
 * una arista sin peso y {@link roj.redcorp.graph.edge.WeightedEdge} que permite
 * la creacion de aristas con peso.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see ldelpino.graph.edge.DefaultEdge
 * @see ldelpino.graph.edge.WeightedEdge
 * @see ldelpino.graph.vertex.Vertex
 *
 * @author Lazaro Cesar del Pino OLivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public abstract class AbstractEdge<T> implements Edge<T> {

    /**
     * El vertice que hace funcion de cola.
     * <p>
     * El vertice que hace funciona de cola es el vertice que conecta.</p>
     */
    private final Vertex<T> tail;

    /**
     * El vertice que hace funcion de cabeza.
     * <p>
     * El vertice que hace funcion de cola es el vertice al cual se conecta.
     */
    private final Vertex<T> head;

    /**
     * Crea una nueva arista con los vertices que ella enlaza.
     *
     * @param tail el vertice que hace funcion de cola.
     * @param head el vertice que hace funcion de cabeza.
     */
    public AbstractEdge(Vertex<T> tail, Vertex<T> head) {
        this.tail = tail;
        this.head = head;
    }

    /**
     *
     * @return
     */
    @Override
    public Vertex<T> getVertexHead() {
        return head;
    }

    /**
     *
     * @return
     */
    @Override
    public Vertex<T> getVertexTail() {
        return tail;
    }
}
