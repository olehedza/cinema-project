package com.dev.cinema.service.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao cartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        try {
            Ticket ticket = new Ticket();
            ticket.setMovieSession(movieSession);
            ticket.setUser(user);
            ShoppingCart cartForUpdate = getByUser(user);
            cartForUpdate.getTickets().add(ticket);
            ticketDao.add(ticket);
            cartDao.update(cartForUpdate);
        } catch (NullPointerException e) {
            throw new DataProcessingException("Can't add new movie session", e);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return cartDao.getByUser(user).orElse(null);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cartDao.add(cart);
    }
}
