package org.hposadas.springresttemplate.client;

import org.hposadas.springresttemplate.model.BeerDTO;
import org.hposadas.springresttemplate.model.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BeerClient {

    Page<BeerDTO> listBeers();
    Page<BeerDTO> listBeers(
            String beerName,
            BeerStyle beerStyle,
            Boolean showInventory,
            Integer pageNumber,
            Integer pageSize
    );

    BeerDTO getBeerById(UUID beerId);

    BeerDTO createBeer(BeerDTO newDTO);

    BeerDTO updateBeer(BeerDTO beerDTOToUpdate);

    void deleteBeer(UUID beerId);
}
