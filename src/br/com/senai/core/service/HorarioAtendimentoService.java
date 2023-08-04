package br.com.senai.core.service;

import java.util.List;

import br.com.senai.core.dao.DaoHorarioAtendimento;
import br.com.senai.core.dao.FactoryDao;
import br.com.senai.core.domain.HorarioAtendimento;

public class HorarioAtendimentoService {
	
	private DaoHorarioAtendimento dao;
	
	public HorarioAtendimentoService() {
		this.dao = FactoryDao.getIntance().getDaoHorarioAtendimento();
	}
	
	public void validar(HorarioAtendimento horarioNovo) {
		if (horarioNovo != null) {
			
			boolean isRestauranteInvalido = horarioNovo.getRestaurante() == null;
			
			if (isRestauranteInvalido) {
				throw new IllegalArgumentException("O restaurante é obrigatório");
			}
			
			boolean isDiaSemanaInvalido = horarioNovo.getDiaSemana() == null;
			
			if (isDiaSemanaInvalido) {
				throw new IllegalArgumentException("O dia da semana é obrigatório");
			}
			
			List<HorarioAtendimento> horariosExistentes = this.dao.listarPor(horarioNovo.getRestaurante());
			for (HorarioAtendimento horarioExistente : horariosExistentes) {
				validarHorario(horarioExistente, horarioNovo);
			}
			
		} else {
			throw new NullPointerException("O horário de atendimento não pode ser nulo");
		}
	}
	
	public void validarHorario(HorarioAtendimento horarioExistente, HorarioAtendimento horarioNovo) {
		if (horarioExistente != null && horarioNovo != null) {
			
			boolean isHorarioNovoInvalido = horarioNovo.getHoraAbertura() == null
					|| (horarioNovo.getHoraAbertura().isAfter(horarioExistente.getHoraAbertura()) && horarioNovo.getHoraAbertura().isBefore(horarioExistente.getHoraFechamento()))
					|| (horarioNovo.getHoraFechamento().isAfter(horarioExistente.getHoraAbertura()) && horarioNovo.getHoraFechamento().isBefore(horarioExistente.getHoraFechamento()))
					|| (horarioNovo.getHoraAbertura().isBefore(horarioExistente.getHoraAbertura()) && horarioNovo.getHoraFechamento().isAfter(horarioExistente.getHoraFechamento()));
			
			if (isHorarioNovoInvalido) {
				throw new IllegalArgumentException("O horário novo não pode entrar em conflito com outros horários existentes");
			}
			
		} else {
			throw new NullPointerException("Os horários de atendimento exitente e novo não podem ser nulos");
		}
	}
	
}
