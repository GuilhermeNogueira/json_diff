package com.guilherme.json_diff.repositories;

import com.guilherme.json_diff.models.Difference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DifferenceRepository extends CrudRepository<Difference, Difference.Key> {

    Optional<Difference> getByKeyIdAndKeySide(Long id, Difference.Side side);
    List<Difference> getByKeyId(Long id);
}
