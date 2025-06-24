package com.mycompany.bookstore;

import com.mycompany.bookstore.models.Book;
import com.mycompany.bookstore.models.Author;
import com.mycompany.bookstore.models.Order;
import com.mycompany.bookstore.models.CartItem;
import com.mycompany.bookstore.models.Customer;
import java.util.*;

/**
 *
 * @author Kavish Weerasingha
 */
public class Database {
    public static Map<Integer, Book> books = new HashMap<>();
    public static Map<Integer, Author> authors = new HashMap<>();
    public static Map<Integer, Customer> customers = new HashMap<>();
    public static Map<Integer, CartItem> carts = new HashMap<>();
    public static Map<Integer, List<Order>> orders = new HashMap<>();
}
