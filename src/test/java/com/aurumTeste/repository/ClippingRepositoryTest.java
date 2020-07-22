package com.aurumTeste.repository;

import com.aurumTeste.model.Clipping;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class ClippingRepositoryTest {

    @Autowired
    private ClippingRepository repository;

    @Test public void
    createShouldPersistData() {
        Clipping c = new Clipping();
        c.setViewed(false);
        repository.save(c);
        Assertions.assertThat(repository.count()).isEqualTo(1);
        Assertions.assertThat(c.getId()).isEqualTo(1);
    }

    @Test public void
    deleteShouldRemoveData(){
        Clipping c = new Clipping();
        c.setViewed(false);
        repository.save(c);
        repository.delete(c);
        Assertions.assertThat(repository.count()).isEqualTo(0);
        Assertions.assertThat(repository.findById(c.getId())).isEqualTo(Optional.empty());
    }

    @Test public void
    findByIdShouldReturnData(){
        Clipping c = new Clipping();
        c.setViewed(false);
        repository.save(c);
        Clipping c2 = repository.findById(c.getId()).get();
        Assertions.assertThat(repository.count()).isEqualTo(1);
        Assertions.assertThat(c2).isEqualTo(c);
    }

    @Test public void
    findAllShouldReturnAllDataSet() {
        List<Clipping> clippings = new ArrayList<>();
        for(int i=0; i < 4; i++) {
            Clipping c = new Clipping();
            c.setViewed(false);
            c.setClippingMatter("c"+ i);
            clippings.add(c);
        }
        repository.saveAll(clippings);
        Assertions.assertThat(repository.count()).isEqualTo(4);
    }

}