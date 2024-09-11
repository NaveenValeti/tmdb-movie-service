package tmdb.example.movieservice.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tmdb.example.movieservice.model.Movie;
import tmdb.example.movieservice.service.MovieService;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RequestMapping
@RestController
@Slf4j
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id){
        Movie movie = movieService.read(id);
        log.info("Returned movie with id:{}", id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/{movies}")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        Movie createdMovie = movieService.Create(movie);
        log.info("Created movie with id: {}",createdMovie.getId());
        return ResponseEntity.ok(createdMovie);

    }

    @PutMapping("/{id}")
    public void updateMovie(@PathVariable Long id, @RequestBody Movie movie ){
        movieService.Update(id,movie);
        log.info("Updated movie with id: {}", id);
    }

    @DeleteMapping("/{id}")
    public void DeleteMovie(@PathVariable Long id){
        movieService.Delete(id);
        log.info("Deleted movie with id: {}", id);
    }

}
