import java.util.Scanner;

public class CollectionManager {
    private Collection collection;


    public CollectionManager() {
        this.collection = new Collection();
    }

    // Main method to process command lines
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Collection Manager is up running.");

        while (true) {
            String commandLine = scanner.nextLine();
            String[] cmd_arr = commandLine.split(",");
            String command = cmd_arr[0].toUpperCase().trim();

            switch (command) {

                //need to change uppercase case
                case "A":
                    if (cmd_arr.length == 6) {
                        Album album = createAlbum(cmd_arr);
                        if (album != null) {
                            if (!collection.contains(album)) {
                                collection.add(album);
                                System.out.println(album.getTitle() + "(" + album.getArtist().toString() + ")" + " added to the collection.");
                            } else {
                                System.out.println(album.getTitle() + "(" + album.getArtist().toString() + ")" + " is already in the collection.");
                            }
                            ;
                        }
                    } else {
                        System.out.println("Invalid command: " + commandLine);
                    }
                    break;


                case "D":
                    if (cmd_arr.length == 4) {
                        String aTitle = cmd_arr[1];
                        int albumIndex = collection.findName(aTitle);
                        Album copy = collection.getAlbum(albumIndex);
                        if (albumIndex != -1) {
                            collection.remove(collection.getAlbum(albumIndex));
                            System.out.println(copy.getTitle() + "(" + copy.getArtist().toString() + ")" + " removed from the collection.");
                        }else {
                            System.out.println("Invalid command: " + commandLine);
                        }
                        break;
                    }
                case "R":
                    if (cmd_arr.length == 5) {
                        String aTitle = cmd_arr[1];
                        int albumIndex = collection.findName(aTitle);
                        if (albumIndex != -1) {
                            int rating = Integer.parseInt(cmd_arr[4].trim());
                            collection.rate(collection.getAlbum(albumIndex), rating);
                            System.out.println("Album rated successfully.");
                        } else {
                            System.out.println("Album not found in the collection.");
                        }
                    } else {
                        System.out.println("Invalid command: " + commandLine);
                    }
                    break;
                case "PD":
                    if (cmd_arr[0].toUpperCase().equals(cmd_arr[0])) {
                        collection.printByDate();
                    } else {
                        System.out.println("Invalid Command!");
                    }
                    break;
                case "PG":
                    if (cmd_arr[0].toUpperCase().equals(cmd_arr[0])) {
                        collection.printByGenre();
                    } else {
                        System.out.println("Invalid Command!");
                    }
                    break;

                case "PR":
                    if (cmd_arr[0].toUpperCase().equals(cmd_arr[0])) {
                        collection.printByRating();
                    } else {
                        System.out.println("Invalid Command!");
                    }
                    break;
                case "Q":
                    System.out.println("Collection Manager terminated.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command: " + command);
                    break;
            }
        }
    }


    // Helper method to create an Album from command line arguments
    private Album createAlbum(String[] cmd_arr) {
        try {
            String title = cmd_arr[1].trim();
            String artistName = cmd_arr[2].trim();
            String artistDOBString = cmd_arr[3].trim();
            String genre = cmd_arr[4].trim();
            String rd = cmd_arr[5].trim();


            //DATE OF BIRTH -- Split info from cmd_arr by / and store them as month,day,year.
            String[] arrDOB = artistDOBString.split("/");
            int dob_month = Integer.parseInt(arrDOB[0]);
            int dob_day = Integer.parseInt(arrDOB[1]);
            int dob_year = Integer.parseInt(arrDOB[2]);

            Date artistDOB = new Date(dob_year, dob_month, dob_day);

            if (!artistDOB.isValid()) {
                System.out.println("Artist DOB: " + artistDOBString + " is invalid.");
                return null;
            }


            Artist artist = new Artist(artistName, artistDOB);
            Genre albumGenre = Genre.fromString(genre);


            //RELEASE DATE --- Split info from cmd_arr by / and store them as month,day,year.
            String[] arrReleaseDate = rd.split("/");
            int rd_month = Integer.parseInt(arrReleaseDate[0]);
            int rd_day = Integer.parseInt(arrReleaseDate[1]);
            int rd_year = Integer.parseInt(arrReleaseDate[2]);

            Date albumReleaseDate = new Date(rd_year, rd_month, rd_day);

            if(!albumReleaseDate.isValid()){
                System.out.println("Date released: " + rd + " is invalid.");
                return null;
            }
//HAVE TO CHANGE SO THAT WHEN DOB > RELEASED IT KNOWS ITS AN ERROR
            if(artistDOB.compareTo(albumReleaseDate) > 0){
                System.out.println("Date Released: " + rd + " is invalid.");
                return null;
            }

            return new Album(title, artist, albumGenre, albumReleaseDate);
        } catch (IllegalArgumentException ignored) {
        }
        return null;
    }
}


/* to fIX
-handle case where release date is before dob
-do delete method
-do ratings stars
 */