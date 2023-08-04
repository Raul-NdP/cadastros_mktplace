package br.com.senai.core.service;

import java.util.List;

import br.com.senai.core.dao.DaoHorarioAtendimento;
import br.com.senai.core.dao.DaoRestaurante;
import br.com.senai.core.dao.FactoryDao;
import br.com.senai.core.domain.Categoria;
import br.com.senai.core.domain.Restaurante;

public class RestauranteService {
	
	private DaoRestaurante daoRestaurante;
	
	private DaoHorarioAtendimento daoHorario;
	
	public RestauranteService() {
		this.daoRestaurante = FactoryDao.getIntance().getDaoRestaurante();
		this.daoHorario = FactoryDao.getIntance().getDaoHorarioAtendimento();
	}
	
	public void salvar(Restaurante restaurante) {
		this.validar(restaurante);
		
		boolean isJaInserido = restaurante.getId() > 1;
		if (isJaInserido) {
			this.daoRestaurante.alterar(restaurante);
		} else {
			this.daoRestaurante.inserir(restaurante);
		}
	}
	
	public void removerPor(int id) {
		if (id > 0) {
			
			int quantidadeHorarios = daoHorario.contarPor(id);
			
			boolean isExisteHorarioVinculado = quantidadeHorarios > 0;
			
			if (isExisteHorarioVinculado) {
				throw new IllegalArgumentException("Não foi possível excluir o restaurante pois "
						+ "existem " + quantidadeHorarios + " vinculados ao restaurante");
			}
			
			this.daoRestaurante.excluirPor(id);
		} else {
			throw new IllegalArgumentException("O id do restaurante deve ser maior que zero");
		}
	}
	
	public Restaurante buscarPor(int id) {
		if (id > 0) {
			Restaurante restauranteEncontrado = this.daoRestaurante.buscarPor(id);
			if (restauranteEncontrado == null) {
				throw new IllegalArgumentException("Não foi encontrado restaurante para o código informado");
			}
			return restauranteEncontrado;
		} else {
			throw new IllegalArgumentException("O id do restaurante deve ser maior que zero");
		}
	}
	
	public List<Restaurante> listarPor(String nome, Categoria categoria) {
		
		boolean isCategoriaInformada = categoria != null && categoria.getId() > 0;
		
		boolean isNomeInformado = nome != null && !nome.isBlank();
		
		if (!isCategoriaInformada && !isNomeInformado) {
			throw new IllegalArgumentException("Informe o nome e/ou categoria para listagem");
		}
		
		String filtroNome = "";
		
		if (isCategoriaInformada) {
			filtroNome = nome + "%";
		} else {
			filtroNome = "%" + nome + "%";
		}

		return this.daoRestaurante.listarPor(filtroNome, categoria);
	
	}
	
	public List<Restaurante> listarTodos() {
		return this.daoRestaurante.listarPor("%%", null);
	}
	
	public void validar(Restaurante restaurante) {
		if (restaurante != null) {
			
			if (restaurante.getEndereco() != null) {
				
				if (restaurante.getCategoria() != null && restaurante.getCategoria().getId() > 0) {
					
					boolean isNomeInvalido = restaurante.getNome() == null
							|| restaurante.getNome().isBlank()
							|| restaurante.getNome().length() > 250;
							
					if (isNomeInvalido) {
						throw new IllegalArgumentException("O nome do restaurante é obrigatório e não "
								+ "deve possuir mais de 250 caracteres");
					}
					
					boolean isDescricaoInvalida = restaurante.getDescricao() == null
							|| restaurante.getDescricao().isBlank();
					
					if (isDescricaoInvalida) {
						throw new IllegalArgumentException("A descrição do restaurante é obrigatória");
					}
					
					boolean isLogradouroInvalido = restaurante.getEndereco().getLogradouro() == null
							|| restaurante.getEndereco().getLogradouro().isBlank()
							|| restaurante.getEndereco().getLogradouro().length() > 200;
							
					if (isLogradouroInvalido) {
						throw new IllegalArgumentException("O logradouro do endereço do restaurante é obrigatório e não "
								+ "deve possuir mais de 200 caracteres");
					}
					
					boolean isCidadeInvalido = restaurante.getEndereco().getCidade() == null
							|| restaurante.getEndereco().getCidade().isBlank()
							|| restaurante.getEndereco().getCidade().length() > 80;
							
					if (isCidadeInvalido) {
						throw new IllegalArgumentException("A cidade do endereço do restaurante é obrigatória e não "
								+ "deve possuir mais de 80 caracteres");
					}
					
					boolean isBairroInvalido = restaurante.getEndereco().getBairro() == null
							|| restaurante.getEndereco().getBairro().isBlank()
							|| restaurante.getEndereco().getBairro().length() > 50;
							
					if (isBairroInvalido) {
						throw new IllegalArgumentException("O bairro do endereço do restaurante é obrigatório e não "
								+ "deve possuir mais de 50 caracteres");
					}
					
					boolean isComplementoInvalido = restaurante.getEndereco().getComplemento().length() > 250;
					
					if (isComplementoInvalido) {
						throw new NullPointerException("O logradouro do endereço do restaurante é obrigatório e não "
								+ "deve possuir mais de 250 caracteres");
					}
					
				} else {
					throw new NullPointerException("A categoria do restaurante não pode ser nula");
				}
				
			} else {
				throw new NullPointerException("O endereço do restaurante não pode ser nulo");
			}
			
			
		} else {
			throw new NullPointerException("O restaurante não pode ser nulo");
		}
	}
	
}