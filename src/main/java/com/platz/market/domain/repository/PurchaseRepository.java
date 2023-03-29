package com.platz.market.domain.repository;


import com.platz.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

//Es una interfaz porque es una especificacion que quiero definir
public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId); //aveces puede ser que se consulte a un cliente que no tiene compras por lo que la lista va dentro de un Optional

    //Metodo para guardar la compra
    Purchase save(Purchase purchase);

}
