package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.API_requestException;
import com.example.demo.model.WashPacks;
import com.example.demo.repository.WashPackRepository;

@Service
public class WashPackService {

    @Autowired
    private WashPackRepository wr;

    //To add a WashPack
    public WashPacks addWP(WashPacks washPacks){
        return wr.save(washPacks);
    }
    //To find all washpacks
    public List<WashPacks> findallWP(){
        return wr.findAll();
    }
    //To find one WashPack
    public ResponseEntity<WashPacks> findoneWP(String id){
        WashPacks wp=wr.findById(id).orElseThrow(() ->  new API_requestException("Washpack with ID -> "+id+" not found"));
        return ResponseEntity.ok(wp);
    }
    //To find washpack with washpack name for user's reciept
    public WashPacks findbyname(String name){
        return wr.findAll().stream().filter(x -> x.getName().contains(name)).findFirst().get();
    }
    //To delete a WashPack
    public ResponseEntity<Map<String,Boolean>> deleteWP(String id){
        WashPacks wp=wr.findById(id).orElseThrow(() ->  new API_requestException("Washpack with ID -> "+id+" not found, deletion failed"));
        wr.delete(wp);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Washpack Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    //To update a WashPack
    public ResponseEntity<WashPacks> updateWP(String id,WashPacks washPacks) {
        WashPacks existingWashPack = wr.findById(id).orElseThrow(() -> new API_requestException("Washpack with ID -> " + id + " not found, updating failed"));
        //ID won't be updated ever
        existingWashPack.setName(washPacks.getName());
        existingWashPack.setCost(washPacks.getCost());
        existingWashPack.setDescription(washPacks.getDescription());
        WashPacks response=wr.save(existingWashPack);
        return ResponseEntity.ok(response);
    }
}
