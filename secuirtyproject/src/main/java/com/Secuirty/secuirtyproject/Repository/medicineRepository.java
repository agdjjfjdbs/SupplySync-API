package com.Secuirty.secuirtyproject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Secuirty.secuirtyproject.Entities.medicine;

public interface medicineRepository extends JpaRepository<medicine,Integer>{

    List<medicine> findByMedicineNameContainingIgnoreCase(String name);

}
