package com.backend.crud.controllers;

import com.backend.crud.model.Event;
import com.backend.crud.model.Tour;
import com.backend.crud.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Андрей on 14.12.2020.
 */
@RestController
@CrossOrigin
public class TourController {

    @Autowired
    private TourRepository tourRepository;

    @GetMapping("/tours/all")
    public List<Tour> getAllEvents() {
        List<Tour> tours = (List<Tour>)tourRepository.findAll();
        return tours;
    }

    @GetMapping(value = "/tours/all/{id}")
    public List<Tour> getAllEventsByPost(@PathVariable("id") Long categoryId) {
        List<Tour> tours = tourRepository.findAllByCategory(categoryId);
        return tours;
    }

    @PostMapping("/tours/create")
    public Tour createCategory(@RequestBody Tour tour) throws ParseException {
        Tour creatingTour = tourRepository.save(tour);
        return creatingTour;
    }

    @PutMapping ("/tours/update")
    public Tour updateCategory(@RequestBody Tour tour) throws Exception {
        Tour tourFromDb = tourRepository.findById(tour.getId()).get();
        if (tourFromDb != null) {
            tourFromDb = tour;
            tourRepository.save(tourFromDb);
        } else  {
            throw new Exception("This tour is not existed");
        }

        return tourFromDb;
    }

    @DeleteMapping(value = "/tours/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Tour deletingTour = tourRepository.findById(id).get();
        try {
            tourRepository.delete(deletingTour);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("It is impossible to delete this tour because it contains some tours");
        }
    }

}
