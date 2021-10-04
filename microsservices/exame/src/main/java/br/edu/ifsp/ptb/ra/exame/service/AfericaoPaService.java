package br.edu.ifsp.ptb.ra.exame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.edu.ifsp.ptb.ra.exame.dto.QuadroPaDTO;

@Service
public class AfericaoPaService
{

    @Autowired
    private RestTemplate httpClient;

    private final String MOCK_HOSTNAME = "f70fef0f-b3ef-4566-8e9d-d0ca86e439b4.mock.pstmn.io";

    public QuadroPaDTO getQuadroAfericoesPA(String idExternoExame)
    {
        ResponseEntity<QuadroPaDTO> response = httpClient.exchange(
                "https://" + MOCK_HOSTNAME + "/afericoes/" /* + idExternoExame */, HttpMethod.GET, null, QuadroPaDTO.class);

        return response.getBody();
    }

}
