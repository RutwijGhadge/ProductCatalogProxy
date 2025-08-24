package product.ProductCatalogProxy.Clients.FakeStore.Dtos;


public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private FakeStoreRatingDTO ratingDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FakeStoreRatingDTO getRatingDTO() {
        return ratingDTO;
    }

    public void setRatingDTO(FakeStoreRatingDTO ratingDTO) {
        this.ratingDTO = ratingDTO;
    }
}
