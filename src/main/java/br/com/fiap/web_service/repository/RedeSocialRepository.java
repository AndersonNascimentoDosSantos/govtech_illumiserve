package br.com.fiap.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.web_service.model.RedeSocial;

@Repository
public interface RedeSocialRepository extends JpaRepository<RedeSocial, Long> {

}
