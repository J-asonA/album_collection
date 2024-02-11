public class Rating {
        private int star;  //linked list, star is head, next is obviously next
        private Rating next;
    public Rating() {
        this.star = 0; // Initialize to some default value
        this.next = null;
    }

    // Method to set the star rating
    public void setStar(int star) {
        this.star = star;
    }

    // Method to get the next reference
    public Rating getNext() {
        return this.next;
    }

    // Method to set the next reference
    public void setNext(Rating next) {
        this.next = next;
    }
    public int getStar(){
        return this.star;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Star: ").append(star);
        return sb.toString();
    }
    }

