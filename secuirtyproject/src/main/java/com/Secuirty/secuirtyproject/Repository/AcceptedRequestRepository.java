package com.Secuirty.secuirtyproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Secuirty.secuirtyproject.Entities.AcceptedRequest;

@Repository
public interface AcceptedRequestRepository extends JpaRepository<AcceptedRequest, Integer> {
}
