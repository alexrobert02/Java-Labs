package com.example.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
        RestTemplate restTemplate = new RestTemplate();

        // Add new players
        Player newPlayer1 = new Player(1L, "John Doe");
        Player addedPlayer1 = restTemplate.postForObject("http://localhost:8095/api/players", newPlayer1, Player.class);
        System.out.println("Added Player: " + addedPlayer1.getName());

        Player newPlayer2 = new Player(2L, "Will Smith");
        Player addedPlayer2 = restTemplate.postForObject("http://localhost:8095/api/players", newPlayer2, Player.class);
        System.out.println("Added Player: " + addedPlayer2.getName());

        // Add game history between two players
        GameHistory gameHistory = new GameHistory(1L, 2L, "2023-05-19", "Player 1 won");
        GameHistory addedGameHistory = restTemplate.postForObject("http://localhost:8095/api/game-history", gameHistory, GameHistory.class);
        System.out.println("Added Game History: " + addedGameHistory.getResult());

        // Modify the name of a player
        Long playerId1 = 1L;
        String newName = "Jane Smith";
        restTemplate.put("http://localhost:8095/api/players/{id}?name={name}", null, playerId1, newName);

        // Delete a player
        Long playerId2 = 2L;
        restTemplate.delete("http://localhost:8095/api/players/{id}", playerId2);

        // Get all players
        Player[] players = restTemplate.getForObject("http://localhost:8095/api/players", Player[].class);
        for (Player player : players) {
            System.out.println("Player: " + player.getName());
        }

        // Get all game history
        GameHistory[] gameHistories = restTemplate.getForObject("http://localhost:8095/api/game-history", GameHistory[].class);
        for (GameHistory history : gameHistories) {
            System.out.println("Game History Result: " + history.getResult());
        }
    }

}
