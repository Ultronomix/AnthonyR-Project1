package ers.rembursement;

import ers.common.ResourceCreationResponse;
import ers.common.exceptions.InvalidRequestException;
import ers.common.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class ReimbService {

    private final ReimbDAO reimbDAO;

    public ReimbService (ReimbDAO reimbDAO) {
        this.reimbDAO = reimbDAO;
    }

    public List<ReimbResponse> getAllReimb() {

        return reimbDAO.getAllReimbs().stream()
                       .map(ReimbResponse::new)
                       .collect(Collectors.toList());
    }

    public ReimbResponse getReimbById(String reimbId) {
        if (reimbId == null || reimbId.length() <= 0) {
            throw new InvalidRequestException("A non-empty id must be provided!");
        }

        try {
            return reimbDAO.findReimbById(reimbId)
                            .map(ReimbResponse::new)
                            .orElseThrow(ResourceNotFoundException::new);
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("An invalid UUID string was provided.");
        }

    }

    public void updateReimb(UpdateReimbRequest updateReimbRequest) {
        System.out.println(updateReimbRequest);

        Reimbursement reimbToUpdate = reimbDAO.findReimbById(updateReimbRequest.getReimbId()).orElseThrow(ResourceNotFoundException::new);
    
        if (updateReimbRequest.getAmount() != 0) {
            reimbToUpdate.setAmount(reimbToUpdate.getAmount());

        }

        if (updateReimbRequest.getResolved() != null) {
            reimbToUpdate.setResolved(reimbToUpdate.getResolved());

        }

        if (updateReimbRequest.getResolverId() != null) {
            reimbToUpdate.setResolver_id (reimbToUpdate.getResolver_id());

        }

        if (updateReimbRequest.getStatusId() != null) {
            reimbToUpdate.setStatus_id(reimbToUpdate.getStatus_id());

        }

        if (updateReimbRequest.getTypeId() != null) {
            reimbToUpdate.setType_id(reimbToUpdate.getType_id());
        }

        reimbDAO.updateReimb(reimbToUpdate);

    }

    public ResourceCreationResponse create(NewReimbursementRequest newReimb) {

        if (newReimb == null) {
            throw new InvalidRequestException("Provided request payload was null.");

        }

        if (newReimb.getAmount() == 0) {
            throw new InvalidRequestException("Provided request payload was null.");
       
        }

        if (newReimb.getTypeId() == null){

        }

        Reimbursement reimbToPersist = newReimb.extractEntity();
        String newReimbId = reimbDAO.save(reimbToPersist);
        return new ResourceCreationResponse(newReimbId);
    }
    
}

