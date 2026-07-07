package br.com.dio.dao;

import br.com.dio.BasicBasket;
import java.util.ArrayList;
import java.util.List;

public class BasicBasketDAO {

    private List<BasicBasket> stock = new ArrayList<>();

    public List<BasicBasket> addBatch(final List<BasicBasket> baskets) {
        stock.addAll(baskets);
        return baskets;
    }
}
