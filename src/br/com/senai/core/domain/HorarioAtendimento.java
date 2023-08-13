package br.com.senai.core.domain;

import java.time.LocalTime;
import java.util.Objects;

public class HorarioAtendimento {

	private int id;
	private DiaSemana diaSemana;
	private LocalTime horaAbertura;
	private LocalTime horaFechamento;
	private Restaurante restaurante;
	
	public HorarioAtendimento(DiaSemana diaSemana, LocalTime horaAbertura, LocalTime horaFechamento,
			Restaurante restaurante) {
		this.diaSemana = diaSemana;
		this.horaAbertura = horaAbertura;
		this.horaFechamento = horaFechamento;
		this.restaurante = restaurante;
	}

	public HorarioAtendimento(int id, DiaSemana diaSemana, LocalTime horaAbertura, LocalTime horaFechamento,
			Restaurante restaurante) {
		this(diaSemana, horaAbertura, horaFechamento, restaurante);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public LocalTime getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(LocalTime horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public LocalTime getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(LocalTime horaFechamento) {
		this.horaFechamento = horaFechamento;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorarioAtendimento other = (HorarioAtendimento) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Dia da Semana - " + diaSemana + "\nHora de Abertura - " + horaAbertura + "\nHora de Fechamento - "
				+ horaFechamento;
	}
	
}
