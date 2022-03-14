package com.itransition.rpcserver.repository;

import com.itransition.rpcserver.entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FunctionRepository extends JpaRepository<Function, UUID> {
}
