package com.Secuirty.secuirtyproject.Entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import com.Secuirty.secuirtyproject.Entities.category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="medicine")
public class medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="medicine_id")
    Integer medicineId;

    @Column(name="medicine_name")
    String medicineName;

    @Column(name="medicine_description")
    String description;

    @Column(name="medicine_price")
    Integer price;

    // New field for storing photo
    @Lob
    @Column(name="medicine_photo")
    private byte[] photo;

    // Transient field to hold photo file during request processing
    @Transient
    private MultipartFile photoFile;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    category category;

    public medicine(String medicineName, String description, int price, int categoryId) {
        this.medicineName = medicineName;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    


}
