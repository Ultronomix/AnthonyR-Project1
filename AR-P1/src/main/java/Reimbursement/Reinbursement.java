import java.sql.Blob;
import java.util.Objects;

public class Reinbursement {
    private String id;
    private float amount;
    private String submitted;
    private String resolved;
    private String description;
    private Blob receipt;
    private String payment_id;
    private String author_id;
    private String status_id;
    private String type_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return id;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getResolved() {
        return resolved;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Blob getReceipt() {
        return receipt;
    }

    public void setReceipt(Blob receipt) {
        this.receipt = receipt;
    }

    public String getAuthor_id() {
        return author_id;
    }
    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reinbursement reimbursement = (Reinbursement) o;
        return Objects.equals(id, reimbursement.id) && Objects.equals(amount, reimbursement.amount) &&
                Objects.equals(submitted, reimbursement.submitted) && Objects.equals(resolved, reimbursement.resolved) &&
                Objects.equals(description, reimbursement.description) && Objects.equals(receipt, reimbursement.receipt) &&
                Objects.equals(payment_id, reimbursement.payment_id) && Objects.equals(author_id, reimbursement.author_id) &&
                Objects.equals(status_id, reimbursement.status_id) && Objects.equals(status_id, reimbursement.status_id) &&
                Objects.equals(type_id, reimbursement.type_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, submitted, resolved, description, receipt, payment_id, author_id, status_id, type_id);
    }

    @Override
    public String toString() {
        return "Reinbursement{" +
                "id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                ", submitted='" + submitted + '\'' +
                ", description='" + description + '\'' +
                ", receipt='" + receipt + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", author_id=" + author_id +
                ", status_id=" + status_id +
                ", type_id=" + type_id +
                '}';
    }
}
