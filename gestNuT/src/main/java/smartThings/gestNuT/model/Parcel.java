package smartThings.gestNuT.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parcel")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "totalPrice", nullable = false)
    private double totalPrice;

    @Column(name = "priceNut", nullable = false)
    private double priceNut;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Parcel() {

    }

    public Parcel(Date date, String status, int quantity, String product, double priceNut, double totalPrice) {
        this.date = date;
        this.status = status;
        this.quantity = quantity;
        this.product = product;
        this.priceNut = priceNut;
        this.totalPrice = totalPrice;
    }

    // Getters
    public Long getID() { return this.id; }
    public Date getDate() { return this.date; }
    public int getQuantity() { return this.quantity; }
    public String getProduct() { return this.product; }
    public User getUser() { return this.user; }
    public Address getAddress() { return this.address; }
    public double getTotalPrice() { return this.totalPrice; }
    public double getPriceNut() { return this.priceNut; }

    // Setters
    public void setDate(Date date) { this.date = date; }
    public void setQuantity(int quant) { this.quantity = quant; }
    public void setProduct(String product) { this.product = product; }
    public void setUser(User user) { this.user = user; }
    public void setAddress(Address address) { this.address = address; }
    public void setTotalPrice(double total) { this.totalPrice = total; }
    public void setPriceNut(double price) { this.priceNut = price; }

    @Override
    public String toString() {
        String parcel = String.format(
            "Parcel[id: %d, Date: '%s', Quantity: %d, Product: '%s', TotalPrice: %d, Price Nut: %d]", 
            id, date, quantity, product, totalPrice, priceNut);
        return parcel;
    }
    
}
