package br.com.condominio.app.modelo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.condominio.app.modelo.Titulo;

@Repository                        
public interface ITituloRepository extends JpaRepository<Titulo, Long>{
	
	//public List<Titulo> findAll();

	//public Page<Titulo> findAll(Pageable pageable);
	
	@Query(value = "  SELECT \n"
			+ "        tit.id ,\n"
			+ "        tit.created_by ,\n"
			+ "        tit.created_date,\n"
			+ "        tit.modified_by,\n"
			+ "        tit.modified_date,\n"
			+ "        tit.abatimento,\n"
			+ "        tit.dt_criacao,\n"
			+ "        tit.desconto,\n"
			+ "        tit.despesa,\n"
			+ "        tit.documento,\n"
			+ "        tit.dt_credito,\n"
			+ "        tit.iof,\n"
			+ "        tit.juros,\n"
			+ "        tit.outrasdespesa,\n"
			+ "        tit.status,\n"
			+ "        tit.unidade_id,\n"
			+ "        tit.valor,\n"
			+ "        tit.valorpago,\n"
			+ "        tit.dt_vencimento, \n"
			+ "        un.numero,\n"
			+ "        con.razaoSocial\n"
			+ "    FROM\n"
			+ "        titulos tit  LEFT JOIN unidades un on un.id = tit.unidade_id \n"
			+ "                     LEFT JOIN condominios con on con.id = un.codominio_id ",
		   countQuery = "SELECT count(*) FROM titulos",
		   nativeQuery = true)
		Page<Titulo> titulos(Pageable pageable);
	
	   List<Titulo> findByUnidadeIdAndUnidadeCondominioId(Long unidadeId, Long condominioId);
	   
	   Titulo findByUnidadeIdAndUnidadeCondominioIdAndVencimento(Long unidadeId, Long condominioId,LocalDate vencimento);
	   
	   
	   List<Titulo> findByUnidadeCondominioIdAndVencimento(Long condominioId,LocalDate vencimento);
	   
	   
	   
}
