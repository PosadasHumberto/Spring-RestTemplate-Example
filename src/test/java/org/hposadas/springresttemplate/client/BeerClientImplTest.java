package org.hposadas.springresttemplate.client;

import org.hposadas.springresttemplate.model.BeerDTO;
import org.hposadas.springresttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    //atributos
    @Autowired
    BeerClientImpl beerClient;

    //métodos

    @Test
    void listBeersNoParams() {
        beerClient.listBeers();
    }
    @Test
    void listBeersWithBeerName() {
        beerClient.listBeers(
                "ALE",null,null,null,null
        );
    }

    @Test
    void getBeerById() {
        Page<BeerDTO> listBeerDTO = beerClient.listBeers();

        BeerDTO beer = listBeerDTO.getContent().get(0);

        BeerDTO beerById = beerClient.getBeerById(beer.getId());

        assertNotNull(beerById);
    }

    @Test
    void testCreateBeer() {

        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("18.99"))
                .beerName("Mango Bobs")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDTO savedBeerDTO = beerClient.createBeer(newDTO);
        assertNotNull(savedBeerDTO);
    }

    @Test
    void testUpdateBeer() {

        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("18.99"))
                .beerName("Mango Bobs 2")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDTO savedBeerDTO = beerClient.createBeer(newDTO);

        final String newName = "Mango Bobs 3";
        savedBeerDTO.setBeerName(newName);
        BeerDTO updatedBeer = beerClient.updateBeer(savedBeerDTO);

        assertEquals(newName, updatedBeer.getBeerName());
    }

    @Test
    void deleteBeer() {

        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("18.99"))
                .beerName("Beer to Delete")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDTO beerDTO = beerClient.createBeer(newDTO);

        beerClient.deleteBeer(beerDTO.getId());

        assertThrows(HttpClientErrorException.class, () -> {
            beerClient.getBeerById(beerDTO.getId());
        });

    }
}