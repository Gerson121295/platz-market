package com.platz.market.persistence;

import com.platz.market.domain.Purchase;
import com.platz.market.domain.repository.PurchaseRepository;
import com.platz.market.persistence.crud.CompraCrudRepository;
import com.platz.market.persistence.entity.Compra;
import com.platz.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Repositorio que se va a comunicar directamente con la BD,
// para interactuar con la BD debemos crear una interfaz que extienda de crudRepository(clic en Crud y crear la interfaz CompraCrudRepository)
public class CompraRepository implements PurchaseRepository {

    //Inyectar CompraCrudRepository
    @Autowired //para inyectarlo
    private CompraCrudRepository compraCrudRepository;

    //Inyectar el mapper porque este Repositorio debe hablar en terminos de dominio
    @Autowired
    private PurchaseMapper mapper;


    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    //Obtener lista de compras por el cliente
    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras)); //map sirve para operar lo que viene en ese optional si es que viene si no hay nada en el optional no se ejecuta

    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase); //primero se debe de convertir a compra
        compra.getProductos().forEach(producto -> producto.setCompra(compra)); //en setCompra se le asigna la compra que se convirtio anteriormente //guardar la informacion en cascada para eso debemos de estar seguro que compra conoce a productos y los procutos conocen a que compra pertenecen
        return mapper.toPurchase(compraCrudRepository.save(compra));  //compra de la linea 35,36(las 2 anteriores)
    }
}
