package br.edu.ifsp.ptb.ra.exame.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;
import br.edu.ifsp.ptb.ra.exame.repository.ExameRepository;

@Service
public class ExameService 
{
    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private UsuarioService usuarioService;

    public void novoExame(ExameDTO dto) 
    {
        UsuarioDTO usuario = usuarioService.buscaUsuarioPorEmail(dto.getEmail());

        agendaNovoExame(usuario, dto.getData());
    }


    public void agendaNovoExame(UsuarioDTO usuario, LocalDateTime data) 
    {
//        exameRepository.agendaNovoExame(usuario, data);
    }

}
