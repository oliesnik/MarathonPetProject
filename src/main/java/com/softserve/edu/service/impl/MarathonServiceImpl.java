package com.softserve.edu.service.impl;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.repository.MarathonRepository;
import com.softserve.edu.service.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarathonServiceImpl implements MarathonService {

    final private MarathonRepository marathonRepository;

    public MarathonServiceImpl(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Override
    public List<Marathon> getAll() {
        List<Marathon> marathons = marathonRepository.findAll();
        if (!marathons.isEmpty()) {
            return marathons;
        }
        return new ArrayList<>();
    }

    @Override
    public Marathon getMarathonById(Long id) {
        Optional<Marathon> marathon = marathonRepository.findById(id);
        return marathon.orElse(null);
    }

    @Override
    public Marathon createOrUpdate(Marathon entity) {
        if (entity.getId() != null) {
            Optional<Marathon> marathon = marathonRepository.findById(entity.getId());
            if (marathon.isPresent()) {
                Marathon newMarathon = marathon.get();
                newMarathon.setTitle(entity.getTitle());
                newMarathon = marathonRepository.save(newMarathon);
                return newMarathon;
            }
            // throw new MarathonNotFoundException("Marathon wit id = " + entity.getId() + " not found.");
        }
        entity = marathonRepository.save(entity);
        return entity;
    }

    @Override
    public void deleteMarathonById(Long id) {
        Optional.ofNullable(marathonRepository.findById(id))
                .ifPresentOrElse(marathon -> marathonRepository.deleteById(id), () -> {
                    //         throw new MarathonNotFoundException("Marathon wit id = " + id + " not found.");
                });
    }
}
