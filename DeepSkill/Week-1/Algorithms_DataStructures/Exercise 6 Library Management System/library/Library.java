package library;

public class Library {

    /**
     * Performs a linear search to find a book by title.
     * Time Complexity: O(n) average/worst case.
     */
    public static Book linearSearchByTitle(Book[] books, String targetTitle) {
        for (Book book : books) {
            if (book != null && book.getTitle().equalsIgnoreCase(targetTitle)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Performs a binary search to find a book by title.
     * The array MUST be sorted by title beforehand.
     * Time Complexity: O(log n) average/worst case.
     */
    public static Book binarySearchByTitle(Book[] books, String targetTitle) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(targetTitle);

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
