package tthttl.vetservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tthttl.vetservice.model.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {
}
