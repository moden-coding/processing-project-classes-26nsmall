import java.util.ArrayList;

public class modal {
     private Block[][] blocks;
    private ArrayList<Coin> coins;
    private Player player;
    private int coinsCollected;
    private ArrayList<Integer> leaderboard;
    private int timeWhenDive = 0;
    public modal(int Size, Player player, Block[][] blocks, ArrayList<Integer> leaderboard) {
        this.blocks = blocks;
        this.coins = new ArrayList<>();
        this.player = player;
        this.coinsCollected = 0;
        this.leaderboard = leaderboard;
    }
    public Block[][] getBlocks() {
        return blocks;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public int timeWhenDive(){
        return timeWhenDive;
    }
    public void setTImewhendive(int set){
        timeWhenDive = set;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    public void collectCoin(Coin coin) {
        coins.remove(coin);
        coinsCollected++;
    }

    public ArrayList<Integer> getLeaderboard() {
        return leaderboard;
    }
    public void addScore(int score) {
        leaderboard.add(score);
        leaderboard.sort((a, b) -> b - a); // from chatgpt
        if (leaderboard.size() > 10) {
            leaderboard.remove(leaderboard.size() - 1);
        }
    }
}
