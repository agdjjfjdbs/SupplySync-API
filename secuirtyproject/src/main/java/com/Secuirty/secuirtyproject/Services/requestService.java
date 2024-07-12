package com.Secuirty.secuirtyproject.Services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Secuirty.secuirtyproject.Repository.AcceptedRequestRepository;
import com.Secuirty.secuirtyproject.Repository.requestRepository;
import com.Secuirty.secuirtyproject.Entities.AcceptedRequest;
import com.Secuirty.secuirtyproject.Entities.request;

@Service
public class requestService {
    @Autowired
    private requestRepository requestRepository;
    @Autowired
    private AcceptedRequestRepository acceptedRequestRepository;


    //method to return all request
    public List<request> getRequests(){
        List<request> result = new ArrayList<request>();
        requestRepository.findAll().forEach(result::add); 
        return result;
    }

    //method to add request
    public request addRequest(request requestObj){
        requestRepository.save(requestObj);
        return requestObj;
    }

    //method to delete request
    public boolean deleteRequest(Integer id){
        requestRepository.deleteById(id);
    
        return true;
    }

    //method to  update request
    public com.Secuirty.secuirtyproject.Entities.request updateRequest(com.Secuirty.secuirtyproject.Entities.request requestObj){
        return requestRepository.save(requestObj);
    }

    //method to get all request

    public List <request> getrequests(){
        List<request> result = new ArrayList<request>();
        requestRepository.findAll().forEach(result::add); 
        return result; 
    }
    
    // Method to accept request
    public boolean acceptRequest(Integer requestId) {
        request request = requestRepository.findById(requestId).orElse(null);
        if (request != null) {
            // Move request to accepted requests
            AcceptedRequest acceptedRequest = new AcceptedRequest();
            acceptedRequest.setRequestDate(request.getRequestDate());
            acceptedRequest.setPatientName(request.getPatientName());
            acceptedRequest.setPatientId(request.getPatientId());
            acceptedRequest.setMedicineId(request.getMedicineId());
            acceptedRequestRepository.save(acceptedRequest);
            // Delete the request from original requests
            requestRepository.deleteById(requestId);
            return true;
        }
        return false;
    }

    // Method to decline request
    public boolean declineRequest(Integer requestId) {
        requestRepository.deleteById(requestId);
        return true;
    }
}
