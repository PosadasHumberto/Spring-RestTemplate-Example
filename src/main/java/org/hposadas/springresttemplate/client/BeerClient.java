package org.hposadas.springresttemplate.client;

import org.hposadas.springresttemplate.model.BeerDTO;
import org.springframework.data.domain.Page;

public interface BeerClient {

    Page<BeerDTO> listBeers();
}
