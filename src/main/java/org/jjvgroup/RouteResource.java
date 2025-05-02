package org.jjvgroup;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jjvgroup.DTOs.SetarRotasRequest;
import org.jjvgroup.Entities.Estacao;
import org.jjvgroup.Repositories.AEstrelaRepository;
import org.jjvgroup.Repositories.EstacaoRepository;
import org.jjvgroup.Repositories.GrafoRepository;

@Path("/rotas")
public class RouteResource {

    @Inject
    GrafoRepository grafoRepository;

    @Inject
    AEstrelaRepository aEstrelaRepository;

    @Inject
    EstacaoRepository estacaoRepository;

    @POST
    @Path("/setarRotas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setEstacoes(SetarRotasRequest request) {
        MutableValueGraph<Estacao, Double> grafo = ValueGraphBuilder.undirected().build();
        grafoRepository.carregarGrafo(grafo);

        for (Estacao estacao : grafo.nodes()) {
            if (request.estacaoOrigem.equalsIgnoreCase(estacao.getNome())) {
                request.usuario.setOrigem(estacao);
            }
        }

        for (Estacao estacao : grafo.nodes()) {
            if (request.estacaoDestino.equalsIgnoreCase(estacao.getNome())) {
                request.usuario.setDestino(estacao);
            }
        }

        aEstrelaRepository.calcularRota(request.usuario, grafo, estacaoRepository);

        return Response.status(Response.Status.OK).build();
    }
}