package dtos;

// Record para enviar as informações essenciais de cada estação na rota calculada
public record EstacaoResumoDTO(String nome, String codigo) {
    // Adicione outros campos se precisar (ex: status, etc)
    // apenas certifique-se de que a entidade Estacao tenha como fornecer esses dados
}
