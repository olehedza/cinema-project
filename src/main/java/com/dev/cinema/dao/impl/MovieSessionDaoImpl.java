package com.dev.cinema.dao.impl;

import java.time.LocalDate;
import java.util.List;
import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> getAll(Long movieId, LocalDate date) {
        return null;
    }

    @Override
    public MovieSession add(MovieSession session) {
        return null;
    }
}
