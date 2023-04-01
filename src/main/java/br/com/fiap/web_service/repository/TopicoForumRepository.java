package br.com.fiap.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.web_service.model.TopicoForum;

@Repository
public interface TopicoForumRepository extends JpaRepository<TopicoForum, Long> {

}
