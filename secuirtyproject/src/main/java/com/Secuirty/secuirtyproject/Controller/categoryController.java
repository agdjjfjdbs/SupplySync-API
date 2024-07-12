package com.Secuirty.secuirtyproject.Controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Secuirty.secuirtyproject.Entities.category;
import com.Secuirty.secuirtyproject.Services.categoryService;

@RestController
@RequestMapping("category")
public class categoryController {
    @Autowired
    private categoryService categoryService;

    // create category
    @PostMapping("/createCategory")
    // localhost:8080/category/createCategory
    public String saveCategory(@RequestBody Map<String, String> request) {
        try {


            if (request.get("categoryName") == null || request.get("categoryName").trim().isEmpty() ) {
                return "Error:  empty required fields. Please ensure 'categoryName' are provided.";
            }

            String categoryName = request.get("categoryName").trim();

            category category = new category(categoryName);
            categoryService.addCategory(category);

            return "category added successfully";
        } catch (Exception e) {
            return "Error: An unexpected error occurred.";
        }
    }

    // delete category
    @DeleteMapping("{id}/delete")
    // localhost:8080/category/id/delete
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return "Deleted successfully";
    }

    // update category
    @PutMapping("/{id}/update")
    // localhost:8080/category/id/update
    public category updateCategory(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        String categoryName = request.get("categoryName");

        category category = new category(categoryName);
        category result = categoryService.updateCategory(category);
        return result;
    }
    // read (view) category

    @GetMapping("/read")
    // localhost:8080/category/read
    public List<category> getCategorys() {
        return categoryService.getCategorys();
    }
}