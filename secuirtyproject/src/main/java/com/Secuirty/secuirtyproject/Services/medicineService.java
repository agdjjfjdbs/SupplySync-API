package com.Secuirty.secuirtyproject.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Secuirty.secuirtyproject.Entities.medicine;
import com.Secuirty.secuirtyproject.Repository.medicineRepository;

@Service
public class medicineService {

    @Autowired
    private medicineRepository medicineRepository;

    public List<medicine> getMedicines() {
        return medicineRepository.findAll();
    }

    public medicine addMedicine(medicine medicineObj) {
        return medicineRepository.save(medicineObj);
    }

    public boolean deleteMedicine(Integer id) {
        medicineRepository.deleteById(id);
        return true;
    }

    public medicine updateMedicine(medicine medicineObj) {
        return medicineRepository.save(medicineObj);
    }

    public List<medicine> searchByName(String name) {
        return medicineRepository.findByMedicineNameContainingIgnoreCase(name);
    }

    public Optional<medicine> findById(Integer id) {
        return medicineRepository.findById(id);
    }
}
