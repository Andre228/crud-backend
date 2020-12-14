package com.backend.crud.controllers;

import com.backend.crud.model.Category;
import com.backend.crud.model.Event;
import com.backend.crud.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Андрей on 12.12.2020.
 */
@RestController
@CrossOrigin
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events/all")
    public List<Event> getAllEvents() {
        List<Event> events = (List<Event>)eventRepository.findAll();
        return events;
    }

    @GetMapping(value = "/events/all/{id}")
    public List<Event> getAllEventsByPost(@PathVariable("id") Long postId) {
        System.out.println(postId);
        List<Event> events = eventRepository.findAllByPost(postId);
        return events;
    }

    @PostMapping("/events/create")
    public Event createCategory(@RequestBody Event event) throws ParseException {
        Event creatingEvent = eventRepository.save(event);
        return creatingEvent;
    }

    @PutMapping ("/events/update")
    public Event updateCategory(@RequestBody Event event) throws Exception {
        Event eventFromDb = eventRepository.findById(event.getId()).get();
        if (eventFromDb != null) {
            eventFromDb = event;
            eventRepository.save(eventFromDb);
        } else  {
            throw new Exception("This event is not existed");
        }

        return eventFromDb;
    }

    @DeleteMapping(value = "/events/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Event deletingEvent = eventRepository.findById(id).get();
        try {
            eventRepository.delete(deletingEvent);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("It is impossible to delete this event because it contains some posts");
        }
    }
}
