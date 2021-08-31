package br.edu.ifsp.ptb.ra.exame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;
import br.edu.ifsp.ptb.ra.exame.model.ExameModel;
import br.edu.ifsp.ptb.ra.exame.repository.ExameRepository;

@Service
public class ExameService 
{
    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private UsuarioService usuarioService;

    public ExameModel novoExame(ExameDTO dto) 
    {
        UsuarioDTO usuario = usuarioService.buscaUsuarioPorEmail(dto.getEmail());

        return agendaNovoExame(new ExameModel(usuario, dto));
    }


    public ExameModel agendaNovoExame(ExameModel model)
    {
        return exameRepository.save(model);
//        return exameRepository.agendaNovoExame(usuario.getId(), idExterno, intervaloAfericaoPA, data);
    }

}
