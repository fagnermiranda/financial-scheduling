package br.com.fagner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fagner.model.TransferScheduling;

public interface TransferSchedulingRepository extends JpaRepository<TransferScheduling, Long> {

}
