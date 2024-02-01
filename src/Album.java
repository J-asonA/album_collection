public class Album {
    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings; //a linked list of ratings

    public Album(String title, Artist artist, Genre genre, Date released){
//        Album album1 = new Album();
//        album1.title = title;
//        album1.artist = artist;
//        album1.genre = genre;
//        album1.released = released;
    }
    public void rate(int star) {

    } //add a rating to the linked list
    public double avgRatings() {
    return 0.2;
    } //compute the average ratings
}
