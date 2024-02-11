public class Artist implements Comparable<Artist> {
    private String name;
    private Date born;

    // Constructor
    public Artist(String name, Date born) {
        this.name = name;
        this.born = born;
    }

    // Getter for name
    public String getName() {

        return name;
    }

    // Getter for born date
    public Date getBorn() {

        return born;
    }

    // Implementing the compareTo() method from Comparable interface
    @Override
    public int compareTo(Artist other) {
        // First compare names ignoring case
        int nameComparison = name.compareToIgnoreCase(other.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }
        // If names are equal, compare dates of birth
        return born.compareTo(other.getBorn());
    }

    // Override equals() method
    @Override
    public boolean equals(Object obj) {
        Artist s = (Artist) obj;
        if(!this.name.toUpperCase().equals(s.name.toUpperCase())){
            return false;
        }
        if(this.born.compareTo(s.born) != 0){
            return false;
        }
        return true;
    }

    // Override toString() method
    @Override
    public String toString() {

        return name + ":" + born;
    }
}


