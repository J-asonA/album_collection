public class Collection {
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH_FACTOR = 4;
    private static final int NOT_FOUND = -1;
    private static final int EMPTY = 0;

    private Album[] albums;
    private int size;

    public Collection() {
        this.albums = new Album[INITIAL_CAPACITY];
        this.size = 0;
    }




    private void grow() {
        if(size>= albums.length) {
            Album[] newAlbums = new Album[albums.length + GROWTH_FACTOR];
            System.arraycopy(albums, 0, newAlbums, 0, size);
            albums = newAlbums;
        }
    }

    private int find(Album album) {
        for (int i = 0; i < size; i++) {
            if (albums[i].equals(album)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public Album getAlbum(int index){
        return albums[index];
    }


    public int findName(String name){
        for ( int i = 0; i< size; i++){
            if(albums[i].getTitle().equals(name)){
                return i;
            }
        }
        // If the album with the given name is not found, return -1 or any other appropriate default value
        return NOT_FOUND;
    }

    public boolean contains(Album album) {
        for(int i = 0; i< size; i++){
            if(albums[i].equals(album)){
                return true;
            }
        }
        return false;
    }

    public boolean add(Album album) {
        if (contains(album)) {
            return false; // Album already exists
        } else {
            if (size >= albums.length) {
                grow();
            }
            if(album.getReleased().isValid() && album.getArtist().getBorn().isValid()) {
                albums[size++] = album;
                return true;
            }
        }
        return false;
    }

    public boolean remove(Album album) {
        int index = find(album);
        if (index == NOT_FOUND) {
            return false; // Album not found
        }else {
            Album copy = albums[index];
            if(album.getTitle().equals(copy.getTitle()) && album.getArtist().equals(copy.getArtist())){
                albums[index] = null;
                return true;

            }
        }
        return false;
    }

    public void rate(Album album, int rating) {
        int index = find(album);
        if (index != NOT_FOUND) {
            albums[index].rate(rating);
        }
    }

    // Helper method for sorting albums by release date, then title
    private void sortByDate() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (albums[j].getReleased().compareTo(albums[j + 1].getReleased()) > 0 ||
                        (albums[j].getReleased().compareTo(albums[j + 1].getReleased()) == 0 &&
                                albums[j].getTitle().compareToIgnoreCase(albums[j + 1].getTitle()) > 0)) {
                    Album temp = albums[j];
                    albums[j] = albums[j + 1];
                    albums[j + 1] = temp;
                }
            }
        }
    }



//need to fix PD after displaying ---check size
//"C:\Program Files\Java\jdk-21\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.3.3\lib\idea_rt.jar=51998:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.3.3\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath C:\Users\Jason\IdeaProjects\album_collection\out\production\album_collection RunTest
//Collection Manager is up running.
//A,Fearless,Taylor Swift,12/13/1989,pop,11/8/2008
//Fearless(Taylor Swift:12/13/1989) added to the collection.
//PD
//[Fearless] Released 11/8/2008 [Taylor Swift:12/13/1989] [POP] Rating: none
//D,Fearless,Taylor Swift,12/13/1989
//Fearless(Taylor Swift:12/13/1989) removed from the collection.
//PD
//Exception in thread "main" java.lang.NullPointerException: Cannot invoke "Album.toString()" because "this.albums[i]" is null
//	at Collection.printByDate(Collection.java:120)
//	at CollectionManager.run(CollectionManager.java:72)
//	at RunTest.main(RunTest.java:6)
//
//Process finished with exit code 1
    public void printByDate(){
        sortByDate();
        if(size == EMPTY || albums == null){
            System.out.println("Collection is empty!");

        }else{
        for (int i = 0; i < size; i++) {
            System.out.println(albums[i].toString());
        }
        }
    } //sort by release date, then title

    // Helper method for sorting albums by genre, then artist
    private void sortByGenre() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (albums[j].getGenre().compareTo(albums[j + 1].getGenre()) > 0 ||
                        (albums[j].getGenre().compareTo(albums[j + 1].getGenre()) == 0 &&
                                albums[j].getArtist().compareTo(albums[j + 1].getArtist()) > 0)) {
                    Album temp = albums[j];
                    albums[j] = albums[j + 1];
                    albums[j + 1] = temp;
                }
            }
        }
    }

    public void printByGenre(){
        sortByGenre();
        if(size == EMPTY){
            System.out.println("Collection is empty!");
        }else {
            for (int i = 0; i < size; i++) {
                System.out.println(albums[i].toString());
            }
        }
    } //sort by genre, then artist

    // Helper method for sorting albums by average rating, then title
    private void sortByRating() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (albums[j].avgRatings() < albums[j + 1].avgRatings() ||
                        (albums[j].avgRatings() == albums[j + 1].avgRatings() &&
                                albums[j].getTitle().compareToIgnoreCase(albums[j + 1].getTitle()) > 0)) {
                    Album temp = albums[j];
                    albums[j] = albums[j + 1];
                    albums[j + 1] = temp;
                }
            }
        }
    }

    public void printByRating(){
        sortByRating();
        if(size == EMPTY){
            System.out.println("Collection is empty!");
        }else {
            for (int i = 0; i < size; i++) {
                System.out.println(albums[i].toString());
            }
        }
    }//sort by average rating, then title
}
