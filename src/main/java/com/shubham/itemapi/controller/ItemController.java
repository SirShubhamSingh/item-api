package com.shubham.itemapi.controller;

import com.shubham.itemapi.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin
@RestController
@RequestMapping("/api/items")
public class ItemController {

    // In-memory storage (Thread-safe List)
    private final List<Item> itemStore = new CopyOnWriteArrayList<>();

    // Constructor: Add dummy data for testing
    public ItemController() {
        itemStore.add(new Item(UUID.randomUUID().toString(), "Laptop", "High performance gaming laptop", 1200.00, "Electronics"));
        itemStore.add(new Item(UUID.randomUUID().toString(), "Coffee Mug", "Ceramic mug", 12.50, "Home"));
    }

//  1. Add a new item
    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        // Generate a unique ID
        item.setId(UUID.randomUUID().toString());
        
        // Save to in-memory store
        itemStore.add(item);
        
        // Return 201 Created
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

//  GET /api/items/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        Optional<Item> item = itemStore.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();

        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found with ID: " + id);
        }
    }

// 	GET /api/items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemStore);
    }
    
//  Health check endpoint
    @GetMapping("/")
    public String healthCheck() {
        return "Item API is running successfully!";
    }
}