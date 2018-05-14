package com.ust.sagaeventsourcing.ms.initsrv.test.repositories;
 
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ust.sagaeventsourcing.ms.initsrv.test.models.kafkamsg;

@Repository
public interface KafkamsgRepository extends JpaRepository<kafkamsg,String> { 

	public List<kafkamsg> findAll();
	
	@Modifying // Necesario para ejecutar una query nativa de update/delete
	@Transactional // Necesario para ejecutar una query nativa de update/delete
	@Query(value = "delete FROM test.kafkamsg", nativeQuery = true)
	public void delete();

	@Query(value = "SELECT count(*) FROM test.kafkamsg where msg like %:status%", nativeQuery = true)
	public int getStatusCount(@Param("status")String status);
	
	/*
	// Ejemplo con query SQL estándar //
	// Es mejor preceder los parametros con @Param y hacerles referencia en la query // 
	@Query(value = "SELECT * FROM PERSONA WHERE ID = :id", nativeQuery = true)
	public Persona findFullPersonaById_SQL(@Param("id")int id);
	*/
	
}