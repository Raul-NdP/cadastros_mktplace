package br.com.senai.core.dao;

import br.com.senai.core.dao.postgresql.DaoPostgresqlCategoria;
import br.com.senai.core.dao.postgresql.DaoPostgresqlHorarioAtendimento;
import br.com.senai.core.dao.postgresql.DaoPostgresqlRestaurante;

public class FactoryDao {
	
	private static FactoryDao instance;
	
	private FactoryDao() {}
	
	public DaoCategoria getDaoCategoria() {
		return new DaoPostgresqlCategoria();
	}
	
	public DaoRestaurante getDaoRestaurante() {
		return new DaoPostgresqlRestaurante();
	}
	
	public DaoHorarioAtendimento getDaoHorarioAtendimento() {
		return new DaoPostgresqlHorarioAtendimento();
	}
	
	public static FactoryDao getIntance() {
		if (instance == null) {
			return new FactoryDao();
		}
		return instance;
	}
	
}
