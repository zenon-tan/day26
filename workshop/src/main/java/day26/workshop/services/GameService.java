package day26.workshop.services;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import day26.workshop.models.Game;
import day26.workshop.repo.CommentRepo;
import day26.workshop.repo.GameRepo;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Service
public class GameService {

    @Autowired
    GameRepo gRepo;

    @Autowired
    CommentRepo cRepo;

    public JsonObject getAllGames(int limit, int offset) {

        List<Document> doc = gRepo.getAllGames(offset, limit);

        List<Game> gameList = doc.stream()
        .map(i -> toGame(i))
        .toList();

        JsonArrayBuilder arr = Json.createArrayBuilder();

        for(Game i : gameList) {
            JsonObjectBuilder json = Json.createObjectBuilder()
            .add("game_id", i.getGid())
            .add("name", i.getName());
            arr.add(json);
        }

        JsonObject json = Json.createObjectBuilder()
        .add("games", arr)
        .add("offset", offset)
        .add("limit", limit)
        .add("total", gRepo.countAllGames())
        .add("timestamp", LocalDateTime.now().toString())
        .build();
        
        return json;
    }

    public JsonObject getAllGamesOrdered(int limit, int offset) {

        List<Document> doc = gRepo.getAllGamesByAsc(offset, limit);

        List<Game> gameList = doc.stream()
        .map(i -> toGame(i))
        .toList();

        JsonArrayBuilder arr = Json.createArrayBuilder();

        for(Game i : gameList) {
            JsonObjectBuilder json = Json.createObjectBuilder()
            .add("game_id", i.getGid())
            .add("name", i.getName())
            .add("ranking", i.getRanking());
            arr.add(json);
        }

        JsonObject json = Json.createObjectBuilder()
        .add("games", arr)
        .add("offset", offset)
        .add("limit", limit)
        .add("total", gRepo.countAllGames())
        .add("timestamp", LocalDateTime.now().toString())
        .build();
        
        return json;
    }

    public JsonObject getGameByGid(int gid) {

        Document doc = gRepo.getGameByGid(gid).get(0);

        Game game = toGame(doc);

        JsonObject json = Json.createObjectBuilder()
        .add("game_id", game.getGid())
        .add("name", game.getName())
        .add("year", game.getYear())
        .add("ranking", game.getRanking())
        .add("average", calculateAverage(gid))
        .add("users_rated", game.getUsersRated())
        .add("url", game.getUrl())
        .add("thumbnail", game.getImage())
        .add("timestamp", LocalDateTime.now().toString())
        .build();
        
        return json;
    }

    public Game toGame(Document doc) {
        Game game = new Game();
        game.setGid(doc.getInteger("gid"));
        game.setName(doc.getString("name"));
        game.setYear(doc.getInteger("year"));
        game.setRanking(doc.getInteger("ranking"));
        game.setUsersRated(doc.getInteger("users_rated"));
        game.setUrl(doc.getString("url"));
        game.setImage(doc.getString("image"));

        return game;
        
    }

    public Double calculateAverage(int gid) {

        List<Document> result = cRepo.getAverageByGid(gid);

        List<Integer> ratings = result.stream()
        .map(i -> i.getInteger("rating"))
        .toList();

        Double total = 0d;
        for(Integer rating : ratings) {
            total += rating;
        }

        Double avg = total/ratings.size();

        return avg;
        
    }
    
}
