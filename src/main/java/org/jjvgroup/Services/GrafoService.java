package org.jjvgroup.Services;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jjvgroup.Entities.Estacao;
import org.jjvgroup.Repositories.GrafoRepository;

@ApplicationScoped
public class GrafoService {
    private MutableValueGraph<Estacao, Double> grafo;

    @Inject
    GrafoRepository grafoRepository;

    @PostConstruct
    public void init() {
        this.grafo = ValueGraphBuilder.undirected().build();
        grafoRepository.carregarGrafo(this.grafo);
    }
    public MutableValueGraph<Estacao, Double> getGrafo() {
        return grafo;
    }

}
