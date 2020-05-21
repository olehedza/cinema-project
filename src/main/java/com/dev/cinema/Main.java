package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        movie1.setTitle("Die hard");
        movie2.setTitle("Once upon a time in Hollywood");
        MovieService movieService = (MovieService) injector
                .getInstance(MovieService.class);
        movieService.add(movie1);
        movieService.add(movie2);

        movieService.getAll().forEach(System.out::println);
    }
}
