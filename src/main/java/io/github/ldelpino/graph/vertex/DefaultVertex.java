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

import io.github.ldelpino.graph.edge.Edge;

/**
 * Permite la creacion y utilizacion de vertices sin pesos.
 * <p>
 * La clase permite la creacion de vertices sin peso en una estructura de datos
 * en forma de grafo.</p>
 * <p>
 * La clase no permite la reimplementacion del metodo
 * {@link ldelpino.graph.vertex.DefaultVertex#isWeighted()}, el metodo es
 * <b>final</b> para evitar incongruencias entre los vertices sin peso y los
 * vertices con peso, ya que en un mismo grafo no pueden existir vertices con
 * peso y vertices sin peso. Si se desea crear un vertice con peso debe
 * utilizarse la clase {@link io.github.ldelpino.graph.vertex.WeightedVertex} que
 * permite la creacion de vertices con peso.</p>
 *
 * @param <T> el tipo de dato de la informacion del vertice.
 * @see io.github.ldelpino.graph.vertex.Vertex
 * @see io.github.ldelpino.graph.vertex.AbstractVertex
 * @see io.github.ldelpino.graph.vertex.WeightedVertex
 * @see io.github.ldelpino.graph.edge.Edge
 * @see io.github.ldelpino.graph.Graph
 * 
 * @author Lazaro Cesar del Pino Olvera <elrojo960905@gmail.com>
 * @since jdk-16.0.1
 * @version 1.0.0
 */
public class DefaultVertex<T> extends AbstractVertex<T> {

    /**
     * Vlor de serializacion del vertice por defecto.
     */
    public static final long serialVersionUID = 1L;

    /**
     * Crea un nuevo vertice sin peso partir de la informacion del mismo.
     *
     * @param info la informacion a almacenar en el vertice.
     */
    public DefaultVertex(T info) {
        super(info);
    }

    /**
     *
     * @return
     */
    @Override
    public final boolean isWeighted() {
        return false;
    }
    
    /**
     *
     * @param head
     * @return
     */
    @Override
    public Edge<T> getEdge(Vertex<T> head) {
        return super.getEdge(head);
    }
}
