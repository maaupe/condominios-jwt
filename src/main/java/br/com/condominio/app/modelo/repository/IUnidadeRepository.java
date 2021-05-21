package br.com.condominio.app.modelo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.condominio.app.controller.dto.UnidadeDto;
import br.com.condominio.app.modelo.Unidade;

@Repository
public interface IUnidadeRepository extends JpaRepository<Unidade, Long> {

	
	//@Query("select u from Unidade u INNER JOIN where u.condominio_id:idCondominio")
	//public List<Unidade> findByUnidadesPorCondominio(@Param("idCondominio") Long idCondominio);
	//@Query("SELECT u FROM Unidade u inner join u.condominio where u.condominio.id =:idCondominio ")
	
	
	@Query(value=" SELECT u.id, u.numero, u.garagem, u.tipounidade, c.id AS condominioId, c.razaosocial AS razaoSocial, r.id as responsavelId, r.nome as nomeresponsavel "
			+ " FROM dbcondominio.unidades u "
			+ " LEFT JOIN dbcondominio.condominios c on u.condominio_id = c.id "
			+ " LEFT JOIN dbcondominio.responsaveis r on r.id  = u.responsavel_id "
			+ " WHERE c.id = :idCondominio ", nativeQuery = true)
	public List<Map<UnidadeDto,Object>> findByCondominioId(@Param("idCondominio") Long idCondominio);

	@Query(value= "SELECT u.id, u.numero, u.garagem, u.tipounidade, c.id AS condominioId, c.razaosocial AS razaoSocial, r.id as responsavelId, r.nome as nomeresponsavel "
			+ " FROM dbcondominio.unidades u "
			+ "JOIN dbcondominio.condominios c on u.condominio_id = c.id "
			+ "JOIN dbcondominio.responsaveis r on r.id  = u.responsavel_id ",nativeQuery = true) 
	public List<Map<UnidadeDto,Object>> todasAsUnidades();
	
	
	/*@Query(value="SELECT id, numero, garagem, tipounidade, condominioId, razaoSocial "
			+ "     FROM dbcondominio.vwUnidades", nativeQuery = true)
	public List<Map<UnidadeDto,Object>> todasAsUnidades();*/
	
	
	@Query(value="SELECT u.id, u.numero, u.garagem, u.tipounidade, c.id AS condominioId, c.razaosocial AS razaoSocial, r.id as responsavelId, r.nome as nomeresponsavel "
			+ " FROM dbcondominio.unidades u "
			+ "LEFT JOIN dbcondominio.condominios c on u.condominio_id = c.id "
			+ "LEFT JOIN dbcondominio.responsaveis r on r.id  = u.responsavel_id  WHERE u.id = :idUnidade", nativeQuery = true)
	public List<Map<UnidadeDto, Object>> buscadPorId(@Param("idUnidade") Long idUnidade);
	
}
