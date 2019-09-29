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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Parcel() {

    }

    public Parcel(Date date, String status, int quatity, String product) {
        this.date = date;
        this.status = status;
        this.quantity = quatity;
        this.product = product;
    }

    // Getters
    public Long getID() { return this.id; }
    public Date getDate() { return this.date; }
    public int getQuantity() { return this.quantity; }
    public String getProduct() { return this.product; }

    // Setters
    public void setDate(Date date) { this.date = date; }
    public void setQuantity(int quant) { this.quantity = quant; }
    public void setProduct(String product) { this.product = product; }

    @Override
    public String toString() {
        String parcel = String.format(
            "Parcel[id: %d, Date: '%s', Quantity: %d, Product: '%s']", 
            id, date, quantity, product);
        return parcel;
    }
    
}
