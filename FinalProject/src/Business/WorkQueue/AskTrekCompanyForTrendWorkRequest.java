/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.Date;

/**
 *
 * @author dedhi
 */
public class AskTrekCompanyForTrendWorkRequest extends WorkRequest{
    private Date dateOfRequest;
    private Date dateOfResponse;
    private String comment;

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public Date getDateOfResponse() {
        return dateOfResponse;
    }

    public void setDateOfResponse(Date dateOfResponse) {
        this.dateOfResponse = dateOfResponse;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
