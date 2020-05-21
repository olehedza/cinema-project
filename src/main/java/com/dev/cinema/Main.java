package com.dev.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");
    private static final MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService =
            (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static final MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);

    public static void main(String[] args) {
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        movie1.setTitle("Die hard");
        movie2.setTitle("Once upon a time in Hollywood");
        movieService.add(movie1);
        movieService.add(movie2);

        movieService.getAll().forEach(System.out::println);

        Movie movie3 = new Movie();
        movie3.setTitle("Joker");
        movie3 = movieService.add(movie3);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(200);
        cinemaHall = cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie3);
        movieSession.setShowTime(LocalDateTime
                .of(LocalDate.now(), LocalTime.of(20, 15)));
        movieSessionService.add(movieSession);

        movieSessionService.findAvailableSessions(movie3.getId(), LocalDate.now())
                .forEach(System.out::println);
    }
}
