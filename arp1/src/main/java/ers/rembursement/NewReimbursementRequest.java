package ers.rembursement;


import ers.common.Request;

import java.sql.Timestamp;

public class NewReimbursementRequest implements Request<Reimbursement> {

    private String reimbId;
    private float amount;
    private String submitted;
    private String description;
    private String paymentId;
    private String authorId;
    private String typeId;

    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    

    @Override
    public String toString() {
        return "NewReimbursementRequest [amount=" + amount + ", authorId=" + authorId + ", description=" + description
                + ", paymentId=" + paymentId + ", reimbId=" + reimbId + ", submitted=" + submitted + ", typeId="
                + typeId + "]";
    }

    @Override
    public Reimbursement extractEntity() {
        Reimbursement extractEntity = new Reimbursement();
        extractEntity.setId(this.reimbId);
        extractEntity.setAmount(this.amount);
        extractEntity.setSubmitted(Timestamp.valueOf(this.submitted.toString()));
        extractEntity.setDescription(this.description);
        extractEntity.setPayment_id(this.paymentId);
        extractEntity.setAuthor_id(this.authorId);
        extractEntity.setType_id(this.typeId);
        return null;
    }

}
