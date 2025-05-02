package org.jjvgroup.Repositories;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.context.ApplicationScoped;
import org.jjvgroup.Entities.Estacao;

@ApplicationScoped
public class GraphProducer {

    @Produces
    public MutableValueGraph<Estacao, Double> produceGraph() {
        return ValueGraphBuilder.directed().build();
    }
}