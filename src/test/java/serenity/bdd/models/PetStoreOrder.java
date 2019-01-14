package serenity.bdd.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import serenity.bdd.config.DateTimeDeserializer;
import serenity.bdd.config.DateTimeSerializer;
import serenity.bdd.statuses.OrderStatus;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by : Volodymyr_Silitskyi
 * Created at : 12/2/2018
 */


public class PetStoreOrder {

    private long id;
    private long petId;
    private long quantity;
    @JsonDeserialize(using = DateTimeDeserializer.class)
    @JsonSerialize(using = DateTimeSerializer.class)
    private LocalDateTime shipDate;
    private OrderStatus status;
    private boolean complete = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getShipDate() {
        return shipDate;
    }

    public void setShipDate(LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetStoreOrder)) return false;
        PetStoreOrder that = (PetStoreOrder) o;
        return getId() == that.getId() &&
                getPetId() == that.getPetId() &&
                getQuantity() == that.getQuantity() &&
                isComplete() == that.isComplete() &&
                Objects.equals(getShipDate(), that.getShipDate()) &&
                Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getPetId(), getQuantity(), getShipDate(), getStatus(), isComplete());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("petId", petId)
                .append("quantity", quantity)
                .append("shipDate", shipDate)
                .append("status", status)
                .append("complete", complete)
                .toString();
    }
}
