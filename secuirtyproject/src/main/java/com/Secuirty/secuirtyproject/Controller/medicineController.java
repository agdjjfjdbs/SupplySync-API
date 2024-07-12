package com.Secuirty.secuirtyproject.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.Secuirty.secuirtyproject.Entities.category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Secuirty.secuirtyproject.Entities.medicine;
import com.Secuirty.secuirtyproject.Services.medicineService;

@RestController
@RequestMapping("/medicine")
public class medicineController {

    @Autowired
    private medicineService medicineService;

    @PostMapping("/addMedicine")
    public ResponseEntity<String> saveMedicine(@RequestParam("file") MultipartFile file, @RequestParam Map<String, String> request) {
        try {
            if (!request.containsKey("medicineName") || request.get("medicineName").trim().isEmpty() ||
                    !request.containsKey("description") || request.get("description").trim().isEmpty() ||
                    !request.containsKey("price") || request.get("price").trim().isEmpty() ||
                    !request.containsKey("categoryId") || request.get("categoryId").trim().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        "Error: Missing or empty required fields. Please ensure 'medicineName', 'description', 'price', and 'categoryId' are provided.");
            }
            if (file == null || file.isEmpty()) {
                return ResponseEntity.badRequest().body("Error: File is empty or null.");
            }

            String medicineName = request.get("medicineName");
            String description = request.get("description");

            int price;
            int categoryId;
            try {
                price = Integer.parseInt(request.get("price"));
                categoryId = Integer.parseInt(request.get("categoryId"));
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body(
                        "Error: Invalid input format for 'price' or 'categoryId'. Please enter valid numbers.");
            }

            medicine medicine = new medicine(medicineName, description, price, categoryId);

            // Handle photo upload
            try {
                medicine.setPhoto(file.getBytes());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error: Failed to upload photo.");
            }

            medicineService.addMedicine(medicine);

            return ResponseEntity.ok("Medicine added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: An unexpected error occurred.");
        }
    }

    // delete medicine
    @DeleteMapping("/{id}/delete")
    public String deleteMedicine(@PathVariable Integer id) {
        medicineService.deleteMedicine(id);
        return "Deleted successfully";
    }

    // update medicine
    @PutMapping("/{id}/update")

    public ResponseEntity<String> updateMedicine(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        try {
            if (!request.containsKey("medicineName") || request.get("medicineName").trim().isEmpty() ||
                    !request.containsKey("medicineDescription") || request.get("medicineDescription").trim().isEmpty()
                    ||
                    !request.containsKey("medicinePrice") || request.get("medicinePrice").trim().isEmpty() ||
                    !request.containsKey("categoryId") || request.get("categoryId").trim().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        "Error: Missing or empty required fields. Please ensure 'medicineName', 'medicineDescription', 'medicinePrice', and 'categoryId' are provided.");
            }

            String medicineName = request.get("medicineName");
            String description = request.get("medicineDescription");

            int price;
            int categoryId;
            try {
                price = Integer.parseInt(request.get("medicinePrice"));
                categoryId = Integer.parseInt(request.get("categoryId"));
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body(
                        "Error: Invalid input format for 'medicinePrice' or 'categoryId'. Please enter valid numbers.");
            }

            medicine medicine = new  medicine( medicineName, description, price, categoryId);
            medicine updatedMedicine = medicineService.updateMedicine(medicine);
            if (updatedMedicine != null) {
                return ResponseEntity.ok("Medicine updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error: Medicine with ID " + id + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: An unexpected error occurred.");
        }
    }
    // read (view) medicine
    @GetMapping("/read")
    public List<medicine> getMedicines() {
        return medicineService.getMedicines();
    }

    // search by name
    @GetMapping("/search")
    public ResponseEntity<List<medicine>> searchMedicineByName(@RequestParam String name) {
        try {
            List<medicine> medicines = medicineService.searchByName(name);
            return ResponseEntity.ok(medicines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
