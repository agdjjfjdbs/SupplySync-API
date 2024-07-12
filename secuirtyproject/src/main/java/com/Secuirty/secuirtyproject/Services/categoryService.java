package com.Secuirty.secuirtyproject.Services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Secuirty.secuirtyproject.Repository.categoryRepository;
import com.Secuirty.secuirtyproject.Entities.category;
@Service
public class categoryService {
    @Autowired
    private categoryRepository category;
    public List<com.Secuirty.secuirtyproject.Entities.category> getCategorys(){
        List<com.Secuirty.secuirtyproject.Entities.category> result = new ArrayList<com.Secuirty.secuirtyproject.Entities.category>();
        category.findAll().forEach(result::add); 
        return result;
    }

    //method to add category
    public com.Secuirty.secuirtyproject.Entities.category addCategory(com.Secuirty.secuirtyproject.Entities.category categoryObj){
        category.save(categoryObj);
        return categoryObj;
    }

    //method to delete category
    public boolean deleteCategory(Integer id){
        category.deleteById(id);
    
        return true;
    }

    //method to  update category
    public com.Secuirty.secuirtyproject.Entities.category updateCategory(com.Secuirty.secuirtyproject.Entities.category categoryObj){
        return category.save(categoryObj);
    }

    //method to get all category

    public List <category> getpatients(){
        List<category> result = new ArrayList<category>();
        category.findAll().forEach(result::add); 
        return result; 
    }
}
