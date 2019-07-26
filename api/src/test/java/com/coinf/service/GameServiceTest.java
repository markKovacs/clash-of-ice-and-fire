package com.coinf.service;

import com.coinf.entity.instance.Game;
import com.coinf.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class GameServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public GameService gameService() {
            return new GameService();
        }
    }

    @Autowired
    private GameService gameService;

    @MockBean
    private GameRepository gameRepository;

    @Before
    public void setUp() {
        Long id = 1L;
        Game game = new Game(); // not a final mock
        game.setId(id);

        Mockito.when(gameRepository.findById(id))
                .thenReturn(Optional.of(game));
    }

    @Test
    public void sampleTest() {
        fail();
    }

}