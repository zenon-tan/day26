package day26.lecture.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import day26.lecture.models.Tv;
import day26.lecture.repositories.TvShowRepo;

@Service
public class TvService {

    @Autowired
    private TvShowRepo tRepo;

    public List<Tv> findAllTvShowsByLang(String lang) {

        return tRepo.findTvShowsByLang(lang).stream()
        .map(i -> Tv.create(i))
        .toList();
        
    }

    public List<String> getAllTypes(String type) {

        return tRepo.getAllTypes();
        
    }

    public List<Tv> findAllTvShowsByType(String type) {

        return tRepo.findTvShowsByType(type).stream()
        .map(i -> Tv.create(i))
        .toList();
        
    }

    public Tv findShowById(int id) {

        Tv tv = Tv.create(tRepo.findTvShowsById(id));
        return tv;

    }


    
}
