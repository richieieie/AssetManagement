package data;

import utils.Inputter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@SuppressWarnings("unused")
public class Request implements Serializable, DataRow {
    private String id;
    private String assetId;
    private String employeeId;
    private int quantity;
    private LocalDateTime time;

    public Request() {

    }

    public Request(String id, String assetId, String employeeId, int quantity, LocalDateTime time) {
        this.id = id;
        this.assetId = assetId;
        this.employeeId = employeeId;
        this.quantity = quantity;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setRequestId(String id) {
        this.id = id;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Request{" + "id='" + id + '\'' + ", assetId='" + assetId + '\'' + ", " +
                "employeeId='" + employeeId + '\'' + ", quantity=" + quantity + ", time=" + time.format(Inputter.formatter) + '}';
    }

    @Override
    public String toStringRow(int[] lens) {
        return String.format("|" + "%1$" + lens[0] + "s|" + "%2$" + lens[1] + "s|" +
                        "%3$" + lens[2] + "s|%4$" + lens[3] + "s|%5$" + lens[4] + "s|", id,
                assetId, employeeId, quantity, time.format(Inputter.formatter));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request request)) return false;
        return Objects.equals(getId(), request.getId());
    }

}
