
public class SearchMessage extends Message {

    private String search_way;
    private String search_element;

    public SearchMessage(String message, String search_way, String search_element) {
        super(message);
        this.search_way = search_way;
        this.search_element = search_element;
    }

    public String getSearch_way() {
        return search_way;
    }

    public String getSearch_element() {
        return search_element;
    }

}
