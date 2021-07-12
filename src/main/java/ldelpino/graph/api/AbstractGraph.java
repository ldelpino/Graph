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
package ldelpino.graph.api;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import ldelpino.graph.Graph;
import ldelpino.graph.vertex.Vertex;

/**
 * Permite establecer las funcionalidades basicas de un grafo.
 * <p>
 * La clase implementa la mayoria de los metodos de los grafos, los metodos de
 * la interfaz {@link java.util.Collection}..</p>
 * <p>
 * La clase se establece como una coleccion de elementos, estos elementos son la
 * informacion de los vertices, al tratar de obtener los elementos del grafo
 * como {@link java.util.Collection} se pierde la estructura de vertices y
 * aristas.</p>
 *
 * @param <T> el tipo de dato de la informacion de los vertices.
 * @see ldelpino.graph.Graph
 * @see ldelpino.graph.api.DirectedGraph
 * @see ldelpino.graph.api.NotDirectedGraph
 * @see ldelpino.graph.vertex.Vertex
 * @see ldelpino.graph.edge.Edge
 *
 * @author Lazaro Cesar del Pino Olivera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public abstract class AbstractGraph<T> extends AbstractCollection<T> implements Graph<T> {

    /**
     * El conjunto de vertices del grafo.
     */
    protected Collection<Vertex<T>> vertices;

    /**
     * Permite la creacion de un nuevo grafo.
     */
    protected AbstractGraph() {
        super();
        this.vertices = new LinkedList<>();
    }

    @Override
    public int getVertexCount() {
        return getVertices0().size();
    }

    @Override
    public int getEdgesCount(T info) {
        if (existVertex(info)) {
            return getVertex(info).getEdgesCount();
        }
        return -1;
    }

    @Override
    public int getTotalEdgesCount() {
        int total = 0;
        total = getVertices0().stream().map((vertex) -> vertex.getEdgesCount())
                .reduce(total, Integer::sum);
        return total;
    }

    @Override
    public Collection<T> getVertices() {
        return getCollectionVertices(getVertices0());
    }

    @Override
    public boolean existVertex(T info) {
        return contains(info);
    }

    @Override
    public boolean removeVertex(T info) {
        if (existVertex(info)) {
            Vertex<T> vertex = getVertex(info);
            removeVertex(vertex);
            return true;
        }
        return false;
    }

    @Override
    public Collection<T> getAdjacents(T info) {
        return getCollectionVertices(getVertex(info).getAdjacents());
    }

    @Override
    public boolean existPath(T infoTail, T infoHead) {
        if (existVertex(infoTail) && existVertex(infoHead) && !infoTail.equals(infoHead)) {
            Vertex<T> vertexTail = getVertex(infoTail);
            Collection<Vertex<T>> visited = new LinkedList<>();
            visited.add(vertexTail);
            return existPathRecursive(vertexTail, getVertex(infoHead), visited);
        }
        return false;
    }

    @Override
    public void cleanGraph() {
        getVertices0().clear();
    }

    @Override
    public boolean existPathWithLength(T infoTail, T infoHead, int length) {
        boolean found = false;
        if (existVertex(infoTail) && existVertex(infoHead) && !infoTail.equals(infoHead)) {
            found = path(getVertex(infoTail), getVertex(infoHead), length);
        }
        return found;
    }

    public T vertexWithMoreAdjacents() {
        return vertexWithMoreAdjacents0().getInfo();
    }

    @Override
    public boolean existVerticesDisconnected() {
        return getVertices0().stream().anyMatch((v) -> (degree(v.getInfo()) == 0));
    }

    @Override
    public Collection<T> getDisconnectedVertices() {
        Collection<T> disconnected = new LinkedList<>();
        Iterator<Vertex<T>> iter = getVertices0().iterator();
        while (iter.hasNext()) {
            Vertex<T> next = iter.next();
            if (degree(next.getInfo()) == 0) {
                disconnected.add(next.getInfo());
            }
        }
        return disconnected;
    }

    @Override
    public Collection<T> removeDisconnectedVertices() {
        Collection<T> disconnected = getDisconnectedVertices();
        Iterator<T> iter = disconnected.iterator();
        while (iter.hasNext()) {
            T next = iter.next();
            removeVertex(next);
        }
        return disconnected;
    }

    @Override
    public Collection<T> removeVertexCascade(T info) {
        Collection<Vertex<T>> deleted = new LinkedList<>();
        if (existVertex(info)) {
            selectVerticesInCascadeRecurs(getVertex(info), deleted);
            Iterator<Vertex<T>> iter = deleted.iterator();
            while (iter.hasNext()) {
                removeVertex(iter.next());
            }
        }
        return getCollectionVertices(deleted);
    }

    @Override
    public AritmethicNumber djisktra(T infoTail, T infoHead, List<T> path) {
        if (existVertex(infoTail) && existVertex(infoHead)) {
            Vertex<T> initialVertex = getVertex(infoTail);
            Vertex<T> finalVertex = getVertex(infoHead);
            path.add(initialVertex.getInfo());
            LinkedList<Vertex<T>> visited = new LinkedList<>();
            visited.add(initialVertex);
            return djisktraAbstract(initialVertex, finalVertex, path, visited);
        }
        return null;
    }

    protected AritmethicNumber<Integer> djisktraAbstract(Vertex<T> initialVertex, Vertex<T> finalVertex,
            List<T> path, List<Vertex<T>> visited) {
        if (initialVertex.isAdjacents(finalVertex)) {
            path.add(finalVertex.getInfo());
            if (initialVertex.equals(finalVertex)) {
                return new IntegerAritmethicNumber(0);
            }
            return new IntegerAritmethicNumber(1);
        }
        Iterator<Vertex<T>> iterator = initialVertex.getAdjacents().iterator();
        AritmethicNumber<Integer> jump = new IntegerAritmethicNumber(0);
        boolean found = false;
        while (iterator.hasNext() && !found) {
            Vertex<T> next = iterator.next();
            if (!visited.contains(next)) {
                visited.add(next);
                jump.increment();
                path.add(next.getInfo());
                AritmethicNumber<Integer> recursiveJumps = djisktraAbstract(next, finalVertex, path, visited);
                found = recursiveJumps.getNumber() > 0;
                if (found) {
                    jump.sumAndAsign(recursiveJumps);
                } else {
                    jump.decrement();
                    path.remove(next.getInfo());
                }
            }
        }
        if (found) {
            return jump;
        }
        return new IntegerAritmethicNumber(-1);
    }

    @Override
    public boolean isEulerPath() {
        return eulerPath().isEmpty();
    }

    @Override
    public List<T> eulerPath() {
        LinkedList<T> camino = new LinkedList<>();
        if (!existVerticesDisconnected()) {
            boolean euler = true;
            Iterator<Vertex<T>> iter = getVertices0().iterator();
            while (iter.hasNext() && euler) {
                Vertex<T> next = iter.next();
                euler = degree(next.getInfo()) % 2 == 0;
                if (euler) {
                    camino.add(next.getInfo());
                }
            }
            if (!euler) {
                camino.clear();
            }
        }
        return camino;
    }

    @Override
    public int[][] getIncidenceMatrix() {
        int totalAristas = getTotalEdgesCount();
        int totalVertices = getVertexCount();
        int[][] matrix = new int[totalVertices][totalAristas];
        fillZeros(matrix);
        LinkedList<Vertex<T>> listVertex = new LinkedList<>(getVertices0());
        for (int i = 0; i < totalVertices; i++) {
            Vertex<T> vertexTail = listVertex.get(i);
            Iterator<Vertex<T>> adjacents = vertexTail.getAdjacents().iterator();
            while (adjacents.hasNext()) {
                Vertex<T> vertexHead = adjacents.next();
                int indexNext = listVertex.indexOf(vertexHead);
                matrix[i][indexNext] = 1;
            }
        }
        return matrix;
    }

    @Override
    public int[][] getAdyacentsMatrix() {
        int[][] matrix = new int[getVertexCount()][getVertexCount()];
        LinkedList<Vertex<T>> listVertex = new LinkedList<>(getVertices0());
        fillZeros(matrix);
        for (int i = 0; i < getVertexCount(); i++) {
            Vertex<T> vertexTail = listVertex.get(i);
            for (int j = 0; j < getVertexCount(); j++) {
                Vertex<T> vertexHead = listVertex.get(j);
                if (areAdjacents(vertexTail.getInfo(), vertexHead.getInfo())) {
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }

    /**
     * Devuelve el tamaño del grafo.
     * <p>
     * El tamaño del grafo es equivalente a la cantidad de vertices del
     * grafo.</p>
     *
     * @return la cantidad de vertices del grafo.
     */
    @Override
    public int size() {
        return getVertexCount();
    }

    /**
     * Establece si el objeto esta contenido en el grafo o no.
     * <p>
     * Un objeto esta contenido en el grafo si el objeto es instancia de vertice
     * o es igual a la informacion de algun vertice.</p>
     *
     * @param o el objeto a buscar dentro del grafo.
     * @return <b>true</b> si el objeto esta contenido en el grafo, de lo
     * contrario devuelve <b>false</b>.
     */
    @Override
    public boolean contains(Object o) {
        //busqueda del vertice
        if (o instanceof Vertex vertex) {
            return existVertex(vertex);
        }
        //busqueda de la informacion del vertice
        return getVertices().stream().anyMatch((vertex) -> (vertex.equals(o)));
    }

    /**
     * Remueve un objeto del grafo.
     * <p>
     * El metodo puede remover tanto un objeto de tipo
     * {@link ldelpino.graph.vertex.Vertex}, como la informacion de un vertice
     * del grafo, si esta se encuentra.</p>
     *
     * @param o el objeto a ser removido del grafo.
     * @return <b>true</b> si el objeto fue removido, de lo contrario devuelve
     * <b>false</b>.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        //busqueda del vertice
        if (o instanceof Vertex vertex) {
            if (existVertex(vertex)) {
                removeVertex(vertex);
                return true;
            }
        }
        //busqued de la informacion del vertice
        T vertex = null;
        Iterator<T> iter = getVertices().iterator();
        while (iter.hasNext() && vertex == null) {
            T next = iter.next();
            if (next.equals(o)) {
                vertex = next;
            }
        }
        if (vertex != null) {
            removeVertex(vertex);
        }
        return vertex != null;
    }

    /**
     * Devuelve el iterador con el recorrido del grafo.
     * <p>
     * El iterador recorre la infrmacion de cada uno de los vertices del
     * grafo.</p>
     *
     * @return el iterador del grafo.
     */
    @Override
    public Iterator<T> iterator() {
        return getVertices().iterator();
    }

    /**
     * Remueve los vertices del grafo.
     *
     * @param c la coleccion de vertices a eliminar.
     * @return <b>true</b> si todos los vertices fueron eliminados, de lo
     * contrario devuelve <b>false</b>.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        int eliminados = 0;
        Iterator<T> iter = getVertices().iterator();
        while (iter.hasNext()) {
            T next = iter.next();
            if (c.contains(next)) {
                removeVertex(next);
                eliminados++;
            }
        }
        return eliminados == c.size();
    }

    /**
     * Retiene los vertices del grafo y remueve los que no estan en la
     * coleccion.
     *
     * @param c los vertices a mantener en el grafo.
     * @return <b>true</b> si fueron eliminados todos los vertices que no estan
     * en la coleccion, de lo contrario devuelve <b>false</b>.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator<T> iter = getVertices().iterator();
        while (iter.hasNext()) {
            T vertex = iter.next();
            if (!c.contains(vertex)) {
                removeVertex(vertex);
            }
        }
        return getVertexCount() == c.size();
    }

    /**
     * Vacia el grafo completamente.
     * <p>
     * Vaciar el grafo conlleva eliminar todos los vertices y sus aristas.</p>
     *
     * @see ldelpino.graph.api.AbstractGraph#cleanGraph() .
     */
    @Override
    public void clear() {
        cleanGraph();
    }

    /**
     * Establece si dos grafos son iguales o no.
     * <p>
     * Dos grafos son iguales si el objeto es instancia de
     * {@link ldelpino.graph.Graph} y tienen los mismo vertices y las mismas
     * aristas.
     * </p>
     *
     * @param o el grafo con el cual comparar.
     * @return <b>true</b> si los dos grafos son iguales, de lo contrario
     * devuelve <b>false</b>.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Graph graph) {
            //permite optimizar la desigualdad antes de iniciar el recorrido
            if (graph.getVertexCount() == getVertexCount()
                    && graph.getTotalEdgesCount() == getTotalEdgesCount()
                    && graph.isDirected() == isDirected()
                    && graph.isWeightedVertex() == isWeightedVertex()
                    && graph.isWeigthedEdge() == isWeigthedEdge()
                    && graph.isCyclic() == isCyclic()
                    && graph.isEmpty() == isEmpty()
                    && graph.getVertices().equals(getVertices())) {
                Iterator<T> iterGraph = graph.iterator();
                boolean equals = true;
                while (iterGraph.hasNext() && equals) {
                    T next = iterGraph.next();
                    Vertex<T> vertex = getVertex(next);
                    equals = vertex != null;
                }
                return equals;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.vertices);
        return hash;
    }

    /**
     * Devuelve una cadena de caracteres que representa este grafo.
     * <pre>
     * La cadena de caracteres a devolver es equivalente a:
     * Graph es dirigido o no,
     * con peso o no,
     * si tiene peso en las aristas y/o en los vertices,
     * cantidad de vertices del grafo,
     * cantidad de aristas del grafo,
     * vertice con mayor cantidad de adyacentes,
     * cantidad de aristas del vertices con mayor cantidad de adyacentes,
     * nombre de la superclase.
     * </pre>
     *
     * @return la cadena de caracteres de este grafo.
     */
    @Override
    public String toString() {
        Vertex<T> vertexMoreAdjacents = vertexWithMoreAdjacents0();
        return "Graph " + (isDirected() ? "Directed " : "NotDirected ")
                + (isWeightedVertex() || isWeigthedEdge() ? "With weight " : "without weight ")
                + (isWeightedVertex() ? "in vertex." : "in edges.")
                + getVertexCount() + " vertices and " + getTotalEdgesCount() + " edges."
                + "vertex with more adjacents "
                + (vertexMoreAdjacents != null ? (vertexMoreAdjacents.toString() + " with "
                        + vertexMoreAdjacents.getEdgesCount() + " edges.") : "not exist.")
                + "SuperClass especifications: " + super.getClass().toString() + ".";
    }

    /**
     * Devuelve el vertice con mayor cantidad de adyacentes.
     *
     * @return el vertice con mayor cantidad de adyacentes.
     */
    protected Vertex<T> vertexWithMoreAdjacents0() {
        Vertex<T> vertex = null;
        for (Vertex<T> v : getVertices0()) {
            if (vertex == null) {
                vertex = v;
            } else {
                if (v.getEdgesCount() > vertex.getEdgesCount()) {
                    vertex = v;
                }
            }
        }
        return vertex;
    }

    /**
     * Devuelve la lista de vertices del grafo.
     *
     * @return la lista de vertices.
     */
    protected Collection<Vertex<T>> getVertices0() {
        return vertices;
    }

    /**
     * Establece la nueva coleccion de vertices del grafo.
     *
     * @param vertices la nueva coleccion de vertices.
     */
    protected void setVerticesList(Collection<Vertex<T>> vertices) {
        this.vertices = Objects.requireNonNull(vertices);
    }

    /**
     * Inserta un vertice en el grafo.
     *
     * @param vertex el vertice a ser insertado.
     * @return <b>true</b> si el vertice fue insertado correctamente, de lo
     * contrario devuelve <b>false</b>.
     */
    protected boolean insertVertex(Vertex<T> vertex) {
        if (!existVertex(vertex)) {
            return getVertices0().add(vertex);
        }
        return false;
    }

    /**
     * Establece si el vertice existe en el grafo o no..
     *
     * @param vertex el vertice a buscar en el grafo.
     * @return <b>true</b> si el vertice existe, de lo contrario devuelve
     * <b>false</b>.
     */
    protected boolean existVertex(Vertex vertex) {
        return getVertices0().stream().anyMatch((v) -> (v.equals(vertex)));
    }

    /**
     * Devuelve el vertice si existe.
     *
     * @param info la informacion con la cual obtener el vertice.
     * @return el vertice del grafo, si el vertice no existe entonces devuelve
     * <b>null</b>.
     */
    protected Vertex<T> getVertex(T info) {
        for (Vertex<T> vertex : getVertices0()) {
            if (vertex.getInfo().equals(info)) {
                return vertex;
            }
        }
        return null;
    }

    /**
     * Remueve un vertice del grafo
     *
     * @param vertex el vertice a ser removido.
     */
    protected void removeVertex(Vertex<T> vertex) {
        getVertices0().remove(vertex);
        getVertices0().forEach((v) -> {
            v.removeEdge(vertex);
        });
    }

    /**
     * Establece si existe un camino de longitud entre dos vertices.
     *
     * @param vertex1 el vertice inicial a buscar si existe un camino.
     * @param vertex2 el vertice final a buscar si existe un camino.
     * @param length la longitud del camino, si existe.
     * @return <b>true</b> si existe un camino de longitud, de lo contrario
     * devuelve <b>false</b>.
     */
    protected boolean path(Vertex<T> vertex1, Vertex<T> vertex2, int length) {
        boolean found = false;
        if (length == 1) {
            found = vertex1.isAdjacents(vertex2);
        } else {
            Iterator<Vertex<T>> iter = vertex1.getAdjacents().iterator();
            while (!found && iter.hasNext()) {
                Vertex<T> next = iter.next();
                found = path(next, vertex2, length - 1);
            }
        }
        return found;
    }

    /**
     * Devuelve una lista de vertices seleccionados en cascada a partir de un
     * vertice inicial.
     *
     * @param vertex el vertice inicial a partir del cual seleccionar sus
     * adyacentes.
     * @param selected la lista de vertices adyacentes almacenados
     * recursivamente, inicialmente la lista estara vacia.
     */
    protected void selectVerticesInCascadeRecurs(Vertex<T> vertex, Collection<Vertex<T>> selected) {
        if (!selected.contains(vertex)) {
            Iterator<Vertex<T>> iter = vertex.getAdjacents().iterator();
            selected.add(vertex);
            while (iter.hasNext()) {
                selectVerticesInCascadeRecurs(iter.next(), selected);
            }
        }
    }

    /**
     * Devuelve una coleccion con la informacion de los vertices a partir de
     * uuna coleccion de vertices.
     *
     * @param vertices la coleccion de vertices de la cual extraer la
     * informacion.
     * @return la coleccion de informacion de los vertices.
     */
    protected Collection<T> getCollectionVertices(Collection<Vertex<T>> vertices) {
        LinkedList<T> infos = new LinkedList<>();
        vertices.forEach((vertex) -> {
            infos.add(vertex.getInfo());
        });
        return infos;
    }

    /**
     * Devuelve la cantidad de adyacentes de un vertice.
     *
     * @param vertex el vertice con el cual saber su cantidad de adyacentes.
     * @return la cantidad de adyacentes del vertice.
     */
    protected int outDegree(Vertex<T> vertex) {
        return vertex.getEdgesCount();
    }

    private boolean existPathRecursive(Vertex<T> vertexTail, Vertex<T> vertexHead,
            Collection<Vertex<T>> visited) {
        boolean found = false;
        if (areAdjacents(vertexTail.getInfo(), vertexHead.getInfo())) {
            return true;
        }
        Iterator<Vertex<T>> iter = vertexTail.getAdjacents().iterator();
        while (iter.hasNext() && !found) {
            Vertex<T> next = iter.next();
            if (!visited.contains(next)) {
                if (next.isAdjacents(vertexHead)) {
                    return true;
                }
                visited.add(next);
                found = existPathRecursive(next, vertexHead, visited);
                if (!found) {
                    visited.remove(next);
                }
            }
        }
        return found;
    }

    private void fillZeros(int[][] matrix) {
        for (int[] m : matrix) {
            for (int e : m) {
                e = 0;
            }
        }
    }
}
