import java.text.DecimalFormat;
public class Album {
    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings; // reference to the head of a singly linked list of ratings.

    public Album(String title, Artist artist, Genre genre, Date released) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.released = released;
    }
    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for artist
    public Artist getArtist() {
        return artist;
    }

    // Getter for genre
    public Genre getGenre() {
        return genre;
    }

    // Getter for released date
    public Date getReleased() {
        return released;
    }
    public void rate(int star) {
        Rating newRating = new Rating();

        // Set star using the setter method in rating class
        newRating.setStar(star);

        if (ratings == null) {
            // If the list is empty, set the new node as the head
            ratings = newRating;
        } else {
            // Iterate thru the list to find the last node
            Rating current = ratings;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            // Adds the new node to the end of the list
            current.setNext(newRating);
        }
    }

    public double avgRatings() {
        if (ratings == null) {
            return 0.0; // Return 0 if there are no ratings
        }

        double sum = 0;
        int count = 0;
        Rating current = ratings;
        while (current != null) {
            sum += current.getStar();
            count++;
            current = current.getNext();
        }
        return sum / count;
    }
    // Override equals() method
    @Override
    public boolean equals(Object obj) {
        Album s = (Album) obj;

        if(!this.title.toUpperCase().equals(s.title.toUpperCase())){
            return false;
        }
        if(!(this.artist).equals(s.artist)){
            return false;
        }

        if(((this.released.compareTo(s.released)) != 0)){
            return false;
        }
            return true;
        }



    // Override toString() method
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##"); // Format average rating to 2 decimal places
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(title).append("] ");
        sb.append("Released ").append(released.toString()).append(" ");
        sb.append("[").append(artist).append("]").append(" ");
        sb.append("[").append(genre).append("] ");
        sb.append("Rating: ");
        if (ratings == null) {
            sb.append("none");
        } else {
            sb.append(ratings);
            sb.append("(average rating: ").append(df.format(avgRatings())).append(")");
        }
        return sb.toString();
    }
}

