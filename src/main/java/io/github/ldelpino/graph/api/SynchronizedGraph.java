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
package io.github.ldelpino.graph.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import io.github.ldelpino.graph.Graph;

/**
 * Permite la creacion de un grafo sincronizado de manera concurrente.
 * <p>
 * Las diferentes implementaciones de grafos no estan preparadas para multiples
 * accesos de manera concurrente. Para crear un grafo sincronizado dirigijase al
 * metodo
 * {@link roj.redcorp.graph.api.SynchronizedGraph#synchronizedGraph(roj.redcorp.graph.Graph)}.
 * La clase es utilizada como medio para su extension en caso de ser
 * necesario.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see io.github.ldelpino.graph.Graph
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public abstract class SynchronizedGraph<T> implements Graph<T>, Serializable {

    /**
     *
     */
    public static final long SERIALVERSIONUID = 1L;
    private final Graph<T> graph;
    private final Object mutex;

    /**
     * Crea un nuevo grafo sincronizado.
     *
     * @param graph el grafo a sincronizar.
     * @param mutex la clase que hace funcion de muteado.
     */
    public SynchronizedGraph(Graph<T> graph, Object mutex) {
        this.graph = graph;
        this.mutex = mutex;
    }

    /**
     * Crea un nuevo grafo sincronizado con esta clase como muteador.
     *
     * @param graph el grafo a ser sincronizado.
     */
    public SynchronizedGraph(Graph<T> graph) {
        this.graph = graph;
        this.mutex = this;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isDirected() {
        synchronized (mutex) {
            return graph.isDirected();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isWeightedVertex() {
        synchronized (mutex) {
            return graph.isWeightedVertex();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isWeigthedEdge() {
        synchronized (mutex) {
            return graph.isWeigthedEdge();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isCyclic() {
        synchronized (mutex) {
            return graph.isCyclic();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getVertexCount() {
        synchronized (mutex) {
            return graph.getVertexCount();
        }
    }

    /**
     *
     * @param info
     * @return
     */
    @Override
    public int getEdgesCount(T info) {
        synchronized (mutex) {
            return graph.getEdgesCount(info);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getTotalEdgesCount() {
        synchronized (mutex) {
            return graph.getTotalEdgesCount();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<T> getVertices() {
        synchronized (mutex) {
            return graph.getVertices();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean existVerticesDisconnected() {
        synchronized (mutex) {
            return graph.existVerticesDisconnected();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<T> getDisconnectedVertices() {
        synchronized (mutex) {
            return graph.getDisconnectedVertices();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<T> removeDisconnectedVertices() {
        synchronized (mutex) {
            return graph.removeDisconnectedVertices();
        }
    }

    /**
     *
     * @param info
     * @return
     */
    @Override
    public int degree(T info) {
        synchronized (mutex) {
            return graph.degree(info);
        }
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @return
     */
    @Override
    public boolean areAdjacents(T infoTail, T infoHead) {
        synchronized (mutex) {
            return graph.areAdjacents(infoTail, infoHead);
        }
    }

    /**
     *
     * @param info
     * @return
     */
    @Override
    public Collection<T> getAdjacents(T info) {
        synchronized (mutex) {
            return graph.getAdjacents(info);
        }
    }

    /**
     *
     * @param info
     * @return
     */
    @Override
    public T removeVertex(T info) {
        synchronized (mutex) {
            return graph.removeVertex(info);
        }
    }

    /**
     *
     * @param info
     * @return
     */
    @Override
    public Collection<T> removeVertexCascade(T info) {
        synchronized (mutex) {
            return graph.removeVertexCascade(info);
        }
    }

    /**
     *
     * @param info
     * @return
     */
    @Override
    public boolean existVertex(T info) {
        synchronized (mutex) {
            return graph.existVertex(info);
        }
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @return
     */
    @Override
    public boolean removeEdge(T infoTail, T infoHead) {
        synchronized (mutex) {
            return graph.removeEdge(infoTail, infoHead);
        }
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @return
     */
    @Override
    public boolean existPath(T infoTail, T infoHead) {
        synchronized (mutex) {
            return graph.existPath(infoTail, infoHead);
        }
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @param length
     * @return
     */
    @Override
    public boolean existPathWithLength(T infoTail, T infoHead, int length) {
        synchronized (mutex) {
            return graph.existPathWithLength(infoTail, infoHead, length);
        }
    }

    /**
     *
     * @param infoTail
     * @param infoHead
     * @param path
     * @return
     */
    @Override
    public AritmethicNumber djisktra(T infoTail, T infoHead, List<T> path) {
        synchronized (mutex) {
            return graph.djisktra(infoTail, infoHead, path);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEulerPath() {
        synchronized (mutex) {
            return graph.isEulerPath();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public List<T> eulerPath() {
        synchronized (mutex) {
            return graph.eulerPath();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int[][] getIncidenceMatrix() {
        synchronized (mutex) {
            return graph.getIncidenceMatrix();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int[][] getAdyacentsMatrix() {
        synchronized (mutex) {
            return graph.getAdyacentsMatrix();
        }
    }

    /**
     *
     */
    @Override
    public void cleanGraph() {
        synchronized (mutex) {
            graph.cleanGraph();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        synchronized (mutex) {
            return graph.size();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        synchronized (mutex) {
            return graph.isEmpty();
        }
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        synchronized (mutex) {
            return graph.contains(o);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        synchronized (mutex) {
            return graph.iterator();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        synchronized (mutex) {
            return graph.toArray();
        }
    }

    /**
     *
     * @param <T>
     * @param a
     * @return
     */
    @Override
    public <T> T[] toArray(T[] a) {
        synchronized (mutex) {
            return graph.toArray(a);
        }
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(T e) {
        synchronized (mutex) {
            return graph.add(e);
        }
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        synchronized (mutex) {
            return graph.remove(o);
        }
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        synchronized (mutex) {
            return graph.containsAll(c);
        }
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        synchronized (mutex) {
            return graph.addAll(c);
        }
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        synchronized (mutex) {
            return graph.removeAll(c);
        }
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        synchronized (mutex) {
            return graph.retainAll(c);
        }
    }

    /**
     *
     */
    @Override
    public void clear() {
        synchronized (mutex) {
            graph.clear();
        }
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        synchronized (mutex) {
            if (o instanceof Graph g) {
                return graph.equals(g);
            }
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
        hash = 71 * hash + Objects.hashCode(this.graph);
        hash = 71 * hash + Objects.hashCode(this.mutex);
        return hash;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return graph.toString();
    }
    
    /**
     * Permite la creacion de un grafo sincronizado.
     *
     * @param <T> el tipo de dato de la informacion de los vertices.
     * @param graph el grafo a convertir en un grafo sincronizado.
     * @param mutex el objeto que sirve como sincronizador.
     * @return el grafo sincronizado de forma concurrente.
     */
    public static <T> Graph<T> synchronizedGraph(Graph<T> graph, Object mutex) {
        return new SimpleSynchronizedGraph<>(graph, mutex);
    }

    /**
     * Permite la creacion de un grafo sincronizado.
     *
     * @param <T> el tipo de dato de la informacion de los vertices.
     * @param graph el grafo a convertir en un grafo sincronizado.
     * @return el grafo sincronizado de forma concurrente.
     */
    public static <T> Graph<T> synchronizedGraph(Graph<T> graph) {
        return new SimpleSynchronizedGraph<>(graph);
    }

    private static class SimpleSynchronizedGraph<T> extends SynchronizedGraph<T> {

        public SimpleSynchronizedGraph(Graph<T> graph, Object mutex) {
            super(graph, mutex);
        }

        public SimpleSynchronizedGraph(Graph<T> graph) {
            super(graph);
        }
    }
}
