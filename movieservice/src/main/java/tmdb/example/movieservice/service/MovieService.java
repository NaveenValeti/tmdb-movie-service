package tmdb.example.movieservice.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import tmdb.example.movieservice.exception.InvalidDataException;
import tmdb.example.movieservice.exception.NotFoundException;
import tmdb.example.movieservice.model.Movie;
import tmdb.example.movieservice.repo.MovieRepository;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    //CRUD Operation - Create, Read, Update, Delete
    public Movie Create(Movie movie){
        if(movie == null){
            throw new InvalidDataException("Invalid movie : null");
        }

        return movieRepository.save(movie);
    }

    public Movie read(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));
    }

    public void Update(Long id, Movie update){
        if(update==null || id==null){
            throw new InvalidDataException("Invalid movie:null");
        }

        //check if exists
        if(movieRepository.existsById(id)){
            Movie movie=(movieRepository.getReferenceById(id));
            movie.setName(update.getName());
            movie.setDirector(update.getDirector());
            movie.setActors(update.getActors());
            movieRepository.save(movie);

        }
        else{
            throw new NotFoundException("Movie not found with id :"+id);

        }
    }

    public void Delete(Long id){
        if (movieRepository.existsById(id)){
            movieRepository.deleteById(id);
        }
        else{
            throw new NotFoundException("Movie not found with id "+id);
        }

    }

}
