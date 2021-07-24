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
package io.github.ldelpino.graph.vertex;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import io.github.ldelpino.graph.edge.Edge;

/**
 * Permite establecer las funcionalidades basicas y generales de un vertice.
 * <p>
 * La clase implementa la mayoria de los metodos necesarios de un vertice.</p>
 * <p>
 * La clase funciona como extension para la creacion de distintos tipos de
 * vertices, predeterminadamente existen dos tipos de vertices
 * {@link io.github.ldelpino.graph.vertex.DefaultVertex} que es la clase que permite crear
 * un vertice sin peso y {@link io.github.ldelpino.graph.vertex.WeightedVertex} que
 * permite la creacion de vertices con peso.</p>
 * <p>
 * La clase es Serializable para permitir el almacenamiento de la informacion
 * del vertice.</p>
 *
 * @param <T> el tipo de dato de la informacion que almacena el vertice.
 * @see io.github.ldelpino.graph.vertex.Vertex
 * @see io.github.ldelpino.graph.vertex.DefaultVertex
 * @see io.github.ldelpino.graph.vertex.WeightedVertex
 * @see io.github.ldelpino.graph.edge.Edge
 * @see io.github.ldelpino.graph.Graph
 *
 * @author Lazaro Cesar del Pino Olvera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public abstract class AbstractVertex<T> implements Vertex<T>, Serializable {

    /**
     * La informacion del vertice
     * <p>
     * La informacion de un vertice es el contenido que el almacena.</p>
     */
    private T info;

    /**
     * La coleccion de aristas del vertice
     * <p>
     * Las aristas que el vertice almacena tienen una referencia a este vertice
     * haciendo funcion de cola, los vertices de las aristas que hacen funcion
     * de cabeza son los vertices adyacentes a este.</p>
     */
    private transient final Collection<Edge<T>> edges;

    /**
     * Crea un nuevo vertice a partir de su informacion.
     *
     * @param info la informacion del vertice a almacenar.
     */
    public AbstractVertex(T info) {
        this.info = info;
        this.edges = new LinkedList<>();
    }

    /**
     *
     * @return
     */
    @Override
    public T getInfo() {
        return info;
    }

    /**
     *
     * @param info
     */
    @Override
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Vertex<T>> getAdjacents() {
        LinkedList<Vertex<T>> vertices = new LinkedList<>();
        getEdges().stream().filter((e) -> (e.getVertexTail().equals(this))).forEachOrdered((e) -> {
            vertices.add(e.getVertexHead());
        });
        return vertices;
    }

    /**
     *
     * @return
     */
    @Override
    public int getAdjacentsCount() {
        int count = 0;
        count = getEdges().stream().filter((edge) -> (edge.getVertexTail()
                .equals(this))).map((_item) -> 1).reduce(count, Integer::sum);
        return count;
    }

    /**
     *
     * @return
     */
    @Override
    public int getEdgesCount() {
        return getEdges().size();
    }

    /**
     *
     * @param edge
     * @return
     */
    @Override
    public boolean insertEdge(Edge<T> edge) {
        if (!containsEdge(edge)) {
            return getEdges().add(edge);
        }
        return false;
    }

    /**
     *
     * @param vertex
     * @return
     */
    @Override
    public boolean removeEdge(Vertex<T> vertex) {
        boolean removido = false;
        if (isAdjacents(vertex)) {
            Iterator<Edge<T>> iter = getEdges().iterator();
            while (iter.hasNext()) {
                Edge next = iter.next();
                if (next.getVertexTail().equals(this) && next.getVertexHead().equals(vertex)) {
                    iter.remove();
                    removido = true;
                }
            }
        }
        return removido;
    }

    /**
     *
     * @param head
     * @return
     */
    @Override
    public boolean isAdjacents(Vertex<T> head) {
        return getEdges().stream().anyMatch((e) -> (e.getVertexTail().equals(this)
                && e.getVertexHead().equals(head)));
    }

    /**
     *
     */
    @Override
    public void disconnect() {
        edges.clear();
    }

    /**
     * Establece si dos objetos son iguales.
     * <p>
     * Dos vertices son iguales si el objeto es instancia de Vertex y la
     * informacion que almacena es igual.
     *
     * @param o el objeto a omparar con <b>este</b> vertice.
     * @return <b>true</b> si los dos objetos son iguales, de lo contrario
     * devuelve <b>false</b>.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex v) {
            return this.getInfo().equals(v.getInfo());
        }
        return info.equals(o);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.info);
        return hash;
    }

    /**
     * Devuelve una cadena de caracteres que representa este objeto de tipo
     * Vertex.
     * <pre>
     * Sintaxis:
     * Vertex: info.toString();
     * </pre>
     *
     * @return la represetacion en cadena de caracteres.
     */
    @Override
    public String toString() {
        return "Vertex: " + info.toString();
    }

    /**
     * Devuelve la arista enlazada con este vertice.
     *
     * @param head el vertice que hace funcion de cabeza en la coleccion de
     * aristas.
     * @return la arista si existe, de lo contrario devuelve <b>null</b>.
     */
    protected Edge<T> getEdge(Vertex<T> head) {
        for (Edge<T> e : getEdges()) {
            if (e.getVertexHead().equals(head) && e.getVertexTail().equals(this)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Devuelve una coleccion de aristas del vertice.
     *
     * @return la coleccion de aristaas.
     */
    protected Collection<Edge<T>> getEdges() {
        return edges;
    }

    /**
     * Establece si la arista esta contenida en la coleccion de aristas.
     *
     * @param edge la arista a comprobar si esta contenida o no en la coleccion
     * de aristas.
     * @return <b>true</b> si la arista esta contenida, de lo contrario devuelve
     * <b>false</b>.
     */
    protected boolean containsEdge(Edge<T> edge) {
        return edge.getVertexTail().equals(this) && getEdges().contains(edge);
    }

    /**
     * Permite la añadir una coleccion de aristas al vertice.
     *
     * @param edgesHead la coleccion de aristas del vertice.
     * @return <b>true</b> si todos las aristas fueron añadidas, de lo contrario
     * devuelve <b>false</b>.
     */
    protected boolean insertEdges(Collection<Edge<T>> edgesHead) {
        boolean contain = false;
        Iterator<Edge<T>> iter = edgesHead.iterator();
        while (iter.hasNext() && !contain) {
            contain = containsEdge(iter.next());
        }
        if (!contain) {
            return getEdges().addAll(edgesHead);
        }
        return false;
    }
}
